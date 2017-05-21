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
package org.enterprisedomain.classmaker.jobs.export;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.pde.core.IEditableModel;
import org.eclipse.pde.core.build.IBuild;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.exports.FeatureExportInfo;
import org.eclipse.pde.internal.core.exports.PluginExportOperation;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.jobs.DelegatingJob;
import org.enterprisedomain.classmaker.util.GitUtil;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Version;

@SuppressWarnings("restriction")
public class PDEPluginExporter extends AbstractExporter {

	public PDEPluginExporter(long runId) {
		super(runId);
	}

	@Override
	public IStatus export(final IProgressMonitor monitor) throws CoreException {
		// if (!getProject().hasNature(ClassMakerOSGi.PDE_PLUGIN_NATURE)) {
		// IProjectDescription description = getProject().getDescription();
		// description.setNatureIds(
		// ResourceUtils.addProjectNature(description.getNatureIds(),
		// ClassMakerOSGi.PDE_PLUGIN_NATURE));
		// getProject().setDescription(description, monitor);
		// }

		if (monitor.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		cleanup();
		getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);

		State contribution = getContributionState();
		Version version = contribution.getVersion();
		final FeatureExportInfo info = new FeatureExportInfo();
		info.destinationDirectory = getExportDestination().toString();
		info.exportSource = true;
		info.exportSourceBundle = true;
		info.toDirectory = true;
		info.useJarFormat = true;
		info.exportMetadata = true;
		info.useWorkspaceCompiledClasses = false;
		info.targets = new String[][] { { "os", TargetPlatform.getOS() }, { "ws", TargetPlatform.getWS() },
				{ "arch", TargetPlatform.getOSArch() }, { "nl", TargetPlatform.getNL() } };
		if (!version.equals(Version.emptyVersion))
			info.qualifier = (String) version.getQualifier();
		PluginModelManager modelManager = PDECore.getDefault().getModelManager();
		modelManager.bundleRootChanged(getProject());
		List<IPluginModelBase> models = new ArrayList<IPluginModelBase>();
		IPluginModelBase model = modelManager.findModel(getProject());
		if (model != null) {
			models.add(model);
			updateBuildProperties(model);
			info.items = models.toArray();
		}

		PluginExportOperation op = new PluginExportOperation(info, Messages.JobNamePDEExport);
		DelegatingJob delegate = new DelegatingJob(op, getRunId());
		delegate.setNextJob(getNextJob());
		delegate.setResultStage(getResultStage());
		delegate.setDirtyStage(getDirtyStage());
		setNextJob(delegate);
		contribution.setPhase(getResultStage());
		monitor.worked(1);
		return Status.OK_STATUS;
	}

	private void cleanup() throws CoreException {
		ResourceUtils.cleanupDir(getProject(), ResourceUtils.getTargetFolderName());
		try {
			GitUtil.add(getProject().getName(), ".");
		} catch (GitAPIException e) {
			throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
		}
	}

	private void updateBuildProperties(IPluginModelBase model) throws CoreException {
		IBuildModel buildModel = PluginRegistry.createBuildModel(model);
		IBuild build = buildModel.getBuild();
		IBuildEntry entry = build.getEntry("compilerArg"); //$NON-NLS-1$
		if (entry == null) {
			entry = buildModel.getFactory().createEntry("compilerArg"); //$NON-NLS-1$
			build.add(entry);
		}
		if (!entry.contains("-proc:none")) //$NON-NLS-1$
			entry.addToken("-proc:none"); //$NON-NLS-1$
		if (buildModel instanceof IEditableModel)
			((IEditableModel) buildModel).save();
	}

	@Override
	public Stage getPrerequisiteStage() {
		return Stage.GENERATED;
	}

	@Override
	public Stage getDirtyStage() {
		return Stage.INSTALLED;
	}
}
