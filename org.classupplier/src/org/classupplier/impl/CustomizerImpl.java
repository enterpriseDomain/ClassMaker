/**
 */
package org.classupplier.impl;

import org.classupplier.ClassSupplierPackage;
import org.classupplier.Customizer;
import org.classupplier.PhaseQualifier;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Customizer</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.classupplier.impl.CustomizerImpl#getAppliesTo <em>Applies To</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomizerImpl extends EObjectImpl implements Customizer {
	/**
	 * The cached value of the '{@link #getAppliesTo() <em>Applies To</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAppliesTo()
	 * @generated
	 * @ordered
	 */
	protected PhaseQualifier appliesTo;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CustomizerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.CUSTOMIZER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PhaseQualifier getAppliesTo() {
		if (appliesTo != null && appliesTo.eIsProxy()) {
			InternalEObject oldAppliesTo = (InternalEObject) appliesTo;
			appliesTo = (PhaseQualifier) eResolveProxy(oldAppliesTo);
			if (appliesTo != oldAppliesTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ClassSupplierPackage.CUSTOMIZER__APPLIES_TO, oldAppliesTo, appliesTo));
			}
		}
		return appliesTo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PhaseQualifier basicGetAppliesTo() {
		return appliesTo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAppliesTo(PhaseQualifier newAppliesTo) {
		PhaseQualifier oldAppliesTo = appliesTo;
		appliesTo = newAppliesTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CUSTOMIZER__APPLIES_TO,
					oldAppliesTo, appliesTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void customize(EList<Object> args) {
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassSupplierPackage.CUSTOMIZER__APPLIES_TO:
			if (resolve)
				return getAppliesTo();
			return basicGetAppliesTo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassSupplierPackage.CUSTOMIZER__APPLIES_TO:
			setAppliesTo((PhaseQualifier) newValue);
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
		case ClassSupplierPackage.CUSTOMIZER__APPLIES_TO:
			setAppliesTo((PhaseQualifier) null);
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
		case ClassSupplierPackage.CUSTOMIZER__APPLIES_TO:
			return appliesTo != null;
		}
		return super.eIsSet(featureID);
	}

} // CustomizerImpl
