package org.classupplier.export;

import java.util.ArrayList;
import java.util.List;

import org.classupplier.State;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.load.BundleEPackageLoader;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.exports.FeatureExportInfo;
import org.eclipse.pde.internal.core.exports.PluginExportOperation;

@SuppressWarnings("restriction")
public class PDEExporter extends AbstractExporter {

	private static final String PDE_PLUGIN_NATURE = "org.eclipse.pde.PluginNature";

	@Override
	public void export(final IProject project, final IProgressMonitor monitor)
			throws CoreException {
		if (!project.hasNature(PDE_PLUGIN_NATURE)) {
			IProjectDescription description = project.getDescription();
			description = ResourceUtil.addProjectNature(description,
					PDE_PLUGIN_NATURE);
			project.setDescription(description, monitor);
		}
		FeatureExportInfo info = new FeatureExportInfo();
		info.destinationDirectory = getExportDestination().toString();
		info.toDirectory = true;
		info.useJarFormat = true;
		State state = ClassSupplierOSGi.getClassSupplier().getWorkspace()
				.getArtifact(project.getName()).getState();
		info.qualifier = state.getVersion().getQualifier();
		PluginModelManager modelManager = PDECore.getDefault()
				.getModelManager();
		modelManager.bundleRootChanged(project);
		List<IPluginModelBase> models = new ArrayList<IPluginModelBase>();
		for (IPluginModelBase model : modelManager.getActiveModels())
			if (model.getBundleDescription().getName()
					.equals(project.getName()))
				models.add(model);
		info.items = models.toArray();
		PluginExportOperation op = new PluginExportOperation(info,
				"Plug-in Export");
		IProgressMonitor progressGroup = Job.getJobManager()
				.createProgressGroup();
		op.setProgressGroup(progressGroup, 1);
		op.setUser(true);
		op.setPriority(Job.SHORT);
		op.setRule(ResourcesPlugin.getWorkspace().getRoot());
		op.addJobChangeListener(new JobChangeAdapter() {

			@Override
			public void done(IJobChangeEvent event) {
				if (event.getResult().isOK()) {
					BundleEPackageLoader loaderJob = new BundleEPackageLoader(
							project);
					loaderJob.setEnforceReplace(!loaderJob.bundleExists());
					loaderJob.setPriority(Job.SHORT);
					loaderJob.setRule(ClassSupplierOSGi.getClassSupplier()
							.getWorkspace());
					loaderJob.schedule();
					loaderJob.addJobChangeListener(new JobChangeAdapter() {

						@Override
						public void done(IJobChangeEvent event) {
							if (ClassSupplierOSGi.getInstance() != null)
								ClassSupplierOSGi.getInstance().getLog()
										.log(event.getResult());
						}

					});
				} else
					ClassSupplierOSGi.getInstance().getLog()
							.log(event.getResult());
			}

		});
		op.schedule();
	}
}
