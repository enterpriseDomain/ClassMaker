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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.jgit.api.Git;
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
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.jobs.DelegatingJob;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Version;

@SuppressWarnings("restriction")
public class PDEPluginExporter extends AbstractExporter {

	private PluginExportOperation op = null;

	private Object lock = new Object();

	public PDEPluginExporter(int depth, long stateTimestamp) {
		super(depth, stateTimestamp);
	}

	@Override
	public IStatus work(final IProgressMonitor monitor) throws CoreException {
		synchronized (lock) {
			State state = getContributionState();
			Version version = state.getProject().getVersion();
			final FeatureExportInfo info = new FeatureExportInfo();
			info.destinationDirectory = getProperties().getProperty(EXPORT_DESTINATION_PROP);
			info.toDirectory = true;
			info.useJarFormat = true;
			info.exportMetadata = true;
			info.useWorkspaceCompiledClasses = false;
			info.targets = new String[][] { { "os", TargetPlatform.getOS() }, { "ws", TargetPlatform.getWS() }, //$NON-NLS-1$ //$NON-NLS-2$
					{ "arch", TargetPlatform.getOSArch() }, { "nl", TargetPlatform.getNL() } }; //$NON-NLS-1$ //$NON-NLS-2$
			if (!version.equals(Version.emptyVersion))
				info.qualifier = (String) version.getQualifier();
			PluginModelManager modelManager = PDECore.getDefault().getModelManager();
			modelManager.bundleRootChanged(getProject());
			List<IPluginModelBase> models = new ArrayList<IPluginModelBase>();
			IPluginModelBase model = modelManager.findModel(getProject());
			if (model != null) {
				models.add(model);
				updateBuildProperties(model, true);
				if (op != null)
					op.deleteBuildFiles(model);
				if (new Version(model.getPluginBase().getVersion()).compareTo(state.getRevision().getVersion()) == 0)
					cleanup(monitor);
			}
			if (getContributionState().isEdit()) {
				IPluginModelBase edit = modelManager.findModel(getProject().getName() + ".edit"); //$NON-NLS-1$
				if (edit != null) {
					models.add(edit);
					updateBuildProperties(edit, false);
					IPluginModelBase emfEdit = modelManager.findModel("org.eclipse.emf.edit"); //$NON-NLS-1$
					if (emfEdit != null)
						models.add(emfEdit);
				}
				if (op != null)
					op.deleteBuildFiles(edit);
			}
			if (getContributionState().isEditor()) {
				IPluginModelBase editor = modelManager.findModel(getProject().getName() + ".editor"); //$NON-NLS-1$
				if (editor != null) {
					models.add(editor);
					updateBuildProperties(editor, false);
					IPluginModelBase emfEditor = modelManager.findModel("org.eclipse.emf.edit.ui"); //$NON-NLS-1$
					if (emfEditor != null)
						models.add(emfEditor);
				}
				if (op != null)
					op.deleteBuildFiles(editor);
			}
			if (!models.isEmpty())
				info.items = models.toArray();
			joinJob(Messages.JobNamePDEExport);
			final SubMonitor pm = SubMonitor.convert(monitor);
			pm.setTaskName(Messages.TaskNamePluginExport);
			pm.subTask(Messages.SubTaskNamePluginExport);
			final SubMonitor m = pm.newChild(9, SubMonitor.SUPPRESS_ISCANCELED);

			op = new PluginExportOperation(info, Messages.JobNamePDEExport);
			DelegatingJob delegate = new DelegatingJob(op, getDepth(), getStateTimestamp());
			delegate.setProgressProvider(new ProgressProvider() {

				@Override
				public IProgressMonitor createMonitor(Job job) {
					return m;
				}
			});
			delegate.setContributionState(getContributionState());
			delegate.setNextJob(getNextJob());
			delegate.setResultStage(getResultStage());
			delegate.setDirtyStage(getDirtyStage());
			setNextJob(delegate);
			delegate.addJobChangeListener(new JobChangeAdapter() {

				@Override
				public void done(IJobChangeEvent event) {
					if (event.getJob().getName().equals(Messages.JobNamePDEExport)) {
						m.done();
						pm.done();
					}
				}

			});
			return ClassMakerPlugin.createInfoStatus(Messages.PDEExportScheduled);
		}
	}

	@Override
	public boolean hasErrors(IStatus status) {
		if (op != null)
			return op.hasAntErrors();
		return super.hasErrors(status);
	}

	private void cleanup(IProgressMonitor monitor) throws CoreException {
		ResourceUtils.cleanupDir(getProject(), ResourceUtils.getTargetFolderName());
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

	private void updateBuildProperties(IPluginModelBase model, boolean src) throws CoreException {
		IBuildModel buildModel = PluginRegistry.createBuildModel(model);
		IBuild build = buildModel.getBuild();
		if (src) {
			IBuildEntry entry = build.getEntry("source.."); //$NON-NLS-1$
			if (entry == null) {
				entry = buildModel.getFactory().createEntry("source.."); //$NON-NLS-1$
				build.add(entry);
			}
			if (!entry.contains("src" + IPath.SEPARATOR)) //$NON-NLS-1$
				entry.addToken("src" + IPath.SEPARATOR); //$NON-NLS-1$
		}
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
	public Stage getDirtyStage() {
		return Stage.INSTALLED;
	}
}
