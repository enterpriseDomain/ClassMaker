package org.classsupplier.impl;

import org.classsupplier.ClassSupplier;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class OSGi extends Plugin {

	public static final String PLUGIN_ID = "org.classsupplier";

	public static final String NATURE_ID = PLUGIN_ID + '.' + "domainNature";

	public static final String MODEL_FOLDER_PREF_KEY = "modelFolder";

	public static final String RESOURCE_EXT_PREF_KEY = "resourceExt";

	private static OSGi instance;

	private static ServiceTracker<ClassSupplier, ClassSupplierImpl> service;

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
		instance = null;
	}

}
