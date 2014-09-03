package org.classupplier.codegen;

import java.io.IOException;
import java.util.Collections;

import org.classupplier.State;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.util.ResourceUtil;
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

		private State state;

		public GenModelSetupRunnable(IPath genModelPath, IProject project,
				State state) {
			this.setPath(genModelPath);
			this.setProject(project);
			this.setState(state);
		}

		@Override
		public void run(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			URI genModelURI = URI.createFileURI(root.getRawLocation()
					.append(getPath()).toString());
			Resource resource = resourceSet.getResource(genModelURI, true);
			GenModel genModel = (GenModel) resource.getContents().get(0);
			setupGenModel(getState(), getPath().removeFileExtension()
					.removeLastSegments(2), genModel);
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				throw new CoreException(
						new Status(IStatus.WARNING,
								ClassSupplierOSGi.PLUGIN_ID,
								e.getLocalizedMessage(), e));
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

		public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}

	}

	@Override
	public void generate(final State state, ISchedulingRule rule,
			final IProgressMonitor monitor) throws CoreException {
		state.setVersion(Version.parseVersion("1.0.0.qualifier"));

		final IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(state.getProjectName());
		IPath modelPath = ensureModelResourcePath(project, state.getName(),
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
							path.toString(), "", state.getName() });
					monitor.worked(1);
				}
			}, monitor);
		}

		if (genModelSetupRunnable == null)
			genModelSetupRunnable = new GenModelSetupRunnable(genModelPath,
					project, state);
		else {
			genModelSetupRunnable.setPath(genModelPath);
			genModelSetupRunnable.setProject(project);
			genModelSetupRunnable.setState(state);
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
							ClassSupplierOSGi.PLUGIN_ID,
							NLS.bind(
									"The project {0} was not created before generation.",
									project)));
		project.open(monitor);
		IFolder folder = project.getFolder(ResourceUtil.getModelFolderName());
		if (!folder.exists())
			folder.create(true, true, monitor);
		IFile file = folder.getFile(ResourceUtil.getFileName(name));
		return file.getFullPath();
	}

	public IPath getGenModelResourcePath(IPath path) {
		return path.removeFileExtension().addFileExtension(GENMODEL_EXT);
	}

	protected void setupGenModel(State state, IPath projectPath,
			org.eclipse.emf.codegen.ecore.genmodel.GenModel ecoreGenModel) {
		ecoreGenModel.setModelName(state.getName());
		ecoreGenModel.setSuppressInterfaces(true);
		ecoreGenModel.setModelDirectory(projectPath.append(SOURCE_FOLDER_NAME)
				.toString());
		ecoreGenModel.setModelPluginID(state.getProjectName());
	}

	@Override
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

}
