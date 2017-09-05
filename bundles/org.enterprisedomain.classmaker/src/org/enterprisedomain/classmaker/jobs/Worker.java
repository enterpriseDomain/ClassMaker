package org.enterprisedomain.classmaker.jobs;

import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public interface Worker extends IAdaptable {

	Properties getProperties();

	IStatus work(IProgressMonitor monitor) throws CoreException;

}
