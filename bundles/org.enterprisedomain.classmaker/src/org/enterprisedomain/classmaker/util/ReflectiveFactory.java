package org.enterprisedomain.classmaker.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class ReflectiveFactory {

	public static <T> T create(Class<T> clazz, Object... parameters) {
		Constructor<?> constructor = null;
		ctr: for (Constructor<?> c : clazz.getConstructors())
			if (parameters.length == c.getParameterTypes().length) {
				constructor = c;
				for (int i = 0; i < parameters.length; i++)
					if (!c.getParameterTypes()[i].isAssignableFrom(parameters[i].getClass()))
						continue ctr;
			}
		if (constructor != null)
			try {
				return (T) constructor.newInstance(parameters);
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
