package org.classupplier.jobs;

import org.classupplier.Messages;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.Workspace;
import org.classupplier.core.ClassSupplierOSGi;
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
import org.eclipse.core.runtime.jobs.JobGroup;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;

public abstract class ClassSupplierJob extends WorkspaceJob {

	private ClassSupplierJob nextJob;

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (Job.class.equals(adapter))
			return (Job) this;
		if (ClassSupplierJob.class.equals(adapter))
			return (ClassSupplierJob) this;

		return super.getAdapter(adapter);
	}

	private IProject project;

	private IJobChangeListener listener = new JobChangeAdapter() {

		@Override
		public void done(IJobChangeEvent event) {
			Status result = new Status(event.getResult().getSeverity(), ClassSupplierOSGi.PLUGIN_ID,
					event.getJob().getName() + ": " + event.getResult().getMessage()); //$NON-NLS-1$
			ClassSupplierOSGi.getInstance().getLog().log(result);
			if (getNextJob() == null) {
				return;
			}
			getNextJob().setProject(getProject());
			getNextJob().schedule();
			try {
				getNextJob().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	};

	private ResourceSet resourceSet;

	public ClassSupplierJob(String name) {
		super(name);
		setUser(true);
		setPriority(Job.SHORT);
		setRule(getWorkspace());
		setJobGroup(new JobGroup("Class Supply", 0, 1)); //$NON-NLS-1$
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		checkStage();
		return work(monitor);
	}

	public abstract IStatus work(IProgressMonitor monitor) throws CoreException;

	@Override
	public boolean belongsTo(Object family) {
		return super.belongsTo(family) || family == ResourcesPlugin.FAMILY_MANUAL_BUILD;
	}

	public static IStatus joinManualBuild(IProgressMonitor monitor) {
		try {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, monitor);
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

	public ClassSupplierJob getNextJob() {
		return nextJob;
	}

	public void setNextJob(ClassSupplierJob nextJob) {
		this.nextJob = nextJob;
		if (this.nextJob != null)
			addListener();
		else
			removeListener();
	}

	public void removeListener() {
		removeJobChangeListener(listener);
	}

	public void addListener() {
		addJobChangeListener(listener);
	}

	public State getContribution() {
		return getWorkspace().getContribution(getProject().getName()).getState();
	}

	protected Workspace getWorkspace() {
		return ClassSupplierOSGi.getClassSupplier().getWorkspace();
	}

	public abstract Phase requiredStage();

	public void checkStage() throws CoreException {
		if (getContribution().getStage().getValue() < requiredStage().getValue())
			throw new CoreException(ClassSupplierOSGi
					.createWarningStatus(NLS.bind(Messages.ContributionStageNotEnough, requiredStage().getLiteral())));
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}

}
