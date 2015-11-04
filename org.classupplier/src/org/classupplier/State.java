/**
 */
package org.classupplier;

import java.util.Date;

import org.classupplier.impl.Constructable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.classupplier.State#getName <em>Name</em>}</li>
 * <li>{@link org.classupplier.State#getLanguage <em>Language</em>}</li>
 * <li>{@link org.classupplier.State#getTimestamp <em>Timestamp</em>}</li>
 * <li>{@link org.classupplier.State#getNumber <em>Number</em>}</li>
 * <li>{@link org.classupplier.State#getVersion <em>Version</em>}</li>
 * <li>{@link org.classupplier.State#getStage <em>Stage</em>}</li>
 * <li>{@link org.classupplier.State#getProjectName <em>Project Name</em>}</li>
 * <li>{@link org.classupplier.State#getDeployableUnitName
 * <em>Deployable Unit Name</em>}</li>
 * <li>{@link org.classupplier.State#getEPackages <em>EPackages</em>}</li>
 * <li>{@link org.classupplier.State#getDynamicEPackages
 * <em>Dynamic EPackages</em>}</li>
 * <li>{@link org.classupplier.State#getGeneratedEPackages
 * <em>Generated EPackages</em>}</li>
 * <li>{@link org.classupplier.State#getContribution <em>Contribution</em>}</li>
 * <li>{@link org.classupplier.State#getCustomizers <em>Customizers</em>}</li>
 * </ul>
 *
 * @see org.classupplier.ClassSupplierPackage#getState()
 * @model superTypes="org.classupplier.Constructable"
 * @generated
 */
public interface State extends EObject, Constructable {
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
	 * Sets the value of the '{@link org.classupplier.State#getName
	 * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see org.classupplier.ClassSupplierPackage#getState_Language()
	 * @model
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getLanguage
	 * <em>Language</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timestamp</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(Date)
	 * @see org.classupplier.ClassSupplierPackage#getState_Timestamp()
	 * @model id="true"
	 * @generated
	 */
	Date getTimestamp();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getTimestamp
	 * <em>Timestamp</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(Date value);

	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(int)
	 * @see org.classupplier.ClassSupplierPackage#getState_Number()
	 * @model
	 * @generated
	 */
	int getNumber();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getNumber
	 * <em>Number</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(int value);

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
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute. The
	 * default value is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.classupplier.ClassSupplierPackage#getState_ProjectName()
	 * @model default=""
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getProjectName
	 * <em>Project Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>Deployable Unit Name</b></em>'
	 * attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployable Unit Name</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Deployable Unit Name</em>' attribute.
	 * @see org.classupplier.ClassSupplierPackage#getState_DeployableUnitName()
	 * @model default="" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getDeployableUnitName();

	/**
	 * Returns the value of the '<em><b>EPackages</b></em>' attribute list. The
	 * list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>EPackages</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>EPackages</em>' attribute list.
	 * @see org.classupplier.ClassSupplierPackage#getState_EPackages()
	 * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        transient="true" extendedMetaData="kind='group'"
	 * @generated
	 */
	FeatureMap getEPackages();

	/**
	 * Returns the value of the '<em><b>Dynamic EPackages</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.EPackage}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic EPackages</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dynamic EPackages</em>' containment
	 *         reference list.
	 * @see org.classupplier.ClassSupplierPackage#getState_DynamicEPackages()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="group='#ePackages'"
	 * @generated
	 */
	EList<EPackage> getDynamicEPackages();

	/**
	 * Returns the value of the '<em><b>Generated EPackages</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.EPackage}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generated EPackages</em>' containment
	 * reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generated EPackages</em>' containment
	 *         reference list.
	 * @see org.classupplier.ClassSupplierPackage#getState_GeneratedEPackages()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="group='#ePackages'"
	 * @generated
	 */
	EList<EPackage> getGeneratedEPackages();

	/**
	 * Returns the value of the '<em><b>Contribution</b></em>' container
	 * reference. It is bidirectional and its opposite is '
	 * {@link org.classupplier.Contribution#getState <em>State</em>}'. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contribution</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Contribution</em>' container reference.
	 * @see #setContribution(Contribution)
	 * @see org.classupplier.ClassSupplierPackage#getState_Contribution()
	 * @see org.classupplier.Contribution#getState
	 * @model opposite="state"
	 * @generated
	 */
	Contribution getContribution();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getContribution
	 * <em>Contribution</em>}' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Contribution</em>' container
	 *            reference.
	 * @see #getContribution()
	 * @generated
	 */
	void setContribution(Contribution value);

	/**
	 * Returns the value of the '<em><b>Customizers</b></em>' map. The key is of
	 * type {@link org.classupplier.PhaseQualifier}, and the value is of type
	 * {@link org.classupplier.Customizer}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Customizers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Customizers</em>' map.
	 * @see org.classupplier.ClassSupplierPackage#getState_Customizers()
	 * @model mapType=
	 *        "org.classupplier.PhaseQualifierToCustomizerMapEntry<org.classupplier.PhaseQualifier, org.classupplier.Customizer>"
	 * @generated
	 */
	EMap<PhaseQualifier, Customizer> getCustomizers();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void fireJobsCompleted();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	String formatVersion();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.classupplier.CoreException"
	 *        monitorDataType="org.classupplier.IProgressMonitor"
	 * @generated
	 */
	void setProjectVersion(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.classupplier.CoreException"
	 *        monitorDataType="org.classupplier.IProgressMonitor"
	 * @generated
	 */
	void delete(IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	EPackage find(EPackage ePackage, Phase stage);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean contains(EStructuralFeature eFeature, EPackage questionEPackage);

	/**
	 * Returns the value of the '<em><b>Stage</b></em>' attribute. The default
	 * value is <code>"DEFINED"</code>. The literals are from the enumeration
	 * {@link org.classupplier.Phase}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stage</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Stage</em>' attribute.
	 * @see org.classupplier.Phase
	 * @see #setStage(Phase)
	 * @see org.classupplier.ClassSupplierPackage#getState_Stage()
	 * @model default="DEFINED"
	 * @generated
	 */
	Phase getStage();

	/**
	 * Sets the value of the '{@link org.classupplier.State#getStage
	 * <em>Stage</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Stage</em>' attribute.
	 * @see org.classupplier.Phase
	 * @see #getStage()
	 * @generated
	 */
	void setStage(Phase value);

} // State
