/**
 */
package org.classupplier.impl;

import java.util.Date;
import java.util.concurrent.Semaphore;

import org.classupplier.ClassSupplierFactory;
import org.classupplier.ClassSupplierPackage;
import org.classupplier.Contribution;
import org.classupplier.Phase;
import org.classupplier.State;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.concurrent.future.IFuture;
import org.eclipse.equinox.concurrent.future.IProgressRunnable;
import org.eclipse.equinox.concurrent.future.ThreadsExecutor;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Contribution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.classupplier.impl.ContributionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.classupplier.impl.ContributionImpl#getTime <em>Time</em>}</li>
 *   <li>{@link org.classupplier.impl.ContributionImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.classupplier.impl.ContributionImpl#getStage <em>Stage</em>}</li>
 *   <li>{@link org.classupplier.impl.ContributionImpl#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link org.classupplier.impl.ContributionImpl#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.classupplier.impl.ContributionImpl#getState <em>State</em>}</li>
 *   <li>{@link org.classupplier.impl.ContributionImpl#getDynamicEPackage <em>Dynamic EPackage</em>}</li>
 *   <li>{@link org.classupplier.impl.ContributionImpl#getGeneratedEPackage <em>Generated EPackage</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContributionImpl extends EObjectImpl implements Contribution {

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected Date time = TIME_EDEFAULT;
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = null;
	/**
	 * The default value of the '{@link #getStage() <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected static final Phase STAGE_EDEFAULT = Phase.GENERATED;
	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getSnapshots() <em>Snapshots</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSnapshots()
	 * @generated
	 * @ordered
	 */
	protected EMap<Date, State> snapshots;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ContributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.CONTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<Date, State> getSnapshots() {
		if (snapshots == null) {
			snapshots = new EcoreEMap<Date, State>(ClassSupplierPackage.Literals.DATE_TO_STATE_MAP_ENTRY,
					DateToStateMapEntryImpl.class, this, ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS);
		}
		return snapshots;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public State getState() {
		State state = basicGetState();
		return state != null && state.eIsProxy() ? (State) eResolveProxy((InternalEObject) state) : state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State basicGetState() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__TIME) && eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS)
				&& getSnapshots().containsKey(getTime()))
			return getSnapshots().get(getTime());
		return null;

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getDynamicEPackage() {
		EPackage dynamicEPackage = basicGetDynamicEPackage();
		return dynamicEPackage != null && dynamicEPackage.eIsProxy()
				? (EPackage) eResolveProxy((InternalEObject) dynamicEPackage) : dynamicEPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EPackage basicGetDynamicEPackage() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			return getState().getDynamicEPackage();
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setDynamicEPackage(EPackage newDynamicEPackage) {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			getState().setDynamicEPackage(newDynamicEPackage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getGeneratedEPackage() {
		EPackage generatedEPackage = basicGetGeneratedEPackage();
		return generatedEPackage != null && generatedEPackage.eIsProxy()
				? (EPackage) eResolveProxy((InternalEObject) generatedEPackage) : generatedEPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EPackage basicGetGeneratedEPackage() {
		return getState().getGeneratedEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public synchronized IFuture<? extends EPackage> construct(IProgressMonitor monitor) {
		ThreadsExecutor executor = new ThreadsExecutor();
		IFuture<? extends EPackage> result = null;
		try {
			result = executor.execute(new ConstructRunnable<EPackage>(), monitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public class ConstructRunnable<T extends EPackage> implements IProgressRunnable<T> {

		public T run(IProgressMonitor monitor) throws Exception {
			@SuppressWarnings("unchecked")
			T result = (T) doConstruct(monitor);
			constructed().acquire();
			return result;
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE))
			return getState().getName();
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			getState().setName(newName);
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__NAME, oldName,
					name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTime(Date newTime) {
		Date oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__TIME, oldTime,
					time));
	}

	@Override
	public Version getVersion() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			return getState().getVersion();
		return VERSION_EDEFAULT;
	}

	@Override
	public void setVersion(Version newVersion) {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			getState().setVersion(newVersion);
	}

	@Override
	public String getProjectName() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS))
			return getState().getProjectName();
		return PROJECT_NAME_EDEFAULT;
	}

	@Override
	public void setProjectName(String newProjectName) {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE))
			getState().setProjectName(newProjectName);
	}

	@Override
	public Phase getStage() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE))
			return getState().getStage();
		return Phase.DEFINED;
	}

	@Override
	public void setStage(Phase newStage) {
		if (eIsSet(ClassSupplierPackage.Literals.CONTRIBUTION__STATE))
			getState().setStage(newStage);
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
		if (getStage().equals(Phase.LOADED))
			return getGeneratedEPackage();
		return ((Constructable) getState()).doConstruct(new SubProgressMonitor(monitor, 1));
	}

	@Override
	public Semaphore constructed() {
		return ((Constructable) getState()).constructed();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			return ((InternalEList<?>) getSnapshots()).basicRemove(otherEnd, msgs);
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
		case ClassSupplierPackage.CONTRIBUTION__NAME:
			return getName();
		case ClassSupplierPackage.CONTRIBUTION__TIME:
			return getTime();
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			return getVersion();
		case ClassSupplierPackage.CONTRIBUTION__STAGE:
			return getStage();
		case ClassSupplierPackage.CONTRIBUTION__PROJECT_NAME:
			return getProjectName();
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			if (coreType)
				return getSnapshots();
			else
				return getSnapshots().map();
		case ClassSupplierPackage.CONTRIBUTION__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGE:
			if (resolve)
				return getDynamicEPackage();
			return basicGetDynamicEPackage();
		case ClassSupplierPackage.CONTRIBUTION__GENERATED_EPACKAGE:
			if (resolve)
				return getGeneratedEPackage();
			return basicGetGeneratedEPackage();
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
		case ClassSupplierPackage.CONTRIBUTION__NAME:
			setName((String) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__TIME:
			setTime((Date) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			setVersion((Version) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__STAGE:
			setStage((Phase) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__PROJECT_NAME:
			setProjectName((String) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			((EStructuralFeature.Setting) getSnapshots()).set(newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EPackage) newValue);
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
		case ClassSupplierPackage.CONTRIBUTION__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__TIME:
			setTime(TIME_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__STAGE:
			setStage(STAGE_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__PROJECT_NAME:
			setProjectName(PROJECT_NAME_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			getSnapshots().clear();
			return;
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EPackage) null);
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
		case ClassSupplierPackage.CONTRIBUTION__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ClassSupplierPackage.CONTRIBUTION__TIME:
			return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			return VERSION_EDEFAULT == null ? getVersion() != null : !VERSION_EDEFAULT.equals(getVersion());
		case ClassSupplierPackage.CONTRIBUTION__STAGE:
			return getStage() != STAGE_EDEFAULT;
		case ClassSupplierPackage.CONTRIBUTION__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? getProjectName() != null
					: !PROJECT_NAME_EDEFAULT.equals(getProjectName());
		case ClassSupplierPackage.CONTRIBUTION__SNAPSHOTS:
			return snapshots != null && !snapshots.isEmpty();
		case ClassSupplierPackage.CONTRIBUTION__STATE:
			return basicGetState() != null;
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGE:
			return basicGetDynamicEPackage() != null;
		case ClassSupplierPackage.CONTRIBUTION__GENERATED_EPACKAGE:
			return basicGetGeneratedEPackage() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", time: ");
		result.append(time);
		result.append(')');
		return result.toString();
	}

} // ContributionImpl
