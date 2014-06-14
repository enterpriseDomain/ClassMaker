package org.classupplier.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;

public class UnfinishedTests extends AbstractTests {

	@Test
	public void metaModel() {
		EcoreFactory factory = EcoreFactory.eINSTANCE;
		EPackage _package = factory.createEPackage();
		_package.setName("meta");
		EClass metaClass = factory.createEClass();
		metaClass.setName("MetaObject");
		EAttribute attribute = factory.createEAttribute();
		attribute.setName("Value");
		attribute.setEType(EcorePackage.Literals.EJAVA_OBJECT);
		metaClass.getEStructuralFeatures().add(attribute);
		_package.getEClassifiers().add(metaClass);
		EPackage result = service.supply(_package,
				new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out));
		EClass resultClass = (EClass) result
				.getEClassifier(metaClass.getName());
		EObject book = result.getEFactoryInstance().create(resultClass);
		EAttribute resultAttribute = (EAttribute) resultClass
				.getEStructuralFeature(attribute.getName());
		book.eSet(resultAttribute, "Text");
		assertEquals("Text", book.eGet(resultAttribute));

	}

}
