/**
 * Copyright 2012-2018 Kyrill Zotkin
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

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Switch;
import org.enterprisedomain.classmaker.*;
import org.enterprisedomain.classmaker.Blueprint;
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
import org.enterprisedomain.classmaker.SelectRevealHandler;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Strategy;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see org.enterprisedomain.classmaker.ClassMakerPackage
 * @generated
 */
public class ClassMakerSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static ClassMakerPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public ClassMakerSwitch() {
		if (modelPackage == null) {
			modelPackage = ClassMakerPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case ClassMakerPackage.CONTRIBUTION: {
			Contribution contribution = (Contribution) theEObject;
			T1 result = caseContribution(contribution);
			if (result == null)
				result = caseProject(contribution);
			if (result == null)
				result = caseISchedulingRule(contribution);
			if (result == null)
				result = caseItem(contribution);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.REVISION: {
			Revision revision = (Revision) theEObject;
			T1 result = caseRevision(revision);
			if (result == null)
				result = caseItem(revision);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.STATE: {
			State state = (State) theEObject;
			T1 result = caseState(state);
			if (result == null)
				result = caseItem(state);
			if (result == null)
				result = caseISchedulingRule(state);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.STRATEGY: {
			Strategy strategy = (Strategy) theEObject;
			T1 result = caseStrategy(strategy);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.ITEM: {
			Item item = (Item) theEObject;
			T1 result = caseItem(item);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.ADAPTER: {
			Adapter adapter = (Adapter) theEObject;
			T1 result = caseAdapter(adapter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.WORKSPACE: {
			Workspace workspace = (Workspace) theEObject;
			T1 result = caseWorkspace(workspace);
			if (result == null)
				result = caseISchedulingRule(workspace);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.LONG_TO_STATE_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<Long, State> longToStateMapEntry = (Map.Entry<Long, State>) theEObject;
			T1 result = caseLongToStateMapEntry(longToStateMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.VERSION_TO_REVISION_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<Version, Revision> versionToRevisionMapEntry = (Map.Entry<Version, Revision>) theEObject;
			T1 result = caseVersionToRevisionMapEntry(versionToRevisionMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.ISCHEDULING_RULE: {
			ISchedulingRule iSchedulingRule = (ISchedulingRule) theEObject;
			T1 result = caseISchedulingRule(iSchedulingRule);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.EXECUTOR: {
			Executor executor = (Executor) theEObject;
			T1 result = caseExecutor(executor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.FUTURE: {
			Future<?> future = (Future<?>) theEObject;
			T1 result = caseFuture(future);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.IADAPTER_FACTORY: {
			IAdapterFactory iAdapterFactory = (IAdapterFactory) theEObject;
			T1 result = caseIAdapterFactory(iAdapterFactory);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.CUSTOMIZER: {
			Customizer customizer = (Customizer) theEObject;
			T1 result = caseCustomizer(customizer);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.STAGE_QUALIFIER: {
			StageQualifier stageQualifier = (StageQualifier) theEObject;
			T1 result = caseStageQualifier(stageQualifier);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<StageQualifier, Customizer> stageQualifierToCustomizerMapEntry = (Map.Entry<StageQualifier, Customizer>) theEObject;
			T1 result = caseStageQualifierToCustomizerMapEntry(stageQualifierToCustomizerMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.MODEL_PAIR: {
			ModelPair modelPair = (ModelPair) theEObject;
			T1 result = caseModelPair(modelPair);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.SCM_OPERATOR: {
			SCMOperator<?> scmOperator = (SCMOperator<?>) theEObject;
			T1 result = caseSCMOperator(scmOperator);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.SCM_REGISTRY: {
			SCMRegistry<?> scmRegistry = (SCMRegistry<?>) theEObject;
			T1 result = caseSCMRegistry(scmRegistry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.PROJECT: {
			Project project = (Project) theEObject;
			T1 result = caseProject(project);
			if (result == null)
				result = caseISchedulingRule(project);
			if (result == null)
				result = caseItem(project);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.RESOURCE: {
			Resource resource = (Resource) theEObject;
			T1 result = caseResource(resource);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.RESOURCE_CHANGE_LISTENER: {
			ResourceChangeListener resourceChangeListener = (ResourceChangeListener) theEObject;
			T1 result = caseResourceChangeListener(resourceChangeListener);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.NOTIFICATION: {
			Notification notification = (Notification) theEObject;
			T1 result = caseNotification(notification);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.COMPLETION_LISTENER: {
			CompletionListener completionListener = (CompletionListener) theEObject;
			T1 result = caseCompletionListener(completionListener);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.RESOURCE_ADAPTER: {
			ResourceAdapter resourceAdapter = (ResourceAdapter) theEObject;
			T1 result = caseResourceAdapter(resourceAdapter);
			if (result == null)
				result = caseAdapter(resourceAdapter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.CLASS_MAKER_SERVICE: {
			ClassMakerService classMakerService = (ClassMakerService) theEObject;
			T1 result = caseClassMakerService(classMakerService);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.BLUEPRINT: {
			Blueprint blueprint = (Blueprint) theEObject;
			T1 result = caseBlueprint(blueprint);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER: {
			CompletionNotificationAdapter completionNotificationAdapter = (CompletionNotificationAdapter) theEObject;
			T1 result = caseCompletionNotificationAdapter(completionNotificationAdapter);
			if (result == null)
				result = caseAdapter(completionNotificationAdapter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.WORKER: {
			Worker worker = (Worker) theEObject;
			T1 result = caseWorker(worker);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.SELECT_REVEAL_HANDLER: {
			SelectRevealHandler selectRevealHandler = (SelectRevealHandler) theEObject;
			T1 result = caseSelectRevealHandler(selectRevealHandler);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contribution</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseContribution(Contribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Revision</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Revision</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRevision(Revision object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseState(State object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Strategy</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Strategy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStrategy(Strategy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Item</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseItem(Item object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adapter</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAdapter(Adapter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workspace</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workspace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseWorkspace(Workspace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long To State Map Entry</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long To State Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLongToStateMapEntry(Map.Entry<Long, State> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Version To Revision Map Entry</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Version To Revision Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseVersionToRevisionMapEntry(Map.Entry<Version, Revision> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IScheduling Rule</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IScheduling Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseISchedulingRule(ISchedulingRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Executor</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Executor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseExecutor(Executor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Future</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Future</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <V> T1 caseFuture(Future<V> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IAdapter Factory</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IAdapter Factory</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIAdapterFactory(IAdapterFactory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Customizer</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Customizer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCustomizer(Customizer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stage Qualifier</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stage Qualifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStageQualifier(StageQualifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stage Qualifier To Customizer Map Entry</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the
	 * switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stage Qualifier To Customizer Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStageQualifierToCustomizerMapEntry(Map.Entry<StageQualifier, Customizer> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Project</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Project</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProject(Project object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Pair</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Pair</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseModelPair(ModelPair object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SCM Operator</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SCM Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseSCMOperator(SCMOperator<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SCM Registry</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SCM Registry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseSCMRegistry(SCMRegistry<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResource(Resource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Change Listener</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Change Listener</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResourceChangeListener(ResourceChangeListener object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Notification</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Notification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNotification(Notification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Completion Listener</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Completion Listener</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCompletionListener(CompletionListener object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Worker</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Worker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseWorker(Worker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Select Reveal Handler</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Select Reveal Handler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSelectRevealHandler(SelectRevealHandler object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Adapter</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResourceAdapter(ResourceAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseClassMakerService(ClassMakerService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Blueprint</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Blueprint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBlueprint(Blueprint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Completion Notification Adapter</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the
	 * switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Completion Notification Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCompletionNotificationAdapter(CompletionNotificationAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last
	 * case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T1 defaultCase(EObject object) {
		return null;
	}

} // ClassMakerSwitch
