package org.classsupplier.codegen;

import org.classsupplier.Artifact;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.ecore.resource.ResourceSet;

public interface Generator {

	void setResourceSet(ResourceSet resourceSet);

	void generate(Artifact artifact, ISchedulingRule rule, IProgressMonitor monitor) throws CoreException;

}
