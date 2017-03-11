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
package org.enterprisedomain.classsupplier.util;

import java.util.Map;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.enterprisedomain.classsupplier.*;
import org.enterprisedomain.classsupplier.ClassPlant;
import org.enterprisedomain.classsupplier.ClassSupplierPackage;
import org.enterprisedomain.classsupplier.CompletionListener;
import org.enterprisedomain.classsupplier.Contribution;
import org.enterprisedomain.classsupplier.Customizer;
import org.enterprisedomain.classsupplier.Item;
import org.enterprisedomain.classsupplier.ModelPair;
import org.enterprisedomain.classsupplier.Project;
import org.enterprisedomain.classsupplier.ResourceAdapter;
import org.enterprisedomain.classsupplier.Revision;
import org.enterprisedomain.classsupplier.StageQualifier;
import org.enterprisedomain.classsupplier.State;
import org.enterprisedomain.classsupplier.Workspace;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage
 * @generated
 */
public class ClassSupplierAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static ClassSupplierPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ClassSupplierAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ClassSupplierPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
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
	protected ClassSupplierSwitch<Adapter> modelSwitch = new ClassSupplierSwitch<Adapter>() {
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
		public Adapter caseIntegerToStateMapEntry(Map.Entry<Integer, State> object) {
			return createIntegerToStateMapEntryAdapter();
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
		public <ResultType> Adapter caseFuture(Future<ResultType> object) {
			return createFutureAdapter();
		}

		@Override
		public <K, V> Adapter caseEMap(EMap<K, V> object) {
			return createEMapAdapter();
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
		public Adapter caseProject(Project object) {
			return createProjectAdapter();
		}

		@Override
		public Adapter caseResourceSet(ResourceSet object) {
			return createResourceSetAdapter();
		}

		@Override
		public Adapter caseModelPair(ModelPair object) {
			return createModelPairAdapter();
		}

		@Override
		public Adapter caseResource(Resource object) {
			return createResourceAdapter();
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
		public Adapter caseClassPlant(ClassPlant object) {
			return createClassPlantAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.enterprisedomain.classsupplier.Contribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.Contribution
	 * @generated
	 */
	public Adapter createContributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.enterprisedomain.classsupplier.Revision <em>Revision</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.Revision
	 * @generated
	 */
	public Adapter createRevisionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classsupplier.State <em>State</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.State
	 * @generated
	 */
	public Adapter createStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classsupplier.Item <em>Item</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.Item
	 * @generated
	 */
	public Adapter createItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.eclipse.emf.common.notify.Adapter <em>Adapter</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
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
	 * '{@link org.enterprisedomain.classsupplier.Workspace
	 * <em>Workspace</em>}'. <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a
	 * case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.Workspace
	 * @generated
	 */
	public Adapter createWorkspaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Integer To State Map Entry</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createIntegerToStateMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Version To Revision Map Entry</em>}'.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createVersionToRevisionMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling Rule</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
	 * @generated
	 */
	public Adapter createISchedulingRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link java.util.concurrent.Future <em>Future</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
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
	 * '{@link org.eclipse.emf.common.util.EMap <em>EMap</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.common.util.EMap
	 * @generated
	 */
	public Adapter createEMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.enterprisedomain.classsupplier.Customizer <em>Customizer</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.Customizer
	 * @generated
	 */
	public Adapter createCustomizerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classsupplier.StageQualifier <em>Stage
	 * Qualifier</em>}'. <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a
	 * case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.StageQualifier
	 * @generated
	 */
	public Adapter createStageQualifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Stage Qualifier To Customizer Map Entry</em>}'.
	 * <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStageQualifierToCustomizerMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.enterprisedomain.classsupplier.Project <em>Project</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.Project
	 * @generated
	 */
	public Adapter createProjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.resource.ResourceSet <em>Resource Set</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.resource.ResourceSet
	 * @generated
	 */
	public Adapter createResourceSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.enterprisedomain.classsupplier.ModelPair <em>Model Pair</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.ModelPair
	 * @generated
	 */
	public Adapter createModelPairAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.eclipse.emf.ecore.resource.Resource <em>Resource</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.resource.Resource
	 * @generated
	 */
	public Adapter createResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.enterprisedomain.classsupplier.CompletionListener <em>Completion Listener</em>}'.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.CompletionListener
	 * @generated
	 */
	public Adapter createCompletionListenerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class
	 * '{@link org.enterprisedomain.classsupplier.ResourceAdapter <em>Resource
	 * Adapter</em>}'. <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a
	 * case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.ResourceAdapter
	 * @generated
	 */
	public Adapter createResourceAdapterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.enterprisedomain.classsupplier.ClassPlant <em>Class Plant</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.enterprisedomain.classsupplier.ClassPlant
	 * @generated
	 */
	public Adapter createClassPlantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // ClassSupplierAdapterFactory
