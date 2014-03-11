package org.classupplier.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.classupplier.Artifact;
import org.classupplier.Infrastructure;
import org.classupplier.State;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;
import org.osgi.framework.wiring.FrameworkWiring;

public class Initializer implements IWorkspaceRunnable {

	private IProject project;

	private Artifact artifact;

	private Infrastructure workspace;

	public Initializer(IProject project, Artifact artifact,
			Infrastructure workspace) {
		this.project = project;
		this.artifact = artifact;
		this.workspace = workspace;
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		IFolder folder = project.getFolder(PathHelper.getModelFolderName());
		if (!folder.exists())
			throw new CoreException(
					new Status(Status.WARNING, OSGi.PLUGIN_ID, NLS.bind(
							"Domain project {0} has no model folder.", project)));
		try {
			for (IResource resource : project.getFolder(
					PathHelper.getModelFolderName()).members())
				if (resource.getFileExtension().equals(PathHelper.getFileExt()))
					artifact.setName(resource.getLocation()
							.removeFileExtension().lastSegment());
		} catch (CoreException e) {
			throw new CoreException(new Status(Status.WARNING, OSGi.PLUGIN_ID,
					NLS.bind("Model for project {0} doesn't exist.", project)));
		}
		IPath modelPath = PathHelper.getModelResourcePath(project, workspace);
		URI modelURI = URI
				.createPlatformResourceURI(modelPath.toString(), true);
		ResourceSet resourceSet = workspace.getResourceSet();
		Resource resource = resourceSet.getResource(modelURI, true);
		if (resource != null && artifact.getState().equals(State.CREATED)) {
			EPackage value = (EPackage) EcoreUtil.copy(resource.getContents()
					.get(0));
			artifact.setName(modelURI.trimFileExtension().lastSegment());
			artifact.setPrototypeEPackage(value);
			artifact.setState(State.PROTOTYPE);
		}
		IPath path = project.getLocation().append("target")
				.append(PathHelper.getJarName(artifact)).addFileExtension("jar");
		if (!path.toFile().exists())
			return;
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
				Version version = new Version(osgiBundle.getHeaders().get(
						Constants.BUNDLE_VERSION));
				artifact.setVersion(version);

				String packageClassName = artifact.getPrototypeEPackage()
						.getName() + "." + artifact.getName() + "Package";

				Class<?> packageClass = osgiBundle.loadClass(packageClassName);
				EPackage ePackage = (EPackage) packageClass.getField(
						"eINSTANCE").get(packageClass);

				artifact.setLoadedEPackage(ePackage);
				artifact.setState(State.COMPLETE);
			}
		} catch (Exception e) {
		}
	}
}
