package org.k.classmaker.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.k.classmaker.ClassMaker;

public class Tests {

	@Test
	public void createClass() {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = ecoreFactory.createEPackage();
		ePackage.setName("reader");
		ePackage.setNsPrefix("reader");
		ePackage.setNsURI("http://reader/1.0");
		EClass clazz = ecoreFactory.createEClass();
		clazz.setName("Book");
		EAttribute attr = ecoreFactory.createEAttribute();
		attr.setName("pagesRead");
		attr.setEType(EcorePackage.Literals.EINT);
		clazz.getEStructuralFeatures().add(attr);
		EOperation op = ecoreFactory.createEOperation();
		op.setName("read");
		EParameter p = ecoreFactory.createEParameter();
		p.setEType(EcorePackage.Literals.EINT);
		p.setName("pages");
		op.getEParameters().add(p);
		EAnnotation an = ecoreFactory.createEAnnotation();
		an.setSource("http://www.eclipse.org/emf/2002/GenModel");
		an.getDetails().put("body",
				"this.setPagesRead(this.getPagesRead() + pages);");
		op.getEAnnotations().add(an);
		clazz.getEOperations().add(op);
		ePackage.getEClassifiers().add(clazz);

		ClassMaker.getDefault().setMonitor(
				new StreamProgressMonitor(System.out));
		ClassMaker.getDefault().addEPackage(ePackage);
		EPackage thePackage = ClassMaker.getDefault().getEPackage(
				ePackage.getNsURI());
		assertNotNull(thePackage);
		assertEquals(ePackage.getName(), thePackage.getName());
		EClass theClass = (EClass) thePackage.getEClassifier(clazz.getName());
		EObject theObject = thePackage.getEFactoryInstance().create(theClass);
		assertEquals(clazz.getName(), theObject.getClass().getSimpleName());
		int pagesRead = 6;
		try {
			Method objectMethod = theObject.getClass().getMethod(op.getName(),
					int.class);
			objectMethod.invoke(theObject, pagesRead);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		EStructuralFeature state = (EStructuralFeature) theClass
				.getEStructuralFeature(attr.getName());
		assertEquals(pagesRead, theObject.eGet(state));
	}
}
