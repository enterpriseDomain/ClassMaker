package org.enterprisedomain.sample;

import javax.inject.Inject;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.enterprisedomain.classmaker.ClassPlant;
import org.osgi.framework.FrameworkUtil;

public class Invoker implements Runnable {

	@Inject
	private ClassPlant m;

	private EPackage ePackage;

	private void inject() {
		IEclipseContext context = EclipseContextFactory
				.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext());
		ContextInjectionFactory.inject(this, context);
	}

	public void run() {
		inject();
		EPackage p = EcoreFactory.eINSTANCE.createEPackage();
		p.setName("ho");
		p.setNsPrefix("ho");
		p.setNsURI("http://ho/0.1");
		final EClass c = EcoreFactory.eINSTANCE.createEClass();
		c.setName("Obj");
		final EAttribute a = EcoreFactory.eINSTANCE.createEAttribute();
		a.setName("p");
		a.setEType(EcorePackage.Literals.ESTRING);
		c.getEStructuralFeatures().add(a);
		p.getEClassifiers().add(c);
		try {
			EPackage ePackage = m.produce(p);
			EClass e = (EClass) ePackage.getEClassifier(c.getName());
			System.out.println(e.getName());
			EObject o = ePackage.getEFactoryInstance().create(e);
			EAttribute ea = (EAttribute) e.getEStructuralFeature(a.getName());
			o.eSet(ea, "Lorem ipsum");
			Object v = o.eGet(ea);
			System.out.println(v);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
