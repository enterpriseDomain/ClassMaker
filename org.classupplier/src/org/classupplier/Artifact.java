/**
 */
package org.classupplier;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classupplier.Artifact#getName <em>Name</em>}</li>
 *   <li>{@link org.classupplier.Artifact#getEPackage <em>EPackage</em>}</li>
 *   <li>{@link org.classupplier.Artifact#getPrototypeEPackage <em>Prototype EPackage</em>}</li>
 *   <li>{@link org.classupplier.Artifact#getVersion <em>Version</em>}</li>
 *   <li>{@link org.classupplier.Artifact#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link org.classupplier.Artifact#getState <em>State</em>}</li>
 *   <li>{@link org.classupplier.Artifact#getStatus <em>Status</em>}</li>
 *   <li>{@link org.classupplier.Artifact#getLoadedEPackage <em>Loaded EPackage</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classupplier.ClasSupplierPackage#getArtifact()
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
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_Name()
	 * @model changeable="false"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EPackage</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EPackage</em>' reference.
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_EPackage()
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
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_PrototypeEPackage()
	 * @model changeable="false"
	 * @generated
	 */
	EPackage getPrototypeEPackage();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_Version()
	 * @model dataType="org.classupplier.Version" changeable="false"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_ProjectName()
	 * @model changeable="false"
	 * @generated
	 */
	String getProjectName();

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The literals are from the enumeration {@link org.classupplier.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see org.classupplier.State
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_State()
	 * @model changeable="false"
	 * @generated
	 */
	State getState();

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_Status()
	 * @model dataType="org.classupplier.IStatus" changeable="false"
	 * @generated
	 */
	IStatus getStatus();

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
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_LoadedEPackage()
	 * @model
	 * @generated
	 */
	EPackage getLoadedEPackage();

	/**
	 * Sets the value of the '{@link org.classupplier.Artifact#getLoadedEPackage <em>Loaded EPackage</em>}' reference.
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
	 * @model monitorDataType="org.classupplier.IProgressMonitor"
	 * @generated
	 */
	void make(IProgressMonitor monitor);

} // Artifact
