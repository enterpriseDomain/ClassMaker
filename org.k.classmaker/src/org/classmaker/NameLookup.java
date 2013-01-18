package org.classmaker;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;

public class NameLookup {

	private final IPreferencesService preferencesService = Platform
			.getPreferencesService();

	private String modelFolderName = preferencesService.getString(
			ClassMaker.PLUGIN_ID, ClassMaker.MODEL_FOLDER_PREF_KEY, "model",
			null);;

	private String fileExt = preferencesService.getString(ClassMaker.PLUGIN_ID,
			ClassMaker.RESOURCE_EXT_PREF_KEY, "xmi", null);;

	private Map<String, String> names = new HashMap<String, String>();

	private Map<String, Version> versions = new HashMap<String, Version>();

	public String getProjectName(String name) {
		String projectName = name.toLowerCase();
		names.put(projectName, name);
		return projectName;
	}

	public String getName(String projectName) {
		return names.get(projectName);
	}

	public String getModelFolderName() {
		return modelFolderName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public String getFileName(String name) {
		return name + '.' + fileExt;
	}

	public Version getVersion(String projectName) {
		if (!versions.containsKey(projectName))
			versions.put(projectName, Version.newVersion());
		return versions.get(projectName);
	}

}
