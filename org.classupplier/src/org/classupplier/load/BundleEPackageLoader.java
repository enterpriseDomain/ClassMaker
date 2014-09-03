package org.classupplier.load;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.classupplier.Artifact;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.FrameworkWiring;

public class BundleEPackageLoader {

	private IProject project;

	public BundleEPackageLoader(IProject project) {
		this.project = project;
	}

	public void load(boolean enforceReplace) {
		if (getState().getStage() == Phase.NEW)
			return;
		IPath jarPath = ResourceUtil.getTargetResourcePath(getProject(),
				getState());
		try {
			BundleContext context = getContext();
			Bundle osgiBundle = null;
			for (Bundle bundle : getExistingBundles())
				if (enforceReplace)
					bundle.uninstall();
				else
					osgiBundle = bundle;
			if (enforceReplace || (!enforceReplace && osgiBundle == null))
				osgiBundle = context.installBundle(URI.createFileURI(
						jarPath.toString()).toString());
			Collection<Bundle> bundles = new ArrayList<Bundle>();
			FrameworkWiring frameworkWiring = context.getBundle(0).adapt(
					FrameworkWiring.class);
			frameworkWiring.refreshBundles(bundles, new FrameworkListener[0]);
			if (frameworkWiring.resolveBundles(bundles)) {
				String packageClassName = getState().getPrototypeEPackage()
						.getName() + "." + getState().getName() + "Package";
				Class<?> packageClass = osgiBundle.loadClass(packageClassName);
				EPackage ePackage = (EPackage) packageClass.getField(
						"eINSTANCE").get(packageClass);
				getState().setLoadedEPackage(ePackage);
				getState().setStage(Phase.LOADED);
				ClassSupplierOSGi
						.getInstance()
						.getLog()
						.log(new Status(IStatus.OK,
								ClassSupplierOSGi.PLUGIN_ID,
								"Loading of bundle '"
										+ osgiBundle.getSymbolicName()
										+ '-'
										+ osgiBundle.getHeaders().get(
												Constants.BUNDLE_VERSION)
										+ "' with EPackage '"
										+ ePackage.getNsURI()
										+ "' has completed successfully."));

			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (BundleException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private BundleContext getContext() {
		return FrameworkUtil.getBundle(this.getClass()).getBundleContext();
	}

	private State getState() {
		Artifact artifact = ClassSupplierOSGi.getClassSupplier().getWorkspace()
				.getArtifact(getProject().getName());

		State state = artifact.getState();
		return state;
	}

	public IProject getProject() {
		return project;
	}

	private Collection<Bundle> getExistingBundles() {
		State state = getState();
		BundleContext context = getContext();
		List<Bundle> results = new ArrayList<Bundle>();
		for (Bundle bundle : context.getBundles())
			if (bundle.getSymbolicName().equals(state.getProjectName())
					&& bundle.getVersion().equals(state.getVersion()))
				results.add(bundle);
		return results;
	}

	public boolean bundleExists() {
		return !getExistingBundles().isEmpty();
	}

}
