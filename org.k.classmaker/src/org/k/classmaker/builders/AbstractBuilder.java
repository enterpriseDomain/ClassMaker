package org.k.classmaker.builders;

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.k.classmaker.Bundle;
import org.k.classmaker.ClassMaker;
import org.k.classmaker.NameLookup;

public abstract class AbstractBuilder extends IncrementalProjectBuilder {

	private NameLookup names = ClassMaker.getDefault().names();
	
	protected final ResourceSet resourceSet = new ResourceSetImpl();;

	protected Bundle getBundle(String projectName) {
		return ClassMaker.getDefault().getBundle(projectName);
	}

	protected NameLookup names() {
		return names;
	}
}
