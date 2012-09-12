/**
 */
package org.k.classmaker;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.k.classmaker.ClassMakerFactory
 * @model kind="package"
 * @generated
 */
public interface ClassMakerPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "classmaker";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org/classmaker/0.6";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "classmaker";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClassMakerPackage eINSTANCE = org.k.classmaker.impl.ClassMakerPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.k.classmaker.impl.BundleImpl <em>Bundle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.k.classmaker.impl.BundleImpl
	 * @see org.k.classmaker.impl.ClassMakerPackageImpl#getBundle()
	 * @generated
	 */
	int BUNDLE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>EPackage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__EPACKAGE = 1;

	/**
	 * The feature id for the '<em><b>Dynamic EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__DYNAMIC_EPACKAGE = 2;

	/**
	 * The feature id for the '<em><b>Need Refresh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__NEED_REFRESH = 3;

	/**
	 * The number of structural features of the '<em>Bundle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link org.k.classmaker.Bundle <em>Bundle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bundle</em>'.
	 * @see org.k.classmaker.Bundle
	 * @generated
	 */
	EClass getBundle();

	/**
	 * Returns the meta object for the attribute '{@link org.k.classmaker.Bundle#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.k.classmaker.Bundle#getName()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.k.classmaker.Bundle#getEPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>EPackage</em>'.
	 * @see org.k.classmaker.Bundle#getEPackage()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_EPackage();

	/**
	 * Returns the meta object for the reference '{@link org.k.classmaker.Bundle#getDynamicEPackage <em>Dynamic EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dynamic EPackage</em>'.
	 * @see org.k.classmaker.Bundle#getDynamicEPackage()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_DynamicEPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.k.classmaker.Bundle#isNeedRefresh <em>Need Refresh</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Need Refresh</em>'.
	 * @see org.k.classmaker.Bundle#isNeedRefresh()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_NeedRefresh();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ClassMakerFactory getClassMakerFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.k.classmaker.impl.BundleImpl <em>Bundle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.k.classmaker.impl.BundleImpl
		 * @see org.k.classmaker.impl.ClassMakerPackageImpl#getBundle()
		 * @generated
		 */
		EClass BUNDLE = eINSTANCE.getBundle();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__NAME = eINSTANCE.getBundle_Name();

		/**
		 * The meta object literal for the '<em><b>EPackage</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUNDLE__EPACKAGE = eINSTANCE.getBundle_EPackage();

		/**
		 * The meta object literal for the '<em><b>Dynamic EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUNDLE__DYNAMIC_EPACKAGE = eINSTANCE.getBundle_DynamicEPackage();

		/**
		 * The meta object literal for the '<em><b>Need Refresh</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__NEED_REFRESH = eINSTANCE.getBundle_NeedRefresh();

	}

} //ClassMakerPackage
