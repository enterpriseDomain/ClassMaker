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
 *   <li>{@link org.enterprisedomain.classmaker.Strategy#getGenerators <em>Generators</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Strategy#getExporters <em>Exporters</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Strategy#getInstallers <em>Installers</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Strategy#getLoaders <em>Loaders</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Strategy#getState <em>State</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStrategy()
 * @model
 * @generated
 */
public interface Strategy extends EObject {
	/**
	 * Returns the value of the '<em><b>Generators</b></em>' reference list.
	 * The list contents are of type {@link org.enterprisedomain.classmaker.jobs.Worker}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generators</em>' reference list.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStrategy_Generators()
	 * @model type="org.enterprisedomain.classmaker.Worker" resolveProxies="false"
	 * @generated
	 */
	EList<Worker> getGenerators();

	/**
	 * Returns the value of the '<em><b>Exporters</b></em>' reference list.
	 * The list contents are of type {@link org.enterprisedomain.classmaker.jobs.Worker}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the value of the '<em>Exporters</em>' reference list.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStrategy_Exporters()
	 * @model type="org.enterprisedomain.classmaker.Worker" resolveProxies="false"
	 * @generated
	 */
	EList<Worker> getExporters();

	/**
	 * Returns the value of the '<em><b>Installers</b></em>' reference list.
	 * The list contents are of type {@link org.enterprisedomain.classmaker.jobs.Worker}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Installers</em>' reference list.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStrategy_Installers()
	 * @model type="org.enterprisedomain.classmaker.Worker" resolveProxies="false"
	 * @generated
	 */
	EList<Worker> getInstallers();

	/**
	 * Returns the value of the '<em><b>Loaders</b></em>' reference list.
	 * The list contents are of type {@link org.enterprisedomain.classmaker.jobs.Worker}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the value of the '<em>Loaders</em>' reference list.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStrategy_Loaders()
	 * @model type="org.enterprisedomain.classmaker.Worker" resolveProxies="false"
	 * @generated
	 */
	EList<Worker> getLoaders();

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model type="org.enterprisedomain.classmaker.Worker"
	 * @generated
	 */
	Worker createGenerator();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model type="org.enterprisedomain.classmaker.Worker"
	 * @generated
	 */
	Worker createExporter();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model type="org.enterprisedomain.classmaker.Worker"
	 * @generated
	 */
	Worker createInstaller();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model type="org.enterprisedomain.classmaker.Worker"
	 * @generated
	 */
	Worker createModelLoader();

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

} // Strategy
