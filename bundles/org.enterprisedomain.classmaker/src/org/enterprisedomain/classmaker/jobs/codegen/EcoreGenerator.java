/**
 * Copyright 2012-2026 Kyrill Zotkin
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
 * 
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 */
package org.enterprisedomain.classmaker.jobs.codegen;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.monitor.Monitor;
import javax.tools.Diagnostic;

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
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOSGiStyle;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
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

	public EcoreGenerator(int depth, long stateTimestamp) {
		super(Messages.JobNameCodeGenerator, depth, stateTimestamp);
	}

	public static final String GENMODEL_EXT = "genmodel"; //$NON-NLS-1$

	private GeneratorJob genModelGeneration = new GenModelGenerationJob(getDepth(), getStateTimestamp());

	private GeneratorJob genModelSetup = new GenModelSetupJob(this, getDepth(), getStateTimestamp());

	private CodeGenerationJob codeGeneration = new CodeGenerationJob(getDepth(), getStateTimestamp());

	private String modelName;

	protected static abstract class GeneratorJob extends EnterpriseDomainJob {

		private org.eclipse.emf.codegen.ecore.generator.Generator generator;
		private org.eclipse.emf.codegen.ecore.Generator generatorBackend;
		private IPath modelPath;
		private IPath genModelPath;

		public GeneratorJob(String jobName, int depth, long stateTimestamp) {
			super(jobName, depth, stateTimestamp);
		}

		public org.eclipse.emf.codegen.ecore.Generator getGeneratorBackend() {
			return generatorBackend;
		}

		public void setGeneratorBackend(org.eclipse.emf.codegen.ecore.Generator generatorBackend) {
			this.generatorBackend = generatorBackend;
		}

		public org.eclipse.emf.codegen.ecore.generator.Generator getGenerator() {
			return generator;
		}

		public void setGenerator(org.eclipse.emf.codegen.ecore.generator.Generator generator) {
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
		public Stage getResultStage() {
			return Stage.GENERATED;
		}

		@Override
		public Stage getDirtyStage() {
			return Stage.EXPORTED;
		}

	}

	public class GenModelGenerationJob extends GeneratorJob {

		public GenModelGenerationJob(int depth, long stateTimestamp) {
			super(Messages.JobNameGenModelGeneration, depth, stateTimestamp);
			setChangeRule(false);
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			final IPath modelFullPath = root.getRawLocation().append(getModelLocation());
			ResourceUtils.delete(obtainToGenModelResourcePath(modelFullPath).toFile(), null);
			int result = (Integer) getGeneratorBackend()
					.run(new String[] { "-ecore2GenModel", modelFullPath.toString(), "", //$NON-NLS-1$ //$NON-NLS-2$
							modelName });
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

	public class CodeGenerationJob extends GeneratorJob {

		public static final GeneratorAdapterFactory.Descriptor MY_DESCRIPTOR = new GeneratorAdapterFactory.Descriptor() {
			public GeneratorAdapterFactory createAdapterFactory() {
				return new MyGenModelGeneratorAdapterFactory();
			}
		};

		public CodeGenerationJob(int depth, long stateTimestamp) {
			super(NLS.bind(Messages.JobNameCodeGeneration, "Code"), depth, stateTimestamp);
			setChangeRule(false);
		}

		static {
			EcorePackage.eINSTANCE.getEFactoryInstance();
			GenModelPackage.eINSTANCE.getEFactoryInstance();
		}

		protected void registerGenModel(ResourceSet resourceSet, URI genmodelURI) throws CoreException {
			Resource resource = resourceSet.getResource(genmodelURI, true);
			if (resource == null)
				throw new CoreException(
						ClassMakerPlugin.createErrorStatus("Couldn't find resource under  " + genmodelURI));
			for (EObject object : resource.getContents())
				if (object instanceof GenModel)
					registerGenModel((GenModel) object);
		}

		protected Collection<GenPackage> collectGenPackages(GenModel genModel) {
			List<GenPackage> pkgs = new ArrayList<GenPackage>();
			for (GenPackage pkg : genModel.getGenPackages()) {
				pkgs.add(pkg);
				pkgs.addAll(collectGenPackages(pkg));
			}
			pkgs.addAll(genModel.getUsedGenPackages());
			return pkgs;
		}

		protected Collection<GenPackage> collectGenPackages(GenPackage genPackage) {
			List<GenPackage> pkgs = new ArrayList<GenPackage>();
			for (GenPackage pkg : genPackage.getNestedGenPackages()) {
				pkgs.add(pkg);
				pkgs.addAll(collectGenPackages(pkg));
			}
			return pkgs;
		}

		protected void registerGenModel(GenModel genModel) throws CoreException {
			Map<String, URI> registry = EcorePlugin.getEPackageNsURIToGenModelLocationMap(false);
			for (GenPackage pkg : collectGenPackages(genModel)) {
				if (pkg.eIsProxy()) {
					ClassMakerPlugin.print("Unresolved proxy for GenPackage " + EcoreUtil.getURI(pkg));
					continue;
				}
				String nsURI = pkg.getEcorePackage().getNsURI();
				if (nsURI != null) {
					URI newUri = pkg.eResource().getURI();
					if (registry.containsKey(nsURI)) {
						URI oldURI = registry.get(nsURI);
						if (!oldURI.equals(newUri))
							throw new CoreException(ClassMakerPlugin.createWarningStatus(
									"There is already a GenModel registered for NamespaceURI '" + nsURI
											+ "'. It will be overwritten from '" + oldURI + "' to '" + newUri + "'"));
						else
							continue;
					}
					registry.put(nsURI, newUri);
					ClassMakerPlugin.print("Registered GenModel '" + nsURI + "' from '" + newUri + "'");
				}
			}
		}

		@Override
		public IStatus work(final IProgressMonitor monitor) throws CoreException {
			try {
				ResourceUtils.cleanupDir(getProject(), ResourceUtils.SOURCE_FOLDER_NAME);
				try {
					getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
				} catch (OperationCanceledException ex) {
				}
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				URI genModelURI = URI.createFileURI(root.getRawLocation().append(getGenModelLocation()).toString());
				final GenModel genModel = (GenModel) getResourceSet().getResource(genModelURI, true).getContents()
						.get(0);
				genModel.setCanGenerate(true);
				genModel.reconcile();
				registerGenModel(getResourceSet(), genModelURI);
				getGenerator().setInput(genModel);
				ClassMakerPlugin.print("Generating EMF code for " + genModel.getModelName());
				{
					getGenerator().getAdapterFactoryDescriptorRegistry().addDescriptor(GenModelPackage.eNS_URI,
							MY_DESCRIPTOR);
					Diagnostic diagnostic = getGenerator().generate(genModel,
							GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, new BasicMonitor());
					if (diagnostic.getSeverity() != Diagnostic.OK)
						throw new CoreException(ClassMakerPlugin.createErrorStatus(diagnostic.getException()));
				}

				if (getContributionState().isEdit()) {
					Diagnostic diagnostic = getGenerator().generate(genModel, GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE,
							new BasicMonitor());
					if (diagnostic.getSeverity() != Diagnostic.OK)
						throw new CoreException(ClassMakerPlugin.createErrorStatus(diagnostic.getException()));
				}

				if (getContributionState().isEditor()) {
					Diagnostic diagnostic = getGenerator().generate(genModel,
							GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, new BasicMonitor());
					if (diagnostic.getSeverity() != Diagnostic.OK)
						throw new CoreException(ClassMakerPlugin.createErrorStatus(diagnostic.getException()));
				}
				getContributionState().setProjectVersion(monitor);

				try {
					@SuppressWarnings("unchecked")
					SCMOperator<Git> operator = (SCMOperator<Git>) getContributionState().getProject().getWorkspace()
							.getSCMRegistry().get(getProject().getName());
					operator.add(".");
				} catch (Exception ex) {
					throw new CoreException(ClassMakerPlugin.createErrorStatus(ex));
				}
				return Status.OK_STATUS;
			} finally {
				monitor.done();
			}
		}

		private static class MyGenModelGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory
				implements GeneratorAdapterFactory {

			@Override
			public boolean isFactoryForType(Object type) {
				return super.isFactoryForType(type);
			}

			@Override
			public Adapter createGenModelAdapter() {
				return new GenModelGeneratorAdapter(this) {

					@Override
					protected void generateModelManifest(GenModel genModel, Monitor monitor) {
						super.generateModelManifest(genModel, monitor);
					}

				};
			}

		}
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		modelName = getProject().getName();
		if (getContributionState() != null)
			modelName = getContributionState().getModelName();
		IPath modelPath = ensureExistsAndGetModelResourcePath(getProject(), modelName, monitor);
		IPath genModelPath = obtainToGenModelResourcePath(modelPath);
		final org.eclipse.emf.codegen.ecore.Generator generatorBackend = new Generator();
		final org.eclipse.emf.codegen.ecore.generator.Generator generator = new org.eclipse.emf.codegen.ecore.generator.Generator() {

			@Override
			public JControlModel getJControlModel() {
				return new JControlModel() {

					@Override
					public boolean canMerge() {
						return false;
					}
				};
			}

			@Override
			protected Collection<GeneratorAdapterFactory> getAdapterFactories(Object object) {
				if (packageIDToAdapterFactories == null) {
					packageIDToAdapterFactories = new HashMap<String, Collection<GeneratorAdapterFactory>>();
				}
				String packageID = getPackageID(object);
				Collection<GeneratorAdapterFactory> result = packageIDToAdapterFactories.get(packageID);
				if (result == null) {
					Collection<GeneratorAdapterFactory.Descriptor> descriptors = getAdapterFactoryDescriptorRegistry()
							.getDescriptors(packageID);
					result = new ArrayList<GeneratorAdapterFactory>(descriptors.size());
					for (GeneratorAdapterFactory.Descriptor descriptor : descriptors) {
						GeneratorAdapterFactory adapterFactory = descriptor.createAdapterFactory();
						adapterFactory.setGenerator(this);
						result.add(adapterFactory);
					}
					packageIDToAdapterFactories.put(packageID, result);
				} else {
					for (GeneratorAdapterFactory factory : result) {
						if (factory instanceof org.enterprisedomain.classmaker.jobs.codegen.EcoreGenerator.CodeGenerationJob.MyGenModelGeneratorAdapterFactory)
							return result;
					}
					GeneratorAdapterFactory adapterFactory = CodeGenerationJob.MY_DESCRIPTOR.createAdapterFactory();
					adapterFactory.setGenerator(this);
					result.add(adapterFactory);
					packageIDToAdapterFactories.put(packageID, result);
				}
				return result;
			}
		};

		codeGeneration.setResourceSet(getResourceSet());
		codeGeneration.setContributionState(getContributionState());
		codeGeneration.setProject(getProject());
		codeGeneration.setGenerator(generator);
		codeGeneration.setGenModelLocation(genModelPath);
		codeGeneration.setNextJob(getNextJob());
		setNextJob(null);

		genModelSetup.setResourceSet(getResourceSet());
		genModelSetup.setContributionState(getContributionState());
		genModelSetup.setProject(getProject());
		genModelSetup.setModelLocation(modelPath);
		genModelSetup.setGenModelLocation(genModelPath);
		genModelSetup.setNextJob(codeGeneration);

		genModelGeneration.setResourceSet(getResourceSet());
		genModelGeneration.setContributionState(getContributionState());
		genModelGeneration.setProject(getProject());
		genModelGeneration.setGeneratorBackend(generatorBackend);
		genModelGeneration.setModelLocation(modelPath);
		genModelGeneration.setNextJob(genModelSetup);

		monitor.beginTask("Generating Code", 3);
		genModelGeneration.schedule();

		return Status.OK_STATUS;
	}

	private IPath ensureExistsAndGetModelResourcePath(IProject project, String name, IProgressMonitor monitor)
			throws CoreException {
		if (!project.exists())
			throw new CoreException(ClassMakerPlugin.createErrorStatus(NLS.bind(Messages.ProjectNotExist, project)));
		project.open(monitor);
		IFolder folder = project.getFolder(ResourceUtils.getModelFolderName());
		if (!folder.exists())
			folder.create(true, true, monitor);
		IFile file = folder.getFile(ResourceUtils.getFileName(name));
		if (!file.exists())
			file = folder.getFile(ResourceUtils.getEcoreFileName(name));
		return file.getFullPath();
	}

	public IPath obtainToGenModelResourcePath(IPath path) {
		return path.removeFileExtension().addFileExtension(GENMODEL_EXT);
	}

	protected void setupGenModel(IPath projectPath, GenModel genModel, Collection<EPackage> ePackages) {
		for (EPackage ePackage : ePackages) {
			GenPackage genPackage = genModel.findGenPackage(ePackage);
			if (genPackage != null) {
				genPackage.setEcorePackage(ePackage);
			}
		}
		genModel.reconcile();
		genModel.initialize(ePackages);
		for (EPackage ePackage : ePackages) {
			GenPackage genPackage = genModel.findGenPackage(ePackage);
			if (genPackage != null) {
				getContributionState().setBasePackage(genPackage.getBasePackage());
			}
		}
		genModel.setCodeFormatting(true);
		genModel.setImportOrganizing(true);
		genModel.setForceOverwrite(true);
		genModel.setCleanup(true);
		genModel.setCanGenerate(true);
		genModel.setComplianceLevel(GenJDKLevel.JDK210_LITERAL);
		genModel.setUpdateClasspath(true);
		genModel.setModelDirectory(projectPath.append(ResourceUtils.SOURCE_FOLDER_NAME).toString());
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
				if (ClassMakerService.Stages.lookup(Stage.GENERATED, "project.generation.genmodel.setup")
						.equals(filter))
					getContributionState().getCustomizers().get(filter)
							.customize(ECollections.asEList(projectPath, genModel, ePackages));
			getContributionState().setPackageClassName(genModel.getGenPackages().get(0).getBasicPackageName());
			getContributionState().setEditPluginClassName(genModel.getGenPackages().get(0).getEditPluginClassName());
			getContributionState()
					.setEditorPluginClassName(genModel.getGenPackages().get(0).getEditorPluginClassName());
			genModel.setOSGiCompatible(true);
			genModel.getOSGiStyle().add(GenOSGiStyle.PROVIDE_CAPABILITY_GENERATED_PACKAGE);
			genModel.setForceOverwrite(true);
			genModel.setPublicConstructors(true);
			genModel.setRuntimeCompatibility(false);
			genModel.setRuntimeJar(false);
			genModel.setUpdateClasspath(true);
		}

		genModel.setValidateModel(true);
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
