package org.classupplier.export;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public interface Exporter {

	void export(IProject project, IProgressMonitor monitor)
			throws CoreException;

	IPath getExportDestination();

	void setExportDestination(IPath path);

	Version getVersion();

	void setVersion(Version version);

}
