package org.enterprisedomain.ecp.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator extends Plugin implements BundleActivator {
	private static Activator instance;
	private static ServiceTracker<ClassMakerService, ClassMakerServiceImpl> classMaker;

	public static ClassMakerService getClassMaker() {
		if (classMaker != null)
			return classMaker.getService();
		return null;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		instance = this;
		classMaker = new ServiceTracker<ClassMakerService, ClassMakerServiceImpl>(context, ClassMakerService.class,
				null);
		classMaker.open();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		classMaker.close();
		classMaker = null;
		instance = null;
	}

	public static void log(CoreException e) {
		instance.getLog().log(e.getStatus());
	}

	public static void log(Throwable e) {
		instance.getLog().log(ClassMakerPlugin.createErrorStatus(e));
	}

}