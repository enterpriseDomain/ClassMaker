package org.classupplier.jobs.export;

import java.util.ArrayList;
import java.util.List;

import org.classupplier.Messages;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.core.ClassSupplierOSGi;
import org.classupplier.jobs.DelegatingJob;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.pde.core.IEditableModel;
import org.eclipse.pde.core.build.IBuild;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.core.target.LoadTargetDefinitionJob;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.exports.FeatureExportInfo;
import org.eclipse.pde.internal.core.exports.PluginExportOperation;
import org.osgi.framework.Version;

@SuppressWarnings("restriction")
public class PDEExporter extends AbstractExporter {

	@Override
	public IStatus export(final IProgressMonitor monitor) throws CoreException {
		if (!getProject().hasNature(ClassSupplierOSGi.PDE_PLUGIN_NATURE)) {
			IProjectDescription description = getProject().getDescription();
			description.setNatureIds(
					ResourceUtil.addProjectNature(description.getNatureIds(), ClassSupplierOSGi.PDE_PLUGIN_NATURE));
			getProject().setDescription(description, monitor);
		}

		State contribution = getContribution();
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
		DelegatingJob delegate = new DelegatingJob(op);
		delegate.setNextJob(getNextJob());
		setNextJob(delegate);
		contribution.setStage(Phase.EXPORTED);
		monitor.worked(1);
		return Status.OK_STATUS;
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
	public Phase requiredStage() {
		return Phase.GENERATED;
	}
}
