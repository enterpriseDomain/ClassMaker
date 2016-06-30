package org.classupplier.jobs.install;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Semaphore;

import org.classupplier.Messages;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.core.ClassSupplierOSGi;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.Version;
import org.osgi.framework.wiring.FrameworkWiring;

public class OSGiInstaller extends Installer {

	private BundleListener listener = new BundleListener() {

		@Override
		public void bundleChanged(BundleEvent event) {
			if (event.getBundle() == null)
				return;
			if (event.getBundle().getSymbolicName().equals(getProject().getName()))
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

	public OSGiInstaller() {
		super();
		installed = new Semaphore(0);
		uninstalled = new Semaphore(0);
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return install(monitor);
	}

	@Override
	public IStatus install(IProgressMonitor monitor) {
		State contribution = getContribution();
		if (contribution.getStage() == Phase.DEFINED)
			return ClassSupplierOSGi.createErrorStatus(Messages.ModelNotSpecified);
		IPath jarPath = ResourceUtil.getTargetResourcePath(getProject(), contribution);
		BundleContext context = getContext();
		try {
			for (Bundle exisingBundle : getBundles()) {
				int compare = contribution.getVersion()
						.compareTo(Version.parseVersion(exisingBundle.getHeaders().get(Constants.BUNDLE_VERSION)));
				if (compare == 0) {
					contribution.setStage(Phase.INSTALLED);
					refreshBundle(exisingBundle, context);
					return getOKStatus(exisingBundle);
				} else
					return installBundle(exisingBundle, jarPath, context);
			}
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
			return installBundle(null, jarPath, context);
		} catch (IllegalArgumentException e) {
			return ClassSupplierOSGi.createErrorStatus(e);
		} catch (SecurityException e) {
			return ClassSupplierOSGi.createErrorStatus(e);
		} catch (InterruptedException e) {
			return ClassSupplierOSGi.createErrorStatus(e);
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
				return ClassSupplierOSGi
						.createErrorStatus(NLS.bind(Messages.BundleNotInstalled, getProject().getName()));
			installed.acquire();
			getContribution().setStage(Phase.INSTALLED);
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
			return ClassSupplierOSGi.createWarningStatus(NLS.bind(Messages.BundleNo, getProject().getName()), e);
		else
			return ClassSupplierOSGi.createWarningStatus(getStateStatusMessage(bundle), e);
	}

	private IStatus getOKStatus(Bundle existingBundle, Bundle bundle) {
		return ClassSupplierOSGi.createOKStatus(getDuplicateStatusMessage(existingBundle, bundle));
	}

	private IStatus getOKStatus(Bundle bundle) {
		return ClassSupplierOSGi.createOKStatus(getStateStatusMessage(bundle));
	}

	protected String getStateStatusMessage(Bundle bundle) {
		return NLS.bind(Messages.BundleState,
				new Object[] { bundle.getSymbolicName(), bundle.getHeaders().get(Constants.BUNDLE_VERSION),
						ClassSupplierOSGi.bundleStateAsString(bundle.getState()) });
	}

	private String getDuplicateStatusMessage(Bundle existingBundle, Bundle bundle) {
		return NLS.bind(Messages.BundleDuplicate,
				new Object[] { existingBundle.getSymbolicName(),
						existingBundle.getHeaders().get(Constants.BUNDLE_VERSION),
						ClassSupplierOSGi.bundleStateAsString(existingBundle.getState()), bundle.getSymbolicName(),
						bundle.getHeaders().get(Constants.BUNDLE_VERSION),
						ClassSupplierOSGi.bundleStateAsString(bundle.getState()) });
	}

	@Override
	public Phase requiredStage() {
		return Phase.EXPORTED;
	}

}
