package org.enterprisedomain.classmaker.core;

import org.eclipse.core.runtime.IProgressMonitor;

public class WrappingProgressMonitor implements IProgressMonitor {

	private IProgressMonitor monitor;

	public WrappingProgressMonitor(IProgressMonitor wrapped) {
		monitor = wrapped;
	}

	@Override
	public void beginTask(final String name, final int totalWork) {
		ClassMakerPlugin.wrapRun(new Runnable() {

			@Override
			public void run() {
				monitor.beginTask(name, totalWork);
			}
		});
	}

	@Override
	public void done() {
		ClassMakerPlugin.wrapRun(new Runnable() {

			@Override
			public void run() {
				monitor.done();
			}
		});
	}

	@Override
	public void internalWorked(final double work) {
		ClassMakerPlugin.wrapRun(new Runnable() {

			@Override
			public void run() {
				monitor.internalWorked(work);
			}
		});
	}

	@Override
	public boolean isCanceled() {
		return monitor.isCanceled();
	}

	@Override
	public void setCanceled(final boolean value) {
		ClassMakerPlugin.wrapRun(new Runnable() {

			@Override
			public void run() {
				monitor.setCanceled(value);
			}
		});
	}

	@Override
	public void setTaskName(final String name) {
		ClassMakerPlugin.wrapRun(new Runnable() {

			@Override
			public void run() {
				monitor.setTaskName(name);
			}
		});
	}

	@Override
	public void subTask(final String name) {
		ClassMakerPlugin.wrapRun(new Runnable() {

			@Override
			public void run() {
				monitor.subTask(name);
			}
		});
	}

	@Override
	public void worked(final int work) {
		ClassMakerPlugin.wrapRun(new Runnable() {

			@Override
			public void run() {
				monitor.worked(work);
			}
		});
	}

}
