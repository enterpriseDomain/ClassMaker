package org.classupplier.jobs.install;

import org.classupplier.jobs.ContainerJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public abstract class Installer extends ContainerJob {

	public Installer() {
		super("Install");
	}

	public abstract IStatus install(IProgressMonitor monitor);

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return install(monitor);
	}

}
