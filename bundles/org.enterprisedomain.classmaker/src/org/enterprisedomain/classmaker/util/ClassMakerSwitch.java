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
package org.enterprisedomain.classmaker.util;

import java.util.Map;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Switch;
import org.enterprisedomain.classmaker.*;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerPlant;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
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
public class ClassMakerSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static ClassMakerPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ClassMakerSwitch() {
		if (modelPackage == null) {
			modelPackage = ClassMakerPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param ePackage
	 *            the package in question.
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
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case ClassMakerPackage.CONTRIBUTION: {
			Contribution contribution = (Contribution) theEObject;
			T result = caseContribution(contribution);
			if (result == null)
				result = caseProject(contribution);
			if (result == null)
				result = caseItem(contribution);
			if (result == null)
				result = caseISchedulingRule(contribution);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.REVISION: {
			Revision revision = (Revision) theEObject;
			T result = caseRevision(revision);
			if (result == null)
				result = caseItem(revision);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.STATE: {
			State state = (State) theEObject;
			T result = caseState(state);
			if (result == null)
				result = caseItem(state);
			if (result == null)
				result = caseISchedulingRule(state);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.ITEM: {
			Item item = (Item) theEObject;
			T result = caseItem(item);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.ADAPTER: {
			Adapter adapter = (Adapter) theEObject;
			T result = caseAdapter(adapter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.WORKSPACE: {
			Workspace workspace = (Workspace) theEObject;
			T result = caseWorkspace(workspace);
			if (result == null)
				result = caseISchedulingRule(workspace);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.INTEGER_TO_STATE_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<Integer, State> integerToStateMapEntry = (Map.Entry<Integer, State>) theEObject;
			T result = caseIntegerToStateMapEntry(integerToStateMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.VERSION_TO_REVISION_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<Version, Revision> versionToRevisionMapEntry = (Map.Entry<Version, Revision>) theEObject;
			T result = caseVersionToRevisionMapEntry(versionToRevisionMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.ISCHEDULING_RULE: {
			ISchedulingRule iSchedulingRule = (ISchedulingRule) theEObject;
			T result = caseISchedulingRule(iSchedulingRule);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.CUSTOMIZER: {
			Customizer customizer = (Customizer) theEObject;
			T result = caseCustomizer(customizer);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.STAGE_QUALIFIER: {
			StageQualifier stageQualifier = (StageQualifier) theEObject;
			T result = caseStageQualifier(stageQualifier);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<StageQualifier, Customizer> stageQualifierToCustomizerMapEntry = (Map.Entry<StageQualifier, Customizer>) theEObject;
			T result = caseStageQualifierToCustomizerMapEntry(stageQualifierToCustomizerMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.PROJECT: {
			Project project = (Project) theEObject;
			T result = caseProject(project);
			if (result == null)
				result = caseISchedulingRule(project);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.MODEL_PAIR: {
			ModelPair modelPair = (ModelPair) theEObject;
			T result = caseModelPair(modelPair);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.RESOURCE: {
			Resource resource = (Resource) theEObject;
			T result = caseResource(resource);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.COMPLETION_LISTENER: {
			CompletionListener completionListener = (CompletionListener) theEObject;
			T result = caseCompletionListener(completionListener);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.RESOURCE_ADAPTER: {
			ResourceAdapter resourceAdapter = (ResourceAdapter) theEObject;
			T result = caseResourceAdapter(resourceAdapter);
			if (result == null)
				result = caseAdapter(resourceAdapter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassMakerPackage.CLASS_MAKER_PLANT: {
			ClassMakerPlant classMakerPlant = (ClassMakerPlant) theEObject;
			T result = caseClassMakerPlant(classMakerPlant);
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
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContribution(Contribution object) {
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
	public T caseRevision(Revision object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseState(State object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Item</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseItem(Item object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adapter</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdapter(Adapter object) {
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
	public T caseWorkspace(Workspace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer To State Map Entry</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer To State Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerToStateMapEntry(Map.Entry<Integer, State> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Version To Revision Map Entry</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Version To Revision Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVersionToRevisionMapEntry(Map.Entry<Version, Revision> object) {
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
	public T caseISchedulingRule(ISchedulingRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Customizer</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Customizer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomizer(Customizer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stage Qualifier</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stage Qualifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStageQualifier(StageQualifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stage Qualifier To Customizer Map Entry</em>'.
	 * <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stage Qualifier To Customizer Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStageQualifierToCustomizerMapEntry(Map.Entry<StageQualifier, Customizer> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Project</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Project</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProject(Project object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Pair</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Pair</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelPair(ModelPair object) {
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
	public T caseResource(Resource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Completion Listener</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Completion Listener</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompletionListener(CompletionListener object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Adapter</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceAdapter(ResourceAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassMakerPlant(ClassMakerPlant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // ClassMakerSwitch
