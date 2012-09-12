package org.k.classmaker.codegen;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.k.classmaker.Bundle;


public interface Generator {
	
	void setResourceSet(ResourceSet resourceSet);

	void generate(Bundle bundle, IProject project, ISchedulingRule rule) throws CoreException;
	
}
