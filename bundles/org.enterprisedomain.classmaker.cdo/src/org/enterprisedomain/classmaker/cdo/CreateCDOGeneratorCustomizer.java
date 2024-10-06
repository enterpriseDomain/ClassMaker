package org.enterprisedomain.classmaker.cdo;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.enterprisedomain.classmaker.impl.CustomizerImpl;

public class CreateCDOGeneratorCustomizer extends CustomizerImpl {

	@Override
	public Object customize(EList<Object> args) {
		CDOGenerator generator = new CDOGenerator((Integer) args.get(0), (Long) args.get(1));
		generator.setProject((IProject) args.get(2));
		return generator;
	}

}
