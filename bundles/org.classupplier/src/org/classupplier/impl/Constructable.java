package org.classupplier.impl;

import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;

public interface Constructable {

	Semaphore constructed();

	<T extends EList<? extends EPackage>> T construct(IProgressMonitor monitor) throws Exception;

}
