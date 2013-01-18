/**
 */
package org.classmaker.impl;

import org.classmaker.Bundle;
import org.classmaker.ClassMaker;
import org.classmaker.ClassMakerPackage;
import org.classmaker.NameLookup;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
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
 * <li>{@link org.classmaker.impl.BundleImpl#isNeedRefresh <em>Need Refresh
 * </em>}</li>
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
	 * <em>Dynamic EPackage</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDynamicEPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage dynamicEPackage;

	/**
	 * The default value of the '{@link #isNeedRefresh() <em>Need Refresh</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isNeedRefresh()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEED_REFRESH_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNeedRefresh() <em>Need Refresh</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isNeedRefresh()
	 * @generated
	 * @ordered
	 */
	protected boolean needRefresh = NEED_REFRESH_EDEFAULT;

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
			if (msg.getFeatureID(getClass()) == ClassMakerPackage.BUNDLE__NEED_REFRESH
					&& msg.getNewBooleanValue())
				make();
		}

	};

	protected void make() {
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
			// if (e.getStatus().getSeverity() == IStatus.ERROR)
			e.printStackTrace();
		}
		setNeedRefresh(false);
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
		if (isNeedRefresh()
				&& eIsSet(ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE))
			return getDynamicEPackage().getName();
		return getEPackage().getName();
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
		if (dynamicEPackage != null && dynamicEPackage.eIsProxy()) {
			InternalEObject oldDynamicEPackage = (InternalEObject) dynamicEPackage;
			dynamicEPackage = (EPackage) eResolveProxy(oldDynamicEPackage);
			if (dynamicEPackage != oldDynamicEPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE,
							oldDynamicEPackage, dynamicEPackage));
			}
		}
		return dynamicEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage basicGetDynamicEPackage() {
		return dynamicEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDynamicEPackage(EPackage newDynamicEPackage) {
		EPackage oldDynamicEPackage = dynamicEPackage;
		dynamicEPackage = newDynamicEPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.BUNDLE__DYNAMIC_EPACKAGE,
					oldDynamicEPackage, dynamicEPackage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isNeedRefresh() {
		return needRefresh;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setNeedRefresh(boolean newNeedRefresh) {
		boolean oldNeedRefresh = needRefresh;
		needRefresh = newNeedRefresh;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.BUNDLE__NEED_REFRESH, oldNeedRefresh,
					needRefresh));
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
			if (resolve)
				return getDynamicEPackage();
			return basicGetDynamicEPackage();
		case ClassMakerPackage.BUNDLE__NEED_REFRESH:
			return isNeedRefresh();
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
		case ClassMakerPackage.BUNDLE__NEED_REFRESH:
			setNeedRefresh((Boolean) newValue);
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
		case ClassMakerPackage.BUNDLE__NEED_REFRESH:
			setNeedRefresh(NEED_REFRESH_EDEFAULT);
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
		case ClassMakerPackage.BUNDLE__NEED_REFRESH:
			return needRefresh != NEED_REFRESH_EDEFAULT;
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
		result.append(", needRefresh: ");
		result.append(needRefresh);
		result.append(')');
		return result.toString();
	}

} // BundleImpl
