package org.classsupplier.incore;

import org.eclipse.emf.ecore.EPackage;

public class PreConstructor {

	public EPackage constructEPackage() {
		return new Builder().group("h").clazz("Obj").build();
	}
}
