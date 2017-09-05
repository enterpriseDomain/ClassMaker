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
package org.enterprisedomain.classmaker.jobs.install;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.jobs.ContainerJob;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.Version;
import org.osgi.framework.wiring.FrameworkWiring;

public class OSGiInstaller extends ContainerJob {

	private BundleListener listener = new BundleListener() {

		@Override
		public void bundleChanged(BundleEvent event) {
			if (event.getBundle() == null)
				return;
			if (event.getBundle().getSymbolicName().equals(getProject().getName()) && versionsAreEqual(
					Version.parseVersion(event.getBundle().getHeaders().get(Constants.BUNDLE_VERSION)),
					getContributionState().getVersion(), false))
				switch (event.getType()) {
				case BundleEvent.INSTALLED:
					installed.release();
					break;
				case BundleEvent.UNINSTALLED:
					uninstalled.release();
					break;
				}
		}
	};
	private Semaphore installed;
	private Semaphore uninstalled;
	private FrameworkWiring frameworkWiring;

	public OSGiInstaller(int stateTimestamp) {
		super(Messages.JobNameInstaller, stateTimestamp);
		installed = new Semaphore(0);
		uninstalled = new Semaphore(0);
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		State contribution = getContributionState();
		if (contribution.getPhase() == Stage.DEFINED)
			return ClassMakerPlugin.createErrorStatus(Messages.ModelNotSpecified);
		IPath jarPath = ResourceUtils.getTargetResourcePath(getProject(), contribution);
		BundleContext bundleContext = getContext();
		try {
			Bundle existingBundle = null;
			for (Bundle bundle : getBundles())
				if (versionsAreEqual(Version.parseVersion(bundle.getHeaders().get(Constants.BUNDLE_VERSION)),
						getContributionState().getVersion(), false))
					existingBundle = bundle;
			return installBundle(existingBundle, jarPath, bundleContext);
		} catch (IllegalArgumentException e) {
			throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
		} catch (SecurityException e) {
			throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
		} catch (InterruptedException e) {
			monitor.setCanceled(true);
			return Status.CANCEL_STATUS;
		} finally {
			monitor.worked(1);
		}
	}

	private IStatus installBundle(Bundle existingBundle, IPath jarPath, BundleContext context)
			throws InterruptedException {
		Bundle bundle = null;
		try {
			context.addBundleListener(listener);
			if (existingBundle != null) {
				existingBundle.uninstall();
				uninstalled.acquire();
				refreshBundle(null, context);
			}
			bundle = context.installBundle(URI.createFileURI(jarPath.toString()).toString());
			if (bundle == null)
				return ClassMakerPlugin
						.createErrorStatus(NLS.bind(Messages.BundleNotInstalled, getProject().getName()));
			installed.acquire();
			getContributionState().setPhase(getResultStage());
			refreshBundle(bundle, context);
			return getOKStatus(bundle);
		} catch (BundleException e) {
			if (e.getType() == BundleException.DUPLICATE_BUNDLE_ERROR)
				return getOKStatus(existingBundle, bundle);
			return getWarningStatus(bundle, e);
		} finally {
			getContext().removeBundleListener(listener);
		}
	}

	protected Collection<Bundle> refreshBundle(Bundle bundle, BundleContext context) {
		Collection<Bundle> bundles = new ArrayList<Bundle>();
		if (bundle != null)
			bundles.add(bundle);
		frameworkWiring = context.getBundle(0).adapt(FrameworkWiring.class);
		frameworkWiring.refreshBundles(bundles, new FrameworkListener[0]);
		return bundles;
	}

	private IStatus getWarningStatus(Bundle bundle, BundleException e) {
		if (bundle == null)
			return ClassMakerPlugin.createWarningStatus(NLS.bind(Messages.BundleNo, getProject().getName()), e);
		else
			return ClassMakerPlugin.createWarningStatus(getStateStatusMessage(bundle), e);
	}

	private IStatus getOKStatus(Bundle existingBundle, Bundle bundle) {
		return ClassMakerPlugin.createOKStatus(Messages.OK + " " + getDuplicateStatusMessage(existingBundle, bundle));
	}

	private IStatus getOKStatus(Bundle bundle) {
		return ClassMakerPlugin.createOKStatus(Messages.OK + " " + getStateStatusMessage(bundle));
	}

	protected String getStateStatusMessage(Bundle bundle) {
		return NLS.bind(Messages.BundleState,
				new Object[] { bundle.getSymbolicName(), bundle.getHeaders().get(Constants.BUNDLE_VERSION),
						ClassMakerPlugin.bundleStateAsString(bundle.getState()) });
	}

	private String getDuplicateStatusMessage(Bundle existingBundle, Bundle bundle) {
		return NLS.bind(Messages.BundleDuplicate,
				new Object[] { existingBundle.getSymbolicName(),
						existingBundle.getHeaders().get(Constants.BUNDLE_VERSION),
						ClassMakerPlugin.bundleStateAsString(existingBundle.getState()), bundle.getSymbolicName(),
						bundle.getHeaders().get(Constants.BUNDLE_VERSION),
						ClassMakerPlugin.bundleStateAsString(bundle.getState()) });
	}

	@Override
	public Stage getPrerequisiteStage() {
		return Stage.EXPORTED;
	}

	@Override
	public Stage getResultStage() {
		return Stage.INSTALLED;
	}

	@Override
	public Stage getDirtyStage() {
		return Stage.LOADED;
	}

}
