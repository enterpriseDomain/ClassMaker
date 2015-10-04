package org.classupplier.jobs.load;

import java.util.concurrent.Semaphore;

import org.classupplier.ClassSupplierPackage;
import org.classupplier.Messages;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.core.ClassSupplierOSGi;
import org.classupplier.jobs.ContainerJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.startlevel.BundleStartLevel;

public class OSGiEPackageLoader extends ContainerJob {

	private EList<EPackage> ePackages;

	private Semaphore loaded = new Semaphore(0);

	private Throwable exception;

	private BundleListener listener = new BundleListener() {

		@Override
		public void bundleChanged(BundleEvent event) {
			if (event.getBundle() == null)
				return;
			if (event.getBundle().getSymbolicName().equals(getProject().getName()))
				if (event.getType() == BundleEvent.STARTED) {
					doLoad(getContribution(), event.getBundle());
				}

		}
	};

	public OSGiEPackageLoader() {
		super("Class Load"); //$NON-NLS-1$
	}

	public IStatus load(IProgressMonitor monitor) throws CoreException {
		State contribution = getContribution();
		if (contribution.getStage() == Phase.DEFINED)
			return ClassSupplierOSGi.createErrorStatus(Messages.ModelNotSpecified);
		try {
			Bundle osgiBundle = getBundle(contribution.getVersion());
			if (osgiBundle != null) {
				if (osgiBundle.getHeaders().get(Constants.FRAGMENT_HOST) == null) {
					if (osgiBundle.getState() == Bundle.STARTING || osgiBundle.getState() == Bundle.ACTIVE) {
						getContext().removeBundleListener(listener);
						doLoad(contribution, osgiBundle);
					} else {
						getContext().addBundleListener(listener);
						int options = getOptions(4, false, osgiBundle);
						try {
							osgiBundle.start(options);
						} catch (BundleException e) {
							setException(e);
						}
					}
				}
			} else
				return ClassSupplierOSGi.createErrorStatus(NLS.bind(Messages.BundleNotFound, getProject().getName()));

			if (monitor.isCanceled()) {
				return Status.CANCEL_STATUS;
			}
			try {
				loaded.acquire();
			} catch (InterruptedException e) {
				return Status.CANCEL_STATUS;
			}
			if (exception == null) {
				if (!ePackages.isEmpty() && ePackages.size() == contribution.getDynamicEPackages().size())
					return getOKStatus(osgiBundle);
			} else
				return ClassSupplierOSGi.createErrorStatus(exception);
		} finally {
			monitor.done();
		}
		throw new Error();
	}

	private IStatus getOKStatus(Bundle osgiBundle) {
		String ePackagesMsg = "";
		for (EPackage ePackage : ePackages)
			ePackagesMsg = ePackagesMsg + ePackage.getNsURI() + ", ";
		ePackagesMsg = ePackagesMsg.subSequence(0, ePackagesMsg.length() - 2).toString();
		return ClassSupplierOSGi.createOKStatus(NLS.bind(Messages.EPackageClassLoadComplete, new Object[] {
				osgiBundle.getSymbolicName(), osgiBundle.getHeaders().get(Constants.BUNDLE_VERSION), ePackagesMsg }));

	}

	private int getOptions(int startlevel, boolean autoStart, Bundle bundle) {
		int options = 0;
		if (bundle.getBundleId() != 0)
			bundle.adapt(BundleStartLevel.class).setStartLevel(startlevel);
		options |= autoStart ? Bundle.START_TRANSIENT : 0;
		options |= bundle.getHeaders(Constants.BUNDLE_ACTIVATIONPOLICY).equals(Constants.ACTIVATION_LAZY)
				? Bundle.START_ACTIVATION_POLICY : 0;
		return options;
	}

	private synchronized void doLoad(State state, Bundle osgiBundle) {
		ePackages = ECollections.newBasicEList();
		for (EPackage model : state.getDynamicEPackages()) {
			String packageClassName = model.getName() + "." + model.getNsPrefix() //$NON-NLS-1$
					+ "Package"; //$NON-NLS-1$
			Class<?> packageClass = null;
			try {
				packageClass = osgiBundle.loadClass(packageClassName);
			} catch (ClassNotFoundException e) {
				setException(e);
			}
			EPackage ePackage = null;
			try {
				ePackage = (EPackage) packageClass.getField("eINSTANCE").get(packageClass); //$NON-NLS-1$
				ePackages.add(ePackage);
			} catch (Exception e) {
				setException(e);
			}
			int index = ePackages.indexOf(ePackage);
			if (ePackages != null) {
				if (getContribution().contains(ClassSupplierPackage.Literals.CONTRIBUTION__GENERATED_EPACKAGES,
						ePackage))
					state.getGeneratedEPackages().set(index, ePackage);
				else
					state.getGeneratedEPackages().add(ePackage);

			} else if (exception == null)
				setException(new Error());
		}
		loaded.release();
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return load(monitor);
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	@Override
	public Phase requiredStage() {
		return Phase.INSTALLED;
	}

}
