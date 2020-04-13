/**
 * Copyright 2019 Kyrill Zotkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.workbench;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator extends AbstractUIPlugin {

	private static BundleContext context;
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

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		plugin = this;
		tracker = new ServiceTracker<ClassMakerService, ClassMakerServiceImpl>(context, ClassMakerService.class, null);
		tracker.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		tracker.close();
		plugin = null;
		Activator.context = null;
	}

	public static void log(CoreException e) {
		plugin.getLog().log(e.getStatus());
	}

	public static void log(Throwable e) {
		plugin.getLog().log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
	}

}
