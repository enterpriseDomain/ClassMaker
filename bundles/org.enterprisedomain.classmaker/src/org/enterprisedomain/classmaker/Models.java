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
 * <!-- begin-model-doc --> A holder of pair of references. To blueprint dynamic
 * model instance, and to a resulting generated one. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.Models#getDynamicEPackage
 * <em>Dynamic EPackage</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Models#getGeneratedEPackage
 * <em>Generated EPackage</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Models#getGeneratedEditPlugin
 * <em>Generated Edit Plugin</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Models#getGeneratedEditorPlugin
 * <em>Generated Editor Plugin</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Models#getParent
 * <em>Parent</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels()
 * @model
 * @generated
 */
public interface Models extends EObject {
	/**
	 * Returns the value of the '<em><b>Dynamic EPackage</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc
	 * --> Dynamic model {@link org.eclipse.emf.ecore.EPackage EPackage} instance,
	 * which serves as a blueprint for {@link ClassMakerService#make() making}. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Dynamic EPackage</em>' containment reference.
	 * @see #setDynamicEPackage(EObject)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels_DynamicEPackage()
	 * @model containment="true"
	 * @generated
	 */
	EObject getDynamicEPackage();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Models#getDynamicEPackage <em>Dynamic
	 * EPackage</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Dynamic EPackage</em>' containment
	 *              reference.
	 * @see #getDynamicEPackage()
	 * @generated
	 */
	void setDynamicEPackage(EObject value);

	/**
	 * Returns the value of the '<em><b>Generated EPackage</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Generated
	 * model {@link org.eclipse.emf.ecore.EPackage EPackage} instance, which appears
	 * as a result of {@link ClassMakerService#make() making}. <!-- end-model-doc
	 * -->
	 * 
	 * @return the value of the '<em>Generated EPackage</em>' reference.
	 * @see #setGeneratedEPackage(EObject)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels_GeneratedEPackage()
	 * @model
	 * @generated
	 */
	EObject getGeneratedEPackage();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Models#getGeneratedEPackage
	 * <em>Generated EPackage</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Generated EPackage</em>' reference.
	 * @see #getGeneratedEPackage()
	 * @generated
	 */
	void setGeneratedEPackage(EObject value);

	/**
	 * Returns the value of the '<em><b>Generated Edit Plugin</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generated Edit Plugin</em>' attribute.
	 * @see #setGeneratedEditPlugin(EMFPlugin)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels_GeneratedEditPlugin()
	 * @model dataType="org.enterprisedomain.classmaker.EMFPlugin"
	 * @generated
	 */
	EMFPlugin getGeneratedEditPlugin();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Models#getGeneratedEditPlugin
	 * <em>Generated Edit Plugin</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Generated Edit Plugin</em>' attribute.
	 * @see #getGeneratedEditPlugin()
	 * @generated
	 */
	void setGeneratedEditPlugin(EMFPlugin value);

	/**
	 * Returns the value of the '<em><b>Generated Editor Plugin</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generated Editor Plugin</em>' attribute.
	 * @see #setGeneratedEditorPlugin(EMFPlugin)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getModels_GeneratedEditorPlugin()
	 * @model dataType="org.enterprisedomain.classmaker.EMFPlugin"
	 * @generated
	 */
	EMFPlugin getGeneratedEditorPlugin();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Models#getGeneratedEditorPlugin
	 * <em>Generated Editor Plugin</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Generated Editor Plugin</em>'
	 *              attribute.
	 * @see #getGeneratedEditorPlugin()
	 * @generated
	 */
	void setGeneratedEditorPlugin(EMFPlugin value);

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
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Models#getParent <em>Parent</em>}'
	 * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Item value);

} // Models
