package org.classsupplier.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.classsupplier.ClassSupplier;
import org.classsupplier.ClassSupplierFactory;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class OSGi extends Plugin {

	public static final String PLUGIN_ID = "org.classsupplier";

	public static final String NATURE_ID = PLUGIN_ID + '.' + "domainNature";

	public static final String MODEL_FOLDER_PREF_KEY = "modelFolder";

	public static final String RESOURCE_EXT_PREF_KEY = "resourceExt";

	private static OSGi instance;

	private static ServiceTracker<ClassSupplier, ClassSupplierImpl> service;

	private ServiceRegistration<ClassSupplier> reg;

	public static OSGi getInstance() {
		return instance;
	}

	public static ClassSupplier getClassSupplier() {
		return service.getService();
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
		reg = context.registerService(ClassSupplier.class,
				ClassSupplierFactory.eINSTANCE.createClassSupplier(), null);
		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put(IContextFunction.SERVICE_CONTEXT_KEY,
				ClassSupplier.class.getName());
		context.registerService(IContextFunction.SERVICE_NAME,
				new ServiceFactory(), properties);
		service = new ServiceTracker<ClassSupplier, ClassSupplierImpl>(context,
				ClassSupplier.class, null);
		service.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		service.close();
		reg.unregister();
		instance = null;

	}

}
