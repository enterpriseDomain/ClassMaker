/**
 */
package org.classupplier.impl;

import java.util.Date;

import org.classupplier.Artifact;
import org.classupplier.ClassSupplierFactory;
import org.classupplier.ClassSupplierPackage;
import org.classupplier.Infrastructure;
import org.classupplier.Phase;
import org.classupplier.State;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Artifact</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.classupplier.impl.ArtifactImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.classupplier.impl.ArtifactImpl#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.classupplier.impl.ArtifactImpl#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArtifactImpl extends StateImpl implements Artifact {

	/**
	 * The default value of the '{@link #getResource() <em>Resource</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected static final Resource RESOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected Resource resource = RESOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSnapshots() <em>Snapshots</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSnapshots()
	 * @generated
	 * @ordered
	 */
	protected EMap<Date, State> snapshots;

	private Adapter adapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(getClass()) == ClassSupplierPackage.ARTIFACT__NAME
					&& msg.getEventType() == Notification.SET
					&& msg.getNewStringValue() != null)
				setProjectName(msg.getNewStringValue().toLowerCase());
		}
	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ArtifactImpl() {
		super();
		eAdapters().add(adapter);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
			return name;
		if (eIsSet(ClassSupplierPackage.ARTIFACT__STATE))
			return getState().getName();
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage basicGetEPackage() {
		State state = getState();
		switch (state.getStage().getValue()) {
		case Phase.MODELED_VALUE:
			return state.getPrototypeEPackage();
		case Phase.LOADED_VALUE:
			return state.getLoadedEPackage();
		default:
			return null;
		}
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
		if (eIsSet(ClassSupplierPackage.ARTIFACT__SNAPSHOTS))
			getState().setProjectName(newProjectName);
	}

	@Override
	public Phase getStage() {
		return getState().getStage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(Resource newResource) {
		Resource oldResource = resource;
		resource = newResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.ARTIFACT__RESOURCE, oldResource, resource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<Date, State> getSnapshots() {
		if (snapshots == null) {
			snapshots = new EcoreEMap<Date,State>(ClassSupplierPackage.Literals.DATE_TO_STATE_MAP_ENTRY, DateToStateMapEntryImpl.class, this, ClassSupplierPackage.ARTIFACT__SNAPSHOTS);
		}
		return snapshots;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public State getState() {
		State state = basicGetState();
		return state != null && state.eIsProxy() ? (State)eResolveProxy((InternalEObject)state) : state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State basicGetState() {
		if (!eIsSet(ClassSupplierPackage.ARTIFACT__TIME))
			if (!getSnapshots().isEmpty())
				return (State) getSnapshots().get(getSnapshots().size() - 1)
						.getValue();
			else
				return null;
		return getSnapshots().get(getTime());
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
		getSnapshots().put(time, newState);
		return newState;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void initState() {
		if (!getSnapshots().isEmpty())
			return;
		apply(newState().getTime());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage getActualEPackage() {
		EPackage actual = null;
		switch (getStage().getValue()) {
		case Phase.MODELED_VALUE:
			actual = getPrototypeEPackage();
			break;
		case Phase.LOADED_VALUE:
			actual = getLoadedEPackage();
			break;
		}
		return actual;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void produce(IProgressMonitor monitor) {
		initState();
		getState().produce(monitor);
		((Infrastructure) eContainer()).save(monitor);
	}

	@Override
	public void setPrototypeEPackage(EPackage newPrototypeEPackage) {
		getState().setPrototypeEPackage(newPrototypeEPackage);
	}

	@Override
	public void setLoadedEPackage(EPackage newLoadedEPackage) {
		getState().setLoadedEPackage(newLoadedEPackage);
	}

	@Override
	public EPackage getPrototypeEPackage() {
		return getState().getPrototypeEPackage();
	}

	@Override
	public EPackage getLoadedEPackage() {
		return getState().getLoadedEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
				return ((InternalEList<?>)getSnapshots()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClassSupplierPackage.ARTIFACT__RESOURCE:
				return getResource();
			case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
				if (coreType) return getSnapshots();
				else return getSnapshots().map();
			case ClassSupplierPackage.ARTIFACT__STATE:
				if (resolve) return getState();
				return basicGetState();
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
			case ClassSupplierPackage.ARTIFACT__RESOURCE:
				setResource((Resource)newValue);
				return;
			case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
				((EStructuralFeature.Setting)getSnapshots()).set(newValue);
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
			case ClassSupplierPackage.ARTIFACT__RESOURCE:
				setResource(RESOURCE_EDEFAULT);
				return;
			case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
				getSnapshots().clear();
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
			case ClassSupplierPackage.ARTIFACT__RESOURCE:
				return RESOURCE_EDEFAULT == null ? resource != null : !RESOURCE_EDEFAULT.equals(resource);
			case ClassSupplierPackage.ARTIFACT__SNAPSHOTS:
				return snapshots != null && !snapshots.isEmpty();
			case ClassSupplierPackage.ARTIFACT__STATE:
				return basicGetState() != null;
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
		result.append(" (resource: ");
		result.append(resource);
		result.append(')');
		return result.toString();
	}

} // ArtifactImpl
