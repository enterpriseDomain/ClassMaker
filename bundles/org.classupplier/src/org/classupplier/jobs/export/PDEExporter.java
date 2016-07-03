package org.classupplier.jobs.export;

import java.util.ArrayList;
import java.util.List;

import org.classupplier.Messages;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.jobs.DelegatingJob;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.exports.FeatureExportInfo;
import org.eclipse.pde.internal.core.exports.PluginExportOperation;
import org.osgi.framework.Version;

@SuppressWarnings("restriction")
public class PDEExporter extends AbstractExporter {

	private static final String PDE_PLUGIN_NATURE = "org.eclipse.pde.PluginNature"; //$NON-NLS-1$

	@Override
	public IStatus export(final IProgressMonitor monitor) throws CoreException {
		if (!getProject().hasNature(PDE_PLUGIN_NATURE)) {
			IProjectDescription description = getProject().getDescription();
			description.setNatureIds(ResourceUtil.addProjectNature(description.getNatureIds(), PDE_PLUGIN_NATURE));
			getProject().setDescription(description, monitor);
		}

		State contribution = getContribution();
		Version version = contribution.getVersion();
		final FeatureExportInfo info = new FeatureExportInfo();
		info.destinationDirectory = getExportDestination().toString();
		info.exportSource = false;
		info.toDirectory = true;
		info.useJarFormat = true;
		info.exportMetadata = true;
		info.useWorkspaceCompiledClasses = false;
		if (!version.equals(Version.emptyVersion))
			info.qualifier = (String) version.getQualifier();

		PluginModelManager modelManager = PDECore.getDefault().getModelManager();
		modelManager.bundleRootChanged(getProject());
		List<IPluginModelBase> models = new ArrayList<IPluginModelBase>();
		IPluginModelBase model = modelManager.findModel(getProject());
		if (model != null) {
			models.add(model);
			info.items = models.toArray();
		}

		PluginExportOperation op = new PluginExportOperation(info, Messages.JobNamePDEExport);
		DelegatingJob delegate = new DelegatingJob(op);
		delegate.setNextJob(getNextJob());
		setNextJob(delegate);
		contribution.setStage(Phase.EXPORTED);
		monitor.worked(1);
		return Status.OK_STATUS;
	}

	@Override
	public Phase requiredStage() {
		return Phase.GENERATED;
	}
}
