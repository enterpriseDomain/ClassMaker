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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Workspace</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.Workspace#getProjects <em>Projects</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.Workspace#getResourceSet <em>Resource Set</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getWorkspace()
 * @model superTypes="org.enterprisedomain.classsupplier.ISchedulingRule"
 * @generated
 */
public interface Workspace extends EObject, ISchedulingRule {
	/**
	 * Returns the value of the '<em><b>Projects</b></em>' containment reference
	 * list. The list contents are of type {@link org.enterprisedomain.classsupplier.Project}. It
	 * is bidirectional and its opposite is '
	 * {@link org.enterprisedomain.classsupplier.Project#getWorkspace <em>Workspace</em>}'. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Projects</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Projects</em>' containment reference list.
	 * @see org.genericdomain.EnterpriseDomainPackage#getWorkspace_Projects()
	 * @see org.enterprisedomain.classsupplier.Project#getWorkspace
	 * @model opposite="workspace" containment="true"
	 * @generated
	 */
	EList<Project> getProjects();

	/**
	 * Returns the value of the '<em><b>Resource Set</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Resource Set</em>' attribute.
	 * @see org.genericdomain.EnterpriseDomainPackage#getWorkspace_ResourceSet()
	 * @model transient="true" changeable="false"
	 * @generated
	 */
	ResourceSet getResourceSet();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void initialize();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	void provision(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	Contribution createContribution(EPackage blueprint, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Contribution getContribution(EObject eObject);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Contribution getContribution(EPackage ePackage);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Contribution getContribution(String projectName);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	Project createProject(String name, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Project getProject(String projectName);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Contribution getContribution(EPackage ePackage, boolean searchOptimistic);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void registerProject(Project project);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void unregisterProject(Project project);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Stage contains(EPackage blueprint);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean contains(EObject eObject);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	void delete(Object object, IProgressMonitor monitor) throws CoreException;

} // Workspace
