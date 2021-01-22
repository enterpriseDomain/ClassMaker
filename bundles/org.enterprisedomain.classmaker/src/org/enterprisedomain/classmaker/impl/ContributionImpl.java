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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.ReflogCommand;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.ReflogEntry;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Project;
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
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getDependencies
 * <em>Dependencies</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ContributionImpl#getLatestVersion
 * <em>Latest Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContributionImpl extends ProjectImpl implements Contribution {

	/**
	 * The default value of the '{@link #getLatestVersion() <em>Latest
	 * Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLatestVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version LATEST_VERSION_EDEFAULT = null;
	protected CompletionListener completionListener = new CompletionListenerImpl() {

		@Override
		public void completed(Project result) throws Exception {
			addResourceChangeListener(getResourceReloadListener());
		}

	};

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
	 * @generated NOT
	 */
	public void copyModel(Item from) {
		setModelName(from.getModelName());
		setLanguage(from.getLanguage());
		EObject eObject = from.getDomainModel().getDynamic();
		if (eObject != null)
			getDomainModel().setDynamic(EcoreUtil.copy(eObject));
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
	public Project basicGetProject() {
		return this;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setProject(Project newProject) {
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
	 * @generated NOT
	 */
	public Item basicGetParent() {
		return this;
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
		setName(getModelName() != null ? getModelName() : getProjectName());
		try {
			Git git = operator.getRepositorySCM();
			// if (git == null)
			// return "";
			String currentBranch = git.getRepository().getBranch();

			ListBranchCommand listBranches = git.branchList();
			List<Ref> branches = listBranches.call();
			Iterator<Ref> it = branches.iterator();
			Ref branch = null;
			long timestamp = -1;
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
					newRevision.setProject(this);
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
			ClassMakerPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
			return null;
		} finally {
			try {
				operator.ungetRepositorySCM();
			} catch (Exception e) {
				ClassMakerPlugin.getInstance().getLog()
						.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
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
			throw new CoreException(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
		}
		return revision.getState().getCommitId();
	}

	@Override
	public String make(IProgressMonitor monitor) throws CoreException {
		removeResourceChangeListener(getResourceReloadListener());
		String result = make(getRevision(), monitor);
		setDirty(false);
		return result;
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
		if (!eIsSet(ClassMakerPackage.ITEM__MODEL_NAME))
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
	 * ({@inheritDoc}) Should be used instead of {@link #getVersion()}.
	 * 
	 */
	@Override
	public void setProjectVersion(Version newProjectVersion) {
		super.setProjectVersion(newProjectVersion);
		setVersion(newProjectVersion);
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
	public State getState(long timestamp) {
		if (isRevisionSet() && getRevision().getStateHistory().containsKey(timestamp))
			return getRevision().getStateHistory().get((Long) timestamp);
		return getState();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void initAdapters(Revision revision) {
		addCompletionListener(completionListener);
		super.initAdapters(revision);
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
					throw new CoreException(
							new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
				}
			}
			getRevision().load(create);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void build(IProgressMonitor monitor) throws CoreException {
		if (isRevisionSet())
			getRevision().build(monitor);
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.CONTRIBUTION__DEPENDENCIES:
			return getDependencies();
		case ClassMakerPackage.CONTRIBUTION__LATEST_VERSION:
			return getLatestVersion();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ClassMakerPackage.CONTRIBUTION__DEPENDENCIES:
			return !getDependencies().isEmpty();
		case ClassMakerPackage.CONTRIBUTION__LATEST_VERSION:
			return LATEST_VERSION_EDEFAULT == null ? getLatestVersion() != null
					: !LATEST_VERSION_EDEFAULT.equals(getLatestVersion());
		}
		return super.eIsSet(featureID);
	}

} // SoftwareContributionImpl
