package org.k.classmaker.export;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

public interface Exporter {

	void export(IProject project) throws CoreException;

	IPath getDestination();

	void setDestination(IPath path);

	String getQualifier();

	void setQualifier(String qualifier);

}
