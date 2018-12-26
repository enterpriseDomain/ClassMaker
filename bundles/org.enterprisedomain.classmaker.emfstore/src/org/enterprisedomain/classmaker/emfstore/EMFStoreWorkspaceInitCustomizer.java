package org.enterprisedomain.classmaker.emfstore;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.emfstore.internal.client.provider.XMIClientURIConverter;
import org.eclipse.emf.emfstore.internal.common.model.util.ModelUtil;
import org.eclipse.emf.emfstore.internal.server.storage.XMIServerURIConverter;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.impl.ResourceSetURIConverter;
import org.enterprisedomain.classmaker.impl.WorkspaceInitCustomizer;

public class EMFStoreWorkspaceInitCustomizer extends WorkspaceInitCustomizer implements Customizer {

	public EMFStoreWorkspaceInitCustomizer() {
	}

	private static class EMFStoreURIConverter extends ResourceSetURIConverter {

		private URIConverter delegate;

		public EMFStoreURIConverter(URIConverter uriConverter) {
			delegate = uriConverter;
		}

		@Override
		public Map<URI, URI> getURIMap() {
			Map<URI, URI> results = super.getURIMap();
			results.putAll(delegate.getURIMap());
			return results;
		}

		@Override
		public URI normalize(URI uri) {
			uri = delegate.normalize(uri);
			return super.normalize(uri);
		}

	}

	@Override
	public Object customize(EList<Object> args) {
		Workspace workspace = (Workspace) args.get(0);
		ResourceSet resourceSet = workspace.getResourceSet();
		resourceSet.setURIConverter(new EMFStoreURIConverter(new XMIClientURIConverter()));
		resourceSet.getLoadOptions().putAll(ModelUtil.getResourceLoadOptions());
		return super.customize(args);
	}

}
