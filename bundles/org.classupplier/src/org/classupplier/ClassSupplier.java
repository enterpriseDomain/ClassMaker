/**
 */
package org.classupplier;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Class Supplier</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.classupplier.ClassSupplier#getWorkspace <em>Workspace</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @see org.classupplier.ClassSupplierPackage#getClassSupplier()
 * @model superTypes="org.classupplier.IAdaptable"
 * @generated NOT
 */
public interface ClassSupplier {

	public static final String INVOCATION_DELEGATE_URI = "org.classupplier.reflection.java"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workspace</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Workspace</em>' reference.
	 * @see org.classupplier.ClassSupplierPackage#getClassSupplier_Workspace()
	 * @model changeable="false"
	 * @generated
	 */
	Workspace getWorkspace();

} // ClassSupplier
