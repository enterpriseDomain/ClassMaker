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
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Contribution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.classupplier.impl.ContributionImpl#getName <em>Name</em>}</li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getLanguage
 * <em>Language</em>}</li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getVersion <em>Version</em>
 * }</li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getStage <em>Stage</em>}
 * </li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getProjectName
 * <em>Project Name</em>}</li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getStateHistory
 * <em>State History</em>}</li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getState <em>State</em>}
 * </li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getDynamicEPackage
 * <em>Dynamic EPackage</em>}</li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getGeneratedEPackage
 * <em>Generated EPackage</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContributionImpl extends EObjectImpl implements Contribution {

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected Version version = VERSION_EDEFAULT;
	/**
	 * The default value of the '{@link #getStage() <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected static final Phase STAGE_EDEFAULT = Phase.DEFINED;
	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getStateHistory() <em>State History</em>
	 * }' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStateHistory()
	 * @generated
	 * @ordered
	 */
	protected EMap<Version, State> stateHistory;

	protected int stateCounter = 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
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
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE_HISTORY)
				&& eIsSet(ClassSupplierPackage.CONTRIBUTION__VERSION) && getStateHistory().containsKey(getVersion()))
			return getStateHistory().get(getVersion());
		return null;

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage getDynamicEPackage() {
		EPackage dynamicEPackage = basicGetDynamicEPackage();
		return dynamicEPackage != null && dynamicEPackage.eIsProxy()
				? (EPackage) eResolveProxy((InternalEObject) dynamicEPackage) : dynamicEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage basicGetDynamicEPackage() {
		if (stateInited())
			return getState().getDynamicEPackage();
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setDynamicEPackage(EPackage newDynamicEPackage) {
		if (stateInited())
			getState().setDynamicEPackage(newDynamicEPackage);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage getGeneratedEPackage() {
		EPackage generatedEPackage = basicGetGeneratedEPackage();
		return generatedEPackage != null && generatedEPackage.eIsProxy()
				? (EPackage) eResolveProxy((InternalEObject) generatedEPackage) : generatedEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	public IFuture<? extends EPackage> apply(IProgressMonitor monitor) {
		ThreadsExecutor executor = new ThreadsExecutor();
		IFuture<? extends EPackage> result = null;
		try {
			result = executor.execute(new ConstructRunnable<EPackage>(), monitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(Version version) {
		if (getStateHistory().containsKey(version))
			setVersion(version);
		else
			throw new IllegalStateException(NLS.bind("Version {0} has no state.", version));
	}

	public class ConstructRunnable<T extends EPackage> implements IProgressRunnable<T> {

		public T run(IProgressMonitor monitor) throws Exception {
			@SuppressWarnings("unchecked")
			T result = (T) construct(monitor);
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
		if (stateInited())
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
		if (stateInited())
			getState().setName(newName);
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__NAME, oldName,
					name));
	}

	protected boolean stateInited() {
		return eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getLanguage() {
		return getState().getLanguage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setLanguage(String newLanguage) {
		getState().setLanguage(newLanguage);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersion(Version newVersion) {
		Version oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__VERSION,
					oldVersion, version));
	}

	@Override
	public String getProjectName() {
		if (stateInited())
			return getState().getProjectName();
		return PROJECT_NAME_EDEFAULT;
	}

	@Override
	public void setProjectName(String newProjectName) {
		if (stateInited())
			getState().setProjectName(newProjectName);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EMap<Version, State> getStateHistory() {
		if (stateHistory == null) {
			stateHistory = new EcoreEMap<Version, State>(ClassSupplierPackage.Literals.VERSION_TO_STATE_MAP_ENTRY,
					VersionToStateMapEntryImpl.class, this, ClassSupplierPackage.CONTRIBUTION__STATE_HISTORY);
		}
		return stateHistory;
	}

	@Override
	public Phase getStage() {
		if (stateInited())
			return getState().getStage();
		return Phase.DEFINED;
	}

	@Override
	public void setStage(Phase newStage) {
		if (stateInited())
			getState().setStage(newStage);
	}

	private int getNewNumber() {
		return stateCounter++;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State newState() {
		Date time = new Date();
		State newState = ClassSupplierFactory.eINSTANCE.createState();
		newState.setTimestamp(time);
		newState.setNumber(getNewNumber());
		newState.setVersion(org.osgi.framework.Version.parseVersion(newState.formatVersion()));
		boolean first = getStateHistory().isEmpty();
		getStateHistory().put(newState.getVersion(), newState);
		if (first)
			checkout(newState.getVersion());
		else {
			newState.setName(getName());
			newState.setDynamicEPackage(getDynamicEPackage());
		}
		return newState;
	}

	@Override
	public EPackage construct(IProgressMonitor monitor) throws Exception {
		return ((Constructable) getState()).construct(new SubProgressMonitor(monitor, 1));
	}

	@Override
	public Semaphore constructed() {
		return ((Constructable) getState()).constructed();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.CONTRIBUTION__STATE_HISTORY:
			return ((InternalEList<?>) getStateHistory()).basicRemove(otherEnd, msgs);
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
		case ClassSupplierPackage.CONTRIBUTION__NAME:
			return getName();
		case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
			return getLanguage();
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			return getVersion();
		case ClassSupplierPackage.CONTRIBUTION__STAGE:
			return getStage();
		case ClassSupplierPackage.CONTRIBUTION__PROJECT_NAME:
			return getProjectName();
		case ClassSupplierPackage.CONTRIBUTION__STATE_HISTORY:
			if (coreType)
				return getStateHistory();
			else
				return getStateHistory().map();
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
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassSupplierPackage.CONTRIBUTION__NAME:
			setName((String) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
			setLanguage((String) newValue);
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
		case ClassSupplierPackage.CONTRIBUTION__STATE_HISTORY:
			((EStructuralFeature.Setting) getStateHistory()).set(newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EPackage) newValue);
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
		case ClassSupplierPackage.CONTRIBUTION__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
			setLanguage(LANGUAGE_EDEFAULT);
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
		case ClassSupplierPackage.CONTRIBUTION__STATE_HISTORY:
			getStateHistory().clear();
			return;
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EPackage) null);
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
		case ClassSupplierPackage.CONTRIBUTION__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
			return LANGUAGE_EDEFAULT == null ? getLanguage() != null : !LANGUAGE_EDEFAULT.equals(getLanguage());
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case ClassSupplierPackage.CONTRIBUTION__STAGE:
			return getStage() != STAGE_EDEFAULT;
		case ClassSupplierPackage.CONTRIBUTION__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? getProjectName() != null
					: !PROJECT_NAME_EDEFAULT.equals(getProjectName());
		case ClassSupplierPackage.CONTRIBUTION__STATE_HISTORY:
			return stateHistory != null && !stateHistory.isEmpty();
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
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} // ContributionImpl
