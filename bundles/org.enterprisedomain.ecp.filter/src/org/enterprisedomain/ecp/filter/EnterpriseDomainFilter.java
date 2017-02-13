/**
 * Copyright 2012-2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.ecp.filter;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecp.core.util.ECPFilterProvider;
import org.enterprisedomain.classsupplier.ClassSupplierPackage;

public class EnterpriseDomainFilter implements ECPFilterProvider {

	public EnterpriseDomainFilter() {		
	}

	@Override
	public Set<String> getHiddenPackages() {
		final Set<String> packages = new HashSet<String>();
		packages.add(ClassSupplierPackage.eNS_URI);
		return packages;
	}

}
