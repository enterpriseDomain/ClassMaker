/**
 * Copyright 2012-2018 Kyrill Zotkin
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

import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl;
import org.enterprisedomain.classmaker.util.ReflectiveFactory;

public abstract class EnterpriseDomainJob extends WorkspaceJob implements Worker {

	private ProgressProvider progressProvider = null;

	private final ProgressProvider DEFAULT_PROGRESS_PROVIDER = new EnterpriseDomainJob.JobProgressProvider();

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
			if (event.getResult().isOK())
				getContributionState().setPhase(getResultStage());

			Status result = new Status(event.getResult().getSeverity(), ClassMakerPlugin.PLUGIN_ID,
					event.getJob().getName() + ": " + event.getResult().getMessage()); //$NON-NLS-1$
			if (ClassMakerPlugin.getInstance() == null)
				return;
			ClassMakerPlugin.getInstance().getLog().log(result);

			if (hasErrors(event.getResult()) && getDepth() < 5) {
				EnterpriseDomainJob job = getJob(getContributionState().getStrategy().getReturnWorker());
				if (job != null) {
					EnterpriseDomainJob nextJob = job.getNextJob();
					EnterpriseDomainJob prevJob = job;
					while (nextJob != null && !nextJob.terminate()) {
						prevJob = nextJob;
						nextJob = nextJob.getNextJob();
					}
					prevJob.setNextJob(getNextJob());
				}
				if (job != null && !EnterpriseDomainJob.this.getClass().equals(job.getClass()))
					setNextJob(job);
			}
			if (getNextJob() == null) {
				if (ClassMakerPlugin.getPreviousProgressProvider() != null)
					Job.getJobManager().setProgressProvider(ClassMakerPlugin.getPreviousProgressProvider());
				else
					Job.getJobManager().setProgressProvider(DEFAULT_PROGRESS_PROVIDER);
				return;
			}
			getNextJob().setProject(getProject());
			if (getNextJob().isExclusive() || EnterpriseDomainJob.this.getClass() == getNextJob().getAfterJob())
				getNextJob().schedule();
		}

	};

	private Class<? extends EnterpriseDomainJob> after;

	private ResourceSet resourceSet;

	private boolean exclusive = true;

	private long stateTimestamp = -1;

	private boolean commitState = false;

	private int depth = -1;

	private int buildKind;

	private boolean changeRule;

	private Properties properties;

	public EnterpriseDomainJob(String name, int depth, long stateTimestamp) {
		super(name);
		setDepth(depth);
		setStateTimestamp(stateTimestamp);
		setUser(true);
		setPriority(Job.BUILD);
		try {
			if (!ClassMakerServiceImpl.initializing && ClassMakerPlugin.getClassMaker() != null)
				setRule(ClassMakerPlugin.getClassMaker().getWorkspace());
			else
				setRule(project);
		} catch (Exception e) {
			setRule(project);
		}
		setChangeRule(true);
		setJobGroup(new JobGroup("ClassMaker", 0, 1)); //$NON-NLS-1$
	}

	public static boolean joinJob(String name) {
		Job[] jobs = Job.getJobManager().find(null);
		boolean joined = false;
		for (Job job : jobs)
			if (job.getName().equals(name))
				try {
					job.join(9000, ClassMakerPlugin.getProgressMonitor());
					joined = true;
				} catch (InterruptedException e) {
				}
		return joined;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		IStatus result = Status.OK_STATUS;
		monitor.subTask(getName());
		if (getBuildKind() == IncrementalProjectBuilder.INCREMENTAL_BUILD
				&& getContributionState().getPhase().getValue() >= getDirtyStage().getValue())
			return result;
		if (progressProvider == null)
			setProgressProvider(DEFAULT_PROGRESS_PROVIDER);
		return work(monitor);
	}

	public boolean hasErrors(IStatus status) {
		return status.getException() != null;
	}

	public static EnterpriseDomainJob getJob(Worker worker) {
		if (worker == null)
			return null;
		return (EnterpriseDomainJob) worker.getAdapter(EnterpriseDomainJob.class);
	}

	public static EList<EnterpriseDomainJob> getJobs(EList<Worker> workers) {
		if (workers == null)
			return null;
		EList<EnterpriseDomainJob> results = ECollections.newBasicEList();
		for (Worker worker : workers)
			results.add((EnterpriseDomainJob) worker.getAdapter(EnterpriseDomainJob.class));
		return results;
	}

	public abstract IStatus work(IProgressMonitor monitor) throws CoreException;

	public static class JobProgressProvider extends ProgressProvider {

		@Override
		public IProgressMonitor createMonitor(Job job) {
			if (ClassMakerPlugin.getInstance() == null)
				return ReflectiveFactory.create(ClassMakerPlugin.getProgressMonitorClass(),
						ClassMakerPlugin.getProgressMonitorClassConstructorParameters());
			return ClassMakerPlugin.getProgressMonitor();
		}
	};

	@Override
	public boolean belongsTo(Object family) {
		return super.belongsTo(family) || family == ResourcesPlugin.FAMILY_MANUAL_BUILD;
	}

	public void setProgressProvider(ProgressProvider progressProvider) {
		this.progressProvider = progressProvider;
		Job.getJobManager().setProgressProvider(progressProvider);
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

	protected int getDepth() {
		return depth;
	}

	protected void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

	/**
	 * 
	 * @return job class after which instances should execute this job
	 */
	public Class<? extends EnterpriseDomainJob> getAfterJob() {
		return after;
	}

	public void setAfterJob(Class<? extends EnterpriseDomainJob> after) {
		this.after = after;
	}

	protected boolean terminate() {
		return false;
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
		if (isChangeRule() && project != null)
			setRule(calcSchedulingRule(project));
	}

	public IProject getEditProject() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(getProject().getName() + ".edit");
	}

	public IProject getEditorProject() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(getProject().getName() + ".editor");
	}

	protected ISchedulingRule calcSchedulingRule(IProject project) {
		if (ClassMakerServiceImpl.initializing)
			return project;
		ClassMakerService service = ClassMakerPlugin.getClassMaker();
		if (service == null)
			return project;
		ISchedulingRule rule = null;
		if (!service.eIsSet(ClassMakerPackage.Literals.CLASS_MAKER_SERVICE__WORKSPACE))
			if (contributionState != null)
				rule = contributionState;
			else
				return project;
		else {
			Contribution contribution = service.getWorkspace().getContribution(project.getName());
			if (contribution != null)
				rule = contribution.getState(getStateTimestamp());
		}
		return MultiRule.combine(project, rule);
	}

	public EnterpriseDomainJob getNextJob() {
		return nextJob;
	}

	public void setNextJob(EnterpriseDomainJob nextJob) {
		if (this.nextJob != null && !this.nextJob.isExclusive() && this.nextJob != nextJob && nextJob != null
				&& !nextJob.terminate()) {
			EnterpriseDomainJob toCheck = this.nextJob.getNextJob();
			EnterpriseDomainJob toSet = toCheck;
			while (toCheck != null && toCheck != nextJob && toCheck != nextJob.getNextJob() && !toCheck.terminate()) {
				toSet = toCheck;
				toCheck = toCheck.getNextJob();
			}
			if (toSet != null)
				toSet.setNextJob(nextJob);
		} else {
			this.nextJob = nextJob;
			if (this.nextJob != null)
				addListener();
			else
				removeListener();
		}
	}

	public void removeListener() {
		removeJobChangeListener(listener);
	}

	public void addListener() {
		addJobChangeListener(listener);
	}

	private State contributionState;

	public void setContributionState(State state) {
		contributionState = state;
	}

	public State getContributionState() {
		if (contributionState == null) {
			Contribution contribution = ClassMakerPlugin.getClassMaker().getWorkspace()
					.getContribution(getProject().getName());
			if (contribution == null)
				return contributionState;
			contributionState = contribution.getState(getStateTimestamp());
		}
		return contributionState;
	}

	protected Workspace getWorkspace() {
		if (getContributionState() != null)
			return getContributionState().getProject().getWorkspace();
		else
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
	 * Returns an earliest stage that becomes dirty after execution of this job.
	 * 
	 * @return dirty stage
	 */
	public abstract Stage getDirtyStage();

	public long getStateTimestamp() {
		return stateTimestamp;
	}

	public void setStateTimestamp(long stateTimestamp) {
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

	@Override
	public Properties getProperties() {
		if (properties == null)
			properties = new Properties();
		return properties;
	}

}
