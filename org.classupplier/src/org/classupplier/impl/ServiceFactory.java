package org.classupplier.impl;

import org.classupplier.ClasSupplier;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class ServiceFactory extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context) {
		ClasSupplier supplier = OSGi.getClasSupplier();
		context.set(ClasSupplier.class, supplier);
		return supplier;
	}

}
