/**
 * Copyright 2012-2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.ecp;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Plugin;
import org.enterprisedomain.classmaker.ClassMakerPlant;
import org.enterprisedomain.classmaker.impl.ClassMakerPlantImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator extends Plugin implements BundleActivator {

	private static Activator instance;

	private static ServiceTracker<ClassMakerPlant, ClassMakerPlantImpl> classMaker;

	public static ClassMakerPlant getClassMaker() {
		if (classMaker != null)
			return classMaker.getService();
		return null;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		instance = this;
		classMaker = new ServiceTracker<ClassMakerPlant, ClassMakerPlantImpl>(context, ClassMakerPlant.class, null);
		classMaker.open();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		classMaker.close();
		classMaker = null;
		instance = null;
	}

	public static void log(CoreException e) {
		instance.getLog().log(e.getStatus());
	}

}
