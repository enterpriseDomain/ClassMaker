/**
 */
package org.k.classmaker;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.k.classmaker.Bundle#getName <em>Name</em>}</li>
 *   <li>{@link org.k.classmaker.Bundle#getEPackage <em>EPackage</em>}</li>
 *   <li>{@link org.k.classmaker.Bundle#getDynamicEPackage <em>Dynamic EPackage</em>}</li>
 *   <li>{@link org.k.classmaker.Bundle#isNeedRefresh <em>Need Refresh</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.k.classmaker.ClassMakerPackage#getBundle()
 * @model
 * @generated
 */
public interface Bundle extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.k.classmaker.ClassMakerPackage#getBundle_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.k.classmaker.Bundle#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>EPackage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EPackage</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EPackage</em>' containment reference.
	 * @see #setEPackage(EPackage)
	 * @see org.k.classmaker.ClassMakerPackage#getBundle_EPackage()
	 * @model containment="true"
	 * @generated
	 */
	EPackage getEPackage();

	/**
	 * Sets the value of the '{@link org.k.classmaker.Bundle#getEPackage <em>EPackage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EPackage</em>' containment reference.
	 * @see #getEPackage()
	 * @generated
	 */
	void setEPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Dynamic EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic EPackage</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic EPackage</em>' reference.
	 * @see #setDynamicEPackage(EPackage)
	 * @see org.k.classmaker.ClassMakerPackage#getBundle_DynamicEPackage()
	 * @model
	 * @generated
	 */
	EPackage getDynamicEPackage();

	/**
	 * Sets the value of the '{@link org.k.classmaker.Bundle#getDynamicEPackage <em>Dynamic EPackage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic EPackage</em>' reference.
	 * @see #getDynamicEPackage()
	 * @generated
	 */
	void setDynamicEPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Need Refresh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Need Refresh</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Need Refresh</em>' attribute.
	 * @see #setNeedRefresh(boolean)
	 * @see org.k.classmaker.ClassMakerPackage#getBundle_NeedRefresh()
	 * @model
	 * @generated
	 */
	boolean isNeedRefresh();

	/**
	 * Sets the value of the '{@link org.k.classmaker.Bundle#isNeedRefresh <em>Need Refresh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Need Refresh</em>' attribute.
	 * @see #isNeedRefresh()
	 * @generated
	 */
	void setNeedRefresh(boolean value);

} // Bundle
