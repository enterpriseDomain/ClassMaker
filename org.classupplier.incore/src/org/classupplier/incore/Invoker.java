package org.classupplier.incore;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

public class Invoker implements Runnable {

	public void run() {
		EPackage ep = new Builder().group("h").clazz("Obj")
				.attribute("p", EcorePackage.Literals.ESTRING).build();
		EClass e = (EClass) ep.getEClassifier("Obj");
		System.out.println(e.getName());
	}
}
