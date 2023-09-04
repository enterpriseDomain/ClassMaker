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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.CustomClassLoader;
import org.enterprisedomain.classmaker.jobs.ContainerJob;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;
import org.osgi.framework.startlevel.BundleStartLevel;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;

public class OSGiEPackageLoader extends ContainerJob implements BundleTrackerCustomizer<EPackage> {

	private Semaphore loaded;

	private Throwable exception;

	private int loadedCount;

	private EPackage ePackage;

	public OSGiEPackageLoader(int depth, long stateTimestamp) {
		super(Messages.JobNameLoader, depth, stateTimestamp);
	}

	public IStatus work(IProgressMonitor monitor) throws CoreException {
		State contribution = getContributionState();
		if (contribution.getPhase() == Stage.DEFINED)
			return ClassMakerPlugin.createErrorStatus(Messages.ModelNotSpecified);
		ePackage = null;
		BundleTracker<EPackage> bundleTracker = null;
		Bundle osgiBundle = null;
		Bundle editBundle = null;
		Bundle editorBundle = null;
		loadedCount = 0;
		try {
			bundleTracker = new BundleTracker<EPackage>(getContext(),
					Bundle.STARTING | Bundle.ACTIVE | Bundle.RESOLVED | Bundle.INSTALLED, this);
			bundleTracker.open();
			for (Bundle bundle : getInstalledBundles())
				if (versionsAreEqual(Version.parseVersion(bundle.getHeaders().get(Constants.BUNDLE_VERSION)),
						getContributionState().getProject().getVersion(), false)) {
					osgiBundle = bundle;
					if (getContributionState().isEdit()
							&& bundle.getSymbolicName().equals(getProject().getName() + ".edit")) { //$NON-NLS-1$
						editBundle = bundle;
						loadedCount++;
					}
					if (getContributionState().isEditor()
							&& bundle.getSymbolicName().equals(getProject().getName() + ".editor")) { //$NON-NLS-1$
						editorBundle = bundle;
						loadedCount++;
					}
				}
			if (osgiBundle != null) {
				start(osgiBundle);
				if (editBundle != null)
					start(editBundle);
				if (editorBundle != null)
					start(editorBundle);
			} else {
				for (Bundle bundle : getBundles()) {
					Version version = Version.parseVersion(bundle.getHeaders().get(Constants.BUNDLE_VERSION));
					Version version2 = getContributionState().getProject().getVersion();
					if (versionsAreEqual(version, version2, false) || versionAreLess(version, version2, true)) {
						osgiBundle = bundle;
						if (getContributionState().isEdit()
								&& bundle.getSymbolicName().equals(getProject().getName() + ".edit")) { //$NON-NLS-1$
							editBundle = bundle;
							loadedCount++;
						}
						if (getContributionState().isEditor()
								&& bundle.getSymbolicName().equals(getProject().getName() + ".editor")) { //$NON-NLS-1$
							editorBundle = bundle;
							loadedCount++;
						}
					}
				}
				if (osgiBundle != null) {
					start(osgiBundle);
					if (editBundle != null)
						start(editBundle);
					if (editorBundle != null)
						start(editorBundle);
				} else
					return ClassMakerPlugin
							.createErrorStatus(NLS.bind(Messages.BundleNotFound, getProject().getName()));
			}
			while (bundleTracker.getTrackingCount() == 0)
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
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
		}
	}

	private void start(Bundle osgiBundle) {
		if (osgiBundle.getHeaders().get(Constants.FRAGMENT_HOST) == null) {
			for (Bundle bundle : getInstalledBundles()) {
				if (bundle.equals(osgiBundle))
					continue;
				startBundle(bundle);
			}
			for (Bundle bundle : getBundles()) {
				if (bundle.equals(osgiBundle))
					continue;
				startBundle(bundle);
			}
			startBundle(osgiBundle);
		}
	}

	private void startBundle(Bundle bundle) {
		if (bundle.getBundleId() != 0)
			bundle.adapt(BundleStartLevel.class).setStartLevel(4);
		try {
			bundle.start(getOptions(false, true));
		} catch (BundleException e) {
			setException(e);
		}
	}

	@Override
	protected boolean terminate() {
		return true;
	}

	private int getOptions(boolean autoStart, boolean activationPolicy) {
		int result = 0;
		if (autoStart)
			result += Bundle.START_TRANSIENT;
		if (activationPolicy)
			result += Bundle.START_ACTIVATION_POLICY;
		return result;
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
		ePackage = null;
		try {
			EObject model = getContributionState().getDomainModel().getDynamic();
			String packageClassName = null;
			if (model == null) {
				loaded = new Semaphore(loadedCount);
				return null;
			}
			if (model instanceof EPackage) {
				if (!bundle.getSymbolicName().equalsIgnoreCase(((EPackage) model).getName())) {
					if (loaded == null)
						loaded = new Semaphore(loadedCount);
					return null;
				}
				packageClassName = (getContributionState().eIsSet(ClassMakerPackage.Literals.STATE__BASE_PACKAGE)
						? getContributionState().getBasePackage() + "." //$NON-NLS-1$
						: "") //$NON-NLS-1$
						+ CodeGenUtil.safeName(((EPackage) model).getName()) + "." //$NON-NLS-1$
						+ getContributionState().getPackageClassName();
			}
			Class<?> packageClass = null;
			try {
				if (bundle.getState() != org.eclipse.osgi.container.Module.State.UNINSTALLED.ordinal())
					packageClass = bundle.loadClass(packageClassName);
			} catch (ClassNotFoundException e) {
				ClassLoader cl = Thread.currentThread().getContextClassLoader();
				try {
					Thread.currentThread().setContextClassLoader(getCustomClassLoader(bundle, (EPackage) model, cl));
					packageClass = Thread.currentThread().getContextClassLoader().loadClass(packageClassName);
				} catch (ClassNotFoundException ex) {
					Thread.currentThread().setContextClassLoader(cl);
					packageClass = Thread.currentThread().getContextClassLoader().loadClass(packageClassName);
				} finally {
					Thread.currentThread().setContextClassLoader(cl);
				}
			}
			loaded = new Semaphore(loadedCount);
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(getCustomClassLoader(bundle, (EPackage) model, cl));
				ePackage = (EPackage) packageClass.getField("eINSTANCE").get(null); //$NON-NLS-1$
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
			} finally {
				Thread.currentThread().setContextClassLoader(cl);
			}
		} catch (ClassNotFoundException e) {

		} catch (Exception e) {
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
	}

	@Override
	public void removedBundle(Bundle bundle, BundleEvent event, EPackage object) {
	}

	public ClassLoader getCustomClassLoader(Bundle bundle, EPackage m, ClassLoader cl) {
		if (!getContributionState().getProject().eIsSet(ClassMakerPackage.Literals.PROJECT__CLASS_LOADER)) {
			List<String> pkgs = new ArrayList<String>(5);
			String pkg = (getContributionState().eIsSet(ClassMakerPackage.Literals.STATE__BASE_PACKAGE)
					? getContributionState().getBasePackage() + "." //$NON-NLS-1$
					: "") //$NON-NLS-1$
					+ CodeGenUtil.safeName(m.getName());
			pkgs.add(pkg);
			pkgs.add(pkg + ".impl");
			pkgs.add(pkg + ".util");
			if (getContributionState().isEdit())
				pkgs.add(pkg + ".provider");
			if (getContributionState().isEditor())
				pkgs.add(pkg + ".presentation");
			getContributionState().getProject().setClassLoader(new CustomClassLoader(bundle, pkgs, cl));
		}
		return getContributionState().getProject().getClassLoader();
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
				return ClassMakerPlugin.createInfoStatus(NLS.bind(Messages.EObjectIsNotAEPackage,
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

}
