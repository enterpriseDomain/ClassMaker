package org.classupplier.impl;

import org.classupplier.State;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EPackage;

public interface InternalState extends State {

	EPackage doConstruct(IProgressMonitor monitor) throws Exception;

}
