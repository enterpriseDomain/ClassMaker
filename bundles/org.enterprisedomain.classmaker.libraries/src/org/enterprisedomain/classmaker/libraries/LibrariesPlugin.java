package org.enterprisedomain.classmaker.libraries;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class LibrariesPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.enterprisedomain.classmaker.libraries"; //$NON-NLS-1$

	// The shared instance
	private static LibrariesPlugin plugin;

	/**
	 * The constructor
	 */
	public LibrariesPlugin() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static LibrariesPlugin getDefault() {
		return plugin;
	}

	public static String getPluginDir(String pluginId) {
		/* get bundle with the specified id */
		Bundle bundle = Platform.getBundle(pluginId);
		if (bundle == null)
			throw new RuntimeException("Could not resolve plugin: " + pluginId + "\r\n"
					+ "Probably the plugin has not been correctly installed.\r\n"
					+ "Running eclipse from shell with -clean option may rectify installation.");

		/* resolve Bundle::getEntry to local URL */
		URL pluginURL = null;
		try {
			pluginURL = FileLocator.resolve(bundle.getEntry("/"));
		} catch (IOException e) {
			throw new RuntimeException("Could not get installation directory of the plugin: " + pluginId);
		}
		String pluginInstallDir = pluginURL.getPath().trim();
		if (pluginInstallDir.length() == 0)
			throw new RuntimeException("Could not get installation directory of the plugin: " + pluginId);

		/*
		 * since path returned by URL::getPath starts with a forward slash, that is not
		 * suitable to run commandlines on Windows-OS, but for Unix-based OSes it is
		 * needed. So strip one character for windows. There seems to be no other clean
		 * way of doing this.
		 */
		if (Platform.getOS().compareTo(Platform.OS_WIN32) == 0)
			pluginInstallDir = pluginInstallDir.substring(1);

		return pluginInstallDir;
	}
}
