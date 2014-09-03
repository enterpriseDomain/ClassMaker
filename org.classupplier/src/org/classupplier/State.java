/**
 */
package org.classupplier;

import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classupplier.State#getName <em>Name</em>}</li>
 *   <li>{@link org.classupplier.State#getTime <em>Time</em>}</li>
 *   <li>{@link org.classupplier.State#getVersion <em>Version</em>}</li>
 *   <li>{@link org.classupplier.State#getStage <em>Stage</em>}</li>
 *   <li>{@link org.classupplier.State#getPrototypeEPackage <em>Prototype EPackage</em>}</li>
 *   <li>{@link org.classupplier.State#getLoadedEPackage <em>Loaded EPackage</em>}</li>
 *   <li>{@link org.classupplier.State#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link org.classupplier.State#getEPackage <em>EPackage</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classupplier.ClassSupplierPackage#getState()
 * @model
 * @generated
 */
public interface State extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.classupplier.ClassSupplierPackage#getState_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(Date)
	 * @see org.classupplier.ClassSupplierPackage#getState_Time()
	 * @model
	 * @generated
	 */
	Date getTime();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(Date value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see org.classupplier.ClassSupplierPackage#getState_Version()
	 * @model dataType="org.classupplier.Version"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getVersion
	 * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Version value);

	/**
	 * Returns the value of the '<em><b>Prototype EPackage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prototype EPackage</em>' reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prototype EPackage</em>' containment reference.
	 * @see #setPrototypeEPackage(EPackage)
	 * @see org.classupplier.ClassSupplierPackage#getState_PrototypeEPackage()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="group='#ePackage'"
	 * @generated
	 */
	EPackage getPrototypeEPackage();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getPrototypeEPackage <em>Prototype EPackage</em>}' containment reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prototype EPackage</em>' containment reference.
	 * @see #getPrototypeEPackage()
	 * @generated
	 */
	void setPrototypeEPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Loaded EPackage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loaded EPackage</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loaded EPackage</em>' containment reference.
	 * @see #setLoadedEPackage(EPackage)
	 * @see org.classupplier.ClassSupplierPackage#getState_LoadedEPackage()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="group='#ePackage'"
	 * @generated
	 */
	EPackage getLoadedEPackage();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getLoadedEPackage <em>Loaded EPackage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loaded EPackage</em>' containment reference.
	 * @see #getLoadedEPackage()
	 * @generated
	 */
	void setLoadedEPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.classupplier.ClassSupplierPackage#getState_ProjectName()
	 * @model default=""
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getProjectName <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>EPackage</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>EPackage</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EPackage</em>' attribute list.
	 * @see org.classupplier.ClassSupplierPackage#getState_EPackage()
	 * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="false" transient="true"
	 *        extendedMetaData="kind='group'"
	 * @generated
	 */
	FeatureMap getEPackage();

	/**
	 * Returns the value of the '<em><b>Stage</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * The literals are from the enumeration {@link org.classupplier.Phase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stage</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stage</em>' attribute.
	 * @see org.classupplier.Phase
	 * @see #setStage(Phase)
	 * @see org.classupplier.ClassSupplierPackage#getState_Stage()
	 * @model default=""
	 * @generated
	 */
	Phase getStage();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getStage <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stage</em>' attribute.
	 * @see org.classupplier.Phase
	 * @see #getStage()
	 * @generated
	 */
	void setStage(Phase value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model monitorDataType="org.classupplier.IProgressMonitor"
	 * @generated
	 */
	void produce(IProgressMonitor monitor);

} // State
