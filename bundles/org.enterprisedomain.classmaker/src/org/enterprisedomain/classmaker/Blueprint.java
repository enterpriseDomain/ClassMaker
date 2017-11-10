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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Blueprint</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Model object wrapper and input specification for {@link #make() making}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.Blueprint#getDynamicModel <em>Dynamic Model</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Blueprint#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Blueprint#getCompletionListeners <em>Completion Listeners</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getBlueprint()
 * @model
 * @generated
 */
public interface Blueprint extends EObject {
	/**
	 * Returns the value of the '<em><b>Dynamic Model</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Model
	 * {@link org.eclipse.emf.ecore.EPackage EPackage} object. <!-- end-model-doc
	 * -->
	 * 
	 * @return the value of the '<em>Dynamic Model</em>' reference.
	 * @see #setDynamicModel(EPackage)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getBlueprint_DynamicModel()
	 * @model
	 * @generated
	 */
	EPackage getDynamicModel();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Blueprint#getDynamicModel <em>Dynamic Model</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic Model</em>' reference.
	 * @see #getDynamicModel()
	 * @generated
	 */
	void setDynamicModel(EPackage value);

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' attribute list. The
	 * list contents are of type {@link java.lang.String}. <!-- begin-user-doc -->
	 * <!-- end-user-doc --> <!-- begin-model-doc --> <em>Contribution's</em>
	 * {@link Contribution#getDependencies() dependencies}. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Dependencies</em>' attribute list.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getBlueprint_Dependencies()
	 * @model
	 * @generated
	 */
	EList<String> getDependencies();

	/**
	 * Returns the value of the '<em><b>Completion Listeners</b></em>' reference list.
	 * The list contents are of type {@link org.enterprisedomain.classmaker.CompletionListener}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completion Listeners</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completion Listeners</em>' reference list.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getBlueprint_CompletionListeners()
	 * @model
	 * @generated
	 */
	EList<CompletionListener> getCompletionListeners();

} // Blueprint
