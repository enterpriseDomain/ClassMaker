/**
 */
package org.enterprisedomain.ecp;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.enterprisedomain.ecp.ECPPackage
 * @generated
 */
public interface ECPFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ECPFactory eINSTANCE = org.enterprisedomain.ecp.impl.ECPFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>EOperation Invocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EOperation Invocation</em>'.
	 * @generated
	 */
	EOperationInvocation createEOperationInvocation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ECPPackage getECPPackage();

} //ECPFactory
