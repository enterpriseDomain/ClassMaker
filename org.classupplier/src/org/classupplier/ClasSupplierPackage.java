/**
 */
package org.classupplier;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.classupplier.ClasSupplierFactory
 * @model kind="package"
 * @generated
 */
public interface ClasSupplierPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "classupplier";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org/classupplier/0.7.3";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "classupplier";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	ClasSupplierPackage eINSTANCE = org.classupplier.impl.ClasSupplierPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.classupplier.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.classupplier.impl.ArtifactImpl
	 * @see org.classupplier.impl.ClasSupplierPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = 0;

	/**
	 * The feature id for the '<em><b>EPackage</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EPACKAGE = 1;

	/**
	 * The feature id for the '<em><b>Prototype EPackage</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PROTOTYPE_EPACKAGE = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__VERSION = 3;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PROJECT_NAME = 4;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__STATE = 5;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__STATUS = 6;

	/**
	 * The feature id for the '<em><b>Loaded EPackage</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__LOADED_EPACKAGE = 7;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.InfrastructureImpl <em>Infrastructure</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.classupplier.impl.InfrastructureImpl
	 * @see org.classupplier.impl.ClasSupplierPackageImpl#getInfrastructure()
	 * @generated
	 */
	int INFRASTRUCTURE = 1;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE__ARTIFACTS = 0;

	/**
	 * The feature id for the '<em><b>Resource Set</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE__RESOURCE_SET = 1;

	/**
	 * The number of structural features of the '<em>Infrastructure</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.ClasSupplierImpl <em>Clas Supplier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.impl.ClasSupplierImpl
	 * @see org.classupplier.impl.ClasSupplierPackageImpl#getClasSupplier()
	 * @generated
	 */
	int CLAS_SUPPLIER = 2;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAS_SUPPLIER__WORKSPACE = 0;

	/**
	 * The number of structural features of the '<em>Clas Supplier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAS_SUPPLIER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.classupplier.State <em>State</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.classupplier.State
	 * @see org.classupplier.impl.ClasSupplierPackageImpl#getState()
	 * @generated
	 */
	int STATE = 3;

	/**
	 * The meta object id for the '<em>Adapter</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @see org.classupplier.impl.ClasSupplierPackageImpl#getAdapter()
	 * @generated
	 */
	int ADAPTER = 4;

	/**
	 * The meta object id for the '<em>IStatus</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IStatus
	 * @see org.classupplier.impl.ClasSupplierPackageImpl#getIStatus()
	 * @generated
	 */
	int ISTATUS = 5;

	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see org.classupplier.impl.ClasSupplierPackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 6;

	/**
	 * The meta object id for the '<em>Version</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.osgi.framework.Version
	 * @see org.classupplier.impl.ClasSupplierPackageImpl#getVersion()
	 * @generated
	 */
	int VERSION = 7;

	/**
	 * Returns the meta object for class '{@link org.classupplier.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see org.classupplier.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.Artifact#getName <em>Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.classupplier.Artifact#getName()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Name();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.classupplier.Artifact#getEPackage <em>EPackage</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>EPackage</em>'.
	 * @see org.classupplier.Artifact#getEPackage()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_EPackage();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.classupplier.Artifact#getPrototypeEPackage
	 * <em>Prototype EPackage</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference '<em>Prototype EPackage</em>'.
	 * @see org.classupplier.Artifact#getPrototypeEPackage()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_PrototypeEPackage();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.Artifact#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.classupplier.Artifact#getVersion()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.Artifact#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.classupplier.Artifact#getProjectName()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_ProjectName();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.Artifact#getState <em>State</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see org.classupplier.Artifact#getState()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_State();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.Artifact#getStatus <em>Status</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see org.classupplier.Artifact#getStatus()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Status();

	/**
	 * Returns the meta object for the reference '{@link org.classupplier.Artifact#getLoadedEPackage <em>Loaded EPackage</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Loaded EPackage</em>'.
	 * @see org.classupplier.Artifact#getLoadedEPackage()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_LoadedEPackage();

	/**
	 * Returns the meta object for class '
	 * {@link org.classupplier.Infrastructure <em>Infrastructure</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Infrastructure</em>'.
	 * @see org.classupplier.Infrastructure
	 * @generated
	 */
	EClass getInfrastructure();

	/**
	 * Returns the meta object for the containment reference list '{@link org.classupplier.Infrastructure#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifacts</em>'.
	 * @see org.classupplier.Infrastructure#getArtifacts()
	 * @see #getInfrastructure()
	 * @generated
	 */
	EReference getInfrastructure_Artifacts();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.Infrastructure#getResourceSet <em>Resource Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Set</em>'.
	 * @see org.classupplier.Infrastructure#getResourceSet()
	 * @see #getInfrastructure()
	 * @generated
	 */
	EAttribute getInfrastructure_ResourceSet();

	/**
	 * Returns the meta object for class '{@link org.classupplier.ClasSupplier <em>Clas Supplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Clas Supplier</em>'.
	 * @see org.classupplier.ClasSupplier
	 * @generated
	 */
	EClass getClasSupplier();

	/**
	 * Returns the meta object for the reference '{@link org.classupplier.ClasSupplier#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Workspace</em>'.
	 * @see org.classupplier.ClasSupplier#getWorkspace()
	 * @see #getClasSupplier()
	 * @generated
	 */
	EReference getClasSupplier_Workspace();

	/**
	 * Returns the meta object for enum '{@link org.classupplier.State <em>State</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>State</em>'.
	 * @see org.classupplier.State
	 * @generated
	 */
	EEnum getState();

	/**
	 * Returns the meta object for data type '
	 * {@link org.eclipse.emf.common.notify.Adapter <em>Adapter</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Adapter</em>'.
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @model instanceClass="org.eclipse.emf.common.notify.Adapter"
	 * @generated
	 */
	EDataType getAdapter();

	/**
	 * Returns the meta object for data type '
	 * {@link org.eclipse.core.runtime.IStatus <em>IStatus</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IStatus</em>'.
	 * @see org.eclipse.core.runtime.IStatus
	 * @model instanceClass="org.eclipse.core.runtime.IStatus"
	 * @generated
	 */
	EDataType getIStatus();

	/**
	 * Returns the meta object for data type '
	 * {@link org.eclipse.core.runtime.IProgressMonitor
	 * <em>IProgress Monitor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for data type '<em>IProgress Monitor</em>'.
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @model instanceClass="org.eclipse.core.runtime.IProgressMonitor"
	 * @generated
	 */
	EDataType getIProgressMonitor();

	/**
	 * Returns the meta object for data type '{@link org.osgi.framework.Version <em>Version</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Version</em>'.
	 * @see org.osgi.framework.Version
	 * @model instanceClass="org.osgi.framework.Version"
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
	ClasSupplierFactory getClasSupplierFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.classupplier.impl.ArtifactImpl <em>Artifact</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.classupplier.impl.ArtifactImpl
		 * @see org.classupplier.impl.ClasSupplierPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__NAME = eINSTANCE.getArtifact_Name();

		/**
		 * The meta object literal for the '<em><b>EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__EPACKAGE = eINSTANCE.getArtifact_EPackage();

		/**
		 * The meta object literal for the '<em><b>Prototype EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__PROTOTYPE_EPACKAGE = eINSTANCE.getArtifact_PrototypeEPackage();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__VERSION = eINSTANCE.getArtifact_Version();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__PROJECT_NAME = eINSTANCE.getArtifact_ProjectName();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__STATE = eINSTANCE.getArtifact_State();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__STATUS = eINSTANCE.getArtifact_Status();

		/**
		 * The meta object literal for the '<em><b>Loaded EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__LOADED_EPACKAGE = eINSTANCE.getArtifact_LoadedEPackage();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.InfrastructureImpl <em>Infrastructure</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see org.classupplier.impl.InfrastructureImpl
		 * @see org.classupplier.impl.ClasSupplierPackageImpl#getInfrastructure()
		 * @generated
		 */
		EClass INFRASTRUCTURE = eINSTANCE.getInfrastructure();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFRASTRUCTURE__ARTIFACTS = eINSTANCE.getInfrastructure_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Resource Set</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFRASTRUCTURE__RESOURCE_SET = eINSTANCE.getInfrastructure_ResourceSet();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.ClasSupplierImpl <em>Clas Supplier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.ClasSupplierImpl
		 * @see org.classupplier.impl.ClasSupplierPackageImpl#getClasSupplier()
		 * @generated
		 */
		EClass CLAS_SUPPLIER = eINSTANCE.getClasSupplier();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLAS_SUPPLIER__WORKSPACE = eINSTANCE.getClasSupplier_Workspace();

		/**
		 * The meta object literal for the '{@link org.classupplier.State <em>State</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.classupplier.State
		 * @see org.classupplier.impl.ClasSupplierPackageImpl#getState()
		 * @generated
		 */
		EEnum STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em>Adapter</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter
		 * @see org.classupplier.impl.ClasSupplierPackageImpl#getAdapter()
		 * @generated
		 */
		EDataType ADAPTER = eINSTANCE.getAdapter();

		/**
		 * The meta object literal for the '<em>IStatus</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.IStatus
		 * @see org.classupplier.impl.ClasSupplierPackageImpl#getIStatus()
		 * @generated
		 */
		EDataType ISTATUS = eINSTANCE.getIStatus();

		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see org.classupplier.impl.ClasSupplierPackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();

		/**
		 * The meta object literal for the '<em>Version</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.osgi.framework.Version
		 * @see org.classupplier.impl.ClasSupplierPackageImpl#getVersion()
		 * @generated
		 */
		EDataType VERSION = eINSTANCE.getVersion();

	}

} // ClasSupplierPackage
