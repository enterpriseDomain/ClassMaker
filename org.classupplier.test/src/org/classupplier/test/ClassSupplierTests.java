package org.classupplier.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.classupplier.Artifact;
import org.classupplier.ClassSupplier;
import org.classupplier.SupplyNotifier;
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
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ClassSupplierTests extends AbstractTests {

	@Test
	public void creationOfTheClass() {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		final EPackage readerEPackage = createEPackage("reader", "1.0");
		final EClass eClass = ecoreFactory.createEClass();
		eClass.setName("Book");
		final EAttribute pageAttr = ecoreFactory.createEAttribute();
		pageAttr.setName("pages");
		pageAttr.setEType(EcorePackage.Literals.EINT);
		eClass.getEStructuralFeatures().add(pageAttr);

		final EAttribute attr = ecoreFactory.createEAttribute();
		attr.setName("readPages");
		attr.setEType(EcorePackage.Literals.EINT);
		eClass.getEStructuralFeatures().add(attr);
		final EOperation op = ecoreFactory.createEOperation();
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
		readerEPackage.getEClassifiers().add(eClass);

		assertNotNull(service);
		final Artifact artifact = service.getWorkspace().createArtifact(
				readerEPackage);
		artifact.eAdapters().add(new SupplyNotifier() {

			private int runCount = 0;

			@Override
			protected void supplyCompleted(EPackage ePackage) {
				runCount = runCount++;
				if (runCount > 1
						|| !ePackage.getNsURI().equals(
								readerEPackage.getNsURI())
						|| !artifact.getAppropriateEPackage().getNsURI()
								.equals(ePackage.getNsURI()))
					fail();
				assertNotNull(ePackage);
				assertEquals(readerEPackage.getName(), ePackage.getName());
				assertEquals(readerEPackage.getNsPrefix(),
						ePackage.getNsPrefix());
				assertEquals(readerEPackage.getNsURI(), ePackage.getNsURI());
				EClass theClass = (EClass) ePackage.getEClassifier(eClass
						.getName());
				EObject theObject = ePackage.getEFactoryInstance().create(
						theClass);

				int pages = 22;
				EAttribute objectPageAttr = (EAttribute) theClass
						.getEStructuralFeature(pageAttr.getName());
				theObject.eSet(objectPageAttr, pages);
				assertEquals(pages, theObject.eGet(objectPageAttr));

				int readPagesCount = 11;
				try {
					Method objectMethod = theObject.getClass().getMethod(
							op.getName(), int.class);
					objectMethod.invoke(theObject, readPagesCount);

				} catch (InvocationTargetException e) {
					fail(e.getTargetException().getLocalizedMessage());
				} catch (Exception e) {
					fail(e.getLocalizedMessage());
				}
				EStructuralFeature state = theClass.getEStructuralFeature(attr
						.getName());
				assertEquals(readPagesCount, theObject.eGet(state));

				assertEquals(eClass.getName(), theObject.getClass()
						.getSimpleName());

			}
		});
		artifact.produce(new CodeGenUtil.EclipseUtil.StreamProgressMonitor(
				System.out));
	}

	@Test
	public void osgiService() {
		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass())
				.getBundleContext();
		ServiceReference<?> serviceReference = bundleContext
				.getServiceReference(ClassSupplier.class);
		ClassSupplier tested = (ClassSupplier) bundleContext
				.getService(serviceReference);
		assertNotNull(tested);
		EPackage ePackage = createEPackage("anything", "0.1");
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		final String className0 = "Hobby";
		eClass.setName(className0);
		final String className1 = "Working";
		ePackage.getEClassifiers().add(eClass);
		eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(className1);
		ePackage.getEClassifiers().add(eClass);
		Artifact artifact = tested.getWorkspace().createArtifact(ePackage);
		artifact.eAdapters().add(new SupplyNotifier() {

			@Override
			protected void supplyCompleted(EPackage ePackage) {
				assertNotNull(ePackage);
				assertObjectClass(className0, ePackage);
				assertObjectClass(className1, ePackage);
			}

		});
		artifact.produce(new CodeGenUtil.EclipseUtil.StreamProgressMonitor(
				System.out));

	}

	private void assertObjectClass(String className, EPackage resultPackage) {
		EObject result = resultPackage.getEFactoryInstance().create(
				(EClass) resultPackage.getEClassifier(className));
		assertEquals(className, result.getClass().getSimpleName());
	}
}
