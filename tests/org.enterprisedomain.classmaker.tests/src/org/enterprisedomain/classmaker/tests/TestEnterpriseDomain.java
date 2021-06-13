/**
 * Copyright 2012-2021 Kyrill Zotkin
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.enterprisedomain.classmaker.Blueprint;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.CompletionListenerImpl;
import org.enterprisedomain.classmaker.impl.CustomizerImpl;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.junit.jupiter.api.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

public class TestEnterpriseDomain extends AbstractTest {

	private EObject o;
	private EPackage p;

	@Test
	public void classCreation() throws Exception {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		final EPackage readerEPackage = createEPackage("reader", "1.0");
		final EClass eClass = ecoreFactory.createEClass();
		eClass.setName("Book");
		final EAttribute pagesAttr = ecoreFactory.createEAttribute();
		pagesAttr.setName("totalPages");
		pagesAttr.setEType(EcorePackage.Literals.EINT);
		eClass.getEStructuralFeatures().add(pagesAttr);

		final EAttribute attribute = ecoreFactory.createEAttribute();
		attribute.setName("pagesRead");
		attribute.setEType(EcorePackage.Literals.EINT);
		eClass.getEStructuralFeatures().add(attribute);

		final EOperation operation = ecoreFactory.createEOperation();
		operation.setName("read");
		EParameter p = ecoreFactory.createEParameter();
		p.setEType(EcorePackage.Literals.EINT);
		p.setName("pagesRead");
		operation.getEParameters().add(p);
		EAnnotation an = ecoreFactory.createEAnnotation();
		an.setSource("http://www.eclipse.org/emf/2002/GenModel");
		an.getDetails().put("body", "setPagesRead(getPagesRead() + pagesRead);");
		operation.getEAnnotations().add(an);
		EAnnotation invocation = ecoreFactory.createEAnnotation();
		invocation.setSource(ClassMakerService.INVOCATION_DELEGATE_URI);
		operation.getEAnnotations().add(invocation);
		eClass.getEOperations().add(operation);
		invocation = ecoreFactory.createEAnnotation();
		invocation.setSource(EcorePackage.eNS_URI);
		invocation.getDetails().put("invocationDelegates", ClassMakerService.INVOCATION_DELEGATE_URI);
		readerEPackage.getEAnnotations().add(invocation);
		readerEPackage.getEClassifiers().add(eClass);

		assertNotNull(service);

		EPackage ePackage = (EPackage) service.make(readerEPackage, getProgressMonitor());
		assertNotNull(ePackage);
		EClass theClass = (EClass) ePackage.getEClassifier(eClass.getName());
		EObject theObject = ePackage.getEFactoryInstance().create(theClass);

		int pages = 22;
		EAttribute objectPageAttr = (EAttribute) theClass.getEStructuralFeature(pagesAttr.getName());
		theObject.eSet(objectPageAttr, pages);
		assertEquals(pages, theObject.eGet(objectPageAttr));

		int readPagesCount = 11;
		EList<?> arguments = ECollections.asEList(readPagesCount);
		for (EOperation op : theClass.getEAllOperations())
			if (op.getName().equals(op.getName())) {
				EcoreUtil.getInvocationDelegateFactory(op).createInvocationDelegate(op)
						.dynamicInvoke((InternalEObject) theObject, arguments);
			}

		EStructuralFeature state = theClass.getEStructuralFeature(attribute.getName());
		assertEquals(readPagesCount, theObject.eGet(state));

		assertEquals(eClass.getName(), theObject.getClass().getSimpleName());

		Project project = service.getWorkspace().createProject(readerEPackage.getName() + "Instance",
				getProgressMonitor());
		((Resource) project.getChildren().get(0)).getContents().add(theObject);
		assertEquals(theObject, ((Resource) project.getChildren().get(0)).getContents().get(0));
		cleanup();
	}

	@Test
	public void checks() throws CoreException {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		final EPackage dynamicEPackage = createEPackage("fieldland", "1.0");
		final EClass base = ecoreFactory.createEClass();
		base.setName("Base");
		dynamicEPackage.getEClassifiers().add(base);
		final EClass specific = ecoreFactory.createEClass();
		specific.setName("Specific");
		specific.getESuperTypes().add(base);
		dynamicEPackage.getEClassifiers().add(specific);
		final EClass version = ecoreFactory.createEClass();
		version.setName("Version");
		final EReference checkedType = ecoreFactory.createEReference();
		checkedType.setName("value");
		checkedType.setEType(base);
		version.getEStructuralFeatures().add(checkedType);
		final EReference checkedExistance = ecoreFactory.createEReference();
		checkedExistance.setName("name");
		checkedExistance.setEType(EcorePackage.Literals.ESTRING);
		checkedExistance.setResolveProxies(false);
		version.getEStructuralFeatures().add(checkedExistance);
		dynamicEPackage.getEClassifiers().add(version);
		EPackage first = (EPackage) service.make(dynamicEPackage, getProgressMonitor());
		EPackage newDynamicEPackage = service.copy(dynamicEPackage);
		EClass newVersion = (EClass) newDynamicEPackage.getEClassifier(version.getName());
		newVersion.getEStructuralFeature(checkedType.getName())
				.setEType(newDynamicEPackage.getEClassifier(specific.getName()));
		EPackage second = (EPackage) service.replace(dynamicEPackage, newDynamicEPackage, getProgressMonitor());
		assertFalse(service.checkEquals(first, second));
		EPackage newerDynamicEPackage = service.copy(newDynamicEPackage);
		EClass newerVersion = (EClass) newerDynamicEPackage.getEClassifier(version.getName());
		newerVersion.getEStructuralFeatures().remove(newerVersion.getEStructuralFeature(checkedExistance.getName()));
		EPackage third = (EPackage) service.replace(newDynamicEPackage, newerDynamicEPackage, getProgressMonitor());
		assertFalse(service.checkEquals(second, third));
		assertFalse(service.checkEquals(first, third));
		EPackage newestDynamicEPackage = service.copy(newerDynamicEPackage);
		EClass newestVersion = (EClass) newestDynamicEPackage.getEClassifier(version.getName());
		newestVersion.getEStructuralFeatures().add(service.copy(checkedExistance));
		newestVersion.getEStructuralFeature(checkedType.getName())
				.setEType(newestDynamicEPackage.getEClassifier(base.getName()));
		EPackage fourth = (EPackage) service.replace(newerDynamicEPackage, newestDynamicEPackage, getProgressMonitor());
		assertFalse(service.checkEquals(second, fourth));
		assertFalse(service.checkEquals(third, fourth));
		assertTrue(service.checkEquals(first, fourth));
	}

	@Test
	public void async() throws ExecutionException, InterruptedException, CoreException {
		Executor executor = Executors.newFixedThreadPool(2);
		setPackageName("few");
		Future<? extends EPackage> ePackage = createAndSaveEPackage(executor, getProgressMonitor());
		test(ePackage.get(), DEFAULT_ATTR_NAME, true);
		ePackage = updateAndSaveEPackage(ePackage, executor, getProgressMonitor());
		testAnother(ePackage.get(), "b", true);
	}

	@Test
	public void osgiService() throws Exception {
		BundleContext bundleContext = FrameworkUtil.getBundle(ClassMakerService.class).getBundleContext();
		ServiceReference<?> serviceReference = bundleContext.getServiceReference(ClassMakerService.class);
		ClassMakerService tested = (ClassMakerService) bundleContext.getService(serviceReference);
		assertNotNull(tested);
		EPackage ePackage = createEPackage("deeds", "0.2");
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		final String className0 = "Hobby";
		eClass.setName(className0);
		ePackage.getEClassifiers().add(eClass);
		final String className1 = "Work";
		eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(className1);
		ePackage.getEClassifiers().add(eClass);

		ePackage = (EPackage) tested.make(ePackage, getProgressMonitor());
		assertNotNull(ePackage);
		assertObjectClass(className0, ePackage);
		assertObjectClass(className1, ePackage);
		cleanup();
	}

	@Test
	public void metaModel() throws Exception {
		EcoreFactory factory = EcoreFactory.eINSTANCE;
		EPackage metaPackage = createEPackage("meta", "");
		EClass metaClass = factory.createEClass();
		metaClass.setName("MetaObject");
		String nameAttributeName = "name";
		EAttribute nameAttribute = factory.createEAttribute();
		nameAttribute.setName(nameAttributeName);
		nameAttribute.setEType(EcorePackage.Literals.ESTRING);
		metaClass.getEStructuralFeatures().add(nameAttribute);
		EAttribute attributesAttribute = factory.createEAttribute();
		String attributesAttributeName = "attributes";
		attributesAttribute.setName(attributesAttributeName);
		attributesAttribute.setEType(EcorePackage.Literals.ESTRING);
		attributesAttribute.setUpperBound(-1);
		metaClass.getEStructuralFeatures().add(attributesAttribute);
		EOperation op = factory.createEOperation();
		op.setName("createInstance");
		op.setEType(metaClass);
		EAnnotation an = factory.createEAnnotation();
		an.setSource("http://www.eclipse.org/emf/2002/GenModel");
		an.getDetails().put("body", "return <%meta.MetaFactory%>.eINSTANCE.createMetaObject();");
		op.getEAnnotations().add(an);
		EAnnotation invocation = factory.createEAnnotation();
		invocation.setSource(ClassMakerService.INVOCATION_DELEGATE_URI);
		op.getEAnnotations().add(invocation);
		metaClass.getEOperations().add(op);
		metaPackage.getEClassifiers().add(metaClass);
		EAnnotation invocationDelegate = factory.createEAnnotation();
		invocationDelegate.setSource(EcorePackage.eNS_URI);
		invocationDelegate.getDetails().put("invocationDelegates", ClassMakerService.INVOCATION_DELEGATE_URI);
		metaPackage.getEAnnotations().add(invocationDelegate);
		EClass modelClass = factory.createEClass();
		modelClass.setName("MetaModel");
		EAttribute modelName = factory.createEAttribute();
		modelName.setName(nameAttributeName);
		modelName.setEType(EcorePackage.Literals.ESTRING);
		modelClass.getEStructuralFeatures().add(modelName);
		EReference objectsReference = factory.createEReference();
		objectsReference.setName("objects");
		objectsReference.setUpperBound(-1);
		objectsReference.setEType(metaClass);
		modelClass.getEStructuralFeatures().add(objectsReference);
		metaPackage.getEClassifiers().add(modelClass);

		ClassMakerPlugin.getInstance().setTurnOffAutoBuilding(true);
		EPackage ePackage = (EPackage) service.make(metaPackage, getProgressMonitor());
		assertNotNull(ePackage);
		EClass resultClass = (EClass) ePackage.getEClassifier(metaClass.getName());
		EObject metaClassObject = ePackage.getEFactoryInstance().create(resultClass);
		EAttribute resultAttribute = (EAttribute) resultClass.getEStructuralFeature(nameAttribute.getName());
		String domainClassName = "Metaphor";
		metaClassObject.eSet(resultAttribute, domainClassName);
		assertEquals(domainClassName, metaClassObject.eGet(resultAttribute));
		EObject object = ePackage.getEFactoryInstance().create(resultClass);
		EList<?> arguments = ECollections.emptyEList();
		EObject nativeObject = null;
		for (EOperation operation : resultClass.getEAllOperations())
			if (operation.getName().equals(op.getName())) {
				nativeObject = (EObject) EcoreUtil.getInvocationDelegateFactory(operation)
						.createInvocationDelegate(operation).dynamicInvoke((InternalEObject) object, arguments);
			}
		assertEquals(object.eClass(), nativeObject.eClass());
		EObject nativeModel = ePackage.getEFactoryInstance().create(modelClass);
		nativeModel.eSet(
				((EClass) ePackage.getEClassifier(modelClass.getName())).getEStructuralFeature(nameAttributeName),
				"Toolkit");
		EList<String> attributes = ECollections.newBasicEList();
		attributes.add(nameAttributeName);
		nativeObject.eSet(
				((EClass) ePackage.getEClassifier(resultClass.getName())).getEStructuralFeature(nameAttributeName),
				domainClassName);
		nativeObject.eSet(((EClass) ePackage.getEClassifier(resultClass.getName()))
				.getEStructuralFeature(attributesAttributeName), attributes);
		EList<EObject> list = ECollections.newBasicEList();
		list.add(nativeObject);
		nativeModel.eSet(((EClass) ePackage.getEClassifier(modelClass.getName()))
				.getEStructuralFeature(objectsReference.getName()), list);
		IPath qvt = ClassMakerTestsPlugin.getInstance().getStateLocation()
				.append(service.getWorkspace().getContribution(metaPackage).getProjectName() + "/transform.qvto");
		ResourceUtils.writeFile(qvt, "import emf.tools;\n\nmodeltype " + EcorePackage.eINSTANCE.getName() + " uses \""
				+ EcorePackage.eINSTANCE.getNsURI() + "\";\n" + "modeltype " + metaPackage.getName() + " uses \""
				+ metaPackage.getNsURI() + "\";\n\n" + "transformation objectToEcore(in obj : " + metaPackage.getName()
				+ ", out eCore : " + EcorePackage.eINSTANCE.getName() + ");\n\n" + "main() {\n\tobj.rootObjects()["
				+ modelClass.getName() + "].map toEPackage();\n" + "}\n\n" + "mapping " + metaPackage.getName() + "::"
				+ modelClass.getName() + "::toEPackage() : EPackage {\n\tname := self." + modelName.getName()
				+ ".toLowerCase();\n\t" + "nsPrefix := self." + modelName.getName() + ";\n"
				+ "\tnsURI := (\"http://\" + self." + modelName.getName() + " + \"/\").toLowerCase();\n"
				+ "\teClassifiers += self." + objectsReference.getName() + ".map toEClass();" + "\n}\n\n" + "mapping "
				+ metaPackage.getName() + "::" + metaClass.getName() + "::toEClass(): EClass {\n"
				+ "init{\n\tvar eObject := object EObject {};\n}\n" + "\tname := self." + nameAttribute.getName()
				+ ";\n" + "\teStructuralFeatures += self." + attributesAttribute.getName()
				+ ".map toEAttribute(eObject.eClass().ePackage.getEClassifier('EString').oclAsType(EDataType));\n}\n\n"
				+ "mapping String::toEAttribute(in type : EDataType) : EAttribute {\n\tname := self;\n"
				+ "\teType := type;\n}\n");
		EPackage metaModel = (EPackage) service.transform(nativeModel, URI.createFileURI(qvt.toString()));
		assertNotNull(metaModel);
		EPackage resultMetaModel = (EPackage) service.make(metaModel, getProgressMonitor());
		assertNotNull(resultMetaModel);
		EObject o = resultMetaModel.getEFactoryInstance()
				.create((EClass) resultMetaModel.getEClassifier(domainClassName));
		String domainObjectName = "Gear";
		o.eSet(((EClass) resultMetaModel.getEClassifier(domainClassName)).getEStructuralFeature(nameAttributeName),
				domainObjectName);
		assertEquals(domainObjectName, o.eGet(
				((EClass) resultMetaModel.getEClassifier(domainClassName)).getEStructuralFeature(nameAttributeName)));
		cleanup();
		ClassMakerPlugin.getInstance().setTurnOffAutoBuilding(false);
	}

	@Test
	public void requiredPlugins() throws CoreException, InvocationTargetException {
		if (System.getProperty("buildingWithTycho") != null)
			return;
		setPackageName("creator");
		setClassName("Builder");
		EPackage p = createEPackage("0.1");
		EClass c = createEClass();
		EcoreFactory f = EcoreFactory.eINSTANCE;
		EReference r = f.createEReference();
		r.setName("product");
		r.setEType(EcorePackage.Literals.EPACKAGE);
		c.getEStructuralFeatures().add(r);
		EOperation opSet = f.createEOperation();
		opSet.setName("set");
		opSet.setEType(c);
		EParameter pa = f.createEParameter();
		pa.setName("packageName");
		pa.setEType(EcorePackage.Literals.ESTRING);
		opSet.getEParameters().add(pa);
		EAnnotation a = f.createEAnnotation();
		a.setSource("http://www.eclipse.org/emf/2002/GenModel");
		a.getDetails().put("body",
				"EPackage effect=<%org.eclipse.emf.ecore.EcoreFactory%>.eINSTANCE.createEPackage();\n"
						+ "effect.setName(packageName.toLowerCase());\n" + "effect.setNsPrefix(packageName);\n"
						+ "effect.setNsURI(\"http://\" + packageName.toLowerCase() + \"/\");\n"
						+ "setProduct(effect);\nreturn this;");
		opSet.getEAnnotations().add(a);
		a = f.createEAnnotation();
		a.setSource(ClassMakerService.INVOCATION_DELEGATE_URI);
		opSet.getEAnnotations().add(a);
		c.getEOperations().add(opSet);
		EOperation opClass = f.createEOperation();
		opClass.setName("newClass");
		opClass.setEType(c);
		pa = f.createEParameter();
		pa.setName("name");
		pa.setEType(EcorePackage.Literals.ESTRING);
		opClass.getEParameters().add(pa);
		a = f.createEAnnotation();
		a.setSource("http://www.eclipse.org/emf/2002/GenModel");
		a.getDetails().put("body", "EClass effect=<%org.eclipse.emf.ecore.EcoreFactory%>.eINSTANCE.createEClass();\n"
				+ "effect.setName(name);\ngetProduct().getEClassifiers().add(effect);\nreturn this;");
		opClass.getEAnnotations().add(a);
		a = f.createEAnnotation();
		a.setSource(ClassMakerService.INVOCATION_DELEGATE_URI);
		opClass.getEAnnotations().add(a);
		c.getEOperations().add(opClass);
		EOperation opBuild = f.createEOperation();
		opBuild.setName("build");
		opBuild.setEType(EcorePackage.Literals.EPACKAGE);
		a = f.createEAnnotation();
		a.setSource("http://www.eclipse.org/emf/2002/GenModel");
		a.getDetails().put("body",
				"return (<%org.eclipse.emf.ecore.EPackage%>) <%org.enterprisedomain.classmaker.core.ClassMakerPlugin%>"
						+ ".getClassMaker().make(getProduct(), <%org.enterprisedomain.classmaker.core.ClassMakerPlugin%>"
						+ ".getProgressMonitor());");
		opBuild.getEAnnotations().add(a);
		a = f.createEAnnotation();
		a.setSource(ClassMakerService.INVOCATION_DELEGATE_URI);
		opBuild.getEAnnotations().add(a);
		c.getEOperations().add(opBuild);
		a = f.createEAnnotation();
		a.setSource(EcorePackage.eNS_URI);
		a.getDetails().put("invocationDelegates", ClassMakerService.INVOCATION_DELEGATE_URI);
		p.getEAnnotations().add(a);
		EDataType e = f.createEDataType();
		e.setName("CoreException");
		e.setInstanceTypeName(CoreException.class.getName());
		opBuild.getEExceptions().add(e);
		p.getEClassifiers().add(e);
		p.getEClassifiers().add(c);

		EList<String> dependencies = ECollections.newBasicEList();
		dependencies.add(ClassMakerPlugin.PLUGIN_ID);
		Blueprint b = service.createBlueprint();
		b.setDynamicModel(p);
		b.getDependencies().addAll(dependencies);
		ClassMakerPlugin.getInstance().setTurnOffAutoBuilding(true);
		EPackage creator = (EPackage) service.make(b, getProgressMonitor());
		assertNotNull(creator);
		EObject builder = (EObject) creator.getEFactoryInstance().create((EClass) creator.getEClassifier(c.getName()));
		EList<Object> args = ECollections.newBasicEList();
		args.add("world");
		builder = (EObject) service.invoke(opSet, builder, args);
		args = ECollections.newBasicEList();
		String n = "Picture";
		args.add(n);
		builder = (EObject) service.invoke(opClass, builder, args);
		args = ECollections.newBasicEList();
		EPackage result = (EPackage) service.invoke(opBuild, builder, args);
		assertNotNull(result);
		EObject o = result.getEFactoryInstance().create((EClass) result.getEClassifier(n));
		assertEquals(n, o.eClass().getName());
		assertEquals(o.eClass().getName(), o.getClass().getSimpleName());
		ClassMakerPlugin.getInstance().setTurnOffAutoBuilding(false);
		cleanup();
	}

	@Test
	public void editor() {
		setPackageName("test");
		setClassName("Edited");
		setAttributeName("t");
		setAttributeType(EcorePackage.Literals.ESTRING);
		EPackage e = createEPackage("1.0");
		e.getEClassifiers().add(createEClass());
		((EClass) e.getEClassifier(getClassName())).getEStructuralFeatures().add(createEAttribute());
		Blueprint b = ClassMakerFactory.eINSTANCE.createBlueprint();
		b.setEdit(true);
		b.setEditor(true);
		b.setDynamicModel(e);
		try {
			EPackage p = (EPackage) service.make(b, getProgressMonitor());
			test(p, attributeName, "test");
		} catch (CoreException ex) {
			fail(ex.getLocalizedMessage());
		}
	}

	@Test
	public void update() throws OperationCanceledException, InterruptedException, CoreException, ExecutionException {
		setPackageName("updatable");
		setClassName("Same");
		EcoreFactory f = EcoreFactory.eINSTANCE;
		p = createEPackage("0.1");
		final EClass cl = f.createEClass();
		cl.setName(getClassName());
		setAttributeName(DEFAULT_ATTR_NAME);
		final EAttribute a = f.createEAttribute();
		a.setName(getAttributeName());
		a.setEType(EcorePackage.Literals.EJAVA_OBJECT);
		cl.getEStructuralFeatures().add(a);
		p.getEClassifiers().add(cl);
		ClassMakerPlugin.getInstance().setTurnOffAutoBuilding(true);
		EPackage e0 = (EPackage) service.make(p, getProgressMonitor());
		assertEquals("http://" + e0.getName() + "/0.1", e0.getNsURI());
		EClass c0 = (EClass) e0.getEClassifier(cl.getName());
		o = e0.getEFactoryInstance().create(c0);
		assertEquals(c0.getName(), o.getClass().getSimpleName());
		o.eSet(c0.getEStructuralFeature(a.getName()), "test");
		assertEquals("test", o.eGet(c0.getEStructuralFeature(a.getName())));
		final Version v1 = service.getWorkspace().getContribution(p).getVersion();
		final Registry packageRegistry = service.getWorkspace().getResourceSet().getPackageRegistry();
		assertNotNull(packageRegistry.getEPackage(p.getNsURI()));

		EPackage p2 = updateEPackage(p, "0.2");
		final EAttribute b = f.createEAttribute();
		setAttributeName("b");
		b.setName(getAttributeName());
		b.setEType(EcorePackage.Literals.EINT);
		((EClass) p2.getEClassifier(cl.getName())).getEStructuralFeatures().add(b);
		EPackage e1 = (EPackage) service.replace(p, p2, getProgressMonitor());
		assertEquals("http://" + e1.getName() + "/0.2", e1.getNsURI());
		EClass cla = (EClass) e1.getEClassifier(cl.getName());
		o = e1.getEFactoryInstance().create(cla);
		assertEquals(cla.getName(), o.getClass().getSimpleName());
		o.eSet(cla.getEStructuralFeature(a.getName()), "test");
		assertEquals("test", o.eGet(cla.getEStructuralFeature(a.getName())));
		o.eSet(cla.getEStructuralFeature(b.getName()), 5);
		assertEquals(5, o.eGet(cla.getEStructuralFeature(b.getName())));
		Version v2 = service.getWorkspace().getContribution(p2).getVersion();
		assertTrue(v2.compareTo(v1) > 0);
		EClass c1 = (EClass) e0.getEClassifier(cl.getName());
		o = e0.getEFactoryInstance().create(c1);
		assertEquals(c1.getName(), o.getClass().getSimpleName());
		o.eSet(c1.getEStructuralFeature(a.getName()), "test");
		assertEquals("test", o.eGet(c1.getEStructuralFeature(a.getName())));
		assertNotNull(packageRegistry.getEPackage(p2.getNsURI()));

		service.getWorkspace().getContribution(service.computeProjectName(p.getName())).checkout(v1);
		EPackage p3 = updateEPackage(p2, "0.3");
		EPackage e2 = (EPackage) service.replace(p2, p3, true, getProgressMonitor());
		assertEquals("http://" + e2.getName() + "/0.3", e2.getNsURI());
		cleanup();
		ClassMakerPlugin.getInstance().setTurnOffAutoBuilding(false);
	}

	@Test
	public void downgrade() throws OperationCanceledException, InterruptedException, ExecutionException, CoreException {
		setPackageName("pack");
		setClassName("C");
		setAttributeName("x");
		setAttributeType(EcorePackage.Literals.EJAVA_OBJECT);
		ClassMakerPlugin.getInstance().setTurnOffAutoBuilding(true);
		EPackage p = createAndTestEPackage(getProgressMonitor());
		Contribution c = service.getWorkspace().getContribution(p, Stage.LOADED);
		p = (EPackage) c.getDomainModel().getDynamic();
		Version v = c.getVersion();

		EPackage p2 = updateEPackage(p, "1");
		EClass clazz = (EClass) p2.getEClassifier(getClassName());
		clazz.getEStructuralFeatures().remove(clazz.getEStructuralFeature(getAttributeName()));
		EPackage g = (EPackage) service.replace(p, p2, getProgressMonitor());
		EClass gClazz = (EClass) g.getEClassifier(getClassName());
		EObject o = g.getEFactoryInstance().create(gClazz);
		assertNull(gClazz.getEStructuralFeature(getAttributeName()));
		assertEquals(getClassName(), o.getClass().getSimpleName());

		EPackage p1 = service.copy(p);
		EClass clazz1 = (EClass) p1.getEClassifier(getClassName());
		clazz1.getEStructuralFeatures().remove(clazz1.getEStructuralFeature(DEFAULT_ATTR_NAME));
		g = (EPackage) service.replace(p, p1, v, getProgressMonitor());
		gClazz = (EClass) g.getEClassifier(getClassName());
		o = g.getEFactoryInstance().create(gClazz);
		EAttribute x = (EAttribute) gClazz.getEStructuralFeature(getAttributeName());
		assertNotNull(x);
		assertEquals(getAttributeType(), x.getEType());
		assertNull(gClazz.getEStructuralFeature(DEFAULT_ATTR_NAME));
		assertEquals(getClassName(), o.getClass().getSimpleName());
		cleanup();
		ClassMakerPlugin.getInstance().setTurnOffAutoBuilding(false);
	}

	@Test
	public void importModel() throws IOException, CoreException {
		setPackageName("extra");
		setClassName("Deluxe");
		EPackage p0 = createEPackage("1.0");
		EClass c = createEClass();
		EAttribute a = EcoreFactory.eINSTANCE.createEAttribute();
		a.setName("number");
		a.setEType(EcorePackage.Literals.EINT);
		c.getEStructuralFeatures().add(a);
		p0.getEClassifiers().add(c);
		URI resourceURI = URI.createFileURI(ClassMakerTestsPlugin.getInstance().getStateLocation().append(p0.getName())
				.append(p0.getName()).addFileExtension("xmi").toString());
		ResourceSet resourceSet = service.getWorkspace().getResourceSet();
		Resource resource0 = resourceSet.createResource(resourceURI);
		resource0.getContents().add(p0);
		resource0.save(Collections.emptyMap());
		EPackage r = (EPackage) service.make(p0, getProgressMonitor());
		assertNotNull(r);

		String modelName = "MetaModel";
		Resource resource1 = resourceSet.getResource(URI.createPlatformPluginURI(ClassMakerTestsPlugin.PLUGIN_ID
				+ IPath.SEPARATOR + "model" + IPath.SEPARATOR + CodeGenUtil.safeName(modelName) + ".ecore", false),
				true);
		resource1.load(new HashMap<String, String>());
		EPackage p1 = (EPackage) resource1.getContents().get(0);
		EPackage ePackage = (EPackage) service.make(p1, getProgressMonitor());
		assertNotNull(ePackage);
		assertEquals(modelName, ePackage.getNsPrefix());
	}

	@Test
	public void recreate() throws CoreException, OperationCanceledException, InterruptedException, ExecutionException {
		setPackageName("dir");
		setClassName("File");
		setAttributeName("data");
		setAttributeType(EcorePackage.Literals.EJAVA_OBJECT);
		Contribution c = service.getWorkspace().getContribution(createAndTestEPackage(getProgressMonitor()),
				Stage.LOADED);
		c.delete(getProgressMonitor());
		createAndTestEPackage(getProgressMonitor());
		cleanup();
	}

	@Test
	public void versions() throws OperationCanceledException, InterruptedException, ExecutionException, CoreException {
		setPackageName("some");
		setClassName("C");
		setAttributeName("c");
		setAttributeType(EcorePackage.Literals.EJAVA_OBJECT);
		EPackage p = createAndTestEPackage(getProgressMonitor());
		Contribution c = service.getWorkspace().getContribution(p, Stage.LOADED);
		Version oldVersion = c.getRevision().getVersion();
		Version newVersion = c.nextVersion();
		EPackage g = (EPackage) service.replace(p, p, newVersion, getProgressMonitor());
		test(g, "c", new Object());
		g = (EPackage) service.replace(p, p, oldVersion, getProgressMonitor());
		assertNotNull(g);
		test(g, "c", new Object());
		cleanup();
	}

	@Test
	public void changeModel()
			throws OperationCanceledException, InterruptedException, ExecutionException, CoreException {
		setPackageName("one");
		setClassName("T");
		setAttributeName("t");
		setAttributeType(EcorePackage.Literals.EJAVA_OBJECT);
		EPackage p = createAndTestAPI(getProgressMonitor());
		setPackageName("another");
		EPackage p2 = updateEPackage(p, "1");
		p2.setName("another");
		p2.setNsPrefix("another");
		EClass cl = (EClass) p2.getEClassifier(getClassName());
		setClassName("P");
		cl.setName(getClassName());
		assertNotNull(cl.getEStructuralFeature(getAttributeName()));
		testAPIUpdate(p, p2, "t", EcorePackage.Literals.EJAVA_OBJECT, new Object(), getProgressMonitor());
		cleanup();
	}

	@Test
	public void immutable() throws Exception {
		setPackageName("immutable");
		EPackage eP = createEPackage("0.0.1");
		setClassName("C");
		EClass eC = createEClass();
		setAttributeName("C");
		setAttributeType(EcorePackage.Literals.EJAVA_OBJECT);
		final EAttribute at = createEAttribute();
		EcoreUtil.setSuppressedVisibility(at, EcoreUtil.SET, true);
		at.setChangeable(false);
		eC.getEStructuralFeatures().add(at);
		eP.getEClassifiers().add(eC);
		final Contribution c = service.getWorkspace().createContribution(eP, getProgressMonitor());
		Customizer customizer = new CustomizerImpl() {

			@Override
			public Object customize(EList<Object> args) {
				GenModel genModel = ((GenModel) args.get(1));
				genModel.setSuppressInterfaces(false);
				return null;
			}

		};
		c.getCustomizers().put(ClassMakerService.Stages
				.lookup(ClassMakerService.Stages.ID_PREFIX + "project.generation.genmodel.setup"), customizer);

		final Semaphore complete = new Semaphore(0);
		CompletionListener l = new CompletionListenerImpl() {

			@Override
			public void completed(Project result) throws Exception {
				try {
					EPackage e = null;
					Class<?> cl = null;
					e = (EPackage) result.getDomainModel().getGenerated();
					cl = TestEnterpriseDomain.class.getClassLoader().loadClass(getPackageName() + "." + getClassName());
					Method getMethod0 = cl.getMethod("get" + at.getName(), new Class<?>[] {});
					EClass ec = (EClass) e.getEClassifier(getClassName());
					EObject eo = e.getEFactoryInstance().create(ec);
					Object o = new Object();
					eo.eSet(ec.getEStructuralFeature(at.getName()), o);
					assertEquals(o, getMethod0.invoke(eo, new Object[] {}));
					try {
						cl.getMethod("set" + at.getName(), Object.class);
					} catch (NoSuchMethodException ex) {
						return;
					}
				} catch (Exception e) {
					fail(e.getLocalizedMessage());
				} finally {
					complete.release();
				}
				assertTrue(false);
			}

		};
		c.addCompletionListener(l);
		c.make(getProgressMonitor());
		complete.acquire();
		c.removeCompletionListener(l);
		cleanup();
	}

	@Test
	public void package_() throws OperationCanceledException, InterruptedException, ExecutionException, CoreException {
		setPackageName("package");
		createAndTestEPackage(getProgressMonitor());
		cleanup();
	}
}
