package org.enterprisedomain.classmaker.util;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.enterprisedomain.classmaker.Contribution;

public class ContributionAdapterFactory implements IAdapterFactory {

	protected Class<?>[] ADAPTED_CLASSES = { Resource.class };

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (ADAPTED_CLASSES[0].isAssignableFrom(adapterType))
			return (T) ((Contribution) adaptableObject).getModelResourceAdapter().getResource();
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return ADAPTED_CLASSES;
	}
}
