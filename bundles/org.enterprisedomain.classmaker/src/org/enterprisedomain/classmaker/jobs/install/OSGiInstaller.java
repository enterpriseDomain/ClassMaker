/**
 * Copyright 2012-2021 Kyrill Zotkin
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.core.bundle.WorkspaceBundleModel;
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
			if ((event.getBundle().getSymbolicName().equals(getProject().getName())
					|| getContributionState().isEdit()
							&& event.getBundle().getSymbolicName().equals(getProject().getName() + ".edit")
					|| getContributionState().isEditor()
							&& event.getBundle().getSymbolicName().equals(getProject().getName() + ".editor"))
					&& (versionsAreEqual(
							Version.parseVersion(event.getBundle().getHeaders().get(Constants.BUNDLE_VERSION)),
							getContributionState().getProject().getVersion(), false)
							|| versionAreLess(getContributionState().getProject().getVersion(),
									Version.parseVersion(event.getBundle().getHeaders().get(Constants.BUNDLE_VERSION)),
									true)))
				switch (event.getType()) {
				case BundleEvent.RESOLVED:
				case BundleEvent.INSTALLED:
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

	public OSGiInstaller(int depth, long stateTimestamp) {
		super(Messages.JobNameInstaller, depth, stateTimestamp);
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
			for (Bundle bundle : getBundles()) {
				if (versionsAreEqual(Version.parseVersion(bundle.getHeaders().get(Constants.BUNDLE_VERSION)),
						getContributionState().getProject().getVersion(), false)) {
					existingBundle = bundle;
				} else if (versionAreLess(Version.parseVersion(bundle.getHeaders().get(Constants.BUNDLE_VERSION)),
						getContributionState().getProject().getVersion(), false)) {
					existingBundle = bundle;
					if (getContributionState().isEdit())
						result = addStatus(updateBundle(existingBundle, 1, bundleContext), result);
					if (getContributionState().isEditor())
						result = addStatus(updateBundle(existingBundle, 2, bundleContext), result);
					if (result.isOK())
						return result;
				}
			}
			((ContainerJob) getNextJob()).getInstalledBundles().clear();
			result = addStatus(installBundle(existingBundle, 0, bundleContext), result);
			if (getContributionState().isEdit())
				result = addStatus(installBundle(existingBundle, 1, bundleContext), result);
			if (getContributionState().isEditor())
				result = addStatus(installBundle(existingBundle, 2, bundleContext), result);
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
		List<Bundle> bundles = new ArrayList<Bundle>();
		if (existingBundle == null)
			return getWarningStatus(bundles, null);
		try {
			context.addBundleListener(listener);
			switch (kind) {
			case 0:
				existingBundle.update(
						new FileInputStream(ResourceUtils.getTargetResourcePath(getContributionState()).toFile()));
				bundles.add(existingBundle);
				break;
			case 1:
				if (getContributionState().isEdit())
					existingBundle.update(new FileInputStream(
							ResourceUtils.getEditTargetResourcePath(getContributionState()).toFile()));
				break;
			case 2:
				if (getContributionState().isEditor())
					existingBundle.update(new FileInputStream(
							ResourceUtils.getEditorTargetResourcePath(getContributionState()).toFile()));
				break;
			}
			refreshBundle(bundles, context);
			installed.acquire();
			getContributionState().setPhase(getResultStage());
			return getOKStatus(bundles);
		} catch (BundleException e) {
			if (e.getType() == BundleException.RESOLVE_ERROR)
				return getWarningStatus(bundles, e);
			else if (e.getType() == BundleException.DUPLICATE_BUNDLE_ERROR)
				return getOKStatus(bundles);
			return getWarningStatus(bundles, e);
		} catch (FileNotFoundException e) {
			return getWarningStatus(bundles, e);
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} finally {
			getContext().removeBundleListener(listener);
		}
	}

	private IStatus installBundle(Bundle existingBundle, int kind, BundleContext context)
			throws InterruptedException, CoreException {
		List<Bundle> bundles = new ArrayList<Bundle>();
		String uri = null;
		Set<String> requiredUris = new HashSet<String>();
		try {
			context.addBundleListener(listener);
			if (existingBundle != null) {
				for (Revision revision : getContributionState().getProject().getRevisions().values())
					if (!versionsAreEqual(existingBundle.getVersion(), revision.getVersion(), false)
							&& revision.eIsSet(ClassMakerPackage.Literals.REVISION__STATE_HISTORY)
							&& !revision.getStateHistory().isEmpty())
						for (State state : revision.getStateHistory().values())
							if (state.getDomainModel().getGenerated() != null
									&& state.getDomainModel().getGenerated() instanceof EPackage
									&& EPackage.Registry.INSTANCE.getEPackage(
											((EPackage) state.getDomainModel().getGenerated()).getNsURI()) != null)
								EPackage.Registry.INSTANCE
										.remove(((EPackage) state.getDomainModel().getGenerated()).getNsURI());
				existingBundle.uninstall();
				refreshBundle(null, context);
				uninstalled.acquire();
			}
			switch (kind) {
			case -1:
			case 0:
				uri = URI.createFileURI(ResourceUtils.getTargetResourcePath(getContributionState()).toString())
						.toString();
				extractRequiredURIs(requiredUris, getProject());
				break;
			case 1:
				if (getContributionState().isEdit()) {
					uri = URI.createFileURI(ResourceUtils.getEditTargetResourcePath(getContributionState()).toString())
							.toString();
					extractRequiredURIs(requiredUris, getEditProject());
				}
				break;
			case 2:
				if (getContributionState().isEditor()) {
					uri = URI
							.createFileURI(ResourceUtils.getEditorTargetResourcePath(getContributionState()).toString())
							.toString();
					extractRequiredURIs(requiredUris, getEditorProject());
				}
				break;
			}
			for (String requiredUri : requiredUris) {
				try {
					bundles.add(context.installBundle(requiredUri));
				} catch (BundleException e) {
					if (e.getType() == BundleException.DUPLICATE_BUNDLE_ERROR) {
						((ContainerJob) getNextJob()).getInstalledBundles().addAll(bundles);
					}
				}
			}
			bundles.add(context.installBundle(uri));
			if (bundles.isEmpty())
				return ClassMakerPlugin
						.createErrorStatus(NLS.bind(Messages.BundleNotInstalled, getProject().getName()));
			((ContainerJob) getNextJob()).getInstalledBundles().addAll(bundles);
			refreshBundle(bundles, context);
//			installed.acquire();
			getContributionState().setPhase(getResultStage());
			return getOKStatus(bundles);
		} catch (BundleException e) {
			if (e.getType() == BundleException.DUPLICATE_BUNDLE_ERROR)
				return getOKStatus(existingBundle, bundles);
			if (e.getCause() instanceof FileNotFoundException) {
				Thread.sleep(3000);
				try {
					for (String requiredUri : requiredUris)
						bundles.add(context.installBundle(requiredUri));
					bundles.add(context.installBundle(uri));
					if (bundles.isEmpty())
						return ClassMakerPlugin
								.createErrorStatus(NLS.bind(Messages.BundleNotInstalled, getProject().getName()));
					refreshBundle(bundles, context);
//					installed.acquire();
					getContributionState().setPhase(getResultStage());
					return getOKStatus(bundles);
				} catch (BundleException ex) {
					if (ex.getType() == BundleException.DUPLICATE_BUNDLE_ERROR)
						return getOKStatus(existingBundle, bundles);
				}
			}
			return getWarningStatus(bundles, e);
		} finally {
			getContext().removeBundleListener(listener);
		}
	}

	private void extractRequiredURIs(Set<String> requiredUris, IProject project) {
		PluginModelManager modelManager = PDECore.getDefault().getModelManager();
		IPluginModelBase model = modelManager.findModel(project);
		if (model != null) {
			BundleDescription bundleDescription = ((IPluginModelBase) model).getBundleDescription();
			if (bundleDescription != null)
				for (BundleSpecification requiredBundle : bundleDescription.getRequiredBundles()) {
					IPluginModelBase requiredModel = modelManager.findModel(requiredBundle.getName());
					if (requiredModel instanceof BundlePluginModel)
						if (((BundlePluginModel) requiredModel).getBundleModel() instanceof WorkspaceBundleModel) {
							BundleDescription bd = requiredModel.getBundleDescription();
							if (!isInstalled(bd))
								requiredUris.add(URI
										.createFileURI(ResourceUtils.getExportDestination(getContributionState())
												.append("plugins") //$NON-NLS-1$
												.addTrailingSeparator()
												.append(bd.getSymbolicName() + "_" + bd.getVersion().toString())
												.addFileExtension("jar").toString()) // $NON-NLS-1
										.toString());
						}

				}
		}
	}

	/**
	 * @return whether the specified bundle is installed.
	 */
	private boolean isInstalled(BundleDescription bd) {
		for (Bundle b : getContext().getBundles()) {
			if (b.getSymbolicName().equals(bd.getSymbolicName()) && b.getVersion().equals(bd.getVersion())) {
				return true;
			}
		}
		return false;
	}

	protected Collection<Bundle> refreshBundle(Collection<Bundle> bundles, BundleContext context) {
		frameworkWiring = context.getBundle(0).adapt(FrameworkWiring.class);
		if (bundles != null)
			frameworkWiring.refreshBundles(bundles, new FrameworkListener[] { (e) -> {
				if (bundles.contains(context.getBundle())) {
					uninstalled.release();
					installed.release();
				}
			} });
		try {
			notifyAll();
		} catch (IllegalMonitorStateException e) {
		}
		return bundles;
	}

	private IStatus getWarningStatus(Collection<Bundle> bundles, Exception e) {
		if (bundles.isEmpty())
			return ClassMakerPlugin.createWarningStatus(NLS.bind(Messages.BundleNo, getProject().getName()), e);
		else
			return ClassMakerPlugin.createWarningStatus(getStateStatusMessage(bundles), e);
	}

	private IStatus getOKStatus(Bundle existingBundle, Collection<Bundle> bundles) {
		return ClassMakerPlugin.createOKStatus(Messages.OK + " " + getDuplicateStatusMessage(existingBundle, bundles));
	}

	private IStatus getOKStatus(Collection<Bundle> bundles) {
		return ClassMakerPlugin.createOKStatus(Messages.OK + " " + getStateStatusMessage(bundles));
	}

	protected String getStateStatusMessage(Collection<Bundle> bundles) {
		StringBuffer states = new StringBuffer();
		for (Bundle bundle : bundles)
			states.append(NLS.bind(Messages.BundleState,
					new Object[] { bundle.getSymbolicName(), bundle.getHeaders().get(Constants.BUNDLE_VERSION),
							ClassMakerPlugin.bundleStateAsString(bundle.getState()) })
					+ " ");
		return states.toString().trim();
	}

	private String getDuplicateStatusMessage(Bundle existingBundle, Collection<Bundle> bundles) {
		StringBuffer states = new StringBuffer();
		for (Bundle bundle : bundles)
			states.append(NLS.bind(Messages.BundleDuplicate,
					new Object[] { existingBundle.getSymbolicName(),
							existingBundle.getHeaders().get(Constants.BUNDLE_VERSION),
							ClassMakerPlugin.bundleStateAsString(existingBundle.getState()), bundle.getSymbolicName(),
							bundle.getHeaders().get(Constants.BUNDLE_VERSION),
							ClassMakerPlugin.bundleStateAsString(bundle.getState()) }));
		return states.toString();
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