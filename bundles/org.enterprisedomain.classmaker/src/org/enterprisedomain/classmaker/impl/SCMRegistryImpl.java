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
package org.enterprisedomain.classmaker.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.SCMRegistry;
import org.enterprisedomain.classmaker.scm.GitSCMOperator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>SCM
 * Registry</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class SCMRegistryImpl<T> extends EObjectImpl implements SCMRegistry<T> {

	private Map<String, SCMOperator<T>> operators = new HashMap<String, SCMOperator<T>>();

	private Map<String, T> scms = new HashMap<String, T>();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected SCMRegistryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.SCM_REGISTRY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean contains(String projectName) {
		return operators.containsKey(projectName);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public SCMOperator<T> get(String projectName) {
		if (!contains(projectName))
			put(projectName, createSCMOperator(projectName));
		return operators.get(projectName);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void put(String projectName, SCMOperator<T> operator) {
		operators.put(projectName, operator);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean containsSCM(String projectName) {
		return scms.containsKey(projectName);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public T getSCM(String projectName) {
		if (scms.containsKey(projectName))
			return scms.get(projectName);
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void putSCM(String projectName, T scm) {
		scms.put(projectName, scm);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void removeSCM(String projectName) {
		scms.remove(projectName);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public SCMOperator<T> createSCMOperator(String projectName) {
		SCMOperator<T> operator = (SCMOperator<T>) (new GitSCMOperator());
		operator.setProjectName(projectName);
		return operator;
	}

} // SCMRegistryImpl
