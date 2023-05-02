/**
 * Copyright 2012-2021 Kyrill Zotkin
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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.p2.artifact.repository.ArtifactRepositoryManager;
import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactRepositoryFactory;
import org.eclipse.equinox.internal.p2.metadata.repository.MetadataRepositoryManager;
import org.eclipse.equinox.internal.p2.metadata.repository.SimpleMetadataRepositoryFactory;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.eclipse.BundlesAction;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.jgit.api.Git;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.pde.core.IEditableModel;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.IModelProviderEvent;
import org.eclipse.pde.core.build.IBuild;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.core.plugin.IPluginImport;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.ModelProviderEvent;
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
			IPluginModelBase model = modelManager.findModel(getProject());
			if (model != null) {
				models.add(model);
				for (BundleSpecification required : model.getBundleDescription().getRequiredBundles()) {
					IPluginModelBase requiredModel = modelManager.findModel(required.getName());
					if (requiredModel instanceof BundlePluginModel)
						if (((BundlePluginModel) requiredModel).getBundleModel() instanceof WorkspaceBundleModel)
							models.add(requiredModel);
				}
				updateBuildProperties(model);
			}
			if (getContributionState().isEdit()) {
				IPluginModelBase edit = modelManager.findModel(getProject().getName() + ".edit"); //$NON-NLS-1$
				if (edit != null) {
					models.add(edit);
					updateBuildProperties(edit);
					IPluginModelBase emfEdit = modelManager.findModel("org.eclipse.emf.edit"); //$NON-NLS-1$
					if (emfEdit != null)
						models.add(emfEdit);
				}
			}
			if (getContributionState().isEditor()) {
				IPluginModelBase editor = modelManager.findModel(getProject().getName() + ".editor"); //$NON-NLS-1$
				if (editor != null) {
					models.add(editor);
					updateBuildProperties(editor);
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
				modelManager.modelsChanged(
						new ModelProviderEvent(getProject(), IModelProviderEvent.MODELS_ADDED, ma, null, null));
			}

			final SubMonitor pm = SubMonitor.convert(monitor);
			pm.setTaskName(Messages.TaskNamePluginExport);
			pm.subTask(Messages.SubTaskNamePluginExport);
			final SubMonitor m = pm.newChild(9, SubMonitor.SUPPRESS_ISCANCELED);

			Publisher publisher = new Publisher(info);
			return publisher.publish(actions, m);
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
						if (requiredModel instanceof BundlePluginModel)
							if (((BundlePluginModel) requiredModel).getBundleModel() instanceof WorkspaceBundleModel) {
								bundleLocations.add(new File(requiredModel.getInstallLocation()));
								writeVersion(model, requiredModel);
							}
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
		for (IPluginImport i : ((IPluginModelBase) model).getPluginBase().getImports()) {
			BundleDescription desc = i.getPluginModel().getBundleDescription();
			BundleDescription rDesc = requiredModel.getBundleDescription();
			if (desc != null && rDesc != null)
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
			build.add(srcEntry);
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

	@Override
	public Stage getDirtyStage() {
		return Stage.INSTALLED;
	}
}