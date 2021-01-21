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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.osgi.framework.Version;

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
 * <li>{@link org.enterprisedomain.classmaker.Project#getResourcePath
 * <em>Resource Path</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#isNeedCompletionNotification
 * <em>Need Completion Notification</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getCompletionNotificationAdapter
 * <em>Completion Notification Adapter</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getResourceReloadListener
 * <em>Resource Reload Listener</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#isSavingResource
 * <em>Saving Resource</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getRevision
 * <em>Revision</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getRevisions
 * <em>Revisions</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getProjectVersion
 * <em>Project Version</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getSelectRevealHandler
 * <em>Select Reveal Handler</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getVersion
 * <em>Version</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getState
 * <em>State</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.Project#getModelResourceAdapter
 * <em>Model Resource Adapter</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject()
 * @model superTypes="org.enterprisedomain.classmaker.ISchedulingRule
 *        org.enterprisedomain.classmaker.Item"
 * @generated
 */
public interface Project extends ISchedulingRule, Item {
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
	 * @param value the new value of the '<em>Name</em>' attribute.
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
	 * @param value the new value of the '<em>Project Name</em>' attribute.
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
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.Project#isDirty
	 * <em>Dirty</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Dirty</em>' attribute.
	 * @see #isDirty()
	 * @generated
	 */
	void setDirty(boolean value);

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
	 * @param value the new value of the '<em>Workspace</em>' container reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Workspace value);

	/**
	 * Returns the value of the '<em><b>Resource Path</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Path</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Resource Path</em>' attribute.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_ResourcePath()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getResourcePath();

	/**
	 * Returns the value of the '<em><b>Need Completion Notification</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc
	 * --> Set to {@code true} when completion notification is needed. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Need Completion Notification</em>' attribute.
	 * @see #setNeedCompletionNotification(boolean)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_NeedCompletionNotification()
	 * @model
	 * @generated
	 */
	boolean isNeedCompletionNotification();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#isNeedCompletionNotification
	 * <em>Need Completion Notification</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Need Completion Notification</em>'
	 *              attribute.
	 * @see #isNeedCompletionNotification()
	 * @generated
	 */
	void setNeedCompletionNotification(boolean value);

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
	 * @param value the new value of the '<em>Completion Notification Adapter</em>'
	 *              containment reference.
	 * @see #getCompletionNotificationAdapter()
	 * @generated
	 */
	void setCompletionNotificationAdapter(CompletionNotificationAdapter value);

	/**
	 * Returns the value of the '<em><b>Resource Reload Listener</b></em>'
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Reload Listener</em>' reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Resource Reload Listener</em>' reference.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_ResourceReloadListener()
	 * @model resolveProxies="false" changeable="false"
	 * @generated
	 */
	ResourceChangeListener getResourceReloadListener();

	/**
	 * Returns the value of the '<em><b>Saving Resource</b></em>' attribute. The
	 * default value is <code>"false"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Saving Resource</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Saving Resource</em>' attribute.
	 * @see #setSavingResource(boolean)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_SavingResource()
	 * @model default="false"
	 * @generated
	 */
	boolean isSavingResource();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#isSavingResource <em>Saving
	 * Resource</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Saving Resource</em>' attribute.
	 * @see #isSavingResource()
	 * @generated
	 */
	void setSavingResource(boolean value);

	/**
	 * Returns the value of the '<em><b>Revision</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Currently
	 * checked out <em>Revision</em>. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Revision</em>' reference.
	 * @see #setRevision(Revision)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_Revision()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	Revision getRevision();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#getRevision
	 * <em>Revision</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Revision</em>' reference.
	 * @see #getRevision()
	 * @generated
	 */
	void setRevision(Revision value);

	/**
	 * Returns the value of the '<em><b>Revisions</b></em>' map. The key is of type
	 * {@link org.osgi.framework.Version}, and the value is of type
	 * {@link org.enterprisedomain.classmaker.Revision}, <!-- begin-user-doc -->
	 * <!-- end-user-doc --> <!-- begin-model-doc --> Collection of
	 * <em><b>Contribution</b></em>'s <em><b>Revision</b></em>'s <!-- end-model-doc
	 * -->
	 * 
	 * @return the value of the '<em>Revisions</em>' map.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_Revisions()
	 * @model mapType="org.enterprisedomain.classmaker.VersionToRevisionMapEntry&lt;org.enterprisedomain.classmaker.OSGiVersion,
	 *        org.enterprisedomain.classmaker.Revision&gt;"
	 * @generated
	 */
	EMap<Version, Revision> getRevisions();

	/**
	 * Returns the value of the '<em><b>Project Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> A
	 * {@link org.osgi.framework.Version Version} of the <em><b>Item</b></em>. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Project Version</em>' attribute.
	 * @see #setProjectVersion(Version)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_ProjectVersion()
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	Version getProjectVersion();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#getProjectVersion <em>Project
	 * Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Project Version</em>' attribute.
	 * @see #getProjectVersion()
	 * @generated
	 */
	void setProjectVersion(Version value);

	/**
	 * Returns the value of the '<em><b>Select Reveal Handler</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Select Reveal Handler</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Select Reveal Handler</em>' reference.
	 * @see #setSelectRevealHandler(SelectRevealHandler)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_SelectRevealHandler()
	 * @model
	 * @generated
	 */
	SelectRevealHandler getSelectRevealHandler();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#getSelectRevealHandler
	 * <em>Select Reveal Handler</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Select Reveal Handler</em>' reference.
	 * @see #getSelectRevealHandler()
	 * @generated
	 */
	void setSelectRevealHandler(SelectRevealHandler value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> A
	 * {@link org.osgi.framework.Version Version} of the <em><b>Item</b></em>. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_Version()
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Project#getVersion <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Version value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Currently
	 * checked out <em>State</em>. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>State</em>' reference.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_State()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	State getState();

	/**
	 * Returns the value of the '<em><b>Model Resource Adapter</b></em>' containment
	 * reference. It is bidirectional and its opposite is
	 * '{@link org.enterprisedomain.classmaker.ResourceAdapter#getProject
	 * <em>Project</em>}'. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> A {@link ResourceAdapter <em><b>ResourceAdapter</b></em>}
	 * for <em>Project</em>. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Model Resource Adapter</em>' containment
	 *         reference.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getProject_ModelResourceAdapter()
	 * @see org.enterprisedomain.classmaker.ResourceAdapter#getProject
	 * @model opposite="project" containment="true" changeable="false"
	 * @generated
	 */
	ResourceAdapter getModelResourceAdapter();

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
	 * @param commit whether to perform commit
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 *        notificationType="org.enterprisedomain.classmaker.Notification"
	 * @generated
	 */
	void notifyResourceChanged(Notification notification) throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void addResourceChangeListener(ResourceChangeListener resourceListener);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void removeResourceChangeListener(ResourceChangeListener resourceListener);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	Revision newRevision(Version version);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	Revision newBareRevision(Version version);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void doNewRevision(Revision newRevision);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        baseDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	Version newVersion(Version base, boolean incrementMajor, boolean incrementMinor, boolean incrementMicro)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 * @generated
	 */
	Version newVersion(boolean incrementMajor, boolean incrementMinor, boolean incrementMicro) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model dataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 * @generated
	 */
	Version nextVersion() throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	void checkout(Version version);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	void checkout(Version version, long timestamp);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	void checkout(Version version, long timestamp, String commitId);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void checkout(long timestamp);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void checkout(long timestamp, String commitId);

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
	void initAdapters(Revision revision);

} // Project
