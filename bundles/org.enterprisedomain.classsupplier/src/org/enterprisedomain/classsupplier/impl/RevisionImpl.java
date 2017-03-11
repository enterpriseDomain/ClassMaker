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
import java.util.Calendar;
import java.util.Collection;
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
import org.eclipse.jgit.api.ReflogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.ReflogEntry;
import org.enterprisedomain.classsupplier.ClassSupplierFactory;
import org.enterprisedomain.classsupplier.ClassSupplierPackage;
import org.enterprisedomain.classsupplier.Contribution;
import org.enterprisedomain.classsupplier.Customizer;
import org.enterprisedomain.classsupplier.Item;
import org.enterprisedomain.classsupplier.ModelPair;
import org.enterprisedomain.classsupplier.Revision;
import org.enterprisedomain.classsupplier.StageQualifier;
import org.enterprisedomain.classsupplier.State;
import org.enterprisedomain.classsupplier.core.ClassSupplierOSGi;
import org.enterprisedomain.classsupplier.util.ListUtil;
import org.enterprisedomain.classsupplier.util.GitUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Revision</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.RevisionImpl#getContribution <em>Contribution</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.RevisionImpl#getState <em>State</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.RevisionImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.RevisionImpl#getStateHistory <em>State History</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.RevisionImpl#getLatestTimestamp <em>Latest Timestamp</em>}</li>
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
		return ClassSupplierPackage.Literals.REVISION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.REVISION__TIMESTAMP,
					oldTimestamp, timestamp));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<Integer, State> getStateHistory() {
		if (stateHistory == null) {
			stateHistory = new EcoreEMap<Integer, State>(ClassSupplierPackage.Literals.INTEGER_TO_STATE_MAP_ENTRY,
					IntegerToStateMapEntryImpl.class, this, ClassSupplierPackage.REVISION__STATE_HISTORY);
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
			return getStateHistory().get(ListUtil.lastIndex(getStateHistory().size())).getKey();
		return StateImpl.TIMESTAMP_EDEFAULT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String initialize() {
		super.initialize();
		try {
			Git git = GitUtil.getRepositoryGit(getContribution().getProjectName());
			Ref branch = git.getRepository().findRef(getVersion().toString());
			if (branch == null) {
				return "";
			}
			ReflogCommand reflog = git.reflog();
			reflog.setRef(branch.getName());
			Collection<ReflogEntry> refs = reflog.call();
			for (ReflogEntry ref : refs) {
				int timestamp = GitUtil.getTimestamp(ref.getComment());
				if (timestamp == -1)
					continue;
				State state = ClassSupplierFactory.eINSTANCE.createState();
				state.setTimestamp(timestamp);
				state.getCommitIds().add(ref.getNewId().toString());
				getStateHistory().put(timestamp, state);
				return state.initialize();
			}
		} catch (GitAPIException e) {
			ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(e));
			return null;
		} catch (IOException e) {
			ClassSupplierOSGi.getInstance().getLog().log(ClassSupplierOSGi.createErrorStatus(e));
			return null;
		}
		return "";
	}

	@Override
	public String getLanguage() {
		return getState().getLanguage();
	}

	@Override
	public void setLanguage(String value) {
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
		return eIsSet(ClassSupplierPackage.REVISION__STATE);
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void create(IProgressMonitor monitor) throws CoreException {
		if (isStateSet()) {
			try {
				Git git = GitUtil.getRepositoryGit(getContribution().getProjectName());
				git.branchCreate().setForce(true).setName(getVersion().toString()).call();
			} catch (GitAPIException e) {
				throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
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
		if (eIsSet(ClassSupplierPackage.REVISION__STATE))
			getState().checkout(commitId);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(int stateTime) {
		setTimestamp(stateTime);
		if (eIsSet(ClassSupplierPackage.REVISION__STATE))
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
		State newState = ClassSupplierFactory.eINSTANCE.createState();
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
	public String save(IProgressMonitor monitor) throws Exception {
		getState().copyModel(getContribution());
		getState().saveResource();
		State state = newState();
		state.copyModel(getState());
		String commitId = state.initialize();
		checkout(state.getTimestamp(), commitId);
		state.load(false);
		return getState().save(monitor);
	}

	@Override
	public void load(boolean create) throws CoreException {
		initialize();
		getContribution().initAdapters(this);
		if (create && isStateSet()) {
			Git git;
			try {
				git = GitUtil.getRepositoryGit(getContribution().getProjectName());
				Ref branch = git.getRepository().findRef(getVersion().toString());
				if (branch == null) {
					getState().initialize();
					create(ClassSupplierOSGi.getInstance().getProgressMonitor());
				}
			} catch (GitAPIException e) {
				throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
			} catch (IOException e) {
				throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
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
			try {
				state.commit(".");
			} catch (Exception e) {
				throw new CoreException(ClassSupplierOSGi.createErrorStatus(e));
			}
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
		case ClassSupplierPackage.REVISION__STATE_HISTORY:
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
		case ClassSupplierPackage.REVISION__CONTRIBUTION:
			if (resolve)
				return getContribution();
			return basicGetContribution();
		case ClassSupplierPackage.REVISION__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ClassSupplierPackage.REVISION__TIMESTAMP:
			return getTimestamp();
		case ClassSupplierPackage.REVISION__STATE_HISTORY:
			if (coreType)
				return getStateHistory();
			else
				return getStateHistory().map();
		case ClassSupplierPackage.REVISION__LATEST_TIMESTAMP:
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
		case ClassSupplierPackage.REVISION__CONTRIBUTION:
			setContribution((Contribution) newValue);
			return;
		case ClassSupplierPackage.REVISION__STATE:
			setState((State) newValue);
			return;
		case ClassSupplierPackage.REVISION__TIMESTAMP:
			setTimestamp((Integer) newValue);
			return;
		case ClassSupplierPackage.REVISION__STATE_HISTORY:
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
		case ClassSupplierPackage.REVISION__CONTRIBUTION:
			setContribution((Contribution) null);
			return;
		case ClassSupplierPackage.REVISION__STATE:
			setState((State) null);
			return;
		case ClassSupplierPackage.REVISION__TIMESTAMP:
			setTimestamp(TIMESTAMP_EDEFAULT);
			return;
		case ClassSupplierPackage.REVISION__STATE_HISTORY:
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
		case ClassSupplierPackage.REVISION__CONTRIBUTION:
			return basicGetContribution() != null;
		case ClassSupplierPackage.REVISION__STATE:
			return basicGetState() != null;
		case ClassSupplierPackage.REVISION__TIMESTAMP:
			return timestamp != TIMESTAMP_EDEFAULT;
		case ClassSupplierPackage.REVISION__STATE_HISTORY:
			return stateHistory != null && !stateHistory.isEmpty();
		case ClassSupplierPackage.REVISION__LATEST_TIMESTAMP:
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
