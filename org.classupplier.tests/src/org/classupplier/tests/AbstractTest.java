package org.classupplier.tests;

import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.classupplier.ClassSupplier;
import org.classupplier.Contribution;
import org.classupplier.Phase;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;

public abstract class AbstractTest {

	protected static ClassSupplier service;

	private static CountDownLatch latch = new CountDownLatch(1);

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
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = ecoreFactory.createEPackage();
		ePackage.setName(name);
		ePackage.setNsPrefix(name);
		ePackage.setNsURI("http://" + name + "/" + version);
		return ePackage;
	}

	protected EPackage updateEPackage(EPackage ePackage, String version) {
		EPackage result = EcoreUtil.copy(ePackage);
		result.setNsURI("http://" + ePackage.getName() + "/" + version);
		return result;
	}

	protected IProgressMonitor getProgressMonitor() {
		return new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out);
	}

}
