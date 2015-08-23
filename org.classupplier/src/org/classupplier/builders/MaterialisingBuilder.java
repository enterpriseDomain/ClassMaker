package org.classupplier.builders;

import java.util.Date;
import java.util.Map;

import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.codegen.EcoreGenerator;
import org.classupplier.codegen.Generator;
import org.classupplier.export.Exporter;
import org.classupplier.export.PDEExporter;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.install.Installer;
import org.classupplier.install.OSGiInstaller;
import org.classupplier.load.BundleEPackageLoader;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.osgi.framework.Version;

public class MaterialisingBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = ClassSupplierOSGi.PLUGIN_ID + '.' + "materializer";

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
		state.setVersion(org.osgi.framework.Version.parseVersion("1.0.0." + ResourceUtil.formatQualifier(new Date())));
		if (exporter.getVersion() == null) {
			Version version = state.getVersion();
			exporter.setVersion(version);
		}

		generator.setResourceSet(ClassSupplierOSGi.getClassSupplier().getWorkspace().getResourceSet());
		SupplementaryJob generatorJob = ((SupplementaryJob) generator.getAdapter(SupplementaryJob.class));
		generatorJob.setProject(getProject());
		generatorJob.setProgressGroup(monitor, 1);
		generatorJob.setNextJob(exporterJob);

		Installer installJob = new OSGiInstaller();
		exporterJob.setNextJob(installJob);
		SupplementaryJob lastJob = new BundleEPackageLoader();
		lastJob.addListener();
		lastJob.addJobChangeListener(new JobChangeAdapter() {

			@Override
			public void done(IJobChangeEvent event) {
				state.fireJobsCompleted();
			}

		});
		installJob.setNextJob(lastJob);

		generatorJob.schedule();
		try {
			generatorJob.join();
			exporterJob.join();
			installJob.join();
			lastJob.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
