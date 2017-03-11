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

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.enterprisedomain.classsupplier.*;
import org.enterprisedomain.classsupplier.ClassPlant;
import org.enterprisedomain.classsupplier.ClassSupplierFactory;
import org.enterprisedomain.classsupplier.ClassSupplierPackage;
import org.enterprisedomain.classsupplier.Contribution;
import org.enterprisedomain.classsupplier.Customizer;
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
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassSupplierFactoryImpl extends EFactoryImpl implements ClassSupplierFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ClassSupplierFactory init() {
		try {
			ClassSupplierFactory theClassSupplierFactory = (ClassSupplierFactory) EPackage.Registry.INSTANCE
					.getEFactory(ClassSupplierPackage.eNS_URI);
			if (theClassSupplierFactory != null) {
				return theClassSupplierFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ClassSupplierFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassSupplierFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case ClassSupplierPackage.CONTRIBUTION:
			return createContribution();
		case ClassSupplierPackage.REVISION:
			return createRevision();
		case ClassSupplierPackage.STATE:
			return createState();
		case ClassSupplierPackage.WORKSPACE:
			return createWorkspace();
		case ClassSupplierPackage.INTEGER_TO_STATE_MAP_ENTRY:
			return (EObject) createIntegerToStateMapEntry();
		case ClassSupplierPackage.VERSION_TO_REVISION_MAP_ENTRY:
			return (EObject) createVersionToRevisionMapEntry();
		case ClassSupplierPackage.CUSTOMIZER:
			return createCustomizer();
		case ClassSupplierPackage.STAGE_QUALIFIER:
			return createStageQualifier();
		case ClassSupplierPackage.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY:
			return (EObject) createStageQualifierToCustomizerMapEntry();
		case ClassSupplierPackage.PROJECT:
			return createProject();
		case ClassSupplierPackage.MODEL_PAIR:
			return createModelPair();
		case ClassSupplierPackage.RESOURCE_ADAPTER:
			return createResourceAdapter();
		case ClassSupplierPackage.CLASS_PLANT:
			return createClassPlant();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case ClassSupplierPackage.STAGE:
			return createStageFromString(eDataType, initialValue);
		case ClassSupplierPackage.IPROGRESS_MONITOR:
			return createIProgressMonitorFromString(eDataType, initialValue);
		case ClassSupplierPackage.OS_GI_VERSION:
			return createOSGiVersionFromString(eDataType, initialValue);
		case ClassSupplierPackage.SEMAPHORE:
			return createSemaphoreFromString(eDataType, initialValue);
		case ClassSupplierPackage.CORE_EXCEPTION:
			return createCoreExceptionFromString(eDataType, initialValue);
		case ClassSupplierPackage.URI:
			return createURIFromString(eDataType, initialValue);
		case ClassSupplierPackage.EXCEPTION:
			return createExceptionFromString(eDataType, initialValue);
		case ClassSupplierPackage.LOCALE:
			return createLocaleFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case ClassSupplierPackage.STAGE:
			return convertStageToString(eDataType, instanceValue);
		case ClassSupplierPackage.IPROGRESS_MONITOR:
			return convertIProgressMonitorToString(eDataType, instanceValue);
		case ClassSupplierPackage.OS_GI_VERSION:
			return convertOSGiVersionToString(eDataType, instanceValue);
		case ClassSupplierPackage.SEMAPHORE:
			return convertSemaphoreToString(eDataType, instanceValue);
		case ClassSupplierPackage.CORE_EXCEPTION:
			return convertCoreExceptionToString(eDataType, instanceValue);
		case ClassSupplierPackage.URI:
			return convertURIToString(eDataType, instanceValue);
		case ClassSupplierPackage.EXCEPTION:
			return convertExceptionToString(eDataType, instanceValue);
		case ClassSupplierPackage.LOCALE:
			return convertLocaleToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Contribution createContribution() {
		ContributionImpl contribution = new ContributionImpl();
		return contribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Revision createRevision() {
		RevisionImpl revision = new RevisionImpl();
		return revision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Workspace createWorkspace() {
		WorkspaceImpl workspace = new WorkspaceImpl();
		return workspace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<Integer, State> createIntegerToStateMapEntry() {
		IntegerToStateMapEntryImpl integerToStateMapEntry = new IntegerToStateMapEntryImpl();
		return integerToStateMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<Version, Revision> createVersionToRevisionMapEntry() {
		VersionToRevisionMapEntryImpl versionToRevisionMapEntry = new VersionToRevisionMapEntryImpl();
		return versionToRevisionMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Customizer createCustomizer() {
		CustomizerImpl customizer = new CustomizerImpl();
		return customizer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StageQualifier createStageQualifier() {
		StageQualifierImpl stageQualifier = new StageQualifierImpl();
		return stageQualifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<StageQualifier, Customizer> createStageQualifierToCustomizerMapEntry() {
		StageQualifierToCustomizerMapEntryImpl stageQualifierToCustomizerMapEntry = new StageQualifierToCustomizerMapEntryImpl();
		return stageQualifierToCustomizerMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Project createProject() {
		ProjectImpl project = new ProjectImpl();
		return project;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPair createModelPair() {
		ModelPairImpl modelPair = new ModelPairImpl();
		return modelPair;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceAdapter createResourceAdapter() {
		ResourceAdapterImpl resourceAdapter = new ResourceAdapterImpl();
		return resourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassPlant createClassPlant() {
		ClassPlantImpl classPlant = new ClassPlantImpl();
		return classPlant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stage createStageFromString(EDataType eDataType, String initialValue) {
		Stage result = Stage.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStageToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IProgressMonitor createIProgressMonitorFromString(EDataType eDataType, String initialValue) {
		return (IProgressMonitor) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIProgressMonitorToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Version createOSGiVersionFromString(EDataType eDataType, String initialValue) {
		return (Version) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOSGiVersionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Semaphore createSemaphoreFromString(EDataType eDataType, String initialValue) {
		return (Semaphore) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSemaphoreToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreException createCoreExceptionFromString(EDataType eDataType, String initialValue) {
		return (CoreException) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCoreExceptionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URI createURIFromString(EDataType eDataType, String initialValue) {
		return (URI) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertURIToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Exception createExceptionFromString(EDataType eDataType, String initialValue) {
		return (Exception) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExceptionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Locale createLocaleFromString(EDataType eDataType, String initialValue) {
		return (Locale) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLocaleToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassSupplierPackage getClassSupplierPackage() {
		return (ClassSupplierPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ClassSupplierPackage getPackage() {
		return ClassSupplierPackage.eINSTANCE;
	}

} //ClassSupplierFactoryImpl
