package org.classupplier.codegen;

import java.io.IOException;
import java.util.Collections;

import org.classupplier.Artifact;
import org.classupplier.impl.ArtifactImpl;
import org.classupplier.impl.OSGi;
import org.classupplier.impl.ResourceHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Version;

public class EcoreGenerator implements org.classupplier.codegen.Generator {

	protected static final String SOURCE_FOLDER_NAME = "src/main/java";

	public static final String GENMODEL_EXT = "genmodel";

	private GenModelSetupRunnable genModelSetupRunnable;

	protected ResourceSet resourceSet;

	protected class GenModelSetupRunnable implements IWorkspaceRunnable {

		private IPath path;

		private IProject project;

		private Artifact artifact;

		public GenModelSetupRunnable(IPath genModelPath, IProject project,
				Artifact artifact) {
			this.setPath(genModelPath);
			this.setProject(project);
			this.setArtifact(artifact);
		}

		@Override
		public void run(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			URI genModelURI = URI.createFileURI(root.getRawLocation()
					.append(getPath()).toString());
			Resource resource = resourceSet.getResource(genModelURI, true);
			GenModel genModel = (GenModel) resource.getContents().get(0);
			setupGenModel(getArtifact(), getPath().removeFileExtension()
					.removeLastSegments(2), genModel);
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.WARNING,
						OSGi.PLUGIN_ID, e.getLocalizedMessage(), e));
			} finally {
				monitor.done();
			}
			try {
				Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD,
						monitor);
			} catch (OperationCanceledException e) {
				return;
			} catch (InterruptedException e) {
				return;
			}
		}

		public IPath getPath() {
			return path;
		}

		public void setPath(IPath path) {
			this.path = path;
		}

		public IProject getProject() {
			return project;
		}

		public void setProject(IProject project) {
			this.project = project;
		}

		public Artifact getArtifact() {
			return artifact;
		}

		public void setArtifact(Artifact artifact) {
			this.artifact = artifact;
		}

	}

	@Override
	public void generate(Artifact artifact, ISchedulingRule rule,
			final IProgressMonitor monitor) throws CoreException {
		final ArtifactImpl artifactImpl = (ArtifactImpl) artifact;
		artifactImpl.setVersion(Version.parseVersion("1.0.0.qualifier"));
		
		final IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(artifactImpl.getProjectName());
		IPath modelPath = ensureModelResourcePath(project, artifactImpl.getName(),
				monitor);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final IPath path = root.getRawLocation().append(modelPath);
		final org.eclipse.emf.codegen.ecore.Generator generator = new Generator();

		IPath genModelPath = getGenModelResourcePath(modelPath);
		if (!genModelPath.toFile().exists()) {
			monitor.beginTask("Generating GenModel '" + genModelPath.toString()
					+ "'.", 1);
			project.getWorkspace().run(new IWorkspaceRunnable() {

				@Override
				public void run(IProgressMonitor monitor) throws CoreException {
					generator.run(new String[] { "-ecore2GenModel",
							path.toString(), "", artifactImpl.getName() });
					monitor.worked(1);
				}
			}, monitor);
		}

		if (genModelSetupRunnable == null)
			genModelSetupRunnable = new GenModelSetupRunnable(genModelPath,
					project, artifactImpl);
		else {
			genModelSetupRunnable.setPath(genModelPath);
			genModelSetupRunnable.setProject(project);
			genModelSetupRunnable.setArtifact(artifactImpl);
		}
		project.getWorkspace().run(genModelSetupRunnable, rule, 0, monitor);

		generator.run(new String[] { "-model",
				root.getRawLocation().append(genModelPath).toString() });
		try {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD,
					monitor);
		} catch (OperationCanceledException e) {
			return;
		} catch (InterruptedException e) {
			return;
		}
	}

	private IPath ensureModelResourcePath(IProject project, String name,
			IProgressMonitor monitor) throws CoreException {
		if (!project.exists())
			throw new CoreException(
					new Status(
							IStatus.ERROR,
							OSGi.PLUGIN_ID,
							NLS.bind(
									"The project {0} was not created before generation.",
									project)));
		project.open(monitor);
		IFolder folder = project.getFolder(ResourceHelper.getModelFolderName());
		if (!folder.exists())
			folder.create(true, true, monitor);
		IFile file = folder.getFile(ResourceHelper.getFileName(name));
		return file.getFullPath();
	}

	public IPath getGenModelResourcePath(IPath path) {
		return path.removeFileExtension().addFileExtension(GENMODEL_EXT);
	}

	protected void setupGenModel(Artifact artifact, IPath projectPath,
			org.eclipse.emf.codegen.ecore.genmodel.GenModel ecoreGenModel) {
		ecoreGenModel.setModelName(artifact.getName());
		ecoreGenModel.setSuppressInterfaces(true);
		ecoreGenModel.setModelDirectory(projectPath.append(SOURCE_FOLDER_NAME)
				.toString());
		ecoreGenModel.setModelPluginID(artifact.getProjectName());
	}

	@Override
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

}
