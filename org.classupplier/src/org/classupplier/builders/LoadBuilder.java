package org.classupplier.builders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.classupplier.Artifact;
import org.classupplier.impl.OSGi;
import org.classupplier.impl.ResourceHelper;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.FrameworkWiring;

public class LoadBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = OSGi.PLUGIN_ID + '.'
			+ "loadBuilder";

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		Artifact artifact = OSGi.getClasSupplier().getWorkspace()
				.getArtifact(getProject().getName());
		IPath path = getProject().getLocation().append("target")
				.append(ResourceHelper.getJarName(artifact))
				.addFileExtension("jar");
		try {
			BundleContext context = FrameworkUtil.getBundle(this.getClass())
					.getBundleContext();
			org.osgi.framework.Bundle osgiBundle = context.installBundle(URI
					.createFileURI(path.toString()).toString());
			Collection<org.osgi.framework.Bundle> bundles = new ArrayList<org.osgi.framework.Bundle>();
			FrameworkWiring frameworkWiring = context.getBundle(0).adapt(
					FrameworkWiring.class);
			frameworkWiring.refreshBundles(bundles, new FrameworkListener[0]);
			if (frameworkWiring.resolveBundles(bundles)) {
				String packageClassName = artifact.getPrototypeEPackage()
						.getName() + "." + artifact.getName() + "Package";

				Class<?> packageClass = osgiBundle.loadClass(packageClassName);
				EPackage ePackage = (EPackage) packageClass.getField(
						"eINSTANCE").get(packageClass);

				artifact.setLoadedEPackage(ePackage);
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
		try {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD,
					monitor);
		} catch (OperationCanceledException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		return null;
	}

}
