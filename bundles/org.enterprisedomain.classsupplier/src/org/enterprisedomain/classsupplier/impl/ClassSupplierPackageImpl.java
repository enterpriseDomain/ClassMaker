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
package org.enterprisedomain.classsupplier.impl;

import static org.enterprisedomain.classsupplier.ClassSupplierPackage.ADAPTER;
import static org.enterprisedomain.classsupplier.ClassSupplierPackage.RESOURCE;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.enterprisedomain.classsupplier.ClassSupplier;
import org.enterprisedomain.classsupplier.ClassSupplierFactory;
import org.enterprisedomain.classsupplier.ClassSupplierPackage;
import org.enterprisedomain.classsupplier.CompletionListener;
import org.enterprisedomain.classsupplier.Contribution;
import org.enterprisedomain.classsupplier.Customizer;
import org.enterprisedomain.classsupplier.Item;
import org.enterprisedomain.classsupplier.ModelPair;
import org.enterprisedomain.classsupplier.Project;
import org.enterprisedomain.classsupplier.ResourceAdapter;
import org.enterprisedomain.classsupplier.Revision;
import org.enterprisedomain.classsupplier.Stage;
import org.enterprisedomain.classsupplier.StageQualifier;
import org.enterprisedomain.classsupplier.State;
import org.enterprisedomain.classsupplier.Workspace;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassSupplierPackageImpl extends EPackageImpl implements ClassSupplierPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass revisionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass itemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adapterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workspaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerToStateMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass versionToRevisionMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iSchedulingRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass futureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eMapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customizerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stageQualifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stageQualifierToCustomizerMapEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass projectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelPairEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass completionListenerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceAdapterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classSupplierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum stageEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iProgressMonitorEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType osGiVersionEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType semaphoreEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType coreExceptionEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uriEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType exceptionEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType localeEDataType = null;

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
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ClassSupplierPackageImpl() {
		super(eNS_URI, ClassSupplierFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ClassSupplierPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ClassSupplierPackage init() {
		if (isInited)
			return (ClassSupplierPackage) EPackage.Registry.INSTANCE.getEPackage(ClassSupplierPackage.eNS_URI);

		// Obtain or create and register package
		ClassSupplierPackageImpl theClassSupplierPackage = (ClassSupplierPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof ClassSupplierPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new ClassSupplierPackageImpl());

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
		EPackage.Registry.INSTANCE.put(ClassSupplierPackage.eNS_URI, theClassSupplierPackage);
		return theClassSupplierPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContribution() {
		return contributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContribution_Revision() {
		return (EReference) contributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContribution_Revisions() {
		return (EReference) contributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContribution_State() {
		return (EReference) contributionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContribution_LatestVersion() {
		return (EAttribute) contributionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContribution_Model() {
		return (EReference) contributionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRevision() {
		return revisionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRevision_Contribution() {
		return (EReference) revisionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRevision_State() {
		return (EReference) revisionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevision_Timestamp() {
		return (EAttribute) revisionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRevision_StateHistory() {
		return (EReference) revisionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRevision_LatestTimestamp() {
		return (EAttribute) revisionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_Contribution() {
		return (EReference) stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_Revision() {
		return (EReference) stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_Timestamp() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_DeployableUnitName() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_JobFamily() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_Resource() {
		return (EReference) stateEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_CommitIds() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_StateCustomizers() {
		return (EReference) stateEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_ProjectName() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getItem() {
		return itemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getItem_ModelName() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getItem_Phase() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getItem_Version() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getItem_Language() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getItem_DomainModel() {
		return (EReference) itemEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getItem_Customizers() {
		return (EReference) itemEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getItem_Parent() {
		return (EReference) itemEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getItem_Locale() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdapter() {
		return adapterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkspace() {
		return workspaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkspace_Projects() {
		return (EReference) workspaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkspace_ResourceSet() {
		return (EAttribute) workspaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerToStateMapEntry() {
		return integerToStateMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerToStateMapEntry_Key() {
		return (EAttribute) integerToStateMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntegerToStateMapEntry_Value() {
		return (EReference) integerToStateMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVersionToRevisionMapEntry() {
		return versionToRevisionMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVersionToRevisionMapEntry_Key() {
		return (EAttribute) versionToRevisionMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVersionToRevisionMapEntry_Value() {
		return (EReference) versionToRevisionMapEntryEClass.getEStructuralFeatures().get(1);
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFuture() {
		return futureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEMap() {
		return eMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomizer() {
		return customizerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStageQualifier() {
		return stageQualifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStageQualifier_Stage() {
		return (EAttribute) stageQualifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStageQualifier_Step() {
		return (EAttribute) stageQualifierEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStageQualifierToCustomizerMapEntry() {
		return stageQualifierToCustomizerMapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStageQualifierToCustomizerMapEntry_Key() {
		return (EReference) stageQualifierToCustomizerMapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStageQualifierToCustomizerMapEntry_Value() {
		return (EReference) stageQualifierToCustomizerMapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProject() {
		return projectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Name() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_ProjectName() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProject_Children() {
		return (EReference) projectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Dirty() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProject_Workspace() {
		return (EReference) projectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceSet() {
		return resourceSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelPair() {
		return modelPairEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelPair_Dynamic() {
		return (EReference) modelPairEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelPair_Generated() {
		return (EReference) modelPairEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelPair_Parent() {
		return (EReference) modelPairEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompletionListener() {
		return completionListenerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceAdapter() {
		return resourceAdapterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceAdapter_Resource() {
		return (EReference) resourceAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceAdapter_Filename() {
		return (EAttribute) resourceAdapterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassSupplier() {
		return classSupplierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassSupplier_Workspace() {
		return (EReference) classSupplierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLocale() {
		return localeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getStage() {
		return stageEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIProgressMonitor() {
		return iProgressMonitorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getOSGiVersion() {
		return osGiVersionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSemaphore() {
		return semaphoreEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCoreException() {
		return coreExceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getURI() {
		return uriEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getException() {
		return exceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassSupplierFactory getClassSupplierFactory() {
		return (ClassSupplierFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		contributionEClass = createEClass(CONTRIBUTION);
		createEReference(contributionEClass, CONTRIBUTION__REVISION);
		createEReference(contributionEClass, CONTRIBUTION__REVISIONS);
		createEReference(contributionEClass, CONTRIBUTION__STATE);
		createEAttribute(contributionEClass, CONTRIBUTION__LATEST_VERSION);
		createEReference(contributionEClass, CONTRIBUTION__MODEL);

		revisionEClass = createEClass(REVISION);
		createEReference(revisionEClass, REVISION__CONTRIBUTION);
		createEReference(revisionEClass, REVISION__STATE);
		createEAttribute(revisionEClass, REVISION__TIMESTAMP);
		createEReference(revisionEClass, REVISION__STATE_HISTORY);
		createEAttribute(revisionEClass, REVISION__LATEST_TIMESTAMP);

		stateEClass = createEClass(STATE);
		createEReference(stateEClass, STATE__CONTRIBUTION);
		createEReference(stateEClass, STATE__REVISION);
		createEAttribute(stateEClass, STATE__TIMESTAMP);
		createEAttribute(stateEClass, STATE__DEPLOYABLE_UNIT_NAME);
		createEAttribute(stateEClass, STATE__JOB_FAMILY);
		createEReference(stateEClass, STATE__RESOURCE);
		createEAttribute(stateEClass, STATE__COMMIT_IDS);
		createEReference(stateEClass, STATE__STATE_CUSTOMIZERS);
		createEAttribute(stateEClass, STATE__PROJECT_NAME);

		itemEClass = createEClass(ITEM);
		createEAttribute(itemEClass, ITEM__MODEL_NAME);
		createEAttribute(itemEClass, ITEM__PHASE);
		createEAttribute(itemEClass, ITEM__VERSION);
		createEAttribute(itemEClass, ITEM__LANGUAGE);
		createEReference(itemEClass, ITEM__DOMAIN_MODEL);
		createEReference(itemEClass, ITEM__CUSTOMIZERS);
		createEReference(itemEClass, ITEM__PARENT);
		createEAttribute(itemEClass, ITEM__LOCALE);

		adapterEClass = createEClass(ADAPTER);

		workspaceEClass = createEClass(WORKSPACE);
		createEReference(workspaceEClass, WORKSPACE__PROJECTS);
		createEAttribute(workspaceEClass, WORKSPACE__RESOURCE_SET);

		integerToStateMapEntryEClass = createEClass(INTEGER_TO_STATE_MAP_ENTRY);
		createEAttribute(integerToStateMapEntryEClass, INTEGER_TO_STATE_MAP_ENTRY__KEY);
		createEReference(integerToStateMapEntryEClass, INTEGER_TO_STATE_MAP_ENTRY__VALUE);

		versionToRevisionMapEntryEClass = createEClass(VERSION_TO_REVISION_MAP_ENTRY);
		createEAttribute(versionToRevisionMapEntryEClass, VERSION_TO_REVISION_MAP_ENTRY__KEY);
		createEReference(versionToRevisionMapEntryEClass, VERSION_TO_REVISION_MAP_ENTRY__VALUE);

		iSchedulingRuleEClass = createEClass(ISCHEDULING_RULE);

		futureEClass = createEClass(FUTURE);

		eMapEClass = createEClass(EMAP);

		customizerEClass = createEClass(CUSTOMIZER);

		stageQualifierEClass = createEClass(STAGE_QUALIFIER);
		createEAttribute(stageQualifierEClass, STAGE_QUALIFIER__STAGE);
		createEAttribute(stageQualifierEClass, STAGE_QUALIFIER__STEP);

		stageQualifierToCustomizerMapEntryEClass = createEClass(STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY);
		createEReference(stageQualifierToCustomizerMapEntryEClass, STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__KEY);
		createEReference(stageQualifierToCustomizerMapEntryEClass, STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__VALUE);

		projectEClass = createEClass(PROJECT);
		createEAttribute(projectEClass, PROJECT__NAME);
		createEAttribute(projectEClass, PROJECT__PROJECT_NAME);
		createEReference(projectEClass, PROJECT__CHILDREN);
		createEAttribute(projectEClass, PROJECT__DIRTY);
		createEReference(projectEClass, PROJECT__WORKSPACE);

		resourceSetEClass = createEClass(RESOURCE_SET);

		modelPairEClass = createEClass(MODEL_PAIR);
		createEReference(modelPairEClass, MODEL_PAIR__DYNAMIC);
		createEReference(modelPairEClass, MODEL_PAIR__GENERATED);
		createEReference(modelPairEClass, MODEL_PAIR__PARENT);

		resourceEClass = createEClass(RESOURCE);

		completionListenerEClass = createEClass(COMPLETION_LISTENER);

		resourceAdapterEClass = createEClass(RESOURCE_ADAPTER);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__RESOURCE);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__FILENAME);

		classSupplierEClass = createEClass(CLASS_SUPPLIER);
		createEReference(classSupplierEClass, CLASS_SUPPLIER__WORKSPACE);

		// Create enums
		stageEEnum = createEEnum(STAGE);

		// Create data types
		iProgressMonitorEDataType = createEDataType(IPROGRESS_MONITOR);
		osGiVersionEDataType = createEDataType(OS_GI_VERSION);
		semaphoreEDataType = createEDataType(SEMAPHORE);
		coreExceptionEDataType = createEDataType(CORE_EXCEPTION);
		uriEDataType = createEDataType(URI);
		exceptionEDataType = createEDataType(EXCEPTION);
		localeEDataType = createEDataType(LOCALE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters
		addETypeParameter(futureEClass, "ResultType");
		addETypeParameter(eMapEClass, "K");
		addETypeParameter(eMapEClass, "V");

		// Set bounds for type parameters

		// Add supertypes to classes
		contributionEClass.getESuperTypes().add(this.getProject());
		contributionEClass.getESuperTypes().add(this.getItem());
		revisionEClass.getESuperTypes().add(this.getItem());
		stateEClass.getESuperTypes().add(this.getItem());
		workspaceEClass.getESuperTypes().add(this.getISchedulingRule());
		projectEClass.getESuperTypes().add(this.getISchedulingRule());
		resourceAdapterEClass.getESuperTypes().add(this.getAdapter());

		// Initialize classes and features; add operations and parameters
		initEClass(contributionEClass, Contribution.class, "Contribution", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
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
		initEReference(getContribution_Model(), this.getResourceAdapter(), null, "model", null, 0, 1,
				Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(contributionEClass, this.getRevision(), "createRevision", 0, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "time", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "time", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "time", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "time", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, this.getRevision(), "newRevision", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, this.getOSGiVersion(), "newVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOSGiVersion(), "base", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEBoolean(), "incrementMajor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEBoolean(), "incrementMinor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEBoolean(), "incrementMicro", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, this.getOSGiVersion(), "newVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEBoolean(), "incrementMajor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEBoolean(), "incrementMinor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEBoolean(), "incrementMicro", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, this.getOSGiVersion(), "nextVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, null, "addSaveCompletionListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCompletionListener(), "resultListener", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "removeSaveCompletionListener", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCompletionListener(), "resultListener", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, ecorePackage.getEString(), "save", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRevision(), "revision", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(contributionEClass, this.getState(), "getState", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "timestamp", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(contributionEClass, null, "initAdapters", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRevision(), "revision", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(revisionEClass, Revision.class, "Revision", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRevision_Contribution(), this.getContribution(), null, "contribution", null, 0, 1,
				Revision.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getRevision_State(), this.getState(), null, "state", null, 0, 1, Revision.class, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getRevision_Timestamp(), theEcorePackage.getEInt(), "timestamp", null, 0, 1, Revision.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRevision_StateHistory(), this.getIntegerToStateMapEntry(), null, "stateHistory", null, 0, -1,
				Revision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRevision_LatestTimestamp(), theEcorePackage.getEInt(), "latestTimestamp", null, 0, 1,
				Revision.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		op = addEOperation(revisionEClass, null, "create", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(revisionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEInt(), "stateTime", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "commitId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(revisionEClass, null, "checkout", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEInt(), "stateTime", 0, 1, IS_UNIQUE, IS_ORDERED);

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
		initEReference(getState_Contribution(), this.getContribution(), null, "contribution", null, 0, 1, State.class,
				IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getState_Revision(), this.getRevision(), null, "revision", null, 0, 1, State.class, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getState_Timestamp(), theEcorePackage.getEInt(), "timestamp", null, 0, 1, State.class,
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
		initEReference(getState_StateCustomizers(), this.getStageQualifierToCustomizerMapEntry(), null,
				"stateCustomizers", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_ProjectName(), ecorePackage.getEString(), "projectName", null, 0, 1, State.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

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

		op = addEOperation(stateEClass, ecorePackage.getEString(), "commit", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "filepattern", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		addEOperation(stateEClass, null, "saveResource", 0, 1, IS_UNIQUE, IS_ORDERED);

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

		op = addEOperation(itemEClass, null, "load", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "create", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(itemEClass, ecorePackage.getEString(), "save", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		addEOperation(itemEClass, ecorePackage.getEString(), "initialize", 0, 1, IS_UNIQUE, IS_ORDERED);

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

		addEOperation(workspaceEClass, null, "initialize", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, null, "provision", 0, 1, IS_UNIQUE, IS_ORDERED);
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
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getProject(), "createProject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(workspaceEClass, this.getProject(), "getProject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "projectName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(workspaceEClass, this.getContribution(), "getContribution", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEPackage(), "ePackage", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "searchOptimistic", 0, 1, IS_UNIQUE, IS_ORDERED);

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
		initEAttribute(getIntegerToStateMapEntry_Key(), theEcorePackage.getEIntegerObject(), "key", null, 0, 1,
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

		initEClass(futureEClass, Future.class, "Future", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(eMapEClass, EMap.class, "EMap", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(customizerEClass, Customizer.class, "Customizer", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(customizerEClass, null, "customize", 0, 1, IS_UNIQUE, IS_ORDERED);
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
		initEReference(getProject_Children(), ecorePackage.getEObject(), null, "children", null, 0, -1, Project.class,
				IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getProject_Dirty(), ecorePackage.getEBoolean(), "dirty", null, 0, 1, Project.class, IS_TRANSIENT,
				IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProject_Workspace(), this.getWorkspace(), this.getWorkspace_Projects(), "workspace", null, 0,
				1, Project.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(projectEClass, null, "create", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(projectEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(projectEClass, null, "delete", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "objects", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(projectEClass, ecorePackage.getEString(), "save", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		initEClass(resourceSetEClass, ResourceSet.class, "ResourceSet", IS_ABSTRACT, IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelPairEClass, ModelPair.class, "ModelPair", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelPair_Dynamic(), theEcorePackage.getEPackage(), null, "dynamic", null, 0, 1,
				ModelPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelPair_Generated(), theEcorePackage.getEPackage(), null, "generated", null, 0, 1,
				ModelPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelPair_Parent(), this.getItem(), this.getItem_DomainModel(), "parent", null, 0, 1,
				ModelPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(completionListenerEClass, CompletionListener.class, "CompletionListener", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(completionListenerEClass, null, "completed", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getContribution(), "result", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getException());

		initEClass(resourceAdapterEClass, ResourceAdapter.class, "ResourceAdapter", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceAdapter_Resource(), this.getResource(), null, "resource", null, 0, 1,
				ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceAdapter_Filename(), ecorePackage.getEString(), "filename", null, 0, 1,
				ResourceAdapter.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		initEClass(classSupplierEClass, ClassSupplier.class, "ClassSupplier", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassSupplier_Workspace(), this.getWorkspace(), null, "workspace", null, 0, 1,
				ClassSupplier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(classSupplierEClass, theEcorePackage.getEPackage(), "create", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "dynamicModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classSupplierEClass, theEcorePackage.getEPackage(), "create", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "dynamicModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classSupplierEClass, theEcorePackage.getEPackage(), "update", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "queryModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "dynamicModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classSupplierEClass, theEcorePackage.getEPackage(), "update", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "queryModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "dynamicModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classSupplierEClass, theEcorePackage.getEPackage(), "update", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "queryModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "dynamicModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "changeVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		op = addEOperation(classSupplierEClass, theEcorePackage.getEPackage(), "update", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "queryModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEPackage(), "dynamicModel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "changeVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getCoreException());

		// Initialize enums and add enum literals
		initEEnum(stageEEnum, Stage.class, "Stage");
		addEEnumLiteral(stageEEnum, Stage.DEFINED);
		addEEnumLiteral(stageEEnum, Stage.MODELED);
		addEEnumLiteral(stageEEnum, Stage.GENERATED);
		addEEnumLiteral(stageEEnum, Stage.EXPORTED);
		addEEnumLiteral(stageEEnum, Stage.INSTALLED);
		addEEnumLiteral(stageEEnum, Stage.LOADED);

		// Initialize data types
		initEDataType(iProgressMonitorEDataType, IProgressMonitor.class, "IProgressMonitor", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(osGiVersionEDataType, Version.class, "OSGiVersion", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(semaphoreEDataType, Semaphore.class, "Semaphore", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(coreExceptionEDataType, CoreException.class, "CoreException", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(uriEDataType, org.eclipse.emf.common.util.URI.class, "URI", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(exceptionEDataType, Exception.class, "Exception", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(localeEDataType, Locale.class, "Locale", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //ClassSupplierPackageImpl
