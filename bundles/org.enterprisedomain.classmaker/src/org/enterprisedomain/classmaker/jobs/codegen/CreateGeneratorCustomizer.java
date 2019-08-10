package org.enterprisedomain.classmaker.jobs.codegen;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.enterprisedomain.classmaker.impl.CustomizerImpl;

public class CreateGeneratorCustomizer extends CustomizerImpl {

	@Override
	public Object customize(EList<Object> args) {
		EcoreGenerator generator = new EcoreGenerator((Long) args.get(1));
		generator.setProject((IProject) args.get(0));
		return generator;
	}

}
