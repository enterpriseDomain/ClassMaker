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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Revision</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A revision of <em>Contribution</em>. Identified by
 * {@link #getVersion() <em>Version</em>}. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.Revision#getState
 * <em>State</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Revision#getTimestamp
 * <em>Timestamp</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Revision#getStateHistory <em>State
 * History</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Revision#getLatestTimestamp
 * <em>Latest Timestamp</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getRevision()
 * @model
 * @generated
 */
public interface Revision extends Item {

	public static final DateFormat VERSION_QUALIFIER_FORMAT = new SimpleDateFormat("yyyyMMddHHmm"); //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>State</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>State</em>' reference.
	 * @see #setState(State)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getRevision_State()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	State getState();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Revision#getState <em>State</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>State</em>' reference.
	 * @see #getState()
	 * @generated
	 */
	void setState(State value);

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
	 * @see #setTimestamp(int)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getRevision_Timestamp()
	 * @model id="true"
	 * @generated
	 */
	int getTimestamp();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Revision#getTimestamp
	 * <em>Timestamp</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(int value);

	/**
	 * Returns the value of the '<em><b>State History</b></em>' map. The key is of
	 * type {@link java.lang.Integer}, and the value is of type
	 * {@link org.enterprisedomain.classmaker.State}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State History</em>' map isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> A {@link State}s history. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>State History</em>' map.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getRevision_StateHistory()
	 * @model mapType="org.enterprisedomain.classmaker.IntegerToStateMapEntry&lt;org.eclipse.emf.ecore.EIntegerObject,
	 *        org.enterprisedomain.classmaker.State&gt;"
	 * @generated
	 */
	EMap<Integer, State> getStateHistory();

	/**
	 * Returns the value of the '<em><b>Latest Timestamp</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * Get latest time stamp from state history. If state history is empty, return
	 * value of default time stamp.
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Latest Timestamp</em>' attribute.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getRevision_LatestTimestamp()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	int getLatestTimestamp();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void create(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void checkout(int timestamp, String commitId);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void checkout(int timestamp);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void checkout(String commitId);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	State newState();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void delete(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model adaptersType="org.enterprisedomain.classmaker.Adapter"
	 *        adaptersMany="true"
	 * @generated
	 */
	void addAdapters(EList<Adapter> adapters);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void copyModel(Revision from);

} // Revision
