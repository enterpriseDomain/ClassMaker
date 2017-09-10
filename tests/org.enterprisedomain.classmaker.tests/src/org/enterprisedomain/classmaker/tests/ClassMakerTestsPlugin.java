package org.enterprisedomain.classmaker.tests;

import org.eclipse.core.runtime.Plugin;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.osgi.framework.BundleContext;

public class ClassMakerTestsPlugin extends Plugin {

	public static String PLUGIN_ID = ClassMakerPlugin.PLUGIN_ID + ".tests";

	private static ClassMakerTestsPlugin instance;

	public static ClassMakerTestsPlugin getInstance() {
		return instance;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		instance = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		instance = null;
	}

}
