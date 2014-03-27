/**
 */
package org.classupplier;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Infrastructure</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classupplier.Infrastructure#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.classupplier.Infrastructure#getResourceSet <em>Resource Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classupplier.ClasSupplierPackage#getInfrastructure()
 * @model
 * @generated
 */
public interface Infrastructure extends EObject {

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' containment reference list.
	 * The list contents are of type {@link org.classupplier.Artifact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifacts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts</em>' containment reference list.
	 * @see org.classupplier.ClasSupplierPackage#getInfrastructure_Artifacts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Artifact> getArtifacts();

	public static final int CONTAINS_PROTOTYPE = 0;

	public static final int CONTAINS_LOADED = 1;

	public static final int DOESNT_CONTAIN = -1;

	/**
	 * Returns the value of the '<em><b>Resource Set</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Resource Set</em>' attribute.
	 * @see #setResourceSet(ResourceSet)
	 * @see org.classupplier.ClasSupplierPackage#getInfrastructure_ResourceSet()
	 * @model transient="true"
	 * @generated
	 */
	ResourceSet getResourceSet();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model listenerDataType="org.classupplier.Adapter"
	 * @generated
	 */
	void addRefreshListener(Adapter listener);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model listenerDataType="org.classupplier.Adapter"
	 * @generated
	 */
	void removeRefreshListener(Adapter listener);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void registerArtifact(Artifact artifact);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void unregisterArtifact(Artifact artifact);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int containsArtifact(EPackage blueprint);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Artifact getArtifact(String projectName);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Artifact getArtifact(EPackage ePackage);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="org.classupplier.IProgressMonitor"
	 * @generated
	 */
	void save(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Artifact createArtifact(EPackage blueprint);

} // Infrastructure
