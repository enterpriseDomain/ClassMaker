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

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
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
import org.enterprisedomain.classmaker.*;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerPlant;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class ClassMakerFactoryImpl extends EFactoryImpl implements ClassMakerFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static ClassMakerFactory init() {
		try {
			ClassMakerFactory theClassMakerFactory = (ClassMakerFactory) EPackage.Registry.INSTANCE
					.getEFactory(ClassMakerPackage.eNS_URI);
			if (theClassMakerFactory != null) {
				return theClassMakerFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ClassMakerFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public ClassMakerFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case ClassMakerPackage.CONTRIBUTION:
			return createContribution();
		case ClassMakerPackage.REVISION:
			return createRevision();
		case ClassMakerPackage.STATE:
			return createState();
		case ClassMakerPackage.WORKSPACE:
			return createWorkspace();
		case ClassMakerPackage.INTEGER_TO_STATE_MAP_ENTRY:
			return (EObject) createIntegerToStateMapEntry();
		case ClassMakerPackage.VERSION_TO_REVISION_MAP_ENTRY:
			return (EObject) createVersionToRevisionMapEntry();
		case ClassMakerPackage.CUSTOMIZER:
			return createCustomizer();
		case ClassMakerPackage.STAGE_QUALIFIER:
			return createStageQualifier();
		case ClassMakerPackage.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY:
			return (EObject) createStageQualifierToCustomizerMapEntry();
		case ClassMakerPackage.PROJECT:
			return createProject();
		case ClassMakerPackage.MODEL_PAIR:
			return createModelPair();
		case ClassMakerPackage.RESOURCE_ADAPTER:
			return createResourceAdapter();
		case ClassMakerPackage.CLASS_MAKER_PLANT:
			return createClassMakerPlant();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case ClassMakerPackage.STAGE:
			return createStageFromString(eDataType, initialValue);
		case ClassMakerPackage.PROPERTIES:
			return createPropertiesFromString(eDataType, initialValue);
		case ClassMakerPackage.IPROGRESS_MONITOR:
			return createIProgressMonitorFromString(eDataType, initialValue);
		case ClassMakerPackage.OS_GI_VERSION:
			return createOSGiVersionFromString(eDataType, initialValue);
		case ClassMakerPackage.SEMAPHORE:
			return createSemaphoreFromString(eDataType, initialValue);
		case ClassMakerPackage.CORE_EXCEPTION:
			return createCoreExceptionFromString(eDataType, initialValue);
		case ClassMakerPackage.INVOCATION_TARGET_EXCEPTION:
			return createInvocationTargetExceptionFromString(eDataType, initialValue);
		case ClassMakerPackage.URI:
			return createURIFromString(eDataType, initialValue);
		case ClassMakerPackage.EXCEPTION:
			return createExceptionFromString(eDataType, initialValue);
		case ClassMakerPackage.LOCALE:
			return createLocaleFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case ClassMakerPackage.STAGE:
			return convertStageToString(eDataType, instanceValue);
		case ClassMakerPackage.PROPERTIES:
			return convertPropertiesToString(eDataType, instanceValue);
		case ClassMakerPackage.IPROGRESS_MONITOR:
			return convertIProgressMonitorToString(eDataType, instanceValue);
		case ClassMakerPackage.OS_GI_VERSION:
			return convertOSGiVersionToString(eDataType, instanceValue);
		case ClassMakerPackage.SEMAPHORE:
			return convertSemaphoreToString(eDataType, instanceValue);
		case ClassMakerPackage.CORE_EXCEPTION:
			return convertCoreExceptionToString(eDataType, instanceValue);
		case ClassMakerPackage.INVOCATION_TARGET_EXCEPTION:
			return convertInvocationTargetExceptionToString(eDataType, instanceValue);
		case ClassMakerPackage.URI:
			return convertURIToString(eDataType, instanceValue);
		case ClassMakerPackage.EXCEPTION:
			return convertExceptionToString(eDataType, instanceValue);
		case ClassMakerPackage.LOCALE:
			return convertLocaleToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Contribution createContribution() {
		ContributionImpl contribution = new ContributionImpl();
		return contribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Revision createRevision() {
		RevisionImpl revision = new RevisionImpl();
		return revision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Workspace createWorkspace() {
		WorkspaceImpl workspace = new WorkspaceImpl();
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<Integer, State> createIntegerToStateMapEntry() {
		IntegerToStateMapEntryImpl integerToStateMapEntry = new IntegerToStateMapEntryImpl();
		return integerToStateMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<Version, Revision> createVersionToRevisionMapEntry() {
		VersionToRevisionMapEntryImpl versionToRevisionMapEntry = new VersionToRevisionMapEntryImpl();
		return versionToRevisionMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Customizer createCustomizer() {
		CustomizerImpl customizer = new CustomizerImpl();
		return customizer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public StageQualifier createStageQualifier() {
		StageQualifierImpl stageQualifier = new StageQualifierImpl();
		return stageQualifier;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<StageQualifier, Customizer> createStageQualifierToCustomizerMapEntry() {
		StageQualifierToCustomizerMapEntryImpl stageQualifierToCustomizerMapEntry = new StageQualifierToCustomizerMapEntryImpl();
		return stageQualifierToCustomizerMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Project createProject() {
		ProjectImpl project = new ProjectImpl();
		return project;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPair createModelPair() {
		ModelPairImpl modelPair = new ModelPairImpl();
		return modelPair;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceAdapter createResourceAdapter() {
		ResourceAdapterImpl resourceAdapter = new ResourceAdapterImpl();
		return resourceAdapter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClassMakerPlant createClassMakerPlant() {
		ClassMakerPlantImpl classMakerPlant = new ClassMakerPlantImpl();
		return classMakerPlant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStageToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Properties createPropertiesFromString(EDataType eDataType, String initialValue) {
		final Properties properties = new Properties();
		try {
			properties.load(new StringReader(initialValue));
			return properties;
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
		}
		return (Properties) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPropertiesToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IProgressMonitor createIProgressMonitorFromString(EDataType eDataType, String initialValue) {
		return (IProgressMonitor) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIProgressMonitorToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Version createOSGiVersionFromString(EDataType eDataType, String initialValue) {
		return (Version) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOSGiVersionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Semaphore createSemaphoreFromString(EDataType eDataType, String initialValue) {
		return (Semaphore) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSemaphoreToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CoreException createCoreExceptionFromString(EDataType eDataType, String initialValue) {
		return (CoreException) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCoreExceptionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InvocationTargetException createInvocationTargetExceptionFromString(EDataType eDataType,
			String initialValue) {
		return (InvocationTargetException) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInvocationTargetExceptionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public URI createURIFromString(EDataType eDataType, String initialValue) {
		return (URI) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertURIToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Exception createExceptionFromString(EDataType eDataType, String initialValue) {
		return (Exception) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExceptionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Locale createLocaleFromString(EDataType eDataType, String initialValue) {
		return (Locale) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLocaleToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ClassMakerPackage getClassMakerPackage() {
		return (ClassMakerPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ClassMakerPackage getPackage() {
		return ClassMakerPackage.eINSTANCE;
	}

} // ClassMakerFactoryImpl
