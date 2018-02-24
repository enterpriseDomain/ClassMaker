package org.enterprisedomain.ecp;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecp.core.ECPProvider;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;

public class EnterpriseDomainProviderAdapterFactory implements IAdapterFactory {

	private static final Class<?>[] CLASSES = { IProgressMonitor.class, ComposeableAdapterFactory.class,
			AdapterFactory.class, ResourceSet.class, ECPProvider.class };

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		return adapt(adaptableObject, adapterType);
	}

	@SuppressWarnings("unchecked")
	public static <T> T adapt(Object adaptable, Class<T> adapterType) {
		if (CLASSES[0].isAssignableFrom(adapterType))
			return (T) ((EnterpriseDomainProvider) adaptable).getUIProvider().getAdapter(adaptable, adapterType);
		if (CLASSES[1].isAssignableFrom(adapterType))
			return (T) ((EnterpriseDomainProvider) adaptable).getUIProvider().getAdapter(adaptable, adapterType);
		if (CLASSES[2].isAssignableFrom(adapterType))
			return (T) EnterpriseDomainProvider.ENTERPRISE_DOMAIN_ADAPTER_FACTORY;
		if (CLASSES[3].isAssignableFrom(adapterType))
			return (T) Activator.getClassMaker().getWorkspace().getResourceSet();
		if (CLASSES[4].isAssignableFrom(adapterType))
			return (T) adaptable;
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return CLASSES;
	}

}
