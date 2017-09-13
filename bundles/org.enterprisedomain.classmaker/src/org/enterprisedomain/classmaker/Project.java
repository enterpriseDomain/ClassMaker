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
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Project</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Default project class. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.Project#getName
 * <em>Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getProjectName <em>Project
 * Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getChildren
 * <em>Children</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#isDirty
 * <em>Dirty</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getWorkspace
 * <em>Workspace</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#isNeedsCompletionNotification
 * <em>Needs Completion Notification</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getCompletionNotificationAdapter
 * <em>Completion Notification Adapter</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject()
 * @model superTypes="org.enterprisedomain.classmaker.ISchedulingRule"
 * @generated
 */
public interface Project extends EObject, ISchedulingRule {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. The default value
	 * is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> A name of the
	 * <em><b>Project</b></em>. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Project#getName
	 * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute. The
	 * default value is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> A name of the
	 * {@link org.eclipse.core.resources.IProject IProject} to which the
	 * <em><b>Project</b></em> is mapped to. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_ProjectName()
	 * @model default=""
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#getProjectName <em>Project
	 * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list. The list
	 * contents are of type {@link org.eclipse.emf.ecore.EObject}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' attribute list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see org.genericdomain.EnterpriseDomainPackage#getProject_Children()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Object> getChildren();

	/**
	 * Returns the value of the '<em><b>Dirty</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dirty</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dirty</em>' attribute.
	 * @see org.genericdomain.EnterpriseDomainPackage#getProject_Dirty()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isDirty();

	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' container reference. It
	 * is bidirectional and its opposite is
	 * '{@link org.enterprisedomain.classmaker.Workspace#getProjects
	 * <em>Projects</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workspace</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> Reference to
	 * <em><b>Project</b></em>'s containing {@link Workspace Workspace}. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Workspace</em>' container reference.
	 * @see #setWorkspace(Workspace)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_Workspace()
	 * @see org.enterprisedomain.classmaker.Workspace#getProjects
	 * @model opposite="projects" transient="false"
	 * @generated
	 */
	Workspace getWorkspace();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#getWorkspace
	 * <em>Workspace</em>}' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Workspace</em>' container reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Workspace value);

	/**
	 * Returns the value of the '<em><b>Needs Completion Notification</b></em>'
	 * attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Needs Completion Notification</em>' attribute
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> Set to <code>true</code> when
	 * completion notification is needed. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Needs Completion Notification</em>' attribute.
	 * @see #setNeedsCompletionNotification(boolean)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_NeedsCompletionNotification()
	 * @model
	 * @generated
	 */
	boolean isNeedsCompletionNotification();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#isNeedsCompletionNotification
	 * <em>Needs Completion Notification</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Needs Completion Notification</em>'
	 *            attribute.
	 * @see #isNeedsCompletionNotification()
	 * @generated
	 */
	void setNeedsCompletionNotification(boolean value);

	/**
	 * Returns the value of the '<em><b>Completion Notification Adapter</b></em>'
	 * containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completion Notification Adapter</em>' reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc -->
	 * <em>CompletionNotificationAdapter</em> for <em><b>Project</b></em>'s
	 * operations. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Completion Notification Adapter</em>'
	 *         containment reference.
	 * @see #setCompletionNotificationAdapter(CompletionNotificationAdapter)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_CompletionNotificationAdapter()
	 * @model containment="true"
	 * @generated
	 */
	CompletionNotificationAdapter getCompletionNotificationAdapter();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#getCompletionNotificationAdapter
	 * <em>Completion Notification Adapter</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Completion Notification Adapter</em>'
	 *            containment reference.
	 * @see #getCompletionNotificationAdapter()
	 * @generated
	 */
	void setCompletionNotificationAdapter(CompletionNotificationAdapter value);

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
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void delete(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model objectsMany="true"
	 * @generated
	 */
	void delete(EList<Object> objects);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	String make(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	boolean open(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void close(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Initializes <em>Project</em>. Calls {@link #initialize() <em>initialize</em>}
	 * on contained <em>Item</em>s.
	 * 
	 * @param commit
	 *            whether to perform commit
	 * @return commit Id <!-- end-model-doc -->
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
	void addCompletionListener(CompletionListener resultListener);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void removeCompletionListener(CompletionListener resultListener);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	void notifyCompletion() throws Exception;

} // Project
