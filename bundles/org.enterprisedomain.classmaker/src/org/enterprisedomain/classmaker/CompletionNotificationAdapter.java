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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Completion Notification Adapter</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> {@link org.eclipse.emf.common.notify.Adapter
 * Adapter} for notification about process completion. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.CompletionNotificationAdapter#getError
 * <em>Error</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.CompletionNotificationAdapter#getProject
 * <em>Project</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getCompletionNotificationAdapter()
 * @model superTypes="org.enterprisedomain.classmaker.Adapter"
 * @generated
 */
public interface CompletionNotificationAdapter extends EObject, Adapter {
	/**
	 * Returns the value of the '<em><b>Error</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Error</em>' attribute.
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getCompletionNotificationAdapter_Error()
	 * @model dataType="org.enterprisedomain.classmaker.IStatus" changeable="false"
	 * @generated
	 */
	IStatus getError();

	/**
	 * Returns the value of the '<em><b>Project</b></em>' reference. It is
	 * bidirectional and its opposite is
	 * '{@link org.enterprisedomain.classmaker.Project#getCompletionNotificationAdapter
	 * <em>Completion Notification Adapter</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the value of the '<em>Project</em>' reference.
	 * @see #setProject(Project)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getCompletionNotificationAdapter_Project()
	 * @see org.enterprisedomain.classmaker.Project#getCompletionNotificationAdapter
	 * @model opposite="completionNotificationAdapter"
	 * @generated
	 */
	Project getProject();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.CompletionNotificationAdapter#getProject
	 * <em>Project</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Project</em>' reference.
	 * @see #getProject()
	 * @generated
	 */
	void setProject(Project value);

} // CompletionNotificationAdapter
