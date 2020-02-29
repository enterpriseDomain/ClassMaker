package org.enterprisedomain.ecp;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecp.core.ECPProject;

public class ECPPropertyTester extends PropertyTester {

	public ECPPropertyTester() {
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof ECPProject && property.equals(EnterpriseDomainProvider.PROP_CONTRIBUTION))
			if (((ECPProject) receiver).getProperties().getKeys().contains(property)
					&& ((ECPProject) receiver).getProperties().getValue(property).equals(String.valueOf(expectedValue)))
				return true;
		return false;
	}

}
