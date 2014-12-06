package org.classupplier.builders;

import org.classupplier.impl.ClassSupplierOSGi;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;

public abstract class SupplementaryJob extends WorkspaceJob {

	private SupplementaryJob nextJob;

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (Job.class.equals(adapter))
			return (Job) this;
		if (SupplementaryJob.class.equals(adapter))
			return (SupplementaryJob) this;

		return super.getAdapter(adapter);
	}

	private IProject project;
	private IJobChangeListener listener = new JobChangeAdapter() {

		@Override
		public void done(IJobChangeEvent event) {
			if (!event.getResult().isOK() || getNextJob() == null)
				return;
			getNextJob().setProject(getProject());
			getNextJob().schedule();
		}

	};

	public SupplementaryJob(String name) {
		super(name);
		setUser(true);
		setPriority(Job.SHORT);
		setRule(ClassSupplierOSGi.getClassSupplier().getWorkspace());
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		return work(monitor);
	}

	public abstract IStatus work(IProgressMonitor monitor) throws CoreException;

	@Override
	public boolean belongsTo(Object family) {
		return super.belongsTo(family)
				|| family == ResourcesPlugin.FAMILY_MANUAL_BUILD;
	}

	public static IStatus joinManualBuild(IProgressMonitor monitor) {
		try {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD,
					monitor);
		} catch (OperationCanceledException e) {
			return Status.CANCEL_STATUS;
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public SupplementaryJob getNextJob() {
		return nextJob;
	}

	public void setNextJob(SupplementaryJob nextJob) {
		this.nextJob = nextJob;
		if (this.nextJob != null)
			addJobChangeListener(listener);
		else
			removeJobChangeListener(listener);
	}

}
