package org.classupplier.impl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EPackage;

public interface Constructable {

	EPackage doConstruct(IProgressMonitor monitor) throws Exception;

}
