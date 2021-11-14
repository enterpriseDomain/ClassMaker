package org.enterprisedomain.ecp;

import org.eclipse.core.expressions.PropertyTester;

public class ObjectPropertyTester extends PropertyTester {

	public ObjectPropertyTester() {
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (property.equals("canDelete"))
			return true;
		return false;
	}

}