package org.classupplier.install;

import org.classupplier.builders.SupplementaryJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public abstract class Installer extends SupplementaryJob {

	public Installer() {
		super("Install");
	}

	public abstract IStatus install(IProgressMonitor monitor);

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return install(monitor);
	}

	protected BundleContext getContext() {
		return FrameworkUtil.getBundle(this.getClass()).getBundleContext();
	}

}
