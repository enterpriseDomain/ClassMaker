/**
 * Copyright 2016 Kyrill Zotkin
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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Resource
 * Adapter</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> {@link org.eclipse.emf.ecore.EObject EObject}
 * {@link org.eclipse.emf.common.notify.Adapter adapter} for
 * {@link org.eclipse.emf.ecore.resource.Resource Resource}. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.ResourceAdapter#getResource
 * <em>Resource</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.ResourceAdapter#getFilename
 * <em>Filename</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.ResourceAdapter#getProject
 * <em>Project</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getResourceAdapter()
 * @model superTypes="org.enterprisedomain.classmaker.Adapter"
 * @generated
 */
public interface ResourceAdapter extends EObject, Adapter {

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
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getResourceAdapter_Resource()
	 * @model type="org.enterprisedomain.classmaker.Resource"
	 * @generated
	 */
	Resource getResource();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.ResourceAdapter#getResource
	 * <em>Resource</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Resource</em>' reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(Resource value);

	/**
	 * Returns the value of the '<em><b>Filename</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filename</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Filename</em>' attribute.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getResourceAdapter_Filename()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getFilename();

	/**
	 * Returns the value of the '<em><b>Project</b></em>' container reference. It is
	 * bidirectional and its opposite is
	 * '{@link org.enterprisedomain.classmaker.Contribution#getModelResourceAdapter
	 * <em>Model Resource Adapter</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Project</em>' container reference.
	 * @see #setProject(Contribution)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getResourceAdapter_Project()
	 * @see org.enterprisedomain.classmaker.Contribution#getModelResourceAdapter
	 * @model opposite="modelResourceAdapter" transient="false"
	 * @generated
	 */
	Contribution getProject();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.ResourceAdapter#getProject
	 * <em>Project</em>}' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Project</em>' container reference.
	 * @see #getProject()
	 * @generated
	 */
	void setProject(Contribution value);
} // ResourceAdapter
