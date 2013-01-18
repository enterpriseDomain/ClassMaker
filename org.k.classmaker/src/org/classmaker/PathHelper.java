package org.classmaker;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;

public class PathHelper {

	private static NameLookup naming = ClassMaker.getInstance().names();

	public static IPath getResourcePath(IProject project) {
		String name = naming.getName(project.getName());
		return project.getFullPath().append(naming.getModelFolderName())
				.append(naming.getFileName(name));
	}

}
