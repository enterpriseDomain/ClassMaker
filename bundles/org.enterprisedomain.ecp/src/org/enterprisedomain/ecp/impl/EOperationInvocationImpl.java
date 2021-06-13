/**
 */
package org.enterprisedomain.ecp.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.enterprisedomain.ecp.ECPPackage;
import org.enterprisedomain.ecp.EOperationInvocation;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>EOperation Invocation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.ecp.impl.EOperationInvocationImpl#getEOperation
 * <em>EOperation</em>}</li>
 * <li>{@link org.enterprisedomain.ecp.impl.EOperationInvocationImpl#getArguments
 * <em>Arguments</em>}</li>
 * <li>{@link org.enterprisedomain.ecp.impl.EOperationInvocationImpl#getResult
 * <em>Result</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EOperationInvocationImpl extends MinimalEObjectImpl.Container implements EOperationInvocation {
	public class Adapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			super.notifyChanged(msg);
			if (msg.getFeatureID(EOperationInvocation.class) == ECPPackage.EOPERATION_INVOCATION__EOPERATION
					&& msg.getEventType() == Notification.SET && msg.getNewValue() != null) {
				getArguments().clear();
				getArguments().keySet().addAll(((EOperation) msg.getNewValue()).getEParameters());
			}
		}

	}

	/**
	 * The cached value of the '{@link #getEOperation() <em>EOperation</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEOperation()
	 * @generated
	 * @ordered
	 */
	protected EOperation eOperation;

	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EMap<EParameter, Object> arguments;

	/**
	 * The default value of the '{@link #getResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected static final Object RESULT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected Object result = RESULT_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected EOperationInvocationImpl() {
		super();
		eAdapters().add(new Adapter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ECPPackage.Literals.EOPERATION_INVOCATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EOperation getEOperation() {
		if (eOperation != null && eOperation.eIsProxy()) {
			InternalEObject oldEOperation = (InternalEObject) eOperation;
			eOperation = (EOperation) eResolveProxy(oldEOperation);
			if (eOperation != oldEOperation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ECPPackage.EOPERATION_INVOCATION__EOPERATION, oldEOperation, eOperation));
			}
		}
		return eOperation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EOperation basicGetEOperation() {
		return eOperation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEOperation(EOperation newEOperation) {
		EOperation oldEOperation = eOperation;
		eOperation = newEOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ECPPackage.EOPERATION_INVOCATION__EOPERATION,
					oldEOperation, eOperation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EMap<EParameter, Object> getArguments() {
		if (arguments == null) {
			arguments = new EcoreEMap<EParameter, Object>(ECPPackage.Literals.EPARAMETER_TO_OBJECT_MAP_ENTRY,
					EParameterToObjectMapEntryImpl.class, this, ECPPackage.EOPERATION_INVOCATION__ARGUMENTS);
		}
		return arguments;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResult(Object newResult) {
		Object oldResult = result;
		result = newResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ECPPackage.EOPERATION_INVOCATION__RESULT, oldResult,
					result));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void execute() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ECPPackage.EOPERATION_INVOCATION__ARGUMENTS:
			return ((InternalEList<?>) getArguments()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ECPPackage.EOPERATION_INVOCATION__EOPERATION:
			if (resolve)
				return getEOperation();
			return basicGetEOperation();
		case ECPPackage.EOPERATION_INVOCATION__ARGUMENTS:
			if (coreType)
				return getArguments();
			else
				return getArguments().map();
		case ECPPackage.EOPERATION_INVOCATION__RESULT:
			return getResult();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ECPPackage.EOPERATION_INVOCATION__EOPERATION:
			setEOperation((EOperation) newValue);
			return;
		case ECPPackage.EOPERATION_INVOCATION__ARGUMENTS:
			((EStructuralFeature.Setting) getArguments()).set(newValue);
			return;
		case ECPPackage.EOPERATION_INVOCATION__RESULT:
			setResult(newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ECPPackage.EOPERATION_INVOCATION__EOPERATION:
			setEOperation((EOperation) null);
			return;
		case ECPPackage.EOPERATION_INVOCATION__ARGUMENTS:
			getArguments().clear();
			return;
		case ECPPackage.EOPERATION_INVOCATION__RESULT:
			setResult(RESULT_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ECPPackage.EOPERATION_INVOCATION__EOPERATION:
			return eOperation != null;
		case ECPPackage.EOPERATION_INVOCATION__ARGUMENTS:
			return arguments != null && !arguments.isEmpty();
		case ECPPackage.EOPERATION_INVOCATION__RESULT:
			return RESULT_EDEFAULT == null ? result != null : !RESULT_EDEFAULT.equals(result);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
		case ECPPackage.EOPERATION_INVOCATION___EXECUTE:
			execute();
			return null;
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (result: ");
		result.append(result);
		result.append(')');
		return result.toString();
	}

} // EOperationInvocationImpl
