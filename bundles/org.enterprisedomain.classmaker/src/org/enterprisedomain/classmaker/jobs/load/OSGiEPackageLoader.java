/**
 * Copyright 2012-2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.jobs.load;

import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.jobs.ContainerJob;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;
import org.osgi.framework.startlevel.BundleStartLevel;

public class OSGiEPackageLoader extends ContainerJob {

	private Semaphore loaded = new Semaphore(0);

	private Throwable exception;

	private BundleListener listener = new BundleListener() {

		@Override
		public void bundleChanged(BundleEvent event) {
			if (event.getBundle() == null)
				return;
			if (event.getBundle().getSymbolicName().equals(getProject().getName()))
				if ((event.getType() == BundleEvent.STARTED || event.getType() == BundleEvent.RESOLVED
						|| event.getType() == BundleEvent.LAZY_ACTIVATION)
						&& event.getBundle().getState() == Bundle.ACTIVE)
					try {
						doLoad(getContributionState(), event.getBundle());
					} catch (Exception e) {
						setException(e);
					}

		}
	};

	public OSGiEPackageLoader(int stateTimestamp) {
		super(Messages.JobNameLoader, stateTimestamp);
	}

	public IStatus work(IProgressMonitor monitor) throws CoreException {
		State contribution = getContributionState();
		if (contribution.getPhase() == Stage.DEFINED)
			return ClassMakerPlugin.createErrorStatus(Messages.ModelNotSpecified);
		Bundle osgiBundle = null;
		try {
			for (Bundle bundle : getBundles())
				if (versionsAreEqual(Version.parseVersion(bundle.getHeaders().get(Constants.BUNDLE_VERSION)),
						getContributionState().getVersion(), false))
					osgiBundle = bundle;
			if (osgiBundle != null) {
				if (osgiBundle.getHeaders().get(Constants.FRAGMENT_HOST) == null) {
					getContext().removeBundleListener(listener);
					getContext().addBundleListener(listener);
					if (osgiBundle.getBundleId() != 0)
						osgiBundle.adapt(BundleStartLevel.class).setStartLevel(4);
					try {
						osgiBundle.start(getOptions(false));
					} catch (BundleException e) {
						setException(e);
					}
				}
			} else
				return ClassMakerPlugin.createErrorStatus(NLS.bind(Messages.BundleNotFound, getProject().getName()));
			if (exception == null) {
				try {
					loaded.acquire();
				} catch (InterruptedException e) {
					return Status.CANCEL_STATUS;
				}
				return getStatus(osgiBundle);
			} else {
				throw new CoreException(ClassMakerPlugin.createErrorStatus(exception));
			}
		} finally {
			monitor.done();
			setException(null);
			getContext().removeBundleListener(listener);
		}
	}

	private IStatus getStatus(Bundle osgiBundle) {
		String ePackagesMsg = ""; //$NON-NLS-1$
		boolean warning = false;
		if (getContributionState().getDomainModel().getGenerated() == null) {
			ePackagesMsg = Messages.EPackageNo;
			warning = true;
		} else {
			EPackage ePackage = getContributionState().getDomainModel().getGenerated();
			if (ePackage != null)
				ePackagesMsg = ePackagesMsg + ePackage.getNsURI() + ", "; //$NON-NLS-1$
			if (ePackagesMsg.length() > 2)
				ePackagesMsg = ePackagesMsg.subSequence(0, ePackagesMsg.length() - 2).toString();
			else {
				ePackagesMsg = Messages.EPackageNo;
				warning = true;
			}
		}
		if (warning)
			return ClassMakerPlugin.createWarningStatus(
					NLS.bind(Messages.EPackageClassLoadComplete, new Object[] { osgiBundle.getSymbolicName(),
							osgiBundle.getHeaders().get(Constants.BUNDLE_VERSION), ePackagesMsg }));
		return ClassMakerPlugin.createOKStatus(Messages.OK + " " //$NON-NLS-1$
				+ NLS.bind(Messages.EPackageClassLoadComplete, new Object[] { osgiBundle.getSymbolicName(),
						osgiBundle.getHeaders().get(Constants.BUNDLE_VERSION), ePackagesMsg }));

	}

	private int getOptions(boolean autoStart) {
		return autoStart ? Bundle.START_TRANSIENT : 0;
	}

	private synchronized void doLoad(State state, Bundle osgiBundle) throws Exception {
		try {
			EPackage model = state.getDomainModel().getDynamic();
			String packageClassName = CodeGenUtil.safeName(model.getName()) + "." //$NON-NLS-1$
					+ state.getPackageClassName();
			Class<?> packageClass = null;
			try {
				packageClass = osgiBundle.loadClass(packageClassName);
			} catch (Exception e) {
				e.printStackTrace();
				setException(e);
				throw e;
			}
			EPackage ePackage = null;
			try {
				ePackage = (EPackage) packageClass.getField("eINSTANCE").get(null); // $NON-NLS-1$ //$NON-NLS-1$
				if (ePackage != null)
					getContributionState().getDomainModel().setGenerated(ePackage);
			} catch (ClassCastException e) {
				if (ePackage != null)
					getContributionState().getDomainModel().setGenerated(ePackage);
			} catch (Exception e) {
				e.printStackTrace();
				setException(e);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			setException(e);
		} finally {
			loaded.release();
			getContributionState().getContribution().setNeedCompletionNotification(true);
		}
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	@Override
	public Stage getPrerequisiteStage() {
		return Stage.INSTALLED;
	}

	@Override
	public Stage getResultStage() {
		return Stage.LOADED;
	}

	@Override
	public Stage getDirtyStage() {
		return Stage.LOADED;
	}

}
