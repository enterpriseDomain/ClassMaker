/**
 * Copyright 2019 Kyrill Zotkin
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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.enterprisedomain.classmaker.jobs.Worker;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Strategy</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.Strategy#getState <em>State</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Strategy#getWorkers <em>Workers</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStrategy()
 * @model
 * @generated
 */
public interface Strategy extends EObject {
	/**
	 * Returns the value of the '<em><b>State</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.enterprisedomain.classmaker.State#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' reference.
	 * @see #setState(State)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStrategy_State()
	 * @see org.enterprisedomain.classmaker.State#getStrategy
	 * @model opposite="strategy"
	 * @generated
	 */
	State getState();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Strategy#getState <em>State</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' reference.
	 * @see #getState()
	 * @generated
	 */
	void setState(State value);

	/**
	 * Returns the value of the '<em><b>Workers</b></em>' map. The key is of type
	 * {@link org.enterprisedomain.classmaker.StageQualifier}, and the value is of
	 * type list of {@link org.enterprisedomain.classmaker.jobs.Worker}, <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Workers</em>' map.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStrategy_Workers()
	 * @model mapType="org.enterprisedomain.classmaker.StageQualifierToWorkersMapEntry&lt;org.enterprisedomain.classmaker.StageQualifier,
	 *        org.enterprisedomain.classmaker.Worker&gt;"
	 * @generated
	 */
	EMap<StageQualifier, EList<Worker>> getWorkers();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model kind="operation" type="org.enterprisedomain.classmaker.Worker"
	 * @generated
	 */
	Worker getReturnWorker();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	int configureJobs(boolean create, IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void configuteBuildJobs(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model type="org.enterprisedomain.classmaker.Worker"
	 * @generated
	 */
	EList<Worker> get(Stage stage, String step);

} // Strategy
