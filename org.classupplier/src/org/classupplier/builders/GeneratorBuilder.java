package org.classupplier.builders;

import java.util.Map;

import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.codegen.EcoreGenerator;
import org.classupplier.codegen.Generator;
import org.classupplier.impl.ClassSupplierOSGi;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class GeneratorBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = ClassSupplierOSGi.PLUGIN_ID + '.'
			+ "generatorBuilder";

	protected Generator generator = new EcoreGenerator();

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		State state = ClassSupplierOSGi.getClassSupplier().getWorkspace()
				.getArtifact(getProject().getName()).getState();
		if (state.getStage().equals(Phase.NEW)
				|| !state.getStage().equals(Phase.PROCESSING))
			return null;
		if (state.getPrototypeEPackage().getEClassifiers().isEmpty())
			return null;
		generator.setResourceSet(ClassSupplierOSGi.getClassSupplier().getWorkspace()
				.getResourceSet());
		generator.generate(state, getRule(kind, args), monitor);
		return null;
	}

}
