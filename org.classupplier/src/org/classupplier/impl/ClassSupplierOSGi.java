package org.classupplier.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.classupplier.ClassSupplier;
import org.classupplier.ClassSupplierFactory;
import org.classupplier.Infrastructure;
import org.classupplier.di.ServiceFactory;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.resources.ISavedState;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class ClassSupplierOSGi extends Plugin {

	public static final String PLUGIN_ID = "org.classupplier";

	public static final String NATURE_ID = PLUGIN_ID + '.' + "userDomainNature";

	public static final String MODEL_FOLDER_PREF_KEY = "modelFolder";

	public static final String TARGET_FOLDER_PREF_KEY = "targetFolder";
	
	public static final String MODEL_RESOURCE_EXT_PREF_KEY = "resourceExt";
	
	public static final String WORKSPACE_SAVE_FILE = "definition";	

	private static ClassSupplierOSGi instance;

	private static ServiceTracker<ClassSupplier, ClassSupplierImpl> tracker;

	private ServiceRegistration<ClassSupplier> reg;

	public static ClassSupplierOSGi getInstance() {
		return instance;
	}

	public static ClassSupplier getClassSupplier() {
		return tracker.getService();
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
		tracker = new ServiceTracker<ClassSupplier, ClassSupplierImpl>(context,
				ClassSupplier.class, null);
		tracker.open();
		ISaveParticipant saveParticipant = new InfrastractureSaveParticipant();
		ISavedState lastState = ResourcesPlugin.getWorkspace()
				.addSaveParticipant(PLUGIN_ID, saveParticipant);
		if (lastState == null)
			return;
		IPath location = lastState.lookup(new Path(WORKSPACE_SAVE_FILE));
		if (location == null)
			return;
		Resource resource = getClassSupplier()
				.getWorkspace()
				.getResourceSet()
				.getResource(
						ResourceUtil.workspaceResourceURI(location.toString()),
						true);
		if (!resource.getContents().isEmpty())
			getClassSupplier().getWorkspace().init(
					(Infrastructure) resource.getContents().get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		getClassSupplier().getWorkspace().save(new NullProgressMonitor());
		tracker.close();
		reg.unregister();
		instance = null;

	}
}
