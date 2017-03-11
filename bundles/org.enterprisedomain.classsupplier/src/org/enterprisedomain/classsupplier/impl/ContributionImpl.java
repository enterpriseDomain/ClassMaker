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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
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
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.DepthWalk.Commit;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classsupplier.ClassSupplierFactory;
import org.enterprisedomain.classsupplier.ClassSupplierPackage;
import org.enterprisedomain.classsupplier.CompletionListener;
import org.enterprisedomain.classsupplier.Contribution;
import org.enterprisedomain.classsupplier.Customizer;
import org.enterprisedomain.classsupplier.Item;
import org.enterprisedomain.classsupplier.Messages;
import org.enterprisedomain.classsupplier.ModelPair;
import org.enterprisedomain.classsupplier.ResourceAdapter;
import org.enterprisedomain.classsupplier.Revision;
import org.enterprisedomain.classsupplier.Stage;
import org.enterprisedomain.classsupplier.StageQualifier;
import org.enterprisedomain.classsupplier.State;
import org.enterprisedomain.classsupplier.core.ClassSupplierOSGi;
import org.enterprisedomain.classsupplier.util.ListUtil;
import org.enterprisedomain.classsupplier.util.GitUtil;
import org.enterprisedomain.classsupplier.util.ModelUtil;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Contribution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getModelName <em>Model Name</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getPhase <em>Phase</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getDomainModel <em>Domain Model</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getCustomizers <em>Customizers</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getLocale <em>Locale</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getRevision <em>Revision</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getRevisions <em>Revisions</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getState <em>State</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getLatestVersion <em>Latest Version</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ContributionImpl#getModel <em>Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContributionImpl extends ProjectImpl implements Contribution {

	/**
	 * The default value of the '{@link #getModelName() <em>Model Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getModelName()
	 * @generated
	 * @ordered
	 */
	protected static final String MODEL_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getModelName() <em>Model Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getModelName()
	 * @generated
	 * @ordered
	 */
	protected String modelName = MODEL_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getPhase() <em>Phase</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected static final Stage PHASE_EDEFAULT = Stage.DEFINED;
	/**
	 * The cached value of the '{@link #getPhase() <em>Phase</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected Stage phase = PHASE_EDEFAULT;
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
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected Version version = VERSION_EDEFAULT;
	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected String language = LANGUAGE_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDomainModel() <em>Domain Model</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDomainModel()
	 * @generated
	 * @ordered
	 */
	protected ModelPair domainModel;
	/**
	 * The default value of the '{@link #getLocale() <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLocale()
	 * @generated
	 * @ordered
	 */
	protected static final Locale LOCALE_EDEFAULT = (Locale) ClassSupplierFactory.eINSTANCE
			.createFromString(ClassSupplierPackage.eINSTANCE.getLocale(), "");
	/**
	 * The cached value of the '{@link #getLocale() <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLocale()
	 * @generated
	 * @ordered
	 */
	protected Locale locale = LOCALE_EDEFAULT;
	/**
	 * The cached value of the '{@link #getRevisions() <em>Revisions</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRevisions()
	 * @generated
	 * @ordered
	 */
	protected EMap<Version, Revision> revisions;
	/**
	 * The default value of the '{@link #getLatestVersion() <em>Latest Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLatestVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version LATEST_VERSION_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getModel() <em>Model</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected ResourceAdapter model;

	protected ListenerList<CompletionListener> listeners = new ListenerList<CompletionListener>();

	protected CompletionListener modelListener = new CompletionListenerImpl() {

		@Override
		public void completed(Contribution result) throws Exception {
			getModel();
		}

	};

	protected Adapter stateModelAdapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == ClassSupplierPackage.STATE__RESOURCE
					&& msg.getEventType() == Notification.SET) {
				getModel();
			}
		}
	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ContributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.CONTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelName(String newModelName) {
		String oldModelName = modelName;
		modelName = newModelName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__MODEL_NAME,
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
	 * @generated
	 */
	public void setPhase(Stage newPhase) {
		Stage oldPhase = phase;
		phase = newPhase == null ? PHASE_EDEFAULT : newPhase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__PHASE, oldPhase,
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
			msgs.add(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__STATE, oldState,
					newState));
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State basicGetState() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__REVISIONS) && eIsSet(ClassSupplierPackage.CONTRIBUTION__REVISION))
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
		sorted.sort(new Comparator<org.osgi.framework.Version>() {

			@Override
			public int compare(org.osgi.framework.Version o1, org.osgi.framework.Version o2) {
				return o1.getMajor() - o2.getMajor() + o1.getMinor() - o2.getMinor() + o1.getMicro() - o2.getMicro();
			}
		});
		return sorted.get(ListUtil.lastIndex(sorted.size()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ResourceAdapter getModel() {
		if (model == null && isStateSet()) {
			model = ClassSupplierFactory.eINSTANCE.createResourceAdapter();
			model.setResource(getState().getResource());
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__MODEL, null,
						model));
		}
		return basicGetModel();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ResourceAdapter basicGetModel() {
		return model;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String initialize() {
		try {
			Git git = GitUtil.getRepositoryGit(getProjectName());

			String currentBranch = git.getRepository().getBranch();

			ListBranchCommand listBranches = git.branchList();
			List<Ref> branches = listBranches.call();
			Iterator<Ref> it = branches.iterator();
			Ref branch = null;
			do {
				Version version = null;
				if (it.hasNext()) {
					branch = it.next();
					String[] name = branch.getName().split("/");
					try {
						version = Version.parseVersion(name[name.length - 1]);
					} catch (IllegalArgumentException e) {
						continue;
					}
				}
				if (version != null && !getRevisions().containsKey(version)) {
					Revision revision = ClassSupplierFactory.eINSTANCE.createRevision();
					revision.setVersion(version);
					getRevisions().put(version, revision);
				}
				if (version != null && branch.getName().equals(currentBranch))
					setVersion(version);
			} while (it.hasNext());
			return getRevision().initialize();
		} catch (GitAPIException e) {
			ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(e));
			return null;
		} catch (IOException e) {
			ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(e));
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void copyModel(Item from) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.CONTRIBUTION__DOMAIN_MODEL:
			if (domainModel != null)
				msgs = ((InternalEObject) domainModel).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassSupplierPackage.CONTRIBUTION__DOMAIN_MODEL, null, msgs);
			return basicSetDomainModel((ModelPair) otherEnd, msgs);
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
		return getRevisions().get(getVersion());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setRevision(Revision newRevision) {
		if (!getRevisions().containsKey(newRevision.getVersion()))
			getRevisions().put(newRevision.getVersion(), newRevision);
		setVersion(newRevision.getVersion());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<Version, Revision> getRevisions() {
		if (revisions == null) {
			revisions = new EcoreEMap<Version, Revision>(ClassSupplierPackage.Literals.VERSION_TO_REVISION_MAP_ENTRY,
					VersionToRevisionMapEntryImpl.class, this, ClassSupplierPackage.CONTRIBUTION__REVISIONS);
		}
		return revisions;
	}

	public void notifyCompletion() throws Exception {
		for (Object listener : listeners.getListeners())
			((CompletionListener) listener).completed(this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String save(Revision revision, IProgressMonitor monitor) throws CoreException {
		try {
			return revision.save(monitor);
		} catch (JGitInternalException e) {
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
		}
		EList<String> commitIds = revision.getState().getCommitIds();
		return commitIds.get(ListUtil.lastIndex(commitIds.size()));
	}

	@Override
	public String save(IProgressMonitor monitor) throws CoreException {
		return save(getRevision(), monitor);
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
			if (!revision.eIsSet(ClassSupplierPackage.Literals.REVISION__TIMESTAMP))
				if (!revision.getStateHistory().isEmpty())
					checkout(version, revision.getStateHistory()
							.get(ListUtil.lastIndex(revision.getStateHistory().size())).getKey());
				else
					return;
			checkout(version, revision.getTimestamp());
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(Version version, int time) {
		Revision revision = null;
		if (getRevisions().containsKey(version)) {
			setVersion(version);
			if (getProjectName().isEmpty())
				return;
			Git git = null;
			try {
				git = GitUtil.getRepositoryGit(getProjectName());
				Ref ref = git.getRepository().findRef(version.toString());
				if (ref != null)
					git.checkout().setName(ref.getName()).call();
			} catch (CheckoutConflictException e) {
				if (git != null) {
					try {
						git.reset().setMode(ResetType.HARD).call();
					} catch (CheckoutConflictException ex) {
						ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(ex));
					} catch (GitAPIException ex) {
						ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(ex));
					}
				}
			} catch (GitAPIException e) {
				ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(e));
			} catch (IOException e) {
				ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(e));
			}
			revision = getRevisions().get(version);
			if (revision.getStateHistory().containsKey(time)) {
				EList<String> commits = revision.getStateHistory().get((Object) time).getCommitIds();
				if (!commits.isEmpty())
					revision.checkout(time, commits.get(ListUtil.lastIndex(commits.size())));
				else
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
			setVersion(version);
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Revision newRevision(Version version) {
		if (getRevisions().contains(version))
			return getRevisions().get(version);
		Revision newRevision = ClassSupplierFactory.eINSTANCE.createRevision();
		newRevision.setVersion(version);
		getRevisions().put(version, newRevision);
		State newState = newRevision.newState();
		if (isStateSet())
			newState.copyModel(getState());
		initAdapters(newRevision);
		newRevision.setTimestamp(newState.getTimestamp());
		return newRevision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Version nextVersion() throws CoreException {
		if (!eIsSet(ClassSupplierPackage.Literals.ITEM__VERSION))
			return newVersion(true, false, false);
		return newVersion(getVersion(), false, false, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Version newVersion(boolean incrementMajor, boolean incrementMinor, boolean incrementMicro)
			throws CoreException {
		return newVersion(Version.emptyVersion, incrementMajor, incrementMinor, incrementMicro);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Version newVersion(Version base, boolean incrementMajor, boolean incrementMinor, boolean incrementMicro)
			throws CoreException {
		int major = base.getMajor();
		if (incrementMajor)
			++major;
		int minor = base.getMinor();
		if (incrementMinor)
			++minor;
		int micro = base.getMicro();
		if (incrementMicro)
			++micro;
		for (Version version : getRevisions().keySet())
			if (version.getMajor() == major && version.getMinor() == minor && version.getMicro() == micro)
				return version;
		return new Version(major, minor, micro, Revision.VERSION_QUALIFIER_FORMAT.format(new Date()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName() {
		if (eIsSet(ClassSupplierPackage.CONTRIBUTION__MODEL_NAME))
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__NAME, oldName,
					name));
	}

	protected boolean isRevisionSet() {
		return eIsSet(ClassSupplierPackage.CONTRIBUTION__REVISION);
	}

	protected boolean isStateSet() {
		return isRevisionSet() && eIsSet(ClassSupplierPackage.CONTRIBUTION__STATE);
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
	 * @generated
	 */
	public NotificationChain basicSetDomainModel(ModelPair newDomainModel, NotificationChain msgs) {
		ModelPair oldDomainModel = domainModel;
		domainModel = newDomainModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassSupplierPackage.CONTRIBUTION__DOMAIN_MODEL, oldDomainModel, newDomainModel);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(Version newVersion) {
		Version oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.CONTRIBUTION__VERSION,
					oldVersion, version));
	}

	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getChildren() {
		if (children == null) {
			children = new LoadingEList(getModel());
			eAdapters().remove(modelAdapter);
			eAdapters().add(modelAdapter);
		}
		return (EList<EObject>) (EList<?>) children;
	}

	private LoadingEList children;

	private Adapter modelAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			super.notifyChanged(msg);
			if (msg.getFeatureID(Contribution.class) == ClassSupplierPackage.CONTRIBUTION__MODEL
					&& msg.getEventType() == Notification.SET) {
				children.attachAdapter();
			}
		}

	};

	private class LoadingEList extends BasicEList<EObject> {

		private static final long serialVersionUID = 164926149524632079L;
		private EObject eObject;

		public LoadingEList(EObject eObject) {
			this.eObject = eObject;
			attachAdapter();
			fill(eObject);
		}

		private void fill(EObject object) {
			clear();
			add(object);
		}

		public void attachAdapter() {
			if (eObject == null)
				return;
			eObject.eAdapters().add(new AdapterImpl() {

				@Override
				public void notifyChanged(Notification msg) {
					super.notifyChanged(msg);
					if (msg.getFeatureID(ResourceAdapter.class) == ClassSupplierPackage.RESOURCE_ADAPTER__RESOURCE
							&& msg.getEventType() == Notification.SET && msg.getNewValue() != null)
						fill((EObject) msg.getNotifier());
				}

			});
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void addSaveCompletionListener(CompletionListener resultListener) {
		listeners.add(resultListener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void removeSaveCompletionListener(CompletionListener resultListener) {
		listeners.remove(resultListener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State getState(int timestamp) {
		if (getRevision().getStateHistory().containsKey(timestamp))
			return getRevision().getStateHistory().get((Integer) timestamp);
		return getState();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void initAdapters(Revision revision) {
		revision.addAdapters(ECollections.singletonEList(stateModelAdapter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void load(boolean create) throws CoreException {
		initialize();
		if (isRevisionSet())
			getRevision().load(create);
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
		if (eIsSet(ClassSupplierPackage.PROJECT__WORKSPACE))
			getWorkspace().unregisterProject(this);
		getRevision().delete(monitor);
	}

	@Override
	public void delete(EList<Object> objects) {
		if (!isStateSet())
			return;
		boolean removed = false;
		for (Object object : objects) {
			if (object instanceof EObject) {
				ModelPair model = getDomainModel();
				EPackage deletedEPackage = ModelUtil.getEPackage((EObject) object);
				if ((model != null && ModelUtil.ePackagesAreEqual(model.getGenerated(), deletedEPackage, true)
						|| model != null && ModelUtil.ePackagesAreEqual(model.getDynamic(), deletedEPackage, true))) {
					model.setDynamic(null);
					removed = true;
				}
			}
		}
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
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.CONTRIBUTION__DOMAIN_MODEL:
			return basicSetDomainModel(null, msgs);
		case ClassSupplierPackage.CONTRIBUTION__CUSTOMIZERS:
			return ((InternalEList<?>) getCustomizers()).basicRemove(otherEnd, msgs);
		case ClassSupplierPackage.CONTRIBUTION__REVISIONS:
			return ((InternalEList<?>) getRevisions()).basicRemove(otherEnd, msgs);
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
		case ClassSupplierPackage.CONTRIBUTION__MODEL_NAME:
			return getModelName();
		case ClassSupplierPackage.CONTRIBUTION__PHASE:
			return getPhase();
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			return getVersion();
		case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
			return getLanguage();
		case ClassSupplierPackage.CONTRIBUTION__DOMAIN_MODEL:
			return getDomainModel();
		case ClassSupplierPackage.CONTRIBUTION__CUSTOMIZERS:
			if (coreType)
				return getCustomizers();
			else
				return getCustomizers().map();
		case ClassSupplierPackage.CONTRIBUTION__PARENT:
			if (resolve)
				return getParent();
			return basicGetParent();
		case ClassSupplierPackage.CONTRIBUTION__LOCALE:
			return getLocale();
		case ClassSupplierPackage.CONTRIBUTION__REVISION:
			if (resolve)
				return getRevision();
			return basicGetRevision();
		case ClassSupplierPackage.CONTRIBUTION__REVISIONS:
			if (coreType)
				return getRevisions();
			else
				return getRevisions().map();
		case ClassSupplierPackage.CONTRIBUTION__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ClassSupplierPackage.CONTRIBUTION__LATEST_VERSION:
			return getLatestVersion();
		case ClassSupplierPackage.CONTRIBUTION__MODEL:
			if (resolve)
				return getModel();
			return basicGetModel();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassSupplierPackage.CONTRIBUTION__MODEL_NAME:
			setModelName((String) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__PHASE:
			setPhase((Stage) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			setVersion((Version) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
			setLanguage((String) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__CUSTOMIZERS:
			((EStructuralFeature.Setting) getCustomizers()).set(newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__PARENT:
			setParent((Item) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__REVISION:
			setRevision((Revision) newValue);
			return;
		case ClassSupplierPackage.CONTRIBUTION__REVISIONS:
			((EStructuralFeature.Setting) getRevisions()).set(newValue);
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
		case ClassSupplierPackage.CONTRIBUTION__MODEL_NAME:
			setModelName(MODEL_NAME_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__PHASE:
			setPhase(PHASE_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
			setLanguage(LANGUAGE_EDEFAULT);
			return;
		case ClassSupplierPackage.CONTRIBUTION__CUSTOMIZERS:
			getCustomizers().clear();
			return;
		case ClassSupplierPackage.CONTRIBUTION__PARENT:
			setParent((Item) null);
			return;
		case ClassSupplierPackage.CONTRIBUTION__REVISION:
			setRevision((Revision) null);
			return;
		case ClassSupplierPackage.CONTRIBUTION__REVISIONS:
			getRevisions().clear();
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
		case ClassSupplierPackage.CONTRIBUTION__MODEL_NAME:
			return MODEL_NAME_EDEFAULT == null ? modelName != null : !MODEL_NAME_EDEFAULT.equals(modelName);
		case ClassSupplierPackage.CONTRIBUTION__PHASE:
			return phase != PHASE_EDEFAULT;
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
			return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
		case ClassSupplierPackage.CONTRIBUTION__DOMAIN_MODEL:
			return domainModel != null;
		case ClassSupplierPackage.CONTRIBUTION__CUSTOMIZERS:
			return !getCustomizers().isEmpty();
		case ClassSupplierPackage.CONTRIBUTION__PARENT:
			return basicGetParent() != null;
		case ClassSupplierPackage.CONTRIBUTION__LOCALE:
			return LOCALE_EDEFAULT == null ? locale != null : !LOCALE_EDEFAULT.equals(locale);
		case ClassSupplierPackage.CONTRIBUTION__REVISION:
			return basicGetRevision() != null;
		case ClassSupplierPackage.CONTRIBUTION__REVISIONS:
			return revisions != null && !revisions.isEmpty();
		case ClassSupplierPackage.CONTRIBUTION__STATE:
			return basicGetState() != null;
		case ClassSupplierPackage.CONTRIBUTION__LATEST_VERSION:
			return LATEST_VERSION_EDEFAULT == null ? getLatestVersion() != null
					: !LATEST_VERSION_EDEFAULT.equals(getLatestVersion());
		case ClassSupplierPackage.CONTRIBUTION__MODEL:
			return model != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Item.class) {
			switch (derivedFeatureID) {
			case ClassSupplierPackage.CONTRIBUTION__MODEL_NAME:
				return ClassSupplierPackage.ITEM__MODEL_NAME;
			case ClassSupplierPackage.CONTRIBUTION__PHASE:
				return ClassSupplierPackage.ITEM__PHASE;
			case ClassSupplierPackage.CONTRIBUTION__VERSION:
				return ClassSupplierPackage.ITEM__VERSION;
			case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
				return ClassSupplierPackage.ITEM__LANGUAGE;
			case ClassSupplierPackage.CONTRIBUTION__DOMAIN_MODEL:
				return ClassSupplierPackage.ITEM__DOMAIN_MODEL;
			case ClassSupplierPackage.CONTRIBUTION__CUSTOMIZERS:
				return ClassSupplierPackage.ITEM__CUSTOMIZERS;
			case ClassSupplierPackage.CONTRIBUTION__PARENT:
				return ClassSupplierPackage.ITEM__PARENT;
			case ClassSupplierPackage.CONTRIBUTION__LOCALE:
				return ClassSupplierPackage.ITEM__LOCALE;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Item.class) {
			switch (baseFeatureID) {
			case ClassSupplierPackage.ITEM__MODEL_NAME:
				return ClassSupplierPackage.CONTRIBUTION__MODEL_NAME;
			case ClassSupplierPackage.ITEM__PHASE:
				return ClassSupplierPackage.CONTRIBUTION__PHASE;
			case ClassSupplierPackage.ITEM__VERSION:
				return ClassSupplierPackage.CONTRIBUTION__VERSION;
			case ClassSupplierPackage.ITEM__LANGUAGE:
				return ClassSupplierPackage.CONTRIBUTION__LANGUAGE;
			case ClassSupplierPackage.ITEM__DOMAIN_MODEL:
				return ClassSupplierPackage.CONTRIBUTION__DOMAIN_MODEL;
			case ClassSupplierPackage.ITEM__CUSTOMIZERS:
				return ClassSupplierPackage.CONTRIBUTION__CUSTOMIZERS;
			case ClassSupplierPackage.ITEM__PARENT:
				return ClassSupplierPackage.CONTRIBUTION__PARENT;
			case ClassSupplierPackage.ITEM__LOCALE:
				return ClassSupplierPackage.CONTRIBUTION__LOCALE;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
