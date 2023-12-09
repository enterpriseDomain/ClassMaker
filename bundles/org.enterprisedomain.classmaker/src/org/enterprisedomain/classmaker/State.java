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

import java.util.Date;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A complete unit, representing the state of
 * <b>Contribution</b>. Identified by {@link State#getTimestamp()
 * <em>Timestamp</em>}. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.State#getPackageClassName
 * <em>Package Class Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getEditPluginClassName
 * <em>Edit Plugin Class Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getEditorPluginClassName
 * <em>Editor Plugin Class Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getRequiredPlugins
 * <em>Required Plugins</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getRevision
 * <em>Revision</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getTimestamp
 * <em>Timestamp</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getDeployableUnitName
 * <em>Deployable Unit Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getEditDeployableUnitName
 * <em>Edit Deployable Unit Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getEditorDeployableUnitName
 * <em>Editor Deployable Unit Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getJobFamily <em>Job
 * Family</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getResource
 * <em>Resource</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getCommitId <em>Commit
 * Id</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getStateCustomizers
 * <em>State Customizers</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getNonExclusiveStateCustomizers
 * <em>Non Exclusive State Customizers</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getProjectName <em>Project
 * Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#isMaking
 * <em>Making</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#isEdit <em>Edit</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#isEditor
 * <em>Editor</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getStrategy
 * <em>Strategy</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.State#getBasePackage <em>Base
 * Package</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState()
 * @model superTypes="org.enterprisedomain.classmaker.Item
 *        org.enterprisedomain.classmaker.ISchedulingRule"
 * @generated
 */
public interface State extends Item, ISchedulingRule {

	public String[] defaultRequiredPlugins = new String[] { "org.eclipse.emf.common", "org.eclipse.emf.ecore",
			"org.eclipse.emf.query" };

	/**
	 * Returns the value of the '<em><b>Package Class Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Generated
	 * {@link org.eclipse.emf.ecore.EPackage EPackage} class name set in a
	 * {@link org.eclipse.emf.codegen.ecore.genmodel.GenModel GenModel}. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Package Class Name</em>' attribute.
	 * @see #setPackageClassName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_PackageClassName()
	 * @model
	 * @generated
	 */
	String getPackageClassName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getPackageClassName <em>Package
	 * Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Package Class Name</em>' attribute.
	 * @see #getPackageClassName()
	 * @generated
	 */
	void setPackageClassName(String value);

	/**
	 * Returns the value of the '<em><b>Edit Plugin Class Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Generated {@link org.eclipse.emf.ecore.EPackage EPackage} class name set in a
	 * {@link org.eclipse.emf.codegen.ecore.genmodel.GenModel GenModel}. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Edit Plugin Class Name</em>' attribute.
	 * @see #setEditPluginClassName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_EditPluginClassName()
	 * @model
	 * @generated
	 */
	String getEditPluginClassName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getEditPluginClassName <em>Edit
	 * Plugin Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value the new value of the '<em>Edit Plugin Class Name</em>'
	 *              attribute.
	 * @see #getEditPluginClassName()
	 * @generated
	 */
	void setEditPluginClassName(String value);

	/**
	 * Returns the value of the '<em><b>Editor Plugin Class Name</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc
	 * --> Generated {@link org.eclipse.emf.ecore.EPackage EPackage} class name set
	 * in a {@link org.eclipse.emf.codegen.ecore.genmodel.GenModel GenModel}. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Editor Plugin Class Name</em>' attribute.
	 * @see #setEditorPluginClassName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_EditorPluginClassName()
	 * @model
	 * @generated
	 */
	String getEditorPluginClassName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getEditorPluginClassName
	 * <em>Editor Plugin Class Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Editor Plugin Class Name</em>'
	 *              attribute.
	 * @see #getEditorPluginClassName()
	 * @generated
	 */
	void setEditorPluginClassName(String value);

	/**
	 * Returns the value of the '<em><b>Required Plugins</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Required Plugins</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> <em><b>State</b></em> bundle's
	 * <b>Require-Bundle</b> dependencies. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Required Plugins</em>' attribute list.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_RequiredPlugins()
	 * @model
	 * @generated
	 */
	EList<String> getRequiredPlugins();

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
	 * @see org.enterprisedomain.EnterpriseDomainPackage#getState_Timestamp()
	 * @model id="true"
	 * @generated
	 */
	long getTimestamp();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getTimestamp
	 * <em>Timestamp</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(long value);

	/**
	 * Returns the value of the '<em><b>Deployable Unit Name</b></em>' attribute.
	 * The default value is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployable Unit Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> A name of the deployed bundle.
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Deployable Unit Name</em>' attribute.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_DeployableUnitName()
	 * @model default="" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getDeployableUnitName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getDeployableUnitName
	 * <em>Deployable Unit Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Deployable Unit Name</em>' attribute.
	 * @see #getDeployableUnitName()
	 * @generated
	 */
	void setDeployableUnitName(String value);

	/**
	 * Returns the value of the '<em><b>Edit Deployable Unit Name</b></em>'
	 * attribute. The default value is <code>""</code>. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> A name of the deployed bundle. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Edit Deployable Unit Name</em>' attribute.
	 * @see #setEditDeployableUnitName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_EditDeployableUnitName()
	 * @model default="" volatile="true" derived="true"
	 * @generated
	 */
	String getEditDeployableUnitName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getEditDeployableUnitName
	 * <em>Edit Deployable Unit Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Edit Deployable Unit Name</em>'
	 *              attribute.
	 * @see #getEditDeployableUnitName()
	 * @generated
	 */
	void setEditDeployableUnitName(String value);

	/**
	 * Returns the value of the '<em><b>Editor Deployable Unit Name</b></em>'
	 * attribute. The default value is <code>""</code>. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> A name of the deployed bundle. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Editor Deployable Unit Name</em>' attribute.
	 * @see #setEditorDeployableUnitName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_EditorDeployableUnitName()
	 * @model default="" volatile="true" derived="true"
	 * @generated
	 */
	String getEditorDeployableUnitName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getEditorDeployableUnitName
	 * <em>Editor Deployable Unit Name</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Editor Deployable Unit Name</em>'
	 *              attribute.
	 * @see #getEditorDeployableUnitName()
	 * @generated
	 */
	void setEditorDeployableUnitName(String value);

	/**
	 * Returns the value of the '<em><b>Revision</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Version</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Revision</em>' reference.
	 * @see #setRevision(Revision)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_Revision()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	Revision getRevision();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getRevision <em>Revision</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Revision</em>' reference.
	 * @see #getRevision()
	 * @generated
	 */
	void setRevision(Revision value);

	/**
	 * Returns the value of the '<em><b>Job Family</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Job Family</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Job Family</em>' attribute.
	 * @see #setJobFamily(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_JobFamily()
	 * @model
	 * @generated
	 */
	String getJobFamily();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getJobFamily <em>Job
	 * Family</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Job Family</em>' attribute.
	 * @see #getJobFamily()
	 * @generated
	 */
	void setJobFamily(String value);

	/**
	 * Returns the value of the '<em><b>Resource</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Resource</em>' reference.
	 * @see #setResource(Resource)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_Resource()
	 * @model type="org.enterprisedomain.classmaker.Resource" resolveProxies="false"
	 * @generated
	 */
	Resource getResource();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getResource <em>Resource</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Resource</em>' reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(Resource value);

	/**
	 * Returns the value of the '<em><b>Commit Id</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Commit Id</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> An SCM commit identifier
	 * attribute. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Commit Id</em>' attribute.
	 * @see #setCommitId(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_CommitId()
	 * @model
	 * @generated
	 */
	String getCommitId();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getCommitId <em>Commit
	 * Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Commit Id</em>' attribute.
	 * @see #getCommitId()
	 * @generated
	 */
	void setCommitId(String value);

	/**
	 * Returns the value of the '<em><b>State Customizers</b></em>' map. The key is
	 * of type {@link org.enterprisedomain.classmaker.StageQualifier}, and the value
	 * is of type {@link org.enterprisedomain.classmaker.Customizer}, <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Customizers</em>' map isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> A particular state's
	 * {@link Customizer}s. Provides actual implementation to which
	 * {@link customizers} feature is delegating. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>State Customizers</em>' map.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_StateCustomizers()
	 * @model mapType="org.enterprisedomain.classmaker.StageQualifierToCustomizerMapEntry&lt;org.enterprisedomain.classmaker.StageQualifier,
	 *        org.enterprisedomain.classmaker.Customizer&gt;"
	 * @generated
	 */
	EMap<StageQualifier, Customizer> getStateCustomizers();

	/**
	 * Returns the value of the '<em><b>Non Exclusive State Customizers</b></em>'
	 * map. The key is of type
	 * {@link org.enterprisedomain.classmaker.StageQualifier}, and the value is of
	 * type {@link org.enterprisedomain.classmaker.Customizer}, <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Non Exclusive State Customizers</em>' map.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_NonExclusiveStateCustomizers()
	 * @model mapType="org.enterprisedomain.classmaker.StageQualifierToCustomizerMapEntry&lt;org.enterprisedomain.classmaker.StageQualifier,
	 *        org.enterprisedomain.classmaker.Customizer&gt;"
	 * @generated
	 */
	EMap<StageQualifier, Customizer> getNonExclusiveStateCustomizers();

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_ProjectName()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getProjectName <em>Project
	 * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>Making</b></em>' attribute. The default
	 * value is <code>"false"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Making</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc --> <!-- begin-model-doc --> Whether
	 * {@link ClassMakerService#make() making}is happening. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Making</em>' attribute.
	 * @see #setMaking(boolean)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_Making()
	 * @model default="false"
	 * @generated
	 */
	boolean isMaking();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.State#isMaking
	 * <em>Making</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Making</em>' attribute.
	 * @see #isMaking()
	 * @generated
	 */
	void setMaking(boolean value);

	/**
	 * Returns the value of the '<em><b>Edit</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Edit</em>' attribute.
	 * @see #setEdit(boolean)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_Edit()
	 * @model
	 * @generated
	 */
	boolean isEdit();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.State#isEdit
	 * <em>Edit</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Edit</em>' attribute.
	 * @see #isEdit()
	 * @generated
	 */
	void setEdit(boolean value);

	/**
	 * Returns the value of the '<em><b>Editor</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Editor</em>' attribute.
	 * @see #setEditor(boolean)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_Editor()
	 * @model
	 * @generated
	 */
	boolean isEditor();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.State#isEditor
	 * <em>Editor</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Editor</em>' attribute.
	 * @see #isEditor()
	 * @generated
	 */
	void setEditor(boolean value);

	/**
	 * Returns the value of the '<em><b>Strategy</b></em>' reference. It is
	 * bidirectional and its opposite is
	 * '{@link org.enterprisedomain.classmaker.Strategy#getState <em>State</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Strategy</em>' reference.
	 * @see #setStrategy(Strategy)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_Strategy()
	 * @see org.enterprisedomain.classmaker.Strategy#getState
	 * @model opposite="state"
	 * @generated
	 */
	Strategy getStrategy();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getStrategy <em>Strategy</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Strategy</em>' reference.
	 * @see #getStrategy()
	 * @generated
	 */
	void setStrategy(Strategy value);

	/**
	 * Returns the value of the '<em><b>Base Package</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Base Package</em>' attribute.
	 * @see #setBasePackage(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getState_BasePackage()
	 * @model
	 * @generated
	 */
	String getBasePackage();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.State#getBasePackage <em>Base
	 * Package</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Base Package</em>' attribute.
	 * @see #getBasePackage()
	 * @generated
	 */
	void setBasePackage(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void setProjectVersion(IProgressMonitor monitor) throws CoreException;

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
	 * @model
	 * @generated
	 */
	EObject find(EObject eObject, Stage stage);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void checkout();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void checkout(String commitId, boolean forced);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	void add(String filepattern) throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Commit
	 * changes to SCM.
	 * 
	 * @return commitId <!-- end-model-doc -->
	 * @model exceptions="org.enterprisedomain.classmaker.Exception"
	 * @generated
	 */
	String commit() throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void saveResource();

} // State
