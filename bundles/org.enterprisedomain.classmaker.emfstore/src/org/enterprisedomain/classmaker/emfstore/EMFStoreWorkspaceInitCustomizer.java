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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.emfstore.internal.client.provider.XMIClientURIConverter;
import org.eclipse.emf.emfstore.internal.common.model.util.ModelUtil;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.impl.WorkspaceInitCustomizer;

public class EMFStoreWorkspaceInitCustomizer extends WorkspaceInitCustomizer implements Customizer {

	public EMFStoreWorkspaceInitCustomizer() {
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
