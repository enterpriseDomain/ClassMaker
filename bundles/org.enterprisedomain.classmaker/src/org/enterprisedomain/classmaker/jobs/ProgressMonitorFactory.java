package org.enterprisedomain.classmaker.jobs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class ProgressMonitorFactory {

	public static <T extends IProgressMonitor> IProgressMonitor create(Class<T> monitorClass, Object... parameters) {
		Constructor<?> constructor = null;
		ctr: for (Constructor<?> c : monitorClass.getConstructors())
			if (parameters.length == c.getParameterTypes().length) {
				constructor = c;
				for (int i = 0; i < parameters.length; i++)
					if (!c.getParameterTypes()[i].isAssignableFrom(parameters[i].getClass()))
						continue ctr;
			}
		if (constructor != null)
			try {
				return (IProgressMonitor) constructor.newInstance(parameters);
			} catch (InstantiationException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
			} catch (IllegalAccessException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
			} catch (IllegalArgumentException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
			} catch (InvocationTargetException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
			}
		return null;
	}

}
