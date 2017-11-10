package org.enterprisedomain.classmaker.core;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.operation.IRunnableWithProgress;

public interface IProgressRunner {

	void run(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException;

}
