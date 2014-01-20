package org.classsupplier.incore;

import javax.inject.Inject;

import org.classsupplier.ClassSupplier;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;

public class Builder {

	@Inject
	private ClassSupplier producer;

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
		group.setName(name);
		group.setNsPrefix(name);
		group.setNsURI("http://" + name + "/0.1");
		return new Builder(group, this);
	}

	public Builder clazz(String name) {
		EClass c = EcoreFactory.eINSTANCE.createEClass();
		c.setName(name);
		((EPackage) knowledge).getEClassifiers().add(c);
		return new Builder(c, this);
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
