package org.enterprisedomain.classmaker.jobs.load;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.jobs.ContainerJob;

public abstract class ModelLoader extends ContainerJob {

	public ModelLoader(int stateTimestamp) {
		super(Messages.JobNameLoader, stateTimestamp);
	}

	public abstract IStatus load(IProgressMonitor monitor) throws CoreException;

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		try {
			return load(monitor);
		} finally {
			monitor.done();
		}
	}

	@Override
	public Stage getPrerequisiteStage() {
		return Stage.INSTALLED;
	}

	@Override
	public Stage getResultStage() {
		return Stage.LOADED;
	}

	@Override
	public Stage getDirtyStage() {
		return Stage.LOADED;
	}

}
