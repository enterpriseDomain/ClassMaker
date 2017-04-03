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
package org.enterprisedomain.classmaker.core;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.jobs.EnterpriseDomainJob;
import org.enterprisedomain.classmaker.jobs.EnterpriseDomainJobListener;
import org.enterprisedomain.classmaker.jobs.codegen.EcoreGenerator;
import org.enterprisedomain.classmaker.jobs.codegen.Generator;
import org.enterprisedomain.classmaker.jobs.export.Exporter;
import org.enterprisedomain.classmaker.jobs.export.PDEPluginExporter;
import org.enterprisedomain.classmaker.jobs.install.OSGiInstaller;
import org.enterprisedomain.classmaker.jobs.load.OSGiEPackageLoader;
import org.enterprisedomain.classmaker.util.GitUtil;
import org.enterprisedomain.classmaker.util.ResourceUtils;

public class ProjectBuilder extends IncrementalProjectBuilder {

	private final class JobListener implements EnterpriseDomainJobListener {
		@Override
		public void hookBefore(State contributionState) throws CoreException {
			try {
				GitUtil.add(contributionState.getProjectName(), ".");
			} catch (GitAPIException e) {				
			}
		}
	}

	public static final String BUILDER_ID = ClassMakerOSGi.PLUGIN_ID + '.' + "builder"; //$NON-NLS-1$

	public static final String INTERNAL_BUILD_ARG = "isInternalBuild";

	public static final String TIMESTAMP_ARG = "stateTimestamp";

	protected static boolean building = false;

	protected static long runId = 0;

	protected Generator generator;

	protected Exporter exporter;

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		boolean autoBuilding = ResourcesPlugin.getWorkspace().isAutoBuilding();
		try {
			Job.getJobManager().setProgressProvider(new EnterpriseDomainJob.JobProgressProvider());
			IProject[] results = new IProject[] { getProject() };
			if (building || kind == AUTO_BUILD)
				return new IProject[0];
			ResourceUtils.setAutoBuilding(ResourcesPlugin.getWorkspace(), false);
			building = true;
			runId++;

			int timestamp = -1;
			try {
				if (args.get(TIMESTAMP_ARG) != null)
					timestamp = Integer.parseInt(args.get(TIMESTAMP_ARG));
			} catch (NumberFormatException e) {

			}

			generator = new EcoreGenerator(getProject(), runId);
			exporter = new PDEPluginExporter(runId);

			EnterpriseDomainJob exporterJob = (EnterpriseDomainJob) exporter.getAdapter(EnterpriseDomainJob.class);
			exporterJob.setProject(getProject());
			exporterJob.setStateTimestamp(timestamp);
			exporterJob.setBuildKind(kind);
			final State state = exporterJob.getContributionState();
			if (state != null) {
				if (state.getPhase().equals(Stage.DEFINED))
					return results;				
			}
			exporter.setExportDestination(ResourceUtils.getExportDestination(getProject()));
			
			generator.setResourceSet(ClassMakerOSGi.getClassMaker().getWorkspace().getResourceSet());
			EnterpriseDomainJob generatorJob = ((EnterpriseDomainJob) generator.getAdapter(EnterpriseDomainJob.class));
			generatorJob.setProject(getProject());
			generatorJob.setBuildKind(kind);
			generatorJob.setStateTimestamp(timestamp);
			generatorJob.setProgressGroup(monitor, 1);
			generatorJob.setNextJob(exporterJob);

			EnterpriseDomainJob installJob = new OSGiInstaller(runId);
			exporterJob.setNextJob(installJob);

			EnterpriseDomainJob loadJob = new OSGiEPackageLoader(runId);
			loadJob.setBuildKind(kind);
			loadJob.setStateTimestamp(timestamp);
			loadJob.addListener(new JobListener());
			loadJob.addListener();
			loadJob.addJobChangeListener(new JobChangeAdapter() {

				@Override
				public void done(IJobChangeEvent event) {
					building = false;
					runId--;
				}

			});

			installJob.setBuildKind(kind);
			installJob.setStateTimestamp(timestamp);
			installJob.setNextJob(loadJob);

			monitor.beginTask(NLS.bind("Build (kind: {0})", buildKindAsString(kind)), 4);
			generatorJob.schedule();
			try {
				generatorJob.join();
				exporterJob.join();
				installJob.join();
				loadJob.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				monitor.setCanceled(true);
			}
			if (args.containsKey(INTERNAL_BUILD_ARG))
				if (Boolean.parseBoolean(args.get(INTERNAL_BUILD_ARG)))
					return new IProject[0];
			return results;
		} finally {
			ResourceUtils.setAutoBuilding(ResourcesPlugin.getWorkspace(), autoBuilding);
		}
	}

	public static long getRunId() {
		return runId;
	}

	public String buildKindAsString(int kind) {
		switch (kind) {
		case IncrementalProjectBuilder.AUTO_BUILD:
			return "AUTO_BUILD"; //$NON-NLS-1$
		case IncrementalProjectBuilder.CLEAN_BUILD:
			return "CLEAN_BUILD"; //$NON-NLS-1$
		case IncrementalProjectBuilder.FULL_BUILD:
			return "FULL_BUILD"; //$NON-NLS-1$
		case IncrementalProjectBuilder.INCREMENTAL_BUILD:
			return "INCREMENTAL_BUILD"; //$NON-NLS-1$
		default:
			return "unknown build kind: " + kind; //$NON-NLS-1$
		}

	}

}
