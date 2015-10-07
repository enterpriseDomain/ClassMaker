/**
 */
package org.classupplier;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Workspace</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.classupplier.Workspace#getContributions <em>Contributions</em>
 * }</li>
 * <li>{@link org.classupplier.Workspace#getResourceSet <em>Resource Set</em>}
 * </li>
 * </ul>
 *
 * @see org.classupplier.ClassSupplierPackage#getWorkspace()
 * @model superTypes="org.classupplier.ISchedulingRule"
 * @generated
 */
public interface Workspace extends EObject, ISchedulingRule {
	/**
	 * Returns the value of the '<em><b>Contributions</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.classupplier.Contribution}. It is bidirectional and its
	 * opposite is '{@link org.classupplier.Contribution#getWorkspace
	 * <em>Workspace</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributions</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Contributions</em>' containment reference
	 *         list.
	 * @see org.classupplier.ClassSupplierPackage#getWorkspace_Contributions()
	 * @see org.classupplier.Contribution#getWorkspace
	 * @model opposite="workspace" containment="true"
	 * @generated
	 */
	EList<Contribution> getContributions();

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
	 * @see org.classupplier.ClassSupplierPackage#getWorkspace_ResourceSet()
	 * @model transient="true" changeable="false"
	 * @generated
	 */
	ResourceSet getResourceSet();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void init();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Contribution createContribution(EPackage blueprint);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model blueprintsMany="true"
	 * @generated
	 */
	Contribution createContribution(EList<EPackage> blueprints);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Contribution getContribution(EObject eObject);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Contribution getContribution(EPackage ePackage);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model ePackagesMany="true"
	 * @generated
	 */
	Contribution getContribution(EList<EPackage> ePackages);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Contribution getContribution(String projectName);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void registerContribution(Contribution contribution);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void unregisterContribution(Contribution contribution);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Phase contains(EPackage blueprint);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.classupplier.CoreException"
	 *        monitorDataType="org.classupplier.IProgressMonitor"
	 * @generated
	 */
	void delete(Object object, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean ePackagesAreEqual(EPackage first, EPackage second, boolean conjunction);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model secondMany="true"
	 * @generated
	 */
	boolean ePackagesAreEqual(EPackage first, EList<EPackage> second, boolean conjunction);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model firstMany="true" secondMany="true"
	 * @generated
	 */
	boolean ePackagesAreEqual(EList<EPackage> first, EList<EPackage> second, boolean conjunction);

} // Workspace
