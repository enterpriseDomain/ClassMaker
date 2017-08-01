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
package org.enterprisedomain.classmaker.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;

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
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.jobs.export.Exporter;
import org.enterprisedomain.classmaker.util.GitUtil;
import org.enterprisedomain.classmaker.util.ListUtil;
import org.enterprisedomain.classmaker.util.ModelUtil;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Contribution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getModelName <em>Model Name</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getPhase <em>Phase</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getDomainModel <em>Domain Model</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getCustomizers <em>Customizers</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getLocale <em>Locale</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getContribution <em>Contribution</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getRevision <em>Revision</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getRevisions <em>Revisions</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getState <em>State</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getLatestVersion <em>Latest Version</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#isCompletionNotified <em>Completion Notified</em>}</li>
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
	protected static final Locale LOCALE_EDEFAULT = (Locale) ClassMakerFactory.eINSTANCE
			.createFromString(ClassMakerPackage.eINSTANCE.getLocale(), "");
	/**
	 * The cached value of the '{@link #getLocale() <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLocale()
	 * @generated
	 * @ordered
	 */
	protected Locale locale = LOCALE_EDEFAULT;
	/**
	 * The default value of the '{@link #getProperties() <em>Properties</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected static final Properties PROPERTIES_EDEFAULT = (Properties) ClassMakerFactory.eINSTANCE
			.createFromString(ClassMakerPackage.eINSTANCE.getProperties(), "");
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected Properties properties = PROPERTIES_EDEFAULT;
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
	 * The cached value of the '{@link #getModel() <em>Model</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected ResourceAdapter model;

	/**
	 * The default value of the '{@link #isCompletionNotified() <em>Completion Notified</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCompletionNotified()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COMPLETION_NOTIFIED_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isCompletionNotified() <em>Completion Notified</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCompletionNotified()
	 * @generated
	 * @ordered
	 */
	protected boolean completionNotified = COMPLETION_NOTIFIED_EDEFAULT;
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
			if (msg.getFeatureID(State.class) == ClassMakerPackage.STATE__RESOURCE
					&& msg.getEventType() == Notification.SET) {
				getModel();
			}
		}
	};

	protected final class CompletionNotificationAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Contribution.class) == ClassMakerPackage.CONTRIBUTION__COMPLETION_NOTIFIED
					&& msg.getNewBooleanValue())
				try {
					ContributionImpl.this.notifyCompletion();
				} catch (Exception e) {
					ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
				}

		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ContributionImpl() {
		super();
		eAdapters().add(new CompletionNotificationAdapter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.CONTRIBUTION;
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
			model = ClassMakerFactory.eINSTANCE.createResourceAdapter();
			model.setResource(getState().getResource());
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CONTRIBUTION__MODEL, null,
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
	 * @generated
	 */
	public boolean isCompletionNotified() {
		return completionNotified;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompletionNotified(boolean newCompletionNotified) {
		boolean oldCompletionNotified = completionNotified;
		completionNotified = newCompletionNotified;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CONTRIBUTION__COMPLETION_NOTIFIED,
					oldCompletionNotified, completionNotified));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override 
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
					Revision revision = ClassMakerFactory.eINSTANCE.createRevision();
					revision.setVersion(version);
					getRevisions().put(version, revision);
				}
				if (version != null && branch.getName().equals(currentBranch))
					setVersion(version);
			} while (it.hasNext());
			return getRevision().initialize();
		} catch (GitAPIException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			return null;
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			return null;
		}
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
	 * @generated
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperties(Properties newProperties) {
		Properties oldProperties = properties;
		properties = newProperties;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CONTRIBUTION__PROPERTIES,
					oldProperties, properties));
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
			revisions = new EcoreEMap<Version, Revision>(ClassMakerPackage.Literals.VERSION_TO_REVISION_MAP_ENTRY,
					VersionToRevisionMapEntryImpl.class, this, ClassMakerPackage.CONTRIBUTION__REVISIONS);
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
			throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
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
			if (!revision.eIsSet(ClassMakerPackage.Literals.REVISION__TIMESTAMP))
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
						ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(ex));
					} catch (GitAPIException ex) {
						ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(ex));
					}
				}
			} catch (GitAPIException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			} catch (IOException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			}
			revision = getRevisions().get(version);
			if (revision.getStateHistory().containsKey(time)) {
				EList<String> commits = revision.getStateHistory().get((Object) time).getCommitIds();
				if (!commits.isEmpty()) {
					revision.checkout(time, commits.get(ListUtil.lastIndex(commits.size())));
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
		Revision newRevision = ClassMakerFactory.eINSTANCE.createRevision();
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
		if (!eIsSet(ClassMakerPackage.Literals.ITEM__VERSION))
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CONTRIBUTION__VERSION, oldVersion,
					version));
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
			if (msg.getFeatureID(Contribution.class) == ClassMakerPackage.CONTRIBUTION__MODEL
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
					if (msg.getFeatureID(ResourceAdapter.class) == ClassMakerPackage.RESOURCE_ADAPTER__RESOURCE
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
		super.delete(monitor);
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
		case ClassMakerPackage.CONTRIBUTION__DOMAIN_MODEL:
			return basicSetDomainModel(null, msgs);
		case ClassMakerPackage.CONTRIBUTION__CUSTOMIZERS:
			return ((InternalEList<?>) getCustomizers()).basicRemove(otherEnd, msgs);
		case ClassMakerPackage.CONTRIBUTION__REVISIONS:
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
		case ClassMakerPackage.CONTRIBUTION__PROPERTIES:
			return getProperties();
		case ClassMakerPackage.CONTRIBUTION__DEPENDENCIES:
			return getDependencies();
		case ClassMakerPackage.CONTRIBUTION__REVISION:
			if (resolve)
				return getRevision();
			return basicGetRevision();
		case ClassMakerPackage.CONTRIBUTION__REVISIONS:
			if (coreType)
				return getRevisions();
			else
				return getRevisions().map();
		case ClassMakerPackage.CONTRIBUTION__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ClassMakerPackage.CONTRIBUTION__LATEST_VERSION:
			return getLatestVersion();
		case ClassMakerPackage.CONTRIBUTION__MODEL:
			if (resolve)
				return getModel();
			return basicGetModel();
		case ClassMakerPackage.CONTRIBUTION__COMPLETION_NOTIFIED:
			return isCompletionNotified();
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
		case ClassMakerPackage.CONTRIBUTION__PROPERTIES:
			setProperties((Properties) newValue);
			return;
		case ClassMakerPackage.CONTRIBUTION__REVISION:
			setRevision((Revision) newValue);
			return;
		case ClassMakerPackage.CONTRIBUTION__REVISIONS:
			((EStructuralFeature.Setting) getRevisions()).set(newValue);
			return;
		case ClassMakerPackage.CONTRIBUTION__COMPLETION_NOTIFIED:
			setCompletionNotified((Boolean) newValue);
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
		case ClassMakerPackage.CONTRIBUTION__PROPERTIES:
			setProperties(PROPERTIES_EDEFAULT);
			return;
		case ClassMakerPackage.CONTRIBUTION__REVISION:
			setRevision((Revision) null);
			return;
		case ClassMakerPackage.CONTRIBUTION__REVISIONS:
			getRevisions().clear();
			return;
		case ClassMakerPackage.CONTRIBUTION__COMPLETION_NOTIFIED:
			setCompletionNotified(COMPLETION_NOTIFIED_EDEFAULT);
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
		case ClassMakerPackage.CONTRIBUTION__PROPERTIES:
			return PROPERTIES_EDEFAULT == null ? properties != null : !PROPERTIES_EDEFAULT.equals(properties);
		case ClassMakerPackage.CONTRIBUTION__DEPENDENCIES:
			return !getDependencies().isEmpty();
		case ClassMakerPackage.CONTRIBUTION__REVISION:
			return basicGetRevision() != null;
		case ClassMakerPackage.CONTRIBUTION__REVISIONS:
			return revisions != null && !revisions.isEmpty();
		case ClassMakerPackage.CONTRIBUTION__STATE:
			return basicGetState() != null;
		case ClassMakerPackage.CONTRIBUTION__LATEST_VERSION:
			return LATEST_VERSION_EDEFAULT == null ? getLatestVersion() != null
					: !LATEST_VERSION_EDEFAULT.equals(getLatestVersion());
		case ClassMakerPackage.CONTRIBUTION__MODEL:
			return model != null;
		case ClassMakerPackage.CONTRIBUTION__COMPLETION_NOTIFIED:
			return completionNotified != COMPLETION_NOTIFIED_EDEFAULT;
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
			case ClassMakerPackage.CONTRIBUTION__PROPERTIES:
				return ClassMakerPackage.ITEM__PROPERTIES;
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
			case ClassMakerPackage.ITEM__PROPERTIES:
				return ClassMakerPackage.CONTRIBUTION__PROPERTIES;
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
		result.append(", properties: ");
		result.append(properties);
		result.append(", completionNotified: ");
		result.append(completionNotified);
		result.append(')');
		return result.toString();
	}

} // SoftwareContributionImpl
