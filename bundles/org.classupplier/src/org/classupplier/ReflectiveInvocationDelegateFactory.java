package org.classupplier;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EOperation.Internal.InvocationDelegate;
import org.eclipse.emf.ecore.EOperation.Internal.InvocationDelegate.Factory;

public class ReflectiveInvocationDelegateFactory implements Factory {

	public ReflectiveInvocationDelegateFactory() {
	}

	@Override
	public InvocationDelegate createInvocationDelegate(EOperation operation) {
		return new ReflectiveInvocationDelegate(operation);
	}

}
