package org.classsupplier.incore;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

public class Invoker implements Runnable {

	public void run() {
		PreConstructor c = new PreConstructor();
		EPackage ep = c.constructEPackage();
		EClass e = (EClass) ep.getEClassifier("Obj");
		System.out.println(e.getName());
	}
}
