package org.classupplier.builders;

import java.util.Map;

import org.classupplier.Artifact;
import org.classupplier.State;
import org.classupplier.export.Exporter;
import org.classupplier.export.MavenExporter;
import org.classupplier.impl.OSGi;
import org.classupplier.impl.ResourceHelper;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.Version;

public class ExportingBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = OSGi.PLUGIN_ID + '.'
			+ "exportBuilder";

	protected Exporter exporter = new MavenExporter();

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		exporter.setDestination(ResourceHelper.getDefaultDestination());
		Artifact artifact = null;
		if (exporter.getVersion() == null) {
			artifact = OSGi.getClasSupplier().getWorkspace()
					.getArtifact(getProject().getName());
			Version version = artifact.getVersion();
			if (artifact.getState().equals(State.CREATED)
					|| !artifact.getState().equals(State.PROCESSING))
				return null;
			if (artifact.getPrototypeEPackage().getEClassifiers().isEmpty())
				return null;
			exporter.setVersion(version.toString());
		}
		if (artifact != null
				&& (artifact.getState().equals(State.CREATED) || !artifact
						.getState().equals(State.PROCESSING)))
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
