/**
 * Copyright 2021 Kyrill Zotkin
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

import javax.management.Notification;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.Models;
import org.enterprisedomain.classmaker.Stage;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Models</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getDynamicEPackage
 * <em>Dynamic EPackage</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getGeneratedEPackage
 * <em>Generated EPackage</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getGeneratedEditPlugin
 * <em>Generated Edit Plugin</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getGeneratedEditorPlugin
 * <em>Generated Editor Plugin</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getParent
 * <em>Parent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelsImpl extends EObjectImpl implements Models {

	/**
	 * The cached value of the '{@link #getDynamicEPackage() <em>Dynamic
	 * EPackage</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDynamicEPackage()
	 * @generated
	 * @ordered
	 */
	protected EObject dynamicEPackage;

	/**
	 * The cached value of the '{@link #getGeneratedEPackage() <em>Generated
	 * EPackage</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGeneratedEPackage()
	 * @generated
	 * @ordered
	 */
	protected EObject generatedEPackage;

	/**
	 * The default value of the '{@link #getGeneratedEditPlugin() <em>Generated Edit
	 * Plugin</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGeneratedEditPlugin()
	 * @generated
	 * @ordered
	 */
	protected static final EMFPlugin GENERATED_EDIT_PLUGIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratedEditPlugin() <em>Generated Edit
	 * Plugin</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGeneratedEditPlugin()
	 * @generated
	 * @ordered
	 */
	protected EMFPlugin generatedEditPlugin = GENERATED_EDIT_PLUGIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeneratedEditorPlugin() <em>Generated
	 * Editor Plugin</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGeneratedEditorPlugin()
	 * @generated
	 * @ordered
	 */
	protected static final EMFPlugin GENERATED_EDITOR_PLUGIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratedEditorPlugin() <em>Generated
	 * Editor Plugin</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGeneratedEditorPlugin()
	 * @generated
	 * @ordered
	 */
	protected EMFPlugin generatedEditorPlugin = GENERATED_EDITOR_PLUGIN_EDEFAULT;

	public class EPackageAdapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			// if (msg.getFeatureID(EPackage.class) == Notification.NO_FEATURE_ID
			// && msg.getEventType() != Notification.REMOVING_ADAPTER)
			//// System.out.println(msg.toString());
			// switch (msg.getEventType()) {
			// case Notification.SET:
			// case Notification.ADD:
			// case Notification.ADD_MANY:
			// capNsPrefix((EPackage) msg.getNotifier());
			// break;
			// }
			if (msg.getEventType() != Notification.REMOVING_ADAPTER)
				System.out.println(msg);
		}

		// private boolean setting = false;
		//
		// private void capNsPrefix(EPackage ePackage) {
		// if (!setting && ePackage != null) {
		// setting = true;
		// ePackage.setNsPrefix(CodeGenUtil.capName(ePackage.getNsPrefix(), ((Item)
		// eContainer()).getLocale()));
		// setting = false;
		// }
		// }
	}

	public class StageAdapter extends AdapterImpl {

		private EPackageAdapter adapter = new EPackageAdapter();

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getNotifier() instanceof Models && msg.getEventType() == Notification.SET) {
				EObject eObject = null;
				switch (msg.getFeatureID(Models.class)) {
				case ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE:
					eObject = (EObject) msg.getNewValue();
					if (eObject != null) {
						getParent().setPhase(Stage.MODELED);
					}
					onDynamicEObject((EObject) msg.getOldValue(), eObject);
					if (eObject instanceof EPackage) {
						String modelName = ((EPackage) eObject).getNsPrefix();
						getParent().setModelName(modelName);
					}
					break;
				case ClassMakerPackage.MODELS__GENERATED_EPACKAGE:
					eObject = (EPackage) msg.getNewValue();
					if (eObject != null) {
						getParent().setPhase(Stage.LOADED);
					}
					break;
				}
			}
		}

		private void onDynamicEObject(EObject oldValue, EObject newValue) {
			if (oldValue != null)
				oldValue.eAdapters().remove(adapter);
			if (newValue != null)
				newValue.eAdapters().add(adapter);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ModelsImpl() {
		super();
		eAdapters().add(new StageAdapter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.MODELS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject getDynamicEPackage() {
		return dynamicEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDynamicEPackage(EObject newDynamicEPackage, NotificationChain msgs) {
		EObject oldDynamicEPackage = dynamicEPackage;
		dynamicEPackage = newDynamicEPackage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE, oldDynamicEPackage, newDynamicEPackage);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDynamicEPackage(EObject newDynamicEPackage) {
		if (newDynamicEPackage != dynamicEPackage) {
			NotificationChain msgs = null;
			if (dynamicEPackage != null)
				msgs = ((InternalEObject) dynamicEPackage).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE, null, msgs);
			if (newDynamicEPackage != null)
				msgs = ((InternalEObject) newDynamicEPackage).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE, null, msgs);
			msgs = basicSetDynamicEPackage(newDynamicEPackage, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE,
					newDynamicEPackage, newDynamicEPackage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject getGeneratedEPackage() {
		if (generatedEPackage != null && generatedEPackage.eIsProxy()) {
			InternalEObject oldGeneratedEPackage = (InternalEObject) generatedEPackage;
			generatedEPackage = eResolveProxy(oldGeneratedEPackage);
			if (generatedEPackage != oldGeneratedEPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ClassMakerPackage.MODELS__GENERATED_EPACKAGE, oldGeneratedEPackage, generatedEPackage));
			}
		}
		return generatedEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EObject basicGetGeneratedEPackage() {
		return generatedEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGeneratedEPackage(EObject newGeneratedEPackage) {
		EObject oldGeneratedEPackage = generatedEPackage;
		generatedEPackage = newGeneratedEPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODELS__GENERATED_EPACKAGE,
					oldGeneratedEPackage, generatedEPackage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMFPlugin getGeneratedEditPlugin() {
		return generatedEditPlugin;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGeneratedEditPlugin(EMFPlugin newGeneratedEditPlugin) {
		EMFPlugin oldGeneratedEditPlugin = generatedEditPlugin;
		generatedEditPlugin = newGeneratedEditPlugin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODELS__GENERATED_EDIT_PLUGIN,
					oldGeneratedEditPlugin, generatedEditPlugin));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMFPlugin getGeneratedEditorPlugin() {
		return generatedEditorPlugin;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGeneratedEditorPlugin(EMFPlugin newGeneratedEditorPlugin) {
		EMFPlugin oldGeneratedEditorPlugin = generatedEditorPlugin;
		generatedEditorPlugin = newGeneratedEditorPlugin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODELS__GENERATED_EDITOR_PLUGIN,
					oldGeneratedEditorPlugin, generatedEditorPlugin));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Item getParent() {
		if (eContainerFeatureID() != ClassMakerPackage.MODELS__PARENT)
			return null;
		return (Item) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetParent(Item newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newParent, ClassMakerPackage.MODELS__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setParent(Item newParent) {
		if (newParent != eInternalContainer()
				|| (eContainerFeatureID() != ClassMakerPackage.MODELS__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject) newParent).eInverseAdd(this, ClassMakerPackage.ITEM__DOMAIN_MODEL, Item.class,
						msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODELS__PARENT, newParent,
					newParent));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.MODELS__PARENT:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetParent((Item) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE:
			return basicSetDynamicEPackage(null, msgs);
		case ClassMakerPackage.MODELS__PARENT:
			return basicSetParent(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ClassMakerPackage.MODELS__PARENT:
			return eInternalContainer().eInverseRemove(this, ClassMakerPackage.ITEM__DOMAIN_MODEL, Item.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE:
			return getDynamicEPackage();
		case ClassMakerPackage.MODELS__GENERATED_EPACKAGE:
			if (resolve)
				return getGeneratedEPackage();
			return basicGetGeneratedEPackage();
		case ClassMakerPackage.MODELS__GENERATED_EDIT_PLUGIN:
			return getGeneratedEditPlugin();
		case ClassMakerPackage.MODELS__GENERATED_EDITOR_PLUGIN:
			return getGeneratedEditorPlugin();
		case ClassMakerPackage.MODELS__PARENT:
			return getParent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EObject) newValue);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EPACKAGE:
			setGeneratedEPackage((EObject) newValue);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EDIT_PLUGIN:
			setGeneratedEditPlugin((EMFPlugin) newValue);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EDITOR_PLUGIN:
			setGeneratedEditorPlugin((EMFPlugin) newValue);
			return;
		case ClassMakerPackage.MODELS__PARENT:
			setParent((Item) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EObject) null);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EPACKAGE:
			setGeneratedEPackage((EObject) null);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EDIT_PLUGIN:
			setGeneratedEditPlugin(GENERATED_EDIT_PLUGIN_EDEFAULT);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EDITOR_PLUGIN:
			setGeneratedEditorPlugin(GENERATED_EDITOR_PLUGIN_EDEFAULT);
			return;
		case ClassMakerPackage.MODELS__PARENT:
			setParent((Item) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ClassMakerPackage.MODELS__DYNAMIC_EPACKAGE:
			return dynamicEPackage != null;
		case ClassMakerPackage.MODELS__GENERATED_EPACKAGE:
			return generatedEPackage != null;
		case ClassMakerPackage.MODELS__GENERATED_EDIT_PLUGIN:
			return GENERATED_EDIT_PLUGIN_EDEFAULT == null ? generatedEditPlugin != null
					: !GENERATED_EDIT_PLUGIN_EDEFAULT.equals(generatedEditPlugin);
		case ClassMakerPackage.MODELS__GENERATED_EDITOR_PLUGIN:
			return GENERATED_EDITOR_PLUGIN_EDEFAULT == null ? generatedEditorPlugin != null
					: !GENERATED_EDITOR_PLUGIN_EDEFAULT.equals(generatedEditorPlugin);
		case ClassMakerPackage.MODELS__PARENT:
			return getParent() != null;
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
		result.append(" (generatedEditPlugin: ");
		result.append(generatedEditPlugin);
		result.append(", generatedEditorPlugin: ");
		result.append(generatedEditorPlugin);
		result.append(')');
		return result.toString();
	}

} // ModelsImpl
