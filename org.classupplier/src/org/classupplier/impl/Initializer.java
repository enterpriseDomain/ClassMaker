package org.classupplier.impl;

import org.classupplier.Artifact;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.Workspace;
import org.classupplier.load.BundleEPackageLoader;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;

public class Initializer implements IWorkspaceRunnable {

	private IProject project;

	private Workspace workspace;

	public Initializer(IProject project, Workspace workspace) {
		this.setProject(project);
		this.setWorkspace(workspace);

	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		boolean autoBuild = workspace.isAutoBuilding();
		ResourceUtil.setAutoBuilding(workspace, false);
		switch (getState().getStage().getValue()) {
		case Phase.NEW_VALUE:
			break;
		case Phase.MODELED_VALUE:
			loadModel();
			break;
		case Phase.LOADED_VALUE:
			loadModel();
			loadJar(monitor);
			break;
		}
		ResourceUtil.setAutoBuilding(workspace, autoBuild);
	}

	private void loadJar(IProgressMonitor monitor) {
		BundleEPackageLoader loaderJob = new BundleEPackageLoader(getProject());
		loaderJob.setPriority(Job.SHORT);
		loaderJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
		loaderJob.schedule();
	}

	private void loadModel() throws CoreException {
		IFolder folder = getProject().getFolder(
				ResourceUtil.getModelFolderName());
		if (!folder.exists())
			throw new CoreException(new Status(Status.WARNING,
					ClassSupplierOSGi.PLUGIN_ID, NLS.bind(
							"Domain project {0} has no model folder.",
							getProject())));
		try {
			for (IResource resource : getProject().getFolder(
					ResourceUtil.getModelFolderName()).members())
				if (resource.getFileExtension().equals(
						ResourceUtil.getModelFileExt()))
					getState().setName(
							resource.getLocation().removeFileExtension()
									.lastSegment());
		} catch (CoreException e) {
			throw new CoreException(new Status(Status.WARNING,
					ClassSupplierOSGi.PLUGIN_ID, NLS.bind(
							"Model for project {0} doesn't exist.",
							getProject())));
		}
		IPath modelPath = ResourceUtil.getModelResourcePath(getProject(),
				getWorkspace());
		URI modelURI = URI
				.createPlatformResourceURI(modelPath.toString(), true);
		ResourceSet resourceSet = getWorkspace().getResourceSet();
		Resource resource = resourceSet.getResource(modelURI, true);
		if (resource != null) {
			if (resource.getContents().isEmpty()) {
				return;
			}

			EPackage value = (EPackage) EcoreUtil.copy(resource.getContents()
					.get(0));
			getState().setName(modelURI.trimFileExtension().lastSegment());
			getState().setDynamicEPackage(value);
		}
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	private State getState() {
		Artifact artifact = getWorkspace().getArtifact(getProject().getName());
		return artifact.getState();
	}
}
