package org.classupplier.impl;

import org.classupplier.Artifact;
import org.classupplier.Infrastructure;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.osgi.framework.Version;

public class ResourceHelper {

	private static final IPreferencesService preferencesService = Platform
			.getPreferencesService();

	private static String modelFolderName = preferencesService.getString(
			OSGi.PLUGIN_ID, OSGi.MODEL_FOLDER_PREF_KEY, "model", null);

	private static String fileExt = preferencesService.getString(
			OSGi.PLUGIN_ID, OSGi.RESOURCE_EXT_PREF_KEY, "xmi", null);;

	public static IPath getModelResourcePath(IProject project,
			Infrastructure workspace) {
		if (workspace == null)
			workspace = OSGi.getClasSupplier().getWorkspace();
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

	/**
	 * Adds the project nature to supplied project.
	 * 
	 * @param project
	 * @param natureId
	 * @param monitor
	 * @throws CoreException
	 */
	public static void addNature(IProject project, String natureId,
			IProgressMonitor monitor) throws CoreException {
		IProjectDescription description = project.getDescription();
		String[] prevNatures = description.getNatureIds();
		String[] newNatures = new String[prevNatures.length + 1];
		System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
		newNatures[prevNatures.length] = natureId;
		description.setNatureIds(newNatures);
		project.setDescription(description, monitor);
	}

	
	public static String getJarName(Artifact artifact) {
		String jarName;
		Version version = artifact.getVersion();
		if (version.equals(Version.emptyVersion))
			jarName = artifact.getProjectName() + '-'
					+ Version.parseVersion("1.0.0.qualifier").toString();
		else
			jarName = artifact.getProjectName() + '-' + version;
		return jarName;
	}

}
