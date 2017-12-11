/**
 * Copyright 2012-2016 Kyrill Zotkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.impl;

import static org.enterprisedomain.classmaker.ClassMakerPackage.ADAPTER;
import static org.enterprisedomain.classmaker.ClassMakerPackage.RESOURCE;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.enterprisedomain.classmaker.Blueprint;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.CompletionNotificationAdapter;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.ResourceChangeListener;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.SCMRegistry;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ClassMakerPackageImpl extends EPackageImpl implements ClassMakerPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass contributionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass revisionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass itemEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass adapterEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass workspaceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass integerToStateMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass versionToRevisionMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass iSchedulingRuleEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass executorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass futureEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass iAdapterFactoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass customizerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass stageQualifierEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass stageQualifierToCustomizerMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass projectEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass modelPairEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass scmOperatorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass scmRegistryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass resourceChangeListenerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass notificationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass completionListenerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass workerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass resourceAdapterEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass classMakerServiceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass blueprintEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass completionNotificationAdapterEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum stageEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType propertiesEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType iProgressMonitorEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType osGiVersionEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType semaphoreEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType coreExceptionEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType invocationTargetExceptionEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType iStatusEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType uriEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType exceptionEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType localeEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method
	 * {@link #init init()}, which also performs initialization of the package, or
	 * returns the registered package, if one already exists. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ClassMakerPackageImpl() {
		super(eNS_URI, ClassMakerFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and
	 * for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link ClassMakerPackage#eINSTANCE} when
	 * that field is accessed. Clients should not invoke it directly. Instead, they
	 * should simply access that field to obtain the package. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ClassMakerPackage init() {
		if (isInited)
			return (ClassMakerPackage) EPackage.Registry.INSTANCE.getEPackage(ClassMakerPackage.eNS_URI);

		// Obtain or create and register package
		ClassMakerPackageImpl theClassMakerPackage = (ClassMakerPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof ClassMakerPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new ClassMakerPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theClassMakerPackage.createPackageContents();

		// Initialize created meta-data
		theClassMakerPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theClassMakerPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ClassMakerPackage.eNS_URI, theClassMakerPackage);
		return theClassMakerPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getContribution() {
		return contributionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContribution_Dependencies() {
		return (EAttribute) contributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContribution_Revision() {
		return (EReference) contributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContribution_Revisions() {
		return (EReference) contributionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContribution_State() {
		return (EReference) contributionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContribution_LatestVersion() {
		return (EAttribute) contributionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContribution_ModelResourceAdapter() {
		return (EReference) contributionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRevision() {
		return revisionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getRevision_State() {
		return (EReference) revisionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRevision_Timestamp() {
		return (EAttribute) revisionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getRevision_StateHistory() {
		return (EReference) revisionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRevision_LatestTimestamp() {
		return (EAttribute) revisionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getState_PackageClassName() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getState_RequiredPlugins() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getState_Revision() {
		return (EReference) stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getState_Timestamp() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getState_DeployableUnitName() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getState_JobFamily() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getState_Resource() {
		return (EReference) stateEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getState_CommitIds() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getState_CommitId() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getState_StateCustomizers() {
		return (EReference) stateEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getState_ProjectName() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getState_Making() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getItem() {
		return itemEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getItem_ModelName() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getItem_Phase() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getItem_Version() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getItem_Language() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getItem_DomainModel() {
		return (EReference) itemEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getItem_Customizers() {
		return (EReference) itemEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getItem_Parent() {
		return (EReference) itemEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getItem_Locale() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getItem_Contribution() {
		return (EReference) itemEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAdapter() {
		return adapterEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getWorkspace() {
		return workspaceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getWorkspace_Projects() {
		return (EReference) workspaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getWorkspace_ResourceSet() {
		return (EAttribute) workspaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getWorkspace_Customizers() {
		return (EReference) workspaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getIntegerToStateMapEntry() {
		return integerToStateMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getIntegerToStateMapEntry_Key() {
		return (EAttribute) integerToStateMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getIntegerToStateMapEntry_Value() {
		return (EReference) integerToStateMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getVersionToRevisionMapEntry() {
		return versionToRevisionMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getVersionToRevisionMapEntry_Key() {
		return (EAttribute) versionToRevisionMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getVersionToRevisionMapEntry_Value() {
		return (EReference) versionToRevisionMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getISchedulingRule() {
		return iSchedulingRuleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExecutor() {
		return executorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getFuture() {
		return futureEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getIAdapterFactory() {
		return iAdapterFactoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCustomizer() {
		return customizerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCustomizer_Rank() {
		return (EAttribute) customizerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getStageQualifier() {
		return stageQualifierEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getStageQualifier_Stage() {
		return (EAttribute) stageQualifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getStageQualifier_Step() {
		return (EAttribute) stageQualifierEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getStageQualifierToCustomizerMapEntry() {
		return stageQualifierToCustomizerMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getStageQualifierToCustomizerMapEntry_Key() {
		return (EReference) stageQualifierToCustomizerMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getStageQualifierToCustomizerMapEntry_Value() {
		return (EReference) stageQualifierToCustomizerMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProject() {
		return projectEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProject_Name() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProject_ProjectName() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProject_Children() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProject_Dirty() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProject_Workspace() {
		return (EReference) projectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProject_ResourcePath() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProject_NeedCompletionNotification() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProject_CompletionNotificationAdapter() {
		return (EReference) projectEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProject_ResourceReloadListener() {
		return (EReference) projectEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getModelPair() {
		return modelPairEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModelPair_Dynamic() {
		return (EReference) modelPairEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModelPair_Generated() {
		return (EReference) modelPairEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModelPair_Parent() {
		return (EReference) modelPairEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getSCMOperator() {
		return scmOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSCMOperator_ProjectName() {
		return (EAttribute) scmOperatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getSCMRegistry() {
		return scmRegistryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getResourceChangeListener() {
		return resourceChangeListenerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getNotification() {
		return notificationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCompletionListener() {
		return completionListenerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getWorker() {
		return workerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getResourceAdapter() {
		return resourceAdapterEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourceAdapter_Resource() {
		return (EReference) resourceAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getResourceAdapter_Filename() {
		return (EAttribute) resourceAdapterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourceAdapter_Project() {
		return (EReference) resourceAdapterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getClassMakerService() {
		return classMakerServiceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getClassMakerService_Workspace() {
		return (EReference) classMakerServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getClassMakerService_SCMRegistry() {
		return (EReference) classMakerServiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getBlueprint() {
		return blueprintEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBlueprint_DynamicModel() {
		return (EReference) blueprintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBlueprint_Dependencies() {
		return (EAttribute) blueprintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBlueprint_CompletionListeners() {
		return (EReference) blueprintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCompletionNotificationAdapter() {
		return completionNotificationAdapterEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCompletionNotificationAdapter_Error() {
		return (EAttribute) completionNotificationAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getLocale() {
		return localeEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getStage() {
		return stageEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getProperties() {
		return propertiesEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getIProgressMonitor() {
		return iProgressMonitorEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getOSGiVersion() {
		return osGiVersionEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getSemaphore() {
		return semaphoreEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getCoreException() {
		return coreExceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getInvocationTargetException() {
		return invocationTargetExceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getIStatus() {
		return iStatusEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getURI() {
		return uriEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getException() {
		return exceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ClassMakerFactory getClassMakerFactory() {
		return (ClassMakerFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		contributionEClass = createEClass(CONTRIBUTION);
		createEAttribute(contributionEClass, CONTRIBUTION__DEPENDENCIES);
		createEReference(contributionEClass, CONTRIBUTION__REVISION);
		createEReference(contributionEClass, CONTRIBUTION__REVISIONS);
		createEReference(contributionEClass, CONTRIBUTION__STATE);
		createEAttribute(contributionEClass, CONTRIBUTION__LATEST_VERSION);
		createEReference(contributionEClass, CONTRIBUTION__MODEL_RESOURCE_ADAPTER);

		revisionEClass = createEClass(REVISION);
		createEReference(revisionEClass, REVISION__STATE);
		createEAttribute(revisionEClass, REVISION__TIMESTAMP);
		createEReference(revisionEClass, REVISION__STATE_HISTORY);
		createEAttribute(revisionEClass, REVISION__LATEST_TIMESTAMP);

		stateEClass = createEClass(STATE);
		createEAttribute(stateEClass, STATE__PACKAGE_CLASS_NAME);
		createEAttribute(stateEClass, STATE__REQUIRED_PLUGINS);
		createEReference(stateEClass, STATE__REVISION);
		createEAttribute(stateEClass, STATE__TIMESTAMP);
		createEAttribute(stateEClass, STATE__DEPLOYABLE_UNIT_NAME);
		createEAttribute(stateEClass, STATE__JOB_FAMILY);
		createEReference(stateEClass, STATE__RESOURCE);
		createEAttribute(stateEClass, STATE__COMMIT_IDS);
		createEAttribute(stateEClass, STATE__COMMIT_ID);
		createEReference(stateEClass, STATE__STATE_CUSTOMIZERS);
		createEAttribute(stateEClass, STATE__PROJECT_NAME);
		createEAttribute(stateEClass, STATE__MAKING);

		itemEClass = createEClass(ITEM);
		createEAttribute(itemEClass, ITEM__MODEL_NAME);
		createEAttribute(itemEClass, ITEM__PHASE);
		createEAttribute(itemEClass, ITEM__VERSION);
		createEAttribute(itemEClass, ITEM__LANGUAGE);
		createEReference(itemEClass, ITEM__DOMAIN_MODEL);
		createEReference(itemEClass, ITEM__CUSTOMIZERS);
		createEReference(itemEClass, ITEM__PARENT);
		createEAttribute(itemEClass, ITEM__LOCALE);
		createEReference(itemEClass, ITEM__CONTRIBUTION);

		adapterEClass = createEClass(ADAPTER);

		workspaceEClass = createEClass(WORKSPACE);
		createEReference(workspaceEClass, WORKSPACE__PROJECTS);
		createEAttribute(workspaceEClass, WORKSPACE__RESOURCE_SET);
		createEReference(workspaceEClass, WORKSPACE__CUSTOMIZERS);

		integerToStateMapEntryEClass = createEClass(INTEGER_TO_STATE_MAP_ENTRY);
		createEAttribute(integerToStateMapEntryEClass, INTEGER_TO_STATE_MAP_ENTRY__KEY);
		createEReference(integerToStateMapEntryEClass, INTEGER_TO_STATE_MAP_ENTRY__VALUE);

		versionToRevisionMapEntryEClass = createEClass(VERSION_TO_REVISION_MAP_ENTRY);
		createEAttribute(versionToRevisionMapEntryEClass, VERSION_TO_REVISION_MAP_ENTRY__KEY);
		createEReference(versionToRevisionMapEntryEClass, VERSION_TO_REVISION_MAP_ENTRY__VALUE);

		iSchedulingRuleEClass = createEClass(ISCHEDULING_RULE);

		executorEClass = createEClass(EXECUTOR);

		futureEClass = createEClass(FUTURE);

		iAdapterFactoryEClass = createEClass(IADAPTER_FACTORY);

		customizerEClass = createEClass(CUSTOMIZER);
		createEAttribute(customizerEClass, CUSTOMIZER__RANK);

		stageQualifierEClass = createEClass(STAGE_QUALIFIER);
		createEAttribute(stageQualifierEClass, STAGE_QUALIFIER__STAGE);
		createEAttribute(stageQualifierEClass, STAGE_QUALIFIER__STEP);

		stageQualifierToCustomizerMapEntryEClass = createEClass(STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY);
		createEReference(stageQualifierToCustomizerMapEntryEClass, STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__KEY);
		createEReference(stageQualifierToCustomizerMapEntryEClass, STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__VALUE);

		projectEClass = createEClass(PROJECT);
		createEAttribute(projectEClass, PROJECT__NAME);
		createEAttribute(projectEClass, PROJECT__PROJECT_NAME);
		createEAttribute(projectEClass, PROJECT__CHILDREN);
		createEAttribute(projectEClass, PROJECT__DIRTY);
		createEReference(projectEClass, PROJECT__WORKSPACE);
		createEAttribute(projectEClass, PROJECT__RESOURCE_PATH);
		createEAttribute(projectEClass, PROJECT__NEED_COMPLETION_NOTIFICATION);
		createEReference(projectEClass, PROJECT__COMPLETION_NOTIFICATION_ADAPTER);
		createEReference(projectEClass, PROJECT__RESOURCE_RELOAD_LISTENER);

		modelPairEClass = createEClass(MODEL_PAIR);
		createEReference(modelPairEClass, MODEL_PAIR__DYNAMIC);
		createEReference(modelPairEClass, MODEL_PAIR__GENERATED);
		createEReference(modelPairEClass, MODEL_PAIR__PARENT);

		scmOperatorEClass = createEClass(SCM_OPERATOR);
		createEAttribute(scmOperatorEClass, SCM_OPERATOR__PROJECT_NAME);

		scmRegistryEClass = createEClass(SCM_REGISTRY);

		resourceEClass = createEClass(RESOURCE);

		resourceChangeListenerEClass = createEClass(RESOURCE_CHANGE_LISTENER);

		notificationEClass = createEClass(NOTIFICATION);

		completionListenerEClass = createEClass(COMPLETION_LISTENER);

		resourceAdapterEClass = createEClass(RESOURCE_ADAPTER);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__RESOURCE);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__FILENAME);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__PROJECT);

		classMakerServiceEClass = createEClass(CLASS_MAKER_SERVICE);
		createEReference(classMakerServiceEClass, CLASS_MAKER_SERVICE__WORKSPACE);
		createEReference(classMakerServiceEClass, CLASS_MAKER_SERVICE__SCM_REGISTRY);

		blueprintEClass = createEClass(BLUEPRINT);
		createEReference(blueprintEClass, BLUEPRINT__DYNAMIC_MODEL);
		createEAttribute(blueprintEClass, BLUEPRINT__DEPENDENCIES);
		createEReference(blueprintEClass, BLUEPRINT__COMPLETION_LISTENERS);

		completionNotificationAdapterEClass = createEClass(COMPLETION_NOTIFICATION_ADAPTER);
		createEAttribute(completionNotificationAdapterEClass, COMPLETION_NOTIFICATION_ADAPTER__ERROR);

		workerEClass = createEClass(WORKER);

		// Create enums
		stageEEnum = createEEnum(STAGE);

		// Create data types
		propertiesEDataType = createEDataType(PROPERTIES);
		iProgressMonitorEDataType = createEDataType(IPROGRESS_MONITOR);
		osGiVersionEDataType = createEDataType(OS_GI_VERSION);
		semaphoreEDataType = createEDataType(SEMAPHORE);
		coreExceptionEDataType = createEDataType(CORE_EXCEPTION);
		invocationTargetExceptionEDataType = createEDataType(INVOCATION_TARGET_EXCEPTION);
		iStatusEDataType = createEDataType(ISTATUS);
		uriEDataType = createEDataType(URI);
		exceptionEDataType = createEDataType(EXCEPTION);
		localeEDataType = createEDataType(LOCALE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is
	 * guarded to have no affect on any invocation but its first. <!--
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

		// Create type parameters
		addETypeParameter(futureEClass, "V");
		ETypeParameter scmOperatorEClass_T = addETypeParameter(scmOperatorEClass, "T");
		ETypeParameter scmRegistryEClass_T = addETypeParameter(scmRegistryEClass, "T");

		// Set bounds for type parameters

		// Add supertypes to classes
		contributionEClass.getESuperTypes().add(this.getProject());
		contributionEClass.getESuperTypes().add(this.getItem());
		revisionEClass.getESuperTypes().add(this.getItem());
		stateEClass.getESuperTypes().add(this.getItem());
		stateEClass.getESuperTypes().add(this.getISchedulingRule());
		workspaceEClass.getESuperTypes().add(this.getISchedulingRule());
		projectEClass.getESuperTypes().add(this.getISchedulingRule());
		resourceAdapterEClass.getESuperTypes().add(this.getAdapter());
		completionNotificationAdapterEClass.getESuperTypes().add(this.getAdapter());

		// Initialize classes and features; add operations and parameters
		initEClass(contributionEClass, Contribution.class, "Contribution", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContribution_Dependencies(), ecorePackage.getEString(), "dependencies", "", 0, -1,
				Contribution.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getContribution_Revision(), this.getRevision(), null, "revision", null, 0, 1, Contribution.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getContribution_Revisions(), this.getVersionToRevisionMapEntry(), null, "revisions", null, 0, -1,
				Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContribution_State(), this.getState(), null, "state", null, 0, 1, Contribution.class,
				IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getContribution_LatestVersion(), this.getOSGiVersion(), "latestVersion", null, 0, 1,
				Contribution.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getContribution_ModelResourceAdapter(), this.getResourceAdapter(),
				this.getResourceAdapter_Project(), "modelResourceAdapter", null, 0, 1, Contribution.class,
				!IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(contributionEClass, this.getRevision(), "createRevision", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "timestamp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "time", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "timestamp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "timestamp", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, this.getRevision(), "newRevision", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, this.getRevision(), "newBareRevision", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, this.getOSGiVersion(), "newVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "base", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "incrementMajor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "incrementMinor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "incrementMicro", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, this.getOSGiVersion(), "newVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "incrementMajor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "incrementMinor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "incrementMicro", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, this.getOSGiVersion(), "nextVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, ecorePackage.getEString(), "make", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRevision(), "revision", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, this.getState(), "getState", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "timestamp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "initAdapters", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRevision(), "revision", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(revisionEClass, Revision.class, "Revision", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRevision_State(), this.getState(), null, "state", null, 0, 1, Revision.class, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getRevision_Timestamp(), ecorePackage.getEInt(), "timestamp", null, 0, 1, Revision.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRevision_StateHistory(), this.getIntegerToStateMapEntry(), null, "stateHistory", null, 0, -1,
				Revision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevision_LatestTimestamp(), ecorePackage.getEInt(), "latestTimestamp", null, 0, 1,
				Revision.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		op = addEOperation(revisionEClass, null, "create", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(revisionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "timestamp", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(revisionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "timestamp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(revisionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(revisionEClass, this.getState(), "newState", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(revisionEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(revisionEClass, null, "addAdapters", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAdapter(), "adapters", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(revisionEClass, null, "copyModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRevision(), "from", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getState_PackageClassName(), ecorePackage.getEString(), "packageClassName", null, 0, 1,
				State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getState_RequiredPlugins(), ecorePackage.getEString(), "requiredPlugins", null, 0, -1,
				State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getState_Revision(), this.getRevision(), null, "revision", null, 0, 1, State.class, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getState_Timestamp(), ecorePackage.getEInt(), "timestamp", null, 0, 1, State.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_DeployableUnitName(), ecorePackage.getEString(), "deployableUnitName", "", 0, 1,
				State.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getState_JobFamily(), ecorePackage.getEString(), "jobFamily", null, 0, 1, State.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_Resource(), this.getResource(), null, "resource", null, 0, 1, State.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_CommitIds(), ecorePackage.getEString(), "commitIds", null, 0, -1, State.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_CommitId(), ecorePackage.getEString(), "commitId", null, 0, 1, State.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_StateCustomizers(), this.getStageQualifierToCustomizerMapEntry(), null,
				"stateCustomizers", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_ProjectName(), ecorePackage.getEString(), "projectName", null, 0, 1, State.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_Making(), ecorePackage.getEBoolean(), "making", "false", 0, 1, State.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(stateEClass, null, "setProjectVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(stateEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(stateEClass, ecorePackage.getEPackage(), "find", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "ePackage", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getStage(), "stage", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(stateEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stateEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(stateEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "filepattern", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		op = addEOperation(stateEClass, ecorePackage.getEString(), "commit", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		addEOperation(stateEClass, null, "saveResource", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(stateEClass, this.getWorker(), "createGenerator", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(stateEClass, this.getWorker(), "createExporter", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(stateEClass, this.getWorker(), "createInstaller", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(stateEClass, this.getWorker(), "createModelLoader", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(itemEClass, Item.class, "Item", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getItem_ModelName(), ecorePackage.getEString(), "modelName", null, 0, 1, Item.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getItem_Phase(), this.getStage(), "phase", "DEFINED", 0, 1, Item.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getItem_Version(), this.getOSGiVersion(), "version", null, 0, 1, Item.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getItem_Language(), ecorePackage.getEString(), "language", "", 0, 1, Item.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getItem_DomainModel(), this.getModelPair(), this.getModelPair_Parent(), "domainModel", null, 0,
				1, Item.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getItem_Customizers(), this.getStageQualifierToCustomizerMapEntry(), null, "customizers", null,
				0, -1, Item.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getItem_Parent(), this.getItem(), null, "parent", null, 0, 1, Item.class, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getItem_Locale(), this.getLocale(), "locale", "", 0, 1, Item.class, !IS_TRANSIENT, !IS_VOLATILE,
				!IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getItem_Contribution(), this.getContribution(), null, "contribution", null, 0, 1, Item.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		op = addEOperation(itemEClass, null, "load", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "create", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(itemEClass, ecorePackage.getEString(), "make", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		op = addEOperation(itemEClass, ecorePackage.getEString(), "initialize", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "commit", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(itemEClass, null, "copyModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getItem(), "from", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(adapterEClass, Adapter.class, "Adapter", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(workspaceEClass, Workspace.class, "Workspace", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkspace_Projects(), this.getProject(), this.getProject_Workspace(), "projects", null, 0, -1,
				Workspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkspace_ResourceSet(), ecorePackage.getEResourceSet(), "resourceSet", null, 0, 1,
				Workspace.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getWorkspace_Customizers(), this.getStageQualifierToCustomizerMapEntry(), null, "customizers",
				null, 0, -1, Workspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(workspaceEClass, null, "initialize", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, null, "provision", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(workspaceEClass, this.getProject(), "createProject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(workspaceEClass, this.getContribution(), "createContribution", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "blueprint", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(workspaceEClass, this.getContribution(), "getContribution", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEObject(), "eObject", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getContribution(), "getContribution", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "ePackage", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getContribution(), "getContribution", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "ePackage", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "searchOptimistic", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getContribution(), "getContribution", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "ePackage", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getStage(), "filter", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getContribution(), "getContribution", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "ePackage", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getStage(), "filter", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "searchOptimistic", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getContribution(), "getContribution", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getProject(), "getProject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getProject(), "getProject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEObject(), "eObject", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getProject(), "getProject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getResource(), "resource", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, null, "registerProject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getProject(), "project", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, null, "unregisterProject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getProject(), "project", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getStage(), "contains", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "blueprint", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, ecorePackage.getEBoolean(), "contains", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEObject(), "eObject", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "object", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		initEClass(integerToStateMapEntryEClass, Map.Entry.class, "IntegerToStateMapEntry", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerToStateMapEntry_Key(), ecorePackage.getEIntegerObject(), "key", null, 0, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getIntegerToStateMapEntry_Value(), this.getState(), null, "value", null, 0, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(versionToRevisionMapEntryEClass, Map.Entry.class, "VersionToRevisionMapEntry", !IS_ABSTRACT,
				!IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVersionToRevisionMapEntry_Key(), this.getOSGiVersion(), "key", null, 0, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVersionToRevisionMapEntry_Value(), this.getRevision(), null, "value", null, 0, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iSchedulingRuleEClass, ISchedulingRule.class, "ISchedulingRule", IS_ABSTRACT, IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(executorEClass, Executor.class, "Executor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(futureEClass, Future.class, "Future", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iAdapterFactoryEClass, IAdapterFactory.class, "IAdapterFactory", IS_ABSTRACT, IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(customizerEClass, Customizer.class, "Customizer", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomizer_Rank(), ecorePackage.getEInt(), "rank", null, 0, 1, Customizer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(customizerEClass, ecorePackage.getEJavaObject(), "customize", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "args", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(stageQualifierEClass, StageQualifier.class, "StageQualifier", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStageQualifier_Stage(), this.getStage(), "stage", null, 0, 1, StageQualifier.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStageQualifier_Step(), ecorePackage.getEString(), "step", null, 0, 1, StageQualifier.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stageQualifierToCustomizerMapEntryEClass, Map.Entry.class, "StageQualifierToCustomizerMapEntry",
				!IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStageQualifierToCustomizerMapEntry_Key(), this.getStageQualifier(), null, "key", null, 0, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStageQualifierToCustomizerMapEntry_Value(), this.getCustomizer(), null, "value", null, 0, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(projectEClass, Project.class, "Project", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProject_Name(), ecorePackage.getEString(), "name", "", 0, 1, Project.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProject_ProjectName(), ecorePackage.getEString(), "projectName", "", 0, 1, Project.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProject_Children(), ecorePackage.getEJavaObject(), "children", null, 0, -1, Project.class,
				IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProject_Dirty(), ecorePackage.getEBoolean(), "dirty", null, 0, 1, Project.class, IS_TRANSIENT,
				IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProject_Workspace(), this.getWorkspace(), this.getWorkspace_Projects(), "workspace", null, 0,
				1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProject_ResourcePath(), ecorePackage.getEString(), "resourcePath", null, 0, 1, Project.class,
				IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProject_NeedCompletionNotification(), ecorePackage.getEBoolean(),
				"needCompletionNotification", null, 0, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProject_CompletionNotificationAdapter(), this.getCompletionNotificationAdapter(), null,
				"completionNotificationAdapter", null, 0, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProject_ResourceReloadListener(), this.getResourceChangeListener(), null,
				"resourceReloadListener", null, 0, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE,
				!IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(projectEClass, null, "create", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(projectEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(projectEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "objects", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(projectEClass, ecorePackage.getEString(), "make", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(projectEClass, ecorePackage.getEBoolean(), "open", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(projectEClass, null, "close", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(projectEClass, ecorePackage.getEString(), "initialize", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "commit", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(projectEClass, null, "addCompletionListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCompletionListener(), "resultListener", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(projectEClass, null, "removeCompletionListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCompletionListener(), "resultListener", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(projectEClass, null, "notifyCompletion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		op = addEOperation(projectEClass, null, "notifyResourceChanged", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNotification(), "notification", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		op = addEOperation(projectEClass, null, "addResourceChangeListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getResourceChangeListener(), "resourceListener", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(projectEClass, null, "removeResourceChangeListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getResourceChangeListener(), "resourceListener", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(modelPairEClass, ModelPair.class, "ModelPair", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelPair_Dynamic(), ecorePackage.getEPackage(), null, "dynamic", null, 0, 1, ModelPair.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelPair_Generated(), ecorePackage.getEPackage(), null, "generated", null, 0, 1,
				ModelPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelPair_Parent(), this.getItem(), this.getItem_DomainModel(), "parent", null, 0, 1,
				ModelPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scmOperatorEClass, SCMOperator.class, "SCMOperator", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSCMOperator_ProjectName(), ecorePackage.getEString(), "projectName", "", 0, 1,
				SCMOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		op = addEOperation(scmOperatorEClass, null, "getRepositorySCM", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());
		EGenericType g1 = createEGenericType(scmOperatorEClass_T);
		initEOperation(op, g1);

		op = addEOperation(scmOperatorEClass, null, "ungetRepositorySCM", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		op = addEOperation(scmOperatorEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "filepattern", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		op = addEOperation(scmOperatorEClass, ecorePackage.getEString(), "commit", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitMessage", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		op = addEOperation(scmOperatorEClass, ecorePackage.getEInt(), "decodeTimestamp", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitMessage", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scmOperatorEClass, this.getOSGiVersion(), "decodeVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitMessage", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scmOperatorEClass, ecorePackage.getEString(), "encodeCommitMessage", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, this.getState(), "state", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "timestamp", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(scmOperatorEClass, null, "deleteProject", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scmOperatorEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "branch", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		op = addEOperation(scmOperatorEClass, null, "checkoutOrphan", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "branch", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "timestamp", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		initEClass(scmRegistryEClass, SCMRegistry.class, "SCMRegistry", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(scmRegistryEClass, ecorePackage.getEBoolean(), "contains", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scmRegistryEClass, null, "get", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getSCMOperator());
		EGenericType g2 = createEGenericType(scmRegistryEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(scmRegistryEClass, null, "put", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getSCMOperator());
		g2 = createEGenericType(scmRegistryEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "operator", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scmRegistryEClass, ecorePackage.getEBoolean(), "containsSCM", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scmRegistryEClass, null, "getSCM", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(scmRegistryEClass_T);
		initEOperation(op, g1);

		op = addEOperation(scmRegistryEClass, null, "putSCM", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(scmRegistryEClass_T);
		addEParameter(op, g1, "scm", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scmRegistryEClass, null, "removeSCM", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scmRegistryEClass, null, "createSCMOperator", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getSCMOperator());
		g2 = createEGenericType(scmRegistryEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEClass(resourceEClass, Resource.class, "Resource", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(resourceChangeListenerEClass, ResourceChangeListener.class, "ResourceChangeListener", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(resourceChangeListenerEClass, null, "changed", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNotification(), "resource", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		initEClass(notificationEClass, Notification.class, "Notification", IS_ABSTRACT, IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(completionListenerEClass, CompletionListener.class, "CompletionListener", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(completionListenerEClass, null, "completed", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getProject(), "result", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		initEClass(resourceAdapterEClass, ResourceAdapter.class, "ResourceAdapter", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceAdapter_Resource(), this.getResource(), null, "resource", null, 0, 1,
				ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceAdapter_Filename(), ecorePackage.getEString(), "filename", null, 0, 1,
				ResourceAdapter.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getResourceAdapter_Project(), this.getContribution(),
				this.getContribution_ModelResourceAdapter(), "project", null, 0, 1, ResourceAdapter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classMakerServiceEClass, ClassMakerService.class, "ClassMakerService", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassMakerService_Workspace(), this.getWorkspace(), null, "workspace", null, 0, 1,
				ClassMakerService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getSCMRegistry());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getClassMakerService_SCMRegistry(), g1, null, "SCMRegistry", null, 0, 1, ClassMakerService.class,
				!IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEPackage(), "make", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "input", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEPackage(), "make", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "input", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEPackage(), "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEPackage(), "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEPackage(), "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "changeVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEPackage(), "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "changeVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEPackage(), "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEPackage(), "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, null, "make", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "input", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutor(), "executor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());
		g1 = createEGenericType(this.getFuture());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		EGenericType g3 = createEGenericType(ecorePackage.getEPackage());
		g2.setEUpperBound(g3);
		initEOperation(op, g1);

		op = addEOperation(classMakerServiceEClass, null, "make", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "input", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutor(), "executor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());
		g1 = createEGenericType(this.getFuture());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g3 = createEGenericType(ecorePackage.getEPackage());
		g2.setEUpperBound(g3);
		initEOperation(op, g1);

		op = addEOperation(classMakerServiceEClass, null, "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutor(), "executor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());
		g1 = createEGenericType(this.getFuture());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g3 = createEGenericType(ecorePackage.getEPackage());
		g2.setEUpperBound(g3);
		initEOperation(op, g1);

		op = addEOperation(classMakerServiceEClass, null, "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutor(), "executor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());
		g1 = createEGenericType(this.getFuture());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g3 = createEGenericType(ecorePackage.getEPackage());
		g2.setEUpperBound(g3);
		initEOperation(op, g1);

		op = addEOperation(classMakerServiceEClass, null, "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "changeVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutor(), "executor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());
		g1 = createEGenericType(this.getFuture());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g3 = createEGenericType(ecorePackage.getEPackage());
		g2.setEUpperBound(g3);
		initEOperation(op, g1);

		op = addEOperation(classMakerServiceEClass, null, "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "changeVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutor(), "executor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());
		g1 = createEGenericType(this.getFuture());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g3 = createEGenericType(ecorePackage.getEPackage());
		g2.setEUpperBound(g3);
		initEOperation(op, g1);

		op = addEOperation(classMakerServiceEClass, null, "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutor(), "executor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());
		g1 = createEGenericType(this.getFuture());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g3 = createEGenericType(ecorePackage.getEPackage());
		g2.setEUpperBound(g3);
		initEOperation(op, g1);

		op = addEOperation(classMakerServiceEClass, null, "replace", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getBlueprint(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getExecutor(), "executor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());
		g1 = createEGenericType(this.getFuture());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g3 = createEGenericType(ecorePackage.getEPackage());
		g2.setEUpperBound(g3);
		initEOperation(op, g1);

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEObject(), "transform", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, ecorePackage.getEObject(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getURI(), "transformationURI", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEObject(), "transform", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, ecorePackage.getEObject(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getURI(), "transformationURI", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEJavaObject(), "invoke", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, ecorePackage.getEOperation(), "operation", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEObject(), "object", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "arguments", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getInvocationTargetException());

		op = addEOperation(classMakerServiceEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "packageName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEBoolean(), "checkEquals", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "model1", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "model2", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(classMakerServiceEClass, this.getBlueprint(), "createBlueprint", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(classMakerServiceEClass, ecorePackage.getEString(), "computeProjectName", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "packageName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(classMakerServiceEClass, null, "copy", 0, 1, IS_UNIQUE, IS_ORDERED);
		ETypeParameter t1 = addETypeParameter(op, "T");
		g1 = createEGenericType(ecorePackage.getEObject());
		t1.getEBounds().add(g1);
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "original", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		initEClass(blueprintEClass, Blueprint.class, "Blueprint", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBlueprint_DynamicModel(), ecorePackage.getEPackage(), null, "dynamicModel", null, 0, 1,
				Blueprint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlueprint_Dependencies(), ecorePackage.getEString(), "dependencies", null, 0, -1,
				Blueprint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getBlueprint_CompletionListeners(), this.getCompletionListener(), null, "completionListeners",
				null, 0, -1, Blueprint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(completionNotificationAdapterEClass, CompletionNotificationAdapter.class,
				"CompletionNotificationAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCompletionNotificationAdapter_Error(), this.getIStatus(), "error", null, 0, 1,
				CompletionNotificationAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workerEClass, Worker.class, "Worker", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(stageEEnum, Stage.class, "Stage");
		addEEnumLiteral(stageEEnum, Stage.DEFINED);
		addEEnumLiteral(stageEEnum, Stage.MODELED);
		addEEnumLiteral(stageEEnum, Stage.GENERATED);
		addEEnumLiteral(stageEEnum, Stage.EXPORTED);
		addEEnumLiteral(stageEEnum, Stage.INSTALLED);
		addEEnumLiteral(stageEEnum, Stage.LOADED);

		// Initialize data types
		initEDataType(propertiesEDataType, Properties.class, "Properties", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iProgressMonitorEDataType, IProgressMonitor.class, "IProgressMonitor", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(osGiVersionEDataType, Version.class, "OSGiVersion", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(semaphoreEDataType, Semaphore.class, "Semaphore", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(coreExceptionEDataType, CoreException.class, "CoreException", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(invocationTargetExceptionEDataType, InvocationTargetException.class, "InvocationTargetException",
				IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iStatusEDataType, IStatus.class, "IStatus", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(uriEDataType, org.eclipse.emf.common.util.URI.class, "URI", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(exceptionEDataType, Exception.class, "Exception", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(localeEDataType, Locale.class, "Locale", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // ClassMakerPackageImpl
