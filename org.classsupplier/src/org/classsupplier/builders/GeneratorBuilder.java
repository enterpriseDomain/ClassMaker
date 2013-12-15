package org.classsupplier.builders;

import java.util.Map;

import org.classsupplier.Artifact;
import org.classsupplier.codegen.EcoreGenerator;
import org.classsupplier.codegen.Generator;
import org.classsupplier.impl.OSGi;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class GeneratorBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = OSGi.PLUGIN_ID + '.'
			+ "generatorBuilder";

	protected Generator generator = new EcoreGenerator();

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		IProgressMonitor oldMonitor = OSGi.getClassSupplier().monitor();
		OSGi.getClassSupplier().setMonitor(monitor);
		Artifact artifact = OSGi.getClassSupplier().getWorkspace()
				.getArtifact(getProject().getName());
		generator.setResourceSet(OSGi.getClassSupplier().getWorkspace()
				.getResourceSet());
		generator.generate(artifact, getRule(kind, args));
		OSGi.getClassSupplier().setMonitor(oldMonitor);
		return null;
	}

}
