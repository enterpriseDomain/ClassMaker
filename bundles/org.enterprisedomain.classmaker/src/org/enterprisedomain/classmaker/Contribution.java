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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EMap;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Software Contribution</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.Contribution#getRevision <em>Revision</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Contribution#getRevisions <em>Revisions</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Contribution#getState <em>State</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Contribution#getLatestVersion <em>Latest Version</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Contribution#getModel <em>Model</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Contribution#isCompletionNotified <em>Completion Notified</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getContribution()
 * @model
 * @generated
 */
public interface Contribution extends Project, Item {

	/**
	 * Returns the value of the '<em><b>Revision</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Revision</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Revision</em>' reference.
	 * @see #setRevision(Revision)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getContribution_Revision()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	Revision getRevision();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Contribution#getRevision <em>Revision</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Revision</em>' reference.
	 * @see #getRevision()
	 * @generated
	 */
	void setRevision(Revision value);

	/**
	 * Returns the value of the '<em><b>Revisions</b></em>' map.
	 * The key is of type {@link org.osgi.framework.Version},
	 * and the value is of type {@link org.enterprisedomain.classmaker.Revision},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Revision History</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Revisions</em>' map.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getContribution_Revisions()
	 * @model mapType="org.enterprisedomain.classmaker.VersionToRevisionMapEntry<org.enterprisedomain.classmaker.OSGiVersion, org.enterprisedomain.classmaker.Revision>"
	 * @generated
	 */
	EMap<Version, Revision> getRevisions();

	/**
	 * Returns the value of the '<em><b>State</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>State</em>' reference.
	 * @see org.enterprisedomain.classmaker.EnterpriseDomainPackage#getSoftwareContribution_State()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	State getState();

	/**
	 * Returns the value of the '<em><b>Latest Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Latest Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Latest Version</em>' attribute.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getContribution_LatestVersion()
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	Version getLatestVersion();

	/**
	 * Returns the value of the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' reference.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getContribution_Model()
	 * @model changeable="false" derived="true"
	 * @generated
	 */
	ResourceAdapter getModel();

	/**
	 * Returns the value of the '<em><b>Completion Notified</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completion Notified</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completion Notified</em>' attribute.
	 * @see #setCompletionNotified(boolean)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getContribution_CompletionNotified()
	 * @model default="false"
	 * @generated
	 */
	boolean isCompletionNotified();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Contribution#isCompletionNotified <em>Completion Notified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completion Notified</em>' attribute.
	 * @see #isCompletionNotified()
	 * @generated
	 */
	void setCompletionNotified(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException" monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Revision createRevision(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	void checkout(Version version);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	void checkout(Version version, int time);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	void checkout(Version version, int time, String commitId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void checkout(int time);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void checkout(int time, String commitId);

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
	 * @model versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	Revision newRevision(Version version);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion" exceptions="org.enterprisedomain.classmaker.CoreException"
	 * @generated
	 */
	Version newVersion(boolean incrementMajor, boolean incrementMinor, boolean incrementMicro) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion" exceptions="org.enterprisedomain.classmaker.CoreException" baseDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	Version newVersion(Version base, boolean incrementMajor, boolean incrementMinor, boolean incrementMicro)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException" monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void delete(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion" exceptions="org.enterprisedomain.classmaker.CoreException"
	 * @generated
	 */
	Version nextVersion() throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void addSaveCompletionListener(CompletionListener resultListener);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void removeSaveCompletionListener(CompletionListener resultListener);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException" monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	String save(Revision revision, IProgressMonitor monitor) throws Exception, CoreException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	State getState(int timestamp);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void initAdapters(Revision revision);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	void notifyCompletion() throws Exception;

} // Contribution
