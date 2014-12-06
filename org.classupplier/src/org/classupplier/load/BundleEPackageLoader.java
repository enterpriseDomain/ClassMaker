package org.classupplier.load;

import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.builders.SupplementaryJob;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.util.ClassSupplierUtil;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;

public class BundleEPackageLoader extends SupplementaryJob {

	public BundleEPackageLoader() {
		super("Load");
	}

	public IStatus load(IProgressMonitor monitor) throws CoreException {
		State state = ClassSupplierUtil.getState(getProject());
		if (state.getStage() == Phase.DEFINED)
			return ClassSupplierOSGi.createErrorStatus("Model not specified.");
		try {
			Bundle osgiBundle = getBundle(getProject().getName());
			if (osgiBundle == null)
				return ClassSupplierOSGi.createErrorStatus(NLS.bind(
						"Bundle {0} not found in plug-in registry.",
						getProject().getName()));
			String packageClassName = state.getDynamicEPackage().getName()
					+ "." + state.getName() + "Package";
			Class<?> packageClass = osgiBundle.loadClass(packageClassName);
			EPackage ePackage = (EPackage) packageClass.getField("eINSTANCE")
					.get(packageClass);
			Assert.isNotNull(ePackage);
			state.setRuntimeEPackage(ePackage);
			
			if (monitor.isCanceled()) {
				return Status.CANCEL_STATUS;
			}
			return ClassSupplierOSGi
					.createOKStatus(NLS
							.bind("Bundle {0}-{1} with EPackage {2} has been successfully loaded.",
									new Object[] {
											osgiBundle.getSymbolicName(),
											osgiBundle.getHeaders().get(
													Constants.BUNDLE_VERSION),
											ePackage.getNsURI() }));
		} catch (IllegalArgumentException e) {
			throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
		} catch (ReflectiveOperationException e) {
			throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
		} catch (SecurityException e) {
			throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
		} catch (Error e) {
			throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
		} finally {
			monitor.done();
		}
	}

	private Bundle getBundle(String name) {
		BundleContext context = getContext();
		for (Bundle bundle : context.getBundles())
			if (bundle.getSymbolicName().equals(name))
				return bundle;
		return null;
	}

	private BundleContext getContext() {
		return FrameworkUtil.getBundle(this.getClass()).getBundleContext();
	}

	@Override
	public boolean belongsTo(Object family) {
		return super.belongsTo(family)
				|| family == ResourcesPlugin.FAMILY_MANUAL_BUILD;
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return load(monitor);
	}

}
