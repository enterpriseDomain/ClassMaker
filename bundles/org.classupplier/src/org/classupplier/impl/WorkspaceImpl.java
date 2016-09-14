/**
 */
package org.classupplier.impl;

import java.io.File;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

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
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.ecf.filetransfer.BrowseFileTransferException;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.internal.p2.director.app.DirectorApplication;
import org.eclipse.equinox.internal.p2.metadata.expression.Expression;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.internal.repository.tools.MirrorApplication;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.expression.ExpressionUtil;
import org.eclipse.equinox.p2.metadata.expression.IExpression;
import org.eclipse.equinox.p2.metadata.expression.IExpressionFactory;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.eclipse.FeaturesAndBundlesPublisherApplication;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.osgi.internal.framework.EquinoxBundle;
import org.eclipse.osgi.storage.BundleInfo.Generation;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetHandle;
import org.eclipse.pde.core.target.ITargetLocation;
import org.eclipse.pde.core.target.ITargetPlatformService;
import org.eclipse.pde.core.target.LoadTargetDefinitionJob;
import org.eclipse.pde.core.target.TargetBundle;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.TargetPlatformHelper;
import org.eclipse.pde.internal.core.target.Messages;
import org.eclipse.pde.internal.core.target.TargetPlatformService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.osgi.framework.Bundle;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Workspace</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.classupplier.impl.WorkspaceImpl#getContributions
 * <em>Contributions</em>}</li>
 * <li>{@link org.classupplier.impl.WorkspaceImpl#getResourceSet <em>Resource
 * Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkspaceImpl extends EObjectImpl implements Workspace {
	/**
	 * The cached value of the '{@link #getContributions()
	 * <em>Contributions</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
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
	public EList<Contribution> getContributions() {
		if (contributions == null) {
			contributions = new EObjectContainmentWithInverseEList<Contribution>(Contribution.class, this,
					ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS, ClassSupplierPackage.CONTRIBUTION__WORKSPACE);
		}
		return contributions;
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
	public void init() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		for (IProject project : workspace.getRoot().getProjects()) {
			Contribution contribution = null;
			try {
				if (project.isOpen() && project.hasNature(ClassSupplierOSGi.NATURE_ID)) {
					if (contribution == null) {
						contribution = (ContributionImpl) ClassSupplierFactory.eINSTANCE.createContribution();
						contribution.setProjectName(project.getName());
					}
					registerContribution(contribution);
				}
				provision(ClassSupplierOSGi.getInstance().getProgressMonitor());
			} catch (CoreException e) {
				ClassSupplierOSGi.getInstance().getLog().log(e.getStatus());
			}
		}
	}

	private StringBuffer targetVMArguments = null;

	private StringBuffer getVMArguments(ITargetLocation[] containers) {
		StringBuffer arguments = new StringBuffer(""); //$NON-NLS-1$
		for (int i = 0; i < containers.length; i++) {
			String[] vmargs = containers[i].getVMArguments();
			if (vmargs == null)
				continue;
			for (int j = 0; j < vmargs.length; j++) {
				arguments.append(vmargs[j]).append(' ');
			}
		}
		return arguments;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution createContribution(EPackage blueprint) {
		return createContribution(ECollections.singletonEList(blueprint));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution createContribution(EList<EPackage> blueprints) {
		Contribution result = getContribution(blueprints, true);
		if (result != null) {
			result.newState();
			result.setName(blueprints.get(blueprints.size() - 1).getName());
			result.getDynamicEPackages().clear();
			result.getDynamicEPackages().addAll(EcoreUtil.copyAll(blueprints));
			return result;
		}
		result = ClassSupplierFactory.eINSTANCE.createContribution();
		result.newState();
		result.setName(blueprints.get(blueprints.size() - 1).getName());
		result.getDynamicEPackages().addAll(EcoreUtil.copyAll(blueprints));
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
		return getContribution(ePackage, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution getContribution(EList<EPackage> ePackages) {
		return getContribution(ePackages, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution getContribution(EPackage ePackage, boolean searchOptimistic) {
		return getContribution(ECollections.singletonEList(ePackage), searchOptimistic);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution getContribution(EList<EPackage> ePackages, boolean searchOptimistic) {
		for (Contribution c : getContributions())
			switch (c.getStage()) {
			case MODELED:
				if (ePackagesAreEqual(ePackages, c.getDynamicEPackages(), !searchOptimistic))
					return c;
			case LOADED:
				if (ePackagesAreEqual(ePackages, c.getGeneratedEPackages(), !searchOptimistic))
					return c;
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
		IProgressMonitor monitor = ClassSupplierOSGi.getInstance().getProgressMonitor();
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
				if (ePackagesAreEqual(blueprint, c.getDynamicEPackages(), false))
					return c.getStage();
			} else {
				if (ePackagesAreEqual(blueprint, c.getGeneratedEPackages(), false))
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
	 * 
	 * @generated NOT
	 */
	public void provision(IProgressMonitor monitor) throws CoreException {
		for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if (project.hasNature(ClassSupplierOSGi.NATURE_ID)
					&& !project.hasNature(ClassSupplierOSGi.PDE_PLUGIN_NATURE)) {
				IProjectDescription description = project.getDescription();
				description.setNatureIds(
						ResourceUtil.addProjectNature(description.getNatureIds(), ClassSupplierOSGi.PDE_PLUGIN_NATURE));
				project.setDescription(description, monitor);
			}
		}

		IPath targetPlatformLocation = ClassSupplierOSGi.getInstance().getStateLocation().append("target")
				.append("workspace").addFileExtension("target");
		ITargetPlatformService targetPlatformService = TargetPlatformService.getDefault();
		ITargetDefinition targetDefinition = null;
		if (targetPlatformLocation.toFile().exists())
			targetDefinition = targetPlatformService.getTarget(targetPlatformLocation.toFile().toURI())
					.getTargetDefinition();
		if (targetDefinition == null) {
			targetDefinition = targetPlatformService.newTarget();
			targetDefinition.setName("Platform");
			Bundle[] bundles = Platform.getBundle("org.eclipse.core.runtime").getBundleContext().getBundles();
			List<ITargetLocation> bundleContainers = new ArrayList<ITargetLocation>();
			Set<File> dirs = new HashSet<File>();
			for (Bundle bundle : bundles) {
				EquinoxBundle bundleImpl = (EquinoxBundle) bundle;
				Generation generation = (Generation) bundleImpl.getModule().getCurrentRevision().getRevisionInfo();
				File file = generation.getBundleFile().getBaseFile();
				File folder = file.getParentFile();
				if (!dirs.contains(folder)) {
					dirs.add(folder);
					bundleContainers.add(targetPlatformService.newDirectoryLocation(folder.getAbsolutePath()));
				}
			}
			targetDefinition.setTargetLocations(bundleContainers.toArray(new ITargetLocation[bundleContainers.size()]));
			targetDefinition.setArch(Platform.getOSArch());
			targetDefinition.setOS(Platform.getOS());
			targetDefinition.setWS(Platform.getWS());
			targetDefinition.setNL(Platform.getNL());
			// targetDef.setJREContainer()
			targetPlatformService.saveTargetDefinition(targetDefinition);
		}
		Job job = new LoadTargetDefinitionJob(targetDefinition);
		job.schedule();
		try {
			job.join();
		} catch (InterruptedException e) {
			monitor.setCanceled(true);
			e.printStackTrace();
		}
		monitor.worked(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean ePackagesAreEqual(EPackage first, EPackage second, boolean conjunction) {
		if (first == null || second == null)
			return false;
		boolean result = conjunction
				? first.getNsURI().equals(second.getNsURI()) && first.getName().equals(second.getName())
				: first.getNsURI().equals(second.getNsURI()) || first.getName().equals(second.getName());
		TreeIterator<EObject> firstIt = first.eAllContents();
		TreeIterator<EObject> secondIt = second.eAllContents();
		while (conjunction ? firstIt.hasNext() && secondIt.hasNext() : firstIt.hasNext() || secondIt.hasNext()) {
			EObject f = null;
			if (firstIt.hasNext())
				f = firstIt.next();
			EObject s = null;
			if (secondIt.hasNext())
				s = secondIt.next();
			boolean eq = conjunction;
			if (f == null || s == null)
				eq = false;
			else {
				eq = f.equals(s); // EcoreUtil.equals(f, s);
			}
			if (conjunction)
				result &= eq;
			else
				result |= eq;
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean ePackagesAreEqual(EPackage first, EList<EPackage> second, boolean conjunction) {
		boolean result = second.contains(first);
		if (result)
			return result;
		for (EPackage secondEPackage : second)
			if (conjunction)
				result &= ePackagesAreEqual(first, secondEPackage, conjunction);
			else
				result |= ePackagesAreEqual(first, secondEPackage, conjunction);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean ePackagesAreEqual(EList<EPackage> first, EList<EPackage> second, boolean conjunction) {
		boolean result = first.equals(second);
		if (result)
			return result;
		for (EPackage firstEPackage : first)
			if (conjunction)
				result &= ePackagesAreEqual(firstEPackage, second, conjunction);
			else
				result |= ePackagesAreEqual(firstEPackage, second, conjunction);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.WORKSPACE__CONTRIBUTIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getContributions()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
