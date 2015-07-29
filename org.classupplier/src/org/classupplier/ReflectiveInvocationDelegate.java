package org.classupplier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EOperation.Internal.InvocationDelegate;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicInvocationDelegate;

public class ReflectiveInvocationDelegate extends BasicInvocationDelegate
		implements InvocationDelegate {

	public ReflectiveInvocationDelegate(EOperation operation) {
		super(operation);
	}

	@Override
	public Object dynamicInvoke(InternalEObject target, EList<?> arguments)
			throws InvocationTargetException {
		Assert.isTrue(target.eClass().getName()
				.equals(target.getClass().getSimpleName()));
		Class<?>[] parameterTypes = new Class<?>[eOperation.getEParameters()
				.size()];
		for (int i = 0; i < parameterTypes.length; i++)
			parameterTypes[i] = eOperation.getEParameters().get(i).getEType()
					.getInstanceClass();

		Method objectMethod;
		try {
			objectMethod = target.getClass().getMethod(eOperation.getName(),
					parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new InvocationTargetException(e);
		} catch (SecurityException e) {
			throw new InvocationTargetException(e);
		}
		try {
			return objectMethod.invoke(target, arguments.toArray());
		} catch (IllegalAccessException e) {
			throw new InvocationTargetException(e);
		} catch (IllegalArgumentException e) {
			throw new InvocationTargetException(e);
		}
	}
}
