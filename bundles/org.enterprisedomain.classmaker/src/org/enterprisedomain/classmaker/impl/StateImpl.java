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

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.m2m.internal.qvt.oml.cst.parser.NLS;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.core.WrappingProgressMonitor;
import org.enterprisedomain.classmaker.jobs.EnterpriseDomainJob;
import org.enterprisedomain.classmaker.jobs.Worker;
import org.enterprisedomain.classmaker.jobs.export.AbstractExporter;
import org.enterprisedomain.classmaker.util.ListUtil;
import org.enterprisedomain.classmaker.util.ModelUtil;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getGenerators
 * <em>Generators</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getExporters
 * <em>Exporters</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getInstallers
 * <em>Installers</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getLoaders
 * <em>Loaders</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getPackageClassName
 * <em>Package Class Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getEditPluginClassName
 * <em>Edit Plugin Class Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getEditorPluginClassName
 * <em>Editor Plugin Class Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getRequiredPlugins
 * <em>Required Plugins</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getRevision
 * <em>Revision</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getTimestamp
 * <em>Timestamp</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getDeployableUnitName
 * <em>Deployable Unit Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getEditDeployableUnitName
 * <em>Edit Deployable Unit Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getEditorDeployableUnitName
 * <em>Editor Deployable Unit Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getJobFamily
 * <em>Job Family</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getResource
 * <em>Resource</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getCommitIds
 * <em>Commit Ids</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getCommitId
 * <em>Commit Id</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getStateCustomizers
 * <em>State Customizers</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#getProjectName
 * <em>Project Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#isMaking
 * <em>Making</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#isEdit
 * <em>Edit</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.StateImpl#isEditor
 * <em>Editor</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateImpl extends ItemImpl implements State {

	/**
	 * The cached value of the '{@link #getGenerators() <em>Generators</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList<Worker> generators;

	/**
	 * The cached value of the '{@link #getExporters() <em>Exporters</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getExporters()
	 * @generated
	 * @ordered
	 */
	protected EList<Worker> exporters;

	/**
	 * The cached value of the '{@link #getInstallers() <em>Installers</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInstallers()
	 * @generated
	 * @ordered
	 */
	protected EList<Worker> installers;

	/**
	 * The cached value of the '{@link #getLoaders() <em>Loaders</em>}' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLoaders()
	 * @generated
	 * @ordered
	 */
	protected EList<Worker> loaders;

	/**
	 * The default value of the '{@link #getPackageClassName() <em>Package Class
	 * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPackageClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageClassName() <em>Package Class
	 * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPackageClassName()
	 * @generated
	 * @ordered
	 */
	protected String packageClassName = PACKAGE_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditPluginClassName() <em>Edit Plugin
	 * Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEditPluginClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDIT_PLUGIN_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditPluginClassName() <em>Edit Plugin
	 * Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEditPluginClassName()
	 * @generated
	 * @ordered
	 */
	protected String editPluginClassName = EDIT_PLUGIN_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditorPluginClassName() <em>Editor
	 * Plugin Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getEditorPluginClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDITOR_PLUGIN_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditorPluginClassName() <em>Editor Plugin
	 * Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEditorPluginClassName()
	 * @generated
	 * @ordered
	 */
	protected String editorPluginClassName = EDITOR_PLUGIN_CLASS_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequiredPlugins() <em>Required
	 * Plugins</em>}' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRequiredPlugins()
	 * @generated
	 * @ordered
	 */
	protected EList<String> requiredPlugins;

	public class StateAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == ClassMakerPackage.STATE__MODEL_NAME
					&& msg.getEventType() == Notification.SET && msg.getNewStringValue() != null
					&& eIsSet(ClassMakerPackage.STATE__PROJECT)) {
				setProjectName(getProject().getWorkspace().getService().computeProjectName(msg.getNewStringValue()));
			} else if (msg.getFeatureID(State.class) == ClassMakerPackage.STATE__EDIT
					&& msg.getEventType() == Notification.SET && msg.getNewBooleanValue()) {
				getRequiredPlugins().add("org.eclipse.emf.edit");
			}
			if (msg.getFeatureID(State.class) == ClassMakerPackage.STATE__EDITOR
					&& msg.getEventType() == Notification.SET && msg.getNewBooleanValue()) {
				getRequiredPlugins().add("org.eclipse.emf.edit.ui");
			}
		}

	}

	public class MakingCompletionListener extends CompletionListenerImpl {

		@Override
		public void completed(Project result) throws Exception {
			if (((Contribution) result).getState().equals(StateImpl.this)) {
				setMaking(false);
				synchronized (makingLock) {
					makingLock.notifyAll();
				}
			}
		}

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
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated NOT
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = Version.emptyVersion;

	/**
	 * The default value of the '{@link #getDeployableUnitName() <em>Deployable Unit
	 * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDeployableUnitName()
	 * @generated
	 * @ordered
	 */
	protected static final String DEPLOYABLE_UNIT_NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getEditDeployableUnitName() <em>Edit
	 * Deployable Unit Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEditDeployableUnitName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDIT_DEPLOYABLE_UNIT_NAME_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getEditorDeployableUnitName() <em>Editor
	 * Deployable Unit Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEditorDeployableUnitName()
	 * @generated
	 * @ordered
	 */
	protected static final String EDITOR_DEPLOYABLE_UNIT_NAME_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getJobFamily() <em>Job Family</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getJobFamily()
	 * @generated
	 * @ordered
	 */
	protected static final String JOB_FAMILY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJobFamily() <em>Job Family</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getJobFamily()
	 * @generated
	 * @ordered
	 */
	protected String jobFamily = JOB_FAMILY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected Resource resource;

	/**
	 * The cached value of the '{@link #getCommitIds() <em>Commit Ids</em>}'
	 * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCommitIds()
	 * @generated
	 * @ordered
	 */
	protected EList<String> commitIds;

	/**
	 * The default value of the '{@link #getCommitId() <em>Commit Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCommitId()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMIT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCommitId() <em>Commit Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCommitId()
	 * @generated
	 * @ordered
	 */
	protected String commitId = COMMIT_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStateCustomizers() <em>State
	 * Customizers</em>}' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStateCustomizers()
	 * @generated
	 * @ordered
	 */
	protected EMap<StageQualifier, Customizer> stateCustomizers;

	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isMaking() <em>Making</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMaking()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MAKING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMaking() <em>Making</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMaking()
	 * @generated
	 * @ordered
	 */
	protected boolean making = MAKING_EDEFAULT;

	/**
	 * The default value of the '{@link #isEdit() <em>Edit</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isEdit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EDIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEdit() <em>Edit</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isEdit()
	 * @generated
	 * @ordered
	 */
	protected boolean edit = EDIT_EDEFAULT;

	/**
	 * The default value of the '{@link #isEditor() <em>Editor</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isEditor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EDITOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEditor() <em>Editor</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isEditor()
	 * @generated
	 * @ordered
	 */
	protected boolean editor = EDITOR_EDEFAULT;

	protected String language = LANGUAGE_EDEFAULT;

	private boolean loading = false;

	private Object makingLock = new Object();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected StateImpl() {
		super();
		eAdapters().add(new StateAdapter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Worker> getGenerators() {
		if (generators == null) {
			generators = new EObjectEList<Worker>(Worker.class, this, ClassMakerPackage.STATE__GENERATORS);
		}
		return generators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Worker> getExporters() {
		if (exporters == null) {
			exporters = new EObjectEList<Worker>(Worker.class, this, ClassMakerPackage.STATE__EXPORTERS);
		}
		return exporters;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Worker> getInstallers() {
		if (installers == null) {
			installers = new EObjectEList<Worker>(Worker.class, this, ClassMakerPackage.STATE__INSTALLERS);
		}
		return installers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Worker> getLoaders() {
		if (loaders == null) {
			loaders = new EObjectEList<Worker>(Worker.class, this, ClassMakerPackage.STATE__LOADERS);
		}
		return loaders;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getPackageClassName() {
		return packageClassName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPackageClassName(String newPackageClassName) {
		String oldPackageClassName = packageClassName;
		packageClassName = newPackageClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__PACKAGE_CLASS_NAME,
					oldPackageClassName, packageClassName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getEditPluginClassName() {
		return editPluginClassName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEditPluginClassName(String newEditPluginClassName) {
		String oldEditPluginClassName = editPluginClassName;
		editPluginClassName = newEditPluginClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__EDIT_PLUGIN_CLASS_NAME,
					oldEditPluginClassName, editPluginClassName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getEditorPluginClassName() {
		return editorPluginClassName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEditorPluginClassName(String newEditorPluginClassName) {
		String oldEditorPluginClassName = editorPluginClassName;
		editorPluginClassName = newEditorPluginClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__EDITOR_PLUGIN_CLASS_NAME,
					oldEditorPluginClassName, editorPluginClassName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EList<String> getRequiredPlugins() {
		if (requiredPlugins == null) {
			requiredPlugins = new EDataTypeUniqueEList<String>(String.class, this,
					ClassMakerPackage.STATE__REQUIRED_PLUGINS);
			requiredPlugins.add("org.eclipse.emf.common");
			requiredPlugins.add("org.eclipse.emf.ecore");
		}
		return requiredPlugins;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__TIMESTAMP, oldTimestamp,
					timestamp));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getDeployableUnitName() {
		String separator = "_"; //$NON-NLS-1$
		return getProjectName() + separator + getRevision().getVersion().toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setDeployableUnitName(String newDeployableUnitName) {
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getEditDeployableUnitName() {
		String separator = "_"; //$NON-NLS-1$
		return getProjectName() + ".edit" + separator + getRevision().getVersion().toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setEditDeployableUnitName(String newDeployableUnitName) {
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getEditorDeployableUnitName() {
		String separator = "_"; //$NON-NLS-1$
		return getProjectName() + ".editor" + separator + getRevision().getVersion().toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setEditorDeployableUnitName(String newDeployableUnitName) {
	}

	@Override
	public Item basicGetParent() {
		return getRevision();
	}

	@Override
	public void setParent(Item newParent) {
		if (newParent instanceof Revision)
			((Revision) newParent).getStateHistory().put(getTimestamp(), this);
		super.setParent(newParent);
	}

	@Override
	public String initialize(boolean commit) {
		if (!eIsSet(ClassMakerPackage.STATE__MODEL_NAME))
			super.initialize(commit);
		if (eIsSet(ClassMakerPackage.STATE__PROJECT)
				&& getProject().eIsSet(ClassMakerPackage.Literals.PROJECT__PROJECT_NAME)
				&& ResourceUtils.isProjectExists(getProjectName())) {
			URI modelURI = getModelURI();
			loadResource(modelURI, !eIsSet(ClassMakerPackage.STATE__RESOURCE), true);
			saveResource();
			if (!getPhase().equals(Stage.LOADED))
				setPhase(Stage.MODELED);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			if (commit)
				try {
					String[] segments = modelURI.deresolve(URI.createFileURI(root.getRawLocation().toString()))
							.segments();
					String[] path = new String[segments.length - 2];
					System.arraycopy(segments, 2, path, 0, segments.length - 2);
					add(URI.createHierarchicalURI(path, null, null).toString());
					String result = commit();
					return result;
				} catch (Exception e) {
					ClassMakerPlugin.getInstance().getLog()
							.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
					return null;
				}
			else {
				@SuppressWarnings("unchecked")
				SCMOperator<Git> operator = (SCMOperator<Git>) getProject().getWorkspace().getSCMRegistry()
						.get(getProjectName());
				try {
					Git git = operator.getRepositorySCM();
					Ref branch = git.getRepository().findRef(getRevision().getVersion().toString());
					LogCommand log = git.log();
					log.add(branch.getObjectId());
					Iterable<RevCommit> commits = log.call();
					for (RevCommit c : commits) {
						if (operator.decodeTimestamp(c.getShortMessage()) == getTimestamp()) {
							String id = c.getId().toString();
							getCommitIds().add(id);
							setCommitId(id);
						}
					}
				} catch (Exception e) {
				} finally {
					try {
						operator.ungetRepositorySCM();
					} catch (Exception e) {
						ClassMakerPlugin.getInstance().getLog()
								.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
					}
				}
				getProject().checkout(getRevision().getVersion(), getTimestamp(), getCommitId());
			}
		}
		return getCommitId(); // $NON-NLS-1$
	}

	private URI modelURI;

	private URI getModelURI() {
		if (modelURI == null) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject project = root.getProject(
					eIsSet(ClassMakerPackage.STATE__PROJECT_NAME) ? getProjectName() : getModelName().toLowerCase());
			IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
			IFolder folder = project.getFolder(ResourceUtils.getModelFolderName());

			if (!folder.exists()) {
				SubMonitor pm = null;
				SubMonitor m = null;
				try {
					pm = SubMonitor.convert(monitor);
					pm.setTaskName("Create Folder");
					pm.subTask("Creating folder...");
					m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
					folder.create(true, true, m);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				} finally {
					if (m != null)
						m.done();
					if (pm != null)
						pm.done();
					monitor.done();
				}
			}
			modelURI = URI.createFileURI(root.getRawLocation().append(getProject().getResourcePath()).toString());
		}
		return modelURI;
	}

	@Override
	public String getModelName() {
		if (super.getModelName() == null && eIsSet(ClassMakerPackage.STATE__PROJECT_NAME))
			setModelName(getProjectName());
		else if (!eIsSet(ClassMakerPackage.STATE__PROJECT_NAME) && eIsSet(ClassMakerPackage.STATE__REVISION))
			setModelName(getRevision().getModelName());
		return super.getModelName();
	}

	public void loadResource(URI modelURI, boolean create, boolean loadOnDemand) {
		if (loading)
			return;
		loading = true;
		boolean created = false;
		ResourceSet resourceSet = getProject().getWorkspace().getResourceSet();
		File modelFile = new File(modelURI.toFileString());
		if (modelFile.exists()) {
			try {
				setResource(resourceSet.getResource(modelURI, loadOnDemand));
			} catch (WrappedException e) {
				if (e.exception() instanceof PackageNotFoundException) {
					Contribution contribution = getProject().getWorkspace()
							.getContribution(((PackageNotFoundException) e.exception()).uri(), Stage.MODELED);
					try {
						contribution.build(ClassMakerPlugin.getProgressMonitor());
					} catch (CoreException e1) {
						ClassMakerPlugin.getInstance().getLog().log(e1.getStatus());
					}
					EPackage ePackage = contribution.getDomainModel().getGenerated();
					resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
					setResource(resourceSet.getResource(modelURI, loadOnDemand));
				}
			}
		} else if (create && !eIsSet(ClassMakerPackage.STATE__RESOURCE)) {
			setResource(resourceSet.createResource(modelURI));
			created = true;
		}
		getResource().eAdapters().remove(resourceToModelsAdapter);
		getResource().eAdapters().add(resourceToModelsAdapter);
		getDomainModel().eAdapters().remove(modelsToResourceAdapter);
		getDomainModel().eAdapters().add(modelsToResourceAdapter);
		if (created) {
			loading = false;
			return;
		}
		try {
			getResource().load(Collections.emptyMap());
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
		}
		if (!getResource().getContents().isEmpty()) {
			getDomainModel().setDynamic(EcoreUtil.copy((EPackage) getResource().getContents().get(0)));
		}
		loading = false;
	}

	public void saveResource() {
		if (getProject().isSavingResource() || isMaking())
			return;
		getProject().setSavingResource(true);
		setMaking(true);
		if (!eIsSet(ClassMakerPackage.STATE__RESOURCE))
			return;
		if (getPhase().getValue() >= Stage.MODELED_VALUE && getDomainModel().getDynamic() != null
				&& getDomainModel().getDynamic().eResource() != null) {
			Resource importSource = getDomainModel().getDynamic().eResource();
			try {
				importSource.load(Collections.emptyMap());
				setPhase(Stage.MODELED);
			} catch (IOException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
			}
			boolean deliver = resource.eDeliver();
			resource.eSetDeliver(false);
			resource.getContents().clear();
			resource.getContents().addAll(EcoreUtil.copyAll(importSource.getContents()));
			importSource.unload();
			resource.eSetDeliver(deliver);
			ClassMakerPlugin.getInstance().getLog()
					.log(ClassMakerPlugin.createInfoStatus(NLS.bind(Messages.ResourceImported, importSource.getURI())));
		} else if (getPhase().getValue() >= Stage.MODELED_VALUE && getDomainModel().getDynamic() != null
				&& packagesDiffer(getDomainModel().getDynamic(), resource.getContents())) {
			boolean deliver = resource.eDeliver();
			resource.eSetDeliver(false);
			resource.getContents().clear();
			resource.getContents().add(EcoreUtil.copy(getDomainModel().getDynamic()));
			resource.eSetDeliver(deliver);
			setPhase(Stage.MODELED);
		}
		try {
			resource.save(Collections.emptyMap());
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
		} finally {
			if (isMaking())
				setMaking(false);
			getProject().setSavingResource(false);
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createGenerator() {
		getGenerators().add(
				create(ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.generator"), //$NON-NLS-1$
						getGenerators().size(), getEclipseProject(), getTimestamp()));
		return getGenerators().get(getGenerators().size() - 1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createExporter() {
		getExporters().add(
				create(ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.exporter"), //$NON-NLS-1$
						getExporters().size(), Long.valueOf(getTimestamp())));
		return getExporters().get(getExporters().size() - 1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createInstaller() {
		getInstallers().add(
				create(ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.installer"), //$NON-NLS-1$
						getInstallers().size(), Long.valueOf(getTimestamp())));
		return getInstallers().get(getInstallers().size() - 1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Worker createModelLoader() {
		getLoaders().add(
				create(ClassMakerService.Stages.lookup(ClassMakerService.Stages.ID_PREFIX + "project.create.loader"), //$NON-NLS-1$
						getLoaders().size(), Long.valueOf(getTimestamp())));
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
			switch (getPhase().getValue()) {
			case Stage.DEFINED_VALUE:
				saveResource();
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
			switch (getPhase().getValue()) {
			case Stage.DEFINED_VALUE:
				saveResource();
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
			switch (getPhase().getValue()) {
			case Stage.DEFINED_VALUE:
				saveResource();
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
		exporterJob.setContributionState(this);
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
		generatorJob.setResourceSet(getProject().getWorkspace().getResourceSet());
		generatorJob.setContributionState(this);
		generatorJob.setProject(getEclipseProject());
		generatorJob.setProgressGroup(monitor, 1);
		generatorJob.setNextJob(exporterJob);

		Worker installer = null;
		if (create)
			installer = createInstaller();
		else
			installer = getInstallers().get(getInstallers().size() - 1);
		EnterpriseDomainJob installJob = EnterpriseDomainJob.getJob(installer);
		installJob.setContributionState(this);
		exporterJob.setNextJob(installJob);

		Worker modelLoader = null;
		if (create)
			modelLoader = createModelLoader();
		else
			modelLoader = getLoaders().get(getLoaders().size() - 1);
		EnterpriseDomainJob loadJob = EnterpriseDomainJob.getJob(modelLoader);
		loadJob.setContributionState(this);
		loadJob.addListener();

		installJob.setNextJob(loadJob);
		return getGenerators().size() - 1;
	}

	private Worker create(StageQualifier stage, Object... customizerArgs) {
		SortedSet<Customizer> customizer = new TreeSet<Customizer>(new Customizer.CustomizerComparator());
		for (StageQualifier filter : getStateCustomizers().keySet())
			if (stage.equals(filter))
				customizer.add(getStateCustomizers().get(filter));
		for (StageQualifier filter : getProject().getWorkspace().getCustomizers().keySet())
			if (filter.equals(stage))
				customizer.add(getProject().getWorkspace().getCustomizers().get(filter));
		if (customizer.isEmpty())
			return null;
		return (Worker) (customizer.last().customize(ECollections.asEList(customizerArgs)));
	}

	private boolean packagesDiffer(EPackage source, EList<EObject> target) {
		if (source == null && target == null)
			return false;
		EList<EPackage> existingEPackages = ECollections.newBasicEList();
		boolean whether = false;
		if (source == null)
			whether = true;
		if (target == null)
			whether = true;
		for (EObject eObject : target)
			if (eObject instanceof EPackage)
				existingEPackages.add((EPackage) eObject);
			else
				whether = true;
		return whether || !ModelUtil.ePackagesAreEqual(source, existingEPackages, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String make(IProgressMonitor monitor) throws Exception {
		if (isMaking())
			return "";
		synchronized (makingLock) {
			IProgressMonitor wrappingMonitor = new WrappingProgressMonitor(monitor);
			CompletionListener completionListener = new MakingCompletionListener();
			getProject().addCompletionListener(completionListener);
			Stage oldStage = getPhase();
			SubMonitor pm = null;
			SubMonitor m = null;
			try {
				if (isMaking())
					if (!getCommitIds().isEmpty())
						return ListUtil.getLast(getCommitIds());
					else
						return ""; //$NON-NLS-1$
				saveResource();
				try {
					getProject().getWorkspace().provision(wrappingMonitor);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				String projectName = null;
				if (eIsSet(ClassMakerPackage.STATE__PROJECT_NAME))
					projectName = getProjectName();
				else
					projectName = getModelName().toLowerCase();
				IProject project = workspace.getRoot().getProject(projectName);
				pm = SubMonitor.convert(wrappingMonitor, 3);
				pm.setTaskName("Open Project");
				pm.subTask("Opening project...");
				m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
				if (project.exists()) {
					if (!project.isOpen())
						project.open(m);
				} else {
					ResourceUtils.createProject(project, ClassMakerPlugin.NATURE_ID, wrappingMonitor);
				}

				configureJobs(getGenerators().isEmpty(), wrappingMonitor);

				monitor.beginTask(Messages.Save, 4);
				if (getPhase().getValue() == Stage.LOADED_VALUE)
					setPhase(Stage.MODELED);

				EnterpriseDomainJob job = null;
				switch (getPhase().getValue()) {
				case Stage.MODELED_VALUE:
					job = EnterpriseDomainJob.getJob(getGenerators().get(getGenerators().size() - 1));
					job.schedule();
					break;
				case Stage.GENERATED_VALUE:
					job = EnterpriseDomainJob.getJob(getExporters().get(getExporters().size() - 1));
					job.schedule();
					break;
				case Stage.EXPORTED_VALUE:
					job = EnterpriseDomainJob.getJob(getInstallers().get(getInstallers().size() - 1));
					job.schedule();
					break;
				case Stage.INSTALLED_VALUE:
					job = EnterpriseDomainJob.getJob(getLoaders().get(getLoaders().size() - 1));
					job.schedule();
					break;
				}
				try {
					job.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
					monitor.setCanceled(true);
				}
			} catch (CoreException e) {
				if (e.getStatus().getSeverity() == IStatus.ERROR) {
					setPhase(oldStage);
				}
				ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				wrappingMonitor.setCanceled(true);
				throw e;
			} catch (OperationCanceledException e) {
				wrappingMonitor.setCanceled(true);
				setPhase(oldStage);
				throw e;
			} catch (Exception e) {
				setPhase(oldStage);
				throw e;
			} finally {
				setMaking(false);
				if (m != null)
					m.done();
				if (pm != null)
					pm.done();
				wrappingMonitor.done();
			}
			if (!monitor.isCanceled() || (!getPhase().equals(Stage.LOADED)
					&& !getDomainModel().eIsSet(ClassMakerPackage.Literals.MODEL_PAIR__GENERATED))) {
				makingLock.wait(7000);
				Thread.yield();
			}
			getProject().removeCompletionListener(completionListener);
			add("."); //$NON-NLS-1$
			String result = commit(); // $NON-NLS-1$
			monitor.done();
			return result;
		}
	}

	private IProject getEclipseProject() {
		return ResourceUtils.getProject(getProjectName());
	}

	/**
	 * Initialize and load resource. Parent revision should be set.
	 */
	@Override
	public void load(boolean create) throws CoreException {
		loadResource(getModelURI(), create, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout() {
		if (getCommitIds().isEmpty()) {
			return;
		}
		if (!eIsSet(ClassMakerPackage.STATE__COMMIT_ID))
			setCommitId(ListUtil.getLast(getCommitIds()));
		checkout(getCommitId(), true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(String commitId, boolean forced) {
		try {
			@SuppressWarnings("unchecked")
			SCMOperator<Git> operator = (SCMOperator<Git>) getProject().getWorkspace().getSCMRegistry()
					.get(getProjectName());
			setCommitId(commitId);
			operator.checkout(getProject().getVersion().toString(), getCommitId(), forced);
			copyModel(getParent());
			load(false);
		} catch (CheckoutConflictException e) {
			e.getConflictingPaths().clear();
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		} catch (Exception e) {
			ClassMakerPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void add(String filepattern) throws Exception {
		@SuppressWarnings("unchecked")
		SCMOperator<Git> operator = (SCMOperator<Git>) getProject().getWorkspace().getSCMRegistry()
				.get(getProjectName());
		operator.add(filepattern);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String commit() throws Exception {
		@SuppressWarnings("unchecked")
		SCMOperator<Git> operator = (SCMOperator<Git>) ClassMakerPlugin.getClassMaker().getWorkspace().getSCMRegistry()
				.get(getProjectName());
		String commitId = null;
		commitId = operator.commit(operator.encodeCommitMessage(this));
		getCommitIds().add(commitId);
		setCommitId(commitId);
		return commitId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			return ((InternalEList<?>) getStateCustomizers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	private boolean resourceModelsSynchronizing = false;

	protected Adapter resourceToModelsAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (resourceModelsSynchronizing)
				return;
			resourceModelsSynchronizing = true;
			boolean deliver = eDeliver();
			eSetDeliver(false);
			if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS)
				switch (msg.getEventType()) {
				case Notification.ADD:
					if (msg.getNewValue() != null && msg.getNewValue() instanceof EPackage) {
						if (findExistingEPackage((EPackage) msg.getNewValue()) == null) {
							getDomainModel().setDynamic(copyEPackage((EPackage) msg.getNewValue()));
						}
					}
					break;
				case Notification.SET:
					if (msg.getOldValue() != null && msg.getOldValue() instanceof EPackage)
						getDomainModel().setDynamic(null);
					if (msg.getNewValue() != null && msg.getNewValue() instanceof EPackage)
						if (findExistingEPackage((EPackage) msg.getNewValue()) == null) {
							getDomainModel().setDynamic(copyEPackage((EPackage) msg.getNewValue()));
						}
					break;
				case Notification.REMOVE_MANY:
					if (msg.getOldValue() != null) {
						for (Object object : (Iterable<?>) msg.getOldValue())
							if (object instanceof EPackage) {
								getDomainModel().setDynamic(null);
							}
					}
					break;
				case Notification.REMOVE:
					if (msg.getOldValue() != null && msg.getOldValue() instanceof EPackage) {
						getDomainModel().setDynamic(null);
					}
					break;
				}
			eSetDeliver(deliver);
			resourceModelsSynchronizing = false;
		}

		private EPackage copyEPackage(EPackage ePackage) {
			EPackage copy = EcoreUtil.copy(ePackage);
			return copy;
		}

		private EPackage findExistingEPackage(EPackage query) {
			EPackage ePackage = getDomainModel().getDynamic();
			if (ModelUtil.ePackagesAreEqual(ePackage, query, true))
				return ePackage;
			return null;
		}

	};

	protected Adapter modelsToResourceAdapter = new EContentAdapter() {

		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			if (resourceModelsSynchronizing)
				return;
			if (notification.getNotifier() instanceof ModelPair
					&& notification.getFeatureID(ModelPair.class) == ClassMakerPackage.MODEL_PAIR__DYNAMIC) {
				resourceModelsSynchronizing = true;
				boolean deliver = getResource().eDeliver();
				getResource().eSetDeliver(false);
				if (notification.getEventType() == Notification.SET) {
					if (notification.getOldValue() != null) {
						EPackage dynamicEPackage = (EPackage) notification.getOldValue();
						EList<EObject> toRemove = ECollections.newBasicEList();
						for (EObject eObject : getResource().getContents())
							if (eObject instanceof EPackage
									&& ModelUtil.ePackagesAreEqual((EPackage) eObject, dynamicEPackage, false))
								toRemove.add(eObject);
						getResource().getContents().removeAll(toRemove);
					}
					if (notification.getNewValue() != null && notification.getPosition() >= Notification.NO_INDEX) {
						EPackage dynamicEPackage = (EPackage) notification.getNewValue();
						if (getResource().getContents().isEmpty()) {
							getResource().getContents().add(EcoreUtil.copy(dynamicEPackage));
						} else
							getResource().getContents().set(0, EcoreUtil.copy(dynamicEPackage));
					}
				}
				getResource().eSetDeliver(deliver);
				resourceModelsSynchronizing = false;
			}
		}

	};

	@Override
	public void copyModel(Item from) {
		if (from instanceof Contribution && !((ContributionImpl) from).isStateSet())
			return;
		super.copyModel(from);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Project basicGetProject() {
		if (eIsSet(ClassMakerPackage.STATE__REVISION))
			return getRevision().getProject();
		else
			return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setProject(Project newProject) {
		newProject.getRevision().getStateHistory().put(getTimestamp(), this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
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
		if (eContainer() != null)
			return (Revision) eContainer().eContainer();
		else
			return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setRevision(Revision newRevision) {
		if (newRevision != null)
			newRevision.getStateHistory().put(getTimestamp(), this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getJobFamily() {
		if (!eIsSet(ClassMakerPackage.STATE__JOB_FAMILY))
			setJobFamily(getDeployableUnitName());
		return jobFamily;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setJobFamily(String newJobFamily) {
		String oldJobFamily = jobFamily;
		jobFamily = newJobFamily;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__JOB_FAMILY, oldJobFamily,
					jobFamily));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Resource getResource() {
		return resource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setResource(Resource newResource) {
		Resource oldResource = resource;
		resource = newResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__RESOURCE, oldResource,
					resource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<String> getCommitIds() {
		if (commitIds == null) {
			commitIds = new EDataTypeUniqueEList<String>(String.class, this, ClassMakerPackage.STATE__COMMIT_IDS);
		}
		return commitIds;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCommitId() {
		return commitId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCommitId(String newCommitId) {
		String oldCommitId = commitId;
		commitId = newCommitId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__COMMIT_ID, oldCommitId,
					commitId));
	}

	@Override
	public EMap<StageQualifier, Customizer> getCustomizers() {
		return getStateCustomizers();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<StageQualifier, Customizer> getStateCustomizers() {
		if (stateCustomizers == null) {
			stateCustomizers = new EcoreEMap<StageQualifier, Customizer>(
					ClassMakerPackage.Literals.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY,
					StageQualifierToCustomizerMapEntryImpl.class, this, ClassMakerPackage.STATE__STATE_CUSTOMIZERS);
		}
		return stateCustomizers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getProjectName() {
		if (eIsSet(ClassMakerPackage.STATE__PROJECT))
			return getProject().getProjectName();
		else
			return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setProjectName(String newProjectName) {
		getProject().setProjectName(newProjectName);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isMaking() {
		return making;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMaking(boolean newMaking) {
		boolean oldMaking = making;
		making = newMaking;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__MAKING, oldMaking, making));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isEdit() {
		return edit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEdit(boolean newEdit) {
		boolean oldEdit = edit;
		edit = newEdit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__EDIT, oldEdit, edit));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isEditor() {
		return editor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEditor(boolean newEditor) {
		boolean oldEditor = editor;
		editor = newEditor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.STATE__EDITOR, oldEditor, editor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setProjectVersion(IProgressMonitor monitor) throws CoreException {
		SubMonitor pm = SubMonitor.convert(monitor, 1);
		pm.setTaskName(Messages.TaskNameSetProjectVersion);
		pm.subTask(Messages.SubTaskNameSetProjectVersion);
		SubMonitor m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
		try {
			IBundleProjectService service = ((IBundleProjectService) ClassMakerPlugin
					.getService(IBundleProjectService.class.getName()));
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProject project = workspace.getRoot().getProject(getProjectName());
			IBundleProjectDescription bundleProjectDescription = service.getDescription(project);
			bundleProjectDescription.setBundleVersion(getProject().getVersion());
			bundleProjectDescription.apply(m);
		} finally {
			m.done();
			pm.done();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(IProgressMonitor monitor) throws CoreException {
		@SuppressWarnings("unchecked")
		SCMOperator<Git> operator = (SCMOperator<Git>) ClassMakerPlugin.getClassMaker().getWorkspace().getSCMRegistry()
				.get(getProjectName());
		try {
			operator.deleteProject();
			try {
				operator.checkoutOrphan(getProject().getVersion().toString(), getTimestamp());
			} catch (Exception e) {
				throw new CoreException(
						new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
			}
		} finally {
			monitor.done();
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage find(EPackage ePackage, Stage stage) {
		if (stage.equals(Stage.MODELED)) {
			EPackage dynamicEPackage = getDomainModel().getDynamic();
			if (ModelUtil.ePackagesAreEqual(ePackage, dynamicEPackage, false))
				return dynamicEPackage;
		}
		if (stage.equals(Stage.GENERATED)) {
			EPackage generatedEPackage = getDomainModel().getGenerated();
			if (ModelUtil.ePackagesAreEqual(ePackage, generatedEPackage, false))
				return generatedEPackage;
		}
		return null;
	}

	EList<? extends EPackage> results;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.STATE__GENERATORS:
			return getGenerators();
		case ClassMakerPackage.STATE__EXPORTERS:
			return getExporters();
		case ClassMakerPackage.STATE__INSTALLERS:
			return getInstallers();
		case ClassMakerPackage.STATE__LOADERS:
			return getLoaders();
		case ClassMakerPackage.STATE__PACKAGE_CLASS_NAME:
			return getPackageClassName();
		case ClassMakerPackage.STATE__EDIT_PLUGIN_CLASS_NAME:
			return getEditPluginClassName();
		case ClassMakerPackage.STATE__EDITOR_PLUGIN_CLASS_NAME:
			return getEditorPluginClassName();
		case ClassMakerPackage.STATE__REQUIRED_PLUGINS:
			return getRequiredPlugins();
		case ClassMakerPackage.STATE__REVISION:
			if (resolve)
				return getRevision();
			return basicGetRevision();
		case ClassMakerPackage.STATE__TIMESTAMP:
			return getTimestamp();
		case ClassMakerPackage.STATE__DEPLOYABLE_UNIT_NAME:
			return getDeployableUnitName();
		case ClassMakerPackage.STATE__EDIT_DEPLOYABLE_UNIT_NAME:
			return getEditDeployableUnitName();
		case ClassMakerPackage.STATE__EDITOR_DEPLOYABLE_UNIT_NAME:
			return getEditorDeployableUnitName();
		case ClassMakerPackage.STATE__JOB_FAMILY:
			return getJobFamily();
		case ClassMakerPackage.STATE__RESOURCE:
			return getResource();
		case ClassMakerPackage.STATE__COMMIT_IDS:
			return getCommitIds();
		case ClassMakerPackage.STATE__COMMIT_ID:
			return getCommitId();
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			if (coreType)
				return getStateCustomizers();
			else
				return getStateCustomizers().map();
		case ClassMakerPackage.STATE__PROJECT_NAME:
			return getProjectName();
		case ClassMakerPackage.STATE__MAKING:
			return isMaking();
		case ClassMakerPackage.STATE__EDIT:
			return isEdit();
		case ClassMakerPackage.STATE__EDITOR:
			return isEditor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassMakerPackage.STATE__GENERATORS:
			getGenerators().clear();
			getGenerators().addAll((Collection<? extends Worker>) newValue);
			return;
		case ClassMakerPackage.STATE__EXPORTERS:
			getExporters().clear();
			getExporters().addAll((Collection<? extends Worker>) newValue);
			return;
		case ClassMakerPackage.STATE__INSTALLERS:
			getInstallers().clear();
			getInstallers().addAll((Collection<? extends Worker>) newValue);
			return;
		case ClassMakerPackage.STATE__LOADERS:
			getLoaders().clear();
			getLoaders().addAll((Collection<? extends Worker>) newValue);
			return;
		case ClassMakerPackage.STATE__PACKAGE_CLASS_NAME:
			setPackageClassName((String) newValue);
			return;
		case ClassMakerPackage.STATE__EDIT_PLUGIN_CLASS_NAME:
			setEditPluginClassName((String) newValue);
			return;
		case ClassMakerPackage.STATE__EDITOR_PLUGIN_CLASS_NAME:
			setEditorPluginClassName((String) newValue);
			return;
		case ClassMakerPackage.STATE__REQUIRED_PLUGINS:
			getRequiredPlugins().clear();
			getRequiredPlugins().addAll((Collection<? extends String>) newValue);
			return;
		case ClassMakerPackage.STATE__REVISION:
			setRevision((Revision) newValue);
			return;
		case ClassMakerPackage.STATE__TIMESTAMP:
			setTimestamp((Long) newValue);
			return;
		case ClassMakerPackage.STATE__DEPLOYABLE_UNIT_NAME:
			setDeployableUnitName((String) newValue);
			return;
		case ClassMakerPackage.STATE__EDIT_DEPLOYABLE_UNIT_NAME:
			setEditDeployableUnitName((String) newValue);
			return;
		case ClassMakerPackage.STATE__EDITOR_DEPLOYABLE_UNIT_NAME:
			setEditorDeployableUnitName((String) newValue);
			return;
		case ClassMakerPackage.STATE__JOB_FAMILY:
			setJobFamily((String) newValue);
			return;
		case ClassMakerPackage.STATE__RESOURCE:
			setResource((Resource) newValue);
			return;
		case ClassMakerPackage.STATE__COMMIT_IDS:
			getCommitIds().clear();
			getCommitIds().addAll((Collection<? extends String>) newValue);
			return;
		case ClassMakerPackage.STATE__COMMIT_ID:
			setCommitId((String) newValue);
			return;
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			((EStructuralFeature.Setting) getStateCustomizers()).set(newValue);
			return;
		case ClassMakerPackage.STATE__PROJECT_NAME:
			setProjectName((String) newValue);
			return;
		case ClassMakerPackage.STATE__MAKING:
			setMaking((Boolean) newValue);
			return;
		case ClassMakerPackage.STATE__EDIT:
			setEdit((Boolean) newValue);
			return;
		case ClassMakerPackage.STATE__EDITOR:
			setEditor((Boolean) newValue);
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
		case ClassMakerPackage.STATE__GENERATORS:
			getGenerators().clear();
			return;
		case ClassMakerPackage.STATE__EXPORTERS:
			getExporters().clear();
			return;
		case ClassMakerPackage.STATE__INSTALLERS:
			getInstallers().clear();
			return;
		case ClassMakerPackage.STATE__LOADERS:
			getLoaders().clear();
			return;
		case ClassMakerPackage.STATE__PACKAGE_CLASS_NAME:
			setPackageClassName(PACKAGE_CLASS_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__EDIT_PLUGIN_CLASS_NAME:
			setEditPluginClassName(EDIT_PLUGIN_CLASS_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__EDITOR_PLUGIN_CLASS_NAME:
			setEditorPluginClassName(EDITOR_PLUGIN_CLASS_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__REQUIRED_PLUGINS:
			getRequiredPlugins().clear();
			return;
		case ClassMakerPackage.STATE__REVISION:
			setRevision((Revision) null);
			return;
		case ClassMakerPackage.STATE__TIMESTAMP:
			setTimestamp(TIMESTAMP_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__DEPLOYABLE_UNIT_NAME:
			setDeployableUnitName(DEPLOYABLE_UNIT_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__EDIT_DEPLOYABLE_UNIT_NAME:
			setEditDeployableUnitName(EDIT_DEPLOYABLE_UNIT_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__EDITOR_DEPLOYABLE_UNIT_NAME:
			setEditorDeployableUnitName(EDITOR_DEPLOYABLE_UNIT_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__JOB_FAMILY:
			setJobFamily(JOB_FAMILY_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__RESOURCE:
			setResource((Resource) null);
			return;
		case ClassMakerPackage.STATE__COMMIT_IDS:
			getCommitIds().clear();
			return;
		case ClassMakerPackage.STATE__COMMIT_ID:
			setCommitId(COMMIT_ID_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			getStateCustomizers().clear();
			return;
		case ClassMakerPackage.STATE__PROJECT_NAME:
			setProjectName(PROJECT_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__MAKING:
			setMaking(MAKING_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__EDIT:
			setEdit(EDIT_EDEFAULT);
			return;
		case ClassMakerPackage.STATE__EDITOR:
			setEditor(EDITOR_EDEFAULT);
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
		case ClassMakerPackage.STATE__GENERATORS:
			return generators != null && !generators.isEmpty();
		case ClassMakerPackage.STATE__EXPORTERS:
			return exporters != null && !exporters.isEmpty();
		case ClassMakerPackage.STATE__INSTALLERS:
			return installers != null && !installers.isEmpty();
		case ClassMakerPackage.STATE__LOADERS:
			return loaders != null && !loaders.isEmpty();
		case ClassMakerPackage.STATE__PACKAGE_CLASS_NAME:
			return PACKAGE_CLASS_NAME_EDEFAULT == null ? packageClassName != null
					: !PACKAGE_CLASS_NAME_EDEFAULT.equals(packageClassName);
		case ClassMakerPackage.STATE__EDIT_PLUGIN_CLASS_NAME:
			return EDIT_PLUGIN_CLASS_NAME_EDEFAULT == null ? editPluginClassName != null
					: !EDIT_PLUGIN_CLASS_NAME_EDEFAULT.equals(editPluginClassName);
		case ClassMakerPackage.STATE__EDITOR_PLUGIN_CLASS_NAME:
			return EDITOR_PLUGIN_CLASS_NAME_EDEFAULT == null ? editorPluginClassName != null
					: !EDITOR_PLUGIN_CLASS_NAME_EDEFAULT.equals(editorPluginClassName);
		case ClassMakerPackage.STATE__REQUIRED_PLUGINS:
			return requiredPlugins != null && !requiredPlugins.isEmpty();
		case ClassMakerPackage.STATE__REVISION:
			return basicGetRevision() != null;
		case ClassMakerPackage.STATE__TIMESTAMP:
			return timestamp != TIMESTAMP_EDEFAULT;
		case ClassMakerPackage.STATE__DEPLOYABLE_UNIT_NAME:
			return DEPLOYABLE_UNIT_NAME_EDEFAULT == null ? getDeployableUnitName() != null
					: !DEPLOYABLE_UNIT_NAME_EDEFAULT.equals(getDeployableUnitName());
		case ClassMakerPackage.STATE__EDIT_DEPLOYABLE_UNIT_NAME:
			return EDIT_DEPLOYABLE_UNIT_NAME_EDEFAULT == null ? getEditDeployableUnitName() != null
					: !EDIT_DEPLOYABLE_UNIT_NAME_EDEFAULT.equals(getEditDeployableUnitName());
		case ClassMakerPackage.STATE__EDITOR_DEPLOYABLE_UNIT_NAME:
			return EDITOR_DEPLOYABLE_UNIT_NAME_EDEFAULT == null ? getEditorDeployableUnitName() != null
					: !EDITOR_DEPLOYABLE_UNIT_NAME_EDEFAULT.equals(getEditorDeployableUnitName());
		case ClassMakerPackage.STATE__JOB_FAMILY:
			return JOB_FAMILY_EDEFAULT == null ? jobFamily != null : !JOB_FAMILY_EDEFAULT.equals(jobFamily);
		case ClassMakerPackage.STATE__RESOURCE:
			return resource != null;
		case ClassMakerPackage.STATE__COMMIT_IDS:
			return commitIds != null && !commitIds.isEmpty();
		case ClassMakerPackage.STATE__COMMIT_ID:
			return COMMIT_ID_EDEFAULT == null ? commitId != null : !COMMIT_ID_EDEFAULT.equals(commitId);
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			return stateCustomizers != null && !stateCustomizers.isEmpty();
		case ClassMakerPackage.STATE__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? getProjectName() != null
					: !PROJECT_NAME_EDEFAULT.equals(getProjectName());
		case ClassMakerPackage.STATE__MAKING:
			return making != MAKING_EDEFAULT;
		case ClassMakerPackage.STATE__EDIT:
			return edit != EDIT_EDEFAULT;
		case ClassMakerPackage.STATE__EDITOR:
			return editor != EDITOR_EDEFAULT;
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
		result.append(" (packageClassName: ");
		result.append(packageClassName);
		result.append(", editPluginClassName: ");
		result.append(editPluginClassName);
		result.append(", editorPluginClassName: ");
		result.append(editorPluginClassName);
		result.append(", requiredPlugins: ");
		result.append(requiredPlugins);
		result.append(", timestamp: ");
		result.append(timestamp);
		result.append(", jobFamily: ");
		result.append(jobFamily);
		result.append(", commitIds: ");
		result.append(commitIds);
		result.append(", commitId: ");
		result.append(commitId);
		result.append(", making: ");
		result.append(making);
		result.append(", edit: ");
		result.append(edit);
		result.append(", editor: ");
		result.append(editor);
		result.append(')');
		return result.toString();
	}

	@Override
	public boolean contains(ISchedulingRule otherRule) {
		if (otherRule instanceof State)
			return getRevision().getVersion().equals(((State) otherRule).getRevision().getVersion())
					&& getRevision().equals(((State) otherRule).getRevision())
					&& getTimestamp() == ((State) otherRule).getTimestamp();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		if (otherRule instanceof MultiRule)
			return ((MultiRule) otherRule).contains(root.getProject(getProjectName()))
					|| ((MultiRule) otherRule).contains(root.getProject(getProjectName() + ".edit"))
					|| ((MultiRule) otherRule).contains(root.getProject(getProjectName() + ".editor"));
		return root.getProject(getProjectName()).contains(otherRule)
				|| root.getProject(getProjectName() + ".edit").contains(otherRule)
				|| root.getProject(getProjectName() + ".editor").contains(otherRule) || root.contains(otherRule);
	}

	@Override
	public boolean isConflicting(ISchedulingRule otherRule) {
		if (otherRule instanceof State)
			return getRevision().getVersion().equals(((State) otherRule).getRevision().getVersion())
					&& getRevision().equals(((State) otherRule).getRevision())
					&& getTimestamp() == ((State) otherRule).getTimestamp()
					&& getCommitId() == ((State) otherRule).getCommitId();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		if (otherRule instanceof MultiRule)
			return ((MultiRule) otherRule).isConflicting(root.getProject(getProjectName()))
					|| ((MultiRule) otherRule).isConflicting(root.getProject(getProjectName() + ".edit"))
					|| ((MultiRule) otherRule).isConflicting(root.getProject(getProjectName() + ".editor"));
		return root.getProject(getProjectName()).isConflicting(otherRule)
				|| root.getProject(getProjectName() + ".edit").isConflicting(otherRule)
				|| root.getProject(getProjectName() + ".editor").isConflicting(otherRule)
				|| root.isConflicting(otherRule);
	}

	@Override
	public void build(IProgressMonitor monitor) throws CoreException {
		Worker exporter = createExporter();
		EnterpriseDomainJob exportJob = EnterpriseDomainJob.getJob(exporter);
		exportJob.setContributionState(this);
		exportJob.setProject(getEclipseProject());
		exporter.getProperties().put(AbstractExporter.EXPORT_DESTINATION_PROP,
				ResourceUtils.getExportDestination(getEclipseProject()).toString());

		Worker generator = createGenerator();
		EnterpriseDomainJob generatorJob = EnterpriseDomainJob.getJob(generator);
		generatorJob.setResourceSet(getProject().getWorkspace().getResourceSet());
		generatorJob.setContributionState(this);
		generatorJob.setProject(getEclipseProject());
		generatorJob.setProgressGroup(monitor, 1);
		generatorJob.setNextJob(exportJob);

		EnterpriseDomainJob installJob = EnterpriseDomainJob.getJob(createInstaller());
		installJob.setContributionState(this);

		EnterpriseDomainJob loadJob = EnterpriseDomainJob.getJob(createModelLoader());
		loadJob.setContributionState(this);
		loadJob.addListener();

		exportJob.setNextJob(installJob);
		installJob.setNextJob(loadJob);

		switch (getPhase().getValue()) {
		case Stage.DEFINED_VALUE:
			saveResource();
		case Stage.MODELED_VALUE:
			generatorJob.schedule();
			break;
		case Stage.GENERATED_VALUE:
			exportJob.schedule();
			break;
		case Stage.EXPORTED_VALUE:
			installJob.schedule();
			break;
		case Stage.INSTALLED_VALUE:
			loadJob.schedule();
			break;
		}
		try {
			generatorJob.join();
			exportJob.join();
			installJob.join();
			loadJob.join();
			add("."); //$NON-NLS-1$
			Thread.yield();
		} catch (InterruptedException e) {
			e.printStackTrace();
			monitor.setCanceled(true);
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
		}

	}

} // StateImpl