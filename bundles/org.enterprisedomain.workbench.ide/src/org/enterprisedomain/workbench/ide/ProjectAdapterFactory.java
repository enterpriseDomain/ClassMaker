package org.enterprisedomain.workbench.ide;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecp.core.ECPProject;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class ProjectAdapterFactory implements IAdapterFactory {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (Contribution.class.isAssignableFrom(adapterType)) {
			if (adaptableObject instanceof IProject)
				return (T) ClassMakerPlugin.getClassMaker().getWorkspace()
						.getContribution(((IProject) adaptableObject).getName());
			else if (adaptableObject instanceof ECPProject)
				return (T) ClassMakerPlugin.getClassMaker().getWorkspace()
						.getContribution(((ECPProject) adaptableObject).getName());
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class<?>[] { Contribution.class };
	}

}
