package org.classupplier.install;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.util.ClassSupplierUtil;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.Version;
import org.osgi.framework.wiring.FrameworkWiring;

public class OSGiInstaller extends Installer {

	public OSGiInstaller() {
		super();
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return install(monitor);
	}

	@Override
	public IStatus install(IProgressMonitor monitor) {
		if (ClassSupplierUtil.getState(getProject()).getStage() == Phase.DEFINED)
			return ClassSupplierOSGi
					.createErrorStatus("Model is not specified.");
		IPath jarPath = getTargetResourcePath(ClassSupplierUtil
				.getState(getProject()));
		try {
			BundleContext context = getContext();
			Bundle osgiBundle = null;
			for (Bundle bundle : getExistingBundles())
				return ClassSupplierOSGi
						.createOKStatus(NLS
								.bind("Bundle {0}-{1} is already installed and in state {2}.",
										new Object[] {
												bundle.getSymbolicName(),
												bundle.getHeaders()
														.get(Constants.BUNDLE_VERSION),
												bundleStateAsString(bundle
														.getState()) }));

			osgiBundle = context.installBundle(URI.createFileURI(
					jarPath.toString()).toString());
			if (osgiBundle == null)
				return ClassSupplierOSGi.createErrorStatus(NLS.bind(
						"Bundle {0} not installed.", getProject().getName()));
			Collection<Bundle> bundles = new ArrayList<Bundle>();
			bundles.add(osgiBundle);
			FrameworkWiring frameworkWiring = context.getBundle(0).adapt(
					FrameworkWiring.class);
			frameworkWiring.refreshBundles(bundles, new FrameworkListener[0]);
			frameworkWiring.resolveBundles(bundles);
			int timeout = 0;
			while (!isReady(osgiBundle.getState()) && timeout < 50) {
				Thread.sleep(12);
				timeout = timeout + 1;
			}
			return ClassSupplierOSGi.createOKStatus(NLS.bind(
					"Bundle {0}-{1} is {2}.",
					new Object[] {
							osgiBundle.getSymbolicName(),
							osgiBundle.getHeaders().get(
									Constants.BUNDLE_VERSION),
							bundleStateAsString(osgiBundle.getState())
									.toLowerCase() }));

		} catch (IllegalArgumentException e) {
			return ClassSupplierOSGi.createErrorStatus(e);
		} catch (SecurityException e) {
			return ClassSupplierOSGi.createErrorStatus(e);
		} catch (BundleException e) {
			return ClassSupplierOSGi.createErrorStatus(e);
		} catch (InterruptedException e) {
			return ClassSupplierOSGi.createErrorStatus(e);
		} finally {
			monitor.worked(1);
		}
	}

	private boolean isReady(int state) {
		return state == Bundle.RESOLVED || state == Bundle.STARTING
				|| state == Bundle.ACTIVE;
	}

	private Collection<Bundle> getExistingBundles() {
		State state = ClassSupplierUtil.getState(getProject());
		BundleContext context = getContext();
		List<Bundle> results = new ArrayList<Bundle>();
		for (Bundle bundle : context.getBundles())
			if (bundle.getSymbolicName().equals(state.getProjectName()))
				results.add(bundle);
		return results;
	}

	public IPath getTargetResourcePath(State state) {
		return ResourceUtil.getExportDestination(getProject())
				.append("plugins").addTrailingSeparator()
				.append(getJarName(state)).addFileExtension("jar");
	}

	public String getJarName(State state) {
		String jarName;
		Version version = Version.parseVersion(state.getVersion().toString());
		if (version.equals(Version.emptyVersion))
			jarName = state.getProjectName() + '_'
					+ Version.parseVersion("1.0.0.qualifier").toString();
		else
			jarName = state.getProjectName() + '_' + version;
		return jarName;
	}

	private String bundleStateAsString(int state) {
		switch (state) {
		case Bundle.ACTIVE:
			return "ACTIVE";
		case Bundle.INSTALLED:
			return "INSTALLED";
		case Bundle.RESOLVED:
			return "RESOLVED";
		case Bundle.STARTING:
			return "STARTING";
		case Bundle.STOPPING:
			return "STOPPING";
		case Bundle.UNINSTALLED:
			return "UNINSTALLED";
		default:
			return "unknown bundle state: " + state;
		}
	}
}
