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

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Model
 * Pair</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelPairImpl#getDynamic
 * <em>Dynamic</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelPairImpl#getGenerated
 * <em>Generated</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelPairImpl#getParent
 * <em>Parent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelPairImpl extends EObjectImpl implements ModelPair {

	public class EPackageAdapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == EcorePackage.EPACKAGE__NS_PREFIX)
				switch (msg.getEventType()) {
				case Notification.SET:
				case Notification.ADD:
				case Notification.ADD_MANY:
					capNsPrefix((EPackage) msg.getNotifier());
					break;
				}
		}

		private boolean setting = false;

		private void capNsPrefix(EPackage ePackage) {
			if (!setting && ePackage != null) {
				setting = true;
				ePackage.setNsPrefix(CodeGenUtil.capName(ePackage.getNsPrefix(), ((Item) eContainer()).getLocale()));
				setting = false;
			}
		}
	}

	public class StageAdapter extends AdapterImpl {

		private EPackageAdapter adapter = new EPackageAdapter();

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getNotifier() instanceof ModelPair && msg.getEventType() == Notification.SET) {
				EPackage ePackage = null;
				switch (msg.getFeatureID(ModelPair.class)) {
				case ClassMakerPackage.MODEL_PAIR__DYNAMIC:
					ePackage = (EPackage) msg.getNewValue();
					if (ePackage != null) {
						getParent().setPhase(Stage.MODELED);
					}
					onDynamicEPackage((EPackage) msg.getOldValue(), ePackage);
					break;
				case ClassMakerPackage.MODEL_PAIR__GENERATED:
					ePackage = (EPackage) msg.getNewValue();
					if (ePackage != null) {
						getParent().setPhase(Stage.LOADED);
					}
					break;
				}
			}
		}

		private void onDynamicEPackage(EPackage oldValue, EPackage newValue) {
			if (oldValue != null)
				oldValue.eAdapters().remove(adapter);
			if (newValue != null)
				newValue.eAdapters().add(adapter);
		}
	}

	/**
	 * The cached value of the '{@link #getDynamic() <em>Dynamic</em>}' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDynamic()
	 * @generated
	 * @ordered
	 */
	protected EPackage dynamic;

	/**
	 * The cached value of the '{@link #getGenerated() <em>Generated</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGenerated()
	 * @generated
	 * @ordered
	 */
	protected EPackage generated;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ModelPairImpl() {
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
		return ClassMakerPackage.Literals.MODEL_PAIR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage getDynamic() {
		return dynamic;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDynamic(EPackage newDynamic, NotificationChain msgs) {
		EPackage oldDynamic = dynamic;
		dynamic = newDynamic;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.MODEL_PAIR__DYNAMIC, oldDynamic, newDynamic);
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
	public void setDynamic(EPackage newDynamic) {
		if (newDynamic != dynamic) {
			NotificationChain msgs = null;
			if (dynamic != null)
				msgs = ((InternalEObject) dynamic).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.MODEL_PAIR__DYNAMIC, null, msgs);
			if (newDynamic != null)
				msgs = ((InternalEObject) newDynamic).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.MODEL_PAIR__DYNAMIC, null, msgs);
			msgs = basicSetDynamic(newDynamic, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODEL_PAIR__DYNAMIC, newDynamic,
					newDynamic));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage getGenerated() {
		if (generated != null && generated.eIsProxy()) {
			InternalEObject oldGenerated = (InternalEObject) generated;
			generated = (EPackage) eResolveProxy(oldGenerated);
			if (generated != oldGenerated) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClassMakerPackage.MODEL_PAIR__GENERATED,
							oldGenerated, generated));
			}
		}
		return generated;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage basicGetGenerated() {
		return generated;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGenerated(EPackage newGenerated) {
		EPackage oldGenerated = generated;
		generated = newGenerated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODEL_PAIR__GENERATED, oldGenerated,
					generated));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Item getParent() {
		if (eContainerFeatureID() != ClassMakerPackage.MODEL_PAIR__PARENT)
			return null;
		return (Item) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetParent(Item newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newParent, ClassMakerPackage.MODEL_PAIR__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setParent(Item newParent) {
		if (newParent != eInternalContainer()
				|| (eContainerFeatureID() != ClassMakerPackage.MODEL_PAIR__PARENT && newParent != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODEL_PAIR__PARENT, newParent,
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
		case ClassMakerPackage.MODEL_PAIR__PARENT:
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
		case ClassMakerPackage.MODEL_PAIR__DYNAMIC:
			return basicSetDynamic(null, msgs);
		case ClassMakerPackage.MODEL_PAIR__PARENT:
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
		case ClassMakerPackage.MODEL_PAIR__PARENT:
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
		case ClassMakerPackage.MODEL_PAIR__DYNAMIC:
			return getDynamic();
		case ClassMakerPackage.MODEL_PAIR__GENERATED:
			if (resolve)
				return getGenerated();
			return basicGetGenerated();
		case ClassMakerPackage.MODEL_PAIR__PARENT:
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
		case ClassMakerPackage.MODEL_PAIR__DYNAMIC:
			setDynamic((EPackage) newValue);
			return;
		case ClassMakerPackage.MODEL_PAIR__GENERATED:
			setGenerated((EPackage) newValue);
			return;
		case ClassMakerPackage.MODEL_PAIR__PARENT:
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
		case ClassMakerPackage.MODEL_PAIR__DYNAMIC:
			setDynamic((EPackage) null);
			return;
		case ClassMakerPackage.MODEL_PAIR__GENERATED:
			setGenerated((EPackage) null);
			return;
		case ClassMakerPackage.MODEL_PAIR__PARENT:
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
		case ClassMakerPackage.MODEL_PAIR__DYNAMIC:
			return dynamic != null;
		case ClassMakerPackage.MODEL_PAIR__GENERATED:
			return generated != null;
		case ClassMakerPackage.MODEL_PAIR__PARENT:
			return getParent() != null;
		}
		return super.eIsSet(featureID);
	}

} // ModelPairImpl
