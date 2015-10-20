/**
 */
package org.classupplier.impl;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Semaphore;

import org.classupplier.ClassSupplierFactory;
import org.classupplier.ClassSupplierPackage;
import org.classupplier.Contribution;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.Workspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
 * <li>{@link org.classupplier.impl.ContributionImpl#getDynamicEPackages
 * <em>Dynamic EPackages</em>}</li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getGeneratedEPackages
 * <em>Generated EPackages</em>}</li>
 * <li>{@link org.classupplier.impl.ContributionImpl#getWorkspace
 * <em>Workspace</em>}</li>
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
	protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$
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
	 * @generated NOT
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = Version.emptyVersion;
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
	protected static final String PROJECT_NAME_EDEFAULT = ""; //$NON-NLS-1$
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
	 * @generated NOT
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
	public NotificationChain basicSetState(State newState, NotificationChain msgs) {
		State oldState = basicGetState();
		getStateHistory().put(newState.getVersion(), newState);
		if (eNotificationRequired())
			msgs.add(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__STATE, oldState,
					newState));
		return msgs;
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
		else if (eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE_HISTORY)
				&& !eIsSet(ClassSupplierPackage.CONTRIBUTION__VERSION)) {
			return null;
		}
		return null;

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<EPackage> getDynamicEPackages() {
		if (stateInited())
			return getState().getDynamicEPackages();
		return ECollections.emptyEList();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<EPackage> getGeneratedEPackages() {
		if (stateInited())
			return getState().getGeneratedEPackages();
		return ECollections.emptyEList();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Workspace getWorkspace() {
		if (eContainerFeatureID() != ClassSupplierPackage.CONTRIBUTION__WORKSPACE)
			return null;
		return (Workspace) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetWorkspace(Workspace newWorkspace, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newWorkspace, ClassSupplierPackage.CONTRIBUTION__WORKSPACE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setWorkspace(Workspace newWorkspace) {
		if (newWorkspace != eInternalContainer()
				|| (eContainerFeatureID() != ClassSupplierPackage.CONTRIBUTION__WORKSPACE && newWorkspace != null)) {
			if (EcoreUtil.isAncestor(this, newWorkspace))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWorkspace != null)
				msgs = ((InternalEObject) newWorkspace).eInverseAdd(this, ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS,
						Workspace.class, msgs);
			msgs = basicSetWorkspace(newWorkspace, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__WORKSPACE,
					newWorkspace, newWorkspace));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public <T extends EList<EPackage>> IFuture<T> apply(IProgressMonitor monitor) {
		ThreadsExecutor executor = new ThreadsExecutor();
		IFuture<T> results = null;
		try {
			results = (IFuture<T>) executor.execute(new ConstructRunnable<T>(), monitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(Version version) {
		if (getStateHistory().containsKey(version) || version.equals(VERSION_EDEFAULT))
			setVersion(version);
		else
			throw new IllegalStateException(NLS.bind("Version {0} has no state.", version)); //$NON-NLS-1$
	}

	public class ConstructRunnable<T extends EList<? extends EPackage>> implements IProgressRunnable<T> {

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
		if (stateInited()) {
			Phase oldStage = getState().getStage();
			getState().setStage(newStage);
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__STAGE,
						oldStage, newStage));
		}
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
		Version newVersion = org.osgi.framework.Version.parseVersion(newState.formatVersion());
		boolean first = getStateHistory().isEmpty();
		getStateHistory().put(newVersion, newState);
		newState.setVersion(newVersion);
		if (!first) {
			newState.setName(getName());
			newState.getDynamicEPackages().addAll(EcoreUtil.copyAll(getDynamicEPackages()));
		}
		checkout(newState.getVersion());
		return newState;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(IProgressMonitor monitor) throws CoreException {
		State state = getState();
		checkout(VERSION_EDEFAULT);
		state.delete(monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean contains(EStructuralFeature eFeature, EPackage questionEPackage) {
		if (stateInited())
			return getState().contains(eFeature, questionEPackage);
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.CONTRIBUTION__WORKSPACE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetWorkspace((Workspace) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	@Override
	public <T extends EList<? extends EPackage>> T construct(IProgressMonitor monitor) throws Exception {
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
		case ClassSupplierPackage.CONTRIBUTION__STATE:
			return basicSetState(null, msgs);
		case ClassSupplierPackage.CONTRIBUTION__WORKSPACE:
			return basicSetWorkspace(null, msgs);
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
		case ClassSupplierPackage.CONTRIBUTION__WORKSPACE:
			return eInternalContainer().eInverseRemove(this, ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS,
					Workspace.class, msgs);
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
			return getState();
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGES:
			return getDynamicEPackages();
		case ClassSupplierPackage.CONTRIBUTION__GENERATED_EPACKAGES:
			return getGeneratedEPackages();
		case ClassSupplierPackage.CONTRIBUTION__WORKSPACE:
			return getWorkspace();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
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
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGES:
			getDynamicEPackages().clear();
			getDynamicEPackages().addAll((Collection<? extends EPackage>) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__WORKSPACE:
			setWorkspace((Workspace) newValue);
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
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGES:
			getDynamicEPackages().clear();
			return;
		case ClassSupplierPackage.CONTRIBUTION__WORKSPACE:
			setWorkspace((Workspace) null);
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
			return getState() != null;
		case ClassSupplierPackage.CONTRIBUTION__DYNAMIC_EPACKAGES:
			return !getDynamicEPackages().isEmpty();
		case ClassSupplierPackage.CONTRIBUTION__GENERATED_EPACKAGES:
			return !getGeneratedEPackages().isEmpty();
		case ClassSupplierPackage.CONTRIBUTION__WORKSPACE:
			return getWorkspace() != null;
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
