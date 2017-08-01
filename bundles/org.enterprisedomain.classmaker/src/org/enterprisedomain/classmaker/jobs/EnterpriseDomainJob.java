/**
 * Copyright 2012-2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.jobs;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.core.runtime.jobs.JobGroup;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public abstract class EnterpriseDomainJob extends WorkspaceJob {

	private EnterpriseDomainJob nextJob;

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (Job.class.equals(adapter))
			return (Job) this;
		if (EnterpriseDomainJob.class.equals(adapter))
			return (EnterpriseDomainJob) this;

		return super.getAdapter(adapter);
	}

	private IProject project;

	private IJobChangeListener listener = new JobChangeAdapter() {

		@Override
		public void done(IJobChangeEvent event) {
			if (!getName().equals(event.getJob().getName()))
				return;
			getContributionState().setPhase(getResultStage());

			Status result = new Status(event.getResult().getSeverity(), ClassMakerPlugin.PLUGIN_ID,
					event.getJob().getName() + ": " + event.getResult().getMessage()); //$NON-NLS-1$
			ClassMakerPlugin.getInstance().getLog().log(result);

			if (getNextJob() == null) {
				return;
			}
			getNextJob().setProject(getProject());
			getNextJob().schedule();
			try {
				getNextJob().join();
			} catch (InterruptedException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
			}
		}

	};

	private ResourceSet resourceSet;

	private int stateTimestamp = -1;

	private boolean commitState = false;

	private int buildKind;

	private boolean changeRule;

	public EnterpriseDomainJob(String name, int stateTimestamp) {
		super(name);
		setStateTimestamp(stateTimestamp);
		setUser(true);
		setPriority(Job.BUILD);
		setRule(getWorkspace());
		setChangeRule(true);
		setJobGroup(new JobGroup("Supplying Class", 0, 1)); //$NON-NLS-1$
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		Contribution contribution = getWorkspace().getContribution(getProject().getName());
		contributionState = contribution.getState(getStateTimestamp());
		fireBeforeHooks(contributionState);
		checkStage();
		IStatus result = Status.OK_STATUS;
		monitor.subTask(getName());
		if (getBuildKind() == IncrementalProjectBuilder.INCREMENTAL_BUILD
				&& getContributionState().getPhase().getValue() >= getDirtyStage().getValue())
			return result;
		result = work(monitor);
		return result;
	}

	private ListenerList<EnterpriseDomainJobListener> listeners = new ListenerList<EnterpriseDomainJobListener>();

	private synchronized void fireBeforeHooks(State contributionState) throws CoreException {
		for (EnterpriseDomainJobListener listener : listeners)
			listener.hookBefore(contributionState);
	}

	public synchronized void addListener(EnterpriseDomainJobListener listener) {
		listeners.add(listener);
	}

	public synchronized void removeListener(EnterpriseDomainJobListener listener) {
		listeners.remove(listener);
	}

	public abstract IStatus work(IProgressMonitor monitor) throws CoreException;

	public static class JobProgressProvider extends ProgressProvider {

		@Override
		public IProgressMonitor createMonitor(Job job) {
			if (ClassMakerPlugin.getInstance() == null)
				return ProgressMonitorFactory.create(ClassMakerPlugin.getProgressMonitorClass(),
						ClassMakerPlugin.getProgressMonitorClassConstructorParameters());
			return ClassMakerPlugin.getProgressMonitor();
		}
	};

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

	public void setBuildKind(int buildKind) {
		this.buildKind = buildKind;
	}

	/**
	 * 
	 * @see IncrementalProjectBuilder#FULL_BUILD
	 * @see IncrementalProjectBuilder#AUTO_BUILD
	 * @see IncrementalProjectBuilder#CLEAN_BUILD
	 * @see IncrementalProjectBuilder#INCREMENTAL_BUILD
	 * @return build kind
	 */
	public int getBuildKind() {
		return buildKind;
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
		if (isChangeRule())
			setRule(calcSchedulingRule(project));
	}

	private ISchedulingRule calcSchedulingRule(IProject project) {
		ISchedulingRule rule = getWorkspace().getContribution(project.getName()).getState(getStateTimestamp());
		return MultiRule.combine(project, rule);
	}

	public EnterpriseDomainJob getNextJob() {
		return nextJob;
	}

	public void setNextJob(EnterpriseDomainJob nextJob) {
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

	private State contributionState;

	public State getContributionState() {
		return contributionState;
	}

	protected Workspace getWorkspace() {
		return ClassMakerPlugin.getClassMaker().getWorkspace();
	}

	/**
	 * Returns a stage in which contribution state remains after execution of this
	 * job.
	 * 
	 * @return job stage
	 */
	public abstract Stage getResultStage();

	/**
	 * Returns stage required for execution of this job.
	 * 
	 * @return prerequisite stage
	 */
	public abstract Stage getPrerequisiteStage();

	/**
	 * Returns an earliest stage that becomes dirty after execution of this job.
	 * 
	 * @return dirty stage
	 */
	public abstract Stage getDirtyStage();

	public void checkStage() {
		while (getContributionState().getProjectName().equals(getProject().getName())
				&& getContributionState().getPhase().getValue() < getPrerequisiteStage().getValue())
			Thread.yield();
	}

	public int getStateTimestamp() {
		return stateTimestamp;
	}

	public void setStateTimestamp(int stateTimestamp) {
		this.stateTimestamp = stateTimestamp;
	}

	public boolean isCommitState() {
		return commitState;
	}

	public void setCommitState(boolean commitState) {
		this.commitState = commitState;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	public boolean isChangeRule() {
		return changeRule;
	}

	public void setChangeRule(boolean changeRule) {
		this.changeRule = changeRule;
	}

}
