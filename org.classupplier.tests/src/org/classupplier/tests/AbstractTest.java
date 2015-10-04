package org.classupplier.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.classupplier.ClassSupplier;
import org.classupplier.Contribution;
import org.classupplier.Phase;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.equinox.concurrent.future.IFuture;
import org.junit.Before;

public abstract class AbstractTest {

	protected static ClassSupplier service;

	private static CountDownLatch latch = new CountDownLatch(1);

	protected String packageName;

	protected String className = "C";

	protected String attrName = "c";

	protected EDataType attrType = EcorePackage.Literals.EJAVA_OBJECT;

	public void setReference(ClassSupplier dependency) {
		service = dependency;
		latch.countDown();
	}

	@Before
	public void dependencyCheck() {
		try {
			latch.await(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			fail(e.getLocalizedMessage());
		}
	}

	protected void waitLoad(Contribution contribution) {
		while (!contribution.getStage().equals(Phase.LOADED))
			Thread.yield();
	}

	protected EPackage createEPackage(String name, String version) {
		setPackageName(name);
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = ecoreFactory.createEPackage();
		ePackage.setName(name);
		ePackage.setNsPrefix(CodeGenUtil.capName(name));
		ePackage.setNsURI("http://" + name + "/" + version);
		return ePackage;
	}

	protected EPackage updateEPackage(EPackage ePackage, String version) {
		ePackage.setNsURI("http://" + getPackageName() + "/" + version);
		return ePackage;
	}

	protected EClass createEClass(String name) {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EClass eClass = ecoreFactory.createEClass();
		eClass.setName(name);
		return eClass;
	}

	protected EAttribute createEAttribute(String name, EDataType type) {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EAttribute eAttribute = ecoreFactory.createEAttribute();
		eAttribute.setName(name);
		eAttribute.setEType(type);
		return eAttribute;
	}

	protected Contribution createAndTestEPackage() {
		EPackage p = createEPackage(getPackageName(), "0");
		EClass c = createEClass(getClassName());
		c.getEStructuralFeatures().add(createEAttribute("a", EcorePackage.Literals.EBOOLEAN));
		c.getEStructuralFeatures().add(createEAttribute(getAttrName(), getAttrType()));
		p.getEClassifiers().add(c);
		Contribution n = createAndTest(p);
		return n;
	}

	protected Contribution createAndTest(EPackage ePackage) {
		Contribution n = service.getWorkspace().createContribution(ePackage);
		return applyAndTest(n);
	}

	protected Contribution applyAndTest(Contribution contribution) {
		IFuture<EList<EPackage>> r = contribution.apply(getProgressMonitor());
		while (!r.isDone()) {
			Thread.yield();
		}
		EPackage e = contribution.getGeneratedEPackages().get(0);
		EClass s = (EClass) e.getEClassifier(getClassName());
		EStructuralFeature a = s.getEStructuralFeatures().get(0);
		EObject o = e.getEFactoryInstance().create(s);
		o.eSet(a, true);
		assertEquals(true, o.eGet(a));
		assertEquals(getClassName(), o.getClass().getSimpleName());
		assertEquals(e.getName(), o.getClass().getPackage().getName());
		return contribution;
	}

	protected void assertObjectClass(String className, EPackage resultPackage) {
		EObject result = resultPackage.getEFactoryInstance().create((EClass) resultPackage.getEClassifier(className));
		assertEquals(className, result.getClass().getSimpleName());
	}

	protected String getPackageName() {
		return packageName;
	}

	protected void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	protected String getClassName() {
		return className;
	}

	protected void setClassName(String className) {
		this.className = className;
	}

	protected String getAttrName() {
		return attrName;
	}

	protected void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	protected EDataType getAttrType() {
		return attrType;
	}

	protected void setAttrType(EDataType attrType) {
		this.attrType = attrType;
	}

	protected IProgressMonitor getProgressMonitor() {
		return new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out);
	}

}
