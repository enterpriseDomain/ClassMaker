/**
 * Copyright 2020 Kyrill Zotkin
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

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.emfstore.internal.server.storage.ServerXMIResourceSetProvider;
import org.eclipse.emf.emfstore.internal.server.storage.XMIServerURIConverter;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class EDServerResourceSetProvider extends ServerXMIResourceSetProvider {

	public EDServerResourceSetProvider() {
	}

	@SuppressWarnings("restriction")
	@Override
	public ResourceSet getResourceSet() {
		ResourceSet result = super.getResourceSet();
		result.setURIConverter(new XMIServerURIConverter());
		return result;
	}

}
