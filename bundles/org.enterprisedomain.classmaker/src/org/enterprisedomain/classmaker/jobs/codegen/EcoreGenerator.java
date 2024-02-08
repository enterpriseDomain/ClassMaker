/**
 * Copyright 2012-2018 Kyrill Zotkin
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenOSGiStyle;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
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

	protected static final String SOURCE_FOLDER_NAME = "src"; /// main/java"; //$NON-NLS-1$

	public static final String GENMODEL_EXT = "genmodel"; //$NON-NLS-1$

	private GeneratorJob genModelGeneration = new GenModelGenerationJob(getDepth(), getStateTimestamp());

	private GeneratorJob genModelSetup = new GenModelSetupJob(this, getDepth(), getStateTimestamp());

	private CodeGenerationJob codeGeneration = new CodeGenerationJob(getDepth(), getStateTimestamp());

	private String modelName;

	protected static abstract class GeneratorJob extends EnterpriseDomainJob {

		private org.eclipse.emf.codegen.ecore.Generator generator;
		private IPath modelPath;
		private IPath genModelPath;

		public GeneratorJob(String jobName, int depth, long stateTimestamp) {
			super(jobName, depth, stateTimestamp);
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
			ResourceUtils.delete(getGenModelResourcePath(modelFullPath).toFile(), null);
			int result = (Integer) getGenerator().run(new String[] { "-ecore2GenModel", modelFullPath.toString(), "", //$NON-NLS-1$ //$NON-NLS-2$
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

		private IClasspathEntry entry;

		public CodeGenerationJob(int depth, long stateTimestamp) {
			super(NLS.bind(Messages.JobNameCodeGeneration, "Code"), depth, stateTimestamp);
			setChangeRule(false);
		}

		@Override
		public IStatus work(final IProgressMonitor monitor) throws CoreException {
			try {
				ResourceUtils.cleanupDir(getProject(), SOURCE_FOLDER_NAME);
				try {
					getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
				} catch (OperationCanceledException ex) {
				}
				List<String> args = new ArrayList<String>();
				args.add("-forceOverwrite");
				args.add("-codeFormatting");
				args.add("default");
				args.add("-model");
				if (getContributionState().isEdit())
					args.add("-edit");
				if (getContributionState().isEditor())
					args.add("-editor");
				args.add("-autoBuild");
				args.add("false");
				args.add("-reconcile");
				args.add(EcorePlugin.getWorkspaceRoot().getRawLocation().append(getGenModelLocation()).toString());
				int result = (Integer) getGenerator().run((String[]) args.toArray(new String[args.size()]));
				getContributionState().setProjectVersion(monitor);

				updateClassPath(monitor);

				try {
					compile(monitor);
				} catch (CoreException ex) {
					return ex.getStatus();
				}

				try {
					@SuppressWarnings("unchecked")
					SCMOperator<Git> operator = (SCMOperator<Git>) getContributionState().getProject().getWorkspace()
							.getSCMRegistry().get(getProject().getName());
					operator.add(".");
				} catch (Exception ex) {
					throw new CoreException(ClassMakerPlugin.createErrorStatus(ex));
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

		private void compile(final IProgressMonitor monitor) throws CoreException {
			final SubMonitor pm = SubMonitor.convert(monitor);
			pm.setTaskName("Compile Java");
			pm.subTask("Compiling Java");
			final SubMonitor m = pm.newChild(5, SubMonitor.SUPPRESS_ISCANCELED);
			try {
				build(getProject(), m);
				if (getContributionState().isEdit()) {
					build(getProject().getWorkspace().getRoot().getProject(getProject().getName() + ".edit"), m);
				}
				if (getContributionState().isEditor()) {
					build(getProject().getWorkspace().getRoot().getProject(getProject().getName() + ".editor"), m);
				}
			} catch (OperationCanceledException ex) {
				monitor.setCanceled(true);
			} catch (CoreException ex) {
				ClassMakerPlugin.getInstance().getLog().log(ex.getStatus());
				throw ex;
			} catch (Exception ex) {
				throw new CoreException(ClassMakerPlugin.createErrorStatus(ex));
			} finally {
				if (m != null)
					m.done();
				if (pm != null)
					pm.done();
			}
			try {
				notifyAll();
			} catch (IllegalMonitorStateException ex) {
			}
		}

		private void build(final IProject project, final IProgressMonitor monitor) throws CoreException {
			ICommand command = ResourceUtils.getBuildSpec(project.getDescription(), JavaCore.BUILDER_ID);
			project.getWorkspace().addResourceChangeListener(new IResourceChangeListener() {

				@Override
				public void resourceChanged(IResourceChangeEvent event) {
					if (event.getDelta() != null)
						for (IResourceDelta delta : event.getDelta().getAffectedChildren(IResourceDelta.CHANGED))
							if (delta.getResource().equals(getProject()))
								try {
									project.notifyAll();
								} catch (IllegalMonitorStateException ex) {
								}
				}
			}, IResourceChangeEvent.POST_BUILD);
			project.build(IncrementalProjectBuilder.FULL_BUILD, JavaCore.BUILDER_ID, command.getArguments(), monitor);
		}

		private void updateClassPath(IProgressMonitor monitor) throws CoreException {
			final SubMonitor pm = SubMonitor.convert(monitor);
			pm.setTaskName("Update Classpath");
			pm.subTask("Setting Classpath...");
			final SubMonitor m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
			try {
				IJavaProject javaProject = null;
				IJavaProject editJavaProject = null;
				IJavaProject editorJavaProject = null;
				try {
					javaProject = JavaCore
							.create(ResourcesPlugin.getWorkspace().getRoot().getProject(getProject().getName()));
					if (getContributionState().isEdit())
						editJavaProject = JavaCore.create(
								ResourcesPlugin.getWorkspace().getRoot().getProject(getProject().getName() + ".edit"));
					if (getContributionState().isEditor())
						editorJavaProject = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot()
								.getProject(getProject().getName() + ".editor"));

				} catch (IllegalStateException ex) {
					throw new CoreException(ClassMakerPlugin.createErrorStatus(ex));
				}
				Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>();
				Set<IClasspathEntry> editEntries = new HashSet<IClasspathEntry>();
				Set<IClasspathEntry> editorEntries = new HashSet<IClasspathEntry>();
				for (IClasspathEntry en : javaProject.getRawClasspath())
					if (!en.getPath().equals(new Path(IPath.SEPARATOR + getProject().getName())))
						entries.add(en);
				if (editJavaProject != null)
					for (IClasspathEntry en : editJavaProject.getRawClasspath())
						if (!en.getPath().equals(new Path(IPath.SEPARATOR + getProject().getName() + ".edit")))
							editEntries.add(en);
				if (editorJavaProject != null)
					for (IClasspathEntry en : editorJavaProject.getRawClasspath())
						if (!en.getPath().equals(new Path(IPath.SEPARATOR + getProject().getName() + ".editor")))
							editorEntries.add(en);
				entry = JavaCore.newSourceEntry(
						new Path(IPath.SEPARATOR + getProject().getName() + IPath.SEPARATOR + SOURCE_FOLDER_NAME), null,
						new Path(IPath.SEPARATOR + getProject().getName() + IPath.SEPARATOR + "bin" + IPath.SEPARATOR));
				entries.removeIf(en -> {
					return en.getPath().isPrefixOf(entry.getPath()) && en.getOutputLocation() == null;
				});
				entries.add(entry);
				if (editJavaProject != null) {
					entry = JavaCore.newSourceEntry(
							new Path(IPath.SEPARATOR + getProject().getName() + ".edit" + IPath.SEPARATOR
									+ SOURCE_FOLDER_NAME),
							null, new Path(IPath.SEPARATOR + getProject().getName() + ".edit" + IPath.SEPARATOR + "bin"
									+ IPath.SEPARATOR));
					editEntries.removeIf(en -> {
						return en.getPath().isPrefixOf(entry.getPath()) && en.getOutputLocation() == null;
					});
					if (!editEntries.contains(entry))
						editEntries.add(entry);
				}
				if (editorJavaProject != null) {
					entry = JavaCore.newSourceEntry(
							new Path(IPath.SEPARATOR + getProject().getName() + ".editor" + IPath.SEPARATOR
									+ SOURCE_FOLDER_NAME),
							null, new Path(IPath.SEPARATOR + getProject().getName() + ".editor" + IPath.SEPARATOR
									+ "bin" + IPath.SEPARATOR));
					editorEntries.removeIf(en -> {
						return en.getPath().isPrefixOf(entry.getPath()) && en.getOutputLocation() == null;
					});
					if (!editorEntries.contains(entry))
						editorEntries.add(entry);
				}
				entry = JavaCore.newContainerEntry(new Path(
						"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-17"),
						null, new IClasspathAttribute[] { JavaCore.newClasspathAttribute("module", "true") }, true);
				entries.removeIf(en -> {
					return en.getPath().isPrefixOf(entry.getPath());
				});
				if (!entries.contains(entry))
					entries.add(entry);
				if (editJavaProject != null) {
					entry = JavaCore.newContainerEntry(new Path(
							"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-17"),
							null, new IClasspathAttribute[] { JavaCore.newClasspathAttribute("module", "true") }, true);
					editEntries.removeIf(en -> {
						return en.getPath().isPrefixOf(entry.getPath());
					});
					if (!editEntries.contains(entry))
						editEntries.add(entry);
				}
				if (editorJavaProject != null) {
					entry = JavaCore.newContainerEntry(new Path(
							"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-17"),
							null, new IClasspathAttribute[] { JavaCore.newClasspathAttribute("module", "true") }, true);
					if (!editorEntries.contains(entry))
						editorEntries.add(entry);
				}
				entry = JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins"), null, null, false);
				editorEntries.removeIf(en -> {
					return en.getPath().isPrefixOf(entry.getPath());
				});
				if (!entries.contains(entry))
					entries.add(entry);
				if (editJavaProject != null) {
					entry = JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins"), null, null,
							false);
					if (!editEntries.contains(entry))
						editEntries.add(entry);
				}
				if (editorJavaProject != null) {
					entry = JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins"), null, null,
							false);
					if (!editorEntries.contains(entry))
						editorEntries.add(entry);
				}
				javaProject.setRawClasspath((IClasspathEntry[]) entries.toArray(new IClasspathEntry[entries.size()]),
						m);
				javaProject.getResolvedClasspath(false);
				if (editJavaProject != null) {
					editJavaProject.setRawClasspath(
							(IClasspathEntry[]) editEntries.toArray(new IClasspathEntry[editEntries.size()]), m);
					editJavaProject.getResolvedClasspath(false);
				}
				if (editorJavaProject != null) {
					editorJavaProject.setRawClasspath(
							(IClasspathEntry[]) editorEntries.toArray(new IClasspathEntry[editorEntries.size()]), m);
					editorJavaProject.getResolvedClasspath(false);
				}
			} catch (JavaModelException mex) {
				throw new CoreException(ClassMakerPlugin.createErrorStatus(mex));
			} catch (OperationCanceledException ocex) {
				monitor.setCanceled(true);
			} finally {
				m.done();
				pm.done();
			}
		}

	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		modelName = getProject().getName();
		if (getContributionState() != null)
			modelName = getContributionState().getModelName();
		IPath modelPath = ensureModelResourcePathExists(getProject(), modelName, monitor);
		IPath genModelPath = getGenModelResourcePath(modelPath);
		final org.eclipse.emf.codegen.ecore.Generator generator = new Generator();

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
		if (!file.exists())
			file = folder.getFile(ResourceUtils.getEcoreFileName(name));
		return file.getFullPath();
	}

	public IPath getGenModelResourcePath(IPath path) {
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
		genModel.setCanGenerate(true);
		genModel.setComplianceLevel(GenJDKLevel.JDK170_LITERAL);
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
				if (ClassMakerService.Stages
						.lookup(ClassMakerService.Stages.ID_PREFIX + "project.generation.genmodel.setup")
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
