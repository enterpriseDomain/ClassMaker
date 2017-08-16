/**
 * Copyright 2012-2016 Kyrill Zotkin
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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.enterprisedomain.classmaker.ClassMakerPackage
 * @generated
 */
public interface ClassMakerFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	ClassMakerFactory eINSTANCE = org.enterprisedomain.classmaker.impl.ClassMakerFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Contribution</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Contribution</em>'.
	 * @generated
	 */
	Contribution createContribution();

	/**
	 * Returns a new object of class '<em>Revision</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Revision</em>'.
	 * @generated
	 */
	Revision createRevision();

	/**
	 * Returns a new object of class '<em>State</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return a new object of class '<em>State</em>'.
	 * @generated
	 */
	State createState();

	/**
	 * Returns a new object of class '<em>Workspace</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Workspace</em>'.
	 * @generated
	 */
	Workspace createWorkspace();

	/**
	 * Returns a new object of class '<em>Customizer</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Customizer</em>'.
	 * @generated
	 */
	Customizer createCustomizer();

	/**
	 * Returns a new object of class '<em>Stage Qualifier</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Stage Qualifier</em>'.
	 * @generated
	 */
	StageQualifier createStageQualifier();

	/**
	 * Returns a new object of class '<em>Project</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Project</em>'.
	 * @generated
	 */
	Project createProject();

	/**
	 * Returns a new object of class '<em>Model Pair</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Model Pair</em>'.
	 * @generated
	 */
	ModelPair createModelPair();

	/**
	 * Returns a new object of class '<em>Resource Adapter</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Resource Adapter</em>'.
	 * @generated
	 */
	ResourceAdapter createResourceAdapter();

	/**
	 * Returns a new object of class '<em>Plant</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return a new object of class '<em>Plant</em>'.
	 * @generated
	 */
	ClassMakerPlant createClassMakerPlant();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	ClassMakerPackage getClassMakerPackage();

} // ClassMakerFactory
