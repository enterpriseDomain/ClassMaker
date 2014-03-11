package org.classupplier.export;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

public interface Exporter {

	void export(IProject project, IProgressMonitor monitor) throws CoreException;

	IPath getDestination();

	void setDestination(IPath path);

	String getVersion();

	void setVersion(String version);

}
