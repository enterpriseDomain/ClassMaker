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
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.CompletionNotificationAdapter#getError
 * <em>Error</em>}</li>
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

} // CompletionNotificationAdapter
