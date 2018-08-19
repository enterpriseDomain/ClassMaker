package org.enterprisedomain.classmaker.impl;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class ResourceSetURIConverter extends ExtensibleURIConverterImpl implements URIConverter {

	@Override
	public Map<URI, URI> getURIMap() {
		Map<URI, URI> results = super.getURIMap();
		for (Project project : ClassMakerPlugin.getClassMaker().getWorkspace().getProjects()) {
			Revision revision = project.getRevision();
			if (revision.getDomainModel().getDynamic() != null && revision.getState().getResource() != null)
				results.put(URI.createURI(revision.getDomainModel().getDynamic().getNsURI()),
						revision.getState().getResource().getURI());
		}
		return results;
	}

}
