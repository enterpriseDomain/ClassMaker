package org.enterprisedomain.classmaker.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.enterprisedomain.classmaker.util.ResourceUtils;

public class ProjectNature implements IProjectNature {

	private IProject project;

	@Override
	public void configure() throws CoreException {
		ResourceUtils.addProjectNature(project, ClassMakerPlugin.JAVA_NATURE);
		ResourceUtils.addProjectNature(project, ClassMakerPlugin.PDE_PLUGIN_NATURE);
	}

	@Override
	public void deconfigure() throws CoreException {
	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}

}
