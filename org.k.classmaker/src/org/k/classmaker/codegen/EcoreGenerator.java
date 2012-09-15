package org.k.classmaker.codegen;

import java.io.IOException;
import java.util.Collections;

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
import org.k.classmaker.Bundle;
import org.k.classmaker.ClassMaker;
import org.k.classmaker.NameLookup;

public class EcoreGenerator implements org.k.classmaker.codegen.Generator {

	protected static final String SOURCE_FOLDER_NAME = "src";

	public static final String GENMODEL_EXT = "genmodel";

	protected NameLookup names = ClassMaker.getInstance().names();

	private GenModelSetupRunnable genModelSetupRunnable;

	protected ResourceSet resourceSet;

	protected class GenModelSetupRunnable implements IWorkspaceRunnable {

		private IPath path;

		private IProject project;

		private Bundle bundle;

		public GenModelSetupRunnable(IPath genModelPath, IProject project,
				Bundle bundle) {
			this.setPath(genModelPath);
			this.setProject(project);
			this.setBundle(bundle);
		}

		@Override
		public void run(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			URI genModelURI = URI.createFileURI(root.getRawLocation()
					.append(getPath()).toString());
			Resource resource = resourceSet.getResource(genModelURI, true);
			GenModel genModel = (GenModel) resource.getContents().get(0);
			setupGenModel(getBundle(), getPath().removeFileExtension()
					.removeLastSegments(2), genModel);
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.WARNING,
						ClassMaker.PLUGIN_ID, e.getLocalizedMessage(), e));
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

		public Bundle getBundle() {
			return bundle;
		}

		public void setBundle(Bundle bundle) {
			this.bundle = bundle;
		}

	}

	@Override
	public void generate(final Bundle bundle, IProject project,
			ISchedulingRule rule) throws CoreException {
		IPath modelPath = ensureModelResourcePath(project, bundle.getName());

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final IPath path = root.getRawLocation().append(modelPath);
		final org.eclipse.emf.codegen.ecore.Generator generator = new Generator();
		final IProgressMonitor monitor = ClassMaker.getInstance().monitor();
		IPath genModelPath = getGenModelResourcePath(modelPath);
		project.getWorkspace().run(new IWorkspaceRunnable() {

			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				generator.run(new String[] { "-ecore2GenModel",
						path.toString(), "", bundle.getName() });
			}
		}, monitor);

		if (genModelSetupRunnable == null)
			genModelSetupRunnable = new GenModelSetupRunnable(genModelPath,
					project, bundle);
		else {
			genModelSetupRunnable.setPath(genModelPath);
			genModelSetupRunnable.setProject(project);
			genModelSetupRunnable.setBundle(bundle);
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

	private IPath ensureModelResourcePath(IProject project, String name)
			throws CoreException {
		IProgressMonitor monitor = ClassMaker.getInstance().monitor();
		if (!project.exists())
			throw new CoreException(
					new Status(
							IStatus.ERROR,
							ClassMaker.PLUGIN_ID,
							NLS.bind(
									"The project {0} wasn't created before generation.",
									project)));
		project.open(monitor);
		IFolder folder = project.getFolder(names.getModelFolderName());
		if (!folder.exists())
			folder.create(true, true, monitor);
		IFile file = folder.getFile(names.getFileName(name));
		return file.getFullPath();
	}

	public IPath getGenModelResourcePath(IPath path) {
		return path.removeFileExtension().addFileExtension(GENMODEL_EXT);
	}

	protected void setupGenModel(Bundle bundle, IPath projectPath,
			org.eclipse.emf.codegen.ecore.genmodel.GenModel ecoreGenModel) {
		ecoreGenModel.setModelName(bundle.getName());
		ecoreGenModel.setSuppressInterfaces(true);
		ecoreGenModel.setModelDirectory(projectPath.append(SOURCE_FOLDER_NAME)
				.toString());
		ecoreGenModel.setModelPluginID(projectPath.lastSegment());
	}

	@Override
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

}
