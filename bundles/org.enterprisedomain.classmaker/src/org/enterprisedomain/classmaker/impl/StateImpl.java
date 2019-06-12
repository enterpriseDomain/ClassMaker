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
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
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
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.m2m.internal.qvt.oml.cst.parser.NLS;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.core.WrappingProgressMonitor;
import org.enterprisedomain.classmaker.jobs.EnterpriseDomainJob;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.enterprisedomain.classmaker.jobs.export.AbstractExporter;
import org.enterprisedomain.classmaker.util.ListUtil;
import org.enterprisedomain.classmaker.util.ModelUtil;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getPackageClassName
 * <em>Package Class Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getRequiredPlugins
 * <em>Required Plugins</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getRevision
 * <em>Revision</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getTimestamp
 * <em>Timestamp</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getDeployableUnitName
 * <em>Deployable Unit Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getJobFamily
 * <em>Job Family</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getResource
 * <em>Resource</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getCommitIds
 * <em>Commit Ids</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getCommitId
 * <em>Commit Id</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getStateCustomizers
 * <em>State Customizers</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getProjectName
 * <em>Project Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#isMaking
 * <em>Making</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateImpl extends ItemImpl implements State {

	/**
	 * The default value of the '{@link #getPackageClassName() <em>Package Class
	 * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPackageClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageClassName() <em>Package Class
	 * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPackageClassName()
	 * @generated
	 * @ordered
	 */
	protected String packageClassName = PACKAGE_CLASS_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequiredPlugins() <em>Required
	 * Plugins</em>}' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRequiredPlugins()
	 * @generated
	 * @ordered
	 */
	protected EList<String> requiredPlugins;

	public class StateAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == ClassMakerPackage.STATE__MODEL_NAME
					&& msg.getEventType() == Notification.SET && msg.getNewStringValue() != null
					&& eIsSet(ClassMakerPackage.STATE__CONTRIBUTION))
				setProjectName(
						getContribution().getWorkspace().getService().computeProjectName(msg.getNewStringValue()));
			// if (eContainer() != null && eIsSet(ClassMakerPackage.STATE__REVISION)
			// && eIsSet(ClassMakerPackage.STATE__CONTRIBUTION) &&
			// !getContribution().isSavingResource()
			// && msg.getFeatureID(State.class) == ClassMakerPackage.STATE__RESOURCE
			// && msg.getEventType() == Notification.SET && msg.getNewValue() != null) {
			// Resource resource = (Resource) msg.getNewValue();
			// resource.eAdapters().remove(resourceToModelsAdapter);
			// resource.eAdapters().add(resourceToModelsAdapter);
			// }

		}

	}

	public class MakingCompletionListener extends CompletionListenerImpl {

		@Override
		public void completed(Project result) throws Exception {
			if (((Contribution) result).getState().equals(StateImpl.this)) {
				setMaking(false);
				synchronized (makingLock) {
					makingLock.notifyAll();
				}
			}
		}

	}

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final int TIMESTAMP_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected int timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated NOT
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = Version.emptyVersion;

	/**
	 * The default value of the '{@link #getDeployableUnitName() <em>Deployable Unit
	 * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDeployableUnitName()
	 * @generated
	 * @ordered
	 */
	protected static final String DEPLOYABLE_UNIT_NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getJobFamily() <em>Job Family</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getJobFamily()
	 * @generated
	 * @ordered
	 */
	protected static final String JOB_FAMILY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJobFamily() <em>Job Family</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getJobFamily()
	 * @generated
	 * @ordered
	 */
	protected String jobFamily = JOB_FAMILY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected Resource resource;

	/**
	 * The cached value of the '{@link #getCommitIds() <em>Commit Ids</em>}'
	 * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCommitIds()
	 * @generated
	 * @ordered
	 */
	protected EList<String> commitIds;

	/**
	 * The default value of the '{@link #getCommitId() <em>Commit Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCommitId()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMIT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCommitId() <em>Commit Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCommitId()
	 * @generated
	 * @ordered
	 */
	protected String commitId = COMMIT_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStateCustomizers() <em>State
	 * Customizers</em>}' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStateCustomizers()
	 * @generated
	 * @ordered
	 */
	protected EMap<StageQualifier, Customizer> stateCustomizers;

	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isMaking() <em>Making</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMaking()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MAKING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMaking() <em>Making</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMaking()
	 * @generated
	 * @ordered
	 */
	protected boolean making = MAKING_EDEFAULT;

	protected String language = LANGUAGE_EDEFAULT;

	private boolean loading = false;

	private Object makingLock = new Object();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected StateImpl() {
		super();
		eAdapters().add(new StateAdapter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getPackageClassName() {
		return packageClassName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPackageClassName(String newPackageClassName) {
		String oldPackageClassName = packageClassName;
		packageClassName = newPackageClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__PACKAGE_CLASS_NAME,
					oldPackageClassName, packageClassName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<String> getRequiredPlugins() {
		if (requiredPlugins == null) {
			requiredPlugins = new EDataTypeUniqueEList<String>(String.class, this,
					ClassMakerPackage.STATE__REQUIRED_PLUGINS);
		}
		return requiredPlugins;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setTimestamp(int newTimestamp) {
		int oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__TIMESTAMP, oldTimestamp,
					timestamp));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getDeployableUnitName() {
		String separator = "_"; //$NON-NLS-1$
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

	@Override
	public String initialize(boolean commit) {
		if (!eIsSet(ClassMakerPackage.STATE__MODEL_NAME))
			super.initialize(commit);
		if (eIsSet(ClassMakerPackage.STATE__CONTRIBUTION)
				&& getContribution().eIsSet(ClassMakerPackage.Literals.PROJECT__PROJECT_NAME)
				&& ResourceUtils.isProjectExists(getProjectName())) {
			URI modelURI = getModelURI();
			loadResource(modelURI, !eIsSet(ClassMakerPackage.STATE__RESOURCE), true);
			saveResource();
			setPhase(Stage.MODELED);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			if (commit)
				try {
					String[] segments = modelURI.deresolve(URI.createFileURI(root.getRawLocation().toString()))
							.segments();
					String[] path = new String[segments.length - 2];
					System.arraycopy(segments, 2, path, 0, segments.length - 2);
					add(URI.createHierarchicalURI(path, null, null).toString());
					String result = commit();
					return result;
				} catch (Exception e) {
					ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
					return null;
				}
			else {
				@SuppressWarnings("unchecked")
				SCMOperator<Git> operator = (SCMOperator<Git>) getContribution().getWorkspace().getSCMRegistry()
						.get(getProjectName());
				try {
					Git git = operator.getRepositorySCM();
					Ref branch = git.getRepository().findRef(getRevision().getVersion().toString());
					LogCommand log = git.log();
					log.add(branch.getObjectId());
					Iterable<RevCommit> commits = log.call();
					for (RevCommit c : commits) {
						if (operator.decodeTimestamp(c.getShortMessage()) == getTimestamp()) {
							String id = c.getId().toString();
							getCommitIds().add(id);
							setCommitId(id);
						}
					}
				} catch (Exception e) {
				} finally {
					try {
						operator.ungetRepositorySCM();
					} catch (Exception e) {
						ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
					}
				}
				getContribution().checkout(getRevision().getVersion(), getTimestamp(), getCommitId());
			}
		}
		return getCommitId(); // $NON-NLS-1$
	}

	private URI modelURI;

	private URI getModelURI() {
		if (modelURI == null) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject project = root.getProject(
					eIsSet(ClassMakerPackage.STATE__PROJECT_NAME) ? getProjectName() : getModelName().toLowerCase());
			IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
			IFolder folder = project.getFolder(ResourceUtils.getModelFolderName());
			if (!folder.exists()) {
				try {
					folder.create(true, true, monitor);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				} finally {
					monitor.done();
				}
			}
			modelURI = URI.createFileURI(root.getRawLocation().append(getContribution().getResourcePath()).toString());
		}
		return modelURI;
	}

	@Override
	public String getModelName() {
		if (super.getModelName() == null && eIsSet(ClassMakerPackage.STATE__PROJECT_NAME))
			setModelName(getProjectName());
		else if (!eIsSet(ClassMakerPackage.STATE__PROJECT_NAME) && eIsSet(ClassMakerPackage.STATE__REVISION))
			setModelName(getRevision().getModelName());
		return super.getModelName();
	}

	public void loadResource(URI modelURI, boolean create, boolean loadOnDemand) {
		if (loading)
			return;
		loading = true;
		boolean created = false;
		ResourceSet resourceSet = getContribution().getWorkspace().getResourceSet();
		File modelFile = new File(modelURI.toFileString());
		if (modelFile.exists())
			setResource(resourceSet.getResource(modelURI, loadOnDemand));
		else if (create && !eIsSet(ClassMakerPackage.STATE__RESOURCE)) {
			setResource(resourceSet.createResource(modelURI));
			created = true;
		}
		getResource().eAdapters().remove(resourceToModelsAdapter);
		getResource().eAdapters().add(resourceToModelsAdapter);
		getDomainModel().eAdapters().remove(modelsToResourceAdapter);
		getDomainModel().eAdapters().add(modelsToResourceAdapter);
		if (created) {
			loading = false;
			return;
		}
		try {
			getResource().load(Collections.emptyMap());
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
		}
		if (!getResource().getContents().isEmpty()) {
			getDomainModel().setDynamic(EcoreUtil.copy((EPackage) getResource().getContents().get(0)));
		}
		loading = false;
	}

	public void saveResource() {
		if (getContribution().isSavingResource() || isMaking())
			return;
		getContribution().setSavingResource(true);
		setMaking(true);
		if (!eIsSet(ClassMakerPackage.STATE__RESOURCE))
			return;
		if (getPhase().getValue() >= Stage.MODELED_VALUE && getDomainModel().getDynamic() != null
				&& getDomainModel().getDynamic().eResource() != null) {
			Resource importSource = getDomainModel().getDynamic().eResource();
			try {
				importSource.load(Collections.emptyMap());
			} catch (IOException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
			}
			boolean deliver = resource.eDeliver();
			resource.eSetDeliver(false);
			resource.getContents().clear();
			resource.getContents().addAll(EcoreUtil.copyAll(importSource.getContents()));
			importSource.unload();
			resource.eSetDeliver(deliver);
			ClassMakerPlugin.getInstance().getLog()
					.log(ClassMakerPlugin.createInfoStatus(NLS.bind(Messages.ResourceImported, importSource.getURI())));
		} else if (getPhase().getValue() >= Stage.MODELED_VALUE && getDomainModel().getDynamic() != null
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
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
		} finally {
			if (isMaking())
				setMaking(false);
			getContribution().setSavingResource(false);
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createGenerator() {
		return create(ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.generator"), //$NON-NLS-1$
				getProject(), getTimestamp());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createExporter() {
		return create(ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.exporter"), //$NON-NLS-1$
				Integer.valueOf(getTimestamp()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createInstaller() {
		return create(ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.installer"), //$NON-NLS-1$
				Integer.valueOf(getTimestamp()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createModelLoader() {
		return create(ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.loader"), //$NON-NLS-1$
				Integer.valueOf(getTimestamp()));
	}

	private Worker create(StageQualifier stage, Object... customizerArgs) {
		SortedSet<Customizer> customizer = new TreeSet<Customizer>(new Customizer.CustomizerComparator());
		for (StageQualifier filter : getStateCustomizers().keySet())
			if (stage.equals(filter))
				customizer.add(getStateCustomizers().get(filter));
		for (StageQualifier filter : getContribution().getWorkspace().getCustomizers().keySet())
			if (filter.equals(stage))
				customizer.add(getContribution().getWorkspace().getCustomizers().get(filter));
		if (customizer.isEmpty())
			return null;
		return (Worker) (customizer.last().customize(ECollections.asEList(customizerArgs)));
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
	public String make(IProgressMonitor monitor) throws Exception {
		synchronized (makingLock) {
			IProgressMonitor wrappingMonitor = new WrappingProgressMonitor(monitor);
			CompletionListener completionListener = new MakingCompletionListener();
			getContribution().addCompletionListener(completionListener);
			Stage oldStage = getPhase();
			try {
				if (isMaking())
					if (!getCommitIds().isEmpty())
						return ListUtil.getLast(getCommitIds());
					else
						return ""; //$NON-NLS-1$
				saveResource();

				try {
					getContribution().getWorkspace().provision(wrappingMonitor);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				String projectName = null;
				if (eIsSet(ClassMakerPackage.STATE__PROJECT_NAME))
					projectName = getProjectName();
				else
					projectName = getModelName().toLowerCase();
				IProject project = workspace.getRoot().getProject(projectName);
				if (project.exists()) {
					project.open(wrappingMonitor);
				} else {
					ResourceUtils.createProject(project, ClassMakerPlugin.NATURE_ID, wrappingMonitor);
				}

				Worker exporter = createExporter();

				EnterpriseDomainJob exporterJob = getJob(exporter);
				exporterJob.setContributionState(this);
				exporterJob.setProject(getProject());
				exporter.getProperties().put(AbstractExporter.EXPORT_DESTINATION_PROP,
						ResourceUtils.getExportDestination(getProject()).toString());

				Worker generator = createGenerator();
				EnterpriseDomainJob generatorJob = getJob(generator);
				generatorJob.setResourceSet(getContribution().getWorkspace().getResourceSet());
				generatorJob.setContributionState(this);
				generatorJob.setProject(getProject());
				generatorJob.setProgressGroup(wrappingMonitor, 1);
				generatorJob.setNextJob(exporterJob);

				EnterpriseDomainJob installJob = getJob(createInstaller());
				installJob.setContributionState(this);
				exporterJob.setNextJob(installJob);

				EnterpriseDomainJob loadJob = getJob(createModelLoader());
				loadJob.setContributionState(this);
				loadJob.addListener();

				installJob.setNextJob(loadJob);

				monitor.beginTask(Messages.Save, 4);
				generatorJob.schedule();
				try {
					generatorJob.join();
					exporterJob.join();
					installJob.join();
					add("."); //$NON-NLS-1$
					loadJob.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
					monitor.setCanceled(true);
				}
			} catch (CoreException e) {
				if (e.getStatus().getSeverity() == IStatus.ERROR) {
					setPhase(oldStage);
				}
				ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				wrappingMonitor.setCanceled(true);
				throw e;
			} catch (Exception e) {
				setPhase(oldStage);
				wrappingMonitor.setCanceled(true);
				throw e;
			} finally {
				setMaking(false);
				wrappingMonitor.done();
			}
			if (!monitor.isCanceled() || (!getPhase().equals(Stage.LOADED)
					&& !getDomainModel().eIsSet(ClassMakerPackage.Literals.MODEL_PAIR__GENERATED))) {
				makingLock.wait(7000);
				Thread.yield();
			}
			getContribution().removeCompletionListener(completionListener);
			String result = commit(); // $NON-NLS-1$
			monitor.done();
			return result;
		}

	}

	private EnterpriseDomainJob getJob(Worker worker) {
		return (EnterpriseDomainJob) worker.getAdapter(EnterpriseDomainJob.class);
	}

	private IProject getProject() {
		return ResourceUtils.getProject(getProjectName());
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
		if (!eIsSet(ClassMakerPackage.STATE__COMMIT_ID))
			setCommitId(ListUtil.getLast(getCommitIds()));
		checkout(getCommitId());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(String commitId) {
		try {
			@SuppressWarnings("unchecked")
			SCMOperator<Git> operator = (SCMOperator<Git>) getContribution().getWorkspace().getSCMRegistry()
					.get(getProjectName());
			setCommitId(commitId);
			operator.checkout(getVersion().toString(), getCommitId());
			copyModel(getContribution());
			load(false);
		} catch (CheckoutConflictException e) {
			e.getConflictingPaths().clear();
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		} catch (Exception e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void add(String filepattern) throws Exception {
		@SuppressWarnings("unchecked")
		SCMOperator<Git> operator = (SCMOperator<Git>) getContribution().getWorkspace().getSCMRegistry()
				.get(getProjectName());
		operator.add(filepattern);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String commit() throws Exception {
		@SuppressWarnings("unchecked")
		SCMOperator<Git> operator = (SCMOperator<Git>) ClassMakerPlugin.getClassMaker().getWorkspace().getSCMRegistry()
				.get(getProjectName());
		String commitId = null;
		commitId = operator.commit(operator.encodeCommitMessage(this));
		getCommitIds().add(commitId);
		setCommitId(commitId);
		return commitId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			return ((InternalEList<?>) getStateCustomizers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	private boolean resourceModelsSynchronizing = false;

	protected Adapter resourceToModelsAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (resourceModelsSynchronizing)
				return;
			resourceModelsSynchronizing = true;
			boolean deliver = eDeliver();
			eSetDeliver(false);
			if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS)
				switch (msg.getEventType()) {
				case Notification.ADD:
					if (msg.getNewValue() != null && msg.getNewValue() instanceof EPackage) {
						if (findExistingEPackage((EPackage) msg.getNewValue()) == null) {
							getDomainModel().setDynamic(copyEPackage((EPackage) msg.getNewValue()));
						}
					}
					break;
				case Notification.SET:
					if (msg.getOldValue() != null && msg.getOldValue() instanceof EPackage)
						getDomainModel().setDynamic(null);
					if (msg.getNewValue() != null && msg.getNewValue() instanceof EPackage)
						if (findExistingEPackage((EPackage) msg.getNewValue()) == null) {
							getDomainModel().setDynamic(copyEPackage((EPackage) msg.getNewValue()));
						}
					break;
				case Notification.REMOVE_MANY:
					if (msg.getOldValue() != null) {
						for (Object object : (Iterable<?>) msg.getOldValue())
							if (object instanceof EPackage) {
								getDomainModel().setDynamic(null);
							}
					}
					break;
				case Notification.REMOVE:
					if (msg.getOldValue() != null && msg.getOldValue() instanceof EPackage) {
						getDomainModel().setDynamic(null);
					}
					break;
				}
			eSetDeliver(deliver);
			resourceModelsSynchronizing = false;
		}

		private EPackage copyEPackage(EPackage ePackage) {
			EPackage copy = EcoreUtil.copy(ePackage);
			return copy;
		}

		private EPackage findExistingEPackage(EPackage query) {
			EPackage ePackage = getDomainModel().getDynamic();
			if (ModelUtil.ePackagesAreEqual(ePackage, query, true))
				return ePackage;
			return null;
		}

	};

	protected Adapter modelsToResourceAdapter = new EContentAdapter() {

		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			if (resourceModelsSynchronizing)
				return;
			if (notification.getNotifier() instanceof ModelPair
					&& notification.getFeatureID(ModelPair.class) == ClassMakerPackage.MODEL_PAIR__DYNAMIC) {
				resourceModelsSynchronizing = true;
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
					if (notification.getNewValue() != null && notification.getPosition() >= Notification.NO_INDEX) {
						EPackage dynamicEPackage = (EPackage) notification.getNewValue();
						if (getResource().getContents().isEmpty()) {
							getResource().getContents().add(EcoreUtil.copy(dynamicEPackage));
						} else
							getResource().getContents().set(0, EcoreUtil.copy(dynamicEPackage));
					}
				}
				getResource().eSetDeliver(deliver);
				resourceModelsSynchronizing = false;
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
	 * 
	 * @generated NOT
	 */
	public Contribution basicGetContribution() {
		if (eIsSet(ClassMakerPackage.STATE__REVISION))
			return getRevision().getContribution();
		else
			return null;
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
	 * 
	 * @generated
	 */
	@Override
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
		if (eContainer() != null)
			return (Revision) eContainer().eContainer();
		else
			return null;
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
		if (!eIsSet(ClassMakerPackage.STATE__JOB_FAMILY))
			setJobFamily(getDeployableUnitName());
		return jobFamily;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setJobFamily(String newJobFamily) {
		String oldJobFamily = jobFamily;
		jobFamily = newJobFamily;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__JOB_FAMILY, oldJobFamily,
					jobFamily));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Resource getResource() {
		return resource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setResource(Resource newResource) {
		Resource oldResource = resource;
		resource = newResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__RESOURCE, oldResource,
					resource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<String> getCommitIds() {
		if (commitIds == null) {
			commitIds = new EDataTypeUniqueEList<String>(String.class, this, ClassMakerPackage.STATE__COMMIT_IDS);
		}
		return commitIds;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCommitId() {
		return commitId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCommitId(String newCommitId) {
		String oldCommitId = commitId;
		commitId = newCommitId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__COMMIT_ID, oldCommitId,
					commitId));
	}

	@Override
	public EMap<StageQualifier, Customizer> getCustomizers() {
		return getStateCustomizers();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<StageQualifier, Customizer> getStateCustomizers() {
		if (stateCustomizers == null) {
			stateCustomizers = new EcoreEMap<StageQualifier, Customizer>(
					ClassMakerPackage.Literals.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY,
					StageQualifierToCustomizerMapEntryImpl.class, this, ClassMakerPackage.STATE__STATE_CUSTOMIZERS);
		}
		return stateCustomizers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getProjectName() {
		if (eIsSet(ClassMakerPackage.STATE__CONTRIBUTION))
			return getContribution().getProjectName();
		else
			return null;
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
	 * @generated
	 */
	@Override
	public boolean isMaking() {
		return making;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMaking(boolean newMaking) {
		boolean oldMaking = making;
		making = newMaking;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__MAKING, oldMaking, making));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setProjectVersion(IProgressMonitor monitor) throws CoreException {
		SubMonitor pm = SubMonitor.convert(monitor, 1);
		pm.setTaskName(Messages.TaskNameSetProjectVersion);
		pm.subTask(Messages.SubTaskNameSetProjectVersion);
		SubMonitor m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
		try {
			IBundleProjectService service = ((IBundleProjectService) ClassMakerPlugin
					.getService(IBundleProjectService.class.getName()));
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProject project = workspace.getRoot().getProject(getProjectName());
			IBundleProjectDescription bundleProjectDescription = service.getDescription(project);
			bundleProjectDescription.setBundleVersion(getVersion());
			bundleProjectDescription.apply(m);
		} finally {
			m.done();
			pm.done();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(IProgressMonitor monitor) throws CoreException {
		@SuppressWarnings("unchecked")
		SCMOperator<Git> operator = (SCMOperator<Git>) ClassMakerPlugin.getClassMaker().getWorkspace().getSCMRegistry()
				.get(getProjectName());
		try {
			operator.deleteProject();
			try {
				operator.checkoutOrphan(getVersion().toString(), getTimestamp());
			} catch (Exception e) {
				throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
			}
		} finally {
			monitor.done();
		}

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
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.STATE__PACKAGE_CLASS_NAME:
			return getPackageClassName();
		case ClassMakerPackage.STATE__REQUIRED_PLUGINS:
			return getRequiredPlugins();
		case ClassMakerPackage.STATE__REVISION:
			if (resolve)
				return getRevision();
			return basicGetRevision();
		case ClassMakerPackage.STATE__TIMESTAMP:
			return getTimestamp();
		case ClassMakerPackage.STATE__DEPLOYABLE_UNIT_NAME:
			return getDeployableUnitName();
		case ClassMakerPackage.STATE__JOB_FAMILY:
			return getJobFamily();
		case ClassMakerPackage.STATE__RESOURCE:
			return getResource();
		case ClassMakerPackage.STATE__COMMIT_IDS:
			return getCommitIds();
		case ClassMakerPackage.STATE__COMMIT_ID:
			return getCommitId();
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			if (coreType)
				return getStateCustomizers();
			else
				return getStateCustomizers().map();
		case ClassMakerPackage.STATE__PROJECT_NAME:
			return getProjectName();
		case ClassMakerPackage.STATE__MAKING:
			return isMaking();
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
		case ClassMakerPackage.STATE__PACKAGE_CLASS_NAME:
			setPackageClassName((String) newValue);
			return;
		case ClassMakerPackage.STATE__REQUIRED_PLUGINS:
			getRequiredPlugins().clear();
			getRequiredPlugins().addAll((Collection<? extends String>) newValue);
			return;
		case ClassMakerPackage.STATE__REVISION:
			setRevision((Revision) newValue);
			return;
		case ClassMakerPackage.STATE__TIMESTAMP:
			setTimestamp((Integer) newValue);
			return;
		case ClassMakerPackage.STATE__JOB_FAMILY:
			setJobFamily((String) newValue);
			return;
		case ClassMakerPackage.STATE__RESOURCE:
			setResource((Resource) newValue);
			return;
		case ClassMakerPackage.STATE__COMMIT_IDS:
			getCommitIds().clear();
			getCommitIds().addAll((Collection<? extends String>) newValue);
			return;
		case ClassMakerPackage.STATE__COMMIT_ID:
			setCommitId((String) newValue);
			return;
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			((EStructuralFeature.Setting) getStateCustomizers()).set(newValue);
			return;
		case ClassMakerPackage.STATE__PROJECT_NAME:
			setProjectName((String) newValue);
			return;
		case ClassMakerPackage.STATE__MAKING:
			setMaking((Boolean) newValue);
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
		case ClassMakerPackage.STATE__PACKAGE_CLASS_NAME:
			setPackageClassName(PACKAGE_CLASS_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__REQUIRED_PLUGINS:
			getRequiredPlugins().clear();
			return;
		case ClassMakerPackage.STATE__REVISION:
			setRevision((Revision) null);
			return;
		case ClassMakerPackage.STATE__TIMESTAMP:
			setTimestamp(TIMESTAMP_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__JOB_FAMILY:
			setJobFamily(JOB_FAMILY_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__RESOURCE:
			setResource((Resource) null);
			return;
		case ClassMakerPackage.STATE__COMMIT_IDS:
			getCommitIds().clear();
			return;
		case ClassMakerPackage.STATE__COMMIT_ID:
			setCommitId(COMMIT_ID_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			getStateCustomizers().clear();
			return;
		case ClassMakerPackage.STATE__PROJECT_NAME:
			setProjectName(PROJECT_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__MAKING:
			setMaking(MAKING_EDEFAULT);
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
		case ClassMakerPackage.STATE__PACKAGE_CLASS_NAME:
			return PACKAGE_CLASS_NAME_EDEFAULT == null ? packageClassName != null
					: !PACKAGE_CLASS_NAME_EDEFAULT.equals(packageClassName);
		case ClassMakerPackage.STATE__REQUIRED_PLUGINS:
			return requiredPlugins != null && !requiredPlugins.isEmpty();
		case ClassMakerPackage.STATE__REVISION:
			return basicGetRevision() != null;
		case ClassMakerPackage.STATE__TIMESTAMP:
			return timestamp != TIMESTAMP_EDEFAULT;
		case ClassMakerPackage.STATE__DEPLOYABLE_UNIT_NAME:
			return DEPLOYABLE_UNIT_NAME_EDEFAULT == null ? getDeployableUnitName() != null
					: !DEPLOYABLE_UNIT_NAME_EDEFAULT.equals(getDeployableUnitName());
		case ClassMakerPackage.STATE__JOB_FAMILY:
			return JOB_FAMILY_EDEFAULT == null ? jobFamily != null : !JOB_FAMILY_EDEFAULT.equals(jobFamily);
		case ClassMakerPackage.STATE__RESOURCE:
			return resource != null;
		case ClassMakerPackage.STATE__COMMIT_IDS:
			return commitIds != null && !commitIds.isEmpty();
		case ClassMakerPackage.STATE__COMMIT_ID:
			return COMMIT_ID_EDEFAULT == null ? commitId != null : !COMMIT_ID_EDEFAULT.equals(commitId);
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			return stateCustomizers != null && !stateCustomizers.isEmpty();
		case ClassMakerPackage.STATE__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? getProjectName() != null
					: !PROJECT_NAME_EDEFAULT.equals(getProjectName());
		case ClassMakerPackage.STATE__MAKING:
			return making != MAKING_EDEFAULT;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (packageClassName: ");
		result.append(packageClassName);
		result.append(", requiredPlugins: ");
		result.append(requiredPlugins);
		result.append(", timestamp: ");
		result.append(timestamp);
		result.append(", jobFamily: ");
		result.append(jobFamily);
		result.append(", commitIds: ");
		result.append(commitIds);
		result.append(", commitId: ");
		result.append(commitId);
		result.append(", making: ");
		result.append(making);
		result.append(')');
		return result.toString();
	}

	@Override
	public boolean contains(ISchedulingRule otherRule) {
		if (otherRule instanceof State)
			return getVersion().equals(((State) otherRule).getVersion())
					&& getRevision().equals(((State) otherRule).getRevision())
					&& getTimestamp() == ((State) otherRule).getTimestamp();
		return false;
	}

	@Override
	public boolean isConflicting(ISchedulingRule otherRule) {
		if (otherRule instanceof State)
			return getVersion().equals(((State) otherRule).getVersion())
					&& getRevision().equals(((State) otherRule).getRevision())
					&& getTimestamp() == ((State) otherRule).getTimestamp()
					&& getCommitId() == ((State) otherRule).getCommitId();
		return false;
	}

	@Override
	public void build(IProgressMonitor monitor) throws CoreException {
		if (getPhase().equals(Stage.LOADED)) {
			EnterpriseDomainJob installJob = getJob(createInstaller());
			installJob.setContributionState(this);

			EnterpriseDomainJob loadJob = getJob(createModelLoader());
			loadJob.setContributionState(this);
			loadJob.addListener();

			installJob.setNextJob(loadJob);

			installJob.schedule();
			try {
				installJob.join();
				add("."); //$NON-NLS-1$
				loadJob.join();
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
				monitor.setCanceled(true);
			} catch (NullPointerException e) {
				try {
					make(monitor);
				} catch (Exception ex) {
					throw new CoreException(ClassMakerPlugin.createErrorStatus(ex));
				}
			} catch (Exception e) {
				throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
			}
		}
	}

} // StateImpl