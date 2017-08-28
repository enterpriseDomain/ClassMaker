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

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerPlant;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ModelUtil;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Plant</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.ClassMakerPlantImpl#getWorkspace
 * <em>Workspace</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassMakerPlantImpl extends EObjectImpl implements ClassMakerPlant {
	/**
	 * The cached value of the '{@link #getWorkspace() <em>Workspace</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWorkspace()
	 * @generated
	 * @ordered
	 */
	protected Workspace workspace;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ClassMakerPlantImpl() {
		super();
		workspace = ClassMakerFactory.eINSTANCE.createWorkspace();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.CLASS_MAKER_PLANT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Workspace getWorkspace() {
		if (workspace != null && workspace.eIsProxy()) {
			InternalEObject oldWorkspace = (InternalEObject) workspace;
			workspace = (Workspace) eResolveProxy(oldWorkspace);
			if (workspace != oldWorkspace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ClassMakerPackage.CLASS_MAKER_PLANT__WORKSPACE, oldWorkspace, workspace));
			}
		}
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Workspace basicGetWorkspace() {
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setWorkspace(Workspace newWorkspace) {
		Workspace oldWorkspace = workspace;
		workspace = newWorkspace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.CLASS_MAKER_PLANT__WORKSPACE,
					oldWorkspace, workspace));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage make(EPackage dynamicModel) throws CoreException {
		IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
		return make(dynamicModel, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> make(final EPackage dynamicModel, Executor executor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return make(dynamicModel);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage make(EPackage dynamicModel, IProgressMonitor monitor) throws CoreException {
		EList<String> dependencies = ECollections.emptyEList();
		return make(dynamicModel, dependencies, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> make(final EPackage dynamicModel, Executor executor,
			final IProgressMonitor monitor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return make(dynamicModel, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage make(EPackage dynamicModel, EList<String> dependencies) throws CoreException {
		IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
		return make(dynamicModel, dependencies, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> make(final EPackage dynamicModel, final EList<String> dependencies,
			Executor executor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return make(dynamicModel, dependencies);
			}
		}, executor);

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage make(EPackage dynamicModel, EList<String> dependencies, IProgressMonitor monitor)
			throws CoreException {
		try {
			Contribution contrib = getWorkspace().createContribution(dynamicModel, monitor);
			contrib.getDependencies().addAll(dependencies);
			contrib.make(monitor);
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
	public Future<? extends EPackage> make(final EPackage dynamicModel, final EList<String> dependencies,
			Executor executor, final IProgressMonitor monitor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return make(dynamicModel, dependencies, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(EPackage queryModel, EPackage dynamicModel) throws CoreException {
		return replace(queryModel, dynamicModel, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(final EPackage queryModel, final EPackage dynamicModel, Executor executor)
			throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(queryModel, dynamicModel);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(EPackage queryModel, EPackage dynamicModel, IProgressMonitor monitor) throws CoreException {
		return replace(queryModel, dynamicModel, false, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(final EPackage queryModel, final EPackage dynamicModel,
			final IProgressMonitor monitor, Executor executor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(queryModel, dynamicModel, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(EPackage queryModel, EPackage dynamicModel, boolean changeVersion) throws CoreException {
		IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
		return replace(queryModel, dynamicModel, changeVersion, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(final EPackage queryModel, final EPackage dynamicModel,
			final boolean changeVersion, Executor executor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(queryModel, dynamicModel, changeVersion);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(EPackage queryModel, EPackage dynamicModel, boolean changeVersion, IProgressMonitor monitor)
			throws CoreException {
		try {
			Version version = null;
			Contribution contribution = getWorkspace().getContribution(queryModel, false);
			if (contribution == null) {
				contribution = getWorkspace().getContribution(queryModel, true);
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
			return replace(queryModel, dynamicModel, version, monitor);
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
	public Future<? extends EPackage> replace(final EPackage queryModel, final EPackage dynamicModel,
			final boolean changeVersion, final IProgressMonitor monitor, Executor executor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(queryModel, dynamicModel, changeVersion, monitor);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(EPackage queryModel, EPackage dynamicModel, Version version) throws CoreException {
		IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
		return replace(queryModel, dynamicModel, version, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(final EPackage queryModel, final EPackage dynamicModel,
			final Version version, Executor executor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(queryModel, dynamicModel, version);
			}
		}, executor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage replace(EPackage queryModel, EPackage dynamicModel, Version version, IProgressMonitor monitor)
			throws CoreException {
		Contribution contribution = getWorkspace().getContribution(queryModel, false);
		if (contribution == null) {
			contribution = getWorkspace().getContribution(queryModel, true);
			if (contribution != null) {
				EPackage existingModel = contribution.getDomainModel().getDynamic();
				if (ModelUtil.ePackagesAreEqual(existingModel, queryModel, false)) {
					Revision revision = null;
					if (version.compareTo(contribution.getVersion()) < 0) {
						if (!contribution.getRevisions().containsKey(version))
							return null;
						revision = contribution.getRevisions().get(version);
						contribution.checkout(revision.getVersion());
					} else
						revision = contribution.createRevision(monitor);
					revision.getDomainModel().setDynamic(EcoreUtil.copy(dynamicModel));
				}
			} else
				return null;
		} else {
			EPackage existingModel = contribution.getDomainModel().getDynamic();
			if (ModelUtil.ePackagesAreEqual(existingModel, queryModel, true)) {
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
				revision.getDomainModel().setDynamic(EcoreUtil.copy(dynamicModel));
			}
		}
		contribution.make(monitor);
		return contribution.getDomainModel().getGenerated();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Future<? extends EPackage> replace(final EPackage queryModel, final EPackage dynamicModel,
			final Version version, final IProgressMonitor monitor, Executor executor) throws CoreException {
		return asyncExecute(new Callable<EPackage>() {

			@Override
			public EPackage call() throws Exception {
				return replace(queryModel, dynamicModel, version, monitor);
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

	private Future<? extends EPackage> asyncExecute(Callable<EPackage> callable, Executor executor) {
		FutureTask<? extends EPackage> future = new FutureTask<EPackage>(callable);
		executor.execute(future);
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
	public String computeProjectName(String packageName) {
		return CodeGenUtil.safeName(packageName.toLowerCase());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.CLASS_MAKER_PLANT__WORKSPACE:
			if (resolve)
				return getWorkspace();
			return basicGetWorkspace();
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
		case ClassMakerPackage.CLASS_MAKER_PLANT__WORKSPACE:
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
		case ClassMakerPackage.CLASS_MAKER_PLANT__WORKSPACE:
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
		case ClassMakerPackage.CLASS_MAKER_PLANT__WORKSPACE:
			return workspace != null;
		}
		return super.eIsSet(featureID);
	}

} // ClassMakerPlantImpl
