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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.NotifyingInternalEListImpl;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.ReflogCommand;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.ReflogEntry;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ListUtil;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Contribution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getModelName
 * <em>Model Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getPhase
 * <em>Phase</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getVersion
 * <em>Version</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getLanguage
 * <em>Language</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getDomainModel
 * <em>Domain Model</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getCustomizers
 * <em>Customizers</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getParent
 * <em>Parent</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getLocale
 * <em>Locale</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getContribution
 * <em>Contribution</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getDependencies
 * <em>Dependencies</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getState
 * <em>State</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getLatestVersion
 * <em>Latest Version</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getModelResourceAdapter
 * <em>Model Resource Adapter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContributionImpl extends ProjectImpl implements Contribution {

	/**
	 * The default value of the '{@link #getModelName() <em>Model Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getModelName()
	 * @generated
	 * @ordered
	 */
	protected static final String MODEL_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getModelName() <em>Model Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getModelName()
	 * @generated
	 * @ordered
	 */
	protected String modelName = MODEL_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getPhase() <em>Phase</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected static final Stage PHASE_EDEFAULT = Stage.DEFINED;
	/**
	 * The cached value of the '{@link #getPhase() <em>Phase</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected Stage phase = PHASE_EDEFAULT;
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
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected Version version = VERSION_EDEFAULT;
	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected String language = LANGUAGE_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDomainModel() <em>Domain Model</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDomainModel()
	 * @generated
	 * @ordered
	 */
	protected ModelPair domainModel;
	/**
	 * The default value of the '{@link #getLocale() <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLocale()
	 * @generated
	 * @ordered
	 */
	protected static final Locale LOCALE_EDEFAULT = (Locale) ClassMakerFactory.eINSTANCE
			.createFromString(ClassMakerPackage.eINSTANCE.getLocale(), ""); //$NON-NLS-1$
	/**
	 * The cached value of the '{@link #getLocale() <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLocale()
	 * @generated
	 * @ordered
	 */
	protected Locale locale = LOCALE_EDEFAULT;
	/**
	 * The default value of the '{@link #getLatestVersion() <em>Latest
	 * Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLatestVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version LATEST_VERSION_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getModelResourceAdapter() <em>Model Resource
	 * Adapter</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getModelResourceAdapter()
	 * @generated
	 * @ordered
	 */
	protected ResourceAdapter modelResourceAdapter;

	protected CompletionListener completionListener = new CompletionListenerImpl() {

		@Override
		public void completed(Project result) throws Exception {
			addResourceChangeListener(getResourceReloadListener());
		}

	};

	protected Adapter stateModelAdapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == ClassMakerPackage.STATE__RESOURCE
					&& msg.getEventType() == Notification.SET) {
				if (isStateSet() && getState().eIsSet(ClassMakerPackage.Literals.STATE__RESOURCE)) {
					ResourceAdapter modelResourceAdapter = null;
					if (eIsSet(ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER)) {
						modelResourceAdapter = getModelResourceAdapter();
					} else {
						modelResourceAdapter = ClassMakerFactory.eINSTANCE.createResourceAdapter();
					}
					modelResourceAdapter.setResource(getState().getResource());
					modelResourceAdapter.setProject(ContributionImpl.this);
				}
			}
		}
	};

	private LoadingEList children;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ContributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.CONTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setModelName(String newModelName) {
		String oldModelName = modelName;
		modelName = newModelName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CONTRIBUTION__MODEL_NAME,
					oldModelName, modelName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Stage getPhase() {
		if (isStateSet())
			return getState().getPhase();
		return Stage.DEFINED;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPhase(Stage newPhase) {
		Stage oldPhase = phase;
		phase = newPhase == null ? PHASE_EDEFAULT : newPhase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CONTRIBUTION__PHASE, oldPhase,
					phase));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State getState() {
		State state = basicGetState();
		return state != null && state.eIsProxy() ? (State) eResolveProxy((InternalEObject) state) : state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public NotificationChain basicSetState(State newState, NotificationChain msgs) {
		State oldState = basicGetState();
		getRevision().getStateHistory().put(newState.getTimestamp(), newState);
		if (eNotificationRequired())
			msgs.add(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CONTRIBUTION__STATE, oldState,
					newState));
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State basicGetState() {
		if (eIsSet(ClassMakerPackage.CONTRIBUTION__REVISIONS) && eIsSet(ClassMakerPackage.CONTRIBUTION__REVISION))
			return getRevision().getState();
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Version getLatestVersion() {
		if (!isRevisionSet() || getRevisions().isEmpty())
			return Version.emptyVersion;
		List<org.osgi.framework.Version> sorted = new ArrayList<org.osgi.framework.Version>();
		sorted.addAll((Collection<? extends org.osgi.framework.Version>) getRevisions().keySet());
		Collections.sort(sorted, new Comparator<org.osgi.framework.Version>() {

			@Override
			public int compare(org.osgi.framework.Version o1, org.osgi.framework.Version o2) {
				return o1.getMajor() - o2.getMajor() + o1.getMinor() - o2.getMinor() + o1.getMicro() - o2.getMicro();
			}
		});
		return ListUtil.getLast(sorted);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceAdapter getModelResourceAdapter() {
		return modelResourceAdapter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetModelResourceAdapter(ResourceAdapter newModelResourceAdapter,
			NotificationChain msgs) {
		ResourceAdapter oldModelResourceAdapter = modelResourceAdapter;
		modelResourceAdapter = newModelResourceAdapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER, oldModelResourceAdapter,
					newModelResourceAdapter);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void copyModel(Item from) {
		setModelName(from.getModelName());
		setLanguage(from.getLanguage());
		EPackage ePackage = from.getDomainModel().getDynamic();
		if (ePackage != null)
			getDomainModel().setDynamic(EcoreUtil.copy(ePackage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.CONTRIBUTION__DOMAIN_MODEL:
			if (domainModel != null)
				msgs = ((InternalEObject) domainModel).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.CONTRIBUTION__DOMAIN_MODEL, null, msgs);
			return basicSetDomainModel((ModelPair) otherEnd, msgs);
		case ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER:
			if (modelResourceAdapter != null)
				msgs = ((InternalEObject) modelResourceAdapter).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER, null, msgs);
			return basicSetModelResourceAdapter((ResourceAdapter) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Locale getLocale() {
		if (locale == null) {
			String language = getLanguage();
			if (language == null) {
				return Locale.getDefault();
			} else {
				locale = new Locale(language);
			}
		}
		return locale;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Contribution getContribution() {
		Contribution contribution = basicGetContribution();
		return contribution != null && contribution.eIsProxy()
				? (Contribution) eResolveProxy((InternalEObject) contribution)
				: contribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Contribution basicGetContribution() {
		return this;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setContribution(Contribution newContribution) {
		// no-op
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> getDependencies() {
		if (!isStateSet())
			return ECollections.emptyEList();
		return getState().getRequiredPlugins();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Revision createRevision(IProgressMonitor monitor) throws CoreException {
		Revision revision = null;
		if (!isRevisionSet()) {
			Version version = nextVersion();
			revision = newRevision(version);
		} else
			revision = getRevision();
		revision.create(monitor);
		return revision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EMap<StageQualifier, Customizer> getCustomizers() {
		if (isStateSet())
			return getState().getCustomizers();

		return ECollections.emptyEMap();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Item getParent() {
		Item parent = basicGetParent();
		return parent != null && parent.eIsProxy() ? (Item) eResolveProxy((InternalEObject) parent) : parent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Item basicGetParent() {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setParent(Item newParent) {
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String initialize(boolean commit) {
		@SuppressWarnings("unchecked")
		SCMOperator<Git> operator = (SCMOperator<Git>) getWorkspace().getSCMRegistry().get(getProjectName());
		setName(getProjectName());
		try {
			Git git = operator.getRepositorySCM();
			// if (git == null)
			// return "";
			String currentBranch = git.getRepository().getBranch();

			ListBranchCommand listBranches = git.branchList();
			List<Ref> branches = listBranches.call();
			Iterator<Ref> it = branches.iterator();
			Ref branch = null;
			int timestamp = -1;
			String commitId = "";
			do {
				Version version = null;
				if (it.hasNext()) {
					branch = it.next();
					String[] name = branch.getName().split("/"); //$NON-NLS-1$
					try {
						version = operator.decodeVersion(name[name.length - 1]);
						ReflogCommand reflog = git.reflog();
						reflog.setRef(branch.getName().toString());
						Collection<ReflogEntry> refs = reflog.call();
						for (ReflogEntry ref : refs)
							if (ref.getNewId().equals(branch.getObjectId())) {
								timestamp = operator.decodeTimestamp(ref.getComment());
								if (timestamp == -1)
									timestamp = operator.decodeTimestamp(version.getQualifier());
							}
					} catch (IllegalArgumentException e) {
						continue;
					}
				}
				if (version != null && !getRevisions().containsKey(version)) {
					Revision newRevision = newBareRevision(version);
					newRevision.setTimestamp(timestamp);
					newRevision.setContribution(this);
					doNewRevision(newRevision);
					commitId = newRevision.initialize(commit);
				}
			} while (it.hasNext());
			if (!getRevisions().isEmpty() && getVersion().equals(Version.emptyVersion))
				setVersion(ListUtil.getLast(getRevisions()).getKey());
			else if (!getVersion().equals(Version.emptyVersion))
				checkout(getVersion(), timestamp);
			if (currentBranch.equals(SCMOperator.MASTER_BRANCH))
				checkout(getVersion(), timestamp);
			getWorkspace().getResourceSet().eAdapters().add(resourceAdapter);
			addResourceChangeListener(getResourceReloadListener());
			return commitId;
		} catch (Exception e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			return null;
		} finally {
			try {
				operator.ungetRepositorySCM();
			} catch (Exception e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			}
		}

	}

	@Override
	public void create(IProgressMonitor monitor) throws CoreException {
		super.create(monitor);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		String projectName = getProjectName();
		IProject project = workspace.getRoot().getProject(projectName);
		ResourceUtils.addProjectNature(project, ClassMakerPlugin.NATURE_ID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String make(Revision revision, IProgressMonitor monitor) throws CoreException {
		try {
			return revision.make(monitor);
		} catch (JGitInternalException e) {
		} catch (OperationCanceledException e) {
			monitor.setCanceled(true);
		} catch (Exception e) {
			throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
		}
		return revision.getState().getCommitId();
	}

	@Override
	public String make(IProgressMonitor monitor) throws CoreException {
		removeResourceChangeListener(getResourceReloadListener());
		return make(getRevision(), monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(Version version) {
		Revision revision = null;
		if (getRevisions().containsKey(version)) {
			revision = getRevisions().get(version);
			if (!revision.eIsSet(ClassMakerPackage.Literals.REVISION__TIMESTAMP))
				if (!revision.getStateHistory().isEmpty())
					checkout(version, ListUtil.getLast(revision.getStateHistory()).getKey());
				else {
					super.checkout(version);
					return;
				}
			checkout(version, revision.getTimestamp());
		} else if (!getRevisions().isEmpty() && (version == null || version.equals(Version.emptyVersion)))
			checkout(ListUtil.getLast(getRevisions()).getKey());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(Version version, int time) {
		Revision revision = null;
		if (getRevisions().containsKey(version)) {
			setProjectVersion(version);
			if (getProjectName().isEmpty())
				return;
			Git git = null;
			@SuppressWarnings("unchecked")
			SCMOperator<Git> operator = (SCMOperator<Git>) getWorkspace().getSCMRegistry().get(getProjectName());
			Ref ref = null;
			try {
				git = operator.getRepositorySCM();
				ref = git.getRepository().findRef(version.toString());
				if (ref != null)
					git.checkout().setName(ref.getName()).call();
			} catch (CheckoutConflictException e) {
				if (git != null) {
					try {
						ResetCommand reset = git.reset().setMode(ResetType.HARD);
						if (ref != null)
							reset.setRef(ref.getName());
						reset.call();
					} catch (CheckoutConflictException ex) {
						ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(ex));
					} catch (GitAPIException ex) {
						ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(ex));
					}
				}
			} catch (Exception e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			} finally {
				try {
					operator.ungetRepositorySCM();
				} catch (Exception e) {
					ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
				}
			}
			revision = getRevisions().get(version);
			if (revision.getStateHistory().containsKey(time)) {
				State state = revision.getStateHistory().get((Object) time);
				EList<String> commits = state.getCommitIds();
				if (!commits.isEmpty()) {
					revision.checkout(time, state.getCommitId());
				} else
					revision.checkout(time);
			}
		} else
			throw new IllegalStateException(NLS.bind(Messages.VersionNotExists, version));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(Version version, int time, String commitId) {
		if (version.equals(VERSION_EDEFAULT)) {
			setProjectVersion(version);
			return;
		}
		checkout(version, time);
		getRevision().checkout(time, commitId);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(int time) {
		if (getRevision().getStateHistory().containsKey(time))
			getRevision().checkout(time);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(int time, String commitId) {
		if (getRevision().getStateHistory().containsKey(time)) {
			State state = getRevision().getStateHistory().get((Object) time);
			if (state.getCommitIds().contains(commitId))
				getRevision().checkout(time, commitId);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(String commitId) {
		if (getState().getCommitIds().contains(commitId))
			getState().checkout(commitId);
	}

	@Override
	public void doNewRevision(Revision newRevision) {
		State newState = newRevision.newState();
		if (isStateSet())
			newState.copyModel(getState());
		initAdapters(newRevision);
		newRevision.setTimestamp(newState.getTimestamp());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		if (eIsSet(ClassMakerPackage.CONTRIBUTION__MODEL_NAME))
			getModelName();
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		setModelName(newName);
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CONTRIBUTION__NAME, oldName, name));
	}

	protected boolean isRevisionSet() {
		return eIsSet(ClassMakerPackage.CONTRIBUTION__REVISION);
	}

	protected boolean isStateSet() {
		return isRevisionSet() && eIsSet(ClassMakerPackage.CONTRIBUTION__STATE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getLanguage() {
		if (isStateSet())
			return getState().getLanguage();
		return LANGUAGE_EDEFAULT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setLanguage(String newLanguage) {
		if (isStateSet())
			getState().setLanguage(newLanguage);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ModelPair getDomainModel() {
		if (isStateSet())
			return getState().getDomainModel();
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDomainModel(ModelPair newDomainModel, NotificationChain msgs) {
		ModelPair oldDomainModel = domainModel;
		domainModel = newDomainModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.CONTRIBUTION__DOMAIN_MODEL, oldDomainModel, newDomainModel);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersion(Version newVersion) {
		Version oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CONTRIBUTION__VERSION, oldVersion,
					version));
	}

	/**
	 * ({@inheritDoc}) Should be used instead of {@link #getVersion()}.
	 * 
	 */
	@Override
	public void setProjectVersion(Version newProjectVersion) {
		super.setProjectVersion(newProjectVersion);
		setVersion(newProjectVersion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EList<Object> getChildren() {
		if (children == null || children.isEmpty()) {
			if (getModelResourceAdapter() != null)
				children = new LoadingEList(getModelResourceAdapter().getResource());
			else
				children = new LoadingEList(null);
			eAdapters().remove(modelAdapter);
			eAdapters().add(modelAdapter);
		}
		return (EList<Object>) (EList<?>) children;
	}

	private Adapter modelAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			super.notifyChanged(msg);
			if (msg.getFeatureID(Contribution.class) == ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER
					&& msg.getEventType() == Notification.SET && msg.getNewValue() != null) {
				children.setContents(getModelResourceAdapter().getResource());
			}
		}

	};

	private class LoadingEList extends NotifyingInternalEListImpl<Notifier> {

		private static final long serialVersionUID = 164926149524632079L;
		private Notifier object;
		private Adapter initAdapter = new AdapterImpl() {

			@Override
			public void notifyChanged(Notification msg) {
				super.notifyChanged(msg);
				if (msg.getFeatureID(ResourceAdapter.class) == ClassMakerPackage.RESOURCE_ADAPTER__RESOURCE
						&& msg.getEventType() == Notification.SET && msg.getNewValue() != null) {
					LoadingEList.this.object = (Notifier) msg.getNewValue();
					fill((Notifier) msg.getNewValue());
				}
			}

		};

		public LoadingEList(Notifier object) {
			setContents(object);
		}

		private class ResourceNotificationImpl extends NotificationImpl {

			public ResourceNotificationImpl(int eventType, Object oldValue, Object newValue) {
				super(eventType, oldValue, newValue);
			}

			public ResourceNotificationImpl(int eventType, Object oldValue, Object newValue, int position) {
				super(eventType, oldValue, newValue, position);
			}

			@Override
			public int getFeatureID(Class<?> expectedClass) {
				if (expectedClass.isAssignableFrom(Resource.class))
					return Resource.RESOURCE__CONTENTS;
				return super.getFeatureID(expectedClass);
			}

		}

		private void fill(Notifier object) {
			clear();
			add(object);
		}

		@Override
		protected void didSet(int index, Notifier newObject, Notifier oldObject) {
			detachInitAdapter();
			setObject(newObject);
			super.didSet(index, newObject, oldObject);
			attachInitAdapter();
			if (eNotificationRequired())
				eNotify(new ResourceNotificationImpl(Notification.SET, oldObject, newObject, index));
		}

		@Override
		protected void didAdd(int index, Notifier newObject) {
			super.didAdd(index, newObject);
			attachInitAdapter();
			setObject(newObject);
			if (eNotificationRequired())
				eNotify(new ResourceNotificationImpl(Notification.ADD, null, newObject, index));
		}

		@Override
		protected void didRemove(int index, Notifier oldObject) {
			detachInitAdapter();
			setObject(null);
			super.didRemove(index, oldObject);
			if (eNotificationRequired())
				eNotify(new ResourceNotificationImpl(Notification.REMOVE, oldObject, null, index));
		}

		@Override
		protected void didClear(int size, Object[] oldObjects) {
			detachInitAdapter();
			setObject(null);
			super.didClear(size, oldObjects);
			if (eNotificationRequired())
				eNotify(new ResourceNotificationImpl(Notification.REMOVE_MANY, oldObjects, null));
		}

		private void attachInitAdapter() {
			if (object == null)
				return;
			object.eAdapters().add(initAdapter);
		}

		private void detachInitAdapter() {
			if (object == null)
				return;
			object.eAdapters().remove(initAdapter);
		}

		public void setContents(Notifier object) {
			detachInitAdapter();
			setObject(object);
			attachInitAdapter();
			fill(object);
		}

		private void setObject(Notifier object) {
			this.object = object;
		}

	}

	@Override
	public String getResourcePath() {
		return IPath.SEPARATOR + getProjectName() + IPath.SEPARATOR + ResourceUtils.getModelFolderName()
				+ IPath.SEPARATOR + ResourceUtils.getFileName(getModelName());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State getState(int timestamp) {
		if (isRevisionSet() && getRevision().getStateHistory().containsKey(timestamp))
			return getRevision().getStateHistory().get((Integer) timestamp);
		return getState();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void initAdapters(Revision revision) {
		addCompletionListener(completionListener);
		revision.addAdapters(ECollections.singletonEList(stateModelAdapter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void load(boolean create) throws CoreException {
		initialize(false);
		if (isRevisionSet()) {
			if (create) {
				try {
					@SuppressWarnings("unchecked")
					SCMOperator<Git> operator = (SCMOperator<Git>) getWorkspace().getSCMRegistry()
							.get(getProjectName());
					operator.add(".");
					operator.commit(getProjectName());
				} catch (Exception e) {
					throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
				}
			}
			getRevision().load(create);
		}
	}

	@Override
	protected void setResource(Resource resource) {
		getChildren().set(0, resource);
	}

	@Override
	public boolean isDirty() {
		if (isStateSet())
			return getState().getResource().isModified();
		return super.isDirty();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(IProgressMonitor monitor) throws CoreException {
		super.delete(monitor);
		getRevision().delete(monitor);
	}

	@Override
	public void delete(EList<Object> objects) {
		if (!isStateSet())
			return;
		boolean removed = ((Resource) getChildren().get(0)).getContents().removeAll(objects);
		if (removed)
			return;

		for (Object object : objects)
			if (object instanceof EObject) {
				for (TreeIterator<EObject> tree = EcoreUtil.getAllProperContents((EObject) object, false); tree
						.hasNext();) {
					EcoreUtil.remove(tree.next());
				}
				EcoreUtil.remove((EObject) object);
			}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.CONTRIBUTION__DOMAIN_MODEL:
			return basicSetDomainModel(null, msgs);
		case ClassMakerPackage.CONTRIBUTION__CUSTOMIZERS:
			return ((InternalEList<?>) getCustomizers()).basicRemove(otherEnd, msgs);
		case ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER:
			return basicSetModelResourceAdapter(null, msgs);
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
		case ClassMakerPackage.CONTRIBUTION__MODEL_NAME:
			return getModelName();
		case ClassMakerPackage.CONTRIBUTION__PHASE:
			return getPhase();
		case ClassMakerPackage.CONTRIBUTION__VERSION:
			return getVersion();
		case ClassMakerPackage.CONTRIBUTION__LANGUAGE:
			return getLanguage();
		case ClassMakerPackage.CONTRIBUTION__DOMAIN_MODEL:
			return getDomainModel();
		case ClassMakerPackage.CONTRIBUTION__CUSTOMIZERS:
			if (coreType)
				return getCustomizers();
			else
				return getCustomizers().map();
		case ClassMakerPackage.CONTRIBUTION__PARENT:
			if (resolve)
				return getParent();
			return basicGetParent();
		case ClassMakerPackage.CONTRIBUTION__LOCALE:
			return getLocale();
		case ClassMakerPackage.CONTRIBUTION__CONTRIBUTION:
			if (resolve)
				return getContribution();
			return basicGetContribution();
		case ClassMakerPackage.CONTRIBUTION__DEPENDENCIES:
			return getDependencies();
		case ClassMakerPackage.CONTRIBUTION__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ClassMakerPackage.CONTRIBUTION__LATEST_VERSION:
			return getLatestVersion();
		case ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER:
			return getModelResourceAdapter();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassMakerPackage.CONTRIBUTION__MODEL_NAME:
			setModelName((String) newValue);
			return;
		case ClassMakerPackage.CONTRIBUTION__PHASE:
			setPhase((Stage) newValue);
			return;
		case ClassMakerPackage.CONTRIBUTION__VERSION:
			setVersion((Version) newValue);
			return;
		case ClassMakerPackage.CONTRIBUTION__LANGUAGE:
			setLanguage((String) newValue);
			return;
		case ClassMakerPackage.CONTRIBUTION__CUSTOMIZERS:
			((EStructuralFeature.Setting) getCustomizers()).set(newValue);
			return;
		case ClassMakerPackage.CONTRIBUTION__PARENT:
			setParent((Item) newValue);
			return;
		case ClassMakerPackage.CONTRIBUTION__CONTRIBUTION:
			setContribution((Contribution) newValue);
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
		case ClassMakerPackage.CONTRIBUTION__MODEL_NAME:
			setModelName(MODEL_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.CONTRIBUTION__PHASE:
			setPhase(PHASE_EDEFAULT);
			return;
		case ClassMakerPackage.CONTRIBUTION__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case ClassMakerPackage.CONTRIBUTION__LANGUAGE:
			setLanguage(LANGUAGE_EDEFAULT);
			return;
		case ClassMakerPackage.CONTRIBUTION__CUSTOMIZERS:
			getCustomizers().clear();
			return;
		case ClassMakerPackage.CONTRIBUTION__PARENT:
			setParent((Item) null);
			return;
		case ClassMakerPackage.CONTRIBUTION__CONTRIBUTION:
			setContribution((Contribution) null);
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
		case ClassMakerPackage.CONTRIBUTION__MODEL_NAME:
			return MODEL_NAME_EDEFAULT == null ? modelName != null : !MODEL_NAME_EDEFAULT.equals(modelName);
		case ClassMakerPackage.CONTRIBUTION__PHASE:
			return phase != PHASE_EDEFAULT;
		case ClassMakerPackage.CONTRIBUTION__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case ClassMakerPackage.CONTRIBUTION__LANGUAGE:
			return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
		case ClassMakerPackage.CONTRIBUTION__DOMAIN_MODEL:
			return domainModel != null;
		case ClassMakerPackage.CONTRIBUTION__CUSTOMIZERS:
			return !getCustomizers().isEmpty();
		case ClassMakerPackage.CONTRIBUTION__PARENT:
			return basicGetParent() != null;
		case ClassMakerPackage.CONTRIBUTION__LOCALE:
			return LOCALE_EDEFAULT == null ? locale != null : !LOCALE_EDEFAULT.equals(locale);
		case ClassMakerPackage.CONTRIBUTION__CONTRIBUTION:
			return basicGetContribution() != null;
		case ClassMakerPackage.CONTRIBUTION__DEPENDENCIES:
			return !getDependencies().isEmpty();
		case ClassMakerPackage.CONTRIBUTION__STATE:
			return basicGetState() != null;
		case ClassMakerPackage.CONTRIBUTION__LATEST_VERSION:
			return LATEST_VERSION_EDEFAULT == null ? getLatestVersion() != null
					: !LATEST_VERSION_EDEFAULT.equals(getLatestVersion());
		case ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER:
			return modelResourceAdapter != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Item.class) {
			switch (derivedFeatureID) {
			case ClassMakerPackage.CONTRIBUTION__MODEL_NAME:
				return ClassMakerPackage.ITEM__MODEL_NAME;
			case ClassMakerPackage.CONTRIBUTION__PHASE:
				return ClassMakerPackage.ITEM__PHASE;
			case ClassMakerPackage.CONTRIBUTION__VERSION:
				return ClassMakerPackage.ITEM__VERSION;
			case ClassMakerPackage.CONTRIBUTION__LANGUAGE:
				return ClassMakerPackage.ITEM__LANGUAGE;
			case ClassMakerPackage.CONTRIBUTION__DOMAIN_MODEL:
				return ClassMakerPackage.ITEM__DOMAIN_MODEL;
			case ClassMakerPackage.CONTRIBUTION__CUSTOMIZERS:
				return ClassMakerPackage.ITEM__CUSTOMIZERS;
			case ClassMakerPackage.CONTRIBUTION__PARENT:
				return ClassMakerPackage.ITEM__PARENT;
			case ClassMakerPackage.CONTRIBUTION__LOCALE:
				return ClassMakerPackage.ITEM__LOCALE;
			case ClassMakerPackage.CONTRIBUTION__CONTRIBUTION:
				return ClassMakerPackage.ITEM__CONTRIBUTION;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Item.class) {
			switch (baseFeatureID) {
			case ClassMakerPackage.ITEM__MODEL_NAME:
				return ClassMakerPackage.CONTRIBUTION__MODEL_NAME;
			case ClassMakerPackage.ITEM__PHASE:
				return ClassMakerPackage.CONTRIBUTION__PHASE;
			case ClassMakerPackage.ITEM__VERSION:
				return ClassMakerPackage.CONTRIBUTION__VERSION;
			case ClassMakerPackage.ITEM__LANGUAGE:
				return ClassMakerPackage.CONTRIBUTION__LANGUAGE;
			case ClassMakerPackage.ITEM__DOMAIN_MODEL:
				return ClassMakerPackage.CONTRIBUTION__DOMAIN_MODEL;
			case ClassMakerPackage.ITEM__CUSTOMIZERS:
				return ClassMakerPackage.CONTRIBUTION__CUSTOMIZERS;
			case ClassMakerPackage.ITEM__PARENT:
				return ClassMakerPackage.CONTRIBUTION__PARENT;
			case ClassMakerPackage.ITEM__LOCALE:
				return ClassMakerPackage.CONTRIBUTION__LOCALE;
			case ClassMakerPackage.ITEM__CONTRIBUTION:
				return ClassMakerPackage.CONTRIBUTION__CONTRIBUTION;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (modelName: ");
		result.append(modelName);
		result.append(", phase: ");
		result.append(phase);
		result.append(", version: ");
		result.append(version);
		result.append(", language: ");
		result.append(language);
		result.append(", locale: ");
		result.append(locale);
		result.append(')');
		return result.toString();
	}

} // SoftwareContributionImpl
