/**
 */
package org.classupplier;

import org.classupplier.impl.Constructable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.equinox.concurrent.future.IFuture;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Contribution</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.classupplier.Contribution#getName <em>Name</em>}</li>
 * <li>{@link org.classupplier.Contribution#getLanguage <em>Language</em>}</li>
 * <li>{@link org.classupplier.Contribution#getVersion <em>Version</em>}</li>
 * <li>{@link org.classupplier.Contribution#getStage <em>Stage</em>}</li>
 * <li>{@link org.classupplier.Contribution#getProjectName <em>Project Name</em>
 * }</li>
 * <li>{@link org.classupplier.Contribution#getStateHistory
 * <em>State History</em>}</li>
 * <li>{@link org.classupplier.Contribution#getState <em>State</em>}</li>
 * <li>{@link org.classupplier.Contribution#getDynamicEPackage
 * <em>Dynamic EPackage</em>}</li>
 * <li>{@link org.classupplier.Contribution#getGeneratedEPackage
 * <em>Generated EPackage</em>}</li>
 * </ul>
 *
 * @see org.classupplier.ClassSupplierPackage#getContribution()
 * @model superTypes="org.classupplier.Constructable"
 * @generated
 */
public interface Contribution extends EObject, Constructable {
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
	 * @see org.classupplier.ClassSupplierPackage#getContribution_State()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	State getState();

	/**
	 * Returns the value of the '<em><b>Dynamic EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic EPackage</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dynamic EPackage</em>' reference.
	 * @see #setDynamicEPackage(EPackage)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_DynamicEPackage()
	 * @model volatile="true"
	 * @generated
	 */
	EPackage getDynamicEPackage();

	/**
	 * Sets the value of the '
	 * {@link org.classupplier.Contribution#getDynamicEPackage
	 * <em>Dynamic EPackage</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Dynamic EPackage</em>' reference.
	 * @see #getDynamicEPackage()
	 * @generated
	 */
	void setDynamicEPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Generated EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generated EPackage</em>' reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generated EPackage</em>' reference.
	 * @see org.classupplier.ClassSupplierPackage#getContribution_GeneratedEPackage()
	 * @model changeable="false" volatile="true"
	 * @generated
	 */
	EPackage getGeneratedEPackage();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type=
	 *        "org.classupplier.IFuture<? extends org.eclipse.emf.ecore.EPackage>"
	 *        monitorDataType="org.classupplier.IProgressMonitor"
	 * @generated
	 */
	IFuture<? extends EPackage> apply(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model versionDataType="org.classupplier.Version"
	 * @generated
	 */
	void checkout(Version version);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	State newState();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. The default
	 * value is <code>""</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getName
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
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Language()
	 * @model volatile="true"
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getLanguage
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
	 * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Version()
	 * @model dataType="org.classupplier.Version" volatile="true" derived="true"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getVersion
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
	 * Returns the value of the '<em><b>Stage</b></em>' attribute. The default
	 * value is <code>""</code>. The literals are from the enumeration
	 * {@link org.classupplier.Phase}. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the value of the '<em>Stage</em>' attribute.
	 * @see org.classupplier.Phase
	 * @see #setStage(Phase)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Stage()
	 * @model default="" volatile="true" derived="true"
	 * @generated
	 */
	Phase getStage();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getStage
	 * <em>Stage</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Stage</em>' attribute.
	 * @see org.classupplier.Phase
	 * @see #getStage()
	 * @generated
	 */
	void setStage(Phase value);

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute. The
	 * default value is <code>""</code>. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_ProjectName()
	 * @model default="" volatile="true" derived="true"
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the '
	 * {@link org.classupplier.Contribution#getProjectName <em>Project Name</em>
	 * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>State History</b></em>' map. The key is
	 * of type {@link org.osgi.framework.Version}, and the value is of type
	 * {@link org.classupplier.State}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State History</em>' map isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>State History</em>' map.
	 * @see org.classupplier.ClassSupplierPackage#getContribution_StateHistory()
	 * @model mapType=
	 *        "org.classupplier.VersionToStateMapEntry<org.classupplier.Version, org.classupplier.State>"
	 * @generated
	 */
	EMap<Version, State> getStateHistory();

} // Contribution
