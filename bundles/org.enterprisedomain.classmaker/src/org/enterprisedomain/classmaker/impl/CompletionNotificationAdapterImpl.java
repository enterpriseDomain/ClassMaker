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

import org.eclipse.core.runtime.IStatus;
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
 * <li>{@link org.enterprisedomain.classmaker.impl.CompletionNotificationAdapterImpl#getError
 * <em>Error</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompletionNotificationAdapterImpl extends EObjectImpl implements CompletionNotificationAdapter {
	/**
	 * The default value of the '{@link #getError() <em>Error</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getError()
	 * @generated
	 * @ordered
	 */
	protected static final IStatus ERROR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getError() <em>Error</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getError()
	 * @generated
	 * @ordered
	 */
	protected IStatus error = ERROR_EDEFAULT;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IStatus getError() {
		return error;
	}

	/**
	 * Returns <code>false</code>
	 * 
	 * @param type the type.
	 * @return <code>false</code>
	 */
	public boolean isAdapterForType(Object type) {
		return false;
	}

	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getFeatureID(Project.class) == ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION
				&& msg.getNewBooleanValue()) {
			error = ERROR_EDEFAULT;
			try {
				((Project) eContainer()).notifyCompletion();
			} catch (NullPointerException e) {
			} catch (Exception e) {
				error = ClassMakerPlugin.createErrorStatus(e);
				ClassMakerPlugin.getInstance().getLog().log(getError());
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__ERROR:
			return getError();
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
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__ERROR:
			return ERROR_EDEFAULT == null ? error != null : !ERROR_EDEFAULT.equals(error);
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (error: ");
		result.append(error);
		result.append(')');
		return result.toString();
	}

} // CompletionNotificationAdapterImpl
