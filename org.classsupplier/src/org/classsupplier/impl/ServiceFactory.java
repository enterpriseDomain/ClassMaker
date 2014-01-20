package org.classsupplier.impl;

import org.classsupplier.ClassSupplier;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class ServiceFactory extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context) {
		ClassSupplier supplier = OSGi.getClassSupplier();
		context.set(ClassSupplier.class, supplier);
		return supplier;
	}

}
