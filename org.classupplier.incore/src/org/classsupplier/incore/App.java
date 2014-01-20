package org.classsupplier.incore;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class App implements IApplication {

	boolean done = false;
	Object appLock = new Object();

	@Override
	public Object start(IApplicationContext context) throws Exception {
		Job run = new Job("Doing it") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Runnable invoker = new Invoker();
				invoker.run();
				return Status.OK_STATUS;
			}
		};
		run.schedule();
		synchronized (appLock) {
			while (!done)
				try {
					appLock.wait();
				} catch (InterruptedException e) {
				}
		}
		wait();
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
