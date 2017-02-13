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
package org.enterprisedomain.classsupplier;

import java.util.Locale;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.Item#getModelName <em>Model Name</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.Item#getPhase <em>Phase</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.Item#getVersion <em>Version</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.Item#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.Item#getDomainModel <em>Domain Model</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.Item#getCustomizers <em>Customizers</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.Item#getParent <em>Parent</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.Item#getLocale <em>Locale</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getItem()
 * @model abstract="true"
 * @generated
 */
public interface Item extends EObject {
	/**
	 * Returns the value of the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Name</em>' attribute.
	 * @see #setModelName(String)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getItem_ModelName()
	 * @model
	 * @generated
	 */
	String getModelName();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.Item#getModelName <em>Model Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Name</em>' attribute.
	 * @see #getModelName()
	 * @generated
	 */
	void setModelName(String value);

	/**
	 * Returns the value of the '<em><b>Phase</b></em>' attribute.
	 * The default value is <code>"DEFINED"</code>.
	 * The literals are from the enumeration {@link org.enterprisedomain.classsupplier.Stage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Phase</em>' attribute.
	 * @see org.enterprisedomain.classsupplier.Stage
	 * @see #setPhase(Stage)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getItem_Phase()
	 * @model default="DEFINED"
	 * @generated
	 */
	Stage getPhase();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.Item#getPhase <em>Phase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phase</em>' attribute.
	 * @see org.enterprisedomain.classsupplier.Stage
	 * @see #getPhase()
	 * @generated
	 */
	void setPhase(Stage value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getItem_Version()
	 * @model dataType="org.enterprisedomain.classsupplier.OSGiVersion"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.Item#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Version value);

	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getItem_Language()
	 * @model default=""
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.Item#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Domain Model</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.enterprisedomain.classsupplier.ModelPair#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Model</em>' containment reference.
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getItem_DomainModel()
	 * @see org.enterprisedomain.classsupplier.ModelPair#getParent
	 * @model opposite="parent" containment="true" changeable="false" derived="true"
	 * @generated
	 */
	ModelPair getDomainModel();

	/**
	 * Returns the value of the '<em><b>Customizers</b></em>' map.
	 * The key is of type {@link org.enterprisedomain.classsupplier.StageQualifier},
	 * and the value is of type {@link org.enterprisedomain.classsupplier.Customizer},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Customizers</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customizers</em>' map.
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getItem_Customizers()
	 * @model mapType="org.enterprisedomain.classsupplier.StageQualifierToCustomizerMapEntry<org.enterprisedomain.classsupplier.StageQualifier, org.enterprisedomain.classsupplier.Customizer>" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EMap<StageQualifier, Customizer> getCustomizers();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(Item)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getItem_Parent()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	Item getParent();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.Item#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Item value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException"
	 * @generated
	 */
	void load(boolean create) throws CoreException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.Exception" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	String save(IProgressMonitor monitor) throws Exception;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String initialize();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void copyModel(Item from);

	/**
	 * Returns the value of the '<em><b>Locale</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locale</em>' attribute.
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getItem_Locale()
	 * @model default="" dataType="org.enterprisedomain.classsupplier.Locale" changeable="false" derived="true"
	 * @generated
	 */
	Locale getLocale();

} // Item
