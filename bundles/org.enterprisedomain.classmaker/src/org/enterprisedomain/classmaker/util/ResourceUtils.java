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
package org.enterprisedomain.classmaker.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

@SuppressWarnings("restriction")
public class ResourceUtils {

	public static List<String> PROJECT_DELETE_MASK;

	private static final String PLUGIN_PROPS_FILENAME_DESCRIPTOR = "plugin.properties";

	private static final IPreferencesService preferencesService = Platform.getPreferencesService();

	private static String modelFolderName = preferencesService.getString(ClassMakerPlugin.PLUGIN_ID,
			ClassMakerPlugin.MODEL_FOLDER_PREF_KEY, Messages.DefaultModelFolder, null);

	private static String fileExt = preferencesService.getString(ClassMakerPlugin.PLUGIN_ID,
			ClassMakerPlugin.MODEL_RESOURCE_EXT_PREF_KEY, Messages.DefaultResourceExt, null);

	private static final String targetFolderName = "target"; //$NON-NLS-1$

	static {
		PROJECT_DELETE_MASK = new ArrayList<String>();
		PROJECT_DELETE_MASK.add(Constants.DOT_GIT);
		PROJECT_DELETE_MASK.add(ICoreConstants.PLUGIN_FILENAME_DESCRIPTOR);
		PROJECT_DELETE_MASK.add(ICoreConstants.MANIFEST_FOLDER_NAME);
		PROJECT_DELETE_MASK.add(ICoreConstants.MANIFEST_FILENAME);
		PROJECT_DELETE_MASK.add(ICoreConstants.BUILD_FILENAME_DESCRIPTOR);
		PROJECT_DELETE_MASK.add(PLUGIN_PROPS_FILENAME_DESCRIPTOR);
		PROJECT_DELETE_MASK.add(IProjectDescription.DESCRIPTION_FILE_NAME);
	}

	public static IPath getModelResourcePath(IProject project, String modelName) {
		return project.getFullPath().append(getModelFolderName()).append(getFileName(modelName));
	}

	public static IPath getModelTransformationPath(IProject project,
			org.eclipse.emf.common.util.URI transformationURI) {
		return project.getFullPath().append(getModelFolderName()).append(transformationURI.lastSegment());
	}

	public static IPath getExportDestination(IProject project) {
		return project.getLocation().append(getTargetFolderName());
	}

	public static IPath getTargetResourcePath(IProject project, State state) {
		return getExportDestination(project).append("plugins").addTrailingSeparator() //$NON-NLS-1$
				.append(state.getDeployableUnitName()).addFileExtension("jar"); //$NON-NLS-1$
	}

	/**
	 * Returns the name of the folder within project, where the model resource
	 * is located.
	 * 
	 * @return
	 */
	public static String getModelFolderName() {
		return modelFolderName;
	}

	public static String getTargetFolderName() {
		return targetFolderName;
	}

	public static String getModelFileExt() {
		return fileExt;
	}

	public static String getFileName(String name) {
		return name + '.' + getModelFileExt();
	}

	public static IPath getWorkspaceLocation() {
		return ResourcesPlugin.getWorkspace().getRoot().getLocation();
	}

	public static IProject getProject(String projectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}

	public static boolean isProjectExists(String projectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).exists();
	}

	public static String[] addElement(String[] elements, String element) {
		String[] oldElements = elements;
		String[] newElements = new String[oldElements.length + 1];
		System.arraycopy(oldElements, 0, newElements, 0, oldElements.length);
		newElements[oldElements.length] = element;
		return newElements;
	}

	public static String[] removeElement(String[] elements, String element) {
		String[] oldElements = elements;
		if (oldElements.length == 0)
			return oldElements;
		String[] newNatures = new String[oldElements.length - 1];
		int index = Arrays.binarySearch(oldElements, element);
		System.arraycopy(oldElements, index + 1, newNatures, index, oldElements.length - 1 - index);
		return newNatures;
	}

	public static void removeProjectNature(IProject project, String natureId) throws CoreException {
		IProjectDescription description = project.getDescription();
		description.setNatureIds(removeElement(description.getNatureIds(), natureId));
		project.setDescription(description, ClassMakerPlugin.getInstance().getProgressMonitor());
	}

	@SuppressWarnings("deprecation")
	public static void createProject(IProject project, String nature, IProgressMonitor monitor) throws CoreException {
		if (project.exists())
			return;
		IProgressMonitor pm = null;
		boolean autoBuilding = project.getWorkspace().isAutoBuilding();
		try {
			pm = new SubProgressMonitor(monitor, 1);
			project.create(pm);
			project.open(pm);
			if (nature != null && !nature.isEmpty()) {
				IProjectDescription description = project.getDescription();
				description.setNatureIds(ResourceUtils.addElement(description.getNatureIds(), nature));
				project.setDescription(description, pm);
			}
		} finally {
			if (pm != null)
				pm.done();
			ResourceUtils.setAutoBuilding(project.getWorkspace(), autoBuilding);
		}
	}

	public static void copyFile(org.eclipse.emf.common.util.URI sourceURI, IPath targetPath) throws CoreException {
		try {
			Files.copy(new File(sourceURI.toFileString()).toPath(),
					ResourcesPlugin.getWorkspace().getRoot().getRawLocation().append(targetPath).toFile().toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
		}
	}

	public static void setAutoBuilding(IWorkspace workspace, boolean value) throws CoreException {
		IWorkspaceDescription d = workspace.getDescription();
		d.setAutoBuilding(value);
		workspace.setDescription(d);
	}

	public static void cleanupDir(IProject project) throws CoreException {
		cleanupDir(project, "");
	}

	public static void cleanupDir(IProject project, String folderPath) throws CoreException {
		cleanupDir(project, folderPath, new String[] {});
	}

	public static void cleanupDir(IProject project, String folderPath, String[] excluding) throws CoreException {
		IPath path = null;
		if (folderPath.isEmpty()) {
			path = project.getFullPath();
			;
		} else {
			path = project.getFolder(folderPath).getFullPath();
		}
		path = project.getWorkspace().getRoot().getLocation().append(path);
		File folder = path.toFile();
		if (!folder.exists())
			return;
		for (String fileName : folder.list())
			delete(new File(folder.toString() + File.separator + fileName), excluding);
	}

	public static void delete(File fileOrDir, String[] excluding) {
		List<String> excludingList = Arrays.asList(excluding);
		if (fileOrDir.isDirectory())
			for (File file : fileOrDir.listFiles())
				delete(file, excluding);
		if (!excludingList.contains(fileOrDir.getName()) && !PROJECT_DELETE_MASK.contains(fileOrDir.getName()))
			fileOrDir.delete();
	}

	public static void writeFile(IPath location, CharSequence contents) throws CoreException {
		location.uptoSegment(location.segmentCount() - 1).toFile().mkdirs();
		File file = location.toFile();
		try {
			file.createNewFile();
			FileWriter writer = null;
			try {
				writer = new FileWriter(file);
				writer.append(contents);
				writer.flush();
			} finally {
				if (writer != null)
					writer.close();
			}
		} catch (IOException e) {
			throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
		}
	}

	public static ICommand getBuildSpec(IProjectDescription description, String builderId) {
		for (ICommand build : description.getBuildSpec())
			if (build.getBuilderName().equals(builderId))
				return build;
		return null;
	}

}
