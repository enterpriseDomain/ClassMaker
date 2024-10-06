/**
 * Copyright 2021 Kyrill Zotkin
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

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Models</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A holder of pair of references. To blueprint dynamic model instance, and to a resulting generated one.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.Models#getDynamic <em>Dynamic</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Models#getGenerated <em>Generated</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Models#getGeneratedEdit <em>Generated Edit</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Models#getGeneratedEditor <em>Generated Editor</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Models#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels()
 * @model
 * @generated
 */
public interface Models extends EObject {
	/**
	 * Returns the value of the '<em><b>Dynamic</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Dynamic model {@link org.eclipse.emf.ecore.EPackage EPackage} instance, which
	 * serves as a blueprint for {@link ClassMakerService#make() making}. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Dynamic</em>' containment reference.
	 * @see #setDynamic(EObject)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels_Dynamic()
	 * @model containment="true"
	 * @generated
	 */
	EObject getDynamic();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Models#getDynamic <em>Dynamic</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic</em>' containment reference.
	 * @see #getDynamic()
	 * @generated
	 */
	void setDynamic(EObject value);

	/**
	 * Returns the value of the '<em><b>Generated</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Generated
	 * model {@link org.eclipse.emf.ecore.EPackage EPackage} instance, which appears
	 * as a result of {@link ClassMakerService#make() making}. <!-- end-model-doc
	 * -->
	 * 
	 * @return the value of the '<em>Generated</em>' reference.
	 * @see #setGenerated(EObject)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels_Generated()
	 * @model
	 * @generated
	 */
	EObject getGenerated();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Models#getGenerated <em>Generated</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated</em>' reference.
	 * @see #getGenerated()
	 * @generated
	 */
	void setGenerated(EObject value);

	/**
	 * Returns the value of the '<em><b>Generated Edit</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generated Edit</em>' reference.
	 * @see #setGeneratedEdit(EMFPlugin)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels_GeneratedEdit()
	 * @model type="org.enterprisedomain.classmaker.EMFPlugin"
	 * @generated
	 */
	EMFPlugin getGeneratedEdit();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Models#getGeneratedEdit <em>Generated Edit</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated Edit</em>' attribute.
	 * @see #getGeneratedEdit()
	 * @generated
	 */
	void setGeneratedEdit(EMFPlugin value);

	/**
	 * Returns the value of the '<em><b>Generated Editor</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generated Editor</em>' reference.
	 * @see #setGeneratedEditor(EMFPlugin)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels_GeneratedEditor()
	 * @model type="org.enterprisedomain.classmaker.EMFPlugin"
	 * @generated
	 */
	EMFPlugin getGeneratedEditor();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Models#getGeneratedEditor <em>Generated Editor</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Generated Editor</em>' attribute.
	 * @see #getGeneratedEditor()
	 * @generated
	 */
	void setGeneratedEditor(EMFPlugin value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference. It is
	 * bidirectional and its opposite is
	 * '{@link org.enterprisedomain.classmaker.Item#getDomainModel <em>Domain
	 * Model</em>}'. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> An {@link Item <em>Item</em>} containing
	 * <em><b>ModelPair</b></em>. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(Item)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels_Parent()
	 * @see org.enterprisedomain.classmaker.Item#getDomainModel
	 * @model opposite="domainModel" transient="false"
	 * @generated
	 */
	Item getParent();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Models#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Item value);

} // Models
