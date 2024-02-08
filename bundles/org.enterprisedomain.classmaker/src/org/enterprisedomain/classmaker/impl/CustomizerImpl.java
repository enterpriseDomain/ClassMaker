/**
 */
package org.enterprisedomain.classmaker.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.StageQualifier;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Customizer</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.CustomizerImpl#getRank
 * <em>Rank</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.CustomizerImpl#isExclusive
 * <em>Exclusive</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.CustomizerImpl#getStage
 * <em>Stage</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomizerImpl extends EObjectImpl implements Customizer {
	/**
	 * The default value of the '{@link #getRank() <em>Rank</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected static final int RANK_EDEFAULT = 0;
	/**
	 * The cached value of the '{@link #getRank() <em>Rank</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected int rank = RANK_EDEFAULT;

	/**
	 * The default value of the '{@link #isExclusive() <em>Exclusive</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isExclusive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXCLUSIVE_EDEFAULT = true;
	/**
	 * The cached value of the '{@link #isExclusive() <em>Exclusive</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isExclusive()
	 * @generated
	 * @ordered
	 */
	protected boolean exclusive = EXCLUSIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStage() <em>Stage</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected StageQualifier stage;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public CustomizerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.CUSTOMIZER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int getRank() {
		return rank;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setRank(int newRank) {
		int oldRank = rank;
		rank = newRank;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CUSTOMIZER__RANK, oldRank, rank));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isExclusive() {
		return exclusive;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setExclusive(boolean newExclusive) {
		boolean oldExclusive = exclusive;
		exclusive = newExclusive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CUSTOMIZER__EXCLUSIVE, oldExclusive,
					exclusive));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public StageQualifier getStage() {
		if (stage != null && stage.eIsProxy()) {
			InternalEObject oldStage = (InternalEObject) stage;
			stage = (StageQualifier) eResolveProxy(oldStage);
			if (stage != oldStage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClassMakerPackage.CUSTOMIZER__STAGE,
							oldStage, stage));
			}
		}
		return stage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public StageQualifier basicGetStage() {
		return stage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setStage(StageQualifier newStage) {
		StageQualifier oldStage = stage;
		stage = newStage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CUSTOMIZER__STAGE, oldStage,
					stage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object customize(EList<Object> args) {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isNextAfter(Class<? extends Customizer> customizerClass) {
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.CUSTOMIZER__RANK:
			return getRank();
		case ClassMakerPackage.CUSTOMIZER__EXCLUSIVE:
			return isExclusive();
		case ClassMakerPackage.CUSTOMIZER__STAGE:
			if (resolve)
				return getStage();
			return basicGetStage();
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
		case ClassMakerPackage.CUSTOMIZER__RANK:
			setRank((Integer) newValue);
			return;
		case ClassMakerPackage.CUSTOMIZER__EXCLUSIVE:
			setExclusive((Boolean) newValue);
			return;
		case ClassMakerPackage.CUSTOMIZER__STAGE:
			setStage((StageQualifier) newValue);
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
		case ClassMakerPackage.CUSTOMIZER__RANK:
			setRank(RANK_EDEFAULT);
			return;
		case ClassMakerPackage.CUSTOMIZER__EXCLUSIVE:
			setExclusive(EXCLUSIVE_EDEFAULT);
			return;
		case ClassMakerPackage.CUSTOMIZER__STAGE:
			setStage((StageQualifier) null);
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
		case ClassMakerPackage.CUSTOMIZER__RANK:
			return rank != RANK_EDEFAULT;
		case ClassMakerPackage.CUSTOMIZER__EXCLUSIVE:
			return exclusive != EXCLUSIVE_EDEFAULT;
		case ClassMakerPackage.CUSTOMIZER__STAGE:
			return stage != null;
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
		result.append(" (rank: ");
		result.append(rank);
		result.append(", exclusive: ");
		result.append(exclusive);
		result.append(')');
		return result.toString();
	}

} // CustomizerImpl
