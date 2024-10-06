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
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>SCM
 * Operator</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Operator of Source Code Management system.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.SCMOperator#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.SCMOperator#getRegistry <em>Registry</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getSCMOperator()
 * @model abstract="true"
 * @generated
 */
public interface SCMOperator<T> extends EObject {

	public static final String MASTER_BRANCH = "master"; // $NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute. The
	 * default value is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> A name of the
	 * {@link org.eclipse.core.resources.IProject IProject} contained in SCM repo.
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getSCMOperator_ProjectName()
	 * @model default=""
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.SCMOperator#getProjectName
	 * <em>Project Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>Registry</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Registry</em>' reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Registry</em>' reference.
	 * @see #setRegistry(SCMRegistry)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getSCMOperator_Registry()
	 * @model
	 * @generated
	 */
	SCMRegistry<T> getRegistry();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.SCMOperator#getRegistry <em>Registry</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Registry</em>' reference.
	 * @see #getRegistry()
	 * @generated
	 */
	void setRegistry(SCMRegistry<T> value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model kind="operation" exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	T getRepositorySCM() throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	void ungetRepositorySCM() throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	void add(String filepattern) throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	String commit(String commitMessage) throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	long decodeTimestamp(String commitMessage);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	Version decodeVersion(String commitMessage);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String encodeCommitMessage(State state);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void deleteProject();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	void checkout(String branch, String commitId, boolean forced) throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	void checkoutOrphan(String branch, long timestamp) throws Exception;

} // SCMOperator
