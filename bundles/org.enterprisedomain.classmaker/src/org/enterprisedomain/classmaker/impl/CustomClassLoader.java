/*******************************************************************************
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.enterprisedomain.classmaker.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

public class CustomClassLoader extends ClassLoader {

	protected final @NonNull Bundle bundle;
	protected final @NonNull List<@NonNull String> qualifiedPackageNames;
	protected final @Nullable ClassLoader fallBackClassLoader;

	private final @NonNull Map<String, Class<?>> hitsAndMisses = new HashMap<String, Class<?>>(); // Miss signalled by
																									// ExplicitClassLoader.class

	public CustomClassLoader(@NonNull Bundle bundle, @NonNull String qualifiedPackageName) {
		this(bundle, qualifiedPackageName, null);
	}

	public CustomClassLoader(@NonNull Bundle bundle, @NonNull String qualifiedPackageName,
			@Nullable ClassLoader fallBackClassLoader) {
		this(bundle, Collections.singletonList(qualifiedPackageName), fallBackClassLoader);
	}

	public CustomClassLoader(@NonNull Bundle bundle, @NonNull List<@NonNull String> qualifiedPackageNames,
			@Nullable ClassLoader fallBackClassLoader) {
		this.bundle = bundle;
		this.qualifiedPackageNames = qualifiedPackageNames;
		this.fallBackClassLoader = fallBackClassLoader;
	}

	/**
	 * Re-implement to load any class sharing qualifiedClassNamePrefix from the
	 * explicitClassPath. Other classes are loaded normally.
	 */
	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		int index = name.lastIndexOf(".");
		String packageName = index >= 0 ? name.substring(0, index) : "";
		if (qualifiedPackageNames.contains(packageName)) {
			Class<?> hitOrMiss = hitsAndMisses.get(name);
			if (hitOrMiss != null) {
				if (hitOrMiss == CustomClassLoader.class) {
					throw new ClassNotFoundException("Attempted reload of '" + name + "' failed");
				} else {
					return hitOrMiss;
				}
			}
			try {
				hitOrMiss = loadExplicitClass(name, resolve);
				hitsAndMisses.put(name, hitOrMiss);
				return hitOrMiss;
			} catch (IOException e) {
				hitsAndMisses.put(name, CustomClassLoader.class);
				throw new ClassNotFoundException(e.getMessage(), e);
			}
		}
		Class<?> result = bundle.loadClass(name);
		if (result != null)
			return result;
		if (fallBackClassLoader != null) {
			return fallBackClassLoader.loadClass(name);
		} else {
			return super.loadClass(name, resolve);
		}
	}

	/**
	 * Load the class whose Java name is qualifiedClassName and whose class file can
	 * be found below explicitClassPath. This method always loads the class and so
	 * ignores any previously cached loads.
	 */
	private @Nullable Class<?> loadExplicitClass(@NonNull String qualifiedClassName, boolean resolve)
			throws ClassNotFoundException, IOException {
		String filePath = qualifiedClassName.replaceAll("\\.", "/") + ".class";
		URL url = bundle.getEntry("/bin/" + filePath);
		if (url == null)
			url = bundle.getResource("/bin/" + filePath);
		InputStream inputStream = FileLocator.toFileURL(url).openStream();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		try {
			int bytes;
			byte[] byteArray = new byte[16384];
			while ((bytes = inputStream.read(byteArray)) >= 0) {
				byteStream.write(byteArray, 0, bytes);
			}
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
		byte[] classBytes = byteStream.toByteArray();
		Class<?> theClass = defineClass(qualifiedClassName, classBytes, 0, classBytes.length);
		if (theClass == null) {
			return null;
		}
		Package thePackage = theClass.getPackage();
		if (thePackage == null) {
			String packageName = qualifiedClassName.replaceAll("\\.\\w+$", "");
			definePackage(packageName, null, null, null, null, null, null, null);
		}
		if (resolve) {
			resolveClass(theClass);
		}
		return theClass;
	}

	public Version getBundleVersion() {
		return bundle.getVersion();
	}

}
