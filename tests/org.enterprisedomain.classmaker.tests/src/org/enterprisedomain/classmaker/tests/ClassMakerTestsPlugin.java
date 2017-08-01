package org.enterprisedomain.classmaker.tests;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class ClassMakerTestsPlugin extends Plugin {
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
