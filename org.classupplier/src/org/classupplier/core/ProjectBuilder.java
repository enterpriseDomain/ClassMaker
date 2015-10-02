package org.classupplier.core;

import java.util.Map;

import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.jobs.SupplementaryJob;
import org.classupplier.jobs.codegen.EcoreGenerator;
import org.classupplier.jobs.codegen.Generator;
import org.classupplier.jobs.export.Exporter;
import org.classupplier.jobs.export.PDEExporter;
import org.classupplier.jobs.install.OSGiInstaller;
import org.classupplier.jobs.load.OSGiEPackageLoader;
import org.classupplier.jobs.model.ModelResourceManager;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.osgi.framework.Version;

public class ProjectBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = ClassSupplierOSGi.PLUGIN_ID + '.' + "builder"; //$NON-NLS-1$

	protected Generator generator = new EcoreGenerator();

	protected Exporter exporter = new PDEExporter();

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;

		SupplementaryJob exporterJob = (SupplementaryJob) exporter.getAdapter(SupplementaryJob.class);
		exporterJob.setProject(getProject());
		final State state = exporterJob.getContribution();
		if (state != null
				&& (state.getDynamicEPackage().getEClassifiers().isEmpty() || (state.getStage().equals(Phase.DEFINED))))
			return null;
		exporter.setExportDestination(ResourceUtil.getExportDestination(getProject()));
		if (exporter.getVersion() == null) {
			Version version = state.getVersion();
			exporter.setVersion(version);
		}

		generator.setResourceSet(ClassSupplierOSGi.getClassSupplier().getWorkspace().getResourceSet());
		SupplementaryJob generatorJob = ((SupplementaryJob) generator.getAdapter(SupplementaryJob.class));
		generatorJob.setProject(getProject());
		generatorJob.setProgressGroup(monitor, 1);
		generatorJob.setNextJob(exporterJob);

		ModelResourceManager resourceJob = new ModelResourceManager();
		resourceJob.setProject(getProject());
		resourceJob.setProgressGroup(monitor, 1);
		resourceJob.setNextJob(generatorJob);

		SupplementaryJob installJob = new OSGiInstaller();
		exporterJob.setNextJob(installJob);
		SupplementaryJob loadJob = new OSGiEPackageLoader();
		loadJob.addListener();
		loadJob.addJobChangeListener(new JobChangeAdapter() {

			@Override
			public void done(IJobChangeEvent event) {
				state.fireJobsCompleted();
			}

		});
		installJob.setNextJob(loadJob);

		resourceJob.schedule();
		try {
			resourceJob.join();
			generatorJob.join();
			exporterJob.join();
			installJob.join();
			loadJob.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new IProject[] { getProject() };
	}

}
