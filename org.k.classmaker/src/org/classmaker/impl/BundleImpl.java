/**
 */
package org.classmaker.impl;

import org.classmaker.Bundle;
import org.classmaker.ClassMaker;
import org.classmaker.ClassMakerPackage;
import org.classmaker.State;
import org.eclipse.core.runtime.IStatus;
import org.classmaker.NameLookup;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Bundle</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.classmaker.impl.BundleImpl#getName <em>Name</em>}</li>
 * <li>{@link org.classmaker.impl.BundleImpl#getEPackage <em>EPackage</em>}</li>
 * <li>{@link org.classmaker.impl.BundleImpl#getDynamicEPackage <em>Dynamic
 * EPackage</em>}</li>
 * <li>{@link org.classmaker.impl.BundleImpl#getState <em>State</em>}</li>
 * <li>{@link org.classmaker.impl.BundleImpl#getStatus <em>Status</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BundleImpl extends EObjectImpl implements Bundle {
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
	 * The cached value of the '{@link #getEPackage() <em>EPackage</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage ePackage;

	/**
	 * The cached value of the '{@link #getDynamicEPackage()
	 * <em>Dynamic EPackage</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getDynamicEPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage dynamicEPackage;

	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final State STATE_EDEFAULT = State.CREATED;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected State state = STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final IStatus STATUS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected IStatus status = STATUS_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected BundleImpl() {
		super();
		eAdapters().add(adapter);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.BUNDLE;
	}

	private Adapter adapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(getClass()) == ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE
					&& msg.getNewValue() != null) {
				setStatus(STATUS_EDEFAULT);
				setState(State.DYNAMIC);
			}
			if (msg.getFeatureID(getClass()) == ClassMakerPackage.BUNDLE__STATE
					&& msg.getNewValue() == State.DYNAMIC
					&& (getStatus() == null || getStatus().isOK()))
				make();
		}

	};

	protected void make() {
		setState(State.REFRESHING);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		NameLookup names = ClassMaker.getInstance().names();
		String projectName = names.getProjectName(getName());
		IProject project = workspace.getRoot().getProject(projectName);
		IProgressMonitor monitor = ClassMaker.getInstance().monitor();
		try {
			project.create(monitor);
			project.open(monitor);
			addNature(project, ClassMaker.NATURE_ID, monitor);
			project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
		} catch (CoreException e) {
			if (e.getStatus().getSeverity() == IStatus.ERROR) {
				setStatus(e.getStatus());
				setState(State.DYNAMIC);
				setEPackage(getDynamicEPackage());
			}
			e.printStackTrace();
			return;
		}
		setStatus(new Status(IStatus.OK, ClassMaker.PLUGIN_ID, "Bundle ready"));
		setState(State.GENERATED);
	}

	/**
	 * Adds the project nature to supplied project.
	 * 
	 * @param project
	 * @param natureId
	 * @param monitor
	 * @throws CoreException
	 */
	private void addNature(IProject project, String natureId,
			IProgressMonitor monitor) throws CoreException {
		IProjectDescription description = project.getDescription();
		String[] prevNatures = description.getNatureIds();
		String[] newNatures = new String[prevNatures.length + 1];
		System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
		newNatures[prevNatures.length] = natureId;
		description.setNatureIds(newNatures);
		project.setDescription(description, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		if (eIsSet(ClassMakerPackage.BUNDLE__NAME))
			return name;
		if ((getState() == State.DYNAMIC || getState() == State.REFRESHING)
				&& eIsSet(ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE))
			return getDynamicEPackage().getName();
		if (getState() == State.GENERATED)
			return getEPackage().getName();
		return null;
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
					ClassMakerPackage.BUNDLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage getEPackage() {
		return ePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetEPackage(EPackage newEPackage,
			NotificationChain msgs) {
		EPackage oldEPackage = ePackage;
		ePackage = newEPackage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, ClassMakerPackage.BUNDLE__EPACKAGE,
					oldEPackage, newEPackage);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEPackage(EPackage newEPackage) {
		if (newEPackage != ePackage) {
			NotificationChain msgs = null;
			if (ePackage != null)
				msgs = ((InternalEObject) ePackage).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- ClassMakerPackage.BUNDLE__EPACKAGE, null,
						msgs);
			if (newEPackage != null)
				msgs = ((InternalEObject) newEPackage).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- ClassMakerPackage.BUNDLE__EPACKAGE, null,
						msgs);
			msgs = basicSetEPackage(newEPackage, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.BUNDLE__EPACKAGE, newEPackage,
					newEPackage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage getDynamicEPackage() {
		return dynamicEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDynamicEPackage(
			EPackage newDynamicEPackage, NotificationChain msgs) {
		EPackage oldDynamicEPackage = dynamicEPackage;
		dynamicEPackage = newDynamicEPackage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE,
					oldDynamicEPackage, newDynamicEPackage);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDynamicEPackage(EPackage newDynamicEPackage) {
		if (newDynamicEPackage != dynamicEPackage) {
			NotificationChain msgs = null;
			if (dynamicEPackage != null)
				msgs = ((InternalEObject) dynamicEPackage).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE,
						null, msgs);
			if (newDynamicEPackage != null)
				msgs = ((InternalEObject) newDynamicEPackage).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE,
						null, msgs);
			msgs = basicSetDynamicEPackage(newDynamicEPackage, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE,
					newDynamicEPackage, newDynamicEPackage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public State getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setState(State newState) {
		State oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.BUNDLE__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IStatus getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStatus(IStatus newStatus) {
		IStatus oldStatus = status;
		status = newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.BUNDLE__STATUS, oldStatus, status));
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
		case ClassMakerPackage.BUNDLE__EPACKAGE:
			return basicSetEPackage(null, msgs);
		case ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE:
			return basicSetDynamicEPackage(null, msgs);
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
		case ClassMakerPackage.BUNDLE__NAME:
			return getName();
		case ClassMakerPackage.BUNDLE__EPACKAGE:
			return getEPackage();
		case ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE:
			return getDynamicEPackage();
		case ClassMakerPackage.BUNDLE__STATE:
			return getState();
		case ClassMakerPackage.BUNDLE__STATUS:
			return getStatus();
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
		case ClassMakerPackage.BUNDLE__NAME:
			setName((String) newValue);
			return;
		case ClassMakerPackage.BUNDLE__EPACKAGE:
			setEPackage((EPackage) newValue);
			return;
		case ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EPackage) newValue);
			return;
		case ClassMakerPackage.BUNDLE__STATE:
			setState((State) newValue);
			return;
		case ClassMakerPackage.BUNDLE__STATUS:
			setStatus((IStatus) newValue);
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
		case ClassMakerPackage.BUNDLE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ClassMakerPackage.BUNDLE__EPACKAGE:
			setEPackage((EPackage) null);
			return;
		case ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE:
			setDynamicEPackage((EPackage) null);
			return;
		case ClassMakerPackage.BUNDLE__STATE:
			setState(STATE_EDEFAULT);
			return;
		case ClassMakerPackage.BUNDLE__STATUS:
			setStatus(STATUS_EDEFAULT);
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
		case ClassMakerPackage.BUNDLE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case ClassMakerPackage.BUNDLE__EPACKAGE:
			return ePackage != null;
		case ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE:
			return dynamicEPackage != null;
		case ClassMakerPackage.BUNDLE__STATE:
			return state != STATE_EDEFAULT;
		case ClassMakerPackage.BUNDLE__STATUS:
			return STATUS_EDEFAULT == null ? status != null : !STATUS_EDEFAULT
					.equals(status);
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
		result.append(", state: ");
		result.append(state);
		result.append(", status: ");
		result.append(status);
		result.append(')');
		return result.toString();
	}

} // BundleImpl
