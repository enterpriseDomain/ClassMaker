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
package org.enterprisedomain.classsupplier.jobs.codegen;

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
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classsupplier.Messages;
import org.enterprisedomain.classsupplier.Stage;
import org.enterprisedomain.classsupplier.StageQualifier;
import org.enterprisedomain.classsupplier.core.ClassSupplierOSGi;
import org.enterprisedomain.classsupplier.jobs.EnterpriseDomainJob;
import org.enterprisedomain.classsupplier.util.GitUtil;
import org.enterprisedomain.classsupplier.util.ResourceUtils;

public class EcoreGenerator extends EnterpriseDomainJob
		implements org.enterprisedomain.classsupplier.jobs.codegen.Generator {

	public EcoreGenerator(IProject project, long runId) {
		super(Messages.JobNameCodeGenerator, runId);
		setProject(project);
	}

	protected static final String SOURCE_FOLDER_NAME = "src/main/java"; //$NON-NLS-1$

	public static final String GENMODEL_EXT = "genmodel"; //$NON-NLS-1$

	private GeneratorJob genModelGeneration = new GenModelGenerationJob(getRunId());

	private GeneratorJob genModelSetup = new GenModelSetupJob(this, getRunId());

	private CodeGenerationJob codeGeneration = new CodeGenerationJob(getRunId());

	protected static abstract class GeneratorJob extends EnterpriseDomainJob {

		private org.eclipse.emf.codegen.ecore.Generator generator;
		private IPath modelPath;
		private IPath genModelPath;

		public GeneratorJob(String jobName, long runId) {
			super(jobName, runId);
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

		public GenModelGenerationJob(long runId) {
			super(Messages.JobNameGenModelGeneration, runId);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			final IPath modelFullPath = root.getRawLocation().append(getModelLocation());
			int result = (Integer) getGenerator().run(new String[] { "-ecore2GenModel", modelFullPath.toString(), "", //$NON-NLS-1$ //$NON-NLS-2$
					getContributionState().getModelName() });
			if (result == 1)
				throw new CoreException(ClassSupplierOSGi.createErrorStatus("GenModel generation failed."));
			else {
				monitor.worked(1);
				if (result == 0)
					return Status.OK_STATUS;
				else
					return ClassSupplierOSGi.createWarningStatus("GenModel generation returned unknown result.");
			}
		}

	}

	protected class CodeGenerationJob extends GeneratorJob {

		public CodeGenerationJob(long runId) {
			super(Messages.JobNameCodeGeneration, runId);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			ResourceUtils.cleanupDir(getProject(), SOURCE_FOLDER_NAME);
			getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
			int result = (Integer) getGenerator()
					.run(new String[] { "-forceOverwrite", "-codeFormatting", "default", "-model", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
							EcorePlugin.getWorkspaceRoot().getRawLocation().append(getGenModelLocation()).toString() });
			getContributionState().setProjectVersion(monitor);
			try {
				GitUtil.add(getProject().getName(), ".");
			} catch (GitAPIException e) {
				throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
			}
			if (result == 1)
				throw new CoreException(ClassSupplierOSGi.createErrorStatus("Code generation failed."));
			else {
				monitor.worked(1);
				if (result == 0)
					return Status.OK_STATUS;
				else
					return ClassSupplierOSGi.createWarningStatus("Code generation returned unknown result.");
			}
		}

	}

	@Override
	public IStatus generate(IProgressMonitor monitor) throws CoreException {
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
			throw new CoreException(ClassSupplierOSGi.createErrorStatus(NLS.bind(Messages.ProjectNotExist, project)));
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

	protected void setupGenModel(IPath projectPath, org.eclipse.emf.codegen.ecore.genmodel.GenModel genModel,
			Collection<EPackage> ePackages) {
		for (EPackage ePackage : ePackages) {
			GenPackage genPackage = genModel.findGenPackage(ePackage);
			if (genPackage != null)
				genPackage.setEcorePackage(ePackage);
		}
		genModel.reconcile();
		genModel.initialize(ePackages);
		genModel.setComplianceLevel(GenJDKLevel.JDK70_LITERAL);
		genModel.setUpdateClasspath(true);
		genModel.setModelDirectory(projectPath.append(SOURCE_FOLDER_NAME).toString());
		genModel.setSuppressInterfaces(true);
		for (GenPackage genPackage : genModel.getGenPackages())
			genPackage.setPrefix(CodeGenUtil.capName(genPackage.getPrefix(), genModel.getLocale()));

		if (getContributionState() != null) {
			genModel.setModelName(getContributionState().getModelName());
			genModel.setLanguage(getContributionState().getLanguage());
			genModel.setModelPluginID(EcoreGenerator.this.getProject().getName());
			for (StageQualifier filter : getContributionState().getCustomizers().keySet())
				if (filter.equals(GenModelSetupJob.STAGE))
					getContributionState().getCustomizers().get(filter)
							.customize(ECollections.asEList(projectPath, genModel, ePackages));
		}
		genModel.setValidateModel(true);
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return generate(monitor);
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
