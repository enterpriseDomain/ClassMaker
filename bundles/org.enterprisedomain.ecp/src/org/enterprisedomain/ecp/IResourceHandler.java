package org.enterprisedomain.ecp;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecp.core.ECPProject;

public interface IResourceHandler {

	void handleResourceChange(final ECPProject project, final Collection<Resource> changedResources,
			final Collection<Resource> removedResources);

}
