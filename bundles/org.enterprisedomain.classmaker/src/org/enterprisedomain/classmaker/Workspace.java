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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Workspace</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Maps {@link org.eclipse.emf.ecore.resource.ResourceSet ResourceSet} to {@link org.eclipse.core.resources.IWorkspaceRoot IWorkspaceRoot}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.Workspace#getProjects <em>Projects</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Workspace#getResourceSet <em>Resource Set</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Workspace#getCustomizers <em>Customizers</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Workspace#getService <em>Service</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.Workspace#getSCMRegistry <em>SCM Registry</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getWorkspace()
 * @model superTypes="org.enterprisedomain.classmaker.ISchedulingRule"
 * @generated
 */
public interface Workspace extends EObject, ISchedulingRule {
	/**
	 * Returns the value of the '<em><b>Projects</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.enterprisedomain.classmaker.Project}. It is bidirectional and its
	 * opposite is '{@link org.enterprisedomain.classmaker.Project#getWorkspace
	 * <em>Workspace</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Projects</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> {@link Project
	 * <em><b>Projects</b></em>} contained in the <em><b>Workspace</b></em>. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Projects</em>' containment reference list.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getWorkspace_Projects()
	 * @see org.enterprisedomain.classmaker.Project#getWorkspace
	 * @model opposite="workspace" containment="true"
	 * @generated
	 */
	EList<Project> getProjects();

	/**
	 * Returns the value of the '<em><b>Resource Set</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Set</em>' attribute isn't clear, there
	 * really should be more of a description here...
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
	 * Returns the value of the '<em><b>Customizers</b></em>' map. The key is of
	 * type {@link org.enterprisedomain.classmaker.StageQualifier}, and the value is
	 * of type {@link org.enterprisedomain.classmaker.Customizer}, <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * {@link Customizer}s collection. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Customizers</em>' map.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getWorkspace_Customizers()
	 * @model mapType="org.enterprisedomain.classmaker.StageQualifierToCustomizerMapEntry&lt;org.enterprisedomain.classmaker.StageQualifier,
	 *        org.enterprisedomain.classmaker.Customizer&gt;"
	 * @generated
	 */
	EMap<StageQualifier, Customizer> getCustomizers();

	/**
	 * Returns the value of the '<em><b>Service</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.enterprisedomain.classmaker.ClassMakerService#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service</em>' reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service</em>' container reference.
	 * @see #setService(ClassMakerService)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getWorkspace_Service()
	 * @see org.enterprisedomain.classmaker.ClassMakerService#getWorkspace
	 * @model opposite="workspace" transient="false"
	 * @generated
	 */
	ClassMakerService getService();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Workspace#getService <em>Service</em>}' container reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Service</em>' container reference.
	 * @see #getService()
	 * @generated
	 */
	void setService(ClassMakerService value);

	/**
	 * Returns the value of the '<em><b>SCM Registry</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
	 * {@link SCMRegistry <em>SCMRegistry</em>}. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>SCM Registry</em>' reference.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getWorkspace_SCMRegistry()
	 * @model changeable="false"
	 * @generated
	 */
	SCMRegistry<?> getSCMRegistry();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Initializes <em>Workspace</em>. <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void initialize();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Provision target platform. <!-- end-model-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void provision(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Create
	 * Project. <!-- end-model-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Project createProject(String name, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Create
	 * new Contribution. <!-- end-model-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Contribution createContribution(EPackage blueprint, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Lookup
	 * workspace Contribution by any type of EObject contained. <!-- end-model-doc
	 * -->
	 * 
	 * @model
	 * @generated
	 */
	Contribution getContribution(EObject eObject);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Lookup
	 * workspace Contribution by model EPackage. <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Contribution getContribution(EPackage ePackage);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Lookup
	 * Contribution by underlying project resource name. <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Contribution getContribution(String projectName);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Lookup
	 * project by name. <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Project getProject(String name);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Lookup
	 * project by name. <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Project getProject(EObject eObject);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Lookup
	 * project by name. <!-- end-model-doc -->
	 * 
	 * @model resourceType="org.enterprisedomain.classmaker.Resource"
	 * @generated
	 */
	Project getProject(Resource resource);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Lookup
	 * workspace Contribution by model EPackage.
	 * 
	 * @param searchOptimistic true if use OR while EPackage comparison, overwise
	 *                         use AND. <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	Contribution getContribution(EPackage ePackage, boolean searchOptimistic);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Lookup
	 * workspace Contribution by model EPackage.
	 * 
	 * @param filter returns blueprint EPackage if Stage is less or equal MODELED,
	 *               generated if it equals LOADED. <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	Contribution getContribution(EPackage ePackage, Stage filter);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Lookup
	 * workspace Contribution by model EPackage.
	 * 
	 * @param filter           returns blueprint EPackage if Stage is less or equal
	 *                         MODELED, generated if it equals LOADED.
	 * @param searchOptimistic true if use OR while EPackage comparison, overwise
	 *                         use AND. <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	Contribution getContribution(EPackage ePackage, Stage filter, boolean searchOptimistic);

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
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException" monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void delete(Object object, IProgressMonitor monitor) throws CoreException;

} // Workspace
