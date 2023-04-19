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
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.ClassMakerPackage;
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
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.service.packageadmin.PackageAdmin;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;

public class OSGiEPackageLoader extends ContainerJob implements BundleTrackerCustomizer<EPackage> {

	private Semaphore loaded;

	private Throwable exception;

	private BundleListener listener = new BundleListener() {

		@Override
		public void bundleChanged(BundleEvent event) {
			if (event != null && event.getBundle() == null)
				return;
			try {
				if (getProject() != null && event.getBundle().getSymbolicName().equals(getProject().getName())) {
					if ((event.getType() == BundleEvent.STARTED || event.getType() == BundleEvent.RESOLVED
							|| event.getType() == BundleEvent.LAZY_ACTIVATION)
							&& (event.getBundle().getState() == Bundle.ACTIVE
									|| event.getBundle().getState() == Bundle.STARTING))
						try {
							doLoad(getContributionState(), event.getBundle(), 0);
						} catch (Exception e) {
							setException(e);
						}
				} else if (getContributionState().isEdit()
						&& event.getBundle().getSymbolicName().equals(getProject().getName() + ".edit")) {
					if ((event.getType() == BundleEvent.STARTED || event.getType() == BundleEvent.RESOLVED
							|| event.getType() == BundleEvent.LAZY_ACTIVATION)
							&& (event.getBundle().getState() == Bundle.ACTIVE
									|| event.getBundle().getState() == Bundle.STARTING))
						try {
							doLoad(getContributionState(), event.getBundle(), 1);
						} catch (Exception e) {
							setException(e);
						}
				} else if (getContributionState().isEditor()
						&& event.getBundle().getSymbolicName().equals(getProject().getName() + ".editor")) {
					if ((event.getType() == BundleEvent.STARTED || event.getType() == BundleEvent.RESOLVED
							|| event.getType() == BundleEvent.LAZY_ACTIVATION)
							&& (event.getBundle().getState() == Bundle.ACTIVE
									|| event.getBundle().getState() == Bundle.STARTING))
						try {
							doLoad(getContributionState(), event.getBundle(), 2);
						} catch (Exception e) {
							setException(e);
						}
				}
			} finally {
				loaded.release();
			}
		}
	};

	public OSGiEPackageLoader(int depth, long stateTimestamp) {
		super(Messages.JobNameLoader, depth, stateTimestamp);
	}

	public IStatus work(IProgressMonitor monitor) throws CoreException {
		State contribution = getContributionState();
		if (contribution.getPhase() == Stage.DEFINED)
			return ClassMakerPlugin.createErrorStatus(Messages.ModelNotSpecified);
		EPackage ePackage = null;
		BundleTracker<EPackage> bundleTracker = null;
		Bundle osgiBundle = null;
		Bundle editBundle = null;
		Bundle editorBundle = null;
		int loadedCount = 0;
		try {
			bundleTracker = new BundleTracker<EPackage>(getContext(), Bundle.ACTIVE | Bundle.RESOLVED, this);
			bundleTracker.open();
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
			// getContext().addBundleListener(listener);
			if (osgiBundle != null) {
				start(osgiBundle);
				if (editBundle != null)
					start(editBundle);
				if (editorBundle != null)
					start(editorBundle);
			} else
				return ClassMakerPlugin.createErrorStatus(NLS.bind(Messages.BundleNotFound, getProject().getName()));
			ePackage = bundleTracker.getObject(osgiBundle);
			if (exception == null) {
				try {
					loaded.acquire();
				} catch (InterruptedException e) {
					return Status.CANCEL_STATUS;
				}
				return getStatus(osgiBundle, ePackage);
			} else {
				throw new CoreException(ClassMakerPlugin.createErrorStatus(exception));
			}
		} finally {
			if (bundleTracker != null)
				bundleTracker.close();
			monitor.done();
//			getContext().removeBundleListener(listener);
		}
	}

	private void start(Bundle osgiBundle) {
		if (osgiBundle.getHeaders().get(Constants.FRAGMENT_HOST) == null) {
//			getContext().removeBundleListener(listener);
//			getContext().addBundleListener(listener);
			for (Long id : getBundleIds()) {
				Bundle requiredBundle = getContext().getBundle(id);
				if (requiredBundle.equals(osgiBundle))
					continue;
				if (requiredBundle.getBundleId() != 0)
					requiredBundle.adapt(BundleStartLevel.class).setStartLevel(4);
				try {
					requiredBundle.start(getOptions(false, true));
				} catch (BundleException e) {
					setException(e);
				}
			}
			if (osgiBundle.getBundleId() != 0)
				osgiBundle.adapt(BundleStartLevel.class).setStartLevel(4);
			try {
				osgiBundle.start(getOptions(false, true));
			} catch (BundleException e) {
				setException(e);
			}
		}
	}

	@Override
	protected boolean terminate() {
		return true;
	}

	private IStatus getStatus(Bundle osgiBundle, EPackage ePackage) {
		String ePackagesMsg = ""; //$NON-NLS-1$
		boolean warning = false;
		if (getContributionState().getDomainModel().getGenerated() == null) {
			ePackagesMsg = Messages.ObjectNo;
			warning = true;
		} else {
			EMFPlugin editPlugin = null;
			EMFPlugin editorPlugin = null;
			if (getContributionState().getDomainModel().getGenerated() instanceof EPackage)
				ePackage = (EPackage) getContributionState().getDomainModel().getGenerated();
			else if (getContributionState().isEdit()
					&& getContributionState().getDomainModel().getGeneratedEdit() instanceof EMFPlugin)
				editPlugin = getContributionState().getDomainModel().getGeneratedEdit();
			else if (getContributionState().isEditor()
					&& getContributionState().getDomainModel().getGeneratedEditor() instanceof EMFPlugin)
				editorPlugin = getContributionState().getDomainModel().getGeneratedEditor();
			else
				return ClassMakerPlugin.createInfoStatus(NLS.bind("EObject {0} is not a EPackage",
						getContributionState().getDomainModel().getGenerated()));
			if (ePackage != null)
				ePackagesMsg = ePackagesMsg + ePackage.getNsURI() + ", "; //$NON-NLS-1$
			else if (editPlugin != null)
				ePackagesMsg = ePackagesMsg + editPlugin.getSymbolicName() + ", "; //$NON-NLS-1$
			else if (editorPlugin != null)
				ePackagesMsg = ePackagesMsg + editorPlugin.getSymbolicName() + ", "; //$NON-NLS-1$
			if (ePackagesMsg.length() > 2)
				ePackagesMsg = ePackagesMsg.subSequence(0, ePackagesMsg.length() - 2).toString();
			else {
				ePackagesMsg = Messages.ObjectNo;
				warning = true;
			}
		}
		if (warning)
			return ClassMakerPlugin.createWarningStatus(
					NLS.bind(Messages.ObjectClassLoadComplete, new Object[] { osgiBundle.getSymbolicName(),
							osgiBundle.getHeaders().get(Constants.BUNDLE_VERSION), ePackagesMsg }));
		return ClassMakerPlugin.createOKStatus(Messages.OK + " " //$NON-NLS-1$
				+ NLS.bind(Messages.ObjectClassLoadComplete, new Object[] { osgiBundle.getSymbolicName(),
						osgiBundle.getHeaders().get(Constants.BUNDLE_VERSION), ePackagesMsg }));

	}

	private int getOptions(boolean autoStart, boolean activationPolicy) {
		int result = 0;
		if (autoStart)
			result += Bundle.START_TRANSIENT;
		if (activationPolicy)
			result += Bundle.START_ACTIVATION_POLICY;
		return result;
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
							packageClassName = (getContributionState()
									.eIsSet(ClassMakerPackage.Literals.STATE__BASE_PACKAGE)
											? getContributionState().getBasePackage() + "."
											: "") //$NON-NLS-1$
									+ CodeGenUtil.safeName(((EPackage) model).getName()) + "."
									+ state.getPackageClassName();
						Class<?> packageClass = null;
						try {
							if (osgiBundle.getState() != org.eclipse.osgi.container.Module.State.UNINSTALLED.ordinal())
								packageClass = osgiBundle.loadClass(packageClassName);
						} catch (Exception e) {
							setException(e);
							ClassMakerPlugin.getInstance().getLog()
									.log(ClassMakerPlugin.createErrorStatus(e.getLocalizedMessage()));
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
						pluginClassName = (getContributionState().eIsSet(ClassMakerPackage.Literals.STATE__BASE_PACKAGE)
								? getContributionState().getBasePackage() + "."
								: "") + CodeGenUtil.safeName(((EPackage) model0).getName()) + ".provider." //$NON-NLS-1$
								+ state.getEditPluginClassName();
					Class<?> pluginClass = null;
					try {
						if (osgiBundle.getState() != org.eclipse.osgi.container.Module.State.UNINSTALLED.ordinal())
							pluginClass = osgiBundle.loadClass(pluginClassName);
					} catch (Exception e) {
						setException(e);
						throw new InvocationTargetException(e);
					}
					EMFPlugin editPlugin = null;
					try {
						editPlugin = (EMFPlugin) pluginClass.getField("INSTANCE").get(null);
						if (editPlugin != null)
							getContributionState().getDomainModel().setGeneratedEdit(editPlugin);
					} catch (Exception e) {
						setException(e);
						throw new InvocationTargetException(e);
					} finally {
						loaded.release();
						if (loaded.availablePermits() >= 1) {
							getContributionState().getProject().setNeedCompletionNotification(true);
						}
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
						pluginClassName1 = (getContributionState()
								.eIsSet(ClassMakerPackage.Literals.STATE__BASE_PACKAGE)
										? getContributionState().getBasePackage() + "."
										: "") //$NON-NLS-1$
								+ CodeGenUtil.safeName(((EPackage) model1).getName()) + ".presentation."
								+ state.getEditorPluginClassName();
					Class<?> pluginClass1 = null;
					try {
						if (osgiBundle.getState() != org.eclipse.osgi.container.Module.State.UNINSTALLED.ordinal())
							pluginClass1 = osgiBundle.loadClass(pluginClassName1);
					} catch (Exception e) {
						setException(e);
					}
					EMFPlugin editorPlugin = null;
					try {
						editorPlugin = (EMFPlugin) pluginClass1.getField("INSTANCE").get(null);
						if (editorPlugin != null)
							getContributionState().getDomainModel().setGeneratedEditor(editorPlugin);
					} catch (Exception e) {
						setException(e);
						throw new InvocationTargetException(e);
					} finally {
						loaded.release();
						if (loaded.availablePermits() >= 1) {
							getContributionState().getProject().setNeedCompletionNotification(true);
						}
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

	@Override
	public EPackage addingBundle(Bundle bundle, BundleEvent event) {
		EPackage ePackage = null;
		int loadedCount = 0;
		try {
			EObject model = getContributionState().getDomainModel().getDynamic();
			String packageClassName = null;
			if (model instanceof EPackage) {
				if (!bundle.getSymbolicName().equalsIgnoreCase(((EPackage) model).getName())) {
					loaded = new Semaphore(loadedCount);
					return null;
				}
				packageClassName = (getContributionState().eIsSet(ClassMakerPackage.Literals.STATE__BASE_PACKAGE)
						? getContributionState().getBasePackage() + "."
						: "") //$NON-NLS-1$
						+ CodeGenUtil.safeName(((EPackage) model).getName()) + "."
						+ getContributionState().getPackageClassName();
			}
			Class<?> packageClass = null;
			try {
				if (bundle.getState() != org.eclipse.osgi.container.Module.State.UNINSTALLED.ordinal())
					packageClass = bundle.loadClass(packageClassName);
			} catch (Exception e) {
				setException(e);
				ClassMakerPlugin.getInstance().getLog()
						.log(ClassMakerPlugin.createErrorStatus(e.getLocalizedMessage()));
			}
			loaded = new Semaphore(loadedCount);
			try {
				ePackage = (EPackage) packageClass.getField("eINSTANCE").get(null); // $NON-NLS-1$
				if (ePackage != null) {
					getContributionState().getDomainModel().setGenerated(ePackage);
					registerEPackage(Registry.INSTANCE, ePackage);
					if (getContributionState().getRevision().getProject().getWorkspace().getResourceSet() != null)
						registerEPackage(getContributionState().getRevision().getProject().getWorkspace()
								.getResourceSet().getPackageRegistry(), ePackage);
				}
			} catch (ClassCastException e) {
				if (ePackage != null) {
					getContributionState().getDomainModel().setGenerated(ePackage);
					registerEPackage(Registry.INSTANCE, ePackage);
					if (getContributionState().getRevision().getProject().getWorkspace().getResourceSet() != null)
						registerEPackage(getContributionState().getRevision().getProject().getWorkspace()
								.getResourceSet().getPackageRegistry(), ePackage);
				}
			}
		} catch (Exception e) {
			BundleWiring wiring = bundle.adapt(BundleWiring.class);
			for (BundleRequirement br : wiring.getRequirements("osgi.wiring.package"))
				for (BundleRequirement r : br.getRevision().getDeclaredRequirements("osgi.wiring.package"))
					System.out.println(r.toString());
			setException(e);
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
		} finally {
			loaded.release();
			getContributionState().getProject().setNeedCompletionNotification(true);
		}
		return ePackage;
	}

	@Override
	public void modifiedBundle(Bundle bundle, BundleEvent event, EPackage object) {
		EObject model = getContributionState().getDomainModel().getDynamic();
		if (model instanceof EPackage) {
			if (!bundle.getSymbolicName().equalsIgnoreCase(((EPackage) model).getName())) {
				loaded = new Semaphore(0);
				return;
			}
		}
	}

	@Override
	public void removedBundle(Bundle bundle, BundleEvent event, EPackage object) {
	}

}
