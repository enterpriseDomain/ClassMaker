/**
 */
package org.classmaker;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.classmaker.ClassMakerFactory
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
	ClassMakerPackage eINSTANCE = org.classmaker.impl.ClassMakerPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.classmaker.impl.BundleImpl <em>Bundle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classmaker.impl.BundleImpl
	 * @see org.classmaker.impl.ClassMakerPackageImpl#getBundle()
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
	 * The feature id for the '<em><b>Dynamic EPackage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__DYNAMIC_EPACKAGE = 2;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__STATE = 3;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__STATUS = 4;

	/**
	 * The number of structural features of the '<em>Bundle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_FEATURE_COUNT = 5;


	/**
	 * The meta object id for the '{@link org.classmaker.impl.ModelWorkspaceImpl <em>Model Workspace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classmaker.impl.ModelWorkspaceImpl
	 * @see org.classmaker.impl.ClassMakerPackageImpl#getModelWorkspace()
	 * @generated
	 */
	int MODEL_WORKSPACE = 1;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_WORKSPACE__CONTENTS = 0;

	/**
	 * The number of structural features of the '<em>Model Workspace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_WORKSPACE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.classmaker.State <em>State</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classmaker.State
	 * @see org.classmaker.impl.ClassMakerPackageImpl#getState()
	 * @generated
	 */
	int STATE = 2;

	/**
	 * The meta object id for the '<em>Adapter</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @see org.classmaker.impl.ClassMakerPackageImpl#getAdapter()
	 * @generated
	 */
	int ADAPTER = 3;

	/**
	 * The meta object id for the '<em>IStatus</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IStatus
	 * @see org.classmaker.impl.ClassMakerPackageImpl#getIStatus()
	 * @generated
	 */
	int ISTATUS = 4;


	/**
	 * Returns the meta object for class '{@link org.classmaker.Bundle <em>Bundle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bundle</em>'.
	 * @see org.classmaker.Bundle
	 * @generated
	 */
	EClass getBundle();

	/**
	 * Returns the meta object for the attribute '{@link org.classmaker.Bundle#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.classmaker.Bundle#getName()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.classmaker.Bundle#getEPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>EPackage</em>'.
	 * @see org.classmaker.Bundle#getEPackage()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_EPackage();

	/**
	 * Returns the meta object for the containment reference '{@link org.classmaker.Bundle#getDynamicEPackage <em>Dynamic EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Dynamic EPackage</em>'.
	 * @see org.classmaker.Bundle#getDynamicEPackage()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_DynamicEPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.classmaker.Bundle#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see org.classmaker.Bundle#getState()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_State();

	/**
	 * Returns the meta object for the attribute '{@link org.classmaker.Bundle#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see org.classmaker.Bundle#getStatus()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Status();

	/**
	 * Returns the meta object for class '{@link org.classmaker.ModelWorkspace <em>Model Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Workspace</em>'.
	 * @see org.classmaker.ModelWorkspace
	 * @generated
	 */
	EClass getModelWorkspace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.classmaker.ModelWorkspace#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contents</em>'.
	 * @see org.classmaker.ModelWorkspace#getContents()
	 * @see #getModelWorkspace()
	 * @generated
	 */
	EReference getModelWorkspace_Contents();

	/**
	 * Returns the meta object for enum '{@link org.classmaker.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>State</em>'.
	 * @see org.classmaker.State
	 * @generated
	 */
	EEnum getState();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.notify.Adapter <em>Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Adapter</em>'.
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @model instanceClass="org.eclipse.emf.common.notify.Adapter"
	 * @generated
	 */
	EDataType getAdapter();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IStatus <em>IStatus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IStatus</em>'.
	 * @see org.eclipse.core.runtime.IStatus
	 * @model instanceClass="org.eclipse.core.runtime.IStatus"
	 * @generated
	 */
	EDataType getIStatus();

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
		 * The meta object literal for the '{@link org.classmaker.impl.BundleImpl <em>Bundle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classmaker.impl.BundleImpl
		 * @see org.classmaker.impl.ClassMakerPackageImpl#getBundle()
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
		 * The meta object literal for the '<em><b>Dynamic EPackage</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUNDLE__DYNAMIC_EPACKAGE = eINSTANCE.getBundle_DynamicEPackage();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__STATE = eINSTANCE.getBundle_State();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__STATUS = eINSTANCE.getBundle_Status();

		/**
		 * The meta object literal for the '{@link org.classmaker.impl.ModelWorkspaceImpl <em>Model Workspace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classmaker.impl.ModelWorkspaceImpl
		 * @see org.classmaker.impl.ClassMakerPackageImpl#getModelWorkspace()
		 * @generated
		 */
		EClass MODEL_WORKSPACE = eINSTANCE.getModelWorkspace();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_WORKSPACE__CONTENTS = eINSTANCE.getModelWorkspace_Contents();

		/**
		 * The meta object literal for the '{@link org.classmaker.State <em>State</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classmaker.State
		 * @see org.classmaker.impl.ClassMakerPackageImpl#getState()
		 * @generated
		 */
		EEnum STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em>Adapter</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.notify.Adapter
		 * @see org.classmaker.impl.ClassMakerPackageImpl#getAdapter()
		 * @generated
		 */
		EDataType ADAPTER = eINSTANCE.getAdapter();

		/**
		 * The meta object literal for the '<em>IStatus</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IStatus
		 * @see org.classmaker.impl.ClassMakerPackageImpl#getIStatus()
		 * @generated
		 */
		EDataType ISTATUS = eINSTANCE.getIStatus();

	}

} //ClassMakerPackage
