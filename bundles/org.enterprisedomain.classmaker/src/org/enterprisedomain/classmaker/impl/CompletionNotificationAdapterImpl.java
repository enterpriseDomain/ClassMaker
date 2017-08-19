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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.CompletionNotificationAdapter;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Completion Notification Adapter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.CompletionNotificationAdapterImpl#getException
 * <em>Exception</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompletionNotificationAdapterImpl extends EObjectImpl implements CompletionNotificationAdapter {
	/**
	 * The default value of the '{@link #getException() <em>Exception</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getException()
	 * @generated
	 * @ordered
	 */
	protected static final Exception EXCEPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getException() <em>Exception</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getException()
	 * @generated
	 * @ordered
	 */
	protected Exception exception = EXCEPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CompletionNotificationAdapterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.COMPLETION_NOTIFICATION_ADAPTER;
	}

	/**
	 * Returns <code>false</code>
	 * 
	 * @param type
	 *            the type.
	 * @return <code>false</code>
	 */
	public boolean isAdapterForType(Object type) {
		return false;
	}

	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getFeatureID(Project.class) == ClassMakerPackage.PROJECT__NEEDS_COMPLETION_NOTIFICATION
				&& msg.getNewBooleanValue()) {
			exception = EXCEPTION_EDEFAULT;
			try {
				((Project) eContainer()).notifyCompletion();
			} catch (Exception e) {
				exception = e;
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			}
		}
	}

	/*
	 * Javadoc copied from interface.
	 */
	public Notifier getTarget() {
		return null;
	}

	/*
	 * Javadoc copied from interface.
	 */
	public void setTarget(Notifier newTarget) {

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__EXCEPTION:
			return getException();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__EXCEPTION:
			return EXCEPTION_EDEFAULT == null ? exception != null : !EXCEPTION_EDEFAULT.equals(exception);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (exception: ");
		result.append(exception);
		result.append(')');
		return result.toString();
	}

} // CompletionNotificationAdapterImpl
