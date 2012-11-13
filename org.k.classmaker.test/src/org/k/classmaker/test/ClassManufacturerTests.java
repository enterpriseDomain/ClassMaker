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

public class ClassManufacturerTests {

	@Test
	public void theClassCreation() {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = ecoreFactory.createEPackage();
		ePackage.setName("bookReader");
		ePackage.setNsPrefix("bookreader");
		ePackage.setNsURI("http://bookreader/1.0");
		EClass eClass = ecoreFactory.createEClass();
		eClass.setName("Book");
		EAttribute pageAttr = ecoreFactory.createEAttribute();
		pageAttr.setName("pages");
		pageAttr.setEType(EcorePackage.Literals.EINT);
		eClass.getEStructuralFeatures().add(pageAttr);

		EAttribute attr = ecoreFactory.createEAttribute();
		attr.setName("pagesRead");
		attr.setEType(EcorePackage.Literals.EINT);
		eClass.getEStructuralFeatures().add(attr);
		EOperation op = ecoreFactory.createEOperation();
		op.setName("read");
		EParameter p = ecoreFactory.createEParameter();
		p.setEType(EcorePackage.Literals.EINT);
		p.setName("pagesIncrement");
		op.getEParameters().add(p);
		EAnnotation an = ecoreFactory.createEAnnotation();
		an.setSource("http://www.eclipse.org/emf/2002/GenModel");
		an.getDetails().put("body",
				"setPagesRead(getPagesRead() + pagesIncrement);");
		op.getEAnnotations().add(an);
		eClass.getEOperations().add(op);
		ePackage.getEClassifiers().add(eClass);

		ClassMaker.getInstance().setMonitor(
				new StreamProgressMonitor(System.out));
		ClassMaker.getInstance().addEPackage(ePackage);

		EPackage nativePackage = ClassMaker.getInstance().getEPackage(
				ePackage.getNsURI());
		assertNotNull(nativePackage);
		assertEquals(ePackage.getName(), nativePackage.getName());
		EClass theClass = (EClass) nativePackage.getEClassifier(eClass
				.getName());
		EObject theObject = nativePackage.getEFactoryInstance()
				.create(theClass);
		assertEquals(eClass.getName(), theObject.getClass().getSimpleName());

		int pages = 500;
		pageAttr = (EAttribute) theClass.getEStructuralFeature(pageAttr.getName());
		theObject.eSet(pageAttr, pages);
		assertEquals(pages, theObject.eGet(pageAttr));

		int pagesReadCount = 9;
		try {
			Method objectMethod = theObject.getClass().getMethod(op.getName(),
					int.class);
			objectMethod.invoke(theObject, pagesReadCount);

		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
		EStructuralFeature state = theClass.getEStructuralFeature(attr
				.getName());
		assertEquals(pagesReadCount, theObject.eGet(state));

	}
}
