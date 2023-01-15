package org.enterprisedomain.parsley.classmaker;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.impl.CustomizerImpl;
import org.enterprisedomain.classmaker.jobs.EnterpriseDomainJob;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.enterprisedomain.classmaker.jobs.export.AbstractExporter;
import org.enterprisedomain.classmaker.jobs.export.CreateExporterCustomizer;

public class ParsleyGeneratorCustomizer extends CustomizerImpl implements Customizer {

	public Object customize(EList<Object> args) {
		EnterpriseDomainJob result = new ParsleyGenerator((int) args.get(0), (long) args.get(1));
		result.setProject((IProject) args.get(2));
		return result;
	}

	@Override
	public boolean isNextAfter(Class<? extends Customizer> customizerClass) {
		return CreateExporterCustomizer.class.isAssignableFrom(customizerClass);
	}

}
