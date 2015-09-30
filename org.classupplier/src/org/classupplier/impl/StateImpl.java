/**
 */
package org.classupplier.impl;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Semaphore;

import org.classupplier.ClassSupplierPackage;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.core.ClassSupplierOSGi;
import org.classupplier.core.SupplementaryJob;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.classupplier.impl.StateImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getStage <em>Stage</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getDeployableUnitName <em>Deployable Unit Name</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getEPackage <em>EPackage</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getDynamicEPackage <em>Dynamic EPackage</em>}</li>
 *   <li>{@link org.classupplier.impl.StateImpl#getGeneratedEPackage <em>Generated EPackage</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateImpl extends EObjectImpl implements State {
	private final class ResultAdapter extends AdapterImpl {
		private EPackage result;

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getEventType() == Notification.SET
					&& msg.getFeatureID(State.class) == ClassSupplierPackage.STATE__STAGE
					&& msg.getNewValue().equals(Phase.LOADED))
				setResult(getGeneratedEPackage());
		}

		public EPackage getResult() {
			return result;
		}

		public void setResult(EPackage result) {
			if (this.result != null && result == null)
				return;
			this.result = result;
			synchronized (lock) {
				lock.notifyAll();
			}
		}
	}

	public class StageAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getEventType() != Notification.SET)
				return;
			switch (msg.getFeatureID(State.class)) {
			case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
				EPackage ePackage = (EPackage) msg.getNewValue();
				ePackage.setNsPrefix(CodeGenUtil.capName(ePackage.getNsPrefix(), getLocale()));
				setStage(Phase.MODELED);
				break;
			case ClassSupplierPackage.STATE__GENERATED_EPACKAGE:
				setStage(Phase.LOADED);
				break;
			case ClassSupplierPackage.STATE__NAME:
				if (msg.getNewStringValue() != null)
					setProjectName(msg.getNewStringValue().toLowerCase());
			}
		}

	}

	public class VersionAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == ClassSupplierPackage.STATE__VERSION
					&& StateImpl.this.eContainer() != null && msg.getEventType() == Notification.SET) {
				EMap<Version, State> map = ((VersionToStateMapEntryImpl) StateImpl.this.eContainer()).getEMap();
				map.remove(msg.getOldValue());
				map.put((Version) msg.getNewValue(), StateImpl.this);
			}
		}

	}

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected String language = LANGUAGE_EDEFAULT;

	protected Locale locale;

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIMESTAMP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected Date timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected int number = NUMBER_EDEFAULT;

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
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected Version version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getStage() <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected static final Phase STAGE_EDEFAULT = Phase.DEFINED;

	/**
	 * The cached value of the '{@link #getStage() <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected Phase stage = STAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected String projectName = PROJECT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeployableUnitName() <em>Deployable Unit Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getDeployableUnitName()
	 * @generated
	 * @ordered
	 */
	protected static final String DEPLOYABLE_UNIT_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getEPackage() <em>EPackage</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEPackage()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap ePackage;

	private ResultAdapter resultCollector = new ResultAdapter();;

	private Semaphore constructed = new Semaphore(0);

	private Object lock = new Object();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected StateImpl() {
		super();
		eAdapters().add(new StageAdapter());
		eAdapters().add(new VersionAdapter());
		eAdapters().add(resultCollector);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLanguage(String newLanguage) {
		String oldLanguage = language;
		language = newLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__LANGUAGE, oldLanguage,
					language));
	}

	public Locale getLocale() {
		if (locale == null) {
			if (getLanguage() == null) {
				return Locale.getDefault();
			} else {
				locale = new Locale(getLanguage());
			}
		}
		return locale;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Date getTimestamp() {
		return new Date(timestamp.getTime());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setTimestamp(Date newTimestamp) {
		Date oldTimestamp = timestamp;
		timestamp = new Date(newTimestamp.getTime());
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__TIMESTAMP, oldTimestamp,
					timestamp));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(int newNumber) {
		int oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__NUMBER, oldNumber,
					number));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setProjectName(String newProjectName) {
		String oldProjectName = projectName;
		projectName = newProjectName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__PROJECT_NAME,
					oldProjectName, projectName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getDeployableUnitName() {
		String deployableUnitName;
		Version version = Version.parseVersion(getVersion().toString());
		if (version.equals(Version.emptyVersion))
			deployableUnitName = getProjectName() + '_' + Version.parseVersion(formatVersion()).toString();
		else
			deployableUnitName = getProjectName() + '_' + version.toString();
		return deployableUnitName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getEPackage() {
		if (ePackage == null) {
			ePackage = new BasicFeatureMap(this, ClassSupplierPackage.STATE__EPACKAGE);
		}
		return ePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage getDynamicEPackage() {
		return EcoreUtil
				.copy((EPackage) getEPackage().get(ClassSupplierPackage.Literals.STATE__DYNAMIC_EPACKAGE, true));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public NotificationChain basicSetDynamicEPackage(EPackage newDynamicEPackage, NotificationChain msgs) {
		return ((FeatureMap.Internal) getEPackage()).basicAdd(ClassSupplierPackage.Literals.STATE__DYNAMIC_EPACKAGE,
				EcoreUtil.copy(newDynamicEPackage), msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setDynamicEPackage(EPackage newDynamicEPackage) {
		((FeatureMap.Internal) getEPackage()).set(ClassSupplierPackage.Literals.STATE__DYNAMIC_EPACKAGE,
				newDynamicEPackage);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getGeneratedEPackage() {
		return (EPackage) getEPackage().get(ClassSupplierPackage.Literals.STATE__GENERATED_EPACKAGE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGeneratedEPackage(EPackage newGeneratedEPackage, NotificationChain msgs) {
		return ((FeatureMap.Internal) getEPackage()).basicAdd(ClassSupplierPackage.Literals.STATE__GENERATED_EPACKAGE,
				newGeneratedEPackage, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeneratedEPackage(EPackage newGeneratedEPackage) {
		((FeatureMap.Internal) getEPackage()).set(ClassSupplierPackage.Literals.STATE__GENERATED_EPACKAGE,
				newGeneratedEPackage);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void fireJobsCompleted() {
		constructed().release();
		synchronized (lock) {
			lock.notifyAll();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String formatVersion() {
		return NLS.bind("1.0.{0}.{1}", getNumber(), ResourceUtil.formatQualifier(getTimestamp()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @throws CoreException
	 * @generated NOT
	 */
	public void setProjectVersion(IProgressMonitor monitor) throws CoreException {
		IBundleProjectService service = ((IBundleProjectService) ClassSupplierOSGi
				.getService(IBundleProjectService.class.getName()));
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(getProjectName());
		IBundleProjectDescription bundleProjectDescription = service.getDescription(project);
		bundleProjectDescription.setBundleVersion(getVersion());
		bundleProjectDescription.apply(monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(IProgressMonitor monitor) throws CoreException {
		ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()).delete(true, monitor);
	}

	public EPackage construct(IProgressMonitor monitor) throws Exception {
		Phase oldStage = getStage();
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
				description.setNatureIds(
						ResourceUtil.addProjectNature(description.getNatureIds(), ClassSupplierOSGi.NATURE_ID));
				project.setDescription(description, monitor);
			} else {
				project.open(monitor);
			}
			project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
			SupplementaryJob.joinManualBuild(monitor);
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

		synchronized (lock) {
			while (resultCollector.getResult() == null || constructed().availablePermits() < 0)
				lock.wait();
		}

		return resultCollector.getResult();
	}

	@Override
	public Semaphore constructed() {
		return constructed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Phase getStage() {
		return stage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setStage(Phase newStage) {
		Phase oldStage = stage;
		stage = newStage == null ? STAGE_EDEFAULT : newStage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__STAGE, oldStage, stage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.STATE__EPACKAGE:
			return ((InternalEList<?>) getEPackage()).basicRemove(otherEnd, msgs);
		case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
			return basicSetDynamicEPackage(null, msgs);
		case ClassSupplierPackage.STATE__GENERATED_EPACKAGE:
			return basicSetGeneratedEPackage(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(Version newVersion) {
		Version oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__VERSION, oldVersion,
					version));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassSupplierPackage.STATE__NAME:
			return getName();
		case ClassSupplierPackage.STATE__LANGUAGE:
			return getLanguage();
		case ClassSupplierPackage.STATE__TIMESTAMP:
			return getTimestamp();
		case ClassSupplierPackage.STATE__NUMBER:
			return getNumber();
		case ClassSupplierPackage.STATE__VERSION:
			return getVersion();
		case ClassSupplierPackage.STATE__STAGE:
			return getStage();
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			return getProjectName();
		case ClassSupplierPackage.STATE__DEPLOYABLE_UNIT_NAME:
			return getDeployableUnitName();
		case ClassSupplierPackage.STATE__EPACKAGE:
			if (coreType)
				return getEPackage();
			return ((FeatureMap.Internal) getEPackage()).getWrapper();
		case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
			return getDynamicEPackage();
		case ClassSupplierPackage.STATE__GENERATED_EPACKAGE:
			return getGeneratedEPackage();
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
		case ClassSupplierPackage.STATE__NAME:
			setName((String) newValue);
			return;
		case ClassSupplierPackage.STATE__LANGUAGE:
			setLanguage((String) newValue);
			return;
		case ClassSupplierPackage.STATE__TIMESTAMP:
			setTimestamp((Date) newValue);
			return;
		case ClassSupplierPackage.STATE__NUMBER:
			setNumber((Integer) newValue);
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
		case ClassSupplierPackage.STATE__GENERATED_EPACKAGE:
			setGeneratedEPackage((EPackage) newValue);
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
		case ClassSupplierPackage.STATE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__LANGUAGE:
			setLanguage(LANGUAGE_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__TIMESTAMP:
			setTimestamp(TIMESTAMP_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__NUMBER:
			setNumber(NUMBER_EDEFAULT);
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
		case ClassSupplierPackage.STATE__GENERATED_EPACKAGE:
			setGeneratedEPackage((EPackage) null);
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
		case ClassSupplierPackage.STATE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ClassSupplierPackage.STATE__LANGUAGE:
			return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
		case ClassSupplierPackage.STATE__TIMESTAMP:
			return TIMESTAMP_EDEFAULT == null ? timestamp != null : !TIMESTAMP_EDEFAULT.equals(timestamp);
		case ClassSupplierPackage.STATE__NUMBER:
			return number != NUMBER_EDEFAULT;
		case ClassSupplierPackage.STATE__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case ClassSupplierPackage.STATE__STAGE:
			return stage != STAGE_EDEFAULT;
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? projectName != null : !PROJECT_NAME_EDEFAULT.equals(projectName);
		case ClassSupplierPackage.STATE__DEPLOYABLE_UNIT_NAME:
			return DEPLOYABLE_UNIT_NAME_EDEFAULT == null ? getDeployableUnitName() != null
					: !DEPLOYABLE_UNIT_NAME_EDEFAULT.equals(getDeployableUnitName());
		case ClassSupplierPackage.STATE__EPACKAGE:
			return ePackage != null && !ePackage.isEmpty();
		case ClassSupplierPackage.STATE__DYNAMIC_EPACKAGE:
			return getDynamicEPackage() != null;
		case ClassSupplierPackage.STATE__GENERATED_EPACKAGE:
			return getGeneratedEPackage() != null;
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
		result.append(", language: ");
		result.append(language);
		result.append(", timestamp: ");
		result.append(timestamp);
		result.append(", number: ");
		result.append(number);
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
