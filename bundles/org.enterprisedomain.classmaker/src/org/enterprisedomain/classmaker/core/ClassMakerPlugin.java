/**
 * Copyright 2012-2018 Kyrill Zotkin
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
package org.enterprisedomain.classmaker.core;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl;
import org.enterprisedomain.classmaker.util.ReflectiveFactory;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceException;
import org.osgi.util.tracker.ServiceTracker;

public class ClassMakerPlugin extends Plugin {

	public static final String PLUGIN_ID = "org.enterprisedomain.classmaker"; //$NON-NLS-1$

	public static final String NATURE_ID = PLUGIN_ID + ".projectNature"; //$NON-NLS-1$

	public static final String BUILDER_ID = PLUGIN_ID + ".projectBuilder"; //$NON-NLS-1$

	public static final String STAGES_EXT_POINT = PLUGIN_ID + ".stages"; //$NON-NLS-1$

	public static final String CUSTOMIZERS_EXT_POINT = PLUGIN_ID + ".customizers";//$NON-NLS-1$ l

	public static final String PDE_PLUGIN_NATURE = "org.eclipse.pde.PluginNature"; //$NON-NLS-1$

	public static final String JAVA_NATURE = "org.eclipse.jdt.core.javanature";

	public static final String MODEL_FOLDER_PREF_KEY = "modelFolder"; //$NON-NLS-1$

	public static final String MODEL_RESOURCE_EXT_PREF_KEY = "resourceExtension"; //$NON-NLS-1$

	public static final String TURN_OFF_AUTO_BUILDING_PREF_KEY = "turnOffAutoBuilding"; //$NON-NLS-1$

	private static ClassMakerPlugin instance;

	private static Object lock = new Object();

	private static Class<? extends IProgressMonitor> monitorClass;

	private static Object[] monitorParameters;

	private static IRunnerWithProgress runner;

	private static IProgressMonitor progressMonitor;

	private static IRunWrapper wrapper;

	private static ProgressProvider previousProgressProvider;

	private static ServiceTracker<ClassMakerService, ClassMakerServiceImpl> tracker;

	private static ClassMakerService service;

	private static PrintStream defaultOutputStream = System.out;

	private boolean turnOffAutoBuilding = Platform.getPreferencesService().getBoolean(PLUGIN_ID,
			TURN_OFF_AUTO_BUILDING_PREF_KEY, false, null);

	public static ClassMakerPlugin getInstance() {
		return instance;
	}

	/**
	 * ClassMaker facade service. Only for internal use.
	 * 
	 * @return ClassMakerService service instance
	 */
	public static ClassMakerService getClassMaker() {
		if (service == null) {
			try {
				if (tracker.getTrackingCount() == -1)
					tracker.open();
				service = tracker.getService();
			} catch (ServiceException e) {
			} catch (Exception e) {
			}
		}
		return service;
	}

	public static <T> T getService(String serviceClass) {
		if (getInstance() == null)
			return null;
		BundleContext context = getInstance().getBundle().getBundleContext();
		return getService(serviceClass, context);
	}

	public static <T> T getService(String serviceClass, BundleContext context) {
		ServiceTracker<T, T> tracker = new ServiceTracker<T, T>(context, serviceClass, null);
		tracker.open();
		T result = tracker.getService();
		tracker.close();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext )
	 */
	public void start(BundleContext context) throws Exception {
		instance = this;
		if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE) {
			tracker = new ServiceTracker<ClassMakerService, ClassMakerServiceImpl>(context, ClassMakerService.class,
					null);
			ResourceUtils.saveAutoBuilding(ResourcesPlugin.getWorkspace());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		IProgressMonitor monitor = getProgressMonitor();
		try {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, monitor);
			ResourcesPlugin.getWorkspace().save(true, monitor);
		} catch (OperationCanceledException e) {
		}
		tracker.close();
		service = null;
		progressMonitor = null;
		instance = null;
	}

	public static PrintStream getDefaultOutputStream() {
		return defaultOutputStream;
	}

	public static void setDefaultOutputStream(PrintStream defaultOutputStream) {
		ClassMakerPlugin.defaultOutputStream = defaultOutputStream;
	}

	public static void setRunnerWithProgress(IRunnerWithProgress runner) {
		ClassMakerPlugin.runner = runner;
	}

	public static void runWithProgress(IRunnableWithProgress runnable)
			throws InvocationTargetException, InterruptedException {
		if (runner == null)
			runner = new DefaultRunnerWithProgress();
		runner.run(runnable);
	}

	public static IProgressMonitor getProgressMonitor() {
		if (progressMonitor == null || (progressMonitor != null && progressMonitor.isCanceled()))
			progressMonitor = createProgressMonitor();
		return progressMonitor;
	}

	public static void setProgressMonitor(IProgressMonitor monitor) {
		progressMonitor = new WrappingProgressMonitor(monitor);
	}

	public static void setClientRunWrapper(IRunWrapper wrapper) {
		ClassMakerPlugin.wrapper = wrapper;
	}

	public static void wrapRun(Runnable runnable) {
		if (wrapper == null)
			wrapper = new DefaultRunWrapper();
		wrapper.wrapRun(runnable);
	}

	public static ProgressProvider getPreviousProgressProvider() {
		return previousProgressProvider;
	}

	public static void setPreviousProgressProvider(ProgressProvider previousProgressProvider) {
		ClassMakerPlugin.previousProgressProvider = previousProgressProvider;
	}

	public static <T extends IProgressMonitor> void setMonitorParameters(Class<T> monitorClass,
			Object... constructorParameters) {
		synchronized (lock) {
			ClassMakerPlugin.monitorClass = monitorClass;
			ClassMakerPlugin.monitorParameters = constructorParameters;
		}
	}

	public static Class<? extends IProgressMonitor> getProgressMonitorClass() {
		return ClassMakerPlugin.monitorClass;
	}

	public static Object[] getProgressMonitorClassConstructorParameters() {
		return ClassMakerPlugin.monitorParameters;
	}

	private static IProgressMonitor createProgressMonitor() {
		if (monitorClass == null || monitorParameters == null)
			setMonitorParameters(CodeGenUtil.EclipseUtil.StreamProgressMonitor.class, defaultOutputStream);
		return new WrappingProgressMonitor(ReflectiveFactory.create(monitorClass, monitorParameters));
	}

	public boolean isTurnOffAutoBuilding() {
		return turnOffAutoBuilding;
	}

	public void setTurnOffAutoBuilding(boolean turnOffAutoBuilding) {
		this.turnOffAutoBuilding = turnOffAutoBuilding;
	}

	public static IStatus createOKStatus(String message) {
		return new Status(IStatus.OK, ClassMakerPlugin.PLUGIN_ID, message);
	}

	public static IStatus createInfoStatus(String message) {
		return new Status(IStatus.INFO, ClassMakerPlugin.PLUGIN_ID, message);
	}

	public static IStatus createWarningStatus(String message) {
		return new Status(IStatus.WARNING, ClassMakerPlugin.PLUGIN_ID, message);
	}

	public static IStatus createWarningStatus(Throwable exception) {
		return new Status(IStatus.WARNING, ClassMakerPlugin.PLUGIN_ID, exception.getLocalizedMessage(), exception);
	}

	public static IStatus createWarningStatus(String message, Throwable exception) {
		return new Status(IStatus.WARNING, ClassMakerPlugin.PLUGIN_ID, message + ": " + exception.getLocalizedMessage(),
				exception);
	}

	public static IStatus createErrorStatus(String message) {
		return new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, message);
	}

	public static Status createErrorStatus(Throwable exception) {
		return new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, exception.getLocalizedMessage(), exception);
	}

	public static IStatus createErrorStatus(CoreException exception) {
		return exception.getStatus();
	}

	public static String bundleStateAsString(int state) {
		switch (state) {
		case Bundle.ACTIVE:
			return "ACTIVE"; //$NON-NLS-1$
		case Bundle.INSTALLED:
			return "INSTALLED"; //$NON-NLS-1$
		case Bundle.RESOLVED:
			return "RESOLVED"; //$NON-NLS-1$
		case Bundle.STARTING:
			return "STARTING"; //$NON-NLS-1$
		case Bundle.STOPPING:
			return "STOPPING"; //$NON-NLS-1$
		case Bundle.UNINSTALLED:
			return "UNINSTALLED"; //$NON-NLS-1$
		default:
			return "unknown bundle state: " + state; //$NON-NLS-1$
		}
	}

}
