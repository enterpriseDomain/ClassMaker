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

import java.util.Date;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.State#getContribution <em>Contribution</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.State#getRevision <em>Revision</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.State#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.State#getDeployableUnitName <em>Deployable Unit Name</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.State#getJobFamily <em>Job Family</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.State#getResource <em>Resource</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.State#getCommitIds <em>Commit Ids</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.State#getStateCustomizers <em>State Customizers</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.State#getProjectName <em>Project Name</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getState()
 * @model
 * @generated
 */
public interface State extends Item {
	/**
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timestamp</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(Date)
	 * @see org.genericdomain.EnterpriseDomainPackage#getState_Timestamp()
	 * @model id="true"
	 * @generated
	 */
	int getTimestamp();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.State#getTimestamp <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(int value);

	/**
	 * Returns the value of the '<em><b>Deployable Unit Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployable Unit Name</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployable Unit Name</em>' attribute.
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getState_DeployableUnitName()
	 * @model default="" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getDeployableUnitName();

	/**
	 * Returns the value of the '<em><b>Contribution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contribution</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contribution</em>' reference.
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getState_Contribution()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	Contribution getContribution();

	/**
	 * Returns the value of the '<em><b>Revision</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Version</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Revision</em>' reference.
	 * @see #setRevision(Revision)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getState_Revision()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	Revision getRevision();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.State#getRevision <em>Revision</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Revision</em>' reference.
	 * @see #getRevision()
	 * @generated
	 */
	void setRevision(Revision value);

	/**
	 * Returns the value of the '<em><b>Job Family</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Job Family</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Job Family</em>' attribute.
	 * @see #setJobFamily(String)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getState_JobFamily()
	 * @model
	 * @generated
	 */
	String getJobFamily();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.State#getJobFamily <em>Job Family</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Job Family</em>' attribute.
	 * @see #getJobFamily()
	 * @generated
	 */
	void setJobFamily(String value);

	/**
	 * Returns the value of the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' reference.
	 * @see #setResource(Resource)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getState_Resource()
	 * @model type="org.enterprisedomain.classsupplier.Resource" resolveProxies="false"
	 * @generated
	 */
	Resource getResource();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.State#getResource <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(Resource value);

	/**
	 * Returns the value of the '<em><b>Commit Ids</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Commit Ids</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Commit Ids</em>' attribute list.
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getState_CommitIds()
	 * @model
	 * @generated
	 */
	EList<String> getCommitIds();

	/**
	 * Returns the value of the '<em><b>State Customizers</b></em>' map.
	 * The key is of type {@link org.enterprisedomain.classsupplier.StageQualifier},
	 * and the value is of type {@link org.enterprisedomain.classsupplier.Customizer},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Customizers</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Customizers</em>' map.
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getState_StateCustomizers()
	 * @model mapType="org.enterprisedomain.classsupplier.StageQualifierToCustomizerMapEntry<org.enterprisedomain.classsupplier.StageQualifier, org.enterprisedomain.classsupplier.Customizer>"
	 * @generated
	 */
	EMap<StageQualifier, Customizer> getStateCustomizers();

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getState_ProjectName()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.State#getProjectName <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	void setProjectVersion(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	void delete(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EPackage find(EPackage ePackage, Stage stage);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void checkout();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void checkout(String commitId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.Exception"
	 * @generated
	 */
	String commit(String filepattern) throws Exception;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void saveResource();

} // State
