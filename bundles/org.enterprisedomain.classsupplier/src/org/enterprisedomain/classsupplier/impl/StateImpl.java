/**
 * Copyright 2012-2016 Kyrill Zotkin
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
package org.enterprisedomain.classsupplier.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.enterprisedomain.classsupplier.ClassSupplierPackage;
import org.enterprisedomain.classsupplier.Contribution;
import org.enterprisedomain.classsupplier.Customizer;
import org.enterprisedomain.classsupplier.Item;
import org.enterprisedomain.classsupplier.ModelPair;
import org.enterprisedomain.classsupplier.Revision;
import org.enterprisedomain.classsupplier.Stage;
import org.enterprisedomain.classsupplier.StageQualifier;
import org.enterprisedomain.classsupplier.State;
import org.enterprisedomain.classsupplier.core.ClassSupplierOSGi;
import org.enterprisedomain.classsupplier.jobs.EnterpriseDomainJob;
import org.enterprisedomain.classsupplier.jobs.EnterpriseDomainJobListener;
import org.enterprisedomain.classsupplier.jobs.codegen.EcoreGenerator;
import org.enterprisedomain.classsupplier.jobs.codegen.Generator;
import org.enterprisedomain.classsupplier.jobs.export.Exporter;
import org.enterprisedomain.classsupplier.jobs.export.PDEPluginExporter;
import org.enterprisedomain.classsupplier.jobs.install.OSGiInstaller;
import org.enterprisedomain.classsupplier.jobs.load.OSGiEPackageLoader;
import org.enterprisedomain.classsupplier.util.GitUtil;
import org.enterprisedomain.classsupplier.util.ListUtil;
import org.enterprisedomain.classsupplier.util.ModelUtil;
import org.enterprisedomain.classsupplier.util.ResourceUtils;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.StateImpl#getContribution <em>Contribution</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.StateImpl#getRevision <em>Revision</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.StateImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.StateImpl#getDeployableUnitName <em>Deployable Unit Name</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.StateImpl#getJobFamily <em>Job Family</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.StateImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.StateImpl#getCommitIds <em>Commit Ids</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.StateImpl#getStateCustomizers <em>State Customizers</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.StateImpl#getProjectName <em>Project Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateImpl extends ItemImpl implements State {

	public class PropertiesAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == ClassSupplierPackage.STATE__MODEL_NAME
					&& msg.getEventType() == Notification.SET && msg.getNewStringValue() != null)
				setProjectName(msg.getNewStringValue().toLowerCase());
		}

	}

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final int TIMESTAMP_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected int timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated NOT
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = Version.emptyVersion;

	/**
	 * The default value of the '{@link #getDeployableUnitName() <em>Deployable Unit Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDeployableUnitName()
	 * @generated
	 * @ordered
	 */
	protected static final String DEPLOYABLE_UNIT_NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getJobFamily() <em>Job Family</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getJobFamily()
	 * @generated
	 * @ordered
	 */
	protected static final String JOB_FAMILY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJobFamily() <em>Job Family</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getJobFamily()
	 * @generated
	 * @ordered
	 */
	protected String jobFamily = JOB_FAMILY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected Resource resource;

	/**
	 * The cached value of the '{@link #getCommitIds() <em>Commit Ids</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCommitIds()
	 * @generated
	 * @ordered
	 */
	protected EList<String> commitIds;

	/**
	 * The cached value of the '{@link #getStateCustomizers() <em>State Customizers</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStateCustomizers()
	 * @generated
	 * @ordered
	 */
	protected EMap<StageQualifier, Customizer> stateCustomizers;

	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = null;

	protected String language = LANGUAGE_EDEFAULT;

	private boolean loading = false;

	private boolean saving = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected StateImpl() {
		super();
		eAdapters().add(new PropertiesAdapter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimestamp(int newTimestamp) {
		int oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__TIMESTAMP, oldTimestamp,
					timestamp));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getDeployableUnitName() {
		String separator = "_"; //$NON-NLS-1$
		// TODO to check: possibly only for Maven
		// if (getProjectName().endsWith("_")) //$NON-NLS-1$
		// separator = "-"; //$NON-NLS-1$
		return getProjectName() + separator + getVersion().toString();
	}

	@Override
	public Item basicGetParent() {
		return getRevision();
	}

	@Override
	public void setParent(Item newParent) {
		if (newParent instanceof Revision)
			((Revision) newParent).getStateHistory().put(getTimestamp(), this);
		super.setParent(newParent);
	}

	public String initialize() {
		if (!eIsSet(ClassSupplierPackage.STATE__MODEL_NAME))
			super.initialize();
		if (eIsSet(ClassSupplierPackage.STATE__CONTRIBUTION)
				&& getContribution().eIsSet(ClassSupplierPackage.Literals.PROJECT__PROJECT_NAME)
				&& ResourceUtils.isProjectExists(getProjectName())) {
			URI modelURI = getModelURI();
			loadResource(modelURI, !eIsSet(ClassSupplierPackage.STATE__RESOURCE), false);
			saveResource();
			setPhase(Stage.MODELED);
			getResource().eAdapters().remove(resourceToModelsAdapter);
			getResource().eAdapters().add(resourceToModelsAdapter);
			getDomainModel().eAdapters().remove(modelsToResourceAdapter);
			getDomainModel().eAdapters().add(modelsToResourceAdapter);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			try {
				return commit(modelURI.deresolve(URI.createFileURI(root.getRawLocation().toString())).toString());
			} catch (Exception e) {
				ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(e));
				return null;
			}
		}
		return "";
	}

	private URI modelURI;

	private URI getModelURI() {
		if (modelURI == null) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject project = root.getProject(getProjectName());
			IProgressMonitor monitor = ClassSupplierOSGi.getInstance().getProgressMonitor();
			IFolder folder = project.getFolder(ResourceUtils.getModelFolderName());
			if (!folder.exists()) {
				try {
					folder.create(true, true, monitor);
				} catch (CoreException e) {
					ClassSupplierOSGi.getInstance().getLog().log(e.getStatus());
				}
			}
			IPath modelPath = ResourceUtils.getModelResourcePath(project, getModelName());
			modelURI = URI.createFileURI(root.getRawLocation().append(modelPath).toString());
		}
		return modelURI;
	}

	public void loadResource(URI modelURI, boolean create, boolean loadOnDemand) {
		if (loading)
			return;
		loading = true;
		ResourceSet resourceSet = ClassSupplierOSGi.getClassSupplier().getWorkspace().getResourceSet();
		File modelFile = new File(modelURI.toFileString());
		if (modelFile.exists())
			setResource(resourceSet.getResource(modelURI, loadOnDemand));
		else if (create && !eIsSet(ClassSupplierPackage.STATE__RESOURCE)) {
			setResource(resourceSet.createResource(modelURI));
			loading = false;
			return;
		}
		try {
			getResource().load(Collections.emptyMap());
		} catch (IOException e) {
			ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createWarningStatus(e));
		}
		loading = false;
	}

	public void saveResource() {
		if (saving)
			return;
		saving = true;
		if (!eIsSet(ClassSupplierPackage.STATE__RESOURCE))
			return;
		if (getPhase().getValue() >= Stage.MODELED_VALUE
				&& packagesDiffer(getDomainModel().getDynamic(), resource.getContents())) {
			boolean deliver = resource.eDeliver();
			resource.eSetDeliver(false);
			resource.getContents().clear();
			resource.getContents().add(EcoreUtil.copy(getDomainModel().getDynamic()));
			resource.eSetDeliver(deliver);
		}
		try {
			resource.save(Collections.emptyMap());
		} catch (IOException e) {
			ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createWarningStatus(e));
		} finally {
			saving = false;
		}
	}

	private boolean packagesDiffer(EPackage source, EList<EObject> target) {
		if (source == null && target == null)
			return false;
		EList<EPackage> existingEPackages = ECollections.newBasicEList();
		boolean whether = false;
		if (source == null)
			whether = true;
		if (target == null)
			whether = true;
		for (EObject eObject : target)
			if (eObject instanceof EPackage)
				existingEPackages.add((EPackage) eObject);
			else
				whether = true;
		return whether || !ModelUtil.ePackagesAreEqual(source, existingEPackages, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String save(IProgressMonitor monitor) throws Exception {
		Stage oldStage = getPhase();
		try {
			getContribution().getWorkspace().provision(monitor);
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			String projectName = getProjectName();
			IProject project = workspace.getRoot().getProject(projectName);
			if (!project.exists()) {
				ResourceUtils.createProject(project, ClassSupplierOSGi.NATURE_ID, monitor);
			} else {
				project.open(monitor);
			}
			Map<String, String> args = new HashMap<String, String>(2);
			Job.getJobManager().setProgressProvider(new EnterpriseDomainJob.JobProgressProvider());
			long runId = getTimestamp();
			Generator generator = new EcoreGenerator(getProject(), runId);
			Exporter exporter = new PDEPluginExporter(runId);

			EnterpriseDomainJob exporterJob = (EnterpriseDomainJob) exporter.getAdapter(EnterpriseDomainJob.class);
			exporterJob.setProject(getProject());
			exporterJob.setStateTimestamp(timestamp);
			exporter.setExportDestination(ResourceUtils.getExportDestination(getProject()));
			if (exporter.getVersion() == null) {
				Version version = getVersion();
				exporter.setVersion(version);
			}

			generator.setResourceSet(ClassSupplierOSGi.getClassSupplier().getWorkspace().getResourceSet());
			EnterpriseDomainJob generatorJob = ((EnterpriseDomainJob) generator.getAdapter(EnterpriseDomainJob.class));
			generatorJob.setProject(getProject());
			generatorJob.setStateTimestamp(timestamp);
			generatorJob.setProgressGroup(monitor, 1);
			generatorJob.setNextJob(exporterJob);

			EnterpriseDomainJob installJob = new OSGiInstaller(runId);
			exporterJob.setNextJob(installJob);

			EnterpriseDomainJob loadJob = new OSGiEPackageLoader(runId);
			loadJob.setStateTimestamp(timestamp);
			loadJob.addListener(new JobListener());
			loadJob.addListener();

			installJob.setStateTimestamp(timestamp);
			installJob.setNextJob(loadJob);

			monitor.beginTask("Save", 4);
			generatorJob.schedule();
			try {
				generatorJob.join();
				exporterJob.join();
				installJob.join();
				loadJob.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				monitor.setCanceled(true);
			}
			EnterpriseDomainJob.joinManualBuild(monitor);
		} catch (CoreException e) {
			if (e.getStatus().getSeverity() == IStatus.ERROR) {
				setPhase(oldStage);
			}
			ClassSupplierOSGi.getInstance().getLog().log(e.getStatus());
			monitor.setCanceled(true);
			throw e;
		} catch (Exception e) {
			setPhase(oldStage);
			monitor.setCanceled(true);
			throw e;
		} finally {
			monitor.done();
		}
		return commit(".");
	}

	private IProject getProject() {
		return ResourceUtils.getProject(getProjectName());
	}

	private final class JobListener implements EnterpriseDomainJobListener {
		@Override
		public void hookBefore(State contributionState) throws CoreException {
			try {
				GitUtil.add(contributionState.getProjectName(), ".");
			} catch (GitAPIException e) {
			}
		}
	}

	/**
	 * Initialize and load resource. Parent revision should be set.
	 */
	@Override
	public void load(boolean create) throws CoreException {
		loadResource(getModelURI(), create, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout() {
		if (getCommitIds().isEmpty()) {
			return;
		}
		int commitIdIndex = ListUtil.lastIndex(getCommitIds().size());
		checkout(getCommitIds().get(commitIdIndex));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(String commitId) {
		try {
			GitUtil.getRepositoryGit(getProjectName()).checkout().setName(commitId).setForce(true).call();
			setModelName(getContribution().getModelName());
			load(false);
		} catch (CheckoutConflictException e) {
			e.getConflictingPaths().clear();
		} catch (GitAPIException e) {
			ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(e));
		} catch (CoreException e) {
			ClassSupplierOSGi.getInstance().getLog().log(e.getStatus());
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String commit(String filepattern) throws Exception {
		GitUtil.add(getProjectName(), filepattern);
		String commitId = GitUtil.commit(getProjectName(), GitUtil.getCommitMessage(this, getTimestamp()));
		getCommitIds().add(commitId);
		return commitId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.STATE__STATE_CUSTOMIZERS:
			return ((InternalEList<?>) getStateCustomizers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	protected Adapter resourceToModelsAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			boolean deliver = eDeliver();
			eSetDeliver(false);
			if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS)
				switch (msg.getEventType()) {
				case Notification.ADD:
					if (msg.getNewValue() != null && msg.getNewValue() instanceof EPackage) {
						if (findExistingEPackage((EPackage) msg.getNewValue()) == null)
							getDomainModel().setDynamic(EcoreUtil.copy((EPackage) msg.getNewValue()));
					}
					break;
				case Notification.SET:
					if (msg.getOldValue() != null && msg.getOldValue() instanceof EPackage)
						getDomainModel().setDynamic(null);
					if (msg.getNewValue() != null && msg.getNewValue() instanceof EPackage)
						if (findExistingEPackage((EPackage) msg.getNewValue()) == null)
							getDomainModel().setDynamic(EcoreUtil.copy((EPackage) msg.getNewValue()));
					break;
				case Notification.REMOVE_MANY:
					if (msg.getOldValue() != null) {
						for (Object object : (Iterable<?>) msg.getOldValue())
							if (object instanceof EPackage)
								getDomainModel().setDynamic(null);
					}
					break;
				case Notification.REMOVE:
					if (msg.getOldValue() != null && msg.getOldValue() instanceof EPackage) {
						getDomainModel().setDynamic(null);
					}
					break;
				}
			eSetDeliver(deliver);
		}

		private EPackage findExistingEPackage(EPackage query) {
			EPackage ePackage = getDomainModel().getDynamic();
			if (ModelUtil.ePackagesAreEqual(ePackage, query, true))
				return ePackage;
			return null;
		}

	};

	protected Adapter modelsToResourceAdapter = new EContentAdapter() {

		@SuppressWarnings("unchecked")
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			if (notification.getNotifier() instanceof ModelPair
					&& notification.getFeatureID(ModelPair.class) == ClassSupplierPackage.MODEL_PAIR__DYNAMIC) {
				boolean deliver = getResource().eDeliver();
				getResource().eSetDeliver(false);
				if (notification.getEventType() == Notification.SET) {
					if (notification.getOldValue() != null) {
						EPackage dynamicEPackage = (EPackage) notification.getOldValue();
						EList<EObject> toRemove = ECollections.newBasicEList();
						for (EObject eObject : getResource().getContents())
							if (eObject instanceof EPackage
									&& ModelUtil.ePackagesAreEqual((EPackage) eObject, dynamicEPackage, true))
								toRemove.add(eObject);
						getResource().getContents().removeAll(toRemove);
					}
					if (notification.getNewValue() != null) {
						EPackage dynamicEPackage = (EPackage) notification.getNewValue();
						getResource().getContents().add(EcoreUtil.copy(dynamicEPackage));
					}
				}
				getResource().eSetDeliver(deliver);
			}
		}

	};

	@Override
	public void copyModel(Item from) {
		if (from instanceof Contribution && !((ContributionImpl) from).isStateSet())
			return;
		super.copyModel(from);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Contribution getContribution() {
		Contribution contribution = basicGetContribution();
		return contribution != null && contribution.eIsProxy()
				? (Contribution) eResolveProxy((InternalEObject) contribution) : contribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution basicGetContribution() {
		return getRevision().getContribution();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setContribution(Contribution newContribution) {
		newContribution.getRevision().getStateHistory().put(getTimestamp(), this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Revision getRevision() {
		Revision revision = basicGetRevision();
		return revision != null && revision.eIsProxy() ? (Revision) eResolveProxy((InternalEObject) revision)
				: revision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Revision basicGetRevision() {
		return (Revision) eContainer().eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setRevision(Revision newRevision) {
		if (newRevision != null)
			newRevision.getStateHistory().put(getTimestamp(), this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getJobFamily() {
		if (!eIsSet(ClassSupplierPackage.STATE__JOB_FAMILY))
			setJobFamily(getDeployableUnitName());
		return jobFamily;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setJobFamily(String newJobFamily) {
		String oldJobFamily = jobFamily;
		jobFamily = newJobFamily;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__JOB_FAMILY, oldJobFamily,
					jobFamily));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(Resource newResource) {
		Resource oldResource = resource;
		resource = newResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.STATE__RESOURCE, oldResource,
					resource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getCommitIds() {
		if (commitIds == null) {
			commitIds = new EDataTypeUniqueEList<String>(String.class, this, ClassSupplierPackage.STATE__COMMIT_IDS);
		}
		return commitIds;
	}

	@Override
	public EMap<StageQualifier, Customizer> getCustomizers() {
		return getStateCustomizers();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<StageQualifier, Customizer> getStateCustomizers() {
		if (stateCustomizers == null) {
			stateCustomizers = new EcoreEMap<StageQualifier, Customizer>(
					ClassSupplierPackage.Literals.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY,
					StageQualifierToCustomizerMapEntryImpl.class, this, ClassSupplierPackage.STATE__STATE_CUSTOMIZERS);
		}
		return stateCustomizers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getProjectName() {
		return getContribution().getProjectName();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setProjectName(String newProjectName) {
		getContribution().setProjectName(newProjectName);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setProjectVersion(IProgressMonitor monitor) throws CoreException {
		IBundleProjectService service = ((IBundleProjectService) ClassSupplierOSGi
				.getService(IBundleProjectService.class.getName()));
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(getProjectName());
		IBundleProjectDescription bundleProjectDescription = service.getDescription(project);
		bundleProjectDescription.setBundleVersion(getVersion());
		bundleProjectDescription.apply(monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(IProgressMonitor monitor) throws CoreException {
		ResourceUtils.deleteProject(ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()),
				new String[0]);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage find(EPackage ePackage, Stage stage) {
		if (stage.equals(Stage.MODELED)) {
			EPackage dynamicEPackage = getDomainModel().getDynamic();
			if (ModelUtil.ePackagesAreEqual(ePackage, dynamicEPackage, false))
				return dynamicEPackage;
		}
		if (stage.equals(Stage.GENERATED)) {
			EPackage generatedEPackage = getDomainModel().getGenerated();
			if (ModelUtil.ePackagesAreEqual(ePackage, generatedEPackage, false))
				return generatedEPackage;
		}
		return null;
	}

	EList<? extends EPackage> results;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassSupplierPackage.STATE__CONTRIBUTION:
			if (resolve)
				return getContribution();
			return basicGetContribution();
		case ClassSupplierPackage.STATE__REVISION:
			if (resolve)
				return getRevision();
			return basicGetRevision();
		case ClassSupplierPackage.STATE__TIMESTAMP:
			return getTimestamp();
		case ClassSupplierPackage.STATE__DEPLOYABLE_UNIT_NAME:
			return getDeployableUnitName();
		case ClassSupplierPackage.STATE__JOB_FAMILY:
			return getJobFamily();
		case ClassSupplierPackage.STATE__RESOURCE:
			return getResource();
		case ClassSupplierPackage.STATE__COMMIT_IDS:
			return getCommitIds();
		case ClassSupplierPackage.STATE__STATE_CUSTOMIZERS:
			if (coreType)
				return getStateCustomizers();
			else
				return getStateCustomizers().map();
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			return getProjectName();
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
		case ClassSupplierPackage.STATE__REVISION:
			setRevision((Revision) newValue);
			return;
		case ClassSupplierPackage.STATE__TIMESTAMP:
			setTimestamp((Integer) newValue);
			return;
		case ClassSupplierPackage.STATE__JOB_FAMILY:
			setJobFamily((String) newValue);
			return;
		case ClassSupplierPackage.STATE__RESOURCE:
			setResource((Resource) newValue);
			return;
		case ClassSupplierPackage.STATE__COMMIT_IDS:
			getCommitIds().clear();
			getCommitIds().addAll((Collection<? extends String>) newValue);
			return;
		case ClassSupplierPackage.STATE__STATE_CUSTOMIZERS:
			((EStructuralFeature.Setting) getStateCustomizers()).set(newValue);
			return;
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			setProjectName((String) newValue);
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
		case ClassSupplierPackage.STATE__REVISION:
			setRevision((Revision) null);
			return;
		case ClassSupplierPackage.STATE__TIMESTAMP:
			setTimestamp(TIMESTAMP_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__JOB_FAMILY:
			setJobFamily(JOB_FAMILY_EDEFAULT);
			return;
		case ClassSupplierPackage.STATE__RESOURCE:
			setResource((Resource) null);
			return;
		case ClassSupplierPackage.STATE__COMMIT_IDS:
			getCommitIds().clear();
			return;
		case ClassSupplierPackage.STATE__STATE_CUSTOMIZERS:
			getStateCustomizers().clear();
			return;
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			setProjectName(PROJECT_NAME_EDEFAULT);
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
		case ClassSupplierPackage.STATE__CONTRIBUTION:
			return basicGetContribution() != null;
		case ClassSupplierPackage.STATE__REVISION:
			return basicGetRevision() != null;
		case ClassSupplierPackage.STATE__TIMESTAMP:
			return timestamp != TIMESTAMP_EDEFAULT;
		case ClassSupplierPackage.STATE__DEPLOYABLE_UNIT_NAME:
			return DEPLOYABLE_UNIT_NAME_EDEFAULT == null ? getDeployableUnitName() != null
					: !DEPLOYABLE_UNIT_NAME_EDEFAULT.equals(getDeployableUnitName());
		case ClassSupplierPackage.STATE__JOB_FAMILY:
			return JOB_FAMILY_EDEFAULT == null ? jobFamily != null : !JOB_FAMILY_EDEFAULT.equals(jobFamily);
		case ClassSupplierPackage.STATE__RESOURCE:
			return resource != null;
		case ClassSupplierPackage.STATE__COMMIT_IDS:
			return commitIds != null && !commitIds.isEmpty();
		case ClassSupplierPackage.STATE__STATE_CUSTOMIZERS:
			return stateCustomizers != null && !stateCustomizers.isEmpty();
		case ClassSupplierPackage.STATE__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? getProjectName() != null
					: !PROJECT_NAME_EDEFAULT.equals(getProjectName());
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
		result.append(" (timestamp: ");
		result.append(timestamp);
		result.append(", jobFamily: ");
		result.append(jobFamily);
		result.append(", commitIds: ");
		result.append(commitIds);
		result.append(')');
		return result.toString();
	}

} // StateImpl