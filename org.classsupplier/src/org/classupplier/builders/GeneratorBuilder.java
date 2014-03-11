package org.classupplier.builders;

import java.util.Map;

import org.classupplier.Artifact;
import org.classupplier.State;
import org.classupplier.codegen.EcoreGenerator;
import org.classupplier.codegen.Generator;
import org.classupplier.impl.OSGi;
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
		Artifact artifact = OSGi.getClasSupplier().getWorkspace()
				.getArtifact(getProject().getName());
		if (artifact.getState().equals(State.CREATED)
				|| !artifact.getState().equals(State.PROCESSING))
			return null;
		if (artifact.getPrototypeEPackage().getEClassifiers().isEmpty())
			return null;
		generator.setResourceSet(OSGi.getClasSupplier().getWorkspace()
				.getResourceSet());
		generator.generate(artifact, getRule(kind, args), monitor);
		return null;
	}

}
