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
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
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

public class BundleEPackageLoader extends WorkspaceJob {

	private IProject project;

	private boolean enforceReplace = false;

	public BundleEPackageLoader(IProject project) {
		super("Install and load");
		this.project = project;
	}

	public IStatus load(IProgressMonitor monitor) throws CoreException {
		if (getArtifactState().getStage() == Phase.NEW)
			return new Status(IStatus.ERROR, ClassSupplierOSGi.PLUGIN_ID,
					"Model not specified.");
		IPath jarPath = ResourceUtil.getTargetResourcePath(getProject(),
				getArtifactState());
		try {
			BundleContext context = getContext();
			Bundle osgiBundle = null;
			for (Bundle bundle : getExistingBundles())
				if (isEnforceReplace())
					bundle.uninstall();
				else
					osgiBundle = bundle;
			if (isEnforceReplace()
					|| (!isEnforceReplace() && osgiBundle == null))
				osgiBundle = context.installBundle(URI.createFileURI(
						jarPath.toString()).toString());
			Collection<Bundle> bundles = new ArrayList<Bundle>();
			FrameworkWiring frameworkWiring = context.getBundle(0).adapt(
					FrameworkWiring.class);
			frameworkWiring.refreshBundles(bundles, new FrameworkListener[0]);
			if (frameworkWiring.resolveBundles(bundles)) {
				String packageClassName = getArtifactState()
						.getDynamicEPackage().getName()
						+ "."
						+ getArtifactState().getName() + "Package";
				Class<?> packageClass = osgiBundle.loadClass(packageClassName);
				EPackage ePackage = (EPackage) packageClass.getField(
						"eINSTANCE").get(packageClass);
				Assert.isNotNull(ePackage);
				getArtifactState().setRuntimeEPackage(ePackage);
				return new Status(IStatus.OK, ClassSupplierOSGi.PLUGIN_ID,
						"Bundle '"
								+ osgiBundle.getSymbolicName()
								+ '-'
								+ osgiBundle.getHeaders().get(
										Constants.BUNDLE_VERSION)
								+ "' with EPackage '" + ePackage.getNsURI()
								+ "' has been loaded successfully.");

			}
		} catch (IllegalArgumentException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					ClassSupplierOSGi.PLUGIN_ID, e.getLocalizedMessage(), e));
		} catch (ReflectiveOperationException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					ClassSupplierOSGi.PLUGIN_ID, e.getLocalizedMessage(), e));
		} catch (SecurityException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					ClassSupplierOSGi.PLUGIN_ID, e.getLocalizedMessage(), e));
		} catch (BundleException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					ClassSupplierOSGi.PLUGIN_ID, e.getLocalizedMessage(), e));
		} catch (Error e) {
			throw new CoreException(new Status(IStatus.ERROR,
					ClassSupplierOSGi.PLUGIN_ID, e.getLocalizedMessage(), e));
		} finally {
			monitor.done();
		}
		return new Status(IStatus.ERROR, ClassSupplierOSGi.PLUGIN_ID,
				"EPackage not loaded.");
	}

	private BundleContext getContext() {
		return FrameworkUtil.getBundle(this.getClass()).getBundleContext();
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		IStatus result = load(monitor);
		return result;
	}

	private State getArtifactState() {
		Artifact artifact = ClassSupplierOSGi.getClassSupplier().getWorkspace()
				.getArtifact(getProject().getName());

		State state = artifact.getState();
		return state;
	}

	@Override
	public boolean belongsTo(Object family) {
		return super.belongsTo(family)
				|| family == ResourcesPlugin.FAMILY_MANUAL_BUILD;
	}

	public IProject getProject() {
		return project;
	}

	private Collection<Bundle> getExistingBundles() {
		State state = getArtifactState();
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

	public boolean isEnforceReplace() {
		return enforceReplace;
	}

	public void setEnforceReplace(boolean enforceReplace) {
		this.enforceReplace = enforceReplace;
	}

}
