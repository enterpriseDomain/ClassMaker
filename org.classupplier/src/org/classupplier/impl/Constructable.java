package org.classupplier.impl;

import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EPackage;

public interface Constructable {

	Semaphore constructed();
	
	EPackage doConstruct(IProgressMonitor monitor) throws Exception;

}
