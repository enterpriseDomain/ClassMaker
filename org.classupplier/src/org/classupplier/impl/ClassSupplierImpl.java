/**
 */
package org.classupplier.impl;

import org.classupplier.Artifact;
import org.classupplier.ClassSupplier;
import org.classupplier.ClassSupplierFactory;
import org.classupplier.ClassSupplierPackage;
import org.classupplier.Infrastructure;
import org.classupplier.Phase;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Class Supplier</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.classupplier.impl.ClassSupplierImpl#getWorkspace <em>Workspace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassSupplierImpl extends EObjectImpl implements ClassSupplier {
	/**
	 * The cached value of the '{@link #getWorkspace() <em>Workspace</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getWorkspace()
	 * @generated
	 * @ordered
	 */
	protected Infrastructure workspace;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ClassSupplierImpl() {
		super();
		workspace = ClassSupplierFactory.eINSTANCE.createInfrastructure();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.CLASS_SUPPLIER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Infrastructure getWorkspace() {
		if (workspace != null && workspace.eIsProxy()) {
			InternalEObject oldWorkspace = (InternalEObject)workspace;
			workspace = (Infrastructure)eResolveProxy(oldWorkspace);
			if (workspace != oldWorkspace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE, oldWorkspace, workspace));
			}
		}
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Infrastructure basicGetWorkspace() {
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setWorkspace(Infrastructure newWorkspace) {
		Infrastructure oldWorkspace = workspace;
		workspace = newWorkspace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE,
					oldWorkspace, workspace));
	}

	/**
	 * <!-- begin-user-doc -->With output of progress to System.out<!--
	 * end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage supply(EPackage model) {
		return supply(model, new CodeGenUtil.EclipseUtil.StreamProgressMonitor(
				System.out));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage supply(EPackage model, IProgressMonitor monitor) {
		Artifact result = null;
		switch (getWorkspace().contains(model).getValue()) {
		case Phase.LOADED_VALUE:
			result = getWorkspace().getArtifact(model);
			if (result != null)
				if (result.getActualEPackage() != null)
					return result.getActualEPackage();
				else
					return producePrototype(model, result, monitor);
		case Phase.MODELED_VALUE:
			result = getWorkspace().getArtifact(model);
			return producePrototype(model, result, monitor);
		case Phase.NEW_VALUE:
		default:
			result = getWorkspace().createArtifact(model);
			result.produce(monitor);
			return result.getActualEPackage();
		}
	}

	private EPackage producePrototype(EPackage model, Artifact result,
			IProgressMonitor monitor) {
		result.setPrototypeEPackage(model);
		result.setStage(Phase.MODELED);
		result.produce(monitor);
		return result.getActualEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE:
				if (resolve) return getWorkspace();
				return basicGetWorkspace();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE:
			setWorkspace((Infrastructure) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE:
			setWorkspace((Infrastructure) null);
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
			case ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE:
				return workspace != null;
		}
		return super.eIsSet(featureID);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

} // ClassSupplierImpl
