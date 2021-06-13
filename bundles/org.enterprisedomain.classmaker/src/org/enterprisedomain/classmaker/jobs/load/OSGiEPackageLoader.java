/**
 * Copyright 2012-2020 Kyrill Zotkin
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

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.jface.operation.IRunnableWithProgress;
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

	private Semaphore loaded;

	private Throwable exception;

	private BundleListener listener = new BundleListener() {

		@Override
		public void bundleChanged(BundleEvent event) {
			if (event != null && event.getBundle() == null)
				return;
			if (getProject() != null && event.getBundle().getSymbolicName().equals(getProject().getName())) {
				if ((event.getType() == BundleEvent.STARTED || event.getType() == BundleEvent.RESOLVED
						|| event.getType() == BundleEvent.LAZY_ACTIVATION)
						&& event.getBundle().getState() == Bundle.ACTIVE)
					try {
						doLoad(getContributionState(), event.getBundle(), 0);
					} catch (Exception e) {
						setException(e);
					}
			} else if (getContributionState().isEdit()
					&& event.getBundle().getSymbolicName().equals(getProject().getName() + ".edit")) {
				if ((event.getType() == BundleEvent.STARTED || event.getType() == BundleEvent.RESOLVED
						|| event.getType() == BundleEvent.LAZY_ACTIVATION)
						&& event.getBundle().getState() == Bundle.ACTIVE)
					try {
						doLoad(getContributionState(), event.getBundle(), 1);
					} catch (Exception e) {
						setException(e);
					}
			} else if (getContributionState().isEditor()
					&& event.getBundle().getSymbolicName().equals(getProject().getName() + ".editor")) {
				if ((event.getType() == BundleEvent.STARTED || event.getType() == BundleEvent.RESOLVED
						|| event.getType() == BundleEvent.LAZY_ACTIVATION)
						&& event.getBundle().getState() == Bundle.ACTIVE)
					try {
						doLoad(getContributionState(), event.getBundle(), 2);
					} catch (Exception e) {
						setException(e);
					}
			} else
				loaded.release();
		}
	};

	public OSGiEPackageLoader(int depth, long stateTimestamp) {
		super(Messages.JobNameLoader, depth, stateTimestamp);
	}

	public IStatus work(IProgressMonitor monitor) throws CoreException {
		State contribution = getContributionState();
		if (contribution.getPhase() == Stage.DEFINED)
			return ClassMakerPlugin.createErrorStatus(Messages.ModelNotSpecified);
		Bundle osgiBundle = null;
		Bundle editBundle = null;
		Bundle editorBundle = null;
		int loadedCount = 0;
		try {
			for (Bundle bundle : getBundles())
				if (versionsAreEqual(Version.parseVersion(bundle.getHeaders().get(Constants.BUNDLE_VERSION)),
						getContributionState().getProject().getVersion(), false)) {
					osgiBundle = bundle;
					if (getContributionState().isEdit()
							&& bundle.getSymbolicName().equals(getProject().getName() + ".edit")) {
						editBundle = bundle;
						loadedCount++;
					}
					if (getContributionState().isEditor()
							&& bundle.getSymbolicName().equals(getProject().getName() + ".editor")) {
						editorBundle = bundle;
						loadedCount++;
					}
				}
			loaded = new Semaphore(loadedCount);
			getContext().addBundleListener(listener);
			if (osgiBundle != null) {
				start(osgiBundle);
				if (editBundle != null)
					start(editBundle);
				if (editorBundle != null)
					start(editorBundle);
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
			getContext().removeBundleListener(listener);
		}
	}

	private void start(Bundle osgiBundle) {
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
	}

	@Override
	protected boolean terminate() {
		return true;
	}

	private IStatus getStatus(Bundle osgiBundle) {
		String ePackagesMsg = ""; //$NON-NLS-1$
		boolean warning = false;
		if (getContributionState().getDomainModel().getGenerated() == null) {
			ePackagesMsg = Messages.EPackageNo;
			warning = true;
		} else {
			EPackage ePackage = null;
			if (getContributionState().getDomainModel().getGenerated() instanceof EPackage)
				ePackage = (EPackage) getContributionState().getDomainModel().getGenerated();
			else
				return ClassMakerPlugin.createInfoStatus(NLS.bind("EObject {0} is not a EPackage",
						getContributionState().getDomainModel().getGenerated()));
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

	private synchronized void doLoad(State state, Bundle osgiBundle, int kind) throws Exception {
		switch (kind) {
		case 0:
			ClassMakerPlugin.runWithProgress(new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						EObject model = state.getDomainModel().getDynamic();
						String packageClassName = null;
						if (model instanceof EPackage)
							packageClassName = CodeGenUtil.safeName(((EPackage) model).getName()) + "." //$NON-NLS-1$
									+ state.getPackageClassName();
						Class<?> packageClass = null;
						try {
							packageClass = osgiBundle.loadClass(packageClassName);
						} catch (Exception e) {
							e.printStackTrace();
							setException(e);
						}

						EPackage ePackage = null;
						try {
							ePackage = (EPackage) packageClass.getField("eINSTANCE").get(null); // $NON-NLS-1$
							if (ePackage != null) {
								getContributionState().getDomainModel().setGenerated(ePackage);
								registerEPackage(Registry.INSTANCE, ePackage);
								if (getContributionState().getRevision().getProject().getWorkspace()
										.getResourceSet() != null)
									registerEPackage(getContributionState().getRevision().getProject().getWorkspace()
											.getResourceSet().getPackageRegistry(), ePackage);
							}
						} catch (ClassCastException e) {
							if (ePackage != null) {
								getContributionState().getDomainModel().setGenerated(ePackage);
								registerEPackage(Registry.INSTANCE, ePackage);
								if (getContributionState().getRevision().getProject().getWorkspace()
										.getResourceSet() != null)
									registerEPackage(getContributionState().getRevision().getProject().getWorkspace()
											.getResourceSet().getPackageRegistry(), ePackage);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						setException(e);
						throw new InvocationTargetException(e);
					} finally {
						loaded.release();
						getContributionState().getProject().setNeedCompletionNotification(true);
					}
				}
			});
			break;
		case 1:
			ClassMakerPlugin.runWithProgress(new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

					EObject model0 = state.getDomainModel().getDynamic();
					String pluginClassName = null;
					if (model0 instanceof EPackage)
						pluginClassName = CodeGenUtil.safeName(((EPackage) model0).getName()) + ".provider." //$NON-NLS-1$
								+ state.getEditPluginClassName();
					Class<?> pluginClass = null;
					try {
						pluginClass = osgiBundle.loadClass(pluginClassName);
					} catch (Exception e) {
						e.printStackTrace();
						setException(e);
					}
					try {
						pluginClass.getField("INSTANCE").get(null);
					} catch (Exception e) {
						e.printStackTrace();
						setException(e);
						throw new InvocationTargetException(e);
					} finally {
						loaded.release();
						if (loaded.availablePermits() >= 1)
							getContributionState().getProject().setNeedCompletionNotification(true);
					}
				}
			});
			break;
		case 2:
			ClassMakerPlugin.runWithProgress(new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

					final EObject model1 = state.getDomainModel().getDynamic();
					String pluginClassName1 = null;
					if (model1 instanceof EPackage)
						pluginClassName1 = CodeGenUtil.safeName(((EPackage) model1).getName()) + ".presentation." //$NON-NLS-1$
								+ state.getEditorPluginClassName();
					Class<?> pluginClass1 = null;
					try {
						pluginClass1 = osgiBundle.loadClass(pluginClassName1);
					} catch (Exception e) {
						e.printStackTrace();
						setException(e);
					}
					try {
						pluginClass1.getField("INSTANCE").get(null);
					} catch (Exception e) {
						e.printStackTrace();
						setException(e);
						throw new InvocationTargetException(e);
					} finally {
						loaded.release();
						if (loaded.availablePermits() >= 1)
							getContributionState().getProject().setNeedCompletionNotification(true);
					}
				}
			});
			break;
		}
	}

	private void registerEPackage(Registry registry, EPackage ePackage) {
		registry.put(ePackage.getNsURI(), ePackage);
	}

	public void setException(Throwable exception) {
		this.exception = exception;
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
