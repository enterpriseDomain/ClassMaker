/**
 */
package org.classsupplier;

import org.eclipse.core.runtime.IStatus;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classsupplier.Artifact#getName <em>Name</em>}</li>
 *   <li>{@link org.classsupplier.Artifact#getEPackage <em>EPackage</em>}</li>
 *   <li>{@link org.classsupplier.Artifact#getPrototypeEPackage <em>Prototype EPackage</em>}</li>
 *   <li>{@link org.classsupplier.Artifact#getVersion <em>Version</em>}</li>
 *   <li>{@link org.classsupplier.Artifact#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link org.classsupplier.Artifact#getState <em>State</em>}</li>
 *   <li>{@link org.classsupplier.Artifact#getStatus <em>Status</em>}</li>
 *   <li>{@link org.classsupplier.Artifact#getLoadedEPackage <em>Loaded EPackage</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classsupplier.ClassSupplierPackage#getArtifact()
 * @model
 * @generated
 */
public interface Artifact extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.classsupplier.ClassSupplierPackage#getArtifact_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.classsupplier.Artifact#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EPackage</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EPackage</em>' reference.
	 * @see org.classsupplier.ClassSupplierPackage#getArtifact_EPackage()
	 * @model changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EPackage getEPackage();

	/**
	 * Returns the value of the '<em><b>Prototype EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prototype EPackage</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prototype EPackage</em>' reference.
	 * @see #setPrototypeEPackage(EPackage)
	 * @see org.classsupplier.ClassSupplierPackage#getArtifact_PrototypeEPackage()
	 * @model
	 * @generated
	 */
	EPackage getPrototypeEPackage();

	/**
	 * Sets the value of the '{@link org.classsupplier.Artifact#getPrototypeEPackage <em>Prototype EPackage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prototype EPackage</em>' reference.
	 * @see #getPrototypeEPackage()
	 * @generated
	 */
	void setPrototypeEPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see org.classsupplier.ClassSupplierPackage#getArtifact_Version()
	 * @model dataType="org.classsupplier.Version"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Sets the value of the '{@link org.classsupplier.Artifact#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Version value);

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.classsupplier.ClassSupplierPackage#getArtifact_ProjectName()
	 * @model
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the '{@link org.classsupplier.Artifact#getProjectName <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The literals are from the enumeration {@link org.classsupplier.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see org.classsupplier.State
	 * @see #setState(State)
	 * @see org.classsupplier.ClassSupplierPackage#getArtifact_State()
	 * @model
	 * @generated
	 */
	State getState();

	/**
	 * Sets the value of the '{@link org.classsupplier.Artifact#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see org.classsupplier.State
	 * @see #getState()
	 * @generated
	 */
	void setState(State value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(IStatus)
	 * @see org.classsupplier.ClassSupplierPackage#getArtifact_Status()
	 * @model dataType="org.classsupplier.IStatus"
	 * @generated
	 */
	IStatus getStatus();

	/**
	 * Sets the value of the '{@link org.classsupplier.Artifact#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(IStatus value);

	/**
	 * Returns the value of the '<em><b>Loaded EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loaded EPackage</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loaded EPackage</em>' reference.
	 * @see #setLoadedEPackage(EPackage)
	 * @see org.classsupplier.ClassSupplierPackage#getArtifact_LoadedEPackage()
	 * @model
	 * @generated
	 */
	EPackage getLoadedEPackage();

	/**
	 * Sets the value of the '{@link org.classsupplier.Artifact#getLoadedEPackage <em>Loaded EPackage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loaded EPackage</em>' reference.
	 * @see #getLoadedEPackage()
	 * @generated
	 */
	void setLoadedEPackage(EPackage value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void make();

} // Artifact
