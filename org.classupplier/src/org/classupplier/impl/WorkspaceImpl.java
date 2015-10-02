/**
 */
package org.classupplier.impl;

import java.util.Collection;

import org.classupplier.ClassSupplierFactory;
import org.classupplier.ClassSupplierPackage;
import org.classupplier.Contribution;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.Workspace;
import org.classupplier.core.ClassSupplierOSGi;
import org.classupplier.util.ClassSupplierSwitch;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
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
 * </p>
 * <ul>
 *   <li>{@link org.classupplier.impl.WorkspaceImpl#getContributions <em>Contributions</em>}</li>
 *   <li>{@link org.classupplier.impl.WorkspaceImpl#getResourceSet <em>Resource Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkspaceImpl extends EObjectImpl implements Workspace {
	/**
	 * The cached value of the '{@link #getContributions() <em>Contributions</em>}' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getContributions()
	 * @generated
	 * @ordered
	 */
	protected EList<Contribution> contributions;

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
	 * The cached value of the '{@link #getResourceSet() <em>Resource Set</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResourceSet()
	 * @generated
	 * @ordered
	 */
	protected ResourceSet resourceSet = RESOURCE_SET_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkspaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.WORKSPACE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Contribution> getContributions() {
		if (contributions == null) {
			contributions = new EObjectContainmentEList<Contribution>(Contribution.class, this,
					ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS);
		}
		return contributions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	public void init() {
		IProgressMonitor monitor = new NullProgressMonitor();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		for (IProject project : workspace.getRoot().getProjects()) {
			Contribution contribution = null;
			try {
				if (!project.isOpen())
					project.open(monitor);
				if (project.hasNature(ClassSupplierOSGi.NATURE_ID)) {
					if (contribution == null) {
						contribution = (ContributionImpl) ClassSupplierFactory.eINSTANCE.createContribution();
						contribution.setProjectName(project.getName());
					}
					registerContribution(contribution);
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
	public Contribution createContribution(EPackage blueprint) {
		Contribution result = getContribution(blueprint);
		if (result != null)
			return result;
		result = ClassSupplierFactory.eINSTANCE.createContribution();
		result.newState();
		result.setName(blueprint.getName());
		result.setDynamicEPackage(blueprint);
		registerContribution(result);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution getContribution(EObject eObject) {
		ClassSupplierSwitch<Contribution> getSwitch = new ClassSupplierSwitch<Contribution>() {

			@Override
			public Contribution caseContribution(Contribution object) {
				return object;
			}

			@Override
			public Contribution caseState(State object) {
				return (Contribution) object.eContainer();
			}

			@Override
			public Contribution caseWorkspace(Workspace object) {
				throw new IllegalArgumentException();
			}

			@Override
			public Contribution defaultCase(EObject object) {
				switch (object.eClass().getClassifierID()) {
				case EcorePackage.EPACKAGE:
					return getContribution((EPackage) object);
				case EcorePackage.ECLASSIFIER:
					return getContribution(((EClassifier) object).getEPackage());
				case EcorePackage.EOBJECT:
					getContribution(((EObject) object).eClass().getEPackage());
				case EcorePackage.ETYPED_ELEMENT:
					getContribution(((ETypedElement) object).getEType().getEPackage());
				case EcorePackage.EFACTORY:
					return getContribution(((EFactory) object).getEPackage());
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
	public Contribution getContribution(EPackage ePackage) {
		for (Contribution a : getContributions())
			switch (a.getStage()) {
			case MODELED:
				if (ResourceUtil.ePackagesAreEqual(ePackage, a.getDynamicEPackage()))
					return a;
			case LOADED:
				if (ResourceUtil.ePackagesAreEqual(ePackage, a.getGeneratedEPackage()))
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
	public void registerContribution(Contribution contribution) {
		getContributions().add(contribution);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void unregisterContribution(Contribution contribution) {
		getContributions().remove(contribution);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution getContribution(String projectName) {
		for (Contribution contribution : getContributions()) {
			if (contribution.getProjectName() != null && contribution.getProjectName().equals(projectName))
				return contribution;
		}
		IProgressMonitor monitor = new NullProgressMonitor();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		Contribution contribution = null;
		try {
			if (!project.isOpen())
				project.open(monitor);
			if (project.hasNature(ClassSupplierOSGi.NATURE_ID)) {
				if (contribution == null) {
					contribution = (ContributionImpl) ClassSupplierFactory.eINSTANCE.createContribution();
					contribution.setProjectName(project.getName());
				}
				registerContribution(contribution);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return contribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Phase contains(EPackage blueprint) {
		for (Contribution c : getContributions()) {
			if (c.getStage().getValue() < Phase.LOADED_VALUE) {
				if (ResourceUtil.ePackagesAreEqual(blueprint, c.getDynamicEPackage()))
					return c.getStage();
			} else {
				if (ResourceUtil.ePackagesAreEqual(blueprint, c.getGeneratedEPackage()))
					return c.getStage();
			}
		}
		return Phase.DEFINED;

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(Object object, IProgressMonitor monitor) throws CoreException {
		if (object instanceof EObject)
			getContribution((EObject) object).delete(monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS:
			return ((InternalEList<?>) getContributions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS:
			return getContributions();
		case ClassSupplierPackage.WORKSPACE__RESOURCE_SET:
			return getResourceSet();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS:
			getContributions().clear();
			getContributions().addAll((Collection<? extends Contribution>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS:
			getContributions().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS:
			return contributions != null && !contributions.isEmpty();
		case ClassSupplierPackage.WORKSPACE__RESOURCE_SET:
			return RESOURCE_SET_EDEFAULT == null ? resourceSet != null : !RESOURCE_SET_EDEFAULT.equals(resourceSet);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (resourceSet: "); //$NON-NLS-1$
		result.append(resourceSet);
		result.append(')');
		return result.toString();
	}

	@Override
	public boolean contains(ISchedulingRule rule) {
		if (this == rule)
			return true;
		if (rule instanceof MultiRule) {
			MultiRule multi = (MultiRule) rule;
			ISchedulingRule[] children = multi.getChildren();
			for (int i = 0; i < children.length; i++)
				if (!contains(children[i]))
					return false;
			return true;
		}
		return ResourcesPlugin.getWorkspace().getRoot().contains(rule);
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if (this == rule)
			return true;
		return ResourcesPlugin.getWorkspace().getRoot().isConflicting(rule);
	}

} // WorkspaceImpl
