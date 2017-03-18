/**
 * Copyright 2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.enterprisedomain.classsupplier.ClassPlant;
import org.enterprisedomain.classsupplier.CompletionListener;
import org.enterprisedomain.classsupplier.Contribution;
import org.enterprisedomain.classsupplier.core.ClassSupplierOSGi;
import org.enterprisedomain.classsupplier.impl.CompletionListenerImpl;
import org.junit.Before;

public abstract class AbstractTest {

	protected static ClassPlant service;

	private static CountDownLatch latch = new CountDownLatch(1);

	protected String packageName;

	protected String className;

	protected String attributeName;

	protected EDataType attributeType;

	public void setReference(ClassPlant dependency) {
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

	@Before
	public void prepare() {
		setPackageName(null);
		setClassName("C");
		setAttributeName("c");
		setAttributeType(EcorePackage.Literals.EJAVA_OBJECT);
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

	protected Contribution createAndTestEPackage() throws CoreException, InterruptedException {
		EPackage p = createEPackage(getPackageName(), "0");
		EClass c = createEClass(getClassName());
		c.getEStructuralFeatures().add(createEAttribute("a", EcorePackage.Literals.EBOOLEAN));
		c.getEStructuralFeatures().add(createEAttribute(getAttributeName(), getAttributeType()));
		p.getEClassifiers().add(c);
		Contribution n = createAndTest(p);
		return n;
	}

	protected Contribution createAndTest(EPackage ePackage) throws CoreException, InterruptedException {
		Contribution n = null;
		try {
			n = service.getWorkspace().createContribution(ePackage, getProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return saveAndTest(n);
	}

	protected EPackage createAndTestAPI() throws CoreException, InterruptedException {
		EPackage p = createEPackage(getPackageName(), "0");
		EClass c = createEClass(getClassName());
		c.getEStructuralFeatures().add(createEAttribute("a", EcorePackage.Literals.EBOOLEAN));
		c.getEStructuralFeatures().add(createEAttribute(getAttributeName(), getAttributeType()));
		p.getEClassifiers().add(c);
		EPackage e = testAPICreate(p, "a", EcorePackage.Literals.EBOOLEAN, true);
		assertNotNull(e);
		return p;
	}

	protected EPackage testAPICreate(EPackage ePackage, String attributeName, EDataType attributeType,
			Object attributeValue) throws CoreException, InterruptedException {
		try {
			EPackage result = service.produce(ePackage);
			return testResult(result, attributeName, attributeType, attributeValue);
		} catch (CoreException e) {
			fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return null;
	}

	protected EPackage testAPIUpdate(EPackage originalEPackage, EPackage ePackage, String attributeName,
			EDataType attributeType, Object attributeValue) throws CoreException, InterruptedException {
		try {
			EPackage result = service.replace(originalEPackage, ePackage);
			return testResult(result, attributeName, attributeType, attributeValue);
		} catch (CoreException e) {
			fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return null;
	}

	private EPackage testResult(EPackage result, String attributeName, EDataType attributeType, Object attributeValue) {
		assertNotNull(result);
		EClass s = (EClass) result.getEClassifier(getClassName());
		EStructuralFeature a = null;
		for (EStructuralFeature f : s.getEStructuralFeatures())
			if (f.getName().equals(attributeName) && f.getEType().equals(attributeType))
				a = f;
		EObject o = result.getEFactoryInstance().create(s);
		o.eSet(a, attributeValue);
		assertEquals(attributeValue, o.eGet(a));
		assertEquals(getClassName(), o.getClass().getSimpleName());
		assertEquals(CodeGenUtil.safeName(result.getName()), o.getClass().getPackage().getName());
		return result;
	}

	protected Contribution saveAndTest(Contribution contribution) throws CoreException, InterruptedException {
		final Semaphore complete = new Semaphore(0);
		CompletionListener l = new CompletionListenerImpl() {

			@Override
			public void completed(Contribution result) {
				try {
					EPackage e = result.getDomainModel().getGenerated();
					if (e == null)
						return;
					EClass s = (EClass) e.getEClassifier(getClassName());
					EStructuralFeature a = s.getEStructuralFeatures().get(0);
					EObject o = e.getEFactoryInstance().create(s);
					o.eSet(a, true);
					assertEquals(true, o.eGet(a));
					assertEquals(getClassName(), o.getClass().getSimpleName());
					assertEquals(CodeGenUtil.safeName(e.getName()), o.getClass().getPackage().getName());

				} catch (Exception e) {
					fail(e.getLocalizedMessage());
				} finally {
					complete.release();
				}
			}

		};
		contribution.addSaveCompletionListener(l);
		contribution.save(getProgressMonitor());
		try {
			complete.acquire();
		} catch (InterruptedException e) {
			throw e;
		}
		contribution.removeSaveCompletionListener(l);
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

	protected String getAttributeName() {
		return attributeName;
	}

	protected void setAttributeName(String attrName) {
		this.attributeName = attrName;
	}

	protected EDataType getAttributeType() {
		return attributeType;
	}

	protected void setAttributeType(EDataType attrType) {
		this.attributeType = attrType;
	}

	protected IProgressMonitor getProgressMonitor() {
		return ClassSupplierOSGi.getInstance().getProgressMonitor();
	}

}
