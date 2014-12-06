package org.classupplier.util;

import org.classupplier.Contribution;
import org.classupplier.State;
import org.classupplier.Workspace;
import org.classupplier.impl.ClassSupplierOSGi;
import org.eclipse.core.resources.IProject;

public class ClassSupplierUtil {

	public static State getState(IProject project) {
		return getState(project.getName());
	}

	public static State getState(String projectName) {
		return getState(ClassSupplierOSGi.getClassSupplier().getWorkspace(),
				projectName);
	}

	public static State getState(Workspace workspace, String projectName) {
		Contribution contribution = workspace.findContribution(projectName);
		return contribution.getState();
	}

	public static State getState(Workspace workspace, IProject project) {
		return getState(workspace, project.getName());
	}

}
