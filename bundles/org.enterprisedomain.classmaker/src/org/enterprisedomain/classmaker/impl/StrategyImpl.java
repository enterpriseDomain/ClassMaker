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

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
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
 *   <li>{@link org.enterprisedomain.classmaker.impl.StrategyImpl#getGenerators <em>Generators</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.StrategyImpl#getExporters <em>Exporters</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.StrategyImpl#getInstallers <em>Installers</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.StrategyImpl#getLoaders <em>Loaders</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.StrategyImpl#getState <em>State</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StrategyImpl extends EObjectImpl implements Strategy {
	/**
	 * The cached value of the '{@link #getGenerators() <em>Generators</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList<Worker> generators;

	/**
	 * The cached value of the '{@link #getExporters() <em>Exporters</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getExporters()
	 * @generated
	 * @ordered
	 */
	protected EList<Worker> exporters;

	/**
	 * The cached value of the '{@link #getInstallers() <em>Installers</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getInstallers()
	 * @generated
	 * @ordered
	 */
	protected EList<Worker> installers;

	/**
	 * The cached value of the '{@link #getLoaders() <em>Loaders</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getLoaders()
	 * @generated
	 * @ordered
	 */
	protected EList<Worker> loaders;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected StrategyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.STRATEGY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Worker> getGenerators() {
		if (generators == null) {
			generators = new EObjectEList<Worker>(Worker.class, this, ClassMakerPackage.STRATEGY__GENERATORS);
		}
		return generators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Worker> getExporters() {
		if (exporters == null) {
			exporters = new EObjectEList<Worker>(Worker.class, this, ClassMakerPackage.STRATEGY__EXPORTERS);
		}
		return exporters;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Worker> getInstallers() {
		if (installers == null) {
			installers = new EObjectEList<Worker>(Worker.class, this, ClassMakerPackage.STRATEGY__INSTALLERS);
		}
		return installers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Worker> getLoaders() {
		if (loaders == null) {
			loaders = new EObjectEList<Worker>(Worker.class, this, ClassMakerPackage.STRATEGY__LOADERS);
		}
		return loaders;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * @generated
	 */
	public State basicGetState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * @generated NOT
	 */
	public Worker createGenerator() {
		getGenerators().add(createWithCustomizer(
				ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.generator"), //$NON-NLS-1$
				getGenerators().size(), getEclipseProject(), getState().getTimestamp()));
		return getGenerators().get(getGenerators().size() - 1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createExporter() {
		getExporters().add(createWithCustomizer(
				ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.exporter"), //$NON-NLS-1$
				getExporters().size(), Long.valueOf(getState().getTimestamp())));
		return getExporters().get(getExporters().size() - 1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createInstaller() {
		getInstallers().add(createWithCustomizer(
				ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.installer"), //$NON-NLS-1$
				getInstallers().size(), Long.valueOf(getState().getTimestamp())));
		return getInstallers().get(getInstallers().size() - 1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createModelLoader() {
		getLoaders().add(createWithCustomizer(
				ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.loader"), //$NON-NLS-1$
				getLoaders().size(), Long.valueOf(getState().getTimestamp())));
		return getLoaders().get(getLoaders().size() - 1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Worker getReturnWorker() {
		int depth = configureJobs(getGenerators().size() < 5, ClassMakerPlugin.getProgressMonitor());
		EnterpriseDomainJob generatorJob = EnterpriseDomainJob.getJob(getGenerators().get(depth));
		EnterpriseDomainJob exporterJob = EnterpriseDomainJob.getJob(getExporters().get(depth));
		EnterpriseDomainJob installerJob = EnterpriseDomainJob.getJob(getInstallers().get(depth));
		EnterpriseDomainJob loaderJob = EnterpriseDomainJob.getJob(getLoaders().get(depth));
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
		Worker exporter = null;
		if (create)
			exporter = createExporter();
		else
			exporter = getExporters().get(getExporters().size() - 1);

		EnterpriseDomainJob exporterJob = EnterpriseDomainJob.getJob(exporter);
		exporterJob.setContributionState(getState());
		boolean changeRule = exporterJob.isChangeRule();
		if (exporterJob.getState() != Job.NONE)
			exporterJob.setChangeRule(false);
		exporterJob.setProject(getEclipseProject());
		exporterJob.setChangeRule(changeRule);
		exporter.getProperties().put(AbstractExporter.EXPORT_DESTINATION_PROP,
				ResourceUtils.getExportDestination(getEclipseProject()).toString());

		Worker generator = null;
		if (create)
			generator = createGenerator();
		else
			generator = getGenerators().get(getGenerators().size() - 1);
		EnterpriseDomainJob generatorJob = EnterpriseDomainJob.getJob(generator);
		generatorJob.setResourceSet(getState().getProject().getWorkspace().getResourceSet());
		generatorJob.setContributionState(getState());
		generatorJob.setProject(getEclipseProject());
		generatorJob.setProgressGroup(monitor, 1);
		generatorJob.setNextJob(exporterJob);

		Worker installer = null;
		if (create)
			installer = createInstaller();
		else
			installer = getInstallers().get(getInstallers().size() - 1);
		EnterpriseDomainJob installJob = EnterpriseDomainJob.getJob(installer);
		installJob.setContributionState(getState());
		installJob.setProject(getEclipseProject());
		exporterJob.setNextJob(installJob);

		Worker modelLoader = null;
		if (create)
			modelLoader = createModelLoader();
		else
			modelLoader = getLoaders().get(getLoaders().size() - 1);
		EnterpriseDomainJob loadJob = EnterpriseDomainJob.getJob(modelLoader);
		loadJob.setContributionState(getState());
		loadJob.addListener();

		installJob.setNextJob(loadJob);
		return getGenerators().size() - 1;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void configuteBuildJobs(IProgressMonitor monitor) {
		Worker exporter = createExporter();
		EnterpriseDomainJob exportJob = EnterpriseDomainJob.getJob(exporter);
		exportJob.setContributionState(getState());
		exportJob.setProject(getEclipseProject());
		exporter.getProperties().put(AbstractExporter.EXPORT_DESTINATION_PROP,
				ResourceUtils.getExportDestination(getEclipseProject()).toString());

		Worker generator = createGenerator();
		EnterpriseDomainJob generatorJob = EnterpriseDomainJob.getJob(generator);
		generatorJob.setResourceSet(getState().getProject().getWorkspace().getResourceSet());
		generatorJob.setContributionState(getState());
		generatorJob.setProject(getEclipseProject());
		generatorJob.setProgressGroup(monitor, 1);
		generatorJob.setNextJob(exportJob);

		EnterpriseDomainJob installJob = EnterpriseDomainJob.getJob(createInstaller());
		installJob.setContributionState(getState());
		installJob.setProject(getEclipseProject());

		EnterpriseDomainJob loadJob = EnterpriseDomainJob.getJob(createModelLoader());
		loadJob.setContributionState(getState());
		loadJob.addListener();

		exportJob.setNextJob(installJob);
		installJob.setNextJob(loadJob);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.STRATEGY__STATE:
			return basicSetState(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	private Worker createWithCustomizer(StageQualifier stage, Object... customizerArgs) {
		SortedSet<Customizer> customizers = createCustomizers(stage);
		if (customizers == null)
			return null;
		Customizer customizer = customizers.last();
		return (Worker) (customizer.customize(ECollections.asEList(customizerArgs)));
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.STRATEGY__GENERATORS:
			return getGenerators();
		case ClassMakerPackage.STRATEGY__EXPORTERS:
			return getExporters();
		case ClassMakerPackage.STRATEGY__INSTALLERS:
			return getInstallers();
		case ClassMakerPackage.STRATEGY__LOADERS:
			return getLoaders();
		case ClassMakerPackage.STRATEGY__STATE:
			if (resolve)
				return getState();
			return basicGetState();
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
		case ClassMakerPackage.STRATEGY__GENERATORS:
			getGenerators().clear();
			getGenerators().addAll((Collection<? extends Worker>) newValue);
			return;
		case ClassMakerPackage.STRATEGY__EXPORTERS:
			getExporters().clear();
			getExporters().addAll((Collection<? extends Worker>) newValue);
			return;
		case ClassMakerPackage.STRATEGY__INSTALLERS:
			getInstallers().clear();
			getInstallers().addAll((Collection<? extends Worker>) newValue);
			return;
		case ClassMakerPackage.STRATEGY__LOADERS:
			getLoaders().clear();
			getLoaders().addAll((Collection<? extends Worker>) newValue);
			return;
		case ClassMakerPackage.STRATEGY__STATE:
			setState((State) newValue);
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
		case ClassMakerPackage.STRATEGY__GENERATORS:
			getGenerators().clear();
			return;
		case ClassMakerPackage.STRATEGY__EXPORTERS:
			getExporters().clear();
			return;
		case ClassMakerPackage.STRATEGY__INSTALLERS:
			getInstallers().clear();
			return;
		case ClassMakerPackage.STRATEGY__LOADERS:
			getLoaders().clear();
			return;
		case ClassMakerPackage.STRATEGY__STATE:
			setState((State) null);
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
		case ClassMakerPackage.STRATEGY__GENERATORS:
			return generators != null && !generators.isEmpty();
		case ClassMakerPackage.STRATEGY__EXPORTERS:
			return exporters != null && !exporters.isEmpty();
		case ClassMakerPackage.STRATEGY__INSTALLERS:
			return installers != null && !installers.isEmpty();
		case ClassMakerPackage.STRATEGY__LOADERS:
			return loaders != null && !loaders.isEmpty();
		case ClassMakerPackage.STRATEGY__STATE:
			return state != null;
		}
		return super.eIsSet(featureID);
	}

} // StrategyImpl
