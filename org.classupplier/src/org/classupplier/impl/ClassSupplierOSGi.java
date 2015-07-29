package org.classupplier.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.classupplier.ClassSupplier;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class ClassSupplierOSGi extends Plugin {

	public static final String PLUGIN_ID = "org.classupplier";

	public static final String NATURE_ID = PLUGIN_ID + '.' + "userDomainNature";

	public static final String MODEL_FOLDER_PREF_KEY = "modelFolder";

	public static final String MODEL_RESOURCE_EXT_PREF_KEY = "resourceExt";

	private static ClassSupplierOSGi instance;

	private static ServiceTracker<ClassSupplier, ClassSupplierImpl> tracker;

	private ServiceRegistration<ClassSupplier> reg;

	public static ClassSupplierOSGi getInstance() {
		return instance;
	}

	/**
	 * Only for internal use.
	 * 
	 * @return ClassSupplier service instance
	 */
	public static ClassSupplier getClassSupplier() {
		return tracker.getService();
	}

	public static <T> T getService(String serviceClass) {
		if (getInstance() == null)
			return null;
		BundleContext context = getInstance().getBundle().getBundleContext();
		return getService(serviceClass, context);
	}

	public static <T> T getService(String serviceClass, BundleContext context) {
		ServiceTracker<T, T> tracker = new ServiceTracker<T, T>(context, serviceClass, null);
		tracker.open();
		T result = tracker.getService();
		tracker.close();
//		ServiceReference<T> reference = (ServiceReference<T>) context.getServiceReference(serviceClass);
//		T result = context.getService(reference);
//		context.ungetService(reference);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		instance = this;
		reg = context.registerService(ClassSupplier.class, new ClassSupplierImpl(), null);
		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put(IContextFunction.SERVICE_CONTEXT_KEY, ClassSupplier.class.getName());
		context.registerService(IContextFunction.SERVICE_NAME, new ServiceFactory(), properties);
		tracker = new ServiceTracker<ClassSupplier, ClassSupplierImpl>(context, ClassSupplier.class, null);
		tracker.open();
		getClassSupplier().getWorkspace().init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		IProgressMonitor monitor = new NullProgressMonitor();
		Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, monitor);
		ResourcesPlugin.getWorkspace().save(true, monitor);
		tracker.close();
		reg.unregister();
		instance = null;

	}

	public static IStatus createOKStatus(String message) {
		return new Status(IStatus.OK, ClassSupplierOSGi.PLUGIN_ID, message);
	}

	public static IStatus createWarningStatus(String message) {
		return new Status(IStatus.WARNING, ClassSupplierOSGi.PLUGIN_ID, message);
	}

	public static IStatus createWarningStatus(Throwable exception) {
		return new Status(IStatus.WARNING, ClassSupplierOSGi.PLUGIN_ID, exception.getLocalizedMessage(), exception);
	}

	public static IStatus createErrorStatus(String message) {
		return new Status(IStatus.ERROR, ClassSupplierOSGi.PLUGIN_ID, message);
	}

	public static IStatus createErrorStatus(Throwable exception) {
		return new Status(IStatus.ERROR, ClassSupplierOSGi.PLUGIN_ID, exception.getLocalizedMessage(), exception);
	}

	public static String bundleStateAsString(int state) {
		switch (state) {
		case Bundle.ACTIVE:
			return "ACTIVE";
		case Bundle.INSTALLED:
			return "INSTALLED";
		case Bundle.RESOLVED:
			return "RESOLVED";
		case Bundle.STARTING:
			return "STARTING";
		case Bundle.STOPPING:
			return "STOPPING";
		case Bundle.UNINSTALLED:
			return "UNINSTALLED";
		default:
			return "unknown bundle state: " + state;
		}
	}

}
