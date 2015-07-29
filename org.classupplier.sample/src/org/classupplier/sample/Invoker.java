package org.classupplier.sample;

import javax.inject.Inject;

import org.classupplier.ClassSupplier;
import org.classupplier.Contribution;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
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
import org.eclipse.equinox.concurrent.future.IFuture;
import org.osgi.framework.FrameworkUtil;

public class Invoker implements Runnable {

	@Inject
	private ClassSupplier supplier;

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
		Contribution contribution = supplier.getWorkspace().createContribution(p);
		EPackage ePackage;
		try {
			IFuture<? extends EPackage> result = contribution
					.construct(new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out));
			ePackage = result.get();
			while (!result.isDone()) {
				Thread.yield();
			}
			IStatus status = result.getStatus();
			if (!status.isOK()) {
				Platform.getLog(FrameworkUtil.getBundle(this.getClass())).log(status);
			}
			EClass e = (EClass) ePackage.getEClassifier(c.getName());
			System.out.println(e.getName());
			EObject o = ePackage.getEFactoryInstance().create(e);
			EAttribute ea = (EAttribute) e.getEStructuralFeature(a.getName());
			o.eSet(ea, "Lorem ipsum");
			Object v = o.eGet(ea);
			System.out.println(v);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
