package org.classupplier.test;

import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.classupplier.ClasSupplier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.Before;

public abstract class AbstractTests {

	protected static ClasSupplier service;

	private static CountDownLatch latch = new CountDownLatch(1);

	public void setReference(ClasSupplier dependency) {
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

	protected EPackage createEPackage(String name, String version) {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = ecoreFactory.createEPackage();
		ePackage.setName(name);
		ePackage.setNsPrefix(name);
		ePackage.setNsURI("http://" + name + "/" + version);
		return ePackage;
	}
	
}
