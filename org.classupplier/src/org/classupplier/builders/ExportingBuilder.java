package org.classupplier.builders;

import java.util.Map;

import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.export.Exporter;
import org.classupplier.export.MavenExporter;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.Version;

public class ExportingBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = ClassSupplierOSGi.PLUGIN_ID + '.'
			+ "exportBuilder";

	protected Exporter exporter = new MavenExporter();

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		exporter.setExportDestination(ResourceUtil.getDefaultExportDestination());
		State state = null;
		if (exporter.getVersion() == null) {
			state = ClassSupplierOSGi.getClassSupplier().getWorkspace()
					.getArtifact(getProject().getName()).getState();
			Version version = state.getVersion();
			if (state.getStage().equals(Phase.NEW)
					|| !state.getStage().equals(Phase.PROCESSING))
				return null;
			if (state.getPrototypeEPackage().getEClassifiers().isEmpty())
				return null;
			exporter.setVersion(version);
		}
		if (state != null
				&& (state.getStage().equals(Phase.NEW) || !state.getStage()
						.equals(Phase.PROCESSING)))
			return null;

		exporter.export(getProject(), monitor);
		try {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD,
					monitor);
		} catch (OperationCanceledException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		return null;
	}
}
