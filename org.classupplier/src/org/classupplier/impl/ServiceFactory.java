package org.classupplier.impl;

import org.classupplier.ClassSupplier;
import org.classupplier.core.ClassSupplierOSGi;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class ServiceFactory extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context) {
		ClassSupplier supplier = ClassSupplierOSGi.getClassSupplier();
		context.set(ClassSupplier.class, supplier);
		return supplier;
	}

}
