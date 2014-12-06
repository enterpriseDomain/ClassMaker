package org.classupplier.export;

import java.util.ArrayList;
import java.util.List;

import org.classupplier.State;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.install.Installer;
import org.classupplier.install.OSGiInstaller;
import org.classupplier.load.BundleEPackageLoader;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.exports.FeatureExportInfo;
import org.eclipse.pde.internal.core.exports.PluginExportOperation;

@SuppressWarnings("restriction")
public class PDEExporter extends AbstractExporter {

	private static final String PDE_PLUGIN_NATURE = "org.eclipse.pde.PluginNature";

	@Override
	public IStatus export(final IProgressMonitor monitor) throws CoreException {
		if (!getProject().hasNature(PDE_PLUGIN_NATURE)) {
			IProjectDescription description = getProject().getDescription();
			description = ResourceUtil.addProjectNature(description,
					PDE_PLUGIN_NATURE);
			getProject().setDescription(description, monitor);
		}
		final FeatureExportInfo info = new FeatureExportInfo();
		info.destinationDirectory = getExportDestination().toString();
		info.exportSource = false;
		info.exportSourceBundle = true;
		info.toDirectory = true;
		info.useJarFormat = true;
		info.exportMetadata = true;
		State state = ClassSupplierOSGi.getClassSupplier().getWorkspace()
				.findContribution(getProject().getName()).getState();
		Version version = state.getVersion();
		if (version.isOSGiCompatible() && !version.equals(Version.emptyVersion))
			info.qualifier = (String) version.getSegment(version
					.getSegmentCount() - 1);
		PluginModelManager modelManager = PDECore.getDefault()
				.getModelManager();
		modelManager.bundleRootChanged(getProject());
		List<IPluginModelBase> models = new ArrayList<IPluginModelBase>();
		for (IPluginModelBase model : modelManager.getActiveModels())
			if (model.getBundleDescription().getName()
					.equals(getProject().getName()))
				models.add(model);
		info.items = models.toArray();
		PluginExportOperation op = new PluginExportOperation(info,
				"PDE Plug-in Export");
		op.addJobChangeListener(new JobChangeAdapter() {

			@Override
			public void done(IJobChangeEvent event) {
				if (event.getResult().isOK()) {
					Installer installJob = new OSGiInstaller(); // TODO
					// new
					// P2Installer(info);
					installJob.setProject(getProject());
					installJob.setNextJob(new BundleEPackageLoader());
					installJob.schedule();

				} else if (event.getResult().getException() != null)
					event.getResult().getException().printStackTrace();

				// if (ClassSupplierOSGi.getInstance() != null)
				// ClassSupplierOSGi.getInstance().getLog()
				// .log(event.getResult());
			}

		});
		op.schedule();
		return Status.OK_STATUS;
	}
}
