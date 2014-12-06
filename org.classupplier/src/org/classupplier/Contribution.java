/**
 */
package org.classupplier;

import java.util.Date;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classupplier.Contribution#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.classupplier.Contribution#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classupplier.ClassSupplierPackage#getContribution()
 * @model
 * @generated
 */
public interface Contribution extends State {
	/**
	 * Returns the value of the '<em><b>Snapshots</b></em>' map.
	 * The key is of type {@link java.util.Date},
	 * and the value is of type {@link org.classupplier.State},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Snapshots</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Snapshots</em>' map.
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Snapshots()
	 * @model mapType="org.classupplier.DateToStateMapEntry<org.eclipse.emf.ecore.EDate, org.classupplier.State>"
	 * @generated
	 */
	EMap<Date, State> getSnapshots();

	/**
	 * Returns the value of the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' reference.
	 * @see org.classupplier.ClassSupplierPackage#getContribution_State()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	State getState();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void apply(Date version);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	State newState();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setName(String newName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.classupplier.Version"
	 * @generated
	 */
	Version getVersion();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model versionDataType="org.classupplier.Version"
	 * @generated
	 */
	void setVersion(Version version);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Phase getStage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setStage(Phase newStage);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getProjectName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setProjectName(String newProjectName);

} // Contribution
