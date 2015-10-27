/**
 */
package org.classupplier;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Customizer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.classupplier.Customizer#getAppliesTo <em>Applies To</em>}</li>
 * </ul>
 *
 * @see org.classupplier.ClassSupplierPackage#getCustomizer()
 * @model
 * @generated
 */
public interface Customizer extends EObject {
	/**
	 * Returns the value of the '<em><b>Applies To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applies To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applies To</em>' reference.
	 * @see #setAppliesTo(PhaseQualifier)
	 * @see org.classupplier.ClassSupplierPackage#getCustomizer_AppliesTo()
	 * @model
	 * @generated
	 */
	PhaseQualifier getAppliesTo();

	/**
	 * Sets the value of the '{@link org.classupplier.Customizer#getAppliesTo <em>Applies To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applies To</em>' reference.
	 * @see #getAppliesTo()
	 * @generated
	 */
	void setAppliesTo(PhaseQualifier value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model argsMany="true"
	 * @generated
	 */
	void customize(EList<Object> args);

} // Customizer
