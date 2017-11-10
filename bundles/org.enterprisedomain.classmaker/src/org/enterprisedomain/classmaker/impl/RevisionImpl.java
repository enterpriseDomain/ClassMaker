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

import java.util.Calendar;
import java.util.TimeZone;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
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
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ListUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Revision</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.impl.RevisionImpl#getState <em>State</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.RevisionImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.RevisionImpl#getStateHistory <em>State History</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.RevisionImpl#getLatestTimestamp <em>Latest Timestamp</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RevisionImpl extends ItemImpl implements Revision {

	static {
		Revision.VERSION_QUALIFIER_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
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
	 * The cached value of the '{@link #getStateHistory() <em>State History</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStateHistory()
	 * @generated
	 * @ordered
	 */
	protected EMap<Integer, State> stateHistory;

	/**
	 * The default value of the '{@link #getLatestTimestamp() <em>Latest Timestamp</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLatestTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final int LATEST_TIMESTAMP_EDEFAULT = 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected RevisionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	public Contribution basicGetContribution() {
		return (Contribution) eContainer().eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setContribution(Contribution newContribution) {
		if (newContribution != null)
			newContribution.getRevisions().put(getVersion(), this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.REVISION__TIMESTAMP, oldTimestamp,
					timestamp));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<Integer, State> getStateHistory() {
		if (stateHistory == null) {
			stateHistory = new EcoreEMap<Integer, State>(ClassMakerPackage.Literals.INTEGER_TO_STATE_MAP_ENTRY,
					IntegerToStateMapEntryImpl.class, this, ClassMakerPackage.REVISION__STATE_HISTORY);
		}
		return stateHistory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getLatestTimestamp() {
		if (!getStateHistory().isEmpty())
			return ListUtil.getLast(getStateHistory()).getKey();
		return StateImpl.TIMESTAMP_EDEFAULT;
	}

	@Override
	public String getLanguage() {
		return getState().getLanguage();
	}

	@Override
	public void setLanguage(String value) {
		if (isStateSet())
			getState().setLanguage(value);
	}

	@Override
	public ModelPair getDomainModel() {
		return getState().getDomainModel();
	}

	@Override
	public EMap<StageQualifier, Customizer> getCustomizers() {
		return getState().getCustomizers();
	}

	protected boolean isStateSet() {
		return eIsSet(ClassMakerPackage.REVISION__STATE);
	}

	@Override
	public Item basicGetParent() {
		return getContribution();
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
		SCMOperator<Git> operator = (SCMOperator<Git>) ClassMakerPlugin.getClassMaker().getSCMRegistry()
				.get(getContribution().getProjectName());
		try {
			Git git = operator.getRepositorySCM();

			LogCommand log = git.log();
			Ref branch = git.getRepository().findRef(getVersion().toString());
			if (branch != null) {
				log.add(branch.getObjectId());
				Iterable<RevCommit> commits = log.call();
				for (RevCommit c : commits) {
					int timestamp = operator.decodeTimestamp(c.getShortMessage());
					if (timestamp == -1)
						continue;
					State state = null;
					if (getStateHistory().containsKey(timestamp))
						state = (State) getStateHistory().get((Object) timestamp);
					else {
						state = ClassMakerFactory.eINSTANCE.createState();
						state.setTimestamp(timestamp);
						getStateHistory().put(timestamp, state);
						state.setVersion(getVersion());
					}
					String commitId = c.getId().toString();
					state.getCommitIds().add(commitId);
					state.setCommitId(commitId);
					setTimestamp(timestamp);
					state.initialize(commit);
				}
				checkout(ListUtil.getLast(getStateHistory()).getKey());
			}
		} catch (NoHeadException e) {
			return null;
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
			SCMOperator<Git> operator = (SCMOperator<Git>) ClassMakerPlugin.getClassMaker().getSCMRegistry()
					.get(getContribution().getProjectName());
			try {
				Git git = operator.getRepositorySCM();
				git.branchCreate().setForce(true).setName(getVersion().toString()).call();
			} catch (Exception e) {
				throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
			} finally {
				try {
					operator.ungetRepositorySCM();
				} catch (Exception e) {
					throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
				}
			}
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(int stateTime, String commitId) {
		setTimestamp(stateTime);
		if (isStateSet())
			getState().checkout(commitId);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(int stateTime) {
		setTimestamp(stateTime);
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
				(int) (Calendar.getInstance(Revision.VERSION_QUALIFIER_FORMAT.getTimeZone()).getTimeInMillis() / 1000));
		getStateHistory().put(newState.getTimestamp(), newState);
		newState.setVersion(getVersion());
		return newState;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return commitId
	 * @generated NOT
	 */
	public String make(IProgressMonitor monitor) throws Exception {
		getState().copyModel(getContribution());
		return getState().make(monitor);
	}

	@Override
	public void load(boolean create) throws CoreException {
		initialize(false);
		getContribution().initAdapters(this);
		if (create && isStateSet()) {
			@SuppressWarnings("unchecked")
			SCMOperator<Git> operator = (SCMOperator<Git>) ClassMakerPlugin.getClassMaker().getSCMRegistry()
					.get(getContribution().getProjectName());
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
				throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
			} finally {
				try {
					operator.ungetRepositorySCM();
				} catch (Exception e) {
					throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
				}
			}
		}
		if (isStateSet())
			getState().load(create);
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
		getState().copyModel(from.getState());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
		case ClassMakerPackage.REVISION__STATE:
			setState((State) newValue);
			return;
		case ClassMakerPackage.REVISION__TIMESTAMP:
			setTimestamp((Integer) newValue);
			return;
		case ClassMakerPackage.REVISION__STATE_HISTORY:
			((EStructuralFeature.Setting) getStateHistory()).set(newValue);
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
		case ClassMakerPackage.REVISION__STATE:
			setState((State) null);
			return;
		case ClassMakerPackage.REVISION__TIMESTAMP:
			setTimestamp(TIMESTAMP_EDEFAULT);
			return;
		case ClassMakerPackage.REVISION__STATE_HISTORY:
			getStateHistory().clear();
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
		case ClassMakerPackage.REVISION__STATE:
			return basicGetState() != null;
		case ClassMakerPackage.REVISION__TIMESTAMP:
			return timestamp != TIMESTAMP_EDEFAULT;
		case ClassMakerPackage.REVISION__STATE_HISTORY:
			return stateHistory != null && !stateHistory.isEmpty();
		case ClassMakerPackage.REVISION__LATEST_TIMESTAMP:
			return getLatestTimestamp() != LATEST_TIMESTAMP_EDEFAULT;
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
		result.append(')');
		return result.toString();
	}

} // RevisionImpl
