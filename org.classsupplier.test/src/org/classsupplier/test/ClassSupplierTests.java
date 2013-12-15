package org.classsupplier.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.classsupplier.ClassSupplier;
import org.eclipse.emf.codegen.util.CodeGenUtil;
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
import org.junit.Before;
import org.junit.Test;

public class ClassSupplierTests {

	private static ClassSupplier service;

	private static CountDownLatch latch = new CountDownLatch(1);

	public void setReference(ClassSupplier dependency) {
		service = dependency;
		latch.countDown();
	}

	@Before
	public void dependencyCheck() {
		try {
			latch.await(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void theClassCreation() {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = ecoreFactory.createEPackage();
		ePackage.setName("reader");
		ePackage.setNsPrefix("reader");
		ePackage.setNsURI("http://reader/1.0");
		EClass eClass = ecoreFactory.createEClass();
		eClass.setName("Book");
		EAttribute pageAttr = ecoreFactory.createEAttribute();
		pageAttr.setName("pages");
		pageAttr.setEType(EcorePackage.Literals.EINT);
		eClass.getEStructuralFeatures().add(pageAttr);

		EAttribute attr = ecoreFactory.createEAttribute();
		attr.setName("readPages");
		attr.setEType(EcorePackage.Literals.EINT);
		eClass.getEStructuralFeatures().add(attr);
		EOperation op = ecoreFactory.createEOperation();
		op.setName("read");
		EParameter p = ecoreFactory.createEParameter();
		p.setEType(EcorePackage.Literals.EINT);
		p.setName("pagesRead");
		op.getEParameters().add(p);
		EAnnotation an = ecoreFactory.createEAnnotation();
		an.setSource("http://www.eclipse.org/emf/2002/GenModel");
		an.getDetails()
				.put("body", "setReadPages(getReadPages() + pagesRead);");
		op.getEAnnotations().add(an);
		eClass.getEOperations().add(op);
		ePackage.getEClassifiers().add(eClass);

		assertNotNull(service);
		EPackage nativePackage = service.supply(ePackage,
				new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out));
		assertNotNull(nativePackage);
		assertEquals(ePackage.getName(), nativePackage.getName());
		EClass theClass = (EClass) nativePackage.getEClassifier(eClass
				.getName());
		EObject theObject = nativePackage.getEFactoryInstance()
				.create(theClass);
		assertEquals(eClass.getName(), theObject.getClass().getSimpleName());

		int pages = 704;
		pageAttr = (EAttribute) theClass.getEStructuralFeature(pageAttr
				.getName());
		theObject.eSet(pageAttr, pages);
		assertEquals(pages, theObject.eGet(pageAttr));

		int readPagesCount = 9;
		try {
			Method objectMethod = theObject.getClass().getMethod(op.getName(),
					int.class);
			objectMethod.invoke(theObject, readPagesCount);

		} catch (NoSuchMethodException e) {
			fail(e.getLocalizedMessage());
		} catch (IllegalAccessException e) {
			fail(e.getLocalizedMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getLocalizedMessage());
		} catch (InvocationTargetException e) {
			fail(e.getTargetException().getLocalizedMessage());
		}

		EStructuralFeature state = theClass.getEStructuralFeature(attr
				.getName());
		assertEquals(readPagesCount, theObject.eGet(state));

	}

//	@Test
	public void experiment() {
		EcoreFactory factory = EcoreFactory.eINSTANCE;
		EPackage _package = factory.createEPackage();
		_package.setName("inputmodel");
		EClass data = factory.createEClass();
		data.setName("Data");
		EAttribute attribute = factory.createEAttribute();
		attribute.setName("Input");
		attribute.setEType(EcorePackage.Literals.EJAVA_OBJECT);
		data.getEStructuralFeatures().add(attribute);
		_package.getEClassifiers().add(data);
		EPackage result = service.supply(_package, new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out));
		EClass resultData = (EClass) result.getEClassifier(data.getName());
		EAttribute resultAttribute = (EAttribute) resultData
				.getEStructuralFeature(attribute.getName());
		EObject someData = result.getEFactoryInstance().create(resultData);
		someData.eSet(resultAttribute, "Help");
		assertEquals("Help", someData.eGet(resultAttribute));
	}
}
