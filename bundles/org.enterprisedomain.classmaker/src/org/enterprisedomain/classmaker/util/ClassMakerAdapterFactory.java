/**
 * Copyright 2012-2022 Kyrill Zotkin
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
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Strategy;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.enterprisedomain.classmaker.ClassMakerPackage
 * @generated
 */
public class ClassMakerAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static ClassMakerPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ClassMakerAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ClassMakerPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!--
	 * begin-user-doc --> This implementation returns <code>true</code> if the
	 * object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ClassMakerSwitch<Adapter> modelSwitch = new ClassMakerSwitch<Adapter>() {
		@Override
		public Adapter caseContribution(Contribution object) {
			return createContributionAdapter();
		}

		@Override
		public Adapter caseRevision(Revision object) {
			return createRevisionAdapter();
		}

		@Override
		public Adapter caseState(State object) {
			return createStateAdapter();
		}

		@Override
		public Adapter caseStrategy(Strategy object) {
			return createStrategyAdapter();
		}

		@Override
		public Adapter caseItem(Item object) {
			return createItemAdapter();
		}

		@Override
		public Adapter caseAdapter(Adapter object) {
			return createAdapterAdapter();
		}

		@Override
		public Adapter caseWorkspace(Workspace object) {
			return createWorkspaceAdapter();
		}

		@Override
		public Adapter caseLongToStateMapEntry(Map.Entry<Long, State> object) {
			return createLongToStateMapEntryAdapter();
		}

		@Override
		public Adapter caseVersionToRevisionMapEntry(Map.Entry<Version, Revision> object) {
			return createVersionToRevisionMapEntryAdapter();
		}

		@Override
		public Adapter caseISchedulingRule(ISchedulingRule object) {
			return createISchedulingRuleAdapter();
		}

		@Override
		public Adapter caseExecutor(Executor object) {
			return createExecutorAdapter();
		}

		@Override
		public <V> Adapter caseFuture(Future<V> object) {
			return createFutureAdapter();
		}

		@Override
		public Adapter caseIAdapterFactory(IAdapterFactory object) {
			return createIAdapterFactoryAdapter();
		}

		@Override
		public Adapter caseCustomizer(Customizer object) {
			return createCustomizerAdapter();
		}

		@Override
		public Adapter caseStageQualifier(StageQualifier object) {
			return createStageQualifierAdapter();
		}

		@Override
		public Adapter caseStageQualifierToCustomizerMapEntry(Map.Entry<StageQualifier, Customizer> object) {
			return createStageQualifierToCustomizerMapEntryAdapter();
		}

		@Override
		public Adapter caseModels(Models object) {
			return createModelsAdapter();
		}

		@Override
		public <T> Adapter caseSCMOperator(SCMOperator<T> object) {
			return createSCMOperatorAdapter();
		}

		@Override
		public <T> Adapter caseSCMRegistry(SCMRegistry<T> object) {
			return createSCMRegistryAdapter();
		}

		@Override
		public Adapter caseProject(Project object) {
			return createProjectAdapter();
		}

		@Override
		public Adapter caseResource(Resource object) {
			return createResourceAdapter();
		}

		@Override
		public Adapter caseResourceChangeListener(ResourceChangeListener object) {
			return createResourceChangeListenerAdapter();
		}

		@Override
		public Adapter caseNotification(Notification object) {
			return createNotificationAdapter();
		}

		@Override
		public Adapter caseNotifier(Notifier object) {
			return createNotifierAdapter();
		}

		@Override
		public Adapter caseCompletionListener(CompletionListener object) {
			return createCompletionListenerAdapter();
		}

		@Override
		public Adapter caseResourceAdapter(ResourceAdapter object) {
			return createResourceAdapterAdapter();
		}

		@Override
		public Adapter caseClassMakerService(ClassMakerService object) {
			return createClassMakerServiceAdapter();
		}

		@Override
		public Adapter caseBlueprint(Blueprint object) {
			return createBlueprintAdapter();
		}

		@Override
		public Adapter caseCompletionNotificationAdapter(CompletionNotificationAdapter object) {
			return createCompletionNotificationAdapterAdapter();
		}

		@Override
		public Adapter caseWorker(Worker object) {
			return createWorkerAdapter();
		}

		@Override
		public Adapter caseSelectRevealHandler(SelectRevealHandler object) {
			return createSelectRevealHandlerAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.Contribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.Contribution
	 * @generated
	 */
	public Adapter createContributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.Revision <em>Revision</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.Revision
	 * @generated
	 */
	public Adapter createRevisionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.State <em>State</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.State
	 * @generated
	 */
	public Adapter createStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.Strategy <em>Strategy</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.Strategy
	 * @generated
	 */
	public Adapter createStrategyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.Item <em>Item</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.Item
	 * @generated
	 */
	public Adapter createItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.eclipse.emf.common.notify.Adapter <em>Adapter</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @generated
	 */
	public Adapter createAdapterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.Workspace <em>Workspace</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.Workspace
	 * @generated
	 */
	public Adapter createWorkspaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry
	 * <em>Long To State Map Entry</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createLongToStateMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry
	 * <em>Version To Revision Map Entry</em>}'. <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createVersionToRevisionMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling
	 * Rule</em>}'. <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
	 * @generated
	 */
	public Adapter createISchedulingRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link java.util.concurrent.Executor <em>Executor</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.concurrent.Executor
	 * @generated
	 */
	public Adapter createExecutorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link java.util.concurrent.Future <em>Future</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.concurrent.Future
	 * @generated
	 */
	public Adapter createFutureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.eclipse.core.runtime.IAdapterFactory <em>IAdapter Factory</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.core.runtime.IAdapterFactory
	 * @generated
	 */
	public Adapter createIAdapterFactoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.Customizer <em>Customizer</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.Customizer
	 * @generated
	 */
	public Adapter createCustomizerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.StageQualifier <em>Stage
	 * Qualifier</em>}'. <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.StageQualifier
	 * @generated
	 */
	public Adapter createStageQualifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry
	 * <em>Stage Qualifier To Customizer Map Entry</em>}'. <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStageQualifierToCustomizerMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.Models <em>Models</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.Models
	 * @generated
	 */
	public Adapter createModelsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.Project <em>Project</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.Project
	 * @generated
	 */
	public Adapter createProjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.SCMOperator <em>SCM Operator</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.SCMOperator
	 * @generated
	 */
	public Adapter createSCMOperatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.SCMRegistry <em>SCM Registry</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.SCMRegistry
	 * @generated
	 */
	public Adapter createSCMRegistryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.eclipse.emf.ecore.resource.Resource <em>Resource</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.resource.Resource
	 * @generated
	 */
	public Adapter createResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.ResourceChangeListener <em>Resource
	 * Change Listener</em>}'. <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.ResourceChangeListener
	 * @generated
	 */
	public Adapter createResourceChangeListenerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.eclipse.emf.common.notify.Notification <em>Notification</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.common.notify.Notification
	 * @generated
	 */
	public Adapter createNotificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.eclipse.emf.common.notify.Notifier <em>Notifier</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.common.notify.Notifier
	 * @generated
	 */
	public Adapter createNotifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.CompletionListener <em>Completion
	 * Listener</em>}'. <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.CompletionListener
	 * @generated
	 */
	public Adapter createCompletionListenerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.jobs.Worker <em>Worker</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.jobs.Worker
	 * @generated
	 */
	public Adapter createWorkerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.SelectRevealHandler <em>Select Reveal
	 * Handler</em>}'. <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.SelectRevealHandler
	 * @generated
	 */
	public Adapter createSelectRevealHandlerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.ResourceAdapter <em>Resource
	 * Adapter</em>}'. <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.ResourceAdapter
	 * @generated
	 */
	public Adapter createResourceAdapterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.ClassMakerService <em>Service</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.ClassMakerService
	 * @generated
	 */
	public Adapter createClassMakerServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.Blueprint <em>Blueprint</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.Blueprint
	 * @generated
	 */
	public Adapter createBlueprintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classmaker.CompletionNotificationAdapter
	 * <em>Completion Notification Adapter</em>}'. <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classmaker.CompletionNotificationAdapter
	 * @generated
	 */
	public Adapter createCompletionNotificationAdapterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // ClassMakerAdapterFactory
