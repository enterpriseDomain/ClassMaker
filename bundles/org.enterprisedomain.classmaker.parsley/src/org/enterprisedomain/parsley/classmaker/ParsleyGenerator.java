package org.enterprisedomain.parsley.classmaker;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.jobs.EnterpriseDomainJob;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.enterprisedomain.classmaker.jobs.codegen.EcoreGenerator.CodeGenerationJob;

public class ParsleyGenerator extends EnterpriseDomainJob implements Worker {

	public ParsleyGenerator(int depth, long stateTimestamp) {
		super("Parsley Generator", depth, stateTimestamp);
		setAfterJob(CodeGenerationJob.class);
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return ClassMakerPlugin.createInfoStatus("OK Parsley");
	}

	@Override
	public Stage getResultStage() {
		return Stage.GENERATED;
	}

	@Override
	public Stage getDirtyStage() {
		return Stage.EXPORTED;
	}

}
