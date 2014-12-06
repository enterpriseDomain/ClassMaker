/**
 */
package org.classupplier.impl;

import java.util.Date;

import org.classupplier.ClassSupplierFactory;
import org.classupplier.ClassSupplierPackage;
import org.classupplier.Contribution;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.Workspace;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Contribution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.classupplier.impl.ContributionImpl#getSnapshots <em>Snapshots
 * </em>}</li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContributionImpl extends StateImpl implements Contribution {
	/**
	 * The cached value of the '{@link #getSnapshots() <em>Snapshots</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSnapshots()
	 * @generated
	 * @ordered
	 */
	protected EMap<Date, State> snapshots;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ContributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.CONTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EMap<Date, State> getSnapshots() {
		if (snapshots == null) {
			snapshots = new EcoreEMap<Date, State>(
					ClassSupplierPackage.Literals.DATE_TO_STATE_MAP_ENTRY,
					DateToStateMapEntryImpl.class, this,
					ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS);
		}
		return snapshots;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public State getState() {
		State state = basicGetState();
		return state != null && state.eIsProxy() ? (State) eResolveProxy((InternalEObject) state)
				: state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State basicGetState() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__TIME)
				&& eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS)
				&& getSnapshots().containsKey(getTime()))
			return getSnapshots().get(getTime());
		return null;

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__NAME))
			return super.getName();
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE))
			return getState().getName();
		return super.getName();
	}

	@Override
	public void setName(String newName) {
		super.setName(newName);
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			getState().setName(newName);
	}

	@Override
	public Version getVersion() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__VERSION))
			return super.getVersion();
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			return getState().getVersion();
		return VERSION_EDEFAULT;
	}

	@Override
	public void setVersion(Version newVersion) {
		super.setVersion(newVersion);
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			getState().setVersion(newVersion);
	}

	@Override
	public String getProjectName() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__PROJECT_NAME))
			return super.getProjectName();
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			return getState().getProjectName();
		return PROJECT_NAME_EDEFAULT;
	}

	@Override
	public void setProjectName(String newProjectName) {
		super.setProjectName(newProjectName);
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE))
			getState().setProjectName(newProjectName);
	}

	@Override
	public Phase getStage() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__STAGE))
			return super.getStage();
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE))
			return getState().getStage();
		return Phase.DEFINED;
	}

	@Override
	public void setStage(Phase newStage) {
		super.setStage(newStage);
		if (eIsSet(ClassSupplierPackage.Literals.CONTRIBUTION__STATE))
			getState().setStage(newStage);
	}

	@Override
	public EPackage getDynamicEPackage() {
		return getState().getDynamicEPackage();
	}

	@Override
	public void setDynamicEPackage(EPackage newDynamicEPackage) {
		getState().setDynamicEPackage(newDynamicEPackage);
	}

	@Override
	public EPackage getRuntimeEPackage() {
		return getState().getRuntimeEPackage();
	}

	@Override
	public void setRuntimeEPackage(EPackage newRuntimeEPackage) {
		getState().setRuntimeEPackage(newRuntimeEPackage);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void apply(Date version) {
		setTime(version);

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State newState() {
		Date time = new Date();
		State newState = ClassSupplierFactory.eINSTANCE.createState();
		newState.setTime(time);
		boolean first = getSnapshots().isEmpty();
		getSnapshots().put(time, newState);
		if (first)
			apply(time);
		return newState;
	}

	@Override
	public EPackage doConstruct(IProgressMonitor monitor) throws Exception {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__RUNTIME_EPACKAGE))
			return getRuntimeEPackage();
		EPackage result = ((InternalState) getState()).doConstruct(monitor);
		((Workspace) eContainer()).save(monitor);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			return ((InternalEList<?>) getSnapshots()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			if (coreType)
				return getSnapshots();
			else
				return getSnapshots().map();
		case ClassSupplierPackage.CONTRIBUTION__STATE:
			if (resolve)
				return getState();
			return basicGetState();
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
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			((EStructuralFeature.Setting) getSnapshots()).set(newValue);
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
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			getSnapshots().clear();
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
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			return snapshots != null && !snapshots.isEmpty();
		case ClassSupplierPackage.CONTRIBUTION__STATE:
			return basicGetState() != null;
		}
		return super.eIsSet(featureID);
	}

} // ContributionImpl
