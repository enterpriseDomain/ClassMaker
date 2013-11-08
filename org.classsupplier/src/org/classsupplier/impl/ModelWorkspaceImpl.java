/**
 */
package org.classsupplier.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.classsupplier.Bundle;
import org.classsupplier.ClassSupplierPackage;
import org.classsupplier.ModelWorkspace;
import org.classsupplier.State;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Model Workspace</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.classsupplier.impl.ModelWorkspaceImpl#getContents <em>Contents</em>}</li>
 *   <li>{@link org.classsupplier.impl.ModelWorkspaceImpl#getResourceSet <em>Resource Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelWorkspaceImpl extends EObjectImpl implements ModelWorkspace {

	/**
	 * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getContents()
	 * @generated
	 * @ordered
	 */
	protected EList<Bundle> contents;

	/**
	 * The default value of the '{@link #getResourceSet() <em>Resource Set</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResourceSet()
	 * @generated NOT
	 * @ordered
	 */
	protected static final ResourceSet RESOURCE_SET_EDEFAULT = new ResourceSetImpl();

	/**
	 * The cached value of the '{@link #getResourceSet() <em>Resource Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceSet()
	 * @generated
	 * @ordered
	 */
	protected ResourceSet resourceSet = RESOURCE_SET_EDEFAULT;

	protected Map<EPackage, Bundle> models = new HashMap<EPackage, Bundle>();

	private Adapter listeningAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Bundle.class) == ClassSupplierPackage.BUNDLE__STATE
					&& msg.getNewValue() == State.DYNAMIC)
				notifyListeners(new NotificationImpl(msg.getEventType(), null,
						msg.getNotifier()));

		}

	};

	private Adapter attachingAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(getClass()) == ClassSupplierPackage.MODEL_WORKSPACE__CONTENTS)
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
		return ClassSupplierPackage.Literals.MODEL_WORKSPACE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Bundle> getContents() {
		if (contents == null) {
			contents = new EObjectContainmentEList<Bundle>(Bundle.class, this, ClassSupplierPackage.MODEL_WORKSPACE__CONTENTS);
		}
		return contents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSet getResourceSet() {
		return resourceSet;
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void registerBundle(EPackage model, Bundle bundle) {
		models.put(model, bundle);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean containsBundle(EPackage model) {
		return models.containsKey(model);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Bundle getBundle(String projectName) {
		for (Bundle bundle : models.values()) {
			if (bundle.getProjectName().equals(projectName))
				return bundle;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Bundle getBundle(EPackage ePackage) {
		return models.get(ePackage);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClassSupplierPackage.MODEL_WORKSPACE__CONTENTS:
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
			case ClassSupplierPackage.MODEL_WORKSPACE__CONTENTS:
				return getContents();
			case ClassSupplierPackage.MODEL_WORKSPACE__RESOURCE_SET:
				return getResourceSet();
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
			case ClassSupplierPackage.MODEL_WORKSPACE__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection<? extends Bundle>)newValue);
				return;
			case ClassSupplierPackage.MODEL_WORKSPACE__RESOURCE_SET:
				setResourceSet((ResourceSet)newValue);
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
			case ClassSupplierPackage.MODEL_WORKSPACE__CONTENTS:
				getContents().clear();
				return;
			case ClassSupplierPackage.MODEL_WORKSPACE__RESOURCE_SET:
				setResourceSet(RESOURCE_SET_EDEFAULT);
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
			case ClassSupplierPackage.MODEL_WORKSPACE__CONTENTS:
				return contents != null && !contents.isEmpty();
			case ClassSupplierPackage.MODEL_WORKSPACE__RESOURCE_SET:
				return RESOURCE_SET_EDEFAULT == null ? resourceSet != null : !RESOURCE_SET_EDEFAULT.equals(resourceSet);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (resourceSet: ");
		result.append(resourceSet);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceSet(ResourceSet newResourceSet) {
		ResourceSet oldResourceSet = resourceSet;
		resourceSet = newResourceSet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.MODEL_WORKSPACE__RESOURCE_SET, oldResourceSet, resourceSet));
	}

} // ModelWorkspaceImpl
