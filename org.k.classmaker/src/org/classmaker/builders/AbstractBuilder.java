package org.classmaker.builders;

import org.classmaker.Bundle;
import org.classmaker.ClassMaker;
import org.classmaker.NameLookup;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public abstract class AbstractBuilder extends IncrementalProjectBuilder {

	private NameLookup names = ClassMaker.getInstance().names();
	
	protected final ResourceSet resourceSet = new ResourceSetImpl();;

	protected Bundle getBundle(String projectName) {
		return ClassMaker.getInstance().getBundle(projectName);
	}

	protected NameLookup names() {
		return names;
	}
}
