package org.classupplier.builders;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

public class DelegatingJob extends SupplementaryJob {

	private Job delegate;

	public DelegatingJob(Job delegate) {
		super(delegate.getName());
		this.delegate = delegate;
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		delegate.schedule();
		try {
			delegate.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return delegate.getResult();
	}

	@Override
	public void checkStage() throws CoreException {

	}

}
