/**
 * Copyright 2012-2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.jobs.codegen;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jgit.api.Git;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.jobs.EnterpriseDomainJob;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.enterprisedomain.classmaker.util.ResourceUtils;

public class EcoreGenerator extends EnterpriseDomainJob implements Worker {

	public EcoreGenerator(int stateTimestamp) {
		super(Messages.JobNameCodeGenerator, stateTimestamp);
	}

	protected static final String SOURCE_FOLDER_NAME = "src/main/java"; //$NON-NLS-1$

	public static final String GENMODEL_EXT = "genmodel"; //$NON-NLS-1$

	private GeneratorJob genModelGeneration = new GenModelGenerationJob(getStateTimestamp());

	private GeneratorJob genModelSetup = new GenModelSetupJob(this, getStateTimestamp());

	private CodeGenerationJob codeGeneration = new CodeGenerationJob(getStateTimestamp());

	protected static abstract class GeneratorJob extends EnterpriseDomainJob {

		private org.eclipse.emf.codegen.ecore.Generator generator;
		private IPath modelPath;
		private IPath genModelPath;

		public GeneratorJob(String jobName, int stateTimestamp) {
			super(jobName, stateTimestamp);
		}

		public org.eclipse.emf.codegen.ecore.Generator getGenerator() {
			return generator;
		}

		public void setGenerator(org.eclipse.emf.codegen.ecore.Generator generator) {
			this.generator = generator;
		}

		public IPath getModelLocation() {
			return modelPath;
		}

		public void setModelLocation(IPath path) {
			this.modelPath = path;
		}

		public IPath getGenModelLocation() {
			return genModelPath;
		}

		public void setGenModelLocation(IPath path) {
			this.genModelPath = path;
		}

		protected IPath computeProjectPath() {
			return getGenModelLocation().removeFileExtension().removeLastSegments(2);
		}

		@Override
		public Stage getPrerequisiteStage() {
			return Stage.MODELED;
		}

		@Override
		public Stage getResultStage() {
			return Stage.GENERATED;
		}

		@Override
		public Stage getDirtyStage() {
			return Stage.EXPORTED;
		}

	}

	protected class GenModelGenerationJob extends GeneratorJob {

		public GenModelGenerationJob(int stateTimestamp) {
			super(Messages.JobNameGenModelGeneration, stateTimestamp);
			setChangeRule(false);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			final IPath modelFullPath = root.getRawLocation().append(getModelLocation());
			int result = (Integer) getGenerator().run(new String[] { "-ecore2GenModel", modelFullPath.toString(), "", //$NON-NLS-1$ //$NON-NLS-2$
					getContributionState().getModelName() });
			if (result == 1)
				throw new CoreException(ClassMakerPlugin.createErrorStatus("GenModel generation failed."));
			else {
				monitor.worked(1);
				if (result == 0)
					return Status.OK_STATUS;
				else
					return ClassMakerPlugin.createWarningStatus("GenModel generation returned unknown result.");
			}
		}

	}

	protected class CodeGenerationJob extends GeneratorJob {

		public CodeGenerationJob(int stateTimestamp) {
			super(NLS.bind(Messages.JobNameCodeGeneration, "Code"), stateTimestamp);
			setChangeRule(false);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			try {
				ResourceUtils.cleanupDir(getProject(), SOURCE_FOLDER_NAME);
				try {
					getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
				} catch (OperationCanceledException e) {
				}
				int result = (Integer) getGenerator().run(new String[] { "-forceOverwrite", "-codeFormatting", //$NON-NLS-1$ //$NON-NLS-2$
						"default", "-model", "-reconcile", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						EcorePlugin.getWorkspaceRoot().getRawLocation().append(getGenModelLocation()).toString() });
				getContributionState().setProjectVersion(monitor);
				try {
					@SuppressWarnings("unchecked")
					SCMOperator<Git> operator = (SCMOperator<Git>) ClassMakerPlugin.getClassMaker().getSCMRegistry()
							.get(getProject().getName());
					operator.add(".");
				} catch (Exception e) {
					throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
				}
				if (result == 1)
					throw new CoreException(ClassMakerPlugin.createErrorStatus("Code generation failed."));
				else {
					if (result == 0)
						return Status.OK_STATUS;
					else
						return ClassMakerPlugin.createWarningStatus("Code generation returned unknown result.");
				}
			} finally {
				monitor.done();
			}

		}

	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		IPath modelPath = ensureModelResourcePathExists(getProject(), getContributionState().getModelName(), monitor);
		IPath genModelPath = getGenModelResourcePath(modelPath);
		final org.eclipse.emf.codegen.ecore.Generator generator = new Generator();

		codeGeneration.setResourceSet(getResourceSet());
		codeGeneration.setProject(getProject());
		codeGeneration.setGenerator(generator);
		codeGeneration.setGenModelLocation(genModelPath);
		codeGeneration.setNextJob(getNextJob());
		setNextJob(null);

		genModelSetup.setResourceSet(getResourceSet());
		genModelSetup.setProject(getProject());
		genModelSetup.setModelLocation(modelPath);
		genModelSetup.setGenModelLocation(genModelPath);
		genModelSetup.setNextJob(codeGeneration);

		genModelGeneration.setResourceSet(getResourceSet());
		genModelGeneration.setProject(getProject());
		genModelGeneration.setGenerator(generator);
		genModelGeneration.setModelLocation(modelPath);
		genModelGeneration.setNextJob(genModelSetup);

		monitor.beginTask("Generating Code", 3);
		genModelGeneration.schedule();

		return Status.OK_STATUS;
	}

	private IPath ensureModelResourcePathExists(IProject project, String name, IProgressMonitor monitor)
			throws CoreException {
		if (!project.exists())
			throw new CoreException(ClassMakerPlugin.createErrorStatus(NLS.bind(Messages.ProjectNotExist, project)));
		project.open(monitor);
		IFolder folder = project.getFolder(ResourceUtils.getModelFolderName());
		if (!folder.exists())
			folder.create(true, true, monitor);
		IFile file = folder.getFile(ResourceUtils.getFileName(name));
		return file.getFullPath();
	}

	public IPath getGenModelResourcePath(IPath path) {
		return path.removeFileExtension().addFileExtension(GENMODEL_EXT);
	}

	protected void setupGenModel(IPath projectPath, GenModel genModel, Collection<EPackage> ePackages) {
		for (EPackage ePackage : ePackages) {
			GenPackage genPackage = genModel.findGenPackage(ePackage);
			if (genPackage != null)
				genPackage.setEcorePackage(ePackage);
		}
		genModel.reconcile();
		genModel.initialize(ePackages);
		genModel.setCanGenerate(true);
		genModel.setComplianceLevel(GenJDKLevel.JDK70_LITERAL);
		genModel.setUpdateClasspath(true);
		genModel.setModelDirectory(projectPath.append(SOURCE_FOLDER_NAME).toString());
		genModel.setSuppressInterfaces(true);
		for (GenPackage genPackage : genModel.getGenPackages())
			genPackage.setPrefix(CodeGenUtil.capName(genPackage.getPrefix(), genModel.getLocale()));

		if (getContributionState() != null) {
			for (String qualifiedName : getContributionState().getRequiredPlugins()) {
				genModel.getModelPluginVariables().add(qualifiedName);
			}
			genModel.setModelName(getContributionState().getModelName());
			genModel.setLanguage(getContributionState().getLanguage());
			genModel.setModelPluginID(EcoreGenerator.this.getProject().getName());
			for (StageQualifier filter : getContributionState().getCustomizers().keySet())
				if (filter.equals(ClassMakerService.Stages
						.lookup(ClassMakerService.Stages.ID_PREFIX + "generation.genmodel.setup")))
					getContributionState().getCustomizers().get(filter)
							.customize(ECollections.asEList(projectPath, genModel, ePackages));
			getContributionState().setPackageClassName(genModel.getGenPackages().get(0).getBasicPackageName());
		}

		genModel.setValidateModel(true);
	}

	@Override
	public Stage getPrerequisiteStage() {
		return Stage.MODELED;
	}

	@Override
	public Stage getResultStage() {
		return Stage.GENERATED;
	}

	@Override
	public Stage getDirtyStage() {
		return Stage.EXPORTED;
	}

}
