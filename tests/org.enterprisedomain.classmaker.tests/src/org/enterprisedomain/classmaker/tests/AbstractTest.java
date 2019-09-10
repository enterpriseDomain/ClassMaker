/**
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
package org.enterprisedomain.classmaker.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class AbstractTest {

	protected static ClassMakerService service;

	private static CountDownLatch latch = new CountDownLatch(1);

	protected String packageName;

	protected String className;

	protected String attributeName;

	protected EDataType attributeType;

	protected static String DEFAULT_ATTR_NAME = "a";

	protected static String ANOTHER_DEFAULT_ATTR_NAME = "b";

	private static Map<Future<? extends EPackage>, EPackage> currentDynamics;

	public void setReference(ClassMakerService dependency) {
		service = dependency;
		latch.countDown();
	}

	@BeforeClass
	public static void init() {
		currentDynamics = new HashMap<Future<? extends EPackage>, EPackage>();
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

	public void cleanup() {
		try {
			service.delete(getPackageName(), getProgressMonitor());
			Project project = service.getWorkspace().getProject(service.computeProjectName(getPackageName()));
			if (project != null)
				project.make(getProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	protected EPackage createEPackage(String name, String version) {
		setPackageName(name);
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = ecoreFactory.createEPackage();
		ePackage.setName(name.toLowerCase());
		ePackage.setNsPrefix(CodeGenUtil.capName(name));
		ePackage.setNsURI("http://" + name.toLowerCase() + "/" + version);
		return ePackage;
	}

	protected EPackage createEPackage(String version) {
		return createEPackage(getPackageName(), version);
	}

	protected EPackage updateEPackage(EPackage ePackage, String version) {
		EPackage result = EcoreUtil.copy(ePackage);
		result.setNsURI("http://" + getPackageName() + "/" + version);
		return result;
	}

	protected EClass createEClass(String name) {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EClass eClass = ecoreFactory.createEClass();
		eClass.setName(name);
		return eClass;
	}

	protected EClass createEClass() {
		return createEClass(getClassName());
	}

	protected EAttribute createEAttribute(String name, EDataType type) {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EAttribute eAttribute = ecoreFactory.createEAttribute();
		eAttribute.setName(name);
		eAttribute.setEType(type);
		return eAttribute;
	}

	protected EAttribute createEAttribute() {
		return createEAttribute(getAttributeName(), getAttributeType());
	}

	protected EPackage createAndTestEPackage(IProgressMonitor monitor, String... dependencies)
			throws CoreException, InterruptedException {
		EPackage p = createEPackage(getPackageName(), "0");
		EClass c = createEClass(getClassName());
		c.getEStructuralFeatures().add(createEAttribute(DEFAULT_ATTR_NAME, EcorePackage.Literals.EBOOLEAN));
		c.getEStructuralFeatures().add(createEAttribute(getAttributeName(), getAttributeType()));
		p.getEClassifiers().add(c);
		return saveAndTest(p, DEFAULT_ATTR_NAME, true, monitor, dependencies);
	}

	protected Future<? extends EPackage> createAndSaveEPackage(Executor executor, IProgressMonitor monitor)
			throws CoreException, InterruptedException {
		EPackage p = createEPackage(getPackageName(), "0");
		EClass c = createEClass(getClassName());
		c.getEStructuralFeatures().add(createEAttribute(DEFAULT_ATTR_NAME, EcorePackage.Literals.EBOOLEAN));
		c.getEStructuralFeatures().add(createEAttribute(getAttributeName(), getAttributeType()));
		p.getEClassifiers().add(c);
		Future<? extends EPackage> r = save(p, executor, monitor);
		currentDynamics.put(r, p);
		return r;
	}

	protected Future<? extends EPackage> updateAndSaveEPackage(Future<? extends EPackage> previousResult,
			Executor executor, IProgressMonitor monitor) throws CoreException {
		EPackage previousEPackage = currentDynamics.get(previousResult);
		EPackage p = EcoreUtil.copy(previousEPackage);
		EClass c = createEClass("Another" + getClassName());
		c.getEStructuralFeatures().add(createEAttribute(ANOTHER_DEFAULT_ATTR_NAME, EcorePackage.Literals.EBOOLEAN));
		c.getEStructuralFeatures().add(createEAttribute(getAttributeName(), getAttributeType()));
		p.getEClassifiers().add(c);
		return updateAndSave(previousEPackage, p, executor, monitor);
	}

	protected EPackage createAndTestAPI(IProgressMonitor monitor) throws CoreException, InterruptedException {
		EPackage p = createEPackage(getPackageName(), "0");
		EClass c = createEClass(getClassName());
		c.getEStructuralFeatures().add(createEAttribute(DEFAULT_ATTR_NAME, EcorePackage.Literals.EBOOLEAN));
		c.getEStructuralFeatures().add(createEAttribute(getAttributeName(), getAttributeType()));
		p.getEClassifiers().add(c);
		EPackage e = testAPICreate(p, DEFAULT_ATTR_NAME, EcorePackage.Literals.EBOOLEAN, true, monitor);
		assertNotNull(e);
		return p;
	}

	protected EPackage testAPICreate(EPackage ePackage, String attributeName, EDataType attributeType,
			Object attributeValue, IProgressMonitor monitor) throws CoreException, InterruptedException {
		try {
			EPackage result = service.make(ePackage, monitor);
			return testResult(result, attributeName, attributeType, attributeValue);
		} catch (CoreException e) {
			fail(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return null;
	}

	protected EPackage testAPIUpdate(EPackage originalEPackage, EPackage ePackage, String attributeName,
			EDataType attributeType, Object attributeValue, IProgressMonitor monitor)
			throws CoreException, InterruptedException {
		try {
			EPackage result = service.replace(originalEPackage, ePackage, monitor);
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

	protected EPackage saveAndTest(EPackage ePackage, String attributeName, Object attributeValue,
			IProgressMonitor monitor, String[] dependencies) throws CoreException, InterruptedException {
		EPackage e = service.make(ePackage, monitor);
		Contribution c = service.getWorkspace().getContribution(ePackage);
		for (String dependency : dependencies)
			c.getDependencies().add(dependency);
		return test(e, attributeName, attributeValue);
	}

	private Future<? extends EPackage> save(final EPackage ePackage, Executor executor, IProgressMonitor monitor)
			throws CoreException, InterruptedException {
		return service.make(ePackage, executor, monitor);
	}

	private Future<? extends EPackage> updateAndSave(EPackage ePackage, EPackage updated, Executor executor,
			IProgressMonitor monitor) throws CoreException {
		return service.replace(ePackage, updated, executor, monitor);
	}

	protected EPackage test(EPackage ePackage, String className, String attributeName, Object attributeValue) {
		if (ePackage == null)
			return ePackage;
		EClass s = (EClass) ePackage.getEClassifier(className);
		EFactory eFactory = ePackage.getEFactoryInstance();
		if (eFactory == null)
			return ePackage;
		EObject o = eFactory.create(s);
		assertEquals(className, o.getClass().getSimpleName());
		EStructuralFeature a = s.getEStructuralFeature(attributeName);
		o.eSet(a, attributeValue);
		assertEquals(attributeValue, o.eGet(a));
		assertEquals(CodeGenUtil.safeName(ePackage.getName()), o.getClass().getPackage().getName());
		return ePackage;
	}

	protected EPackage test(EPackage ePackage, String attributeName, Object attributeValue) {
		return test(ePackage, getClassName(), attributeName, attributeValue);
	}

	protected EPackage testAnother(EPackage ePackage, String attributeName, Object attributeValue) {
		return test(ePackage, "Another" + getClassName(), attributeName, attributeValue);
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
		return ClassMakerPlugin.getProgressMonitor();
	}

}
