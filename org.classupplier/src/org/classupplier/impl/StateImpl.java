/**
 */
package org.classupplier.impl;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.classupplier.ClassSupplierPackage;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.classupplier.impl.StateImpl#getName <em>Name</em>}</li>
 * <li>{@link org.classupplier.impl.StateImpl#getTime <em>Time</em>}</li>
 * <li>{@link org.classupplier.impl.StateImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.classupplier.impl.StateImpl#getStage <em>Stage</em>}</li>
 * <li>{@link org.classupplier.impl.StateImpl#getProjectName <em>Project Name
 * </em>}</li>
 * <li>{@link org.classupplier.impl.StateImpl#getEPackage <em>EPackage</em>}</li>
 * <li>{@link org.classupplier.impl.StateImpl#getDynamicEPackage <em>Dynamic
 * EPackage</em>}</li>
 * <li>{@link org.classupplier.impl.StateImpl#getRuntimeEPackage <em>Runtime
 * EPackage</em>}</li>
 * </ul>
 * </p>
 *
 * @generated NOT
 */
public class StateImpl extends EObjectImpl implements State, InternalState {
	private final class ResultAdapter extends AdapterImpl {
		private EPackage result;

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getEventType() == Notification.SET
					&& msg.getFeatureID(State.class) == ClassSupplierPackage.CONTRIBUTION__STAGE
					&& msg.getNewValue().equals(Phase.LOADED))
				setResult(getRuntimeEPackage());
		}

		public EPackage getResult() {
			return result;
		}

		public void setResult(EPackage result) {
			this.result = result;
		}
	}

	public class StageAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getEventType() != Notification.SET)
				return;
			switch (msg.getFeatureID(State.class)) {
			case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
				setStage(Phase.MODELED);
				break;
			case ClassSupplierPackage.STATE__RUNTIME_EPACKAGE:
				setStage(Phase.LOADED);
				break;
			case ClassSupplierPackage.STATE__NAME:
				setProjectName(msg.getNewStringValue().toLowerCase());
			}
		}

	}

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

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
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected Date time = TIME_EDEFAULT;

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
	protected static final Phase STAGE_EDEFAULT = Phase.GENERATED;

	/**
	 * The cached value of the '{@link #getStage() <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected Phase stage = STAGE_EDEFAULT;

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
	 * The cached value of the '{@link #getProjectName() <em>Project Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected String projectName = PROJECT_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEPackage() <em>EPackage</em>}'
	 * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEPackage()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap ePackage;

	private ResultAdapter results = new ResultAdapter();;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected StateImpl() {
		super();
		eAdapters().add(new StageAdapter());
		eAdapters().add(results);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		if (getAppropriateEPackage() != null)
			return getAppropriateEPackage().getName();
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassSupplierPackage.STATE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Date getTime() {
		return new Date(time.getTime());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setTime(Date newTime) {
		Date oldTime = time;
		time = new Date(newTime.getTime());
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassSupplierPackage.STATE__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProjectName(String newProjectName) {
		String oldProjectName = projectName;
		projectName = newProjectName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassSupplierPackage.STATE__PROJECT_NAME, oldProjectName,
					projectName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getEPackage() {
		if (ePackage == null) {
			ePackage = new BasicFeatureMap(this,
					ClassSupplierPackage.STATE__EPACKAGE);
		}
		return ePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage getDynamicEPackage() {
		return (EPackage) getEPackage().get(
				ClassSupplierPackage.Literals.STATE__DYNAMIC_EPACKAGE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDynamicEPackage(
			EPackage newDynamicEPackage, NotificationChain msgs) {
		return ((FeatureMap.Internal) getEPackage()).basicAdd(
				ClassSupplierPackage.Literals.STATE__DYNAMIC_EPACKAGE,
				newDynamicEPackage, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDynamicEPackage(EPackage newDynamicEPackage) {
		((FeatureMap.Internal) getEPackage()).set(
				ClassSupplierPackage.Literals.STATE__DYNAMIC_EPACKAGE,
				newDynamicEPackage);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage getRuntimeEPackage() {
		return (EPackage) getEPackage().get(
				ClassSupplierPackage.Literals.STATE__RUNTIME_EPACKAGE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetRuntimeEPackage(
			EPackage newRuntimeEPackage, NotificationChain msgs) {
		return ((FeatureMap.Internal) getEPackage()).basicAdd(
				ClassSupplierPackage.Literals.STATE__RUNTIME_EPACKAGE,
				newRuntimeEPackage, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRuntimeEPackage(EPackage newRuntimeEPackage) {
		((FeatureMap.Internal) getEPackage()).set(
				ClassSupplierPackage.Literals.STATE__RUNTIME_EPACKAGE,
				newRuntimeEPackage);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public synchronized Future<? extends EPackage> construct(
			IProgressMonitor monitor) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<? extends EPackage> result = executorService
				.submit(new ConstructCallable(monitor));
		return result;
	}

	public EPackage doConstruct(IProgressMonitor monitor) throws Exception {
		Phase oldStage = getStage();
		setStage(Phase.PROCESSING);
		try {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			boolean autoBuild = workspace.isAutoBuilding();
			String projectName = getProjectName();
			IProject project = workspace.getRoot().getProject(projectName);
			if (!project.exists()) {
				project.create(monitor);
				project.open(monitor);
				ResourceUtil.setAutoBuilding(workspace, false);
				IProjectDescription description = project.getDescription();
				description = ResourceUtil.addProjectNature(description,
						ClassSupplierOSGi.NATURE_ID);
				project.setDescription(description, monitor);
			} else {
				project.open(monitor);
			}
			project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
			ResourceUtil.setAutoBuilding(workspace, autoBuild);
		} catch (CoreException e) {
			if (e.getStatus().getSeverity() == IStatus.ERROR) {
				setStage(oldStage);
			}
			ClassSupplierOSGi.getInstance().getLog().log(e.getStatus());
			monitor.setCanceled(true);
			throw e;
		} catch (Exception e) {
			setStage(oldStage);
			monitor.setCanceled(true);
			throw e;
		} finally {
			monitor.done();
		}

		while (!eIsSet(ClassSupplierPackage.CONTRIBUTION__RUNTIME_EPACKAGE)) {
			Thread.yield();
		}
		return results.getResult();
	}

	protected class ConstructCallable implements Callable<EPackage> {

		protected IProgressMonitor monitor;

		public ConstructCallable(IProgressMonitor monitor) {
			this.monitor = monitor;

		}

		@Override
		public EPackage call() throws Exception {
			return doConstruct(monitor);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Phase getStage() {
		return stage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStage(Phase newStage) {
		Phase oldStage = stage;
		stage = newStage == null ? STAGE_EDEFAULT : newStage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassSupplierPackage.STATE__STAGE, oldStage, stage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage getAppropriateEPackage() {
		switch (getStage()) {
		case LOADED:
			if (eIsSet(ClassSupplierPackage.STATE__RUNTIME_EPACKAGE))
				return getRuntimeEPackage();
		case MODELED:
			if (eIsSet(ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE))
				return getDynamicEPackage();
		default:
			return null;
		}
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
		case ClassSupplierPackage.STATE__EPACKAGE:
			return ((InternalEList<?>) getEPackage()).basicRemove(otherEnd,
					msgs);
		case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
			return basicSetDynamicEPackage(null, msgs);
		case ClassSupplierPackage.STATE__RUNTIME_EPACKAGE:
			return basicSetRuntimeEPackage(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassSupplierPackage.STATE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassSupplierPackage.STATE__NAME:
			return getName();
		case ClassSupplierPackage.STATE__TIME:
			return getTime();
		case ClassSupplierPackage.STATE__VERSION:
			return getVersion();
		case ClassSupplierPackage.STATE__STAGE:
			return getStage();
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			return getProjectName();
		case ClassSupplierPackage.STATE__EPACKAGE:
			if (coreType)
				return getEPackage();
			return ((FeatureMap.Internal) getEPackage()).getWrapper();
		case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
			return getDynamicEPackage();
		case ClassSupplierPackage.STATE__RUNTIME_EPACKAGE:
			return getRuntimeEPackage();
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
		case ClassSupplierPackage.STATE__NAME:
			setName((String) newValue);
			return;
		case ClassSupplierPackage.STATE__TIME:
			setTime((Date) newValue);
			return;
		case ClassSupplierPackage.STATE__VERSION:
			setVersion((Version) newValue);
			return;
		case ClassSupplierPackage.STATE__STAGE:
			setStage((Phase) newValue);
			return;
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			setProjectName((String) newValue);
			return;
		case ClassSupplierPackage.STATE__EPACKAGE:
			((FeatureMap.Internal) getEPackage()).set(newValue);
			return;
		case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EPackage) newValue);
			return;
		case ClassSupplierPackage.STATE__RUNTIME_EPACKAGE:
			setRuntimeEPackage((EPackage) newValue);
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
		case ClassSupplierPackage.STATE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__TIME:
			setTime(TIME_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__STAGE:
			setStage(STAGE_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			setProjectName(PROJECT_NAME_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__EPACKAGE:
			getEPackage().clear();
			return;
		case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EPackage) null);
			return;
		case ClassSupplierPackage.STATE__RUNTIME_EPACKAGE:
			setRuntimeEPackage((EPackage) null);
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
		case ClassSupplierPackage.STATE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case ClassSupplierPackage.STATE__TIME:
			return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT
					.equals(time);
		case ClassSupplierPackage.STATE__VERSION:
			return VERSION_EDEFAULT == null ? version != null
					: !VERSION_EDEFAULT.equals(version);
		case ClassSupplierPackage.STATE__STAGE:
			return stage != STAGE_EDEFAULT;
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? projectName != null
					: !PROJECT_NAME_EDEFAULT.equals(projectName);
		case ClassSupplierPackage.STATE__EPACKAGE:
			return ePackage != null && !ePackage.isEmpty();
		case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
			return getDynamicEPackage() != null;
		case ClassSupplierPackage.STATE__RUNTIME_EPACKAGE:
			return getRuntimeEPackage() != null;
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
		result.append(", time: ");
		result.append(time);
		result.append(", version: ");
		result.append(version);
		result.append(", stage: ");
		result.append(stage);
		result.append(", projectName: ");
		result.append(projectName);
		result.append(", ePackage: ");
		result.append(ePackage);
		result.append(')');
		return result.toString();
	}

} // StateImpl
