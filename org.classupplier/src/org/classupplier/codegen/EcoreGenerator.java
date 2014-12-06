package org.classupplier.codegen;

import java.io.IOException;
import java.util.Collections;

import org.classupplier.builders.SupplementaryJob;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.util.ClassSupplierUtil;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;

public class EcoreGenerator extends SupplementaryJob implements
		org.classupplier.codegen.Generator {

	public EcoreGenerator() {
		super("Generate Code");
		// setRule(EcorePlugin.getWorkspaceRoot());
		// setPriority(BUILD);
	}

	protected static final String SOURCE_FOLDER_NAME = "src/main/java";

	public static final String GENMODEL_EXT = "genmodel";

	private GenModelSetupJob genModelSetup = new GenModelSetupJob();

	private GenModelGenerationJob genModelGeneration = new GenModelGenerationJob();

	private CodeGenerationJob codeGeneration = new CodeGenerationJob();

	protected ResourceSet resourceSet;

	protected class GenModelSetupJob extends SupplementaryJob {

		public GenModelSetupJob() {
			super("Setup GenModel");
		}

		private IPath path;

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			URI genModelURI = URI.createFileURI(root.getRawLocation()
					.append(getGenModelPath()).toString());
			Resource resource = resourceSet.getResource(genModelURI, true);
			GenModel genModel = (GenModel) resource.getContents().get(0);
			setupGenModel(getGenModelPath().removeFileExtension()
					.removeLastSegments(2), genModel);
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				throw new CoreException(
						ClassSupplierOSGi.createWarningStatus(e));
			}
			return Status.OK_STATUS;
		}

		public IPath getGenModelPath() {
			return path;
		}

		public void setGenModelPath(IPath path) {
			this.path = path;
		}

	}

	protected class GenModelGenerationJob extends SupplementaryJob {

		private org.eclipse.emf.codegen.ecore.Generator generator;

		private IPath path;

		public GenModelGenerationJob() {
			super("GenModel Generation");
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			getGenerator()
					.run(new String[] { "-ecore2GenModel",
							getPath().toString(), "",
							ClassSupplierUtil.getState(getProject()).getName() });
			return Status.OK_STATUS;
		}

		public org.eclipse.emf.codegen.ecore.Generator getGenerator() {
			return generator;
		}

		public void setGenerator(
				org.eclipse.emf.codegen.ecore.Generator generator) {
			this.generator = generator;
		}

		public IPath getPath() {
			return path;
		}

		public void setPath(IPath path) {
			this.path = path;
		}

	}

	protected class CodeGenerationJob extends SupplementaryJob {

		public CodeGenerationJob() {
			super("Code Generation");
		}

		private org.eclipse.emf.codegen.ecore.Generator generator;

		private IPath path;

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			getGenerator().run(
					new String[] {
							"-model",
							EcorePlugin.getWorkspaceRoot().getRawLocation()
									.append(getGenModelPath()).toString() });
			return Status.OK_STATUS;
		}

		public org.eclipse.emf.codegen.ecore.Generator getGenerator() {
			return generator;
		}

		public void setGenerator(
				org.eclipse.emf.codegen.ecore.Generator generator) {
			this.generator = generator;
		}

		public IPath getGenModelPath() {
			return path;
		}

		public void setGenModelPath(IPath path) {
			this.path = path;
		}

	}

	@Override
	public IStatus generate(IProgressMonitor monitor) throws CoreException {
		IPath modelPath = ensureModelResourcePathExists(getProject(),
				ClassSupplierUtil.getState(getProject()).getName(), monitor);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final IPath path = root.getRawLocation().append(modelPath);
		final org.eclipse.emf.codegen.ecore.Generator generator = new Generator();

		IPath genModelPath = getGenModelResourcePath(modelPath);
		codeGeneration.setProject(getProject());
		codeGeneration.setGenerator(generator);
		codeGeneration.setGenModelPath(genModelPath);
		codeGeneration.setNextJob(getNextJob());
		setNextJob(null);

		genModelSetup.setProject(getProject());
		genModelSetup.setGenModelPath(genModelPath);
		genModelSetup.setNextJob(codeGeneration);

		if (!genModelPath.toFile().exists()) {
			genModelGeneration.setProject(getProject());
			genModelGeneration.setGenerator(generator);
			genModelGeneration.setPath(path);
			genModelGeneration.setNextJob(genModelSetup);
			genModelGeneration.schedule();
		} else
			genModelSetup.schedule();

		return Status.OK_STATUS;
	}

	private IPath ensureModelResourcePathExists(IProject project, String name,
			IProgressMonitor monitor) throws CoreException {
		if (!project.exists())
			throw new CoreException(ClassSupplierOSGi.createErrorStatus(NLS
					.bind("The project {0} was not created before generation.",
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

	protected void setupGenModel(IPath projectPath,
			org.eclipse.emf.codegen.ecore.genmodel.GenModel ecoreGenModel) {
		ecoreGenModel.setModelName(ClassSupplierUtil.getState(getProject())
				.getName());
		ecoreGenModel.setSuppressInterfaces(true);
		ecoreGenModel.setModelDirectory(projectPath.append(SOURCE_FOLDER_NAME)
				.toString());
		ecoreGenModel.setModelPluginID(getProject().getName());
	}

	@Override
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return generate(monitor);
	}

}
