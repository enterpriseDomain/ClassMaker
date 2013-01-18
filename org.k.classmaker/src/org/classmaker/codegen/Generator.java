package org.classmaker.codegen;

import org.classmaker.Bundle;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.ecore.resource.ResourceSet;


public interface Generator {
	
	void setResourceSet(ResourceSet resourceSet);

	void generate(Bundle bundle, IProject project, ISchedulingRule rule) throws CoreException;
	
}
