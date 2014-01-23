/**
 */
package org.classsupplier.impl;

import org.classsupplier.Artifact;
import org.classsupplier.ClassSupplier;
import org.classsupplier.ClassSupplierFactory;
import org.classsupplier.ClassSupplierPackage;
import org.classsupplier.Infrastructure;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Class Factory</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.classsupplier.impl.ClassSupplierImpl#getWorkspace <em>Workspace</em>}</li>
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
	 * @generated
	 */
	public void setWorkspace(Infrastructure newWorkspace) {
		Infrastructure oldWorkspace = workspace;
		workspace = newWorkspace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE, oldWorkspace, workspace));
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
		switch (getWorkspace().containsArtifact(model)) {
		case Infrastructure.CONTAINS_LOADED:
			result = getWorkspace().getArtifact(model);
			if (result != null)
				if (result.getEPackage() != null)
					return result.getEPackage();
				else
					return makePrototype(model, result, monitor);
		case Infrastructure.CONTAINS_PROTOTYPE:
			result = getWorkspace().getArtifact(model);
			return makePrototype(model, result, monitor);
		case Infrastructure.DOESNT_CONTAIN:
		default:
			result = getWorkspace().createArtifact(model);
			result.make(monitor);
			return result.getEPackage();
		}
	}

	private EPackage makePrototype(EPackage model, Artifact result,
			IProgressMonitor monitor) {
		result.setPrototypeEPackage(model);
		result.make(monitor);
		return result.getEPackage();
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
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE:
				setWorkspace((Infrastructure)newValue);
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
			case ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE:
				setWorkspace((Infrastructure)null);
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

} // ClassSupplierImpl
