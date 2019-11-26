/**
 * Copyright 2017 Kyrill Zotkin
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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.enterprisedomain.classmaker.Blueprint;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ModelUtil;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl#getWorkspace
 * <em>Workspace</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassMakerServiceImpl extends EObjectImpl implements ClassMakerService {
	/**
	 * The cached value of the '{@link #getWorkspace() <em>Workspace</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWorkspace()
	 * @generated
	 * @ordered
	 */
	protected Workspace workspace;

	private WorkspaceResourceAdapter workspaceResourceAdapter = new WorkspaceResourceAdapter();

	private class WorkspaceResourceAdapter extends EContentAdapter {

		@Override
		public void notifyChanged(Notification msg) {
			super.notifyChanged(msg);
			if (msg.getFeatureID(State.class) == ClassMakerPackage.STATE__PHASE
					&& msg.getEventType() == Notification.SET) {
				final URI uri = URI.createFileURI(ResourceUtils.WORKSPACE_RESOURCE_PATH.toString());
				Resource workspaceResource = WorkspaceImpl.RESOURCE_SET_EDEFAULT.getResource(uri, false);
				Map<String, String> options = new HashMap<String, String>();
				options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD);
				options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
				if (workspaceResource != null) {
					if (getWorkspace().eIsSet(ClassMakerPackage.Literals.WORKSPACE__PROJECTS)) {
						workspaceResource.getContents().set(0, EcoreUtil.copy(getWorkspace()));
						try {
							workspaceResource.save(options);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					workspaceResource = WorkspaceImpl.RESOURCE_SET_EDEFAULT.createResource(uri);
					workspaceResource.getContents().add(EcoreUtil.copy(getWorkspace()));
					try {
						workspaceResource.save(options);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ClassMakerServiceImpl() {
		super();
		workspace = ClassMakerFactory.eINSTANCE.createWorkspace();
		workspace.getResourceSet().eAdapters().add(workspaceResourceAdapter);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.CLASS_MAKER_SERVICE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Workspace getWorkspace() {
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetWorkspace(Workspace newWorkspace, NotificationChain msgs) {
		Workspace oldWorkspace = workspace;
		workspace = newWorkspace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE, oldWorkspace, newWorkspace);
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
	public void setWorkspace(Workspace newWorkspace) {
		if (newWorkspace != workspace) {
			NotificationChain msgs = null;
			if (workspace != null)
				msgs = ((InternalEObject) workspace).eInverseRemove(this, ClassMakerPackage.WORKSPACE__SERVICE,
						Workspace.class, msgs);
			if (newWorkspace != null)
				msgs = ((InternalEObject) newWorkspace).eInverseAdd(this, ClassMakerPackage.WORKSPACE__SERVICE,
						Workspace.class, msgs);
			msgs = basicSetWorkspace(newWorkspace, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE,
					newWorkspace, newWorkspace));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage make(EPackage input, IProgressMonitor monitor) throws CoreException {
		Blueprint blueprint = createBlueprint();
		blueprint.setDynamicModel(input);
		return make(blueprint, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage make(Blueprint input, IProgressMonitor monitor) throws CoreException {
		try {
			Contribution contrib = getWorkspace().createContribution(input.getDynamicModel(), monitor);
			contrib.getDependencies().addAll(input.getDependencies());
			final Semaphore wait = new Semaphore(0);
			input.getCompletionListeners().add(new CompletionListenerImpl() {

				@Override
				public void completed(Project result) throws Exception {
					wait.release();
				}

			});
			for (CompletionListener listener : input.getCompletionListeners())
				contrib.addCompletionListener(listener);
			contrib.make(monitor);
			try {
				wait.acquire();
			} catch (InterruptedException e) {
			}
			return contrib.getDomainModel().getGenerated();
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
			throw e;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(EPackage source, EPackage target, IProgressMonitor monitor) throws CoreException {
		return replace(source, target, true, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(Blueprint source, Blueprint target, IProgressMonitor monitor) throws CoreException {
		return replace(source, target, true, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(EPackage source, EPackage target, boolean changeVersion, IProgressMonitor monitor)
			throws CoreException {
		Blueprint sourceBlueprint = createBlueprint();
		sourceBlueprint.setDynamicModel(source);
		Blueprint targetBlueprint = createBlueprint();
		targetBlueprint.setDynamicModel(target);
		return replace(sourceBlueprint, targetBlueprint, changeVersion, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(Blueprint source, Blueprint target, boolean changeVersion, IProgressMonitor monitor)
			throws CoreException {
		try {
			Version version = null;
			Contribution contribution = getWorkspace().getContribution(source.getDynamicModel(), false);
			if (contribution == null) {
				contribution = getWorkspace().getContribution(source.getDynamicModel(), true);
				if (contribution != null) {
					version = contribution.getVersion();
					if (changeVersion)
						version = contribution.newVersion(version, false, false, true);
				} else
					return null;
			} else if (changeVersion)
				version = contribution.nextVersion();
			else
				version = contribution.getVersion();
			return replace(source, target, version, monitor);
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
			throw e;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(EPackage source, EPackage target, Version version, IProgressMonitor monitor)
			throws CoreException {
		Blueprint sourceBlueprint = createBlueprint();
		sourceBlueprint.setDynamicModel(source);
		Blueprint targetBlueprint = createBlueprint();
		targetBlueprint.setDynamicModel(target);
		targetBlueprint.getDependencies().addAll(getWorkspace().getContribution(source).getDependencies());
		return replace(sourceBlueprint, targetBlueprint, version, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(Blueprint source, Blueprint target, Version version, IProgressMonitor monitor)
			throws CoreException {
		Contribution contribution = getWorkspace().getContribution(source.getDynamicModel(), false);
		if (contribution == null) {
			contribution = getWorkspace().getContribution(source.getDynamicModel(), true);
			if (contribution != null) {
				EPackage existingModel = contribution.getDomainModel().getDynamic();
				if (ModelUtil.ePackagesAreEqual(existingModel, source.getDynamicModel(), false)) {
					Revision revision = null;
					if (version.compareTo(contribution.getVersion()) < 0) {
						if (!contribution.getRevisions().containsKey(version))
							return null;
						revision = contribution.getRevisions().get(version);
						contribution.checkout(revision.getVersion());
					} else
						revision = contribution.createRevision(monitor);
					revision.getDomainModel().setDynamic(EcoreUtil.copy(target.getDynamicModel()));
				}
			} else
				return null;
		} else {
			EPackage existingModel = contribution.getDomainModel().getDynamic();
			if (ModelUtil.ePackagesAreEqual(existingModel, source.getDynamicModel(), true)) {
				Revision revision = contribution.getRevision();
				if (version.compareTo(contribution.getVersion()) > 0) {
					revision = contribution.newRevision(version);
					State state = revision.getState();
					state.copyModel(contribution.getState());
					revision.create(monitor);
					String commitId = state.initialize(true);
					contribution.checkout(revision.getVersion(), state.getTimestamp(), commitId);
				} else if (version.compareTo(contribution.getVersion()) < 0) {
					if (!contribution.getRevisions().containsKey(version))
						return null;
					revision = contribution.getRevisions().get(version);
					contribution.checkout(revision.getVersion());
				}
				revision.getDomainModel().setDynamic(EcoreUtil.copy(target.getDynamicModel()));
			}
		}
		contribution.getDependencies().clear();
		contribution.getDependencies().addAll(target.getDependencies());
		final Semaphore wait = new Semaphore(0);
		CompletionListener waitListener = new CompletionListenerImpl() {

			@Override
			public void completed(Project result) throws Exception {
				wait.release();
			}

		};
		target.getCompletionListeners().add(waitListener);
		for (CompletionListener listener : target.getCompletionListeners())
			contribution.addCompletionListener(listener);
		contribution.make(monitor);
		try {
			wait.acquire();
		} catch (InterruptedException e) {
		}
		return contribution.getDomainModel().getGenerated();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> make(final Blueprint input, Executor executor, final IProgressMonitor monitor)
			throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return make(input, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(EPackage source, EPackage target, Executor executor,
			final IProgressMonitor monitor) throws CoreException {
		final Blueprint sourceBlueprint = createBlueprint();
		sourceBlueprint.setDynamicModel(source);
		final Blueprint targetBlueprint = createBlueprint();
		targetBlueprint.setDynamicModel(target);
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(sourceBlueprint, targetBlueprint, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> make(EPackage input, Executor executor, IProgressMonitor monitor)
			throws CoreException {
		Blueprint blueprint = createBlueprint();
		blueprint.setDynamicModel(input);
		return make(blueprint, executor, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(final Blueprint source, final Blueprint target, Executor executor,
			final IProgressMonitor monitor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(source, target, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(EPackage source, EPackage target, final boolean changeVersion,
			Executor executor, final IProgressMonitor monitor) throws CoreException {
		final Blueprint sourceBlueprint = createBlueprint();
		sourceBlueprint.setDynamicModel(source);
		final Blueprint targetBlueprint = createBlueprint();
		targetBlueprint.setDynamicModel(target);
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(sourceBlueprint, targetBlueprint, changeVersion, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(final Blueprint source, final Blueprint target,
			final boolean changeVersion, Executor executor, final IProgressMonitor monitor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(source, target, changeVersion, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(EPackage source, EPackage target, final Version version,
			Executor executor, final IProgressMonitor monitor) throws CoreException {
		final Blueprint sourceBlueprint = createBlueprint();
		sourceBlueprint.setDynamicModel(source);
		final Blueprint targetBlueprint = createBlueprint();
		targetBlueprint.setDynamicModel(target);
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(sourceBlueprint, targetBlueprint, version, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(final Blueprint source, final Blueprint target, final Version version,
			Executor executor, final IProgressMonitor monitor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(source, target, version, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EObject transform(EObject source, URI transformationURI) throws CoreException {
		IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
		return transform(source, transformationURI, monitor);
	}

	private Future<? extends EPackage> asyncExecute(Callable<EPackage> callable, Executor executor)
			throws CoreException {
		FutureTask<? extends EPackage> future = new FutureTask<EPackage>(callable);
		try {
			executor.execute(future);
		} catch (Exception e) {
			throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
		}
		return future;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EObject transform(EObject source, URI transformationURI, IProgressMonitor monitor) throws CoreException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IPath transformationPath = ResourceUtils.getModelTransformationPath(
				root.getProject(getWorkspace().getContribution(source).getProjectName()), transformationURI);
		ResourceUtils.copyFile(transformationURI, transformationPath);
		URI localTransformationURI = URI.createFileURI(
				ResourcesPlugin.getWorkspace().getRoot().getRawLocation().append(transformationPath).toOSString());
		TransformationExecutor executor = new TransformationExecutor(localTransformationURI);

		EList<EObject> inObjects = ECollections.newBasicEList(source);

		ModelExtent input = new BasicModelExtent(inObjects);
		ModelExtent output = new BasicModelExtent();

		// setup the execution environment details ->
		// configuration properties, logger, monitor object etc.
		ExecutionContextImpl context = new ExecutionContextImpl();
		context.setConfigProperty("keepModeling", true);

		// run the transformation assigned to the executor with the given
		// input and output and execution context
		// Remark: variable arguments count is supported
		ExecutionDiagnostic result = executor.execute(context, input, output);

		// check the result for success
		if (result.getSeverity() == Diagnostic.OK) {
			return output.getContents().get(0);
		} else {
			// turn the result diagnostic into status and send it to error log
			IStatus status = BasicDiagnostic.toIStatus(result);
			ClassMakerPlugin.getInstance().getLog().log(status);
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Object invoke(EOperation operation, EObject object, EList<Object> arguments)
			throws InvocationTargetException {
		for (EOperation o : object.eClass().getEAllOperations())
			if (o.getName().equals(operation.getName())) {
				return EcoreUtil.getInvocationDelegateFactory(o).createInvocationDelegate(o)
						.dynamicInvoke((InternalEObject) object, arguments);
			}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(String packageName, IProgressMonitor monitor) throws CoreException {
		Contribution contribution = getWorkspace().getContribution(computeProjectName(packageName));
		if (contribution != null)
			contribution.delete(monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean checkEquals(EPackage model1, EPackage model2) {
		return EcoreUtil.equals(model1, model2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Blueprint createBlueprint() {
		return ClassMakerFactory.eINSTANCE.createBlueprint();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String computeProjectName(String packageName) {
		return CodeGenUtil.safeName(packageName.toLowerCase());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public <T extends EObject> T copy(T original) {
		return EcoreUtil.copy(original);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void initialize() {
		getWorkspace().setService(this);
		ClassMakerService.Stages.contributeStages();
		final URI uri = URI.createFileURI(ResourceUtils.WORKSPACE_RESOURCE_PATH.toString());
		if (uri.isFile() && new File(uri.toFileString()).exists()) {
			try {
				Resource workspaceResource = WorkspaceImpl.RESOURCE_SET_EDEFAULT.getResource(uri, true);
				try {
					workspaceResource.load(Collections.emptyMap());
					setWorkspace(EcoreUtil.copy((Workspace) workspaceResource.getContents().get(0)));
					for (Project project : getWorkspace().getProjects()) {
						if (project.eIsSet(ClassMakerPackage.Literals.PROJECT__STATE))
							project.getState().load(false);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			getWorkspace().initialize();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void dispose() {
		eUnset(ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE:
			if (workspace != null)
				msgs = ((InternalEObject) workspace).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE, null, msgs);
			return basicSetWorkspace((Workspace) otherEnd, msgs);
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
		case ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE:
			return basicSetWorkspace(null, msgs);
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
		case ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE:
			return getWorkspace();
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
		case ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE:
			setWorkspace((Workspace) newValue);
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
		case ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE:
			setWorkspace((Workspace) null);
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
		case ClassMakerPackage.CLASS_MAKER_SERVICE__WORKSPACE:
			return workspace != null;
		}
		return super.eIsSet(featureID);
	}

} // ClassMakerServiceImpl
