/**
 */
package org.classupplier.impl;

import org.classupplier.ClassSupplier;
import org.classupplier.ClassSupplierFactory;
import org.classupplier.Workspace;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Class Supplier</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.classupplier.impl.ClassSupplierImpl#getWorkspace <em>Workspace
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassSupplierImpl implements ClassSupplier {

	/**
	 * The cached value of the '{@link #getWorkspace() <em>Workspace</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWorkspace()
	 * @generated
	 * @ordered
	 */
	protected Workspace workspace;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ClassSupplierImpl() {
		workspace = ClassSupplierFactory.eINSTANCE.createWorkspace();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Workspace getWorkspace() {
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setWorkspace(Workspace newWorkspace) {
		workspace = newWorkspace;
	}

} // ClassSupplierImpl
