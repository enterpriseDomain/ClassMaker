/**
 */
package org.classupplier;

import java.util.Date;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Artifact</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classupplier.Artifact#getResource <em>Resource</em>}</li>
 *   <li>{@link org.classupplier.Artifact#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.classupplier.Artifact#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classupplier.ClassSupplierPackage#getArtifact()
 * @model
 * @generated
 */
public interface Artifact extends State {
	/**
	 * Returns the value of the '<em><b>Resource</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Resource</em>' attribute.
	 * @see #setResource(Resource)
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_Resource()
	 * @model transient="true"
	 * @generated
	 */
	Resource getResource();

	/**
	 * Sets the value of the '{@link org.classupplier.Artifact#getResource
	 * <em>Resource</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Resource</em>' attribute.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(Resource value);

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
	 * @see org.classupplier.ClassSupplierPackage#getArtifact_Snapshots()
	 * @model mapType="org.classupplier.DateToStateMapEntry<org.eclipse.emf.ecore.EDate, org.classupplier.State>"
	 * @generated
	 */
	EMap<Date, State> getSnapshots();

	/**
	 * Returns the value of the '<em><b>State</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>State</em>' reference.
	 * @see org.classupplier.ClasSupplierPackage#getArtifact_State()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	State getState();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void initState();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EPackage getActualEPackage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Phase getStage();

} // Artifact
