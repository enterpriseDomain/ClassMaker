package org.classupplier.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.classupplier.ClassSupplier;
import org.classupplier.Contribution;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.concurrent.future.IFuture;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ClassSupplierTest extends AbstractTest {

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
		an.getDetails().put("body", "setReadPages(getReadPages() + pagesRead);");
		op.getEAnnotations().add(an);
		EAnnotation invocation = ecoreFactory.createEAnnotation();
		invocation.setSource(ClassSupplier.INVOCATION_DELEGATE_URI);
		op.getEAnnotations().add(invocation);
		eClass.getEOperations().add(op);
		invocation = ecoreFactory.createEAnnotation();
		invocation.setSource(EcorePackage.eNS_URI);
		invocation.getDetails().put("invocationDelegates", ClassSupplier.INVOCATION_DELEGATE_URI);
		readerEPackage.getEAnnotations().add(invocation);
		readerEPackage.getEClassifiers().add(eClass);

		assertNotNull(service);
		Contribution contribution = service.getWorkspace().createContribution(readerEPackage);
		IFuture<? extends EPackage> result = contribution
				.construct(new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out));
		EPackage ePackage;
		try {
			ePackage = result.get();
			while (!result.isDone()) {
				Thread.yield();
			}
			IStatus status = result.getStatus();
			if (!status.isOK()) {
				if (status.getSeverity() == Status.ERROR) {
					assertNotNull(status.getException());
					status.getException().printStackTrace();
				}
			}
			assertNotNull(ePackage);
			assertEquals(contribution.getGeneratedEPackage().getNsURI(), ePackage.getNsURI());
			assertEquals(readerEPackage.getName(), ePackage.getName());
			assertEquals(readerEPackage.getNsPrefix(), ePackage.getNsPrefix());
			assertEquals(readerEPackage.getNsURI(), ePackage.getNsURI());
			EClass theClass = (EClass) ePackage.getEClassifier(eClass.getName());
			EObject theObject = ePackage.getEFactoryInstance().create(theClass);

			int pages = 22;
			EAttribute objectPageAttr = (EAttribute) theClass.getEStructuralFeature(pageAttr.getName());
			theObject.eSet(objectPageAttr, pages);
			assertEquals(pages, theObject.eGet(objectPageAttr));

			int readPagesCount = 11;
			EList<?> arguments = ECollections.asEList(readPagesCount);
			for (EOperation operation : theClass.getEAllOperations())
				if (operation.getName().equals(op.getName())) {
					EcoreUtil.getInvocationDelegateFactory(operation).createInvocationDelegate(operation)
							.dynamicInvoke((InternalEObject) theObject, arguments);
				}

			EStructuralFeature state = theClass.getEStructuralFeature(attr.getName());
			assertEquals(readPagesCount, theObject.eGet(state));

			assertEquals(eClass.getName(), theObject.getClass().getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}

	}

	@Test
	public void osgiService() {
		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ServiceReference<?> serviceReference = bundleContext.getServiceReference(ClassSupplier.class);
		ClassSupplier tested = (ClassSupplier) bundleContext.getService(serviceReference);
		assertNotNull(tested);
		EPackage ePackage = createEPackage("doings", "0.2");
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		final String className0 = "Hobby";
		eClass.setName(className0);
		final String className1 = "Work";
		ePackage.getEClassifiers().add(eClass);
		eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(className1);
		ePackage.getEClassifiers().add(eClass);

		Contribution contribution = tested.getWorkspace().createContribution(ePackage);
		EPackage resultEPackage;
		try {
			IFuture<? extends EPackage> result = contribution
					.construct(new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out));
			resultEPackage = result.get();
			while (!result.isDone()) {
				Thread.yield();
			}
			IStatus status = result.getStatus();
			if (!status.isOK()) {
				if (status.getSeverity() == Status.ERROR) {
					assertNotNull(status.getException());
					status.getException().printStackTrace();
				}
			}
			assertNotNull(resultEPackage);
			assertObjectClass(className0, resultEPackage);
			assertObjectClass(className1, resultEPackage);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void metaModel() {
		EcoreFactory factory = EcoreFactory.eINSTANCE;
		EPackage _package = createEPackage("meta", "");
		EClass metaClass = factory.createEClass();
		metaClass.setName("MetaObject");
		EAttribute attribute = factory.createEAttribute();
		attribute.setName("value");
		attribute.setEType(EcorePackage.Literals.EJAVA_OBJECT);
		metaClass.getEStructuralFeatures().add(attribute);
		_package.getEClassifiers().add(metaClass);
		Contribution contribution = service.getWorkspace().createContribution(_package);
		IFuture<? extends EPackage> result = contribution
				.construct(new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out));
		EPackage ePackage;
		try {
			ePackage = result.get();
			while (!result.isDone()) {
				Thread.yield();
			}
			IStatus status = result.getStatus();
			if (!status.isOK()) {
				if (status.getSeverity() == Status.ERROR) {
					assertNotNull(status.getException());
					status.getException().printStackTrace();
				}
			}
			assertNotNull(ePackage);
			EClass resultClass = (EClass) ePackage.getEClassifier(metaClass.getName());
			EObject book = ePackage.getEFactoryInstance().create(resultClass);
			EAttribute resultAttribute = (EAttribute) resultClass.getEStructuralFeature(attribute.getName());
			book.eSet(resultAttribute, "Text");
			assertEquals("Text", book.eGet(resultAttribute));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	private void assertObjectClass(String className, EPackage resultPackage) {
		EObject result = resultPackage.getEFactoryInstance().create((EClass) resultPackage.getEClassifier(className));
		assertEquals(className, result.getClass().getSimpleName());
	}
}
