/**
 * Copyright 2012-2018 Kyrill Zotkin
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

import java.util.Locale;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Item</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Common denominator for <em><b>Project</b></em>'s and
 * they parts. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.Item#getModelName <em>Model
 * Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Item#getPhase <em>Phase</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Item#getLanguage
 * <em>Language</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Item#getDomainModel <em>Domain
 * Model</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Item#getCustomizers
 * <em>Customizers</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Item#getParent
 * <em>Parent</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Item#getLocale
 * <em>Locale</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Item#getProject
 * <em>Project</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getItem()
 * @model abstract="true"
 * @generated
 */
public interface Item extends EObject {
	/**
	 * Returns the value of the '<em><b>Model Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Name</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Model Name</em>' attribute.
	 * @see #setModelName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getItem_ModelName()
	 * @model
	 * @generated
	 */
	String getModelName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Item#getModelName <em>Model
	 * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Model Name</em>' attribute.
	 * @see #getModelName()
	 * @generated
	 */
	void setModelName(String value);

	/**
	 * Returns the value of the '<em><b>Phase</b></em>' attribute. The default value
	 * is <code>"DEFINED"</code>. The literals are from the enumeration
	 * {@link org.enterprisedomain.classmaker.Stage}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stage</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> A <em><b>Stage</b></em> of
	 * em><b>Item</b></em>'s model. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Phase</em>' attribute.
	 * @see org.enterprisedomain.classmaker.Stage
	 * @see #setPhase(Stage)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getItem_Phase()
	 * @model default="DEFINED"
	 * @generated
	 */
	Stage getPhase();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Item#getPhase
	 * <em>Phase</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Phase</em>' attribute.
	 * @see org.enterprisedomain.classmaker.Stage
	 * @see #getPhase()
	 * @generated
	 */
	void setPhase(Stage value);

	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute. The default
	 * value is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> {@link java.util.Locale
	 * Locale}'s language. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getItem_Language()
	 * @model default=""
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Item#getLanguage <em>Language</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Domain Model</b></em>' containment
	 * reference. It is bidirectional and its opposite is
	 * '{@link org.enterprisedomain.classmaker.ModelPair#getParent
	 * <em>Parent</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Model</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> Subject <em>ModelPair</em> for
	 * <em><b>Item</b></em> {@link ClassMakerService#make() making}. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Domain Model</em>' containment reference.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getItem_DomainModel()
	 * @see org.enterprisedomain.classmaker.ModelPair#getParent
	 * @model opposite="parent" containment="true" changeable="false" derived="true"
	 * @generated
	 */
	ModelPair getDomainModel();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Item#getDomainModel <em>Domain
	 * Model</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value the new value of the '<em>Domain Model</em>' containment
	 *              reference.
	 * @see #getDomainModel()
	 * @generated
	 */
	void setDomainModel(ModelPair value);

	/**
	 * Returns the value of the '<em><b>Customizers</b></em>' map. The key is of
	 * type {@link org.enterprisedomain.classmaker.StageQualifier}, and the value is
	 * of type {@link org.enterprisedomain.classmaker.Customizer}, <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Customizers</em>' map isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> {@link Customizer}s
	 * collection. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Customizers</em>' map.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getItem_Customizers()
	 * @model mapType="org.enterprisedomain.classmaker.StageQualifierToCustomizerMapEntry&lt;org.enterprisedomain.classmaker.StageQualifier,
	 *        org.enterprisedomain.classmaker.Customizer&gt;" transient="true"
	 *        volatile="true" derived="true"
	 * @generated
	 */
	EMap<StageQualifier, Customizer> getCustomizers();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(Item)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getItem_Parent()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	Item getParent();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Item#getParent
	 * <em>Parent</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Item value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	String make(IProgressMonitor monitor) throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void build(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	String initialize(boolean commit);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void copyModel(Item from);

	/**
	 * Returns the value of the '<em><b>Locale</b></em>' attribute. The default
	 * value is <code>""</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> A {@link java.util.Locale Locale}. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Locale</em>' attribute.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getItem_Locale()
	 * @model default="" dataType="org.enterprisedomain.classmaker.Locale"
	 *        changeable="false" derived="true"
	 * @generated
	 */
	Locale getLocale();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Item#getLocale
	 * <em>Locale</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Locale</em>' attribute.
	 * @see #getLocale()
	 * @generated
	 */
	void setLocale(Locale value);

	/**
	 * Returns the value of the '<em><b>Project</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * <em>Contribution</em> associated with the <em><b>Item</b></em>. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Project</em>' reference.
	 * @see #setProject(Project)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getItem_Project()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	Project getProject();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Item#getProject
	 * <em>Project</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Project</em>' reference.
	 * @see #getProject()
	 * @generated
	 */
	void setProject(Project value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 * @generated
	 */
	void load(boolean create, boolean loadOnDemand) throws CoreException;

} // Item
