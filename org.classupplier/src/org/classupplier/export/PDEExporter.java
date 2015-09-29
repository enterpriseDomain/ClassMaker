package org.classupplier.export;

import java.util.ArrayList;
import java.util.List;

import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.core.ClassSupplierOSGi;
import org.classupplier.core.DelegatingJob;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.exports.FeatureExportInfo;
import org.eclipse.pde.internal.core.exports.PluginExportOperation;
import org.osgi.framework.Version;

@SuppressWarnings("restriction")
public class PDEExporter extends AbstractExporter {

	private static final String PDE_PLUGIN_NATURE = "org.eclipse.pde.PluginNature";

	@Override
	public IStatus export(final IProgressMonitor monitor) throws CoreException {
		if (!getProject().hasNature(PDE_PLUGIN_NATURE)) {
			IProjectDescription description = getProject().getDescription();
			description.setNatureIds(ResourceUtil.addProjectNature(description.getNatureIds(), PDE_PLUGIN_NATURE));
			getProject().setDescription(description, monitor);
		}

		final FeatureExportInfo info = new FeatureExportInfo();
		info.destinationDirectory = getExportDestination().toString();
		info.exportSource = false;
		info.toDirectory = true;
		info.useJarFormat = true;
		info.exportMetadata = true;
		State state = getContribution();
		Version version = state.getVersion();
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

		PluginExportOperation op = new PluginExportOperation(info, "PDE Plug-in Export");
		DelegatingJob delegate = new DelegatingJob(op);
		delegate.setNextJob(getNextJob());
		setNextJob(delegate);
		getContribution().setStage(Phase.EXPORTED);
		return Status.OK_STATUS;
	}

	@Override
	public void checkStage() throws CoreException {
		if (getContribution().getStage().getValue() < Phase.GENERATED_VALUE)
			throw new CoreException(ClassSupplierOSGi.createWarningStatus("Contribution is not generated."));
	}
}
