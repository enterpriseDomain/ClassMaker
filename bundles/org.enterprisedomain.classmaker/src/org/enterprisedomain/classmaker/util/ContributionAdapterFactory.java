/**
 * Copyright 2012-2017 Kyrill Zotkin
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
package org.enterprisedomain.classmaker.util;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.enterprisedomain.classmaker.Contribution;

public class ContributionAdapterFactory implements IAdapterFactory {

	protected Class<?>[] ADAPTED_CLASSES = { Resource.class };

	@SuppressWarnings("unchecked")
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
