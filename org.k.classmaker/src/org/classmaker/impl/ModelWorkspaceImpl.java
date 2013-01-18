/**
 */
package org.classmaker.impl;

import java.util.Collection;

import org.classmaker.Bundle;
import org.classmaker.ClassMakerPackage;
import org.classmaker.ModelWorkspace;
import org.classmaker.State;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Model Workspace</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.classmaker.impl.ModelWorkspaceImpl#getContents <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelWorkspaceImpl extends EObjectImpl implements ModelWorkspace {

	/**
	 * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContents()
	 * @generated
	 * @ordered
	 */
	protected EList<Bundle> contents;

	private Adapter listeningAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Bundle.class) == ClassMakerPackage.BUNDLE__STATE
					&& msg.getNewValue() == State.DYNAMIC)
				notifyListeners(new NotificationImpl(msg.getEventType(), null,
						msg.getNotifier()));

		}

	};

	private Adapter attachingAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(getClass()) == ClassMakerPackage.MODEL_WORKSPACE__CONTENTS)
				switch (msg.getEventType()) {
				case Notification.ADD:
					((EObject) msg.getNewValue()).eAdapters().add(
							listeningAdapter);
					break;
				case Notification.REMOVE:
					((EObject) msg.getOldValue()).eAdapters().remove(
							listeningAdapter);
					break;
				}
		}

	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ModelWorkspaceImpl() {
		super();
		eAdapters().add(attachingAdapter);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.MODEL_WORKSPACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Bundle> getContents() {
		if (contents == null) {
			contents = new EObjectContainmentEList<Bundle>(Bundle.class, this, ClassMakerPackage.MODEL_WORKSPACE__CONTENTS);
		}
		return contents;
	}

	private EAdapterList<Adapter> listeners = new EAdapterList<Adapter>(this);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void addRefreshListener(Adapter listener) {
		listeners.add(listener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void removeRefreshListener(Adapter listener) {
		listeners.remove(listener);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClassMakerPackage.MODEL_WORKSPACE__CONTENTS:
				return ((InternalEList<?>)getContents()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	private void notifyListeners(Notification notification) {
		for (Adapter listener : listeners)
			listener.notifyChanged(notification);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClassMakerPackage.MODEL_WORKSPACE__CONTENTS:
				return getContents();
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
			case ClassMakerPackage.MODEL_WORKSPACE__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection<? extends Bundle>)newValue);
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
			case ClassMakerPackage.MODEL_WORKSPACE__CONTENTS:
				getContents().clear();
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
			case ClassMakerPackage.MODEL_WORKSPACE__CONTENTS:
				return contents != null && !contents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // ModelWorkspaceImpl
