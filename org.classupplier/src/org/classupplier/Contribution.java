/**
 */
package org.classupplier;

import org.classupplier.impl.Constructable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
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
 * <li>{@link org.classupplier.Contribution#getDynamicEPackages
 * <em>Dynamic EPackages</em>}</li>
 * <li>{@link org.classupplier.Contribution#getGeneratedEPackages
 * <em>Generated EPackages</em>}</li>
 * <li>{@link org.classupplier.Contribution#getWorkspace <em>Workspace</em>}
 * </li>
 * <li>{@link org.classupplier.Contribution#getCustomizers <em>Customizers</em>}
 * </li>
 * </ul>
 *
 * @see org.classupplier.ClassSupplierPackage#getContribution()
 * @model superTypes="org.classupplier.Constructable"
 * @generated
 */
public interface Contribution extends EObject, Constructable {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. The default
	 * value is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
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
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Version()
	 * @model dataType="org.classupplier.Version"
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
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Stage()
	 * @model default="DEFINED" volatile="true" derived="true"
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
	 * default value is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
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

	/**
	 * Returns the value of the '<em><b>State</b></em>' containment reference.
	 * It is bidirectional and its opposite is '
	 * {@link org.classupplier.State#getContribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>State</em>' containment reference.
	 * @see org.classupplier.ClassSupplierPackage#getContribution_State()
	 * @see org.classupplier.State#getContribution
	 * @model opposite="contribution" containment="true" transient="true"
	 *        changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	State getState();

	/**
	 * Returns the value of the '<em><b>Dynamic EPackages</b></em>' reference
	 * list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.EPackage}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic EPackages</em>' reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dynamic EPackages</em>' reference list.
	 * @see org.classupplier.ClassSupplierPackage#getContribution_DynamicEPackages()
	 * @model volatile="true"
	 * @generated
	 */
	EList<EPackage> getDynamicEPackages();

	/**
	 * Returns the value of the '<em><b>Generated EPackages</b></em>' reference
	 * list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.EPackage}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generated EPackages</em>' reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generated EPackages</em>' reference list.
	 * @see org.classupplier.ClassSupplierPackage#getContribution_GeneratedEPackages()
	 * @model changeable="false" volatile="true"
	 * @generated
	 */
	EList<EPackage> getGeneratedEPackages();

	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' container reference.
	 * It is bidirectional and its opposite is '
	 * {@link org.classupplier.Workspace#getContributions <em>Contributions</em>
	 * }'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workspace</em>' container reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Workspace</em>' container reference.
	 * @see #setWorkspace(Workspace)
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Workspace()
	 * @see org.classupplier.Workspace#getContributions
	 * @model opposite="contributions" transient="false"
	 * @generated
	 */
	Workspace getWorkspace();

	/**
	 * Sets the value of the '{@link org.classupplier.Contribution#getWorkspace
	 * <em>Workspace</em>}' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Workspace</em>' container reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Workspace value);

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
	 * @see org.classupplier.ClassSupplierPackage#getContribution_Customizers()
	 * @model mapType=
	 *        "org.classupplier.PhaseQualifierToCustomizerMapEntry<org.classupplier.PhaseQualifier, org.classupplier.Customizer>"
	 *        volatile="true" derived="true"
	 * @generated
	 */
	EMap<PhaseQualifier, Customizer> getCustomizers();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.classupplier.IFuture<T>"
	 *        monitorDataType="org.classupplier.IProgressMonitor" TBounds=
	 *        "org.classupplier.EList<org.eclipse.emf.ecore.EPackage>"
	 * @generated
	 */
	<T extends EList<EPackage>> IFuture<T> apply(IProgressMonitor monitor);

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
	boolean contains(EStructuralFeature eFeature, EPackage questionEPackage);

} // Contribution