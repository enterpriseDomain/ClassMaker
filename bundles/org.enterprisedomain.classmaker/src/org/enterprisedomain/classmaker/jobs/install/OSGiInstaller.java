/**
 * Copyright 2012-2018 Kyrill Zotkin
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.Revision;
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
			if (event.getBundle().getSymbolicName().equals(getProject().getName()) && (versionsAreEqual(
					Version.parseVersion(event.getBundle().getHeaders().get(Constants.BUNDLE_VERSION)),
					getContributionState().getProject().getVersion(), false)
					|| versionAreLess(
							Version.parseVersion(event.getBundle().getHeaders().get(Constants.BUNDLE_VERSION)),
							getContributionState().getProject().getVersion(), true)))
				switch (event.getType()) {
				case BundleEvent.INSTALLED:
					installed.release();
					break;
				case BundleEvent.UPDATED:
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

	public OSGiInstaller(long stateTimestamp) {
		super(Messages.JobNameInstaller, stateTimestamp);
		installed = new Semaphore(0);
		uninstalled = new Semaphore(0);
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		State contribution = getContributionState();
		if (contribution.getPhase() == Stage.DEFINED)
			return ClassMakerPlugin.createErrorStatus(Messages.ModelNotSpecified);
		BundleContext bundleContext = getContext();
		IStatus result = new MultiStatus(ClassMakerPlugin.PLUGIN_ID, IStatus.OK, "", null);
		try {
			Bundle existingBundle = null;
			int kind = -1;
			for (Bundle bundle : getBundles()) {
				if (versionsAreEqual(Version.parseVersion(bundle.getHeaders().get(Constants.BUNDLE_VERSION)),
						getContributionState().getProject().getVersion(), false)) {
					existingBundle = bundle;
					kind = 0;
					if (getContributionState().isEdit()
							&& bundle.getSymbolicName().equals(bundle.getSymbolicName() + ".edit")) {
						kind = 1;
					}
					if (getContributionState().isEditor()
							&& bundle.getSymbolicName().equals(bundle.getSymbolicName() + ".editor")) {
						kind = 2;
					}
				} else if (versionAreLess(Version.parseVersion(bundle.getHeaders().get(Constants.BUNDLE_VERSION)),
						getContributionState().getProject().getVersion(), false)) {
					existingBundle = bundle;
					kind = 0;
					if (getContributionState().isEdit()
							&& bundle.getSymbolicName().equals(bundle.getSymbolicName() + ".edit"))
						kind = 1;
					if (getContributionState().isEditor()
							&& bundle.getSymbolicName().equals(bundle.getSymbolicName() + ".editor"))
						kind = 2;
					result = addStatus(updateBundle(existingBundle, kind, bundleContext), result);
					if (result.isOK())
						return result;
				}
			}
			result = addStatus(installBundle(existingBundle, kind, bundleContext), result);
		} catch (IllegalArgumentException e) {
			throw new CoreException(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
		} catch (SecurityException e) {
			throw new CoreException(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
		} catch (InterruptedException e) {
			monitor.setCanceled(true);
			return Status.CANCEL_STATUS;
		} finally {
			monitor.worked(1);
		}
		return result;
	}

	private IStatus addStatus(IStatus status, IStatus parent) {
		List<IStatus> statuses = new ArrayList<IStatus>();
		for (IStatus st : parent.getChildren())
			statuses.add(st);
		statuses.add(status);
		parent = new MultiStatus(ClassMakerPlugin.PLUGIN_ID, IStatus.INFO,
				(IStatus[]) statuses.toArray(new IStatus[statuses.size()]), status.getMessage(), status.getException());
		return parent;
	}

	private IStatus updateBundle(Bundle existingBundle, int kind, BundleContext context) {
		if (existingBundle == null)
			return getWarningStatus(existingBundle, null);
		try {
			context.addBundleListener(listener);
			switch (kind) {
			case 0:
				existingBundle.update(new FileInputStream(
						ResourceUtils.getTargetResourcePath(getProject(), getContributionState()).toFile()));
				break;
			case 1:
				if (getContributionState().isEdit())
					existingBundle.update(new FileInputStream(
							ResourceUtils.getEditTargetResourcePath(getProject(), getContributionState()).toFile()));
				break;
			case 2:
				if (getContributionState().isEditor())
					existingBundle.update(new FileInputStream(
							ResourceUtils.getEditorTargetResourcePath(getProject(), getContributionState()).toFile()));
				break;
			}
			refreshBundle(existingBundle, context);
			installed.acquire();
			getContributionState().setPhase(getResultStage());
			return getOKStatus(existingBundle);
		} catch (

		BundleException e) {
			if (e.getType() == BundleException.RESOLVE_ERROR)
				return getWarningStatus(existingBundle, e);
			else if (e.getType() == BundleException.DUPLICATE_BUNDLE_ERROR)
				return getOKStatus(existingBundle);
			return getWarningStatus(existingBundle, e);
		} catch (FileNotFoundException e) {
			return getWarningStatus(existingBundle, e);
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} finally {
			getContext().removeBundleListener(listener);
		}
	}

	private IStatus installBundle(Bundle existingBundle, int kind, BundleContext context)
			throws InterruptedException, CoreException {
		Bundle bundle = null;
		try {
			context.addBundleListener(listener);
			if (existingBundle != null) {
				for (Revision revision : getContributionState().getProject().getRevisions().values())
					if (!versionsAreEqual(existingBundle.getVersion(), revision.getVersion(), false)
							&& revision.eIsSet(ClassMakerPackage.Literals.REVISION__STATE_HISTORY)
							&& !revision.getStateHistory().isEmpty())
						for (State state : revision.getStateHistory().values())
							if (state.getDomainModel().getGenerated() != null && EPackage.Registry.INSTANCE.getEPackage(
									getContributionState().getDomainModel().getGenerated().getNsURI()) != null)
								EPackage.Registry.INSTANCE
										.remove(getContributionState().getDomainModel().getGenerated().getNsURI());
				existingBundle.uninstall();
				uninstalled.acquire();
				refreshBundle(null, context);
			}
			switch (kind) {
			case -1:
			case 0:
				bundle = context.installBundle(URI
						.createFileURI(
								ResourceUtils.getTargetResourcePath(getProject(), getContributionState()).toString())
						.toString());
				break;
			case 1:
				if (getContributionState().isEdit())
					bundle = context
							.installBundle(URI
									.createFileURI(ResourceUtils
											.getEditTargetResourcePath(getProject(), getContributionState()).toString())
									.toString());
				break;
			case 2:
				if (getContributionState().isEditor())
					bundle = context.installBundle(URI
							.createFileURI(ResourceUtils
									.getEditorTargetResourcePath(getProject(), getContributionState()).toString())
							.toString());
				break;
			}
			if (bundle == null)
				return ClassMakerPlugin
						.createErrorStatus(NLS.bind(Messages.BundleNotInstalled, getProject().getName()));
			refreshBundle(bundle, context);
			installed.acquire();
			getContributionState().setPhase(getResultStage());
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
		try {
			notifyAll();
		} catch (IllegalMonitorStateException e) {
		}
		return bundles;
	}

	private IStatus getWarningStatus(Bundle bundle, Exception e) {
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
	public Stage getResultStage() {
		return Stage.INSTALLED;
	}

	@Override
	public Stage getDirtyStage() {
		return Stage.LOADED;
	}

}
