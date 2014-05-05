package org.classupplier.query;

import org.classupplier.ClasSupplier;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.query.conditions.eobjects.IEObjectSource;

public class ClasSupplierAdapterFactory implements IAdapterFactory {

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (IEObjectSource.class.isAssignableFrom(adapterType)
				&& adaptableObject instanceof ClasSupplier)
			return new LoadedObjectSource(
					((ClasSupplier) adaptableObject).getWorkspace());
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class[] getAdapterList() {
		return new Class[] { IEObjectSource.class };
	}

}
