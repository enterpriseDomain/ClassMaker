/**
 */
package org.classsupplier;

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
 * @see org.classsupplier.ClassSupplierFactory
 * @model kind="package"
 * @generated
 */
public interface ClassSupplierPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "classsupplier";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org/classsupplier/0.7.1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "classsupplier";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClassSupplierPackage eINSTANCE = org.classsupplier.impl.ClassSupplierPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.classsupplier.impl.BundleImpl <em>Bundle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classsupplier.impl.BundleImpl
	 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getBundle()
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
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__VERSION = 3;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__PROJECT_NAME = 4;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__STATE = 5;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__STATUS = 6;

	/**
	 * The number of structural features of the '<em>Bundle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.classsupplier.impl.ModelWorkspaceImpl <em>Model Workspace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classsupplier.impl.ModelWorkspaceImpl
	 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getModelWorkspace()
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
	 * The feature id for the '<em><b>Resource Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_WORKSPACE__RESOURCE_SET = 1;

	/**
	 * The number of structural features of the '<em>Model Workspace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_WORKSPACE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.classsupplier.impl.ClassSupplierImpl <em>Class Supplier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classsupplier.impl.ClassSupplierImpl
	 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getClassSupplier()
	 * @generated
	 */
	int CLASS_SUPPLIER = 2;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_SUPPLIER__WORKSPACE = 0;

	/**
	 * The number of structural features of the '<em>Class Supplier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_SUPPLIER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.classsupplier.State <em>State</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classsupplier.State
	 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getState()
	 * @generated
	 */
	int STATE = 3;

	/**
	 * The meta object id for the '<em>Adapter</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getAdapter()
	 * @generated
	 */
	int ADAPTER = 4;

	/**
	 * The meta object id for the '<em>IStatus</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IStatus
	 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getIStatus()
	 * @generated
	 */
	int ISTATUS = 5;

	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 6;


	/**
	 * The meta object id for the '<em>Version</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classsupplier.Version
	 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getVersion()
	 * @generated
	 */
	int VERSION = 7;


	/**
	 * Returns the meta object for class '{@link org.classsupplier.Bundle <em>Bundle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bundle</em>'.
	 * @see org.classsupplier.Bundle
	 * @generated
	 */
	EClass getBundle();

	/**
	 * Returns the meta object for the attribute '{@link org.classsupplier.Bundle#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.classsupplier.Bundle#getName()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.classsupplier.Bundle#getEPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>EPackage</em>'.
	 * @see org.classsupplier.Bundle#getEPackage()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_EPackage();

	/**
	 * Returns the meta object for the containment reference '{@link org.classsupplier.Bundle#getDynamicEPackage <em>Dynamic EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Dynamic EPackage</em>'.
	 * @see org.classsupplier.Bundle#getDynamicEPackage()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_DynamicEPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.classsupplier.Bundle#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.classsupplier.Bundle#getVersion()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.classsupplier.Bundle#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.classsupplier.Bundle#getProjectName()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_ProjectName();

	/**
	 * Returns the meta object for the attribute '{@link org.classsupplier.Bundle#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see org.classsupplier.Bundle#getState()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_State();

	/**
	 * Returns the meta object for the attribute '{@link org.classsupplier.Bundle#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see org.classsupplier.Bundle#getStatus()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Status();

	/**
	 * Returns the meta object for class '{@link org.classsupplier.ModelWorkspace <em>Model Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Workspace</em>'.
	 * @see org.classsupplier.ModelWorkspace
	 * @generated
	 */
	EClass getModelWorkspace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.classsupplier.ModelWorkspace#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contents</em>'.
	 * @see org.classsupplier.ModelWorkspace#getContents()
	 * @see #getModelWorkspace()
	 * @generated
	 */
	EReference getModelWorkspace_Contents();

	/**
	 * Returns the meta object for the attribute '{@link org.classsupplier.ModelWorkspace#getResourceSet <em>Resource Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Set</em>'.
	 * @see org.classsupplier.ModelWorkspace#getResourceSet()
	 * @see #getModelWorkspace()
	 * @generated
	 */
	EAttribute getModelWorkspace_ResourceSet();

	/**
	 * Returns the meta object for class '{@link org.classsupplier.ClassSupplier <em>Class Supplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Supplier</em>'.
	 * @see org.classsupplier.ClassSupplier
	 * @generated
	 */
	EClass getClassSupplier();

	/**
	 * Returns the meta object for the reference '{@link org.classsupplier.ClassSupplier#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Workspace</em>'.
	 * @see org.classsupplier.ClassSupplier#getWorkspace()
	 * @see #getClassSupplier()
	 * @generated
	 */
	EReference getClassSupplier_Workspace();

	/**
	 * Returns the meta object for enum '{@link org.classsupplier.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>State</em>'.
	 * @see org.classsupplier.State
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
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IProgressMonitor <em>IProgress Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IProgress Monitor</em>'.
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @model instanceClass="org.eclipse.core.runtime.IProgressMonitor"
	 * @generated
	 */
	EDataType getIProgressMonitor();

	/**
	 * Returns the meta object for data type '{@link org.classsupplier.Version <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Version</em>'.
	 * @see org.classsupplier.Version
	 * @model instanceClass="org.classsupplier.Version"
	 * @generated
	 */
	EDataType getVersion();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ClassSupplierFactory getClassSupplierFactory();

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
		 * The meta object literal for the '{@link org.classsupplier.impl.BundleImpl <em>Bundle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classsupplier.impl.BundleImpl
		 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getBundle()
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
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__VERSION = eINSTANCE.getBundle_Version();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__PROJECT_NAME = eINSTANCE.getBundle_ProjectName();

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
		 * The meta object literal for the '{@link org.classsupplier.impl.ModelWorkspaceImpl <em>Model Workspace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classsupplier.impl.ModelWorkspaceImpl
		 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getModelWorkspace()
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
		 * The meta object literal for the '<em><b>Resource Set</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_WORKSPACE__RESOURCE_SET = eINSTANCE.getModelWorkspace_ResourceSet();

		/**
		 * The meta object literal for the '{@link org.classsupplier.impl.ClassSupplierImpl <em>Class Supplier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classsupplier.impl.ClassSupplierImpl
		 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getClassSupplier()
		 * @generated
		 */
		EClass CLASS_SUPPLIER = eINSTANCE.getClassSupplier();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_SUPPLIER__WORKSPACE = eINSTANCE.getClassSupplier_Workspace();

		/**
		 * The meta object literal for the '{@link org.classsupplier.State <em>State</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classsupplier.State
		 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getState()
		 * @generated
		 */
		EEnum STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em>Adapter</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.notify.Adapter
		 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getAdapter()
		 * @generated
		 */
		EDataType ADAPTER = eINSTANCE.getAdapter();

		/**
		 * The meta object literal for the '<em>IStatus</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IStatus
		 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getIStatus()
		 * @generated
		 */
		EDataType ISTATUS = eINSTANCE.getIStatus();

		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();

		/**
		 * The meta object literal for the '<em>Version</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classsupplier.Version
		 * @see org.classsupplier.impl.ClassSupplierPackageImpl#getVersion()
		 * @generated
		 */
		EDataType VERSION = eINSTANCE.getVersion();

	}

} //ClassSupplierPackage
