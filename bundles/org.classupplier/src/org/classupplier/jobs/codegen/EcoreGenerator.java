package org.classupplier.jobs.codegen;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.classupplier.ClassSupplierFactory;
import org.classupplier.Messages;
import org.classupplier.Phase;
import org.classupplier.PhaseQualifier;
import org.classupplier.core.ClassSupplierOSGi;
import org.classupplier.jobs.ClassSupplierJob;
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
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;

public class EcoreGenerator extends ClassSupplierJob implements org.classupplier.jobs.codegen.Generator {

	private static EcoreGenerator INSTANCE;

	protected EcoreGenerator() {
		super(Messages.JobNameCodeGenerator);
		// setRule(EcorePlugin.getWorkspaceRoot());
		// setPriority(BUILD);
	}

	public static EcoreGenerator createInstance() {
		EcoreGenerator newInstance = new EcoreGenerator();
		INSTANCE = newInstance;
		return newInstance;
	}

	protected static final String SOURCE_FOLDER_NAME = "src/main/java"; //$NON-NLS-1$

	public static final String GENMODEL_EXT = "genmodel"; //$NON-NLS-1$

	private GeneratorJob genModelGeneration = new GenModelGenerationJob();

	private GeneratorJob genModelSetup = new GenModelSetupJob();

	private CodeGenerationJob codeGeneration = new CodeGenerationJob();

	protected static abstract class GeneratorJob extends ClassSupplierJob {

		private org.eclipse.emf.codegen.ecore.Generator generator;
		private IPath modelPath;
		private IPath genModelPath;

		public GeneratorJob(String jobName) {
			super(jobName);
		}

		public org.eclipse.emf.codegen.ecore.Generator getGenerator() {
			return generator;
		}

		public void setGenerator(org.eclipse.emf.codegen.ecore.Generator generator) {
			this.generator = generator;
		}

		public IPath getModelPath() {
			return modelPath;
		}

		public void setModelPath(IPath path) {
			this.modelPath = path;
		}

		public IPath getGenModelPath() {
			return genModelPath;
		}

		public void setGenModelPath(IPath path) {
			this.genModelPath = path;
		}

		protected IPath computeProjectPath() {
			return getGenModelPath().removeFileExtension().removeLastSegments(2);
		}

		@Override
		public Phase requiredStage() {
			return Phase.MODELED;
		}

	}

	protected class GenModelGenerationJob extends GeneratorJob {

		public GenModelGenerationJob() {
			super(Messages.JobNameGenModelGeneration);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			final IPath modelFullPath = root.getRawLocation().append(getModelPath());
			getGenerator()
					.run(new String[] { "-ecore2GenModel", modelFullPath.toString(), "", getContribution().getName() }); //$NON-NLS-1$ //$NON-NLS-2$
			monitor.worked(1);
			return Status.OK_STATUS;
		}

	}

	public static class GenModelSetupJob extends GeneratorJob {

		static {
			PhaseQualifier genModelSetupQualifier = ClassSupplierFactory.eINSTANCE.createPhaseQualifier();
			genModelSetupQualifier.setStage(Phase.GENERATED);
			genModelSetupQualifier.setFilter("genmodel.setup");
			STAGE = genModelSetupQualifier;
		}

		public static PhaseQualifier STAGE;

		public GenModelSetupJob() {
			super(Messages.JobNameGenModelConfiguration);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			// getResourceSet().getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));
			URI modelURI = URI.createFileURI(root.getRawLocation().append(getModelPath()).toString());
			Resource resource = getResourceSet().getResource(modelURI, true);
			EList<EPackage> ePackages = ECollections.newBasicEList();
			for (EObject eObject : resource.getContents()) {
				if (eObject instanceof EPackage) {
					ePackages.add((EPackage) eObject);
				}
			}

			URI genModelURI = URI.createFileURI(root.getRawLocation().append(getGenModelPath()).toString());
			resource = getResourceSet().getResource(genModelURI, true);
			GenModel genModel = (GenModel) resource.getContents().get(0);
			setupGenModel(computeProjectPath(), genModel, ePackages);
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				throw new CoreException(ClassSupplierOSGi.createWarningStatus(e));
			}
			monitor.worked(1);
			return Status.OK_STATUS;
		}

	}

	protected class CodeGenerationJob extends GeneratorJob {

		public CodeGenerationJob() {
			super(Messages.JobNameCodeGeneration);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			getGenerator().run(new String[] { "-forceOverwrite", "-codeFormatting", "default", "-model", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					EcorePlugin.getWorkspaceRoot().getRawLocation().append(getGenModelPath()).toString() });
			getContribution().setProjectVersion(monitor);
			monitor.worked(1);
			return Status.OK_STATUS;
		}

	}

	@Override
	public IStatus generate(IProgressMonitor monitor) throws CoreException {
		IPath modelPath = ensureModelResourcePathExists(getProject(), getContribution().getName(), monitor);
		IPath genModelPath = getGenModelResourcePath(modelPath);
		final org.eclipse.emf.codegen.ecore.Generator generator = new Generator();

		codeGeneration.setResourceSet(getResourceSet());
		codeGeneration.setProject(getProject());
		codeGeneration.setGenerator(generator);
		codeGeneration.setGenModelPath(genModelPath);
		codeGeneration.setNextJob(getNextJob());
		setNextJob(null);

		genModelSetup.setResourceSet(getResourceSet());
		genModelSetup.setProject(getProject());
		genModelSetup.setModelPath(modelPath);
		genModelSetup.setGenModelPath(genModelPath);
		genModelSetup.setNextJob(codeGeneration);

		genModelGeneration.setResourceSet(getResourceSet());
		genModelGeneration.setProject(getProject());
		genModelGeneration.setGenerator(generator);
		genModelGeneration.setModelPath(modelPath);
		genModelGeneration.setNextJob(genModelSetup);

		monitor.beginTask("Generating Code", 3);
		genModelGeneration.schedule();

		getContribution().setStage(Phase.GENERATED);
		return Status.OK_STATUS;
	}

	private IPath ensureModelResourcePathExists(IProject project, String name, IProgressMonitor monitor)
			throws CoreException {
		if (!project.exists())
			throw new CoreException(ClassSupplierOSGi.createErrorStatus(NLS.bind(Messages.ProjectNotExist, project)));
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

	protected static void setupGenModel(IPath projectPath, org.eclipse.emf.codegen.ecore.genmodel.GenModel genModel,
			Collection<EPackage> ePackages) {
		for (EPackage ePackage : ePackages) {
			GenPackage genPackage = genModel.findGenPackage(ePackage);
			if (genPackage != null)
				genPackage.setEcorePackage(ePackage);
		}
		genModel.reconcile();
		genModel.initialize(ePackages);
		genModel.setModelName(INSTANCE.getContribution().getName());
		genModel.setLanguage(INSTANCE.getContribution().getLanguage());
		genModel.setComplianceLevel(GenJDKLevel.JDK70_LITERAL);
		genModel.setUpdateClasspath(true);
		genModel.setModelDirectory(projectPath.append(SOURCE_FOLDER_NAME).toString());
		genModel.setModelPluginID(INSTANCE.getProject().getName());
		genModel.setSuppressInterfaces(true);
		for (GenPackage genPackage : genModel.getGenPackages())
			genPackage.setPrefix(CodeGenUtil.capName(genPackage.getPrefix(), genModel.getLocale()));
		for (PhaseQualifier filter : INSTANCE.getContribution().getCustomizers().keySet())
			if (filter.equals(GenModelSetupJob.STAGE))
				INSTANCE.getContribution().getCustomizers().get(filter)
						.customize(ECollections.asEList(projectPath, genModel, ePackages));
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return generate(monitor);
	}

	@Override
	public Phase requiredStage() {
		return Phase.MODELED;
	}

}
