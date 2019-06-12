/**
 * Copyright 2019 Kyrill Zotkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.emfstore;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.enterprisedomain.classmaker.impl.ResourceSetURIConverter;

class EMFStoreURIConverter extends ResourceSetURIConverter {

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