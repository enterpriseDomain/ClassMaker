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
 * @see org.classupplier.ClassSupplierFactory
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
	String eNAME = "classupplier";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org/classupplier/0.7.6";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "classupplier";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClassSupplierPackage eINSTANCE = org.classupplier.impl.ClassSupplierPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.classupplier.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.impl.StateImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getState()
	 * @generated
	 */
	int STATE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__TIME = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__STAGE = 3;

	/**
	 * The feature id for the '<em><b>Prototype EPackage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__PROTOTYPE_EPACKAGE = 4;

	/**
	 * The feature id for the '<em><b>Loaded EPackage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__LOADED_EPACKAGE = 5;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__PROJECT_NAME = 6;

	/**
	 * The feature id for the '<em><b>EPackage</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__EPACKAGE = 7;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.impl.ArtifactImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = STATE__NAME;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__TIME = STATE__TIME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__VERSION = STATE__VERSION;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__STAGE = STATE__STAGE;

	/**
	 * The feature id for the '<em><b>Prototype EPackage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PROTOTYPE_EPACKAGE = STATE__PROTOTYPE_EPACKAGE;

	/**
	 * The feature id for the '<em><b>Loaded EPackage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__LOADED_EPACKAGE = STATE__LOADED_EPACKAGE;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PROJECT_NAME = STATE__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>EPackage</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EPACKAGE = STATE__EPACKAGE;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__RESOURCE = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Snapshots</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__SNAPSHOTS = STATE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__STATE = STATE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = STATE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.InfrastructureImpl <em>Infrastructure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.impl.InfrastructureImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getInfrastructure()
	 * @generated
	 */
	int INFRASTRUCTURE = 2;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE__ARTIFACTS = 0;

	/**
	 * The feature id for the '<em><b>Resource Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE__RESOURCE_SET = 1;

	/**
	 * The number of structural features of the '<em>Infrastructure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFRASTRUCTURE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.core.runtime.IAdaptable <em>IAdaptable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IAdaptable
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIAdaptable()
	 * @generated
	 */
	int IADAPTABLE = 4;

	/**
	 * The number of structural features of the '<em>IAdaptable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IADAPTABLE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.ClassSupplierImpl <em>Class Supplier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.impl.ClassSupplierImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getClassSupplier()
	 * @generated
	 */
	int CLASS_SUPPLIER = 3;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_SUPPLIER__WORKSPACE = IADAPTABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Class Supplier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_SUPPLIER_FEATURE_COUNT = IADAPTABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.DateToStateMapEntryImpl <em>Date To State Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.impl.DateToStateMapEntryImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getDateToStateMapEntry()
	 * @generated
	 */
	int DATE_TO_STATE_MAP_ENTRY = 5;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TO_STATE_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TO_STATE_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Date To State Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TO_STATE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.classupplier.Phase <em>Phase</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.Phase
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getPhase()
	 * @generated
	 */
	int PHASE = 6;

	/**
	 * The meta object id for the '<em>Adapter</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getAdapter()
	 * @generated
	 */
	int ADAPTER = 7;

	/**
	 * The meta object id for the '<em>IStatus</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IStatus
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIStatus()
	 * @generated
	 */
	int ISTATUS = 8;

	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 9;

	/**
	 * The meta object id for the '<em>Version</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.framework.Version
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getVersion()
	 * @generated
	 */
	int VERSION = 10;


	/**
	 * Returns the meta object for class '{@link org.classupplier.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see org.classupplier.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.Artifact#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource</em>'.
	 * @see org.classupplier.Artifact#getResource()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Resource();

	/**
	 * Returns the meta object for the map '{@link org.classupplier.Artifact#getSnapshots <em>Snapshots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Snapshots</em>'.
	 * @see org.classupplier.Artifact#getSnapshots()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Snapshots();

	/**
	 * Returns the meta object for the reference '{@link org.classupplier.Artifact#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see org.classupplier.Artifact#getState()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_State();

	/**
	 * Returns the meta object for class '{@link org.classupplier.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see org.classupplier.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.State#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.classupplier.State#getName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.State#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see org.classupplier.State#getTime()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Time();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.State#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.classupplier.State#getVersion()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.State#getStage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stage</em>'.
	 * @see org.classupplier.State#getStage()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Stage();

	/**
	 * Returns the meta object for the containment reference '{@link org.classupplier.State#getPrototypeEPackage <em>Prototype EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Prototype EPackage</em>'.
	 * @see org.classupplier.State#getPrototypeEPackage()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_PrototypeEPackage();

	/**
	 * Returns the meta object for the containment reference '{@link org.classupplier.State#getLoadedEPackage <em>Loaded EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Loaded EPackage</em>'.
	 * @see org.classupplier.State#getLoadedEPackage()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_LoadedEPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.State#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.classupplier.State#getProjectName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_ProjectName();

	/**
	 * Returns the meta object for the attribute list '{@link org.classupplier.State#getEPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>EPackage</em>'.
	 * @see org.classupplier.State#getEPackage()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_EPackage();

	/**
	 * Returns the meta object for class '{@link org.classupplier.Infrastructure <em>Infrastructure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Set</em>'.
	 * @see org.classupplier.Infrastructure#getResourceSet()
	 * @see #getInfrastructure()
	 * @generated
	 */
	EAttribute getInfrastructure_ResourceSet();

	/**
	 * Returns the meta object for class '{@link org.classupplier.ClassSupplier <em>Class Supplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Supplier</em>'.
	 * @see org.classupplier.ClassSupplier
	 * @generated
	 */
	EClass getClassSupplier();

	/**
	 * Returns the meta object for the reference '{@link org.classupplier.ClassSupplier#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Workspace</em>'.
	 * @see org.classupplier.ClassSupplier#getWorkspace()
	 * @see #getClassSupplier()
	 * @generated
	 */
	EReference getClassSupplier_Workspace();

	/**
	 * Returns the meta object for class '{@link org.eclipse.core.runtime.IAdaptable <em>IAdaptable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IAdaptable</em>'.
	 * @see org.eclipse.core.runtime.IAdaptable
	 * @model instanceClass="org.eclipse.core.runtime.IAdaptable"
	 * @generated
	 */
	EClass getIAdaptable();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Date To State Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date To State Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EDate"
	 *        valueType="org.classupplier.State" valueContainment="true"
	 * @generated
	 */
	EClass getDateToStateMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getDateToStateMapEntry()
	 * @generated
	 */
	EAttribute getDateToStateMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getDateToStateMapEntry()
	 * @generated
	 */
	EReference getDateToStateMapEntry_Value();

	/**
	 * Returns the meta object for enum '{@link org.classupplier.Phase <em>Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Phase</em>'.
	 * @see org.classupplier.Phase
	 * @generated
	 */
	EEnum getPhase();

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
	 * Returns the meta object for data type '{@link org.osgi.framework.Version <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		 * The meta object literal for the '{@link org.classupplier.impl.ArtifactImpl <em>Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.ArtifactImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__RESOURCE = eINSTANCE.getArtifact_Resource();

		/**
		 * The meta object literal for the '<em><b>Snapshots</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__SNAPSHOTS = eINSTANCE.getArtifact_Snapshots();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__STATE = eINSTANCE.getArtifact_State();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.StateImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__NAME = eINSTANCE.getState_Name();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__TIME = eINSTANCE.getState_Time();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__VERSION = eINSTANCE.getState_Version();

		/**
		 * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__STAGE = eINSTANCE.getState_Stage();

		/**
		 * The meta object literal for the '<em><b>Prototype EPackage</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__PROTOTYPE_EPACKAGE = eINSTANCE.getState_PrototypeEPackage();

		/**
		 * The meta object literal for the '<em><b>Loaded EPackage</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__LOADED_EPACKAGE = eINSTANCE.getState_LoadedEPackage();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__PROJECT_NAME = eINSTANCE.getState_ProjectName();

		/**
		 * The meta object literal for the '<em><b>EPackage</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__EPACKAGE = eINSTANCE.getState_EPackage();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.InfrastructureImpl <em>Infrastructure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.InfrastructureImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getInfrastructure()
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
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFRASTRUCTURE__RESOURCE_SET = eINSTANCE.getInfrastructure_ResourceSet();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.ClassSupplierImpl <em>Class Supplier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.ClassSupplierImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getClassSupplier()
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
		 * The meta object literal for the '{@link org.eclipse.core.runtime.IAdaptable <em>IAdaptable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IAdaptable
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIAdaptable()
		 * @generated
		 */
		EClass IADAPTABLE = eINSTANCE.getIAdaptable();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.DateToStateMapEntryImpl <em>Date To State Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.DateToStateMapEntryImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getDateToStateMapEntry()
		 * @generated
		 */
		EClass DATE_TO_STATE_MAP_ENTRY = eINSTANCE.getDateToStateMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_TO_STATE_MAP_ENTRY__KEY = eINSTANCE.getDateToStateMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATE_TO_STATE_MAP_ENTRY__VALUE = eINSTANCE.getDateToStateMapEntry_Value();

		/**
		 * The meta object literal for the '{@link org.classupplier.Phase <em>Phase</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.Phase
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getPhase()
		 * @generated
		 */
		EEnum PHASE = eINSTANCE.getPhase();

		/**
		 * The meta object literal for the '<em>Adapter</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.notify.Adapter
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getAdapter()
		 * @generated
		 */
		EDataType ADAPTER = eINSTANCE.getAdapter();

		/**
		 * The meta object literal for the '<em>IStatus</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IStatus
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIStatus()
		 * @generated
		 */
		EDataType ISTATUS = eINSTANCE.getIStatus();

		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();

		/**
		 * The meta object literal for the '<em>Version</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.framework.Version
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getVersion()
		 * @generated
		 */
		EDataType VERSION = eINSTANCE.getVersion();

	}

} //ClassSupplierPackage
