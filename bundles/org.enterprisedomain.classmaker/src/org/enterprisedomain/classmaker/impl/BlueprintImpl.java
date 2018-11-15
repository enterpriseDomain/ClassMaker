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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.enterprisedomain.classmaker.Blueprint;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.CompletionListener;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Blueprint</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.impl.BlueprintImpl#getDynamicModel <em>Dynamic Model</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.BlueprintImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.BlueprintImpl#getCompletionListeners <em>Completion Listeners</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BlueprintImpl extends EObjectImpl implements Blueprint {
	/**
	 * The cached value of the '{@link #getDynamicModel() <em>Dynamic Model</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDynamicModel()
	 * @generated
	 * @ordered
	 */
	protected EPackage dynamicModel;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<String> dependencies;

	/**
	 * The cached value of the '{@link #getCompletionListeners() <em>Completion
	 * Listeners</em>}' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getCompletionListeners()
	 * @generated
	 * @ordered
	 */
	protected EList<CompletionListener> completionListeners;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected BlueprintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.BLUEPRINT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getDynamicModel() {
		if (dynamicModel != null && dynamicModel.eIsProxy()) {
			InternalEObject oldDynamicModel = (InternalEObject) dynamicModel;
			dynamicModel = (EPackage) eResolveProxy(oldDynamicModel);
			if (dynamicModel != oldDynamicModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ClassMakerPackage.BLUEPRINT__DYNAMIC_MODEL, oldDynamicModel, dynamicModel));
			}
		}
		return dynamicModel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage basicGetDynamicModel() {
		return dynamicModel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDynamicModel(EPackage newDynamicModel) {
		EPackage oldDynamicModel = dynamicModel;
		dynamicModel = newDynamicModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.BLUEPRINT__DYNAMIC_MODEL,
					oldDynamicModel, dynamicModel));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDependencies() {
		if (dependencies == null) {
			dependencies = new EDataTypeUniqueEList<String>(String.class, this,
					ClassMakerPackage.BLUEPRINT__DEPENDENCIES);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompletionListener> getCompletionListeners() {
		if (completionListeners == null) {
			completionListeners = new EObjectResolvingEList<CompletionListener>(CompletionListener.class, this,
					ClassMakerPackage.BLUEPRINT__COMPLETION_LISTENERS);
		}
		return completionListeners;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.BLUEPRINT__DYNAMIC_MODEL:
			if (resolve)
				return getDynamicModel();
			return basicGetDynamicModel();
		case ClassMakerPackage.BLUEPRINT__DEPENDENCIES:
			return getDependencies();
		case ClassMakerPackage.BLUEPRINT__COMPLETION_LISTENERS:
			return getCompletionListeners();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassMakerPackage.BLUEPRINT__DYNAMIC_MODEL:
			setDynamicModel((EPackage) newValue);
			return;
		case ClassMakerPackage.BLUEPRINT__DEPENDENCIES:
			getDependencies().clear();
			getDependencies().addAll((Collection<? extends String>) newValue);
			return;
		case ClassMakerPackage.BLUEPRINT__COMPLETION_LISTENERS:
			getCompletionListeners().clear();
			getCompletionListeners().addAll((Collection<? extends CompletionListener>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ClassMakerPackage.BLUEPRINT__DYNAMIC_MODEL:
			setDynamicModel((EPackage) null);
			return;
		case ClassMakerPackage.BLUEPRINT__DEPENDENCIES:
			getDependencies().clear();
			return;
		case ClassMakerPackage.BLUEPRINT__COMPLETION_LISTENERS:
			getCompletionListeners().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ClassMakerPackage.BLUEPRINT__DYNAMIC_MODEL:
			return dynamicModel != null;
		case ClassMakerPackage.BLUEPRINT__DEPENDENCIES:
			return dependencies != null && !dependencies.isEmpty();
		case ClassMakerPackage.BLUEPRINT__COMPLETION_LISTENERS:
			return completionListeners != null && !completionListeners.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (dependencies: ");
		result.append(dependencies);
		result.append(')');
		return result.toString();
	}

} // BlueprintImpl
