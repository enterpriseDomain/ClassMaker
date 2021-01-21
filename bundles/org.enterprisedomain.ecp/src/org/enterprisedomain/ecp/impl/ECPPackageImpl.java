/**
 */
package org.enterprisedomain.ecp.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.enterprisedomain.ecp.ECPFactory;
import org.enterprisedomain.ecp.ECPPackage;
import org.enterprisedomain.ecp.EOperationInvocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ECPPackageImpl extends EPackageImpl implements ECPPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eOperationInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eParameterToObjectMapEntryEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.enterprisedomain.ecp.ECPPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ECPPackageImpl() {
		super(eNS_URI, ECPFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ECPPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ECPPackage init() {
		if (isInited) return (ECPPackage)EPackage.Registry.INSTANCE.getEPackage(ECPPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredECPPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ECPPackageImpl theECPPackage = registeredECPPackage instanceof ECPPackageImpl ? (ECPPackageImpl)registeredECPPackage : new ECPPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theECPPackage.createPackageContents();

		// Initialize created meta-data
		theECPPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theECPPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ECPPackage.eNS_URI, theECPPackage);
		return theECPPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEOperationInvocation() {
		return eOperationInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEOperationInvocation_EOperation() {
		return (EReference)eOperationInvocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEOperationInvocation_Arguments() {
		return (EReference)eOperationInvocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEOperationInvocation_Result() {
		return (EAttribute)eOperationInvocationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getEOperationInvocation__Execute() {
		return eOperationInvocationEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEParameterToObjectMapEntry() {
		return eParameterToObjectMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEParameterToObjectMapEntry_Key() {
		return (EReference)eParameterToObjectMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEParameterToObjectMapEntry_Value() {
		return (EAttribute)eParameterToObjectMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ECPFactory getECPFactory() {
		return (ECPFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		eOperationInvocationEClass = createEClass(EOPERATION_INVOCATION);
		createEReference(eOperationInvocationEClass, EOPERATION_INVOCATION__EOPERATION);
		createEReference(eOperationInvocationEClass, EOPERATION_INVOCATION__ARGUMENTS);
		createEAttribute(eOperationInvocationEClass, EOPERATION_INVOCATION__RESULT);
		createEOperation(eOperationInvocationEClass, EOPERATION_INVOCATION___EXECUTE);

		eParameterToObjectMapEntryEClass = createEClass(EPARAMETER_TO_OBJECT_MAP_ENTRY);
		createEReference(eParameterToObjectMapEntryEClass, EPARAMETER_TO_OBJECT_MAP_ENTRY__KEY);
		createEAttribute(eParameterToObjectMapEntryEClass, EPARAMETER_TO_OBJECT_MAP_ENTRY__VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(eOperationInvocationEClass, EOperationInvocation.class, "EOperationInvocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEOperationInvocation_EOperation(), ecorePackage.getEOperation(), null, "eOperation", null, 0, 1, EOperationInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEOperationInvocation_Arguments(), this.getEParameterToObjectMapEntry(), null, "arguments", null, 0, -1, EOperationInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEOperationInvocation_Result(), ecorePackage.getEJavaObject(), "result", null, 0, 1, EOperationInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getEOperationInvocation__Execute(), null, "execute", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(eParameterToObjectMapEntryEClass, Map.Entry.class, "EParameterToObjectMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEParameterToObjectMapEntry_Key(), ecorePackage.getEParameter(), null, "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEParameterToObjectMapEntry_Value(), ecorePackage.getEJavaObject(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ECPPackageImpl
