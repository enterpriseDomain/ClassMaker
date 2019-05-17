/**
 * Copyright 2017 Kyrill Zotkin
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
package org.enterprisedomain.classmaker;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Select
 * Reveal Handler</b></em>'. <!-- end-user-doc -->
 *
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getSelectRevealHandler()
 * @model
 * @generated
 */
public interface SelectRevealHandler extends EObject {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void prepare();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model newResourceType="org.enterprisedomain.classmaker.Resource"
	 * @generated
	 */
	void selectReveal(Resource newResource);

} // SelectRevealHandler
