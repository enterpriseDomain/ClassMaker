/**
 * Copyright 2019 Kyrill Zotkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.impl;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Strategy;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.jobs.EnterpriseDomainJob;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.enterprisedomain.classmaker.jobs.export.AbstractExporter;
import org.enterprisedomain.classmaker.util.ResourceUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Strategy</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.StrategyImpl#getState
 * <em>State</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StrategyImpl#getWorkers
 * <em>Workers</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StrategyImpl extends EObjectImpl implements Strategy {
	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected State state;

	/**
	 * The cached value of the '{@link #getWorkers() <em>Workers</em>}' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWorkers()
	 * @generated
	 * @ordered
	 */
	protected EMap<StageQualifier, EList<Worker>> workers;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StrategyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.STRATEGY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public State getState() {
		if (state != null && state.eIsProxy()) {
			InternalEObject oldState = (InternalEObject) state;
			state = (State) eResolveProxy(oldState);
			if (state != oldState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClassMakerPackage.STRATEGY__STATE,
							oldState, state));
			}
		}
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public State basicGetState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetState(State newState, NotificationChain msgs) {
		State oldState = state;
		state = newState;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.STRATEGY__STATE, oldState, newState);
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
	@Override
	public void setState(State newState) {
		if (newState != state) {
			NotificationChain msgs = null;
			if (state != null)
				msgs = ((InternalEObject) state).eInverseRemove(this, ClassMakerPackage.STATE__STRATEGY, State.class,
						msgs);
			if (newState != null)
				msgs = ((InternalEObject) newState).eInverseAdd(this, ClassMakerPackage.STATE__STRATEGY, State.class,
						msgs);
			msgs = basicSetState(newState, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STRATEGY__STATE, newState,
					newState));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<StageQualifier, EList<Worker>> getWorkers() {
		if (workers == null) {
			workers = new EcoreEMap<StageQualifier, EList<Worker>>(
					ClassMakerPackage.Literals.STAGE_QUALIFIER_TO_WORKERS_MAP_ENTRY,
					StageQualifierToWorkersMapEntryImpl.class, this, ClassMakerPackage.STRATEGY__WORKERS);
		}
		return workers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private Worker create(Stage stage, String step) {
		StageQualifier stageQualifier = stageLookup(stage, step);
		Worker worker = createWithCustomizer(stageQualifier,
				getWorkers().get(stageQualifier) != null ? getWorkers().get(stageQualifier).size() : 0,
				getState().getTimestamp(), getEclipseProject());
		if (getWorkers().containsKey(stageQualifier)) {
			EList<Worker> workers = getWorkers().get(stageQualifier);
			workers.add(worker);
			getWorkers().put(stageQualifier, workers);
		} else {
			getWorkers().put(stageQualifier, ECollections.newBasicEList(worker));
		}
		return worker;
	}

	private StageQualifier stageLookup(Stage stage, String step) {
		return ClassMakerService.Stages.lookup(stage, step);
	}

	private Worker getOrCreate(boolean create, Stage stage, String step) {
		if (create)
			return create(stage, step);
		else
			return get(stage, step).get(get(stage, step).size() - 1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Worker getReturnWorker() {
		int depth = configureJobs(get(Stage.GENERATED, "project.create.generator").size() < 5,
				ClassMakerPlugin.getProgressMonitor());
		EnterpriseDomainJob generatorJob = EnterpriseDomainJob
				.getJob(get(Stage.GENERATED, "project.create.generator").get(depth));
		EnterpriseDomainJob exporterJob = EnterpriseDomainJob
				.getJob(get(Stage.EXPORTED, "project.create.exporter").get(depth));
		EnterpriseDomainJob installerJob = EnterpriseDomainJob
				.getJob(get(Stage.INSTALLED, "project.create.installer").get(depth));
		EnterpriseDomainJob loaderJob = EnterpriseDomainJob
				.getJob(get(Stage.LOADED, "project.create.loader").get(depth));
		switch (depth % 5) {
		case 1:
			switch (getState().getPhase().getValue()) {
			case Stage.DEFINED_VALUE:
				getState().saveResource();
			case Stage.MODELED_VALUE:
				return generatorJob;
			case Stage.GENERATED_VALUE:
				return exporterJob;
			case Stage.EXPORTED_VALUE:
				return installerJob;
			case Stage.INSTALLED_VALUE:
			case Stage.LOADED_VALUE:
				return loaderJob;
			}
		case 2:
			switch (getState().getPhase().getValue()) {
			case Stage.DEFINED_VALUE:
				getState().saveResource();
			case Stage.MODELED_VALUE:
			case Stage.INSTALLED_VALUE:
				return generatorJob;
			case Stage.GENERATED_VALUE:
				return exporterJob;
			case Stage.EXPORTED_VALUE:
				return installerJob;
			case Stage.LOADED_VALUE:
				return loaderJob;
			}
		case 3:
			switch (getState().getPhase().getValue()) {
			case Stage.DEFINED_VALUE:
				getState().saveResource();
			case Stage.MODELED_VALUE:
				return generatorJob;
			case Stage.GENERATED_VALUE:
				return exporterJob;
			case Stage.EXPORTED_VALUE:
			case Stage.INSTALLED_VALUE:
				return installerJob;
			case Stage.LOADED_VALUE:
				return loaderJob;
			}
		case 4:
			return null;
		}
		return null;
	}

	private IProject getEclipseProject() {
		return ResourceUtils.getProject(getState().getProjectName());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public int configureJobs(boolean create, IProgressMonitor monitor) {
		Worker exporter = getOrCreate(create, Stage.EXPORTED, "project.create.exporter");

		EnterpriseDomainJob exporterJob = EnterpriseDomainJob.getJob(exporter);
		exporterJob.setContributionState(getState());
		boolean changeRule = exporterJob.isChangeRule();
		if (exporterJob.getState() != Job.NONE)
			exporterJob.setChangeRule(false);
		exporterJob.setProject(getEclipseProject());
		exporterJob.setChangeRule(changeRule);
		exporter.getProperties().put(AbstractExporter.EXPORT_DESTINATION_PROP,
				ResourceUtils.getExportDestination(getState()).toString());

		Worker generator = getOrCreate(create, Stage.GENERATED, "project.create.generator");
		EnterpriseDomainJob generatorJob = EnterpriseDomainJob.getJob(generator);
		generatorJob.setResourceSet(getState().getProject().getWorkspace().getResourceSet());
		generatorJob.setContributionState(getState());
		generatorJob.setProject(getEclipseProject());
		generatorJob.setProgressGroup(monitor, 1);
		generatorJob.setNextJob(exporterJob);

		Worker installer = getOrCreate(create, Stage.INSTALLED, "project.create.installer");
		EnterpriseDomainJob installJob = EnterpriseDomainJob.getJob(installer);
		installJob.setContributionState(getState());
		installJob.setProject(getEclipseProject());
		exporterJob.setNextJob(installJob);

		Worker modelLoader = getOrCreate(create, Stage.LOADED, "project.create.loader");
		EnterpriseDomainJob loadJob = EnterpriseDomainJob.getJob(modelLoader);
		loadJob.setContributionState(getState());
		loadJob.addListener();

		installJob.setNextJob(loadJob);
		return get(Stage.GENERATED, "project.create.generator").size() - 1;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void configuteBuildJobs(IProgressMonitor monitor) {
		Worker exporter = create(Stage.EXPORTED, "project.create.exporter");
		EnterpriseDomainJob exportJob = EnterpriseDomainJob.getJob(exporter);
		exportJob.setContributionState(getState());
		exportJob.setProject(getEclipseProject());
		exporter.getProperties().put(AbstractExporter.EXPORT_DESTINATION_PROP,
				ResourceUtils.getExportDestination(getState()).toString());

		Worker generator = create(Stage.GENERATED, "project.create.generator");
		EnterpriseDomainJob generatorJob = EnterpriseDomainJob.getJob(generator);
		generatorJob.setResourceSet(getState().getProject().getWorkspace().getResourceSet());
		generatorJob.setContributionState(getState());
		generatorJob.setProject(getEclipseProject());
		generatorJob.setProgressGroup(monitor, 1);
		generatorJob.setNextJob(exportJob);

		EnterpriseDomainJob installJob = EnterpriseDomainJob
				.getJob(create(Stage.INSTALLED, "project.create.installer"));
		installJob.setContributionState(getState());
		installJob.setProject(getEclipseProject());

		EnterpriseDomainJob loadJob = EnterpriseDomainJob.getJob(create(Stage.LOADED, "project.create.loader"));
		loadJob.setContributionState(getState());
		loadJob.addListener();

		exportJob.setNextJob(installJob);
		installJob.setNextJob(loadJob);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EList<Worker> get(Stage stage, String step) {
		StageQualifier stageQualifier = stageLookup(stage, step);
		if (getWorkers().containsKey(stageQualifier))
			return getWorkers().get(stageQualifier);
		else
			return ECollections.emptyEList();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.STRATEGY__STATE:
			if (state != null)
				msgs = ((InternalEObject) state).eInverseRemove(this, ClassMakerPackage.STATE__STRATEGY, State.class,
						msgs);
			return basicSetState((State) otherEnd, msgs);
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
		case ClassMakerPackage.STRATEGY__STATE:
			return basicSetState(null, msgs);
		case ClassMakerPackage.STRATEGY__WORKERS:
			return ((InternalEList<?>) getWorkers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	private Worker createWithCustomizer(StageQualifier stage, Object... customizerArgs) {
		SortedSet<Customizer> customizers = createCustomizers(stage);
		if (customizers == null)
			return null;
		Customizer c = customizers.last();
		SortedSet<Customizer> neCustomizers = createNonExclusiveCustomizers(stage);
		Worker w = (Worker) c.customize(ECollections.asEList(customizerArgs));
		EnterpriseDomainJob e = w.getAdapter(EnterpriseDomainJob.class);
		if (neCustomizers == null)
			return w;
		EnterpriseDomainJob job = null;
		Iterator<Customizer> it = neCustomizers.iterator();
		Customizer n = null;
		if (it.hasNext()) {
			n = it.next();
			job = ((Worker) n.customize(ECollections.asEList(customizerArgs))).getAdapter(EnterpriseDomainJob.class);
			job.setExclusive(false);
			e.setNextJob(job);
		}
		while (it.hasNext()) {
			job = ((Worker) n.customize(ECollections.asEList(customizerArgs))).getAdapter(EnterpriseDomainJob.class);
			if (job != null) {
				job.setExclusive(false);
				n = it.next();
				job.setNextJob(((Worker) n.customize(ECollections.asEList(customizerArgs)))
						.getAdapter(EnterpriseDomainJob.class));
				job.getNextJob().setExclusive(false);
			}
		}
		return w;
	}

	private SortedSet<Customizer> createCustomizers(StageQualifier stage) {
		SortedSet<Customizer> customizers = new TreeSet<Customizer>(new Customizer.CustomizerComparator());

		for (StageQualifier filter : getState().getStateCustomizers().keySet())
			if (stage.equals(filter))
				customizers.add(getState().getStateCustomizers().get(filter));
		for (StageQualifier filter : getState().getProject().getWorkspace().getCustomizers().keySet())
			if (filter.equals(stage))
				customizers.add(getState().getProject().getWorkspace().getCustomizers().get(filter));
		if (customizers.isEmpty())
			return null;
		return customizers;
	}

	private SortedSet<Customizer> createNonExclusiveCustomizers(StageQualifier stage) {
		SortedSet<Customizer> customizers = new TreeSet<Customizer>(new Customizer.CustomizerComparator());

		for (StageQualifier filter : getState().getNonExclusiveStateCustomizers().keySet())
			if (stage.equals(filter))
				customizers.add(getState().getNonExclusiveStateCustomizers().get(filter));
		for (StageQualifier filter : getState().getProject().getWorkspace().getNonExclusiveCustomizers().keySet())
			if (filter.equals(stage))
				customizers.add(getState().getProject().getWorkspace().getNonExclusiveCustomizers().get(filter));
		if (customizers.isEmpty())
			return null;
		return customizers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.STRATEGY__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ClassMakerPackage.STRATEGY__WORKERS:
			if (coreType)
				return getWorkers();
			else
				return getWorkers().map();
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
		case ClassMakerPackage.STRATEGY__STATE:
			setState((State) newValue);
			return;
		case ClassMakerPackage.STRATEGY__WORKERS:
			((EStructuralFeature.Setting) getWorkers()).set(newValue);
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
		case ClassMakerPackage.STRATEGY__STATE:
			setState((State) null);
			return;
		case ClassMakerPackage.STRATEGY__WORKERS:
			getWorkers().clear();
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
		case ClassMakerPackage.STRATEGY__STATE:
			return state != null;
		case ClassMakerPackage.STRATEGY__WORKERS:
			return workers != null && !workers.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // StrategyImpl
