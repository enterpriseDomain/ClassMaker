/**
 * Copyright 2017 Kyrill Zotkin
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

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.enterprisedomain.classmaker.scm.GitSCMRegistry;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Plant</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> ClassMaker's facade API service. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.ClassMakerPlant#getWorkspace
 * <em>Workspace</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.ClassMakerPlant#getSCMRegistry
 * <em>SCM Registry</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getClassMakerPlant()
 * @model
 * @generated
 */
public interface ClassMakerPlant extends EObject {

	public static final String INVOCATION_DELEGATE_URI = "org.enterprisedomain.classmaker.reflection.java"; //$NON-NLS-1$

	public abstract class Stages {

		public static StageQualifier GENMODEL_SETUP = createStageQualifier(Stage.GENERATED, "genmodel.setup");

		private static StageQualifier createStageQualifier(Stage phase, String step) {
			StageQualifier stageQualifier = ClassMakerFactory.eINSTANCE.createStageQualifier();
			stageQualifier.setStage(phase);
			stageQualifier.setStep(step);
			return stageQualifier;
		}

	}

	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workspace</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Workspace</em>' reference.
	 * @see #setWorkspace(Workspace)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getClassMakerPlant_Workspace()
	 * @model
	 * @generated
	 */
	Workspace getWorkspace();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.ClassMakerPlant#getWorkspace
	 * <em>Workspace</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Workspace</em>' reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Workspace value);

	/**
	 * Returns the value of the '<em><b>SCM Registry</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SCM Registry</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>SCM Registry</em>' attribute.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getClassMakerPlant_SCMRegistry()
	 * @model dataType="org.enterprisedomain.classmaker.GitSCMRegistry"
	 *        changeable="false"
	 * @generated
	 */
	GitSCMRegistry getSCMRegistry();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Performs EPackage generation, build and export, then installs results into
	 * OSGi container and loads generated EPackage class. <!-- end-model-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 * @generated
	 */
	EPackage make(EPackage dynamicModel) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 * @generated
	 */
	Future<? extends EPackage> make(EPackage dynamicModel, Executor executor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EPackage make(EPackage dynamicModel, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EPackage> make(EPackage dynamicModel, Executor executor, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        dependenciesMany="true"
	 * @generated
	 */
	EPackage make(EPackage dynamicModel, EList<String> dependencies) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        dependenciesMany="true"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 * @generated
	 */
	Future<? extends EPackage> make(EPackage dynamicModel, EList<String> dependencies, Executor executor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        dependenciesMany="true"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EPackage make(EPackage dynamicModel, EList<String> dependencies, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        dependenciesMany="true"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EPackage> make(EPackage dynamicModel, EList<String> dependencies, Executor executor,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 * @generated
	 */
	EPackage replace(EPackage queryModel, EPackage dynamicModel) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 * @generated
	 */
	Future<? extends EPackage> replace(EPackage queryModel, EPackage dynamicModel, Executor executor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EPackage replace(EPackage queryModel, EPackage dynamicModel, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 * @generated
	 */
	Future<? extends EPackage> replace(EPackage queryModel, EPackage dynamicModel, IProgressMonitor monitor,
			Executor executor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 * @generated
	 */
	EPackage replace(EPackage queryModel, EPackage dynamicModel, boolean changeVersion) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 * @generated
	 */
	Future<? extends EPackage> replace(EPackage queryModel, EPackage dynamicModel, boolean changeVersion,
			Executor executor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EPackage replace(EPackage queryModel, EPackage dynamicModel, boolean changeVersion, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 * @generated
	 */
	Future<? extends EPackage> replace(EPackage queryModel, EPackage dynamicModel, boolean changeVersion,
			IProgressMonitor monitor, Executor executor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 * @generated
	 */
	EPackage replace(EPackage queryModel, EPackage dynamicModel, Version version) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 * @generated
	 */
	Future<? extends EPackage> replace(EPackage queryModel, EPackage dynamicModel, Version version, Executor executor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EPackage replace(EPackage queryModel, EPackage dynamicModel, Version version, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 * @generated
	 */
	Future<? extends EPackage> replace(EPackage queryModel, EPackage dynamicModel, Version version,
			IProgressMonitor monitor, Executor executor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        transformationURIDataType="org.enterprisedomain.classmaker.URI"
	 * @generated
	 */
	EObject transform(EObject source, URI transformationURI) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        transformationURIDataType="org.enterprisedomain.classmaker.URI"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject transform(EObject source, URI transformationURI, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.InvocationTargetException"
	 *        argumentsMany="true"
	 * @generated
	 */
	Object invoke(EOperation operation, EObject object, EList<Object> arguments) throws InvocationTargetException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void delete(String packageName, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	String computeProjectName(String packageName);

} // ClassMakerPlant
