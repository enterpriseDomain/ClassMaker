/**
 * Copyright 2019 Kyrill Zotkin
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
package org.enterprisedomain.classmaker.util;

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
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.enterprisedomain.classmaker.Blueprint;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.CompletionNotificationAdapter;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.Models;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.ResourceChangeListener;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.SCMRegistry;
import org.enterprisedomain.classmaker.SelectRevealHandler;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Strategy;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc
 * -->
 * 
 * @see org.enterprisedomain.classmaker.ClassMakerPackage
 * @generated
 */
public class ClassMakerValidator extends EObjectValidator {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final ClassMakerValidator INSTANCE = new ClassMakerValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource()
	 * source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode()
	 * codes} from this package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.enterprisedomain.classmaker";

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants in a derived class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public ClassMakerValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return ClassMakerPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		switch (classifierID) {
		case ClassMakerPackage.CONTRIBUTION:
			return validateContribution((Contribution) value, diagnostics, context);
		case ClassMakerPackage.REVISION:
			return validateRevision((Revision) value, diagnostics, context);
		case ClassMakerPackage.STATE:
			return validateState((State) value, diagnostics, context);
		case ClassMakerPackage.STRATEGY:
			return validateStrategy((Strategy) value, diagnostics, context);
		case ClassMakerPackage.ITEM:
			return validateItem((Item) value, diagnostics, context);
		case ClassMakerPackage.ADAPTER:
			return validateAdapter((Adapter) value, diagnostics, context);
		case ClassMakerPackage.WORKSPACE:
			return validateWorkspace((Workspace) value, diagnostics, context);
		case ClassMakerPackage.LONG_TO_STATE_MAP_ENTRY:
			return validateLongToStateMapEntry((Map.Entry<?, ?>) value, diagnostics, context);
		case ClassMakerPackage.VERSION_TO_REVISION_MAP_ENTRY:
			return validateVersionToRevisionMapEntry((Map.Entry<?, ?>) value, diagnostics, context);
		case ClassMakerPackage.ISCHEDULING_RULE:
			return validateISchedulingRule((ISchedulingRule) value, diagnostics, context);
		case ClassMakerPackage.EXECUTOR:
			return validateExecutor((Executor) value, diagnostics, context);
		case ClassMakerPackage.FUTURE:
			return validateFuture((Future<?>) value, diagnostics, context);
		case ClassMakerPackage.IADAPTER_FACTORY:
			return validateIAdapterFactory((IAdapterFactory) value, diagnostics, context);
		case ClassMakerPackage.CUSTOMIZER:
			return validateCustomizer((Customizer) value, diagnostics, context);
		case ClassMakerPackage.STAGE_QUALIFIER:
			return validateStageQualifier((StageQualifier) value, diagnostics, context);
		case ClassMakerPackage.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY:
			return validateStageQualifierToCustomizerMapEntry((Map.Entry<?, ?>) value, diagnostics, context);
		case ClassMakerPackage.MODELS:
			return validateModels((Models) value, diagnostics, context);
		case ClassMakerPackage.EMF_PLUGIN:
			return validateEMFPlugin((EMFPlugin) value, diagnostics, context);
		case ClassMakerPackage.SCM_OPERATOR:
			return validateSCMOperator((SCMOperator<?>) value, diagnostics, context);
		case ClassMakerPackage.SCM_REGISTRY:
			return validateSCMRegistry((SCMRegistry<?>) value, diagnostics, context);
		case ClassMakerPackage.PROJECT:
			return validateProject((Project) value, diagnostics, context);
		case ClassMakerPackage.RESOURCE:
			return validateResource((Resource) value, diagnostics, context);
		case ClassMakerPackage.RESOURCE_CHANGE_LISTENER:
			return validateResourceChangeListener((ResourceChangeListener) value, diagnostics, context);
		case ClassMakerPackage.NOTIFICATION:
			return validateNotification((Notification) value, diagnostics, context);
		case ClassMakerPackage.NOTIFIER:
			return validateNotifier((Notifier) value, diagnostics, context);
		case ClassMakerPackage.COMPLETION_LISTENER:
			return validateCompletionListener((CompletionListener) value, diagnostics, context);
		case ClassMakerPackage.RESOURCE_ADAPTER:
			return validateResourceAdapter((ResourceAdapter) value, diagnostics, context);
		case ClassMakerPackage.CLASS_MAKER_SERVICE:
			return validateClassMakerService((ClassMakerService) value, diagnostics, context);
		case ClassMakerPackage.BLUEPRINT:
			return validateBlueprint((Blueprint) value, diagnostics, context);
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER:
			return validateCompletionNotificationAdapter((CompletionNotificationAdapter) value, diagnostics, context);
		case ClassMakerPackage.WORKER:
			return validateWorker((Worker) value, diagnostics, context);
		case ClassMakerPackage.SELECT_REVEAL_HANDLER:
			return validateSelectRevealHandler((SelectRevealHandler) value, diagnostics, context);
		case ClassMakerPackage.STAGE:
			return validateStage((Stage) value, diagnostics, context);
		case ClassMakerPackage.PROPERTIES:
			return validateProperties((Properties) value, diagnostics, context);
		case ClassMakerPackage.IPROGRESS_MONITOR:
			return validateIProgressMonitor((IProgressMonitor) value, diagnostics, context);
		case ClassMakerPackage.OS_GI_VERSION:
			return validateOSGiVersion((Version) value, diagnostics, context);
		case ClassMakerPackage.SEMAPHORE:
			return validateSemaphore((Semaphore) value, diagnostics, context);
		case ClassMakerPackage.CORE_EXCEPTION:
			return validateCoreException((CoreException) value, diagnostics, context);
		case ClassMakerPackage.INVOCATION_TARGET_EXCEPTION:
			return validateInvocationTargetException((InvocationTargetException) value, diagnostics, context);
		case ClassMakerPackage.ISTATUS:
			return validateIStatus((IStatus) value, diagnostics, context);
		case ClassMakerPackage.URI:
			return validateURI((URI) value, diagnostics, context);
		case ClassMakerPackage.NAME:
			return validateName((String) value, diagnostics, context);
		case ClassMakerPackage.EXCEPTION:
			return validateException((Exception) value, diagnostics, context);
		case ClassMakerPackage.LOCALE:
			return validateLocale((Locale) value, diagnostics, context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateContribution(Contribution contribution, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(contribution, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateRevision(Revision revision, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(revision, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateState(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(state, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateStrategy(Strategy strategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(strategy, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateItem(Item item, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(item, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAdapter(Adapter adapter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) adapter, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateWorkspace(Workspace workspace, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(workspace, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateLongToStateMapEntry(Map.Entry<?, ?> longToStateMapEntry, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) longToStateMapEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateVersionToRevisionMapEntry(Map.Entry<?, ?> versionToRevisionMapEntry,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) versionToRevisionMapEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateISchedulingRule(ISchedulingRule iSchedulingRule, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) iSchedulingRule, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateExecutor(Executor executor, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) executor, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateFuture(Future<?> future, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) future, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIAdapterFactory(IAdapterFactory iAdapterFactory, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) iAdapterFactory, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCustomizer(Customizer customizer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(customizer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateStageQualifier(StageQualifier stageQualifier, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stageQualifier, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateStageQualifierToCustomizerMapEntry(Map.Entry<?, ?> stageQualifierToCustomizerMapEntry,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) stageQualifierToCustomizerMapEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateModels(Models models, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(models, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEMFPlugin(EMFPlugin emfPlugin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) emfPlugin, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSCMOperator(SCMOperator<?> scmOperator, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scmOperator, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSCMRegistry(SCMRegistry<?> scmRegistry, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scmRegistry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateProject(Project project, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(project, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean validateResource(Resource resource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateResourceChangeListener(ResourceChangeListener resourceChangeListener,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(resourceChangeListener, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateNotification(Notification notification, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) notification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateNotifier(Notifier notifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) notifier, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCompletionListener(CompletionListener completionListener, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(completionListener, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateResourceAdapter(ResourceAdapter resourceAdapter, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(resourceAdapter, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateClassMakerService(ClassMakerService classMakerService, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classMakerService, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateBlueprint(Blueprint blueprint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(blueprint, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCompletionNotificationAdapter(CompletionNotificationAdapter completionNotificationAdapter,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(completionNotificationAdapter, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateWorker(Worker worker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) worker, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSelectRevealHandler(SelectRevealHandler selectRevealHandler, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(selectRevealHandler, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateStage(Stage stage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateProperties(Properties properties, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIProgressMonitor(IProgressMonitor iProgressMonitor, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOSGiVersion(Version osGiVersion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSemaphore(Semaphore semaphore, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCoreException(CoreException coreException, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateInvocationTargetException(InvocationTargetException invocationTargetException,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIStatus(IStatus iStatus, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateURI(URI uri, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateName(String name, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateName_Pattern(name, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @see #validateName_Pattern
	 */
	public static final PatternMatcher[][] NAME__PATTERN__VALUES = new PatternMatcher[][] {
			new PatternMatcher[] { XMLTypeUtil.createPatternMatcher("[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*") } };

	/**
	 * Validates the Pattern constraint of '<em>Name</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateName_Pattern(String name, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(ClassMakerPackage.Literals.NAME, name, NAME__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateException(Exception exception, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateLocale(Locale locale, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this
	 * validator's diagnostics. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this
		// validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} // ClassMakerValidator
