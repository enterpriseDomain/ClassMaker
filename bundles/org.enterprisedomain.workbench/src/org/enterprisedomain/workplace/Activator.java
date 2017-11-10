package org.enterprisedomain.workplace;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.enterprisedomain.workplace"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private static ServiceTracker<ClassMakerService, ClassMakerServiceImpl> tracker;

	/**
	 * ClassMaker facade service.
	 * 
	 * @return ClassMakerService service instance
	 */
	public static ClassMakerService getClassMaker() {
		return tracker.getService();
	}

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		tracker = new ServiceTracker<ClassMakerService, ClassMakerServiceImpl>(context, ClassMakerService.class, null);
		tracker.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		tracker.close();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void log(CoreException e) {
		plugin.getLog().log(e.getStatus());
	}

	public static void log(Throwable e) {
		plugin.getLog().log(ClassMakerPlugin.createErrorStatus(e));
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative
	 * path
	 *
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
