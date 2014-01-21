/**
 */
package org.classsupplier;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Class Factory</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classsupplier.ClassSupplier#getWorkspace <em>Workspace</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classsupplier.ClassSupplierPackage#getClassSupplier()
 * @model
 * @generated
 */
public interface ClassSupplier extends EObject {
	
	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workspace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workspace</em>' reference.
	 * @see #setWorkspace(Infrastructure)
	 * @see org.classsupplier.ClassSupplierPackage#getClassSupplier_Workspace()
	 * @model
	 * @generated
	 */
	Infrastructure getWorkspace();

	/**
	 * Sets the value of the '{@link org.classsupplier.ClassSupplier#getWorkspace <em>Workspace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workspace</em>' reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Infrastructure value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EPackage supply(EPackage model);

	/**
	 * <!-- begin-user-doc -->
	 *  
	 *  @param  model    a blueprint
	 *  @param 	 monitor  progress reporting for such is a long-running operation
	 *  @return          regular Java program
	 *  <!-- end-user-doc -->
	 * @model monitorDataType="org.classsupplier.IProgressMonitor"
	 * @generated
	 */
	EPackage supply(EPackage model, IProgressMonitor monitor);

	IProgressMonitor monitor();

	void setMonitor(IProgressMonitor monitor);

} // ClassSupplier
