/**
 */
package org.classupplier.impl;

import java.util.Map;

import org.classupplier.Artifact;
import org.classupplier.ClassSupplierFactory;
import org.classupplier.ClassSupplierPackage;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.Workspace;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
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
public class ClassSupplierPackageImpl extends EPackageImpl implements
		ClassSupplierPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workspaceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dateToStateMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iSchedulingRuleEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum phaseEEnum = null;

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
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.classupplier.ClassSupplierPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ClassSupplierPackageImpl() {
		super(eNS_URI, ClassSupplierFactory.eINSTANCE);
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
	 * This method is used to initialize {@link ClassSupplierPackage#eINSTANCE}
	 * when that field is accessed. Clients should not invoke it directly.
	 * Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ClassSupplierPackage init() {
		if (isInited)
			return (ClassSupplierPackage) EPackage.Registry.INSTANCE
					.getEPackage(ClassSupplierPackage.eNS_URI);

		// Obtain or create and register package
		ClassSupplierPackageImpl theClassSupplierPackage = (ClassSupplierPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof ClassSupplierPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new ClassSupplierPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theClassSupplierPackage.createPackageContents();

		// Initialize created meta-data
		theClassSupplierPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theClassSupplierPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ClassSupplierPackage.eNS_URI,
				theClassSupplierPackage);
		return theClassSupplierPackage;
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
	public EReference getArtifact_Snapshots() {
		return (EReference) artifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArtifact_State() {
		return (EReference) artifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_Name() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_Time() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_Version() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_Stage() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_ProjectName() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_EPackage() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_DynamicEPackage() {
		return (EReference) stateEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_RuntimeEPackage() {
		return (EReference) stateEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkspace() {
		return workspaceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkspace_Artifacts() {
		return (EReference) workspaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkspace_ResourceSet() {
		return (EAttribute) workspaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDateToStateMapEntry() {
		return dateToStateMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDateToStateMapEntry_Key() {
		return (EAttribute) dateToStateMapEntryEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDateToStateMapEntry_Value() {
		return (EReference) dateToStateMapEntryEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getISchedulingRule() {
		return iSchedulingRuleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPhase() {
		return phaseEEnum;
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClassSupplierFactory getClassSupplierFactory() {
		return (ClassSupplierFactory) getEFactoryInstance();
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
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		artifactEClass = createEClass(ARTIFACT);
		createEReference(artifactEClass, ARTIFACT__SNAPSHOTS);
		createEReference(artifactEClass, ARTIFACT__STATE);

		stateEClass = createEClass(STATE);
		createEAttribute(stateEClass, STATE__NAME);
		createEAttribute(stateEClass, STATE__TIME);
		createEAttribute(stateEClass, STATE__VERSION);
		createEAttribute(stateEClass, STATE__STAGE);
		createEAttribute(stateEClass, STATE__PROJECT_NAME);
		createEAttribute(stateEClass, STATE__EPACKAGE);
		createEReference(stateEClass, STATE__DYNAMIC_EPACKAGE);
		createEReference(stateEClass, STATE__RUNTIME_EPACKAGE);

		workspaceEClass = createEClass(WORKSPACE);
		createEReference(workspaceEClass, WORKSPACE__ARTIFACTS);
		createEAttribute(workspaceEClass, WORKSPACE__RESOURCE_SET);

		dateToStateMapEntryEClass = createEClass(DATE_TO_STATE_MAP_ENTRY);
		createEAttribute(dateToStateMapEntryEClass,
				DATE_TO_STATE_MAP_ENTRY__KEY);
		createEReference(dateToStateMapEntryEClass,
				DATE_TO_STATE_MAP_ENTRY__VALUE);

		iSchedulingRuleEClass = createEClass(ISCHEDULING_RULE);

		// Create enums
		phaseEEnum = createEEnum(PHASE);

		// Create data types
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
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE
				.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		artifactEClass.getESuperTypes().add(this.getState());
		workspaceEClass.getESuperTypes().add(this.getISchedulingRule());

		// Initialize classes and features; add operations and parameters
		initEClass(artifactEClass, Artifact.class, "Artifact", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArtifact_Snapshots(), this.getDateToStateMapEntry(),
				null, "snapshots", null, 0, -1, Artifact.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_State(), this.getState(), null, "state",
				null, 0, 1, Artifact.class, IS_TRANSIENT, IS_VOLATILE,
				!IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(artifactEClass, null, "apply", 0, 1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDate(), "version", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		addEOperation(artifactEClass, this.getState(), "newState", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		addEOperation(artifactEClass, theEcorePackage.getEString(), "getName",
				0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(artifactEClass, null, "setName", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, theEcorePackage.getEString(), "newName", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		addEOperation(artifactEClass, this.getVersion(), "getVersion", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		op = addEOperation(artifactEClass, null, "setVersion", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, this.getVersion(), "version", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		addEOperation(artifactEClass, this.getPhase(), "getStage", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		op = addEOperation(artifactEClass, null, "setStage", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, this.getPhase(), "newStage", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		addEOperation(artifactEClass, ecorePackage.getEString(),
				"getProjectName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(artifactEClass, null, "setProjectName", 0, 1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "newProjectName", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		addEOperation(artifactEClass, this.getState(), "getOrCreateState", 0,
				1, IS_UNIQUE, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getState_Name(), ecorePackage.getEString(), "name",
				null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getState_Time(), ecorePackage.getEDate(), "time", null,
				0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_Version(), this.getVersion(), "version", null,
				0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_Stage(), this.getPhase(), "stage", "", 0, 1,
				State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_ProjectName(), ecorePackage.getEString(),
				"projectName", "", 0, 1, State.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_EPackage(),
				theEcorePackage.getEFeatureMapEntry(), "ePackage", null, 0, 1,
				State.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_DynamicEPackage(),
				theEcorePackage.getEPackage(), null, "dynamicEPackage", null,
				0, 1, State.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getState_RuntimeEPackage(),
				theEcorePackage.getEPackage(), null, "runtimeEPackage", null,
				0, 1, State.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		op = addEOperation(stateEClass, null, "produce", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		addEOperation(stateEClass, theEcorePackage.getEPackage(),
				"getAppropriateEPackage", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(workspaceEClass, Workspace.class, "Workspace", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkspace_Artifacts(), this.getArtifact(), null,
				"artifacts", null, 0, -1, Workspace.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkspace_ResourceSet(),
				ecorePackage.getEResourceSet(), "resourceSet", null, 0, 1,
				Workspace.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(workspaceEClass, null, "init", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, this.getWorkspace(), "oldWorkspace", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getArtifact(),
				"createArtifact", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "blueprint", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getArtifact(), "getArtifact",
				0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEObject(), "eObject", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getArtifact(), "getArtifact",
				0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "ePackage", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getArtifact(), "getArtifact",
				0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, null, "registerArtifact", 0, 1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getArtifact(), "artifact", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		op = addEOperation(workspaceEClass, null, "unregisterArtifact", 0, 1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getArtifact(), "artifact", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getPhase(), "contains", 0, 1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "blueprint", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, null, "save", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		initEClass(dateToStateMapEntryEClass, Map.Entry.class,
				"DateToStateMapEntry", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDateToStateMapEntry_Key(), ecorePackage.getEDate(),
				"key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getDateToStateMapEntry_Value(), this.getState(), null,
				"value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iSchedulingRuleEClass, ISchedulingRule.class,
				"ISchedulingRule", IS_ABSTRACT, IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(phaseEEnum, Phase.class, "Phase");
		addEEnumLiteral(phaseEEnum, Phase.PROCESSING);
		addEEnumLiteral(phaseEEnum, Phase.NEW);
		addEEnumLiteral(phaseEEnum, Phase.MODELED);
		addEEnumLiteral(phaseEEnum, Phase.GENERATED);
		addEEnumLiteral(phaseEEnum, Phase.EXPORTED);
		addEEnumLiteral(phaseEEnum, Phase.LOADED);

		// Initialize data types
		initEDataType(iProgressMonitorEDataType, IProgressMonitor.class,
				"IProgressMonitor", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(versionEDataType, Version.class, "Version",
				IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for
	 * <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(getState_EPackage(), source, new String[] { "kind",
				"group" });
		addAnnotation(getState_DynamicEPackage(), source, new String[] {
				"group", "#ePackage" });
		addAnnotation(getState_RuntimeEPackage(), source, new String[] {
				"group", "#ePackage" });
	}

} // ClassSupplierPackageImpl
