/**
 */
package org.enterprisedomain.ecp;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EOperation Invocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.ecp.EOperationInvocation#getEOperation <em>EOperation</em>}</li>
 *   <li>{@link org.enterprisedomain.ecp.EOperationInvocation#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.enterprisedomain.ecp.EOperationInvocation#getResult <em>Result</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.ecp.ECPPackage#getEOperationInvocation()
 * @model
 * @generated
 */
public interface EOperationInvocation extends EObject {
	/**
	 * Returns the value of the '<em><b>EOperation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EOperation</em>' reference.
	 * @see #setEOperation(EOperation)
	 * @see org.enterprisedomain.ecp.ECPPackage#getEOperationInvocation_EOperation()
	 * @model
	 * @generated
	 */
	EOperation getEOperation();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.ecp.EOperationInvocation#getEOperation <em>EOperation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EOperation</em>' reference.
	 * @see #getEOperation()
	 * @generated
	 */
	void setEOperation(EOperation value);

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' map.
	 * The key is of type {@link org.eclipse.emf.ecore.EParameter},
	 * and the value is of type {@link java.lang.Object},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' map.
	 * @see org.enterprisedomain.ecp.ECPPackage#getEOperationInvocation_Arguments()
	 * @model mapType="org.enterprisedomain.ecp.EParameterToObjectMapEntry&lt;org.eclipse.emf.ecore.EParameter, org.eclipse.emf.ecore.EJavaObject&gt;"
	 * @generated
	 */
	EMap<EParameter, Object> getArguments();

	/**
	 * Returns the value of the '<em><b>Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' attribute.
	 * @see #setResult(Object)
	 * @see org.enterprisedomain.ecp.ECPPackage#getEOperationInvocation_Result()
	 * @model
	 * @generated
	 */
	Object getResult();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.ecp.EOperationInvocation#getResult <em>Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' attribute.
	 * @see #getResult()
	 * @generated
	 */
	void setResult(Object value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void execute();

} // EOperationInvocation
