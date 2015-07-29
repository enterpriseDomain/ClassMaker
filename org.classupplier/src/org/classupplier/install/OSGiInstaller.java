package org.classupplier.install;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.impl.ClassSupplierOSGi;
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

	private Bundle osgiBundle;
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
		if (getContribution().getStage() == Phase.DEFINED)
			return ClassSupplierOSGi.createErrorStatus("Model is not specified.");
		IPath jarPath = getTargetResourcePath(getContribution());
		BundleContext context = getContext();
		try {
			for (Bundle bundle : getExistingBundles())
				return ClassSupplierOSGi
						.createOKStatus(NLS.bind("Bundle {0}-{1} is already installed and in state {2}.",
								new Object[] { bundle.getSymbolicName(),
										bundle.getHeaders().get(Constants.BUNDLE_VERSION),
										ClassSupplierOSGi.bundleStateAsString(bundle.getState()) }));
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
			return installBundle(jarPath, context);

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

	private IStatus installBundle(IPath jarPath, BundleContext context) throws InterruptedException {
		osgiBundle = null;
		try {
			context.addBundleListener(listener);
			osgiBundle = ResourceUtil.getBundle(getContribution().getProjectName(), context);
			if (osgiBundle != null) {
				osgiBundle.uninstall();
				uninstalled.acquire();
			}
			osgiBundle = context.installBundle(URI.createFileURI(jarPath.toString()).toString());
			if (osgiBundle == null)
				return ClassSupplierOSGi
						.createErrorStatus(NLS.bind("Bundle {0} is not installed.", getProject().getName()));
			installed.acquire();
			Collection<Bundle> bundles = new ArrayList<Bundle>();
			bundles.add(osgiBundle);
			FrameworkWiring frameworkWiring = context.getBundle(0).adapt(FrameworkWiring.class);
			frameworkWiring.refreshBundles(bundles, new FrameworkListener[0]);
			frameworkWiring.resolveBundles(bundles);
			getContribution().setStage(Phase.INSTALLED);
			return getOKStatus(osgiBundle);
		} catch (BundleException e) {
			Bundle bundle = ResourceUtil.getBundle(getProject().getName(), context);
			if (bundle != null) {
				if (e.getType() == BundleException.DUPLICATE_BUNDLE_ERROR)
					return getOKStatus(bundle);
			} else {
				e.printStackTrace();
				return getWarningStatus();
			}
		} finally {
			getContext().removeBundleListener(listener);
		}
		if (osgiBundle != null)
			return getOKStatus(osgiBundle);
		else
			return getWarningStatus();
	}

	private IStatus getWarningStatus() {
		return ClassSupplierOSGi.createWarningStatus(NLS.bind("Bundle {0} not found.", getProject().getName()));
	}

	private IStatus getOKStatus(Bundle osgiBundle) {
		return ClassSupplierOSGi
				.createOKStatus(NLS.bind("Bundle {0}-{1} is {2}.",
						new Object[] { osgiBundle.getSymbolicName(),
								osgiBundle.getHeaders().get(Constants.BUNDLE_VERSION),
								ClassSupplierOSGi.bundleStateAsString(osgiBundle.getState()).toLowerCase() }));
	}

	private Collection<Bundle> getExistingBundles() {
		State state = getContribution();
		BundleContext context = getContext();
		List<Bundle> results = new ArrayList<Bundle>();
		for (Bundle bundle : context.getBundles())
			if (bundle.getSymbolicName().equals(state.getProjectName()))
				results.add(bundle);
		return results;
	}

	public IPath getTargetResourcePath(State state) {
		return ResourceUtil.getExportDestination(getProject()).append("plugins").addTrailingSeparator()
				.append(getJarName(state)).addFileExtension("jar");
	}

	public String getJarName(State state) {
		String jarName;
		Version version = Version.parseVersion(state.getVersion().toString());
		if (version.equals(Version.emptyVersion))
			jarName = state.getProjectName() + '_' + Version.parseVersion("1.0.0.qualifier").toString();
		else
			jarName = state.getProjectName() + '_' + version;
		return jarName;
	}

	@Override
	public void checkStage() throws CoreException {
		if (getContribution().getStage().getValue() < Phase.EXPORTED_VALUE)
			throw new CoreException(ClassSupplierOSGi.createWarningStatus("Contribution is not exported."));
	}

}
