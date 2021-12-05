package org.enterprisedomain.classmaker.util;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.enterprisedomain.classmaker.Project;

public class ProjectAdapterFactory implements IAdapterFactory {

	protected Class<?>[] ADAPTED_CLASSES = { Resource.class };

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (ADAPTED_CLASSES[0].isAssignableFrom(adapterType))
			return (T) ((Project) adaptableObject).getChildren().get(0);
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return ADAPTED_CLASSES;
	}

}
