/**
 */
package org.classupplier.impl;

import org.classupplier.Artifact;
import org.classupplier.ClasSupplier;
import org.classupplier.ClasSupplierFactory;
import org.classupplier.ClasSupplierPackage;
import org.classupplier.Infrastructure;
import org.classupplier.State;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class ClasSupplierPackageImpl extends EPackageImpl implements
		ClasSupplierPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infrastructureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clasSupplierEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum stateEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType adapterEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iStatusEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iProgressMonitorEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType versionEDataType = null;

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
	 * @see org.classupplier.ClasSupplierPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ClasSupplierPackageImpl() {
		super(eNS_URI, ClasSupplierFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link ClasSupplierPackage#eINSTANCE}
	 * when that field is accessed. Clients should not invoke it directly.
	 * Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ClasSupplierPackage init() {
		if (isInited) return (ClasSupplierPackage)EPackage.Registry.INSTANCE.getEPackage(ClasSupplierPackage.eNS_URI);

		// Obtain or create and register package
		ClasSupplierPackageImpl theClasSupplierPackage = (ClasSupplierPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ClasSupplierPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ClasSupplierPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theClasSupplierPackage.createPackageContents();

		// Initialize created meta-data
		theClasSupplierPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theClasSupplierPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ClasSupplierPackage.eNS_URI, theClasSupplierPackage);
		return theClasSupplierPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArtifact() {
		return artifactEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifact_Name() {
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifact_EPackage() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifact_PrototypeEPackage() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifact_Version() {
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifact_ProjectName() {
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifact_State() {
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArtifact_Status() {
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifact_LoadedEPackage() {
		return (EReference)artifactEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfrastructure() {
		return infrastructureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfrastructure_Artifacts() {
		return (EReference)infrastructureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfrastructure_ResourceSet() {
		return (EAttribute)infrastructureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClasSupplier() {
		return clasSupplierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClasSupplier_Workspace() {
		return (EReference)clasSupplierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getState() {
		return stateEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getAdapter() {
		return adapterEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIStatus() {
		return iStatusEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIProgressMonitor() {
		return iProgressMonitorEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getVersion() {
		return versionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClasSupplierFactory getClasSupplierFactory() {
		return (ClasSupplierFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
		artifactEClass = createEClass(ARTIFACT);
		createEAttribute(artifactEClass, ARTIFACT__NAME);
		createEReference(artifactEClass, ARTIFACT__EPACKAGE);
		createEReference(artifactEClass, ARTIFACT__PROTOTYPE_EPACKAGE);
		createEAttribute(artifactEClass, ARTIFACT__VERSION);
		createEAttribute(artifactEClass, ARTIFACT__PROJECT_NAME);
		createEAttribute(artifactEClass, ARTIFACT__STATE);
		createEAttribute(artifactEClass, ARTIFACT__STATUS);
		createEReference(artifactEClass, ARTIFACT__LOADED_EPACKAGE);

		infrastructureEClass = createEClass(INFRASTRUCTURE);
		createEReference(infrastructureEClass, INFRASTRUCTURE__ARTIFACTS);
		createEAttribute(infrastructureEClass, INFRASTRUCTURE__RESOURCE_SET);

		clasSupplierEClass = createEClass(CLAS_SUPPLIER);
		createEReference(clasSupplierEClass, CLAS_SUPPLIER__WORKSPACE);

		// Create enums
		stateEEnum = createEEnum(STATE);

		// Create data types
		adapterEDataType = createEDataType(ADAPTER);
		iStatusEDataType = createEDataType(ISTATUS);
		iProgressMonitorEDataType = createEDataType(IPROGRESS_MONITOR);
		versionEDataType = createEDataType(VERSION);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(artifactEClass, Artifact.class, "Artifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifact_Name(), ecorePackage.getEString(), "name", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_EPackage(), theEcorePackage.getEPackage(), null, "ePackage", null, 0, 1, Artifact.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_PrototypeEPackage(), theEcorePackage.getEPackage(), null, "prototypeEPackage", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Version(), this.getVersion(), "version", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_ProjectName(), ecorePackage.getEString(), "projectName", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_State(), this.getState(), "state", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Status(), this.getIStatus(), "status", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_LoadedEPackage(), theEcorePackage.getEPackage(), null, "loadedEPackage", null, 0, 1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(artifactEClass, null, "make", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(infrastructureEClass, Infrastructure.class, "Infrastructure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInfrastructure_Artifacts(), this.getArtifact(), null, "artifacts", null, 0, -1, Infrastructure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInfrastructure_ResourceSet(), ecorePackage.getEResourceSet(), "resourceSet", null, 0, 1, Infrastructure.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(infrastructureEClass, null, "addRefreshListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAdapter(), "listener", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(infrastructureEClass, null, "removeRefreshListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAdapter(), "listener", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(infrastructureEClass, this.getArtifact(), "createArtifact", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "blueprint", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(infrastructureEClass, null, "registerArtifact", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getArtifact(), "artifact", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(infrastructureEClass, null, "unregisterArtifact", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getArtifact(), "artifact", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(infrastructureEClass, ecorePackage.getEInt(), "containsArtifact", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "blueprint", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(infrastructureEClass, this.getArtifact(), "getArtifact", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(infrastructureEClass, this.getArtifact(), "getArtifact", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "ePackage", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(infrastructureEClass, null, "save", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(clasSupplierEClass, ClasSupplier.class, "ClasSupplier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClasSupplier_Workspace(), this.getInfrastructure(), null, "workspace", null, 0, 1, ClasSupplier.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(clasSupplierEClass, theEcorePackage.getEPackage(), "supply", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "model", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(clasSupplierEClass, theEcorePackage.getEPackage(), "supply", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "model", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(stateEEnum, State.class, "State");
		addEEnumLiteral(stateEEnum, State.CREATED);
		addEEnumLiteral(stateEEnum, State.PROTOTYPE);
		addEEnumLiteral(stateEEnum, State.PROCESSING);
		addEEnumLiteral(stateEEnum, State.COMPLETE);

		// Initialize data types
		initEDataType(adapterEDataType, Adapter.class, "Adapter", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iStatusEDataType, IStatus.class, "IStatus", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iProgressMonitorEDataType, IProgressMonitor.class, "IProgressMonitor", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(versionEDataType, Version.class, "Version", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // ClasSupplierPackageImpl
