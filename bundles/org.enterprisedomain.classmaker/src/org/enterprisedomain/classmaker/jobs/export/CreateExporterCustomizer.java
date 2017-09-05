package org.enterprisedomain.classmaker.jobs.export;

import org.eclipse.emf.common.util.EList;
import org.enterprisedomain.classmaker.impl.CustomizerImpl;

public class CreateExporterCustomizer extends CustomizerImpl {

	@Override
	public Object customize(EList<Object> args) {
		return new PDEPluginExporter((Integer) args.get(0));
	}

}
