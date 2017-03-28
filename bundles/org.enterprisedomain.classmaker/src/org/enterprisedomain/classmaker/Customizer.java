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
package org.enterprisedomain.classmaker;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Customizer</b></em>'. <!-- end-user-doc -->
 *
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getCustomizer()
 * @model
 * @generated
 */
public interface Customizer extends EObject {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model argsMany="true"
	 * @generated
	 */
	void customize(EList<Object> args);

} // Customizer
