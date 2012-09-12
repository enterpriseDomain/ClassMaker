package org.k.classmaker.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.Test;
import org.k.classmaker.ClassMaker;

public class Tests {

	@Test
	public void createClass() {
		EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setName("thepackage");
		ePackage.setNsPrefix("thepackage");
		ePackage.setNsURI("http://thepackage/1.0");
		EClass clazz = EcoreFactory.eINSTANCE.createEClass();
		clazz.setName("DerClass");
		ePackage.getEClassifiers().add(clazz);

		ClassMaker.getDefault().setMonitor(
				new StreamProgressMonitor(System.out));
		ClassMaker.getDefault().addEPackage(ePackage);
		EPackage resultingPackage = ClassMaker.getDefault().getEPackage(
				ePackage.getNsURI());
		assertNotNull(resultingPackage);
		assertEquals(ePackage.getName(), resultingPackage.getName());
	}

}
