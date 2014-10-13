package org.classupplier.incore;

import javax.inject.Inject;

import org.classupplier.Artifact;
import org.classupplier.ClassSupplier;
import org.classupplier.SupplyNotifier;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

public class Invoker implements Runnable {

	@Inject
	private ClassSupplier supplier;

	private void prepare() {
		IEclipseContext context = EclipseContextFactory
				.getServiceContext(Activator.getContext());
		ContextInjectionFactory.inject(this, context);
	}

	public void run() {
		prepare();
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
		Artifact artifact = supplier.getWorkspace().createArtifact(p);
		artifact.eAdapters().add(new SupplyNotifier() {

			@Override
			protected void supplyCompleted(EPackage ePackage) {
				EClass e = (EClass) ePackage.getEClassifier(c.getName());
				System.out.println(e.getName());
				EObject o = ePackage.getEFactoryInstance().create(e);
				EAttribute ea = (EAttribute) e.getEStructuralFeature(a
						.getName());
				o.eSet(ea, "Lorem ipsum");
				Object v = o.eGet(ea);
				System.out.println(v);
			}
		});
		artifact.produce(new CodeGenUtil.EclipseUtil.StreamProgressMonitor(
				System.out));
	}
}
