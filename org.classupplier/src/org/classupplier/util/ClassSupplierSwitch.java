/**
 */
package org.classupplier.util;

import java.util.Map;

import org.classupplier.ClassSupplierPackage;
import org.classupplier.Contribution;
import org.classupplier.Customizer;
import org.classupplier.PhaseQualifier;
import org.classupplier.State;
import org.classupplier.Workspace;
import org.classupplier.impl.Constructable;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.equinox.concurrent.future.IFuture;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see org.classupplier.ClassSupplierPackage
 * @generated
 */
public class ClassSupplierSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static ClassSupplierPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public ClassSupplierSwitch() {
		if (modelPackage == null) {
			modelPackage = ClassSupplierPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @parameter ePackage the package in question.
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
		case ClassSupplierPackage.CONTRIBUTION: {
			Contribution contribution = (Contribution) theEObject;
			T1 result = caseContribution(contribution);
			if (result == null)
				result = caseConstructable(contribution);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.STATE: {
			State state = (State) theEObject;
			T1 result = caseState(state);
			if (result == null)
				result = caseConstructable(state);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.WORKSPACE: {
			Workspace workspace = (Workspace) theEObject;
			T1 result = caseWorkspace(workspace);
			if (result == null)
				result = caseISchedulingRule(workspace);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.VERSION_TO_STATE_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<Version, State> versionToStateMapEntry = (Map.Entry<Version, State>) theEObject;
			T1 result = caseVersionToStateMapEntry(versionToStateMapEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.ISCHEDULING_RULE: {
			ISchedulingRule iSchedulingRule = (ISchedulingRule) theEObject;
			T1 result = caseISchedulingRule(iSchedulingRule);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.IFUTURE: {
			IFuture<?> iFuture = (IFuture<?>) theEObject;
			T1 result = caseIFuture(iFuture);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.CONSTRUCTABLE: {
			Constructable constructable = (Constructable) theEObject;
			T1 result = caseConstructable(constructable);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.ELIST: {
			EList<?> eList = (EList<?>) theEObject;
			T1 result = caseEList(eList);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.CUSTOMIZER: {
			Customizer customizer = (Customizer) theEObject;
			T1 result = caseCustomizer(customizer);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.PHASE_QUALIFIER: {
			PhaseQualifier phaseQualifier = (PhaseQualifier) theEObject;
			T1 result = casePhaseQualifier(phaseQualifier);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ClassSupplierPackage.PHASE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<PhaseQualifier, Customizer> phaseQualifierToCustomizerMapEntry = (Map.Entry<PhaseQualifier, Customizer>) theEObject;
			T1 result = casePhaseQualifierToCustomizerMapEntry(phaseQualifierToCustomizerMapEntry);
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
	public T1 caseContribution(Contribution object) {
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
	public T1 caseState(State object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Version To State Map Entry</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Version To State Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseVersionToStateMapEntry(Map.Entry<Version, State> object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>IFuture</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IFuture</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <ResultType> T1 caseIFuture(IFuture<ResultType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constructable</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constructable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseConstructable(Constructable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EList</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EList</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseEList(EList<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Customizer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Customizer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCustomizer(Customizer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Phase Qualifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Phase Qualifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePhaseQualifier(PhaseQualifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Phase Qualifier To Customizer Map Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Phase Qualifier To Customizer Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePhaseQualifierToCustomizerMapEntry(Map.Entry<PhaseQualifier, Customizer> object) {
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
	public T1 defaultCase(EObject object) {
		return null;
	}

} // ClassSupplierSwitch
