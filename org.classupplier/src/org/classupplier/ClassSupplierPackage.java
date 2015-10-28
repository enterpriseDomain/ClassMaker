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
 * @see org.classupplier.ClassSupplierFactory
 * @model kind="package"
 * @generated
 */
public interface ClassSupplierPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "classupplier"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org/classupplier/0.7.8"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "classupplier"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	ClassSupplierPackage eINSTANCE = org.classupplier.impl.ClassSupplierPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.classupplier.impl.Constructable
	 * <em>Constructable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.classupplier.impl.Constructable
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getConstructable()
	 * @generated
	 */
	int CONSTRUCTABLE = 6;

	/**
	 * The number of structural features of the '<em>Constructable</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTABLE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.classupplier.impl.StateImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getState()
	 * @generated
	 */
	int STATE = 1;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.ContributionImpl
	 * <em>Contribution</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.classupplier.impl.ContributionImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getContribution()
	 * @generated
	 */
	int CONTRIBUTION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__NAME = CONSTRUCTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__LANGUAGE = CONSTRUCTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__VERSION = CONSTRUCTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__STAGE = CONSTRUCTABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__PROJECT_NAME = CONSTRUCTABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>State History</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__STATE_HISTORY = CONSTRUCTABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__STATE = CONSTRUCTABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Dynamic EPackages</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__DYNAMIC_EPACKAGES = CONSTRUCTABLE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Generated EPackages</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__GENERATED_EPACKAGES = CONSTRUCTABLE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__WORKSPACE = CONSTRUCTABLE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__CUSTOMIZERS = CONSTRUCTABLE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Contribution</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_FEATURE_COUNT = CONSTRUCTABLE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = CONSTRUCTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__LANGUAGE = CONSTRUCTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__TIMESTAMP = CONSTRUCTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__NUMBER = CONSTRUCTABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__VERSION = CONSTRUCTABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__STAGE = CONSTRUCTABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__PROJECT_NAME = CONSTRUCTABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Deployable Unit Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DEPLOYABLE_UNIT_NAME = CONSTRUCTABLE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>EPackages</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__EPACKAGES = CONSTRUCTABLE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Dynamic EPackages</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DYNAMIC_EPACKAGES = CONSTRUCTABLE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Generated EPackages</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__GENERATED_EPACKAGES = CONSTRUCTABLE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Contribution</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__CONTRIBUTION = CONSTRUCTABLE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__CUSTOMIZERS = CONSTRUCTABLE_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>State</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = CONSTRUCTABLE_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling Rule</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getISchedulingRule()
	 * @generated
	 */
	int ISCHEDULING_RULE = 4;

	/**
	 * The number of structural features of the '<em>IScheduling Rule</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISCHEDULING_RULE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.WorkspaceImpl <em>Workspace</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.classupplier.impl.WorkspaceImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getWorkspace()
	 * @generated
	 */
	int WORKSPACE = 2;

	/**
	 * The feature id for the '<em><b>Contributions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__CONTRIBUTIONS = ISCHEDULING_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resource Set</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__RESOURCE_SET = ISCHEDULING_RULE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Workspace</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_FEATURE_COUNT = ISCHEDULING_RULE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.VersionToStateMapEntryImpl <em>Version To State Map Entry</em>}' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see org.classupplier.impl.VersionToStateMapEntryImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getVersionToStateMapEntry()
	 * @generated
	 */
	int VERSION_TO_STATE_MAP_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_TO_STATE_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_TO_STATE_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Version To State Map Entry</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_TO_STATE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.concurrent.future.IFuture <em>IFuture</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.equinox.concurrent.future.IFuture
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIFuture()
	 * @generated
	 */
	int IFUTURE = 5;

	/**
	 * The number of structural features of the '<em>IFuture</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IFUTURE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.classupplier.Phase <em>Phase</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.classupplier.Phase
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getPhase()
	 * @generated
	 */
	int PHASE = 11;

	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 12;

	/**
	 * The meta object id for the '<em>Version</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.osgi.framework.Version
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getVersion()
	 * @generated
	 */
	int VERSION = 13;

	/**
	 * The meta object id for the '<em>Semaphore</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.util.concurrent.Semaphore
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getSemaphore()
	 * @generated
	 */
	int SEMAPHORE = 14;

	/**
	 * The meta object id for the '<em>Core Exception</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.CoreException
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getCoreException()
	 * @generated
	 */
	int CORE_EXCEPTION = 15;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.common.util.EList <em>EList</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.EList
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getEList()
	 * @generated
	 */
	int ELIST = 7;

	/**
	 * The number of structural features of the '<em>EList</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELIST_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.CustomizerImpl <em>Customizer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.impl.CustomizerImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getCustomizer()
	 * @generated
	 */
	int CUSTOMIZER = 8;

	/**
	 * The number of structural features of the '<em>Customizer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.PhaseQualifierImpl <em>Phase Qualifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.impl.PhaseQualifierImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getPhaseQualifier()
	 * @generated
	 */
	int PHASE_QUALIFIER = 9;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_QUALIFIER__STAGE = 0;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_QUALIFIER__FILTER = 1;

	/**
	 * The number of structural features of the '<em>Phase Qualifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_QUALIFIER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.classupplier.impl.PhaseQualifierToCustomizerMapEntryImpl <em>Phase Qualifier To Customizer Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.classupplier.impl.PhaseQualifierToCustomizerMapEntryImpl
	 * @see org.classupplier.impl.ClassSupplierPackageImpl#getPhaseQualifierToCustomizerMapEntry()
	 * @generated
	 */
	int PHASE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY = 10;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Phase Qualifier To Customizer Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * Returns the meta object for class '{@link org.classupplier.Contribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contribution</em>'.
	 * @see org.classupplier.Contribution
	 * @generated
	 */
	EClass getContribution();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.Contribution#getName <em>Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.classupplier.Contribution#getName()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.Contribution#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.classupplier.Contribution#getLanguage()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_Language();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.Contribution#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.classupplier.Contribution#getVersion()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_Version();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.Contribution#getStage <em>Stage</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Stage</em>'.
	 * @see org.classupplier.Contribution#getStage()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_Stage();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.Contribution#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.classupplier.Contribution#getProjectName()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_ProjectName();

	/**
	 * Returns the meta object for the map '{@link org.classupplier.Contribution#getStateHistory <em>State History</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the map '<em>State History</em>'.
	 * @see org.classupplier.Contribution#getStateHistory()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_StateHistory();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.classupplier.Contribution#getState <em>State</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see org.classupplier.Contribution#getState()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_State();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.classupplier.Contribution#getDynamicEPackages
	 * <em>Dynamic EPackages</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Dynamic EPackages</em>'.
	 * @see org.classupplier.Contribution#getDynamicEPackages()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_DynamicEPackages();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.classupplier.Contribution#getGeneratedEPackages
	 * <em>Generated EPackages</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Generated EPackages</em>'.
	 * @see org.classupplier.Contribution#getGeneratedEPackages()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_GeneratedEPackages();

	/**
	 * Returns the meta object for the container reference '{@link org.classupplier.Contribution#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Workspace</em>'.
	 * @see org.classupplier.Contribution#getWorkspace()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Workspace();

	/**
	 * Returns the meta object for the map '{@link org.classupplier.Contribution#getCustomizers <em>Customizers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Customizers</em>'.
	 * @see org.classupplier.Contribution#getCustomizers()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Customizers();

	/**
	 * Returns the meta object for class '{@link org.classupplier.State <em>State</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see org.classupplier.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.State#getName <em>Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.classupplier.State#getName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.State#getLanguage <em>Language</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.classupplier.State#getLanguage()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Language();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.State#getTimestamp <em>Timestamp</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.classupplier.State#getTimestamp()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Timestamp();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.State#getNumber <em>Number</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see org.classupplier.State#getNumber()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Number();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.State#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.classupplier.State#getVersion()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Version();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.classupplier.State#getStage <em>Stage</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Stage</em>'.
	 * @see org.classupplier.State#getStage()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Stage();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.State#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.classupplier.State#getProjectName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_ProjectName();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.State#getDeployableUnitName <em>Deployable Unit Name</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the attribute '<em>Deployable Unit Name</em>'.
	 * @see org.classupplier.State#getDeployableUnitName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_DeployableUnitName();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.classupplier.State#getEPackages <em>EPackages</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>EPackages</em>'.
	 * @see org.classupplier.State#getEPackages()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_EPackages();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.classupplier.State#getDynamicEPackages
	 * <em>Dynamic EPackages</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Dynamic EPackages</em>'.
	 * @see org.classupplier.State#getDynamicEPackages()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_DynamicEPackages();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.classupplier.State#getGeneratedEPackages
	 * <em>Generated EPackages</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Generated EPackages</em>'.
	 * @see org.classupplier.State#getGeneratedEPackages()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_GeneratedEPackages();

	/**
	 * Returns the meta object for the container reference '{@link org.classupplier.State#getContribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Contribution</em>'.
	 * @see org.classupplier.State#getContribution()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Contribution();

	/**
	 * Returns the meta object for the map '{@link org.classupplier.State#getCustomizers <em>Customizers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Customizers</em>'.
	 * @see org.classupplier.State#getCustomizers()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Customizers();

	/**
	 * Returns the meta object for class '{@link org.classupplier.Workspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workspace</em>'.
	 * @see org.classupplier.Workspace
	 * @generated
	 */
	EClass getWorkspace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.classupplier.Workspace#getContributions <em>Contributions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contributions</em>'.
	 * @see org.classupplier.Workspace#getContributions()
	 * @see #getWorkspace()
	 * @generated
	 */
	EReference getWorkspace_Contributions();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.Workspace#getResourceSet <em>Resource Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Set</em>'.
	 * @see org.classupplier.Workspace#getResourceSet()
	 * @see #getWorkspace()
	 * @generated
	 */
	EAttribute getWorkspace_ResourceSet();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Version To State Map Entry</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for class '<em>Version To State Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.classupplier.Version"
	 *        valueType="org.classupplier.State" valueContainment="true"
	 * @generated
	 */
	EClass getVersionToStateMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVersionToStateMapEntry()
	 * @generated
	 */
	EAttribute getVersionToStateMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVersionToStateMapEntry()
	 * @generated
	 */
	EReference getVersionToStateMapEntry_Value();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.core.runtime.jobs.ISchedulingRule
	 * <em>IScheduling Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>IScheduling Rule</em>'.
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
	 * @model instanceClass="org.eclipse.core.runtime.jobs.ISchedulingRule"
	 * @generated
	 */
	EClass getISchedulingRule();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.concurrent.future.IFuture <em>IFuture</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>IFuture</em>'.
	 * @see org.eclipse.equinox.concurrent.future.IFuture
	 * @model instanceClass="org.eclipse.equinox.concurrent.future.IFuture" typeParameters="ResultType"
	 * @generated
	 */
	EClass getIFuture();

	/**
	 * Returns the meta object for class '
	 * {@link org.classupplier.impl.Constructable <em>Constructable</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Constructable</em>'.
	 * @see org.classupplier.impl.Constructable
	 * @model instanceClass="org.classupplier.impl.Constructable"
	 * @generated
	 */
	EClass getConstructable();

	/**
	 * Returns the meta object for enum '{@link org.classupplier.Phase <em>Phase</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Phase</em>'.
	 * @see org.classupplier.Phase
	 * @generated
	 */
	EEnum getPhase();

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
	 * Returns the meta object for data type '
	 * {@link java.util.concurrent.Semaphore <em>Semaphore</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Semaphore</em>'.
	 * @see java.util.concurrent.Semaphore
	 * @model instanceClass="java.util.concurrent.Semaphore"
	 * @generated
	 */
	EDataType getSemaphore();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.CoreException <em>Core Exception</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Core Exception</em>'.
	 * @see org.eclipse.core.runtime.CoreException
	 * @model instanceClass="org.eclipse.core.runtime.CoreException"
	 * @generated
	 */
	EDataType getCoreException();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.emf.common.util.EList <em>EList</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EList</em>'.
	 * @see org.eclipse.emf.common.util.EList
	 * @model instanceClass="org.eclipse.emf.common.util.EList"
	 *        typeParameters="T"
	 * @generated
	 */
	EClass getEList();

	/**
	 * Returns the meta object for class '{@link org.classupplier.Customizer <em>Customizer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customizer</em>'.
	 * @see org.classupplier.Customizer
	 * @generated
	 */
	EClass getCustomizer();

	/**
	 * Returns the meta object for class '{@link org.classupplier.PhaseQualifier <em>Phase Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phase Qualifier</em>'.
	 * @see org.classupplier.PhaseQualifier
	 * @generated
	 */
	EClass getPhaseQualifier();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.PhaseQualifier#getStage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stage</em>'.
	 * @see org.classupplier.PhaseQualifier#getStage()
	 * @see #getPhaseQualifier()
	 * @generated
	 */
	EAttribute getPhaseQualifier_Stage();

	/**
	 * Returns the meta object for the attribute '{@link org.classupplier.PhaseQualifier#getFilter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.classupplier.PhaseQualifier#getFilter()
	 * @see #getPhaseQualifier()
	 * @generated
	 */
	EAttribute getPhaseQualifier_Filter();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Phase Qualifier To Customizer Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phase Qualifier To Customizer Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="org.classupplier.PhaseQualifier"
	 *        valueType="org.classupplier.Customizer" valueContainment="true"
	 * @generated
	 */
	EClass getPhaseQualifierToCustomizerMapEntry();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPhaseQualifierToCustomizerMapEntry()
	 * @generated
	 */
	EReference getPhaseQualifierToCustomizerMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPhaseQualifierToCustomizerMapEntry()
	 * @generated
	 */
	EReference getPhaseQualifierToCustomizerMapEntry_Value();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ClassSupplierFactory getClassSupplierFactory();

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
		 * The meta object literal for the '{@link org.classupplier.impl.ContributionImpl <em>Contribution</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.classupplier.impl.ContributionImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getContribution()
		 * @generated
		 */
		EClass CONTRIBUTION = eINSTANCE.getContribution();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__NAME = eINSTANCE.getContribution_Name();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__LANGUAGE = eINSTANCE.getContribution_Language();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__VERSION = eINSTANCE.getContribution_Version();

		/**
		 * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__STAGE = eINSTANCE.getContribution_Stage();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__PROJECT_NAME = eINSTANCE.getContribution_ProjectName();

		/**
		 * The meta object literal for the '<em><b>State History</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__STATE_HISTORY = eINSTANCE.getContribution_StateHistory();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__STATE = eINSTANCE.getContribution_State();

		/**
		 * The meta object literal for the '<em><b>Dynamic EPackages</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__DYNAMIC_EPACKAGES = eINSTANCE.getContribution_DynamicEPackages();

		/**
		 * The meta object literal for the '<em><b>Generated EPackages</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__GENERATED_EPACKAGES = eINSTANCE.getContribution_GeneratedEPackages();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__WORKSPACE = eINSTANCE.getContribution_Workspace();

		/**
		 * The meta object literal for the '<em><b>Customizers</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__CUSTOMIZERS = eINSTANCE.getContribution_Customizers();

		/**
		 * The meta object literal for the '
		 * {@link org.classupplier.impl.StateImpl <em>State</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.classupplier.impl.StateImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__NAME = eINSTANCE.getState_Name();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__LANGUAGE = eINSTANCE.getState_Language();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__TIMESTAMP = eINSTANCE.getState_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__NUMBER = eINSTANCE.getState_Number();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__VERSION = eINSTANCE.getState_Version();

		/**
		 * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__STAGE = eINSTANCE.getState_Stage();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__PROJECT_NAME = eINSTANCE.getState_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Deployable Unit Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__DEPLOYABLE_UNIT_NAME = eINSTANCE.getState_DeployableUnitName();

		/**
		 * The meta object literal for the '<em><b>EPackages</b></em>' attribute list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__EPACKAGES = eINSTANCE.getState_EPackages();

		/**
		 * The meta object literal for the '<em><b>Dynamic EPackages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference STATE__DYNAMIC_EPACKAGES = eINSTANCE.getState_DynamicEPackages();

		/**
		 * The meta object literal for the '<em><b>Generated EPackages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference STATE__GENERATED_EPACKAGES = eINSTANCE.getState_GeneratedEPackages();

		/**
		 * The meta object literal for the '<em><b>Contribution</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference STATE__CONTRIBUTION = eINSTANCE.getState_Contribution();

		/**
		 * The meta object literal for the '<em><b>Customizers</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__CUSTOMIZERS = eINSTANCE.getState_Customizers();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.WorkspaceImpl <em>Workspace</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.classupplier.impl.WorkspaceImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getWorkspace()
		 * @generated
		 */
		EClass WORKSPACE = eINSTANCE.getWorkspace();

		/**
		 * The meta object literal for the '<em><b>Contributions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE__CONTRIBUTIONS = eINSTANCE.getWorkspace_Contributions();

		/**
		 * The meta object literal for the '<em><b>Resource Set</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE__RESOURCE_SET = eINSTANCE.getWorkspace_ResourceSet();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.VersionToStateMapEntryImpl <em>Version To State Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.VersionToStateMapEntryImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getVersionToStateMapEntry()
		 * @generated
		 */
		EClass VERSION_TO_STATE_MAP_ENTRY = eINSTANCE.getVersionToStateMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERSION_TO_STATE_MAP_ENTRY__KEY = eINSTANCE.getVersionToStateMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERSION_TO_STATE_MAP_ENTRY__VALUE = eINSTANCE.getVersionToStateMapEntry_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling Rule</em>}' class.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getISchedulingRule()
		 * @generated
		 */
		EClass ISCHEDULING_RULE = eINSTANCE.getISchedulingRule();

		/**
		 * The meta object literal for the '{@link org.eclipse.equinox.concurrent.future.IFuture <em>IFuture</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.equinox.concurrent.future.IFuture
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIFuture()
		 * @generated
		 */
		EClass IFUTURE = eINSTANCE.getIFuture();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.Constructable <em>Constructable</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.classupplier.impl.Constructable
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getConstructable()
		 * @generated
		 */
		EClass CONSTRUCTABLE = eINSTANCE.getConstructable();

		/**
		 * The meta object literal for the '{@link org.classupplier.Phase <em>Phase</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.classupplier.Phase
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getPhase()
		 * @generated
		 */
		EEnum PHASE = eINSTANCE.getPhase();

		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();

		/**
		 * The meta object literal for the '<em>Version</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.osgi.framework.Version
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getVersion()
		 * @generated
		 */
		EDataType VERSION = eINSTANCE.getVersion();

		/**
		 * The meta object literal for the '<em>Semaphore</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.util.concurrent.Semaphore
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getSemaphore()
		 * @generated
		 */
		EDataType SEMAPHORE = eINSTANCE.getSemaphore();

		/**
		 * The meta object literal for the '<em>Core Exception</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.CoreException
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getCoreException()
		 * @generated
		 */
		EDataType CORE_EXCEPTION = eINSTANCE.getCoreException();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.emf.common.util.EList <em>EList</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emf.common.util.EList
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getEList()
		 * @generated
		 */
		EClass ELIST = eINSTANCE.getEList();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.CustomizerImpl <em>Customizer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.CustomizerImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getCustomizer()
		 * @generated
		 */
		EClass CUSTOMIZER = eINSTANCE.getCustomizer();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.PhaseQualifierImpl <em>Phase Qualifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.PhaseQualifierImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getPhaseQualifier()
		 * @generated
		 */
		EClass PHASE_QUALIFIER = eINSTANCE.getPhaseQualifier();

		/**
		 * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHASE_QUALIFIER__STAGE = eINSTANCE.getPhaseQualifier_Stage();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHASE_QUALIFIER__FILTER = eINSTANCE.getPhaseQualifier_Filter();

		/**
		 * The meta object literal for the '{@link org.classupplier.impl.PhaseQualifierToCustomizerMapEntryImpl <em>Phase Qualifier To Customizer Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.classupplier.impl.PhaseQualifierToCustomizerMapEntryImpl
		 * @see org.classupplier.impl.ClassSupplierPackageImpl#getPhaseQualifierToCustomizerMapEntry()
		 * @generated
		 */
		EClass PHASE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY = eINSTANCE.getPhaseQualifierToCustomizerMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHASE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__KEY = eINSTANCE.getPhaseQualifierToCustomizerMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PHASE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__VALUE = eINSTANCE
				.getPhaseQualifierToCustomizerMapEntry_Value();

	}

} // ClassSupplierPackage
