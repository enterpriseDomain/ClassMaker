/**
 */
package org.classupplier.impl;

import java.util.Date;

import org.classupplier.Artifact;
import org.classupplier.ClassSupplierFactory;
import org.classupplier.ClassSupplierPackage;
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
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Artifact</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.classupplier.impl.ArtifactImpl#getSnapshots <em>Snapshots
 * </em>}</li>
 * <li>{@link org.classupplier.impl.ArtifactImpl#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArtifactImpl extends StateImpl implements Artifact {

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
	protected ArtifactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.ARTIFACT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		if (eIsSet(ClassSupplierPackage.ARTIFACT__NAME))
			return super.getName();
		if (eIsSet(ClassSupplierPackage.ARTIFACT__STATE))
			return getState().getName();
		return super.getName();
	}

	@Override
	public void setName(String newName) {
		super.setName(newName);
		if (eIsSet(ClassSupplierPackage.ARTIFACT__SNAPSHOTS))
			getState().setName(newName);
	}

	@Override
	public Version getVersion() {
		if (eIsSet(ClassSupplierPackage.ARTIFACT__VERSION))
			return super.getVersion();
		if (eIsSet(ClassSupplierPackage.ARTIFACT__SNAPSHOTS))
			return getState().getVersion();
		return VERSION_EDEFAULT;
	}

	@Override
	public void setVersion(Version newVersion) {
		super.setVersion(newVersion);
		if (eIsSet(ClassSupplierPackage.ARTIFACT__SNAPSHOTS))
			getState().setVersion(newVersion);
	}

	@Override
	public String getProjectName() {
		if (eIsSet(ClassSupplierPackage.ARTIFACT__PROJECT_NAME))
			return super.getProjectName();
		if (eIsSet(ClassSupplierPackage.ARTIFACT__SNAPSHOTS))
			return getState().getProjectName();
		return PROJECT_NAME_EDEFAULT;
	}

	@Override
	public void setProjectName(String newProjectName) {
		super.setProjectName(newProjectName);
		if (eIsSet(ClassSupplierPackage.ARTIFACT__STATE))
			getState().setProjectName(newProjectName);
	}

	@Override
	public Phase getStage() {
		if (eIsSet(ClassSupplierPackage.ARTIFACT__STAGE))
			return super.getStage();
		if (eIsSet(ClassSupplierPackage.ARTIFACT__STATE))
			return getState().getStage();
		return Phase.NEW;
	}

	@Override
	public void setStage(Phase newStage) {
		super.setStage(newStage);
		getState().setStage(newStage);
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
					ClassSupplierPackage.ARTIFACT__SNAPSHOTS);
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
		if (eIsSet(ClassSupplierPackage.ARTIFACT__TIME)
				&& eIsSet(ClassSupplierPackage.ARTIFACT__SNAPSHOTS)
				&& getSnapshots().containsKey(getTime()))
			return getSnapshots().get(getTime());
		return null;
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State getOrCreateState() {
		if (!eIsSet(ClassSupplierPackage.ARTIFACT__STATE))
			return newState();
		return getState();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void produce(IProgressMonitor monitor) {
		if (eIsSet(ClassSupplierPackage.ARTIFACT__RUNTIME_EPACKAGE)) {
			setRuntimeEPackage(getRuntimeEPackage());
			return;
		}
		getState().produce(monitor);
		((Workspace) eContainer()).save(monitor);
	}

	@Override
	public void setDynamicEPackage(EPackage newDynamicEPackage) {
		getState().setDynamicEPackage(newDynamicEPackage);
	}

	@Override
	public void setRuntimeEPackage(EPackage newRuntimeEPackage) {
		getState().setRuntimeEPackage(newRuntimeEPackage);
	}

	@Override
	public EPackage getDynamicEPackage() {
		return getState().getDynamicEPackage();
	}

	@Override
	public EPackage getRuntimeEPackage() {
		return getState().getRuntimeEPackage();
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
		case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
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
		case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
			if (coreType)
				return getSnapshots();
			else
				return getSnapshots().map();
		case ClassSupplierPackage.ARTIFACT__STATE:
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
		case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
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
		case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
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
		case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
			return snapshots != null && !snapshots.isEmpty();
		case ClassSupplierPackage.ARTIFACT__STATE:
			return basicGetState() != null;
		}
		return super.eIsSet(featureID);
	}

} // ArtifactImpl
