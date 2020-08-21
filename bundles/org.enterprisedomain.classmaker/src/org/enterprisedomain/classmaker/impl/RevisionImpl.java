/**
 * Copyright 2012-2018 Kyrill Zotkin
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

import java.util.Calendar;
import java.util.TimeZone;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ListUtil;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Revision</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.RevisionImpl#getState
 * <em>State</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.RevisionImpl#getTimestamp
 * <em>Timestamp</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.RevisionImpl#getStateHistory
 * <em>State History</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.RevisionImpl#getLatestTimestamp
 * <em>Latest Timestamp</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.RevisionImpl#getVersion
 * <em>Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RevisionImpl extends ItemImpl implements Revision {

	static {
		Revision.VERSION_QUALIFIER_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
	}

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final long TIMESTAMP_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected long timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStateHistory() <em>State History</em>}'
	 * map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStateHistory()
	 * @generated
	 * @ordered
	 */
	protected EMap<Long, State> stateHistory;

	/**
	 * The default value of the '{@link #getLatestTimestamp() <em>Latest
	 * Timestamp</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLatestTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final long LATEST_TIMESTAMP_EDEFAULT = 0L;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = null;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RevisionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.REVISION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setProject(Project newProject) {
		if (newProject != null)
			newProject.getRevisions().put(getVersion(), this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public State getState() {
		State state = basicGetState();
		return state != null && state.eIsProxy() ? (State) eResolveProxy((InternalEObject) state) : state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State basicGetState() {
		return getStateHistory().get((Object) getTimestamp());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setState(State newState) {
		if (!getStateHistory().containsKey(newState.getTimestamp()))
			getStateHistory().put(newState.getTimestamp(), newState);
		setTimestamp(newState.getTimestamp());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setTimestamp(long newTimestamp) {
		long oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.REVISION__TIMESTAMP, oldTimestamp,
					timestamp));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<Long, State> getStateHistory() {
		if (stateHistory == null) {
			stateHistory = new EcoreEMap<Long, State>(ClassMakerPackage.Literals.LONG_TO_STATE_MAP_ENTRY,
					LongToStateMapEntryImpl.class, this, ClassMakerPackage.REVISION__STATE_HISTORY);
		}
		return stateHistory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public long getLatestTimestamp() {
		if (!getStateHistory().isEmpty())
			return ListUtil.getLast(getStateHistory()).getKey();
		return StateImpl.TIMESTAMP_EDEFAULT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Version getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setVersion(Version newVersion) {
		Version oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.REVISION__VERSION, oldVersion,
					version));
	}

	@Override
	public String getLanguage() {
		if (isStateSet())
			return getState().getLanguage();
		return LANGUAGE_EDEFAULT;
	}

	@Override
	public void setLanguage(String value) {
		if (isStateSet())
			getState().setLanguage(value);
	}

	@Override
	public ModelPair getDomainModel() {
		if (isStateSet())
			return getState().getDomainModel();
		return null;
	}

	@Override
	public EMap<StageQualifier, Customizer> getCustomizers() {
		if (isStateSet())
			return getState().getCustomizers();
		return ECollections.emptyEMap();
	}

	protected boolean isStateSet() {
		return eIsSet(ClassMakerPackage.REVISION__STATE);
	}

	@Override
	public Item basicGetParent() {
		if (getProject() instanceof Item)
			return (Item) getProject();
		return null;
	}

	@Override
	public void setParent(Item newParent) {
		if (newParent instanceof Contribution)
			((Contribution) newParent).getRevisions().put(getVersion(), this);
		super.setParent(newParent);
	}

	@Override
	public String initialize(boolean commit) {
		super.initialize(commit);
		@SuppressWarnings("unchecked")
		SCMOperator<Git> operator = (SCMOperator<Git>) getProject().getWorkspace().getSCMRegistry()
				.get(getProject().getProjectName());
		try {
			Git git = operator.getRepositorySCM();

			LogCommand log = git.log();
			Ref branch = git.getRepository().findRef(getVersion().toString());
			if (branch != null) {
				log.add(branch.getObjectId());
				Iterable<RevCommit> commits = log.call();
				for (RevCommit c : commits) {
					long timestamp = operator.decodeTimestamp(c.getShortMessage());
					if (timestamp == -1) {
						timestamp = operator.decodeTimestamp(getVersion().getQualifier());
						if (timestamp == -1)
							continue;
					}
					State state = null;
					if (getStateHistory().containsKey(timestamp))
						state = (State) getStateHistory().get((Object) timestamp);
					else {
						state = ClassMakerFactory.eINSTANCE.createState();
						state.setTimestamp(timestamp);
						getStateHistory().put(timestamp, state);
						state.getProject().setVersion(getVersion());
					}
					String commitId = c.getId().toString();
					state.getCommitIds().add(commitId);
					state.setCommitId(commitId);
					setTimestamp(timestamp);
					state.initialize(commit);
				}
				if (getStateHistory().isEmpty())
					return null;
			}
		} catch (NoHeadException e) {
			return null;
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
		return getState().getCommitId();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void create(IProgressMonitor monitor) throws CoreException {
		if (isStateSet()) {
			@SuppressWarnings("unchecked")
			SCMOperator<Git> operator = (SCMOperator<Git>) getProject().getWorkspace().getSCMRegistry()
					.get(getProject().getProjectName());
			Git git = null;
			try {
				git = operator.getRepositorySCM();
				if (git != null)
					git.branchCreate().setForce(true).setName(getVersion().toString()).call();
			} catch (RefNotFoundException e) {
				try {
					getState().commit();
					git.branchCreate().setForce(true).setName(getVersion().toString()).call();
				} catch (Exception e1) {
					throw new CoreException(
							new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
				}
			} catch (Exception e) {
				throw new CoreException(
						new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
			} finally {
				try {
					operator.ungetRepositorySCM();
				} catch (Exception e) {
					throw new CoreException(
							new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
				}
			}
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(long stateTime, String commitId) {
		setTimestamp(stateTime);
		try {
			load(true);
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		}
		if (isStateSet())
			getState().checkout(commitId, false);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(long stateTime) {
		setTimestamp(stateTime);
		try {
			load(true);
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		}
		if (isStateSet())
			getState().checkout();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(String commitId) {
		for (State state : getStateHistory().values())
			if (state.getCommitIds().contains(commitId))
				checkout(state.getTimestamp(), commitId);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public State newState() {
		State newState = ClassMakerFactory.eINSTANCE.createState();
		newState.setTimestamp(
				(long) (Calendar.getInstance(Revision.VERSION_QUALIFIER_FORMAT.getTimeZone()).getTimeInMillis()
						/ 1000));
		getStateHistory().put(newState.getTimestamp(), newState);
		newState.getProject().setVersion(getVersion());
		return newState;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return commitId
	 * @generated NOT
	 */
	public String make(IProgressMonitor monitor) throws Exception {
		getState().copyModel(getParent());
		return getState().make(monitor);
	}

	@Override
	public void load(boolean create) throws CoreException {
		initialize(false);
		if (create && isStateSet()) {
			@SuppressWarnings("unchecked")
			SCMOperator<Git> operator = (SCMOperator<Git>) getProject().getWorkspace().getSCMRegistry()
					.get(getProject().getProjectName());
			Git git = null;
			try {
				git = operator.getRepositorySCM();
				Ref branch = git.getRepository().findRef(getVersion().toString());
				if (branch == null) {
					if (create) {
						create(ClassMakerPlugin.getProgressMonitor());
					}
					getState().initialize(false);
				}
			} catch (Exception e) {
				throw new CoreException(
						new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
			} finally {
				try {
					operator.ungetRepositorySCM();
				} catch (Exception e) {
					throw new CoreException(
							new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
				}
			}
		}
		if (isStateSet()) {
			getState().load(create);
			getProject().initAdapters(this);
		}
	}

	/**
	 * <!-- begin-user-doc --><!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(IProgressMonitor monitor) throws CoreException {
		if (isStateSet()) {
			State state = getState();
			state.delete(monitor);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void addAdapters(EList<Adapter> adapters) {
		for (State state : getStateHistory().values())
			state.eAdapters().addAll(adapters);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void copyModel(Revision from) {
		super.copyModel(from);
		getState().copyModel(from.getState());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.REVISION__STATE_HISTORY:
			return ((InternalEList<?>) getStateHistory()).basicRemove(otherEnd, msgs);
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
		case ClassMakerPackage.REVISION__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ClassMakerPackage.REVISION__TIMESTAMP:
			return getTimestamp();
		case ClassMakerPackage.REVISION__STATE_HISTORY:
			if (coreType)
				return getStateHistory();
			else
				return getStateHistory().map();
		case ClassMakerPackage.REVISION__LATEST_TIMESTAMP:
			return getLatestTimestamp();
		case ClassMakerPackage.REVISION__VERSION:
			return getVersion();
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
		case ClassMakerPackage.REVISION__STATE:
			setState((State) newValue);
			return;
		case ClassMakerPackage.REVISION__TIMESTAMP:
			setTimestamp((Long) newValue);
			return;
		case ClassMakerPackage.REVISION__STATE_HISTORY:
			((EStructuralFeature.Setting) getStateHistory()).set(newValue);
			return;
		case ClassMakerPackage.REVISION__VERSION:
			setVersion((Version) newValue);
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
		case ClassMakerPackage.REVISION__STATE:
			setState((State) null);
			return;
		case ClassMakerPackage.REVISION__TIMESTAMP:
			setTimestamp(TIMESTAMP_EDEFAULT);
			return;
		case ClassMakerPackage.REVISION__STATE_HISTORY:
			getStateHistory().clear();
			return;
		case ClassMakerPackage.REVISION__VERSION:
			setVersion(VERSION_EDEFAULT);
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
		case ClassMakerPackage.REVISION__STATE:
			return basicGetState() != null;
		case ClassMakerPackage.REVISION__TIMESTAMP:
			return timestamp != TIMESTAMP_EDEFAULT;
		case ClassMakerPackage.REVISION__STATE_HISTORY:
			return stateHistory != null && !stateHistory.isEmpty();
		case ClassMakerPackage.REVISION__LATEST_TIMESTAMP:
			return getLatestTimestamp() != LATEST_TIMESTAMP_EDEFAULT;
		case ClassMakerPackage.REVISION__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
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
		result.append(" (timestamp: ");
		result.append(timestamp);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

	@Override
	public void build(IProgressMonitor monitor) throws CoreException {
		getState().build(monitor);
	}

	@Override
	public Project basicGetProject() {
		if (eContainer().eContainer() instanceof Project)
			return (Project) eContainer().eContainer();
		else
			return null;
	}

} // RevisionImpl
