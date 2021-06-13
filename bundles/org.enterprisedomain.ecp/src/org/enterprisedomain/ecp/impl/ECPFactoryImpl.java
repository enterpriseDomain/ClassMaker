/**
 */
package org.enterprisedomain.ecp.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.enterprisedomain.ecp.ECPFactory;
import org.enterprisedomain.ecp.ECPPackage;
import org.enterprisedomain.ecp.EOperationInvocation;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ECPFactoryImpl extends EFactoryImpl implements ECPFactory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static ECPFactory init() {
		try {
			ECPFactory theECPFactory = (ECPFactory) EPackage.Registry.INSTANCE.getEFactory(ECPPackage.eNS_URI);
			if (theECPFactory != null) {
				return theECPFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ECPFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public ECPFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case ECPPackage.EOPERATION_INVOCATION:
			return createEOperationInvocation();
		case ECPPackage.EPARAMETER_TO_OBJECT_MAP_ENTRY:
			return (EObject) createEParameterToObjectMapEntry();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EOperationInvocation createEOperationInvocation() {
		EOperationInvocationImpl eOperationInvocation = new EOperationInvocationImpl();
		return eOperationInvocation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Map.Entry<EParameter, Object> createEParameterToObjectMapEntry() {
		EParameterToObjectMapEntryImpl eParameterToObjectMapEntry = new EParameterToObjectMapEntryImpl();
		return eParameterToObjectMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ECPPackage getECPPackage() {
		return (ECPPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ECPPackage getPackage() {
		return ECPPackage.eINSTANCE;
	}

} // ECPFactoryImpl
