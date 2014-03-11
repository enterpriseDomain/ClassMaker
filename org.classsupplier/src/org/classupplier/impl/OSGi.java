package org.classupplier.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.classupplier.ClasSupplier;
import org.classupplier.ClasSupplierFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class OSGi extends Plugin {

	public static final String PLUGIN_ID = "org.classupplier";

	public static final String NATURE_ID = PLUGIN_ID + '.' + "domainNature";

	public static final String MODEL_FOLDER_PREF_KEY = "modelFolder";

	public static final String RESOURCE_EXT_PREF_KEY = "resourceExt";

	private static OSGi instance;

	private static ServiceTracker<ClasSupplier, ClasSupplierImpl> service;

	private ServiceRegistration<ClasSupplier> reg;

	public static OSGi getInstance() {
		return instance;
	}

	public static ClasSupplier getClasSupplier() {
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
		reg = context.registerService(ClasSupplier.class,
				ClasSupplierFactory.eINSTANCE.createClasSupplier(), null);
		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put(IContextFunction.SERVICE_CONTEXT_KEY,
				ClasSupplier.class.getName());
		context.registerService(IContextFunction.SERVICE_NAME,
				new ServiceFactory(), properties);
		service = new ServiceTracker<ClasSupplier, ClasSupplierImpl>(context,
				ClasSupplier.class, null);
		service.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		getClasSupplier().getWorkspace().save(new NullProgressMonitor());
		service.close();
		reg.unregister();
		instance = null;

	}
}
