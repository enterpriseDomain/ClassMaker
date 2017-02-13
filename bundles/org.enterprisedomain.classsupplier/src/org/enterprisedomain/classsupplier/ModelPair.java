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
package org.enterprisedomain.classsupplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.ModelPair#getDynamic <em>Dynamic</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.ModelPair#getGenerated <em>Generated</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.ModelPair#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getModelPair()
 * @model
 * @generated
 */
public interface ModelPair extends EObject {
	/**
	 * Returns the value of the '<em><b>Dynamic</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic</em>' reference.
	 * @see #setDynamic(EPackage)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getModelPair_Dynamic()
	 * @model
	 * @generated
	 */
	EPackage getDynamic();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.ModelPair#getDynamic <em>Dynamic</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic</em>' reference.
	 * @see #getDynamic()
	 * @generated
	 */
	void setDynamic(EPackage value);

	/**
	 * Returns the value of the '<em><b>Generated</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generated</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generated</em>' reference.
	 * @see #setGenerated(EPackage)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getModelPair_Generated()
	 * @model
	 * @generated
	 */
	EPackage getGenerated();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.ModelPair#getGenerated <em>Generated</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated</em>' reference.
	 * @see #getGenerated()
	 * @generated
	 */
	void setGenerated(EPackage value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.enterprisedomain.classsupplier.Item#getDomainModel <em>Domain Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(Item)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getModelPair_Parent()
	 * @see org.enterprisedomain.classsupplier.Item#getDomainModel
	 * @model opposite="domainModel" transient="false"
	 * @generated
	 */
	Item getParent();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.ModelPair#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Item value);

} // ModelPair
