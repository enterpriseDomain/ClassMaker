package org.classmaker.builders;

import java.util.Map;

import org.classmaker.ClassMaker;
import org.classmaker.Version;
import org.classmaker.export.Exporter;
import org.classmaker.export.PDEExporter;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;

public class ExportingBuilder extends AbstractBuilder {

	public static final String BUILDER_ID = ClassMaker.PLUGIN_ID + '.'
			+ "exportBuilder";

	private boolean isWaiting;

	private IJobChangeListener listener = new JobChangeAdapter() {

		@Override
		public void done(IJobChangeEvent event) {
			Job job = event.getJob();
			String jobName = "PDE Export";
			if (job.getName().equals(jobName))
				isWaiting = false;
		}

	};

	protected void beginWait() {
		Job.getJobManager().addJobChangeListener(listener);
		isWaiting = true;
	}

	protected void doWait() {
		while (isWaiting)
			Thread.yield();
		Job.getJobManager().removeJobChangeListener(listener);
	}

	protected Exporter exporter = new PDEExporter();

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		IProgressMonitor oldMonitor = ClassMaker.getInstance().monitor();
		ClassMaker.getInstance().setMonitor(monitor);
		exporter.setDestination(ClassMaker.getInstance().getStateLocation());
		if (exporter.getQualifier() == null) {
			Version version = names().getVersion(getProject().getName());
			exporter.setQualifier(version.getQualifier());
		}
		beginWait();
		exporter.export(getProject());
		doWait();
		try {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD,
					monitor);
		} catch (OperationCanceledException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		ClassMaker.getInstance().setMonitor(oldMonitor);
		return null;
	}
}
