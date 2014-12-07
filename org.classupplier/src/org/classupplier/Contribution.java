/**
 */
package org.classupplier;

import java.util.Date;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classupplier.Contribution#getName <em>Name</em>}</li>
 *   <li>{@link org.classupplier.Contribution#getTime <em>Time</em>}</li>
 *   <li>{@link org.classupplier.Contribution#getVersion <em>Version</em>}</li>
 *   <li>{@link org.classupplier.Contribution#getStage <em>Stage</em>}</li>
 *   <li>{@link org.classupplier.Contribution#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link org.classupplier.Contribution#getSnapshots <em>Snapshots</em>}</li>
 *   <li>{@link org.classupplier.Contribution#getState <em>State</em>}</li>
 *   <li>{@link org.classupplier.Contribution#getEPackage <em>EPackage</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classupplier.ClassSupplierPackage#getContribution()
 * @model
 * @generated
 */
public interface Contribution extends EObject {
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
	 * Returns the value of the '<em><b>EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EPackage</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EPackage</em>' reference.
	 * @see org.classupplier.ClassSupplierPackage#getContribution_EPackage()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EPackage getEPackage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model type="org.classupplier.Future<? extends org.eclipse.emf.ecore.EPackage>" monitorDataType="org.classupplier.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EPackage> construct(IProgressMonitor monitor);

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
	 * @model
	 * @generated
	 */
	void setModelEPackage(EPackage blueprint);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(Date)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Time()
	 * @model id="true"
	 * @generated
	 */
	Date getTime();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(Date value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Version()
	 * @model dataType="org.classupplier.Version" volatile="true" derived="true"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Version value);

	/**
	 * Returns the value of the '<em><b>Stage</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * The literals are from the enumeration {@link org.classupplier.Phase}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stage</em>' attribute.
	 * @see org.classupplier.Phase
	 * @see #setStage(Phase)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Stage()
	 * @model default="" volatile="true" derived="true"
	 * @generated
	 */
	Phase getStage();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getStage <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stage</em>' attribute.
	 * @see org.classupplier.Phase
	 * @see #getStage()
	 * @generated
	 */
	void setStage(Phase value);

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_ProjectName()
	 * @model default="" volatile="true" derived="true"
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getProjectName <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

} // Contribution
