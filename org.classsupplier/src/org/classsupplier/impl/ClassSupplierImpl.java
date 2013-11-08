/**
 */
package org.classsupplier.impl;

import org.classsupplier.Bundle;
import org.classsupplier.ClassSupplier;
import org.classsupplier.ClassSupplierFactory;
import org.classsupplier.ClassSupplierPackage;
import org.classsupplier.ModelWorkspace;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
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

	protected ModelWorkspace workspace = ClassSupplierFactory.eINSTANCE
			.createModelWorkspace();

	private IProgressMonitor progressMonitor;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ClassSupplierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.CLASS_SUPPLIER;
	}

	public ModelWorkspace getWorkspace() {
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ModelWorkspace basicGetWorkspace() {
		return workspace;
	}

	public IProgressMonitor monitor() {
		if (progressMonitor == null)
			progressMonitor = new NullProgressMonitor();
		return progressMonitor;
	}

	public void setMonitor(IProgressMonitor monitor) {
		this.progressMonitor = monitor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage supply(EPackage model, IProgressMonitor monitor) {
		Bundle result = null;
		setMonitor(monitor);
		if (getWorkspace().containsBundle(model)) {
			result = getWorkspace().getBundle(model);
			result.setDynamicEPackage(model);
			return result.getEPackage();
		}
		result = ClassSupplierFactory.eINSTANCE.createBundle();
		result.setName(model.getName());
		getWorkspace().registerBundle(model, result);
		result.setDynamicEPackage(model);
		workspace.getContents().add(result);
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE:
				return workspace != null;
		}
		return super.eIsSet(featureID);
	}

} // ClassSupplierImpl
