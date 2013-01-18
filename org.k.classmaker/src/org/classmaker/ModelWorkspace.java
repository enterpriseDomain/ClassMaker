/**
 */
package org.classmaker;

import org.eclipse.emf.common.notify.Adapter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Workspace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classmaker.ModelWorkspace#getContents <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classmaker.ClassMakerPackage#getModelWorkspace()
 * @model
 * @generated
 */
public interface ModelWorkspace extends EObject {
	/**
	 * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
	 * The list contents are of type {@link org.classmaker.Bundle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' containment reference list.
	 * @see org.classmaker.ClassMakerPackage#getModelWorkspace_Contents()
	 * @model containment="true"
	 * @generated
	 */
	EList<Bundle> getContents();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model listenerDataType="org.classmaker.Adapter"
	 * @generated
	 */
	void addRefreshListener(Adapter listener);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model listenerDataType="org.classmaker.Adapter"
	 * @generated
	 */
	void removeRefreshListener(Adapter listener);

} // ModelWorkspace
