/**
 */
package org.classupplier.impl;

import java.util.Collection;

import org.classupplier.Artifact;
import org.classupplier.ClassSupplierFactory;
import org.classupplier.ClassSupplierPackage;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.Workspace;
import org.classupplier.util.ClassSupplierSwitch;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Workspace</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.classupplier.impl.WorkspaceImpl#getArtifacts <em>Artifacts
 * </em>}</li>
 * <li>{@link org.classupplier.impl.WorkspaceImpl#getResourceSet <em>Resource
 * Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkspaceImpl extends EObjectImpl implements Workspace {
	/**
	 * The cached value of the '{@link #getArtifacts() <em>Artifacts</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getArtifacts()
	 * @generated
	 * @ordered
	 */
	protected EList<Artifact> artifacts;

	/**
	 * The default value of the '{@link #getResourceSet() <em>Resource Set</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResourceSet()
	 * @generated NOT
	 * @ordered
	 */
	protected static final ResourceSet RESOURCE_SET_EDEFAULT = new ResourceSetImpl();

	/**
	 * The cached value of the '{@link #getResourceSet() <em>Resource Set</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResourceSet()
	 * @generated
	 * @ordered
	 */
	protected ResourceSet resourceSet = RESOURCE_SET_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WorkspaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.WORKSPACE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Artifact> getArtifacts() {
		if (artifacts == null) {
			artifacts = new EObjectContainmentEList<Artifact>(Artifact.class,
					this, ClassSupplierPackage.WORKSPACE__ARTIFACTS);
		}
		return artifacts;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void init(Workspace oldWorkspace) {
		IProgressMonitor monitor = new NullProgressMonitor();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		for (IProject project : workspace.getRoot().getProjects()) {
			Artifact artifact = null;
			try {
				if (!project.isOpen())
					project.open(monitor);
				if (project.hasNature(ClassSupplierOSGi.NATURE_ID)) {
					if (oldWorkspace != null)
						artifact = oldWorkspace.getArtifact(project.getName());
					if (artifact == null) {
						artifact = (ArtifactImpl) ClassSupplierFactory.eINSTANCE
								.createArtifact();
						artifact.setProjectName(project.getName());
					}
					registerArtifact(artifact);
					workspace.run(new Initializer(project, this), monitor);
				}
			} catch (CoreException e) {
				ClassSupplierOSGi.getInstance().getLog().log(e.getStatus());
			}
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact createArtifact(EPackage blueprint) {
		Artifact result = getArtifact(blueprint);
		if (result != null)
			return result;
		result = (ArtifactImpl) ClassSupplierFactory.eINSTANCE.createArtifact();
		result.newState();
		result.setName(blueprint.getName());
		result.setDynamicEPackage(blueprint);
		registerArtifact(result);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact getArtifact(EObject eObject) {
		ClassSupplierSwitch<Artifact> getSwitch = new ClassSupplierSwitch<Artifact>() {

			@Override
			public Artifact caseArtifact(Artifact object) {
				return object;
			}

			@Override
			public Artifact caseState(State object) {
				return (Artifact) object.eContainer();
			}

			@Override
			public Artifact caseWorkspace(Workspace object) {
				throw new IllegalArgumentException();
			}

			@Override
			public Artifact defaultCase(EObject object) {
				switch (object.eClass().getClassifierID()) {
				case EcorePackage.EPACKAGE:
					return getArtifact((EPackage) object);
				case EcorePackage.ECLASSIFIER:
					return getArtifact(((EClassifier) object).getEPackage());
				case EcorePackage.EOBJECT:
					getArtifact(((EObject) object).eClass().getEPackage());
				case EcorePackage.ETYPED_ELEMENT:
					getArtifact(((ETypedElement) object).getEType()
							.getEPackage());
				case EcorePackage.EFACTORY:
					return getArtifact(((EFactory) object).getEPackage());
				}
				return super.defaultCase(object);
			}

		};
		return getSwitch.doSwitch(eObject);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact getArtifact(EPackage ePackage) {
		for (Artifact a : getArtifacts())
			switch (a.getStage()) {
			case MODELED:
				if (packagesAreEqual(ePackage, a.getAppropriateEPackage()))
					return a;
			case LOADED:
				if (packagesAreEqual(ePackage, a.getAppropriateEPackage()))
					return a;
			default:
				break;
			}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact getArtifact(String projectName) {
		for (Artifact artifact : getArtifacts()) {
			if (artifact.getProjectName() != null
					&& artifact.getProjectName().equals(projectName))
				return artifact;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void registerArtifact(Artifact artifact) {
		getArtifacts().add(artifact);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void unregisterArtifact(Artifact artifact) {
		getArtifacts().remove(artifact);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Phase contains(EPackage blueprint) {
		for (Artifact a : getArtifacts())
			if (packagesAreEqual(blueprint, a.getAppropriateEPackage()))
				return a.getStage();
		return Phase.NEW;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void save(IProgressMonitor monitor) {
		try {
			ResourcesPlugin.getWorkspace().save(true, monitor);
		} catch (CoreException e) {
			ClassSupplierOSGi.getInstance().getLog().log(e.getStatus());
		}
	}

	private boolean packagesAreEqual(EPackage first, EPackage second) {
		if (first == null || second == null)
			return false;
		return first.getNsURI().equals(second.getNsURI())
				|| first.getName().equals(second.getName());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__ARTIFACTS:
			return ((InternalEList<?>) getArtifacts()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__ARTIFACTS:
			return getArtifacts();
		case ClassSupplierPackage.WORKSPACE__RESOURCE_SET:
			return getResourceSet();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__ARTIFACTS:
			getArtifacts().clear();
			getArtifacts().addAll((Collection<? extends Artifact>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__ARTIFACTS:
			getArtifacts().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__ARTIFACTS:
			return artifacts != null && !artifacts.isEmpty();
		case ClassSupplierPackage.WORKSPACE__RESOURCE_SET:
			return RESOURCE_SET_EDEFAULT == null ? resourceSet != null
					: !RESOURCE_SET_EDEFAULT.equals(resourceSet);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (resourceSet: ");
		result.append(resourceSet);
		result.append(')');
		return result.toString();
	}

	@Override
	public boolean contains(ISchedulingRule rule) {
		if (this == rule)
			return true;
		return ResourcesPlugin.getWorkspace().getRoot().contains(rule);
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if (this == rule)
			return true;
		return ResourcesPlugin.getWorkspace().getRoot().isConflicting(rule);
	}

} // WorkspaceImpl
