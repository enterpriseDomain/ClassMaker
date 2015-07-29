package org.classupplier.export;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.osgi.framework.Version;

public interface Exporter extends IAdaptable {

	IStatus export(IProgressMonitor monitor) throws CoreException;

	IPath getExportDestination();

	void setExportDestination(IPath path);

	Version getVersion();

	void setVersion(Version version);

}
