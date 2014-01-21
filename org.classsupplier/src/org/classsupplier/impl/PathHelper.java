package org.classsupplier.impl;

import org.classsupplier.Infrastructure;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;

public class PathHelper {

	private static final IPreferencesService preferencesService = Platform
			.getPreferencesService();

	private static String modelFolderName = preferencesService.getString(
			OSGi.PLUGIN_ID, OSGi.MODEL_FOLDER_PREF_KEY, "model", null);

	private static String fileExt = preferencesService.getString(
			OSGi.PLUGIN_ID, OSGi.RESOURCE_EXT_PREF_KEY, "xmi", null);;

	public static IPath getModelResourcePath(IProject project,
			Infrastructure workspace) {
		if (workspace == null)
			workspace = OSGi.getClassSupplier().getWorkspace();
		String name = workspace.getArtifact(project.getName()).getName();
		return project.getFullPath().append(getModelFolderName())
				.append(getFileName(name));
	}

	public static String getModelFolderName() {
		return modelFolderName;
	}

	public static String getFileExt() {
		return fileExt;
	}

	public static String getFileName(String name) {
		return name + '.' + getFileExt();
	}

	public static IPath getDefaultDestination() {
		return getWorkspace().removeLastSegments(1);
	}

	public static IPath getWorkspace() {
		return ResourcesPlugin.getWorkspace().getRoot().getLocation();
	}

}
