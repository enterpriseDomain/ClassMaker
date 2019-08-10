package org.enterprisedomain.classmaker.jobs.load;

import org.eclipse.emf.common.util.EList;
import org.enterprisedomain.classmaker.impl.CustomizerImpl;

public class CreateLoaderCustomizer extends CustomizerImpl {

	@Override
	public Object customize(EList<Object> args) {
		return new OSGiEPackageLoader((Long) args.get(0));
	}

}
