/**
 */
package org.classupplier;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Supplier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classupplier.ClassSupplier#getWorkspace <em>Workspace</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classupplier.ClassSupplierPackage#getClassSupplier()
 * @model superTypes="org.classupplier.IAdaptable"
 * @generated
 */
public interface ClassSupplier extends EObject, IAdaptable {
	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workspace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workspace</em>' reference.
	 * @see org.classupplier.ClassSupplierPackage#getClassSupplier_Workspace()
	 * @model changeable="false"
	 * @generated
	 */
	Infrastructure getWorkspace();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Same as <code>org.classupplier.ClasSupplier#supply(EPackage,IProgressMonitor)</code> but with <code>NullProgressMonitor</code> as default.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	EPackage supply(EPackage model);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Perform the dynamic model transformation process which yields the running model object.
	 * <!-- end-model-doc -->
	 * @model monitorDataType="org.classupplier.IProgressMonitor"
	 * @generated
	 */
	EPackage supply(EPackage model, IProgressMonitor monitor);

} // ClassSupplier
