/**
 * Copyright 2012-2017 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.osgi.internal.framework.EquinoxBundle;
import org.eclipse.osgi.storage.BundleInfo.Generation;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetLocation;
import org.eclipse.pde.core.target.ITargetPlatformService;
import org.eclipse.pde.core.target.LoadTargetDefinitionJob;
import org.eclipse.pde.internal.core.target.TargetPlatformService;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMRegistry;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ClassMakerSwitch;
import org.enterprisedomain.classmaker.util.ModelUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Workspace</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.WorkspaceImpl#getProjects
 * <em>Projects</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.WorkspaceImpl#getResourceSet
 * <em>Resource Set</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.WorkspaceImpl#getCustomizers
 * <em>Customizers</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.WorkspaceImpl#getService
 * <em>Service</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.WorkspaceImpl#getSCMRegistry
 * <em>SCM Registry</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkspaceImpl extends EObjectImpl implements Workspace {
	/**
	 * The cached value of the '{@link #getProjects() <em>Projects</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjects()
	 * @generated
	 * @ordered
	 */
	protected EList<Project> projects;

	/**
	 * The default value of the '{@link #getResourceSet() <em>Resource Set</em>} '
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResourceSet()
	 * @generated NOT
	 * @ordered
	 */
	protected static final ResourceSet RESOURCE_SET_EDEFAULT = new ResourceSetImpl();

	static {
		RESOURCE_SET_EDEFAULT.setURIConverter(new ResourceSetURIConverter());
	}

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
	 * The cached value of the '{@link #getCustomizers() <em>Customizers</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCustomizers()
	 * @generated
	 * @ordered
	 */
	protected EMap<StageQualifier, Customizer> customizers;

	/**
	 * The cached value of the '{@link #getSCMRegistry() <em>SCM Registry</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSCMRegistry()
	 * @generated
	 * @ordered
	 */
	protected SCMRegistry<?> scmRegistry;

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
		return ClassMakerPackage.Literals.WORKSPACE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Project> getProjects() {
		if (projects == null) {
			projects = new EObjectContainmentWithInverseEList<Project>(Project.class, this,
					ClassMakerPackage.WORKSPACE__PROJECTS, ClassMakerPackage.PROJECT__WORKSPACE);
		}
		return projects;
	}

	private EList<Contribution> getContributions() {
		EList<Contribution> results = ECollections.newBasicEList();
		for (Project project : getProjects())
			if (project instanceof Contribution)
				results.add((Contribution) project);
		return results;
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
	public EMap<StageQualifier, Customizer> getCustomizers() {
		if (customizers == null) {
			customizers = new EcoreEMap<StageQualifier, Customizer>(
					ClassMakerPackage.Literals.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY,
					StageQualifierToCustomizerMapEntryImpl.class, this, ClassMakerPackage.WORKSPACE__CUSTOMIZERS);
			for (String id : ClassMakerService.Stages.ids()) {
				SortedSet<Customizer> customizers = ClassMakerService.Stages.createCustomizers(id);
				if (!customizers.isEmpty())
					for (Customizer customizer : customizers)
						this.customizers.put(ClassMakerService.Stages.lookup(id), customizer);
			}
		}
		return customizers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ClassMakerService getService() {
		if (eContainerFeatureID() != ClassMakerPackage.WORKSPACE__SERVICE)
			return ClassMakerPlugin.getClassMaker();
		return (ClassMakerService) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetService(ClassMakerService newService, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newService, ClassMakerPackage.WORKSPACE__SERVICE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setService(ClassMakerService newService) {
		if (newService != eInternalContainer()
				|| (eContainerFeatureID() != ClassMakerPackage.WORKSPACE__SERVICE && newService != null)) {
			if (EcoreUtil.isAncestor(this, newService))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newService != null)
				msgs = ((InternalEObject) newService).eInverseAdd(this,
						ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE, ClassMakerService.class, msgs);
			msgs = basicSetService(newService, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.WORKSPACE__SERVICE, newService,
					newService));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public SCMRegistry<?> getSCMRegistry() {
		if (scmRegistry != null && scmRegistry.eIsProxy()) {
			InternalEObject oldSCMRegistry = (InternalEObject) scmRegistry;
			scmRegistry = (SCMRegistry<?>) eResolveProxy(oldSCMRegistry);
			if (scmRegistry != oldSCMRegistry) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClassMakerPackage.WORKSPACE__SCM_REGISTRY,
							oldSCMRegistry, scmRegistry));
			}
		}
		if (scmRegistry == null)
			scmRegistry = ClassMakerFactory.eINSTANCE.createSCMRegistry();
		return scmRegistry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public SCMRegistry<?> basicGetSCMRegistry() {
		return scmRegistry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void initialize() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		for (IProject eProject : workspace.getRoot().getProjects()) {
			Project project = null;
			try {
				if (eProject.hasNature(ClassMakerPlugin.NATURE_ID)) {
					project = ClassMakerFactory.eINSTANCE.createContribution();
				} else {
					project = ClassMakerFactory.eINSTANCE.createProject();
				}
				project.setProjectName(eProject.getName());
				registerProject(project);
				project.initialize(false);
			} catch (CoreException e) {
				ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
			}
		}
	}

	private static boolean targetPlatformAlreadySet = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("restriction")
	public void provision(IProgressMonitor monitor) throws CoreException {
		if (System.getProperty("buildingWithTycho") != null) {
			if (targetPlatformAlreadySet) {
				return;
			}
			targetPlatformAlreadySet = true;
		} else {
			return;
		}
		try {
			Bundle currentBundle = FrameworkUtil.getBundle(getClass());
			ITargetPlatformService targetPlatformService = TargetPlatformService.getDefault();
			ITargetDefinition targetDefinition = null;
			targetDefinition = targetPlatformService.newTarget();
			targetDefinition.setName("Platform");
			Bundle[] bundles = Platform.getBundle(Platform.PI_RUNTIME).getBundleContext().getBundles();
			List<ITargetLocation> bundleContainers = new ArrayList<ITargetLocation>();
			Set<File> dirs = new HashSet<File>();
			for (Bundle bundle : bundles) {
				if (bundle.equals(currentBundle)) {
					continue;
				}
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
			targetPlatformService.saveTargetDefinition(targetDefinition);
			LoadTargetDefinitionJob job = new LoadTargetDefinitionJob(targetDefinition);
			job.schedule();
			try {
				job.join();
			} catch (InterruptedException e) {
				monitor.setCanceled(true);
				e.printStackTrace();
			}
		} catch (OperationCanceledException e) {
			monitor.setCanceled(true);
			return;
		} finally {
			monitor.done();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution createContribution(EPackage blueprint, IProgressMonitor monitor) throws CoreException {
		try {
			Contribution result = getContribution(blueprint, true);
			if (result != null) {
				if (!result.getRevisions().isEmpty()) {
					Revision newRevision = result.newRevision(result.nextVersion());
					result.checkout(newRevision.getVersion());
				} else {
					result.createRevision(monitor);
					result.load(true);
				}
				registerProject(result);
				return result;
			}
			// Contribution not exists. Create
			result = ClassMakerFactory.eINSTANCE.createContribution();
			registerProject(result);
			result.setName(blueprint.getName());
			result.create(monitor);
			Version version = result.nextVersion();
			Revision revision = result.newRevision(version);
			result.checkout(revision.getVersion());
			result.load(true);
			EPackage model = EcoreUtil.copy(blueprint);
			result.getDomainModel().setDynamic(model);
			result.getState().saveResource();
			return result;
		} finally {
			monitor.done();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution getContribution(EObject eObject) {
		ClassMakerSwitch<Contribution> getSwitch = new ClassMakerSwitch<Contribution>() {

			@Override
			public Contribution caseContribution(Contribution object) {
				return object;
			}

			@Override
			public Contribution caseRevision(Revision object) {
				return object.getContribution();
			}

			@Override
			public Contribution caseResourceAdapter(ResourceAdapter object) {
				URI uri = object.getResource().getURI();
				try {
					uri = uri.deresolve(URI.createURI(ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toFile()
							.toURI().toURL().toExternalForm().toString()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				return getContribution(uri.segment(0));
			}

			@Override
			public Contribution caseState(State object) {
				return object.getContribution();
			}

			@Override
			public Contribution caseWorkspace(Workspace object) {
				throw new IllegalArgumentException();
			}

			@Override
			public Contribution defaultCase(EObject object) {
				return getContribution(ModelUtil.getEPackage(object));
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
	public Contribution getContribution(EPackage ePackage, boolean searchOptimistic) {
		return getContribution(ePackage, Stage.MODELED, searchOptimistic);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution getContribution(EPackage ePackage, Stage filter) {
		return getContribution(ePackage, filter, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution getContribution(EPackage ePackage, Stage filter, boolean searchOptimistic) {
		switch (filter.getValue()) {
		case Stage.DEFINED_VALUE:
		case Stage.MODELED_VALUE:
		case Stage.GENERATED_VALUE:
		case Stage.EXPORTED_VALUE:
		case Stage.INSTALLED_VALUE:
			for (Contribution c : getContributions())
				if (ModelUtil.ePackagesAreEqual(ePackage, c.getDomainModel().getDynamic(), !searchOptimistic))
					return c;
			break;
		case Stage.LOADED_VALUE:
			for (Contribution c : getContributions()) {
				if (ModelUtil.ePackagesAreEqual(ePackage, c.getDomainModel().getDynamic(), false))
					while (c.getState().isMaking() && !c.getPhase().equals(Stage.LOADED))
						Thread.yield();
				if (ModelUtil.ePackagesAreEqual(ePackage, c.getDomainModel().getGenerated(), !searchOptimistic))
					return c;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void registerProject(Project project) {
		getProjects().add(project);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void unregisterProject(Project project) {
		getProjects().remove(project);
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
		// IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
		// IProject project =
		// ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		// Contribution contribution = null;
		// try {
		// if (!project.exists())
		// return contribution;
		// if (!project.isOpen()) {
		// SubMonitor pm = SubMonitor.convert(monitor);
		// SubMonitor m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
		// try {
		// project.open(m);
		// } finally {
		// m.done();
		// pm.done();
		// }
		// }
		// if (project.hasNature(ClassMakerPlugin.NATURE_ID)) {
		// if (contribution == null) {
		// contribution = (ContributionImpl)
		// ClassMakerFactory.eINSTANCE.createContribution();
		// contribution.setProjectName(project.getName());
		// }
		// registerProject(contribution);
		// }
		// } catch (CoreException e) {
		// ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		// } finally {
		// monitor.done();
		// }
		// return contribution;
		return null;

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Project createProject(String name, IProgressMonitor monitor) throws CoreException {
		try {
			Project project = ClassMakerFactory.eINSTANCE.createProject();
			registerProject(project);
			project.setName(name);
			project.create(monitor);
			Revision revision = project.newRevision(project.nextVersion());
			project.checkout(revision.getVersion());
			return project;
		} finally {
			monitor.done();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Project getProject(String name) {
		String projectName = name;
		Contribution contribution = getContribution(getService().computeProjectName(projectName));
		if (contribution != null)
			projectName = contribution.getProjectName();
		Project project = contribution;
		for (Project theProject : getProjects()) {
			if (theProject.getProjectName() != null && theProject.getProjectName().equals(projectName))
				project = theProject;
		}
		// IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
		// IProject eProject =
		// ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		// try {
		// if (!eProject.exists())
		// return project;
		// if (!eProject.isOpen()) {
		// SubMonitor pm = SubMonitor.convert(monitor);
		// SubMonitor m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
		// try {
		// eProject.open(m);
		// } finally {
		// m.done();
		// pm.done();
		// }
		// }
		// if (eProject.hasNature(ClassMakerPlugin.NATURE_ID)) {
		// if (contribution == null) {
		// contribution = (ContributionImpl)
		// ClassMakerFactory.eINSTANCE.createContribution();
		// contribution.setProjectName(eProject.getName());
		// }
		// registerProject(contribution);
		// } else {
		// project = ClassMakerFactory.eINSTANCE.createProject();
		// project.setName(eProject.getName());
		// registerProject(project);
		// }
		// } catch (CoreException e) {
		// ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		// } finally {
		// monitor.done();
		// }
		return project;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Project getProject(EObject eObject) {
		return getProject(eObject.eResource());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Project getProject(Resource resource) {
		URIConverter uriConverter = resource.getResourceSet().getURIConverter();
		IPath workspaceRootPath = ResourcesPlugin.getWorkspace().getRoot().getFullPath().addTrailingSeparator();
		URI workspaceRootURI = URI.createPlatformResourceURI(workspaceRootPath.toString(), true);
		IPath workspaceRootLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation().addTrailingSeparator();
		URI workspaceRootLocationURI = URI.createURI(workspaceRootLocation.toString(), true);
		URI workspaceRootLocationFileURI = URI.createFileURI(workspaceRootLocation.toString());
		uriConverter.getURIMap().put(workspaceRootLocationURI, workspaceRootURI);
		uriConverter.getURIMap().put(workspaceRootLocationFileURI, workspaceRootURI);
		for (Project project : getProjects()) {
			if (!project.getChildren().isEmpty() && project.getChildren().get(0) instanceof Resource) {
				if (uriConverter.normalize(resource.getURI())
						.equals(uriConverter.normalize(((Resource) project.getChildren().get(0)).getURI())))
					return project;
			} else if (!project.getChildren().isEmpty() && project.getChildren().get(0) instanceof EObject) {
				if (uriConverter.normalize(resource.getURI())
						.equals(uriConverter.normalize(((EObject) project.getChildren().get(0)).eResource().getURI())))
					return project;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Stage contains(EPackage blueprint) {
		for (Contribution c : getContributions()) {
			if (c.getPhase().getValue() < Stage.LOADED_VALUE) {
				if (ModelUtil.ePackagesAreEqual(blueprint, c.getDomainModel().getDynamic(), false))
					return c.getPhase();
			} else {
				if (ModelUtil.ePackagesAreEqual(blueprint, c.getDomainModel().getGenerated(), false))
					return c.getPhase();
			}
		}
		return Stage.DEFINED;

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean contains(EObject eObject) {
		return getContribution((EObject) eObject) != null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(Object object, IProgressMonitor monitor) throws CoreException {
		try {
			if (object instanceof EObject)
				getContribution((EObject) object).delete(monitor);
		} finally {
			monitor.done();
		}
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
		case ClassMakerPackage.WORKSPACE__PROJECTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getProjects()).basicAdd(otherEnd, msgs);
		case ClassMakerPackage.WORKSPACE__SERVICE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetService((ClassMakerService) otherEnd, msgs);
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
		case ClassMakerPackage.WORKSPACE__PROJECTS:
			return ((InternalEList<?>) getProjects()).basicRemove(otherEnd, msgs);
		case ClassMakerPackage.WORKSPACE__CUSTOMIZERS:
			return ((InternalEList<?>) getCustomizers()).basicRemove(otherEnd, msgs);
		case ClassMakerPackage.WORKSPACE__SERVICE:
			return basicSetService(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ClassMakerPackage.WORKSPACE__SERVICE:
			return eInternalContainer().eInverseRemove(this, ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE,
					ClassMakerService.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.WORKSPACE__PROJECTS:
			return getProjects();
		case ClassMakerPackage.WORKSPACE__RESOURCE_SET:
			return getResourceSet();
		case ClassMakerPackage.WORKSPACE__CUSTOMIZERS:
			if (coreType)
				return getCustomizers();
			else
				return getCustomizers().map();
		case ClassMakerPackage.WORKSPACE__SERVICE:
			return getService();
		case ClassMakerPackage.WORKSPACE__SCM_REGISTRY:
			if (resolve)
				return getSCMRegistry();
			return basicGetSCMRegistry();
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
		case ClassMakerPackage.WORKSPACE__PROJECTS:
			getProjects().clear();
			getProjects().addAll((Collection<? extends Project>) newValue);
			return;
		case ClassMakerPackage.WORKSPACE__CUSTOMIZERS:
			((EStructuralFeature.Setting) getCustomizers()).set(newValue);
			return;
		case ClassMakerPackage.WORKSPACE__SERVICE:
			setService((ClassMakerService) newValue);
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
		case ClassMakerPackage.WORKSPACE__PROJECTS:
			getProjects().clear();
			return;
		case ClassMakerPackage.WORKSPACE__CUSTOMIZERS:
			getCustomizers().clear();
			return;
		case ClassMakerPackage.WORKSPACE__SERVICE:
			setService((ClassMakerService) null);
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
		case ClassMakerPackage.WORKSPACE__PROJECTS:
			return projects != null && !projects.isEmpty();
		case ClassMakerPackage.WORKSPACE__RESOURCE_SET:
			return RESOURCE_SET_EDEFAULT == null ? resourceSet != null : !RESOURCE_SET_EDEFAULT.equals(resourceSet);
		case ClassMakerPackage.WORKSPACE__CUSTOMIZERS:
			return customizers != null && !customizers.isEmpty();
		case ClassMakerPackage.WORKSPACE__SERVICE:
			return getService() != null;
		case ClassMakerPackage.WORKSPACE__SCM_REGISTRY:
			return scmRegistry != null;
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
		return ResourcesPlugin.getWorkspace().getRuleFactory().buildRule().contains(rule);
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if (this == rule)
			return true;
		return ResourcesPlugin.getWorkspace().getRuleFactory().buildRule().isConflicting(rule);
	}

} // WorkspaceImpl
