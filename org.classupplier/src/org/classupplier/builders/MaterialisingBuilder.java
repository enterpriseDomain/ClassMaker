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
import org.classupplier.util.ClassSupplierUtil;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.p2.metadata.Version;

public class MaterialisingBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = ClassSupplierOSGi.PLUGIN_ID + '.'
			+ "materializer";

	protected Generator generator = new EcoreGenerator();

	protected Exporter exporter = new PDEExporter();

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		State state = ClassSupplierUtil.getState(getProject());
		SupplementaryJob exporterJob = (SupplementaryJob) exporter
				.getAdapter(SupplementaryJob.class);
		if (state != null
				&& (state.getDynamicEPackage().getEClassifiers().isEmpty() || (state
						.getStage().equals(Phase.DEFINED) || !state.getStage()
						.equals(Phase.PROCESSING))))
			return null;
		exporter.setExportDestination(ResourceUtil
				.getExportDestination(getProject()));
		state.setVersion(Version.createOSGi(1, 0, 0,
				ResourceUtil.formatQualifier(new Date())));
		if (exporter.getVersion() == null) {
			Version version = state.getVersion();
			exporter.setVersion(version);
		}

		generator.setResourceSet(ClassSupplierOSGi.getClassSupplier()
				.getWorkspace().getResourceSet());
		SupplementaryJob generatorJob = ((SupplementaryJob) generator
				.getAdapter(SupplementaryJob.class));
		generatorJob.setProject(getProject());
		generatorJob.setProgressGroup(monitor, 1);
		generatorJob.setNextJob(exporterJob);
		generatorJob.schedule();
		SupplementaryJob.joinManualBuild(monitor);
		return null;
	}
}
