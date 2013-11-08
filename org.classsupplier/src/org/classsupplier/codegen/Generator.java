package org.classsupplier.codegen;

import org.classsupplier.Bundle;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.ecore.resource.ResourceSet;

public interface Generator {

	void setResourceSet(ResourceSet resourceSet);

	void generate(Bundle bundle, ISchedulingRule rule) throws CoreException;

}
