package org.enterprisedomain.classmaker.core;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.operation.IRunnableWithProgress;

public class DefaultRunnerWithProgress implements IRunnerWithProgress {

	@Override
	public void run(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException {
		runnable.run(ClassMakerPlugin.getProgressMonitor());
	}

}
