package org.classupplier.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.classupplier.ClassSupplier;
import org.classupplier.Contribution;
import org.classupplier.Customizer;
import org.classupplier.State;
import org.classupplier.impl.CustomizerImpl;
import org.classupplier.jobs.codegen.EcoreGenerator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
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
import org.osgi.framework.Version;

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
		IFuture<? extends EList<EPackage>> result = contribution.apply(getProgressMonitor());
		EList<EPackage> ePackages;
		try {
			ePackages = result.get();
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
			assertNotNull(ePackages);
			assertTrue(!ePackages.isEmpty());
			assertTrue(contribution.getWorkspace().ePackagesAreEqual(contribution.getGeneratedEPackages(), ePackages,
					true));
			EPackage ePackage = ePackages.get(0);
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
			IFuture<EList<EPackage>> result = contribution.apply(getProgressMonitor());
			resultEPackage = result.get().get(0);
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
		IFuture<EList<EPackage>> result = contribution.apply(getProgressMonitor());
		EPackage ePackage;
		try {
			ePackage = result.get().get(0);
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

	@Test
	public void update() {
		setClassName("SomeClass");
		EcoreFactory f = EcoreFactory.eINSTANCE;
		EPackage p = createEPackage("updatable", "0.1");
		EClass cl = f.createEClass();
		cl.setName(getClassName());
		setAttrName("a");
		EAttribute a = f.createEAttribute();
		a.setName(getAttrName());
		a.setEType(EcorePackage.Literals.EJAVA_OBJECT);
		cl.getEStructuralFeatures().add(a);
		p.getEClassifiers().add(cl);

		Contribution c = service.getWorkspace().createContribution(p);
		try {
			IFuture<? extends EList<EPackage>> r = c.apply(getProgressMonitor());
			while (!r.isDone()) {
				Thread.yield();
			}
			EPackage e = r.get().get(0);
			EClass cla = (EClass) e.getEClassifier(cl.getName());
			EObject o = e.getEFactoryInstance().create(cla);
			assertEquals(cla.getName(), o.getClass().getSimpleName());
			o.eSet(cla.getEStructuralFeature(a.getName()), "test");
			assertEquals("test", o.eGet(cla.getEStructuralFeature(a.getName())));
		} catch (OperationCanceledException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Version v1 = c.getVersion();

		Registry packageRegistry = service.getWorkspace().getResourceSet().getPackageRegistry();
		assertNotNull(packageRegistry.getEPackage(p.getNsURI()));
		String oldNS_URI = p.getNsURI();

		try {
			c.newState();
			p = updateEPackage(c.getDynamicEPackages().get(0), "0.2");
			EAttribute b = f.createEAttribute();
			setAttrName("b");
			b.setName(getAttrName());
			b.setEType(EcorePackage.Literals.EINT);
			((EClass) p.getEClassifier(cl.getName())).getEStructuralFeatures().add(b);

			IFuture<EList<EPackage>> r = c.apply(getProgressMonitor());
			while (!r.isDone()) {
				Thread.yield();
			}
			EPackage e = r.get().get(0);
			EClass cla = (EClass) e.getEClassifier(cl.getName());
			EObject o = e.getEFactoryInstance().create(cla);
			assertEquals(cla.getName(), o.getClass().getSimpleName());
			o.eSet(cla.getEStructuralFeature(a.getName()), "test");
			assertEquals("test", o.eGet(cla.getEStructuralFeature(a.getName())));
			o.eSet(cla.getEStructuralFeature(b.getName()), 5);
			assertEquals(5, o.eGet(cla.getEStructuralFeature(b.getName())));
		} catch (OperationCanceledException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Version v2 = c.getVersion();
		assertTrue(v2.compareTo(v1) > 0);

		assertNull(packageRegistry.getEPackage(oldNS_URI));
		assertNotNull(packageRegistry.getEPackage(p.getNsURI()));

		try {
			c.newState();
			p = updateEPackage(c.getDynamicEPackages().get(0), "0.3");
			IFuture<EList<EPackage>> r = c.apply(getProgressMonitor());
			while (!r.isDone()) {
				Thread.yield();
			}
			EPackage e = r.get().get(0);
			assertEquals("http://" + e.getName() + "/0.3", e.getNsURI());
		} catch (OperationCanceledException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void downgrade() {
		setPackageName("pack");
		setClassName("C");
		setAttrName("x");
		setAttrType(EcorePackage.Literals.EJAVA_OBJECT);
		Contribution contribution = createAndTestEPackage();
		Version v0 = contribution.getVersion();

		contribution.newState();
		// contains a copy of model EPackages from previous state
		EPackage p = contribution.getDynamicEPackages().get(0);
		EClass clazz = (EClass) p.getEClassifier(getClassName());
		clazz.getEStructuralFeatures().remove(clazz.getEStructuralFeature(getAttrName()));
		p = updateEPackage(p, "1");
		contribution.getDynamicEPackages().set(0, p);

		applyAndTest(contribution);
		EPackage g = contribution.getGeneratedEPackages().get(0);
		EClass gClazz = (EClass) g.getEClassifier(getClassName());
		EObject o = g.getEFactoryInstance().create(gClazz);
		assertNull(gClazz.getEStructuralFeature(getAttrName()));
		assertEquals(getClassName(), o.getClass().getSimpleName());

		contribution.checkout(v0);
		contribution = createAndTestEPackage();
		g = contribution.getGeneratedEPackages().get(0);
		gClazz = (EClass) g.getEClassifier(getClassName());
		o = g.getEFactoryInstance().create(gClazz);
		EAttribute a = (EAttribute) gClazz.getEStructuralFeature(getAttrName());
		assertNotNull(a);
		assertEquals(getAttrType(), a.getEType());
		assertEquals(getClassName(), o.getClass().getSimpleName());
	}

	@Test
	public void recreate() {
		setPackageName("p");
		setClassName("C");
		setAttrName("c");
		setAttrType(EcorePackage.Literals.EJAVA_OBJECT);
		Contribution c = createAndTestEPackage();
		try {
			c.delete(getProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		c = createAndTestEPackage();
		applyAndTest(c);
	}

	@Test
	public void oldVersion() {
		setPackageName("same");
		setClassName("C");
		setAttrName("c");
		setAttrType(EcorePackage.Literals.EJAVA_OBJECT);
		Contribution c = createAndTestEPackage();
		State oldState = c.getState();
		c.newState();
		c.checkout(oldState.getVersion());
		applyAndTest(c);
	}

	@Test
	public void rename() {
		setPackageName("one");
		setClassName("T");
		setAttrName("t");
		setAttrType(EcorePackage.Literals.EJAVA_OBJECT);
		Contribution c = createAndTestEPackage();
		c.newState();
		EPackage p = c.getDynamicEPackages().get(0);
		setPackageName("another");
		updateEPackage(p, "1");
		p.setName("another");
		p.setNsPrefix("another");
		EClass cl = (EClass) p.getEClassifier(getClassName());
		setClassName("P");
		cl.setName(getClassName());
		cl.getEStructuralFeature(getAttrName());
		c.getDynamicEPackages().set(0, p);
		applyAndTest(c);
	}

	@Test(expected = NoSuchMethodException.class)
	public void immutable() throws NoSuchMethodException {
		setPackageName("immutable");
		EPackage eP = createEPackage(getPackageName(), "0.0.1");
		EClass eC = createEClass(getClassName());
		setAttrName("C");
		setAttrType(EcorePackage.Literals.EJAVA_OBJECT);
		EAttribute at = createEAttribute(getAttrName(), getAttrType());
		EcoreUtil.setSuppressedVisibility(at, EcoreUtil.SET, true);
		eC.getEStructuralFeatures().add(at);
		eP.getEClassifiers().add(eC);
		Contribution c = service.getWorkspace().createContribution(eP);
		Customizer customizer = new CustomizerImpl() {

			@Override
			public void customize(EList<Object> args) {
				GenModel genModel = ((GenModel) args.get(1));
				genModel.setDynamicTemplates(true);
				genModel.setTemplateDirectory("platform:/plugin/org.classupplier.tests/templates");
				genModel.setSuppressInterfaces(false);
			}

		};
		c.getCustomizers().put(EcoreGenerator.GenModelSetupJob.STAGE, customizer);
		IFuture<EList<EPackage>> r = c.apply(getProgressMonitor());
		while (!r.isDone()) {
			Thread.yield();
		}
		EPackage e;
		try {
			e = r.get().get(0);
			Class<?> cl = e.getClass().getClassLoader().loadClass(getPackageName() + "." + getClassName());
			cl.getMethod("set" + getAttrName(), Object.class);
		} catch (OperationCanceledException ex) {
			ex.printStackTrace();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	}
}
