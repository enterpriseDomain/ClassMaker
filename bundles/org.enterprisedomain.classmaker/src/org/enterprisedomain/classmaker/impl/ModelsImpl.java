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

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.Models;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.State;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Models</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getDynamic
 * <em>Dynamic</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getGenerated
 * <em>Generated</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getGeneratedEdit
 * <em>Generated Edit</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getGeneratedEditor
 * <em>Generated Editor</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ModelsImpl#getParent
 * <em>Parent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelsImpl extends EObjectImpl implements Models {

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
			if (msg.getNotifier() instanceof Models && msg.getEventType() == Notification.SET) {
				EObject eObject = null;
				switch (msg.getFeatureID(Models.class)) {
				case ClassMakerPackage.MODELS__DYNAMIC:
					eObject = (EObject) msg.getNewValue();
					if (eObject != null && !getParent().getPhase().equals(Stage.LOADED)) {
						getParent().setPhase(Stage.MODELED);
					}
					onDynamicEObject((EObject) msg.getOldValue(), eObject);
					break;
				case ClassMakerPackage.MODELS__GENERATED:
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
	 * The cached value of the '{@link #getDynamic() <em>Dynamic</em>}' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDynamic()
	 * @generated
	 * @ordered
	 */
	protected EObject dynamic;

	/**
	 * The cached value of the '{@link #getGenerated() <em>Generated</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGenerated()
	 * @generated
	 * @ordered
	 */
	protected EObject generated;

	/**
	 * The default value of the '{@link #getGeneratedEdit() <em>Generated
	 * Edit</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGeneratedEdit()
	 * @generated
	 * @ordered
	 */
	protected static final EMFPlugin GENERATED_EDIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratedEdit() <em>Generated Edit</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGeneratedEdit()
	 * @generated
	 * @ordered
	 */
	protected EMFPlugin generatedEdit = GENERATED_EDIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeneratedEditor() <em>Generated
	 * Editor</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGeneratedEditor()
	 * @generated
	 * @ordered
	 */
	protected static final EMFPlugin GENERATED_EDITOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratedEditor() <em>Generated
	 * Editor</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGeneratedEditor()
	 * @generated
	 * @ordered
	 */
	protected EMFPlugin generatedEditor = GENERATED_EDITOR_EDEFAULT;

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
	public EObject getDynamic() {
		return dynamic;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDynamic(EObject newDynamic, NotificationChain msgs) {
		EObject oldDynamic = dynamic;
		dynamic = newDynamic;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.MODELS__DYNAMIC, oldDynamic, newDynamic);
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
	public void setDynamic(EObject newDynamic) {
		if (newDynamic != dynamic) {
			NotificationChain msgs = null;
			if (dynamic != null)
				msgs = ((InternalEObject) dynamic).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.MODELS__DYNAMIC, null, msgs);
			if (newDynamic != null)
				msgs = ((InternalEObject) newDynamic).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.MODELS__DYNAMIC, null, msgs);
			msgs = basicSetDynamic(newDynamic, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODELS__DYNAMIC, newDynamic,
					newDynamic));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject getGenerated() {
		if (generated != null && generated.eIsProxy()) {
			InternalEObject oldGenerated = (InternalEObject) generated;
			generated = eResolveProxy(oldGenerated);
			if (generated != oldGenerated) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClassMakerPackage.MODELS__GENERATED,
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
	public EObject basicGetGenerated() {
		return generated;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGenerated(EObject newGenerated) {
		EObject oldGenerated = generated;
		generated = newGenerated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODELS__GENERATED, oldGenerated,
					generated));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMFPlugin getGeneratedEdit() {
		return generatedEdit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGeneratedEdit(EMFPlugin newGeneratedEdit) {
		EMFPlugin oldGeneratedEdit = generatedEdit;
		generatedEdit = newGeneratedEdit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODELS__GENERATED_EDIT,
					oldGeneratedEdit, generatedEdit));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMFPlugin getGeneratedEditor() {
		return generatedEditor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGeneratedEditor(EMFPlugin newGeneratedEditor) {
		EMFPlugin oldGeneratedEditor = generatedEditor;
		generatedEditor = newGeneratedEditor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.MODELS__GENERATED_EDITOR,
					oldGeneratedEditor, generatedEditor));
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
		case ClassMakerPackage.MODELS__DYNAMIC:
			return basicSetDynamic(null, msgs);
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
		case ClassMakerPackage.MODELS__DYNAMIC:
			return getDynamic();
		case ClassMakerPackage.MODELS__GENERATED:
			if (resolve)
				return getGenerated();
			return basicGetGenerated();
		case ClassMakerPackage.MODELS__GENERATED_EDIT:
			return getGeneratedEdit();
		case ClassMakerPackage.MODELS__GENERATED_EDITOR:
			return getGeneratedEditor();
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
		case ClassMakerPackage.MODELS__DYNAMIC:
			setDynamic((EObject) newValue);
			return;
		case ClassMakerPackage.MODELS__GENERATED:
			setGenerated((EObject) newValue);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EDIT:
			setGeneratedEdit((EMFPlugin) newValue);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EDITOR:
			setGeneratedEditor((EMFPlugin) newValue);
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
		case ClassMakerPackage.MODELS__DYNAMIC:
			setDynamic((EObject) null);
			return;
		case ClassMakerPackage.MODELS__GENERATED:
			setGenerated((EObject) null);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EDIT:
			setGeneratedEdit(GENERATED_EDIT_EDEFAULT);
			return;
		case ClassMakerPackage.MODELS__GENERATED_EDITOR:
			setGeneratedEditor(GENERATED_EDITOR_EDEFAULT);
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
		case ClassMakerPackage.MODELS__DYNAMIC:
			return dynamic != null;
		case ClassMakerPackage.MODELS__GENERATED:
			return generated != null;
		case ClassMakerPackage.MODELS__GENERATED_EDIT:
			return GENERATED_EDIT_EDEFAULT == null ? generatedEdit != null
					: !GENERATED_EDIT_EDEFAULT.equals(generatedEdit);
		case ClassMakerPackage.MODELS__GENERATED_EDITOR:
			return GENERATED_EDITOR_EDEFAULT == null ? generatedEditor != null
					: !GENERATED_EDITOR_EDEFAULT.equals(generatedEditor);
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
		result.append(" (generatedEdit: ");
		result.append(generatedEdit);
		result.append(", generatedEditor: ");
		result.append(generatedEditor);
		result.append(')');
		return result.toString();
	}

} // ModelsImpl
