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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stage Qualifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.StageQualifier#getStage <em>Stage</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.StageQualifier#getStep <em>Step</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStageQualifier()
 * @model
 * @generated
 */
public interface StageQualifier extends EObject {
	/**
	 * Returns the value of the '<em><b>Stage</b></em>' attribute.
	 * The literals are from the enumeration {@link org.enterprisedomain.classmaker.Stage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stage</em>' attribute.
	 * @see org.enterprisedomain.classmaker.Stage
	 * @see #setStage(Stage)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStageQualifier_Stage()
	 * @model
	 * @generated
	 */
	Stage getStage();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.StageQualifier#getStage <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stage</em>' attribute.
	 * @see org.enterprisedomain.classmaker.Stage
	 * @see #getStage()
	 * @generated
	 */
	void setStage(Stage value);

	/**
	 * Returns the value of the '<em><b>Step</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Step</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Step</em>' attribute.
	 * @see #setStep(String)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getStageQualifier_Step()
	 * @model
	 * @generated
	 */
	String getStep();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.StageQualifier#getStep <em>Step</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Step</em>' attribute.
	 * @see #getStep()
	 * @generated
	 */
	void setStep(String value);

} // StageQualifier
