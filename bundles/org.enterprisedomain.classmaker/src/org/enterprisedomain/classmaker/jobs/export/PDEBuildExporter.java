/**
 * Copyright 2012-2023 Kyrill Zotkin
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
package org.enterprisedomain.classmaker.jobs.export;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Flow.Publisher;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.internal.p2.artifact.repository.ArtifactRepositoryManager;
import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactRepositoryFactory;
import org.eclipse.equinox.internal.p2.metadata.repository.MetadataRepositoryManager;
import org.eclipse.equinox.internal.p2.metadata.repository.SimpleMetadataRepositoryFactory;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.eclipse.BundlesAction;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.manipulation.JavaManipulation;
import org.eclipse.jdt.core.manipulation.OrganizeImportsOperation;
import org.eclipse.jdt.core.manipulation.SharedASTProviderCore;
import org.eclipse.jgit.api.Git;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.pde.core.IEditableModel;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.build.IBuild;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.core.plugin.IPluginImport;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.core.project.IBundleClasspathEntry;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.eclipse.pde.core.project.IRequiredBundleDescription;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.core.bundle.WorkspaceBundleModel;
import org.eclipse.pde.internal.core.plugin.AbstractPluginModelBase;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ResourceUtils;

/**
 * PDE Build-based Plug-in Exporter.
 */
@SuppressWarnings("restriction")
public class PDEBuildExporter extends AbstractExporter {

	private IClasspathEntry entry;

	private Object lock = new Object();

	public PDEBuildExporter(int depth, long stateTimestamp) {
		super(depth, stateTimestamp);
	}

	@Override
	public IStatus work(final IProgressMonitor monitor) throws CoreException {
		synchronized (lock) {
			getContributionState();
			if (getProperties().isEmpty())
				getProperties().put(AbstractExporter.EXPORT_DESTINATION_PROP,
						ResourceUtils.getExportDestination(getContributionState()).toString());
			cleanup(monitor);
			PluginModelManager modelManager = PDECore.getDefault().getModelManager();
			Set<IPluginModelBase> models = new HashSet<IPluginModelBase>();
			IBundleProjectService service = ((IBundleProjectService) ClassMakerPlugin
					.getService(IBundleProjectService.class.getName()));
			addOutputClasspath(service, monitor);
			IBundleProjectDescription description = service.getDescription(getProject());
			description.setBinIncludes(ResourceUtils.addElement(description.getBinIncludes(), new Path(".")));

			for (IRequiredBundleDescription req : description.getRequiredBundles()) {
				IPluginModelBase reqModel = modelManager.findModel(req.name());
				models.add(reqModel.getPluginBase().getPluginModel());
			}
			for (IPluginModelBase m : PluginRegistry.getAllModels())
				if (m.getPluginBase().getId().equals(getProject().getName())) {
					updateBuildProperties(m);
					break;
				}
//			IPluginModelBase model = modelManager.findModel(getProject());
//			for (Bundle bundle : getBundles())
//				System.out.println(bundle.adapt(BundleWiring.class));
//			org.osgi.framework.wiring.BundleWiring;
//			for (IPluginModelBase b : modelManager.getActiveModels())
//				if (b.getPluginBase().getName().equals(getProject().getName()))
//					model = b;
//			IPluginModelBase model = modelManager.findModel(getProject().getDescription());// modelManager.findModel(getProject());

//			if (model != null) {
//				models.add(model);
//				for (BundleSpecification required : model.getBundleDescription().getRequiredBundles()) {
//					IPluginModelBase requiredModel = modelManager.findModel(required.getName());
//					if (requiredModel instanceof BundlePluginModel)
//						if (((BundlePluginModel) requiredModel).getBundleModel() instanceof WorkspaceBundleModel)
//							models.add(requiredModel);
//				}
//				updateBuildProperties(model);
//			}
			if (getContributionState().isEdit()) {
				IBundleProjectDescription editDescription = service.getDescription(getEditProject());
				if (editDescription != null) {
					for (IRequiredBundleDescription req : editDescription.getRequiredBundles()) {
						IPluginModelBase reqModel = modelManager.findModel(req.name());
						models.add(reqModel.getPluginBase().getPluginModel());
					}
					updateBuildProperties(modelManager.findModel(editDescription.getSymbolicName()));
					IPluginModelBase emfEdit = modelManager.findModel("org.eclipse.emf.edit"); //$NON-NLS-1$
					if (emfEdit != null)
						models.add(emfEdit);
				}
			}
			if (getContributionState().isEditor()) {
				IBundleProjectDescription editorDescription = service.getDescription(getEditorProject());
				if (editorDescription != null) {
					for (IRequiredBundleDescription req : editorDescription.getRequiredBundles()) {
						IPluginModelBase reqModel = modelManager.findModel(req.name());
						models.add(reqModel.getPluginBase().getPluginModel());
					}
					updateBuildProperties(modelManager.findModel(editorDescription.getSymbolicName()));
					IPluginModelBase emfEditor = modelManager.findModel("org.eclipse.emf.edit.ui"); //$NON-NLS-1$
					if (emfEditor != null)
						models.add(emfEditor);
				}
			}
			IPublisherInfo info;
			try {
				info = createPublisherInfo();
			} catch (ProvisionException e) {
				throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
			} catch (URISyntaxException e) {
				throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
			}
			IPublisherAction[] actions = null;
			if (!models.isEmpty()) {
				Set<IModel> ms = new HashSet<IModel>();
				for (IPluginModelBase m : models)
					ms.add(m);
				IModel[] ma = ms.toArray(new IModel[ms.size()]);
				actions = createActions(ma);
//				modelManager.modelsChanged(
//						new ModelProviderEvent(getProject(), IModelProviderEvent.MODELS_ADDED, ma, null, null));
			}

			final SubMonitor pm = SubMonitor.convert(monitor);
			pm.setTaskName(Messages.TaskNamePluginExport);
			pm.subTask(Messages.SubTaskNamePluginExport);
			final SubMonitor m = pm.newChild(9, SubMonitor.SUPPRESS_ISCANCELED);
			description.apply(m);
			try {
				compile(monitor);
			} catch (CoreException ex) {
				return ex.getStatus();
			}
			Publisher publisher = new Publisher(info);
			try {
				return publisher.publish(actions, m);
			} finally {
				if (m != null)
					m.done();
				if (pm != null)
					pm.done();
			}
		}
	}

	private void addOutputClasspath(IBundleProjectService service, IProgressMonitor monitor) throws CoreException {
		IBundleProjectDescription desc = service.getDescription(getProject());
		IBundleClasspathEntry[] es = desc.getBundleClasspath();
		IPath srcPath = null;
		IPath binPath = null;
		IPath jarPath = null;
		for (IBundleClasspathEntry e : es) {
			if (e.getSourcePath() != null)
				srcPath = e.getSourcePath();
			if (e.getBinaryPath() != null)
				binPath = e.getBinaryPath();
			if (e.getLibrary() != null)
				jarPath = e.getLibrary();
		}
		if (binPath == null)
			binPath = new Path("bin/");
		if (jarPath == null || jarPath.equals(new Path(".")))
			jarPath = new Path("bin");
		es[0] = service.newBundleClasspathEntry(srcPath, binPath, jarPath);
		desc.setBundleClasspath(es);
		SubMonitor pm = null;
		SubMonitor m = null;
		try {
			pm = SubMonitor.convert(monitor);
			pm.setTaskName("Update Classpath");
			pm.subTask("Updating Bundle Classpath...");
			m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
			desc.apply(m);
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		} finally {
			if (m != null)
				m.done();
			if (pm != null)
				pm.done();
		}
	}

	public IPublisherInfo createPublisherInfo() throws ProvisionException, URISyntaxException {
		PublisherInfo result = new PublisherInfo();

		Map<String, String> props = new HashMap<String, String>();

		props.put("p2.gathering", Boolean.TRUE.toString());
		props.put("p2.product.qualifier", getContributionState().getProject().getVersion().getQualifier());

		IMetadataRepository metadataRepository = new SimpleMetadataRepositoryFactory().create(
				new URI("file:/" + getProperties().getProperty(EXPORT_DESTINATION_PROP)), "Metadata Repository",
				MetadataRepositoryManager.TYPE_SIMPLE_REPOSITORY, props);

		IArtifactRepository artifactRepository = new SimpleArtifactRepositoryFactory().create(
				new URI("file:/" + getProperties().getProperty(EXPORT_DESTINATION_PROP)), "Artifact Repository",
				ArtifactRepositoryManager.TYPE_SIMPLE_REPOSITORY, props);

		result.setMetadataRepository(metadataRepository);
		result.setArtifactRepository(artifactRepository);
		result.setArtifactOptions(IPublisherInfo.A_PUBLISH | IPublisherInfo.A_INDEX);
		return result;
	}

	public IPublisherAction[] createActions(IModel[] models) {
		IPublisherAction[] result = new IPublisherAction[1];
		Set<File> bundleLocations = new HashSet<File>();
		bundleLocations.add(getProject().getLocation().toFile());
		PluginModelManager modelManager = PDECore.getDefault().getModelManager();
		for (IModel model : models)
			if (model instanceof IPluginModelBase) {
				BundleDescription bundleDescription = ((IPluginModelBase) model).getBundleDescription();
				if (bundleDescription != null)
					for (BundleSpecification requiredBundle : bundleDescription.getRequiredBundles()) {
						IPluginModelBase requiredModel = modelManager.findModel(requiredBundle.getName());
						bundleLocations.add(new File(requiredModel.getInstallLocation()));
						writeVersion(model, requiredModel);
					}
			}
		if (getContributionState().isEdit()) {
			bundleLocations.add(new File(getProject().getLocation().toString() + ".edit"));
			for (IModel model : models) {
				if (model instanceof AbstractPluginModelBase && ((AbstractPluginModelBase) model).getBundleDescription()
						.getName().equals("org.eclipse.emf.edit"))
					bundleLocations.add(new File(((AbstractPluginModelBase) model).getInstallLocation()));
				if (model instanceof IPluginModelBase) {
					BundleDescription bundleDescription = ((IPluginModelBase) model).getBundleDescription();
					if (bundleDescription != null)
						for (BundleSpecification requiredBundle : bundleDescription.getRequiredBundles()) {
							IPluginModelBase requiredModel = modelManager.findModel(requiredBundle.getName() + ".edit");
							if (requiredModel instanceof BundlePluginModel)
								if (((BundlePluginModel) requiredModel)
										.getBundleModel() instanceof WorkspaceBundleModel) {
									writeVersion(model, requiredModel);
								}
						}
				}
			}
		}
		if (getContributionState().isEditor()) {
			bundleLocations.add(new File(getProject().getLocation().toString() + ".editor"));
			for (IModel model : models) {
				if (model instanceof AbstractPluginModelBase && ((AbstractPluginModelBase) model).getBundleDescription()
						.getName().equals("org.eclipse.emf.edit.ui"))
					bundleLocations.add(new File(((AbstractPluginModelBase) model).getInstallLocation()));
				if (model instanceof IPluginModelBase) {
					BundleDescription bundleDescription = ((IPluginModelBase) model).getBundleDescription();
					if (bundleDescription != null)
						for (BundleSpecification requiredBundle : bundleDescription.getRequiredBundles()) {
							IPluginModelBase requiredModel = modelManager
									.findModel(requiredBundle.getName() + ".editor");
							if (requiredModel instanceof BundlePluginModel)
								if (((BundlePluginModel) requiredModel)
										.getBundleModel() instanceof WorkspaceBundleModel) {
									writeVersion(model, requiredModel);
								}
						}
				}
			}
		}
		BundlesAction bundlesAction = new BundlesAction(bundleLocations.toArray(new File[bundleLocations.size()]));
		result[0] = bundlesAction;
		return result;
	}

	private void writeVersion(IModel model, IPluginModelBase requiredModel) {
		BundleDescription rDesc = requiredModel.getBundleDescription();
		if (rDesc != null)
			for (IPluginImport i : ((IPluginModelBase) model).getPluginBase().getImports()) {
				BundleDescription desc = i.getPluginModel().getBundleDescription();
				if (desc != null)
					if (desc.getSymbolicName().equals(rDesc.getSymbolicName()))
						try {
							if (i.getPluginModel().isEditable())
								i.setVersion(getContributionState().getRevision().getVersion().toString());
						} catch (CoreException e) {
							ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
						}
			}
	}

	private void cleanup(IProgressMonitor monitor) throws CoreException {
		ResourceUtils.cleanupDir(ResourceUtils.getExportDestination(getContributionState()).toString());
		try {
			@SuppressWarnings("unchecked")
			SCMOperator<Git> operator = (SCMOperator<Git>) getContributionState().getProject().getWorkspace()
					.getSCMRegistry().get(getProject().getName());
			operator.add("."); //$NON-NLS-1$
		} catch (Exception e) {
			throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
		}
		refreshLocal(getProject(), IResource.DEPTH_ONE, monitor);
	}

	private void refreshLocal(IResource resource, int depth, IProgressMonitor monitor) throws CoreException {
		try {
			IProgressMonitor pm = SubMonitor.convert(monitor, 1);
			resource.refreshLocal(depth, pm);
		} catch (OperationCanceledException e) {
		}
	}

	private void updateBuildProperties(IPluginModelBase model) throws CoreException {
		IBuildModel buildModel = PluginRegistry.createBuildModel(model);
		IBuild build = buildModel.getBuild();
		IBuildEntry srcEntry = build.getEntry("source.."); //$NON-NLS-1$
		if (srcEntry == null) {
			srcEntry = buildModel.getFactory().createEntry("source.."); //$NON-NLS-1$
			if (!srcEntry.contains("src/"))
				srcEntry.addToken("src/");
			build.add(srcEntry);
		}
		IBuildEntry outputEntry = build.getEntry("output.."); //$NON-NLS-1$
		if (outputEntry == null) {
			outputEntry = buildModel.getFactory().createEntry("output.."); //$NON-NLS-1$
			if (!outputEntry.contains("bin"))
				outputEntry.addToken("bin");
			build.add(outputEntry);
		}
		if (!srcEntry.contains("src" + IPath.SEPARATOR)) //$NON-NLS-1$
			srcEntry.addToken("src" + IPath.SEPARATOR); //$NON-NLS-1$
		IBuildEntry compilerEntry = build.getEntry("compilerArg"); //$NON-NLS-1$
		if (compilerEntry == null) {
			compilerEntry = buildModel.getFactory().createEntry("compilerArg"); //$NON-NLS-1$
			build.add(compilerEntry);
		}
		if (!compilerEntry.contains("-proc:none")) //$NON-NLS-1$
			compilerEntry.addToken("-proc:none"); //$NON-NLS-1$
		if (buildModel instanceof IEditableModel)
			((IEditableModel) buildModel).save();
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
		if (JavaManipulation.getPreferenceNodeId() == null)
			JavaManipulation.setPreferenceNodeId("org.eclipse.jdt.ui");
		IEclipsePreferences prefs = new ProjectScope(project).getNode(JavaManipulation.getPreferenceNodeId());
		prefs.put("org.eclipse.jdt.ui.importorder", "java;javax;org;com");
		IEclipsePreferences instancePrefs = InstanceScope.INSTANCE.getNode(JavaManipulation.getPreferenceNodeId());
		instancePrefs.put("org.eclipse.jdt.ui.typefilter.enabled", "com.sun.*;sun.org.*;javax.swing.*;java.awt.*");
		try {
			prefs.flush();
			instancePrefs.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		IJavaProject javaProject = updateClassPath(monitor);

		for (IPackageFragment p : javaProject.getPackageFragments())
			if (p.getKind() == IPackageFragmentRoot.K_SOURCE)
				for (ICompilationUnit cu : p.getCompilationUnits()) {
					CompilationUnit astRoot = SharedASTProviderCore.getAST(cu, SharedASTProviderCore.WAIT_YES, monitor);
					OrganizeImportsOperation op = new OrganizeImportsOperation(cu, astRoot, false, true, false, null);
					project.getWorkspace().run(op, javaProject.getSchedulingRule(), 0, monitor);
//					op.run(monitor);
				}
		project.build(IncrementalProjectBuilder.FULL_BUILD, JavaCore.BUILDER_ID, command.getArguments(), monitor);
		for (IMarker m : project.findMarkers(IMarker.MARKER, true, IResource.DEPTH_INFINITE)) {
			System.out.print(m.getAttribute(IMarker.MESSAGE) + " ");
			System.out.print(m.getResource().getName() + " ");
			System.out.println(m.getAttribute(IMarker.LINE_NUMBER));
		}
	}

	private IJavaProject updateClassPath(IProgressMonitor monitor) throws CoreException {
		IJavaProject javaProject = null;
		final SubMonitor pm = SubMonitor.convert(monitor);
		pm.setTaskName("Update Classpath");
		pm.subTask("Setting Classpath...");
		final SubMonitor m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
		try {
			IJavaProject editJavaProject = null;
			IJavaProject editorJavaProject = null;
			try {
				javaProject = JavaCore
						.create(ResourcesPlugin.getWorkspace().getRoot().getProject(getProject().getName()));
				PDECore.getDefault().getModelManager().findEntry(getProject().getName());// findModel(getProject()).;
				JavaCore.getClasspathContainer(PDECore.REQUIRED_PLUGINS_CONTAINER_PATH, javaProject);
//				ITargetPlatformService s = ClassMakerPlugin.getService(ITargetPlatformService.class.getName());
//				ITargetDefinition d = s.getWorkspaceTargetDefinition();
//				d.resolve(monitor);
//				for (TargetBundle b : d.getAllBundles())
//					System.out.println(b.getBundleInfo().getSymbolicName());
//
//				try {
//					java.nio.file.Path p = FileSystems.getDefault().getPath("file:" + getProject().getLocation(),
//							"META-INF", "MANIFEST.MF");
//					BufferedReader r = Files.newBufferedReader(p);
//					String l = r.lines().toString();
//					Files.writeString(p, l);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

				if (getContributionState().isEdit())
					editJavaProject = JavaCore.create(
							ResourcesPlugin.getWorkspace().getRoot().getProject(getProject().getName() + ".edit"));
				if (getContributionState().isEditor())
					editorJavaProject = JavaCore.create(
							ResourcesPlugin.getWorkspace().getRoot().getProject(getProject().getName() + ".editor"));

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
					new Path(IPath.SEPARATOR + getProject().getName() + IPath.SEPARATOR
							+ ResourceUtils.SOURCE_FOLDER_NAME),
					null,
					new Path(IPath.SEPARATOR + getProject().getName() + IPath.SEPARATOR + "bin" + IPath.SEPARATOR));
			entries.removeIf(en -> {
				return en.getPath().isPrefixOf(entry.getPath()) && en.getOutputLocation() == null;
			});
			entries.add(entry);
			if (editJavaProject != null) {
				entry = JavaCore.newSourceEntry(
						new Path(IPath.SEPARATOR + getProject().getName() + ".edit" + IPath.SEPARATOR
								+ ResourceUtils.SOURCE_FOLDER_NAME),
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
								+ ResourceUtils.SOURCE_FOLDER_NAME),
						null, new Path(IPath.SEPARATOR + getProject().getName() + ".editor" + IPath.SEPARATOR + "bin"
								+ IPath.SEPARATOR));
				editorEntries.removeIf(en -> {
					return en.getPath().isPrefixOf(entry.getPath()) && en.getOutputLocation() == null;
				});
				if (!editorEntries.contains(entry))
					editorEntries.add(entry);
			}
			entry = JavaCore.newContainerEntry(new Path(
					"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-21"),
					null, new IClasspathAttribute[] { JavaCore.newClasspathAttribute("module", "true") }, true);
			entries.removeIf(en -> {
				return en.getPath().isPrefixOf(entry.getPath());
			});
			if (!entries.contains(entry))
				entries.add(entry);
			if (editJavaProject != null) {
				entry = JavaCore.newContainerEntry(new Path(
						"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-21"),
						null, new IClasspathAttribute[] { JavaCore.newClasspathAttribute("module", "true") }, true);
				editEntries.removeIf(en -> {
					return en.getPath().isPrefixOf(entry.getPath());
				});
				if (!editEntries.contains(entry))
					editEntries.add(entry);
			}
			if (editorJavaProject != null) {
				entry = JavaCore.newContainerEntry(new Path(
						"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-21"),
						null, new IClasspathAttribute[] { JavaCore.newClasspathAttribute("module", "true") }, true);
				if (!editorEntries.contains(entry))
					editorEntries.add(entry);
			}
			entry = JavaCore.newContainerEntry(PDECore.REQUIRED_PLUGINS_CONTAINER_PATH, null, null, false);
			entries.removeIf(en -> {
				return en.getPath().isPrefixOf(entry.getPath());
			});
			if (!entries.contains(entry))
				entries.add(entry);
			javaProject.open(m);
			JavaCore.getClasspathContainer(PDECore.REQUIRED_PLUGINS_CONTAINER_PATH, javaProject);

			if (editJavaProject != null) {
				entry = JavaCore.newContainerEntry(PDECore.REQUIRED_PLUGINS_CONTAINER_PATH, null, null, false);
				if (!editEntries.contains(entry))
					editEntries.add(entry);
			}
			if (editorJavaProject != null) {
				entry = JavaCore.newContainerEntry(PDECore.REQUIRED_PLUGINS_CONTAINER_PATH, null, null, false);
				if (!editorEntries.contains(entry))
					editorEntries.add(entry);
			}

//			IPluginModelBase model = PluginRegistry.findModel(getProject());
//			JavaCore.setClasspathContainer(PDECore.REQUIRED_PLUGINS_CONTAINER_PATH, new IJavaProject[] { javaProject },
//					new IClasspathContainer[] { new RequiredPluginsClasspathContainer(model) }, m);

			{
				javaProject.setRawClasspath((IClasspathEntry[]) entries.toArray(new IClasspathEntry[entries.size()]),
						m);
//				JavaCore.getClasspathContainer(PDECore.REQUIRED_PLUGINS_CONTAINER_PATH, javaProject);
//				ClasspathContainerState.requestClasspathUpdate(getProject());
//				javaProject.getResolvedClasspath(false);
			}
			if (editJavaProject != null) {
				editJavaProject.setRawClasspath(
						(IClasspathEntry[]) editEntries.toArray(new IClasspathEntry[editEntries.size()]), m);
//				editJavaProject.getResolvedClasspath(false);
			}
			if (editorJavaProject != null) {
				editorJavaProject.setRawClasspath(
						(IClasspathEntry[]) editorEntries.toArray(new IClasspathEntry[editorEntries.size()]), m);
//				editorJavaProject.getResolvedClasspath(false);
			}
		} catch (JavaModelException mex) {
			throw new CoreException(ClassMakerPlugin.createErrorStatus(mex));
		} catch (OperationCanceledException ocex) {
			monitor.setCanceled(true);
		} finally {
			m.done();
			pm.done();
		}
		return javaProject;
	}

	@Override
	public Stage getDirtyStage() {
		return Stage.INSTALLED;
	}
}