/**
 */
package org.enterprisedomain.ecp;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.enterprisedomain.ecp.ECPFactory
 * @model kind="package"
 * @generated
 */
public interface ECPPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "ecp";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://org/enterprisedomain/ClassMaker/ECP/0.8.46";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "ECP";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	ECPPackage eINSTANCE = org.enterprisedomain.ecp.impl.ECPPackageImpl.init();

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.ecp.impl.EOperationInvocationImpl <em>EOperation
	 * Invocation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.ecp.impl.EOperationInvocationImpl
	 * @see org.enterprisedomain.ecp.impl.ECPPackageImpl#getEOperationInvocation()
	 * @generated
	 */
	int EOPERATION_INVOCATION = 0;

	/**
	 * The feature id for the '<em><b>EOperation</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOPERATION_INVOCATION__EOPERATION = 0;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOPERATION_INVOCATION__ARGUMENTS = 1;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOPERATION_INVOCATION__RESULT = 2;

	/**
	 * The number of structural features of the '<em>EOperation Invocation</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOPERATION_INVOCATION_FEATURE_COUNT = 3;

	/**
	 * The operation id for the '<em>Execute</em>' operation. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOPERATION_INVOCATION___EXECUTE = 0;

	/**
	 * The number of operations of the '<em>EOperation Invocation</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOPERATION_INVOCATION_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.ecp.impl.EParameterToObjectMapEntryImpl
	 * <em>EParameter To Object Map Entry</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.enterprisedomain.ecp.impl.EParameterToObjectMapEntryImpl
	 * @see org.enterprisedomain.ecp.impl.ECPPackageImpl#getEParameterToObjectMapEntry()
	 * @generated
	 */
	int EPARAMETER_TO_OBJECT_MAP_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EPARAMETER_TO_OBJECT_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EPARAMETER_TO_OBJECT_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>EParameter To Object Map
	 * Entry</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EPARAMETER_TO_OBJECT_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>EParameter To Object Map Entry</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EPARAMETER_TO_OBJECT_MAP_ENTRY_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.ecp.EOperationInvocation <em>EOperation
	 * Invocation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EOperation Invocation</em>'.
	 * @see org.enterprisedomain.ecp.EOperationInvocation
	 * @generated
	 */
	EClass getEOperationInvocation();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.ecp.EOperationInvocation#getEOperation
	 * <em>EOperation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>EOperation</em>'.
	 * @see org.enterprisedomain.ecp.EOperationInvocation#getEOperation()
	 * @see #getEOperationInvocation()
	 * @generated
	 */
	EReference getEOperationInvocation_EOperation();

	/**
	 * Returns the meta object for the map
	 * '{@link org.enterprisedomain.ecp.EOperationInvocation#getArguments
	 * <em>Arguments</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Arguments</em>'.
	 * @see org.enterprisedomain.ecp.EOperationInvocation#getArguments()
	 * @see #getEOperationInvocation()
	 * @generated
	 */
	EReference getEOperationInvocation_Arguments();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.ecp.EOperationInvocation#getResult
	 * <em>Result</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Result</em>'.
	 * @see org.enterprisedomain.ecp.EOperationInvocation#getResult()
	 * @see #getEOperationInvocation()
	 * @generated
	 */
	EAttribute getEOperationInvocation_Result();

	/**
	 * Returns the meta object for the
	 * '{@link org.enterprisedomain.ecp.EOperationInvocation#execute()
	 * <em>Execute</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the '<em>Execute</em>' operation.
	 * @see org.enterprisedomain.ecp.EOperationInvocation#execute()
	 * @generated
	 */
	EOperation getEOperationInvocation__Execute();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>EParameter
	 * To Object Map Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EParameter To Object Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="org.eclipse.emf.ecore.EParameter"
	 *        valueDataType="org.eclipse.emf.ecore.EJavaObject"
	 * @generated
	 */
	EClass getEParameterToObjectMapEntry();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry
	 * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEParameterToObjectMapEntry()
	 * @generated
	 */
	EReference getEParameterToObjectMapEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEParameterToObjectMapEntry()
	 * @generated
	 */
	EAttribute getEParameterToObjectMapEntry_Value();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ECPFactory getECPFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.ecp.impl.EOperationInvocationImpl <em>EOperation
		 * Invocation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.ecp.impl.EOperationInvocationImpl
		 * @see org.enterprisedomain.ecp.impl.ECPPackageImpl#getEOperationInvocation()
		 * @generated
		 */
		EClass EOPERATION_INVOCATION = eINSTANCE.getEOperationInvocation();

		/**
		 * The meta object literal for the '<em><b>EOperation</b></em>' reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EOPERATION_INVOCATION__EOPERATION = eINSTANCE.getEOperationInvocation_EOperation();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' map feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EOPERATION_INVOCATION__ARGUMENTS = eINSTANCE.getEOperationInvocation_Arguments();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOPERATION_INVOCATION__RESULT = eINSTANCE.getEOperationInvocation_Result();

		/**
		 * The meta object literal for the '<em><b>Execute</b></em>' operation. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EOperation EOPERATION_INVOCATION___EXECUTE = eINSTANCE.getEOperationInvocation__Execute();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.ecp.impl.EParameterToObjectMapEntryImpl
		 * <em>EParameter To Object Map Entry</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.enterprisedomain.ecp.impl.EParameterToObjectMapEntryImpl
		 * @see org.enterprisedomain.ecp.impl.ECPPackageImpl#getEParameterToObjectMapEntry()
		 * @generated
		 */
		EClass EPARAMETER_TO_OBJECT_MAP_ENTRY = eINSTANCE.getEParameterToObjectMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EPARAMETER_TO_OBJECT_MAP_ENTRY__KEY = eINSTANCE.getEParameterToObjectMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EPARAMETER_TO_OBJECT_MAP_ENTRY__VALUE = eINSTANCE.getEParameterToObjectMapEntry_Value();

	}

} // ECPPackage
