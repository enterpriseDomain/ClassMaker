package org.classupplier.codegen;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.ResourceSet;

public interface Generator extends IAdaptable {

	ResourceSet getResourceSet();

	void setResourceSet(ResourceSet resourceSet);

	IStatus generate(IProgressMonitor monitor) throws CoreException;

}
