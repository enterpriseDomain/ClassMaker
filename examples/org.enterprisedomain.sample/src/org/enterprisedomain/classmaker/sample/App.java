package org.enterprisedomain.classmaker.sample;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class App implements IApplication {

	private static final String JOB_NAME = "Doing it";
	boolean done = false;
	Object appLock = new Object();

	@Override
	public Object start(IApplicationContext context) throws Exception {
		Job run = new Job(JOB_NAME) {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Worker worker = new Worker();
				worker.run();
				if (worker.getError() != null)
					return worker.getError();
				return Status.OK_STATUS;
			}
		};
		run.schedule();
		Job.getJobManager().addJobChangeListener(new JobChangeAdapter() {

			@Override
			public void done(IJobChangeEvent event) {
				if (event.getJob().getName().equals(JOB_NAME))
					stop();
			}

		});
		synchronized (appLock) {
			while (!done)
				try {
					appLock.wait();
				} catch (InterruptedException e) {
				}
		}
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		synchronized (appLock) {
			done = true;
			appLock.notifyAll();
		}
	}

}
