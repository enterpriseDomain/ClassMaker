package org.k.classmaker.builders;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.k.classmaker.Bundle;
import org.k.classmaker.ClassMaker;
import org.k.classmaker.codegen.EcoreGenerator;
import org.k.classmaker.codegen.Generator;

public class GeneratorBuilder extends AbstractBuilder {

	public static final String BUILDER_ID = ClassMaker.PLUGIN_ID + '.'
			+ "generatorBuilder";

	protected Generator generator = new EcoreGenerator();

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		IProgressMonitor oldMonitor = ClassMaker.getDefault().monitor();
		ClassMaker.getDefault().setMonitor(monitor);
		Bundle bundle = getBundle(getProject().getName());
		generator.setResourceSet(resourceSet);
		generator.generate(bundle, getProject(), getRule(kind, args));
		ClassMaker.getDefault().setMonitor(oldMonitor);
		return null;
	}

}
