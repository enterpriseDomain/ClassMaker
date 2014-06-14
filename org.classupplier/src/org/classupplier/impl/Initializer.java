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

	private ArtifactImpl artifact;

	private Infrastructure workspace;

	public Initializer(IProject project, Artifact artifact,
			Infrastructure workspace) {
		this.setProject(project);
		this.setArtifact((ArtifactImpl) artifact);
		this.setWorkspace(workspace);

	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		IFolder folder = getProject().getFolder(
				ResourceHelper.getModelFolderName());
		if (!folder.exists())
			throw new CoreException(new Status(Status.WARNING, OSGi.PLUGIN_ID,
					NLS.bind("Domain project {0} has no model folder.",
							getProject())));
		try {
			for (IResource resource : getProject().getFolder(
					ResourceHelper.getModelFolderName()).members())
				if (resource.getFileExtension().equals(
						ResourceHelper.getFileExt()))
					getArtifact().setName(
							resource.getLocation().removeFileExtension()
									.lastSegment());
		} catch (CoreException e) {
			throw new CoreException(new Status(Status.WARNING, OSGi.PLUGIN_ID,
					NLS.bind("Model for project {0} doesn't exist.",
							getProject())));
		}
		IPath modelPath = ResourceHelper.getModelResourcePath(getProject(),
				getWorkspace());
		URI modelURI = URI
				.createPlatformResourceURI(modelPath.toString(), true);
		ResourceSet resourceSet = getWorkspace().getResourceSet();
		Resource resource = resourceSet.getResource(modelURI, true);
		if (resource != null && getArtifact().getState().equals(State.CREATED)) {
			if (resource.getContents().isEmpty()) {
				getArtifact().setState(State.CREATED);
				return;
			}

			EPackage value = (EPackage) EcoreUtil.copy(resource.getContents()
					.get(0));
			getArtifact().setName(modelURI.trimFileExtension().lastSegment());
			getArtifact().setPrototypeEPackage(value);
			getArtifact().setState(State.PROTOTYPE);
		}
		IPath path = getProject().getLocation().append("target")
				.append(ResourceHelper.getJarName(getArtifact()))
				.addFileExtension("jar");
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
				getArtifact().setVersion(version);

				String packageClassName = getArtifact().getPrototypeEPackage()
						.getName() + "." + getArtifact().getName() + "Package";

				Class<?> packageClass = osgiBundle.loadClass(packageClassName);
				EPackage ePackage = (EPackage) packageClass.getField(
						"eINSTANCE").get(packageClass);

				getArtifact().setLoadedEPackage(ePackage);
				getArtifact().setState(State.COMPLETE);
			}
		} catch (Exception e) {
		}
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public ArtifactImpl getArtifact() {
		return artifact;
	}

	public void setArtifact(ArtifactImpl artifact) {
		this.artifact = artifact;
	}

	public Infrastructure getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Infrastructure workspace) {
		this.workspace = workspace;
	}

}
