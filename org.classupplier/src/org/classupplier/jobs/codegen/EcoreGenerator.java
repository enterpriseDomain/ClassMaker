package org.classupplier.jobs.codegen;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.classupplier.Messages;
import org.classupplier.Phase;
import org.classupplier.core.ClassSupplierOSGi;
import org.classupplier.jobs.SupplementaryJob;
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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;

public class EcoreGenerator extends SupplementaryJob implements org.classupplier.jobs.codegen.Generator {

	public EcoreGenerator() {
		super(Messages.CodeGeneratorJobName);
		// setRule(EcorePlugin.getWorkspaceRoot());
		// setPriority(BUILD);
	}

	protected static final String SOURCE_FOLDER_NAME = "src/main/java"; //$NON-NLS-1$

	public static final String GENMODEL_EXT = "genmodel"; //$NON-NLS-1$

	private GeneratorJob genModelGeneration = new GenModelGenerationJob();

	private GeneratorJob genModelSetup = new GenModelSetupJob();

	private CodeGenerationJob codeGeneration = new CodeGenerationJob();

	protected ResourceSet resourceSet;

	protected abstract class GeneratorJob extends SupplementaryJob {

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
			super(Messages.GenModelGenerationJobName);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			final IPath modelFullPath = root.getRawLocation().append(getModelPath());
			getGenerator()
					.run(new String[] { "-ecore2GenModel", modelFullPath.toString(), "", getContribution().getName() }); //$NON-NLS-1$ //$NON-NLS-2$
			return Status.OK_STATUS;
		}

	}

	protected class GenModelSetupJob extends GeneratorJob {

		public GenModelSetupJob() {
			super(Messages.GenModelConfigurationJobName);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			// getResourceSet().getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));
			URI modelURI = URI.createFileURI(root.getRawLocation().append(getModelPath()).toString());
			Resource resource = getResourceSet().getResource(modelURI, true);
			EPackage modelEPackage = null;
			EList<EPackage> allEPackages = ECollections.newBasicEList();
			for (EObject eObject : resource.getContents()) {
				EPackage ePackage = null;
				if (eObject instanceof EPackage) {
					ePackage = (EPackage) eObject;
					allEPackages.add(ePackage);
					if (ResourceUtil.ePackagesAreEqual(ePackage, getContribution().getDynamicEPackage()))
						modelEPackage = ePackage;
				}
			}
			if (modelEPackage == null && !allEPackages.isEmpty()) {
				EList<EPackage> choise = ECollections.newBasicEList(allEPackages);
				choise.sort(new Comparator<EPackage>() {

					@Override
					public int compare(EPackage o1, EPackage o2) {
						return o1.getNsURI().compareToIgnoreCase(o2.getNsURI())
								+ o2.getName().compareToIgnoreCase(o2.getName());
					}

				});
				modelEPackage = choise.get(choise.size() - 1);
			}

			URI genModelURI = URI.createFileURI(root.getRawLocation().append(getGenModelPath()).toString());
			resource = getResourceSet().getResource(genModelURI, true);
			GenModel genModel = (GenModel) resource.getContents().get(0);
			setupGenModel(computeProjectPath(), genModel, modelEPackage, allEPackages);
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				throw new CoreException(ClassSupplierOSGi.createWarningStatus(e));
			}
			return Status.OK_STATUS;
		}

	}

	protected class CodeGenerationJob extends GeneratorJob {

		public CodeGenerationJob() {
			super(Messages.CodeGenerationJobName);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			getGenerator().run(new String[] { "-forceOverwrite", "-codeFormatting", "default", "-model", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					EcorePlugin.getWorkspaceRoot().getRawLocation().append(getGenModelPath()).toString() });
			getContribution().setProjectVersion(monitor);
			return Status.OK_STATUS;
		}

	}

	@Override
	public IStatus generate(IProgressMonitor monitor) throws CoreException {
		IPath modelPath = ensureModelResourcePathExists(getProject(), getContribution().getName(), monitor);
		IPath genModelPath = getGenModelResourcePath(modelPath);
		final org.eclipse.emf.codegen.ecore.Generator generator = new Generator();

		codeGeneration.setProject(getProject());
		codeGeneration.setGenerator(generator);
		codeGeneration.setGenModelPath(genModelPath);
		codeGeneration.setNextJob(getNextJob());
		setNextJob(null);

		genModelSetup.setProject(getProject());
		genModelSetup.setModelPath(modelPath);
		genModelSetup.setGenModelPath(genModelPath);
		genModelSetup.setNextJob(codeGeneration);

		genModelGeneration.setProject(getProject());
		genModelGeneration.setGenerator(generator);
		genModelGeneration.setModelPath(modelPath);
		genModelGeneration.setNextJob(genModelSetup);
		genModelGeneration.schedule();

		getContribution().setStage(Phase.GENERATED);
		return Status.OK_STATUS;
	}

	private IPath ensureModelResourcePathExists(IProject project, String name, IProgressMonitor monitor)
			throws CoreException {
		if (!project.exists())
			throw new CoreException(ClassSupplierOSGi
					.createErrorStatus(NLS.bind(Messages.ProjectNotExist, project)));
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

	protected void setupGenModel(IPath projectPath, org.eclipse.emf.codegen.ecore.genmodel.GenModel genModel,
			EPackage modelEPackage, Collection<EPackage> allEPackages) {
		for (EPackage ePackage : allEPackages) {
			GenPackage genPackage = genModel.findGenPackage(ePackage);
			if (genPackage != null)
				genPackage.setEcorePackage(modelEPackage);
		}
		genModel.reconcile();
		genModel.initialize(Collections.singletonList(modelEPackage));
		genModel.setModelName(getContribution().getName());
		genModel.setLanguage(getContribution().getLanguage());
		genModel.setComplianceLevel(GenJDKLevel.JDK70_LITERAL);
		genModel.setUpdateClasspath(true);
		genModel.setModelDirectory(projectPath.append(SOURCE_FOLDER_NAME).toString());
		genModel.setModelPluginID(getProject().getName());
		genModel.setSuppressInterfaces(true);
		for (GenPackage genPackage : genModel.getGenPackages())
			genPackage.setPrefix(CodeGenUtil.capName(genPackage.getPrefix(), genModel.getLocale()));
	}

	@Override
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
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
