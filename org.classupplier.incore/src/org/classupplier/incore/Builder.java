package org.classupplier.incore;

import javax.inject.Inject;

import org.classupplier.ClasSupplier;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;

public class Builder {

	@Inject
	private ClasSupplier producer;

	private EObject knowledge;

	private Builder parent;

	public Builder() {
		this(null, null);
		prepare();
	}

	public Builder(EObject knowledge, Builder parent) {
		this.knowledge = knowledge;
		this.parent = parent;
	}

	public Builder group(String name) {
		EPackage group = EcoreFactory.eINSTANCE.createEPackage();
		named(group, name);
		group.setNsPrefix(name);
		group.setNsURI("http://" + name + "/0.1");
		return new Builder(group, this);
	}

	public Builder clazz(String name) {
		EClass c = EcoreFactory.eINSTANCE.createEClass();
		named(c, name);
		((EPackage) knowledge).getEClassifiers().add(c);
		return new Builder(c, this);
	}

	public Builder attribute(String name, EDataType type) {
		EAttribute a = EcoreFactory.eINSTANCE.createEAttribute();
		named(a, name);
		a.setEType(type);
		((EClass) knowledge).getEStructuralFeatures().add(a);
		return new Builder(a, this);
	}

	private void named(ENamedElement n, String name) {
		n.setName(name);
	}

	protected EPackage build(EObject knowledge) {
		if (parent == null && knowledge instanceof EPackage)
			return producer.supply((EPackage) knowledge);
		else
			return parent.build(this.knowledge);
	}

	public EPackage build() {
		return build(knowledge);
	}

	private void prepare() {
		IEclipseContext context = EclipseContextFactory
				.getServiceContext(Activator.getContext());
		ContextInjectionFactory.inject(this, context);
	}

}
