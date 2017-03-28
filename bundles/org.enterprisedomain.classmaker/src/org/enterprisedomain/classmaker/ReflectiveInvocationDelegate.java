/**
 * Copyright 2012-2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EOperation.Internal.InvocationDelegate;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicInvocationDelegate;

public class ReflectiveInvocationDelegate extends BasicInvocationDelegate implements InvocationDelegate {

	public ReflectiveInvocationDelegate(EOperation operation) {
		super(operation);
	}

	@Override
	public Object dynamicInvoke(InternalEObject target, EList<?> arguments) throws InvocationTargetException {
		Assert.isTrue(target.eClass().getName().equals(target.getClass().getSimpleName()));
		Class<?>[] parameterTypes = new Class<?>[eOperation.getEParameters().size()];
		for (int i = 0; i < parameterTypes.length; i++)
			parameterTypes[i] = eOperation.getEParameters().get(i).getEType().getInstanceClass();

		Method objectMethod;
		try {
			objectMethod = target.getClass().getMethod(eOperation.getName(), parameterTypes);
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
