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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.NotifyingInternalEListImpl;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.ReflogCommand;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.ReflogEntry;
import org.eclipse.osgi.util.NLS;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.CompletionNotificationAdapter;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.ModelPair;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.ResourceChangeListener;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMOperator;
import org.enterprisedomain.classmaker.SelectRevealHandler;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ListUtil;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Project</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getModelName
 * <em>Model Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getPhase
 * <em>Phase</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getLanguage
 * <em>Language</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getDomainModel
 * <em>Domain Model</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getCustomizers
 * <em>Customizers</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getParent
 * <em>Parent</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getLocale
 * <em>Locale</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getProject
 * <em>Project</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getName
 * <em>Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getProjectName
 * <em>Project Name</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getChildren
 * <em>Children</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#isDirty
 * <em>Dirty</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getWorkspace
 * <em>Workspace</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getResourcePath
 * <em>Resource Path</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#isNeedCompletionNotification
 * <em>Need Completion Notification</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getCompletionNotificationAdapter
 * <em>Completion Notification Adapter</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getResourceReloadListener
 * <em>Resource Reload Listener</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#isSavingResource
 * <em>Saving Resource</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getRevision
 * <em>Revision</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getRevisions
 * <em>Revisions</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getProjectVersion
 * <em>Project Version</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getSelectRevealHandler
 * <em>Select Reveal Handler</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getVersion
 * <em>Version</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getState
 * <em>State</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getModelResourceAdapter
 * <em>Model Resource Adapter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProjectImpl extends EObjectImpl implements Project {

	/**
	 * The default value of the '{@link #getModelName() <em>Model Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getModelName()
	 * @generated
	 * @ordered
	 */
	protected static final String MODEL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModelName() <em>Model Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	 * The default value of the '{@link #getLanguage() <em>Language</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected String language = LANGUAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDomainModel() <em>Domain Model</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDomainModel()
	 * @generated
	 * @ordered
	 */
	protected ModelPair domainModel;

	/**
	 * The default value of the '{@link #getLocale() <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLocale()
	 * @generated
	 * @ordered
	 */
	protected static final Locale LOCALE_EDEFAULT = (Locale) ClassMakerFactory.eINSTANCE
			.createFromString(ClassMakerPackage.eINSTANCE.getLocale(), "");

	/**
	 * The cached value of the '{@link #getLocale() <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLocale()
	 * @generated
	 * @ordered
	 */
	protected Locale locale = LOCALE_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getProjectName() <em>Project Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected String projectName = PROJECT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isDirty() <em>Dirty</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isDirty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DIRTY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDirty() <em>Dirty</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isDirty()
	 * @generated
	 * @ordered
	 */
	protected boolean dirty = DIRTY_EDEFAULT;

	/**
	 * The default value of the '{@link #getResourcePath() <em>Resource Path</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResourcePath()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_PATH_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isNeedCompletionNotification() <em>Need
	 * Completion Notification</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isNeedCompletionNotification()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEED_COMPLETION_NOTIFICATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNeedCompletionNotification() <em>Need
	 * Completion Notification</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isNeedCompletionNotification()
	 * @generated
	 * @ordered
	 */
	protected boolean needCompletionNotification = NEED_COMPLETION_NOTIFICATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCompletionNotificationAdapter()
	 * <em>Completion Notification Adapter</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCompletionNotificationAdapter()
	 * @generated
	 * @ordered
	 */
	protected CompletionNotificationAdapter completionNotificationAdapter;

	/**
	 * The cached value of the '{@link #getResourceReloadListener() <em>Resource
	 * Reload Listener</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getResourceReloadListener()
	 * @generated
	 * @ordered
	 */
	protected ResourceChangeListener resourceReloadListener;

	/**
	 * The default value of the '{@link #isSavingResource() <em>Saving
	 * Resource</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSavingResource()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SAVING_RESOURCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSavingResource() <em>Saving
	 * Resource</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSavingResource()
	 * @generated
	 * @ordered
	 */
	protected boolean savingResource = SAVING_RESOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRevisions() <em>Revisions</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRevisions()
	 * @generated
	 * @ordered
	 */
	protected EMap<Version, Revision> revisions;

	/**
	 * The default value of the '{@link #getProjectVersion() <em>Project
	 * Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version PROJECT_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjectVersion() <em>Project
	 * Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectVersion()
	 * @generated
	 * @ordered
	 */
	protected Version projectVersion = PROJECT_VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSelectRevealHandler() <em>Select Reveal
	 * Handler</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSelectRevealHandler()
	 * @generated
	 * @ordered
	 */
	protected SelectRevealHandler selectRevealHandler;

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
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected Version version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModelResourceAdapter() <em>Model Resource
	 * Adapter</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getModelResourceAdapter()
	 * @generated
	 * @ordered
	 */
	protected ResourceAdapter modelResourceAdapter;

	protected ListenerList<CompletionListener> completionListeners = new ListenerList<CompletionListener>();

	protected ListenerList<ResourceChangeListener> resourceChangeListeners = new ListenerList<ResourceChangeListener>();

	protected LoadingEList children;

	protected ResourceChangeAdapter resourceAdapter = new ResourceChangeAdapter(this);

	public class ProjectNameAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Project.class) == ClassMakerPackage.PROJECT__NAME
					&& msg.getEventType() == Notification.SET && msg.getNewStringValue() != null
					&& eIsSet(ClassMakerPackage.PROJECT__WORKSPACE))
				setProjectName(getWorkspace().getService().computeProjectName(msg.getNewStringValue()));
			if (msg.getFeatureID(Item.class) == ClassMakerPackage.ITEM__MODEL_NAME
					&& msg.getEventType() == Notification.SET && msg.getNewStringValue() != null
					&& eIsSet(ClassMakerPackage.PROJECT__WORKSPACE))
				setProjectName(getWorkspace().getService().computeProjectName(msg.getNewStringValue()));
		}

	}

	protected Adapter stateModelAdapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == ClassMakerPackage.STATE__RESOURCE
					&& msg.getEventType() == Notification.SET) {
				if (isStateSet() && getState().eIsSet(ClassMakerPackage.Literals.STATE__RESOURCE)) {
					ResourceAdapter modelResourceAdapter = null;
					if (eIsSet(ClassMakerPackage.PROJECT__MODEL_RESOURCE_ADAPTER)) {
						modelResourceAdapter = getModelResourceAdapter();
					} else {
						modelResourceAdapter = ClassMakerFactory.eINSTANCE.createResourceAdapter();
					}
					modelResourceAdapter.setResource(getState().getResource());
					modelResourceAdapter.setProject(ProjectImpl.this);
				}
			}
		}
	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ProjectImpl() {
		super();
		setCompletionNotificationAdapter(ClassMakerFactory.eINSTANCE.createCompletionNotificationAdapter());
		eAdapters().add(getCompletionNotificationAdapter());
		eAdapters().add(new ProjectNameAdapter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.PROJECT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getModelName() {
		return modelName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setModelName(String newModelName) {
		String oldModelName = modelName;
		modelName = newModelName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__MODEL_NAME, oldModelName,
					modelName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Stage getPhase() {
		return phase;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPhase(Stage newPhase) {
		Stage oldPhase = phase;
		phase = newPhase == null ? PHASE_EDEFAULT : newPhase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__PHASE, oldPhase, phase));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getLanguage() {
		return language;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setLanguage(String newLanguage) {
		String oldLanguage = language;
		language = newLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__LANGUAGE, oldLanguage,
					language));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ModelPair getDomainModel() {
		return domainModel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDomainModel(ModelPair newDomainModel, NotificationChain msgs) {
		ModelPair oldDomainModel = domainModel;
		domainModel = newDomainModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.PROJECT__DOMAIN_MODEL, oldDomainModel, newDomainModel);
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
	public void setDomainModel(ModelPair newDomainModel) {
		if (newDomainModel != domainModel) {
			NotificationChain msgs = null;
			if (domainModel != null)
				msgs = ((InternalEObject) domainModel).eInverseRemove(this, ClassMakerPackage.MODEL_PAIR__PARENT,
						ModelPair.class, msgs);
			if (newDomainModel != null)
				msgs = ((InternalEObject) newDomainModel).eInverseAdd(this, ClassMakerPackage.MODEL_PAIR__PARENT,
						ModelPair.class, msgs);
			msgs = basicSetDomainModel(newDomainModel, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__DOMAIN_MODEL,
					newDomainModel, newDomainModel));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EMap<StageQualifier, Customizer> getCustomizers() {
		if (isStateSet())
			return getState().getCustomizers();

		return ECollections.emptyEMap();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
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
		return this;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setParent(Item newParent) {
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Locale getLocale() {
		return locale;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setLocale(Locale newLocale) {
		Locale oldLocale = locale;
		locale = newLocale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__LOCALE, oldLocale,
					locale));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Project getProject() {
		Project project = basicGetProject();
		return project != null && project.eIsProxy() ? (Project) eResolveProxy((InternalEObject) project) : project;
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
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getProjectName() {
		return projectName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setProjectName(String newProjectName) {
		String oldProjectName = projectName;
		projectName = CodeGenUtil.safeName(newProjectName);
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__PROJECT_NAME,
					oldProjectName, projectName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public EList<Object> getChildren() {
		if (children == null || children.isEmpty() || children.get(0) == null) {
			if (getModelResourceAdapter() != null)
				children = new LoadingEList(getModelResourceAdapter().getResource());
			else
				children = new LoadingEList(null);
			eAdapters().remove(modelAdapter);
			eAdapters().add(modelAdapter);
		}
		return (EList<Object>) (EList<?>) children;
	}

	private Adapter modelAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			super.notifyChanged(msg);
			if (msg.getFeatureID(Project.class) == ClassMakerPackage.PROJECT__MODEL_RESOURCE_ADAPTER
					&& msg.getEventType() == Notification.SET && msg.getNewValue() != null) {
				children.setContents(getModelResourceAdapter().getResource());
			}
		}

	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isDirty() {
		return dirty;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDirty(boolean newDirty) {
		boolean oldDirty = dirty;
		dirty = newDirty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__DIRTY, oldDirty, dirty));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Workspace getWorkspace() {
		if (eContainerFeatureID() != ClassMakerPackage.PROJECT__WORKSPACE)
			return null;
		return (Workspace) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetWorkspace(Workspace newWorkspace, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newWorkspace, ClassMakerPackage.PROJECT__WORKSPACE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setWorkspace(Workspace newWorkspace) {
		if (newWorkspace != eInternalContainer()
				|| (eContainerFeatureID() != ClassMakerPackage.PROJECT__WORKSPACE && newWorkspace != null)) {
			if (EcoreUtil.isAncestor(this, newWorkspace))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWorkspace != null)
				msgs = ((InternalEObject) newWorkspace).eInverseAdd(this, ClassMakerPackage.WORKSPACE__PROJECTS,
						Workspace.class, msgs);
			msgs = basicSetWorkspace(newWorkspace, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__WORKSPACE, newWorkspace,
					newWorkspace));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getResourcePath() {
		return IPath.SEPARATOR + getProjectName() + IPath.SEPARATOR + ResourceUtils.getFileName(getName());
	}

	protected boolean isRevisionSet() {
		return eIsSet(ClassMakerPackage.PROJECT__REVISION);
	}

	protected boolean isStateSet() {
		return isRevisionSet() && eIsSet(ClassMakerPackage.PROJECT__STATE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isNeedCompletionNotification() {
		return needCompletionNotification;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setNeedCompletionNotification(boolean newNeedCompletionNotification) {
		boolean oldNeedCompletionNotification = needCompletionNotification;
		needCompletionNotification = newNeedCompletionNotification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION, oldNeedCompletionNotification,
					needCompletionNotification));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public CompletionNotificationAdapter getCompletionNotificationAdapter() {
		if (completionNotificationAdapter != null && completionNotificationAdapter.eIsProxy()) {
			InternalEObject oldCompletionNotificationAdapter = (InternalEObject) completionNotificationAdapter;
			completionNotificationAdapter = (CompletionNotificationAdapter) eResolveProxy(
					oldCompletionNotificationAdapter);
			if (completionNotificationAdapter != oldCompletionNotificationAdapter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER,
							oldCompletionNotificationAdapter, completionNotificationAdapter));
			}
		}
		return completionNotificationAdapter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CompletionNotificationAdapter basicGetCompletionNotificationAdapter() {
		return completionNotificationAdapter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetCompletionNotificationAdapter(
			CompletionNotificationAdapter newCompletionNotificationAdapter, NotificationChain msgs) {
		CompletionNotificationAdapter oldCompletionNotificationAdapter = completionNotificationAdapter;
		completionNotificationAdapter = newCompletionNotificationAdapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, oldCompletionNotificationAdapter,
					newCompletionNotificationAdapter);
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
	public void setCompletionNotificationAdapter(CompletionNotificationAdapter newCompletionNotificationAdapter) {
		if (newCompletionNotificationAdapter != completionNotificationAdapter) {
			NotificationChain msgs = null;
			if (completionNotificationAdapter != null)
				msgs = ((InternalEObject) completionNotificationAdapter).eInverseRemove(this,
						ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT, CompletionNotificationAdapter.class,
						msgs);
			if (newCompletionNotificationAdapter != null)
				msgs = ((InternalEObject) newCompletionNotificationAdapter).eInverseAdd(this,
						ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT, CompletionNotificationAdapter.class,
						msgs);
			msgs = basicSetCompletionNotificationAdapter(newCompletionNotificationAdapter, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, newCompletionNotificationAdapter,
					newCompletionNotificationAdapter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ResourceChangeListener getResourceReloadListener() {
		if (resourceReloadListener == null) {
			resourceReloadListener = new ResourceChangeListenerImpl() {

				private boolean loading = false;

				@Override
				public void changed(Notification notification) throws Exception {
					Resource theResource = (Resource) getChildren().get(0);
					Resource resource = (Resource) notification.getNotifier();
					if (!loading && !isSavingResource() && resource.getURI().equals(theResource.getURI())
							&& theResource.isLoaded()) {
						loading = true;
						if (ProjectImpl.this.eIsSet(ClassMakerPackage.PROJECT__SELECT_REVEAL_HANDLER))
							getSelectRevealHandler().prepare();
						if (!theResource.isModified())
							setResource(resource);
						theResource = ((Resource) getChildren().get(0));
						// theResource.setURI(getResourceURI());
						// theResource.unload();
						// theResource.load(Collections.emptyMap());
						if (ProjectImpl.this.eIsSet(ClassMakerPackage.PROJECT__SELECT_REVEAL_HANDLER))
							getSelectRevealHandler().selectReveal(theResource);
						loading = false;
					}
				}

			};
		}
		return resourceReloadListener;
	}

	protected void setResource(Resource resource) {
		getRevision().getState().setResource(resource);
		getChildren().set(0, resource);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSavingResource() {
		return savingResource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSavingResource(boolean newSavingResource) {
		boolean oldSavingResource = savingResource;
		savingResource = newSavingResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__SAVING_RESOURCE,
					oldSavingResource, savingResource));
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
		return getRevisions().get(getProjectVersion());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setRevision(Revision newRevision) {
		if (!getRevisions().containsKey(newRevision.getVersion()))
			getRevisions().put(newRevision.getVersion(), newRevision);
		setProjectVersion(newRevision.getVersion());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<Version, Revision> getRevisions() {
		if (revisions == null) {
			revisions = new EcoreEMap<Version, Revision>(ClassMakerPackage.Literals.VERSION_TO_REVISION_MAP_ENTRY,
					VersionToRevisionMapEntryImpl.class, this, ClassMakerPackage.PROJECT__REVISIONS);
		}
		return revisions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Version getProjectVersion() {
		return projectVersion;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setProjectVersion(Version newProjectVersion) {
		Version oldProjectVersion = projectVersion;
		projectVersion = newProjectVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__PROJECT_VERSION,
					oldProjectVersion, projectVersion));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SelectRevealHandler getSelectRevealHandler() {
		if (selectRevealHandler != null && selectRevealHandler.eIsProxy()) {
			InternalEObject oldSelectRevealHandler = (InternalEObject) selectRevealHandler;
			selectRevealHandler = (SelectRevealHandler) eResolveProxy(oldSelectRevealHandler);
			if (selectRevealHandler != oldSelectRevealHandler) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ClassMakerPackage.PROJECT__SELECT_REVEAL_HANDLER, oldSelectRevealHandler,
							selectRevealHandler));
			}
		}
		return selectRevealHandler;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public SelectRevealHandler basicGetSelectRevealHandler() {
		return selectRevealHandler;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSelectRevealHandler(SelectRevealHandler newSelectRevealHandler) {
		SelectRevealHandler oldSelectRevealHandler = selectRevealHandler;
		selectRevealHandler = newSelectRevealHandler;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__SELECT_REVEAL_HANDLER,
					oldSelectRevealHandler, selectRevealHandler));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__VERSION, oldVersion,
					version));
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
		return getRevision().getState();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceAdapter getModelResourceAdapter() {
		return modelResourceAdapter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetModelResourceAdapter(ResourceAdapter newModelResourceAdapter,
			NotificationChain msgs) {
		ResourceAdapter oldModelResourceAdapter = modelResourceAdapter;
		modelResourceAdapter = newModelResourceAdapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.PROJECT__MODEL_RESOURCE_ADAPTER, oldModelResourceAdapter,
					newModelResourceAdapter);
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
	 * @generated NOT
	 */
	public void create(IProgressMonitor monitor) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		String projectName = getProjectName();
		IProject project = workspace.getRoot().getProject(projectName);
		ResourceUtils.createProject(project, null, monitor);
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
	public void delete(IProgressMonitor monitor) throws CoreException {
		if (eIsSet(ClassMakerPackage.PROJECT__WORKSPACE)) {
			getWorkspace().getResourceSet().eAdapters().remove(resourceAdapter);
			getWorkspace().unregisterProject(this);
		}
		String projectName = getProjectName();
		if (projectName.isEmpty())
			return;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(projectName);
		SubMonitor pm = SubMonitor.convert(monitor);
		SubMonitor m = pm.newChild(1, SubMonitor.SUPPRESS_ISCANCELED);
		try {
			project.delete(true, true, m);
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
	public void delete(EList<Object> objects) {
		TreeIterator<EObject> it = ((Resource) getChildren().get(0)).getAllContents();
		while (it.hasNext()) {
			EObject o = it.next();
			ListIterator<Object> li = objects.listIterator();
			while (li.hasNext()) {
				Object obj = li.next();
				if (obj.equals(o) || (obj instanceof Collection<?> ? ((Collection<?>) obj).contains(o) : false))
					it.remove();
			}
		}
		try {
			((Resource) getChildren().get(0)).save(Collections.emptyMap());
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog()
					.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
		}
	}

	public void notifyCompletion() throws Exception {
		for (Object listener : completionListeners.getListeners())
			((CompletionListener) listener).completed(this);
		setNeedCompletionNotification(false);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void addResourceChangeListener(ResourceChangeListener resourceListener) {
		resourceChangeListeners.add(resourceListener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void removeResourceChangeListener(ResourceChangeListener resourceListener) {
		resourceChangeListeners.remove(resourceListener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Revision newRevision(Version version) {
		if (getRevisions().containsKey(version))
			return getRevisions().get(version);

		Revision newRevision = newBareRevision(version);
		doNewRevision(newRevision);
		newRevision.initialize(false);
		return newRevision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Revision newBareRevision(Version version) {
		Revision newRevision = ClassMakerFactory.eINSTANCE.createRevision();
		newRevision.setVersion(version);
		getRevisions().put(version, newRevision);
		return newRevision;
	}

	/**
	 * <!-- begin-user-doc --> Sub-classes may implement. <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void doNewRevision(Revision newRevision) {
		State newState = newRevision.newState();
		newRevision.setTimestamp(newState.getTimestamp());
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
	public Version nextVersion() throws CoreException {
		if (!eIsSet(ClassMakerPackage.Literals.PROJECT__PROJECT_VERSION))
			return newVersion(true, false, false);
		return newVersion(getProjectVersion(), false, false, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(Version version) {
		Revision revision = null;
		if (getRevisions().containsKey(version)) {
			setProjectVersion(version);
			revision = getRevisions().get(version);
			if (!revision.eIsSet(ClassMakerPackage.Literals.REVISION__TIMESTAMP))
				if (!revision.getStateHistory().isEmpty())
					checkout(version, ListUtil.getLast(revision.getStateHistory()).getKey());
				else {
					return;
				}
			checkout(version, revision.getTimestamp());
		} else if (!getRevisions().isEmpty() && (version == null || version.equals(Version.emptyVersion)))
			checkout(ListUtil.getLast(getRevisions()).getKey());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(Version version, long time) {
		Revision revision = null;
		if (getRevisions().containsKey(version)) {
			setProjectVersion(version);
			if (getProjectName().isEmpty())
				return;
			Git git = null;
			@SuppressWarnings("unchecked")
			SCMOperator<Git> operator = (SCMOperator<Git>) getWorkspace().getSCMRegistry().get(getProjectName());
			Ref ref = null;
			try {
				git = operator.getRepositorySCM();
				ref = git.getRepository().findRef(version.toString());
				if (ref != null)
					git.checkout().setName(ref.getName()).call();
			} catch (CheckoutConflictException e) {
				if (git != null) {
					try {
						ResetCommand reset = git.reset().setMode(ResetType.HARD);
						if (ref != null)
							reset.setRef(ref.getName());
						reset.call();
					} catch (CheckoutConflictException ex) {
						ClassMakerPlugin.getInstance().getLog().log(
								new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, ex.getLocalizedMessage(), ex));
					} catch (GitAPIException ex) {
						ClassMakerPlugin.getInstance().getLog().log(
								new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, ex.getLocalizedMessage(), ex));
					}
				}
			} catch (Exception e) {
				ClassMakerPlugin.getInstance().getLog()
						.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
			} finally {
				try {
					operator.ungetRepositorySCM();
				} catch (Exception e) {
					ClassMakerPlugin.getInstance().getLog()
							.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
				}
			}
			revision = getRevisions().get(version);
			if (revision == null) {
				revision = newBareRevision(version);
				doNewRevision(revision);
				revision.initialize(false);
				revision.checkout(time);
			} else if (revision.getStateHistory().containsKey(time)) {
				State state = revision.getStateHistory().get((Object) time);
				EList<String> commits = state.getCommitIds();
				if (!commits.isEmpty()) {
					revision.checkout(time, state.getCommitId());
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
	public void checkout(Version version, long time, String commitId) {
		if (version.equals(VERSION_EDEFAULT)) {
			setProjectVersion(version);
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
	public void checkout(long time) {
		if (getRevision().getStateHistory().containsKey(time))
			getRevision().checkout(time);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void checkout(long time, String commitId) {
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
			getState().checkout(commitId, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void initAdapters(Revision revision) {
		revision.addAdapters(ECollections.singletonEList(stateModelAdapter));
		if (isStateSet())
			getState().getResource().eAdapters().add(new AdapterImpl() {

				@Override
				public void notifyChanged(Notification msg) {
					if (msg.getFeatureID(Resource.class) == Resource.RESOURCE__IS_MODIFIED
							&& msg.getNewBooleanValue()) {
						setDirty(true);
					}
				}

			});

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void load(boolean create, boolean loadOnDemand) throws CoreException {
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
					throw new CoreException(ClassMakerPlugin.createErrorStatus(e));
				}
			}
			getRevision().load(create, loadOnDemand);
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
	@Override
	public void copyModel(Item from) {
		if (isRevisionSet())
			getRevision().copyModel(from);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void notifyResourceChanged(Notification notification) throws Exception {
		for (Object listener : resourceChangeListeners.getListeners()) {
			((ResourceChangeListener) listener).changed(notification);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String make(IProgressMonitor monitor) throws CoreException {
		removeResourceChangeListener(getResourceReloadListener());
		String result = "You made it!"; //$NON-NLS-1$
		addResourceChangeListener(getResourceReloadListener());
		setNeedCompletionNotification(true);
		setDirty(false);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean open(IProgressMonitor monitor) throws CoreException {
		String projectName = getProjectName();
		if (projectName.isEmpty())
			return false;
		initialize(false);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if (ClassMakerPlugin.getInstance().isTurnOffAutoBuilding() && workspace.isAutoBuilding()) {
			ResourceUtils.setAutoBuilding(workspace, false);
		}
		IProject project = workspace.getRoot().getProject(projectName);
		if (project.isOpen())
			return true;
		project.open(monitor);
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void close(IProgressMonitor monitor) throws CoreException {
		String projectName = getProjectName();
		if (projectName.isEmpty())
			return;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(projectName);
		project.close(monitor);
		if (ClassMakerPlugin.getInstance().isTurnOffAutoBuilding())
			try {
				ResourceUtils.restoreAutoBuilding(workspace);
			} catch (IllegalArgumentException e) {
			}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String initialize(boolean commit) {
		setName(getProjectName());
		URI uri = getResourceURI();
		Resource resource = null;
		if (new File(uri.toFileString()).exists()) {
			try {
				resource = getWorkspace().getResourceSet().getResource(uri, true);
			} catch (WrappedException e) {
				if (e.exception() instanceof PackageNotFoundException) {
					Contribution contribution = getWorkspace()
							.getContribution(((PackageNotFoundException) e.exception()).uri(), Stage.MODELED);
					if (contribution.getPhase().getValue() >= Stage.INSTALLED_VALUE)
						try {
							contribution.build(ClassMakerPlugin.getProgressMonitor());
							resource = getWorkspace().getResourceSet().getResource(uri, true);
						} catch (CoreException e1) {
							e1.printStackTrace();
						}
				}
			}
			if (resource != null) {
				try {
					resource.load(Collections.emptyMap());
				} catch (IOException e) {
					ClassMakerPlugin.getInstance().getLog()
							.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
				}
				@SuppressWarnings("unchecked")
				SCMOperator<Git> operator = (SCMOperator<Git>) getWorkspace().getSCMRegistry().get(getProjectName());
				setName(getProjectName());
				try {
					Git git = operator.getRepositorySCM();
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
					if (eIsSet(ClassMakerPackage.PROJECT__REVISION)
							&& getRevision().eIsSet(ClassMakerPackage.Literals.REVISION__STATE)) {
						getRevision().getState().setResource(resource);
						initAdapters(getRevision());
					}
					onModelResourceCreate(resource);
					getWorkspace().getResourceSet().eAdapters().add(resourceAdapter);
					addResourceChangeListener(getResourceReloadListener());
					return commitId;
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
			} else {
				createResource(uri, commit);
			}
		} else {
			createResource(uri, commit);
		}
		getWorkspace().getResourceSet().eAdapters().add(resourceAdapter);
		addResourceChangeListener(getResourceReloadListener());
		return ""; //$NON-NLS-1$
	}

	private void createResource(URI uri, boolean commit) {
		Resource resource = getWorkspace().getResourceSet().createResource(uri);
		if (eIsSet(ClassMakerPackage.PROJECT__REVISION)
				&& getRevision().eIsSet(ClassMakerPackage.Literals.REVISION__STATE))
			getRevision().getState().setResource(resource);
		if (commit)
			try {
				resource.save(Collections.emptyMap());
			} catch (IOException e) {
				ClassMakerPlugin.getInstance().getLog()
						.log(new Status(IStatus.ERROR, ClassMakerPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
			}
		onModelResourceCreate(resource);
	}

	private URI getResourceURI() {
		IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
		return URI.createFileURI(workspacePath.append(getResourcePath()).toString());
	}

	protected class LoadingEList extends NotifyingInternalEListImpl<Notifier> {

		private static final long serialVersionUID = 164926149524632079L;
		private Notifier object;
		private Adapter initAdapter = new AdapterImpl() {

			@Override
			public void notifyChanged(Notification msg) {
				super.notifyChanged(msg);
				if (msg.getFeatureID(ResourceAdapter.class) == ClassMakerPackage.RESOURCE_ADAPTER__RESOURCE
						&& msg.getEventType() == Notification.SET && msg.getNewValue() != null)
					setContents((Notifier) msg.getNewValue());
			}

		};

		public LoadingEList(Notifier object) {
			setContents(object);
		}

		private class ResourceNotificationImpl extends NotificationImpl {

			public ResourceNotificationImpl(int eventType, Object oldValue, Object newValue) {
				super(eventType, oldValue, newValue);
			}

			public ResourceNotificationImpl(int eventType, Object oldValue, Object newValue, int position) {
				super(eventType, oldValue, newValue, position);
			}

			@Override
			public int getFeatureID(Class<?> expectedClass) {
				if (expectedClass.isAssignableFrom(Resource.class))
					return Resource.RESOURCE__CONTENTS;
				return super.getFeatureID(expectedClass);
			}

		}

		private void fill(Notifier object) {
			if (object instanceof Resource) {
				clear();
				add(object);
			} else if (object instanceof EObject) {
				((Resource) this.object).getContents().clear();
				((Resource) this.object).getContents().add((EObject) object);
			}
		}

		@Override
		protected void didSet(int index, Notifier newObject, Notifier oldObject) {
			detachInitAdapter();
			setObject(newObject);
			super.didSet(index, newObject, oldObject);
			attachInitAdapter();
			if (eNotificationRequired())
				eNotify(new ResourceNotificationImpl(Notification.SET, oldObject, newObject, index));
		}

		@Override
		protected void didAdd(int index, Notifier newObject) {
			super.didAdd(index, newObject);
			attachInitAdapter();
			setObject(newObject);
			if (eNotificationRequired())
				eNotify(new ResourceNotificationImpl(Notification.ADD, null, newObject, index));
		}

		@Override
		protected void didRemove(int index, Notifier oldObject) {
			detachInitAdapter();
			setObject(null);
			super.didRemove(index, oldObject);
			if (eNotificationRequired())
				eNotify(new ResourceNotificationImpl(Notification.REMOVE, oldObject, null, index));
		}

		@Override
		protected void didClear(int size, Object[] oldObjects) {
			detachInitAdapter();
			setObject(null);
			super.didClear(size, oldObjects);
			if (eNotificationRequired())
				eNotify(new ResourceNotificationImpl(Notification.REMOVE_MANY, oldObjects, null));
		}

		private void attachInitAdapter() {
			if (object == null)
				return;
			object.eAdapters().add(initAdapter);
		}

		private void detachInitAdapter() {
			if (object == null)
				return;
			object.eAdapters().remove(initAdapter);
		}

		public void setContents(Notifier object) {
			detachInitAdapter();
			setObject(object);
			attachInitAdapter();
			fill(object);
		}

		private void setObject(Notifier object) {
			if (object instanceof Resource)
				this.object = object;
			else if (object instanceof EObject)
				((Resource) this.object).getContents().add((EObject) object);
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void addCompletionListener(CompletionListener resultListener) {
		for (Object listener : completionListeners.getListeners())
			if (listener.equals(resultListener))
				return;
		completionListeners.add(resultListener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void removeCompletionListener(CompletionListener resultListener) {
		completionListeners.remove(resultListener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void onModelResourceCreate(Resource eResource) {
		for (Adapter adapter : eAdapters())
			if (adapter.isAdapterForType(Resource.class)) {
				adapter.notifyChanged(new NotificationImpl(Notification.SET, null, eResource));
			}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.PROJECT__DOMAIN_MODEL:
			if (domainModel != null)
				msgs = ((InternalEObject) domainModel).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.PROJECT__DOMAIN_MODEL, null, msgs);
			return basicSetDomainModel((ModelPair) otherEnd, msgs);
		case ClassMakerPackage.PROJECT__WORKSPACE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetWorkspace((Workspace) otherEnd, msgs);
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			if (completionNotificationAdapter != null)
				msgs = ((InternalEObject) completionNotificationAdapter).eInverseRemove(this,
						ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT, CompletionNotificationAdapter.class,
						msgs);
			return basicSetCompletionNotificationAdapter((CompletionNotificationAdapter) otherEnd, msgs);
		case ClassMakerPackage.PROJECT__MODEL_RESOURCE_ADAPTER:
			if (modelResourceAdapter != null)
				msgs = ((InternalEObject) modelResourceAdapter).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.PROJECT__MODEL_RESOURCE_ADAPTER, null, msgs);
			return basicSetModelResourceAdapter((ResourceAdapter) otherEnd, msgs);
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
		case ClassMakerPackage.PROJECT__DOMAIN_MODEL:
			return basicSetDomainModel(null, msgs);
		case ClassMakerPackage.PROJECT__CUSTOMIZERS:
			return ((InternalEList<?>) getCustomizers()).basicRemove(otherEnd, msgs);
		case ClassMakerPackage.PROJECT__WORKSPACE:
			return basicSetWorkspace(null, msgs);
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			return basicSetCompletionNotificationAdapter(null, msgs);
		case ClassMakerPackage.PROJECT__REVISIONS:
			return ((InternalEList<?>) getRevisions()).basicRemove(otherEnd, msgs);
		case ClassMakerPackage.PROJECT__MODEL_RESOURCE_ADAPTER:
			return basicSetModelResourceAdapter(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ClassMakerPackage.PROJECT__WORKSPACE:
			return eInternalContainer().eInverseRemove(this, ClassMakerPackage.WORKSPACE__PROJECTS, Workspace.class,
					msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	@Override
	public boolean contains(ISchedulingRule rule) {
		if (this == rule)
			return true;
		if (rule instanceof MultiRule) {
			MultiRule multi = (MultiRule) rule;
			ISchedulingRule[] children = multi.getChildren();
			for (int i = 0; i < children.length; i++)
				if (!contains(children[i]))
					return false;
			return true;
		}
		if (ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()).contains(rule))
			return true;
		if (ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName() + ".edit").contains(rule))
			return true;
		if (ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName() + ".editor").contains(rule))
			return true;
		return getWorkspace().contains(rule);
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if (this == rule)
			return true;
		return (ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()).isConflicting(rule)
				&& ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName() + ".edit").isConflicting(rule)
				&& ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName() + ".editor")
						.isConflicting(rule))
				|| getWorkspace().isConflicting(rule);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.PROJECT__MODEL_NAME:
			return getModelName();
		case ClassMakerPackage.PROJECT__PHASE:
			return getPhase();
		case ClassMakerPackage.PROJECT__LANGUAGE:
			return getLanguage();
		case ClassMakerPackage.PROJECT__DOMAIN_MODEL:
			return getDomainModel();
		case ClassMakerPackage.PROJECT__CUSTOMIZERS:
			if (coreType)
				return getCustomizers();
			else
				return getCustomizers().map();
		case ClassMakerPackage.PROJECT__PARENT:
			if (resolve)
				return getParent();
			return basicGetParent();
		case ClassMakerPackage.PROJECT__LOCALE:
			return getLocale();
		case ClassMakerPackage.PROJECT__PROJECT:
			if (resolve)
				return getProject();
			return basicGetProject();
		case ClassMakerPackage.PROJECT__NAME:
			return getName();
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			return getProjectName();
		case ClassMakerPackage.PROJECT__CHILDREN:
			return getChildren();
		case ClassMakerPackage.PROJECT__DIRTY:
			return isDirty();
		case ClassMakerPackage.PROJECT__WORKSPACE:
			return getWorkspace();
		case ClassMakerPackage.PROJECT__RESOURCE_PATH:
			return getResourcePath();
		case ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION:
			return isNeedCompletionNotification();
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			if (resolve)
				return getCompletionNotificationAdapter();
			return basicGetCompletionNotificationAdapter();
		case ClassMakerPackage.PROJECT__RESOURCE_RELOAD_LISTENER:
			return getResourceReloadListener();
		case ClassMakerPackage.PROJECT__SAVING_RESOURCE:
			return isSavingResource();
		case ClassMakerPackage.PROJECT__REVISION:
			if (resolve)
				return getRevision();
			return basicGetRevision();
		case ClassMakerPackage.PROJECT__REVISIONS:
			if (coreType)
				return getRevisions();
			else
				return getRevisions().map();
		case ClassMakerPackage.PROJECT__PROJECT_VERSION:
			return getProjectVersion();
		case ClassMakerPackage.PROJECT__SELECT_REVEAL_HANDLER:
			if (resolve)
				return getSelectRevealHandler();
			return basicGetSelectRevealHandler();
		case ClassMakerPackage.PROJECT__VERSION:
			return getVersion();
		case ClassMakerPackage.PROJECT__STATE:
			if (resolve)
				return getState();
			return basicGetState();
		case ClassMakerPackage.PROJECT__MODEL_RESOURCE_ADAPTER:
			return getModelResourceAdapter();
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
		case ClassMakerPackage.PROJECT__MODEL_NAME:
			setModelName((String) newValue);
			return;
		case ClassMakerPackage.PROJECT__PHASE:
			setPhase((Stage) newValue);
			return;
		case ClassMakerPackage.PROJECT__LANGUAGE:
			setLanguage((String) newValue);
			return;
		case ClassMakerPackage.PROJECT__DOMAIN_MODEL:
			setDomainModel((ModelPair) newValue);
			return;
		case ClassMakerPackage.PROJECT__CUSTOMIZERS:
			((EStructuralFeature.Setting) getCustomizers()).set(newValue);
			return;
		case ClassMakerPackage.PROJECT__PARENT:
			setParent((Item) newValue);
			return;
		case ClassMakerPackage.PROJECT__LOCALE:
			setLocale((Locale) newValue);
			return;
		case ClassMakerPackage.PROJECT__PROJECT:
			setProject((Project) newValue);
			return;
		case ClassMakerPackage.PROJECT__NAME:
			setName((String) newValue);
			return;
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			setProjectName((String) newValue);
			return;
		case ClassMakerPackage.PROJECT__DIRTY:
			setDirty((Boolean) newValue);
			return;
		case ClassMakerPackage.PROJECT__WORKSPACE:
			setWorkspace((Workspace) newValue);
			return;
		case ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION:
			setNeedCompletionNotification((Boolean) newValue);
			return;
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			setCompletionNotificationAdapter((CompletionNotificationAdapter) newValue);
			return;
		case ClassMakerPackage.PROJECT__SAVING_RESOURCE:
			setSavingResource((Boolean) newValue);
			return;
		case ClassMakerPackage.PROJECT__REVISION:
			setRevision((Revision) newValue);
			return;
		case ClassMakerPackage.PROJECT__REVISIONS:
			((EStructuralFeature.Setting) getRevisions()).set(newValue);
			return;
		case ClassMakerPackage.PROJECT__PROJECT_VERSION:
			setProjectVersion((Version) newValue);
			return;
		case ClassMakerPackage.PROJECT__SELECT_REVEAL_HANDLER:
			setSelectRevealHandler((SelectRevealHandler) newValue);
			return;
		case ClassMakerPackage.PROJECT__VERSION:
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
		case ClassMakerPackage.PROJECT__MODEL_NAME:
			setModelName(MODEL_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__PHASE:
			setPhase(PHASE_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__LANGUAGE:
			setLanguage(LANGUAGE_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__DOMAIN_MODEL:
			setDomainModel((ModelPair) null);
			return;
		case ClassMakerPackage.PROJECT__CUSTOMIZERS:
			getCustomizers().clear();
			return;
		case ClassMakerPackage.PROJECT__PARENT:
			setParent((Item) null);
			return;
		case ClassMakerPackage.PROJECT__LOCALE:
			setLocale(LOCALE_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__PROJECT:
			setProject((Project) null);
			return;
		case ClassMakerPackage.PROJECT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			setProjectName(PROJECT_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__DIRTY:
			setDirty(DIRTY_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__WORKSPACE:
			setWorkspace((Workspace) null);
			return;
		case ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION:
			setNeedCompletionNotification(NEED_COMPLETION_NOTIFICATION_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			setCompletionNotificationAdapter((CompletionNotificationAdapter) null);
			return;
		case ClassMakerPackage.PROJECT__SAVING_RESOURCE:
			setSavingResource(SAVING_RESOURCE_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__REVISION:
			setRevision((Revision) null);
			return;
		case ClassMakerPackage.PROJECT__REVISIONS:
			getRevisions().clear();
			return;
		case ClassMakerPackage.PROJECT__PROJECT_VERSION:
			setProjectVersion(PROJECT_VERSION_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__SELECT_REVEAL_HANDLER:
			setSelectRevealHandler((SelectRevealHandler) null);
			return;
		case ClassMakerPackage.PROJECT__VERSION:
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
		case ClassMakerPackage.PROJECT__MODEL_NAME:
			return MODEL_NAME_EDEFAULT == null ? modelName != null : !MODEL_NAME_EDEFAULT.equals(modelName);
		case ClassMakerPackage.PROJECT__PHASE:
			return phase != PHASE_EDEFAULT;
		case ClassMakerPackage.PROJECT__LANGUAGE:
			return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
		case ClassMakerPackage.PROJECT__DOMAIN_MODEL:
			return domainModel != null;
		case ClassMakerPackage.PROJECT__CUSTOMIZERS:
			return !getCustomizers().isEmpty();
		case ClassMakerPackage.PROJECT__PARENT:
			return basicGetParent() != null;
		case ClassMakerPackage.PROJECT__LOCALE:
			return LOCALE_EDEFAULT == null ? locale != null : !LOCALE_EDEFAULT.equals(locale);
		case ClassMakerPackage.PROJECT__PROJECT:
			return basicGetProject() != null;
		case ClassMakerPackage.PROJECT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? projectName != null : !PROJECT_NAME_EDEFAULT.equals(projectName);
		case ClassMakerPackage.PROJECT__CHILDREN:
			return !getChildren().isEmpty();
		case ClassMakerPackage.PROJECT__DIRTY:
			return dirty != DIRTY_EDEFAULT;
		case ClassMakerPackage.PROJECT__WORKSPACE:
			return getWorkspace() != null;
		case ClassMakerPackage.PROJECT__RESOURCE_PATH:
			return RESOURCE_PATH_EDEFAULT == null ? getResourcePath() != null
					: !RESOURCE_PATH_EDEFAULT.equals(getResourcePath());
		case ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION:
			return needCompletionNotification != NEED_COMPLETION_NOTIFICATION_EDEFAULT;
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			return completionNotificationAdapter != null;
		case ClassMakerPackage.PROJECT__RESOURCE_RELOAD_LISTENER:
			return resourceReloadListener != null;
		case ClassMakerPackage.PROJECT__SAVING_RESOURCE:
			return savingResource != SAVING_RESOURCE_EDEFAULT;
		case ClassMakerPackage.PROJECT__REVISION:
			return basicGetRevision() != null;
		case ClassMakerPackage.PROJECT__REVISIONS:
			return revisions != null && !revisions.isEmpty();
		case ClassMakerPackage.PROJECT__PROJECT_VERSION:
			return PROJECT_VERSION_EDEFAULT == null ? projectVersion != null
					: !PROJECT_VERSION_EDEFAULT.equals(projectVersion);
		case ClassMakerPackage.PROJECT__SELECT_REVEAL_HANDLER:
			return selectRevealHandler != null;
		case ClassMakerPackage.PROJECT__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case ClassMakerPackage.PROJECT__STATE:
			return basicGetState() != null;
		case ClassMakerPackage.PROJECT__MODEL_RESOURCE_ADAPTER:
			return modelResourceAdapter != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Item.class) {
			switch (derivedFeatureID) {
			case ClassMakerPackage.PROJECT__MODEL_NAME:
				return ClassMakerPackage.ITEM__MODEL_NAME;
			case ClassMakerPackage.PROJECT__PHASE:
				return ClassMakerPackage.ITEM__PHASE;
			case ClassMakerPackage.PROJECT__LANGUAGE:
				return ClassMakerPackage.ITEM__LANGUAGE;
			case ClassMakerPackage.PROJECT__DOMAIN_MODEL:
				return ClassMakerPackage.ITEM__DOMAIN_MODEL;
			case ClassMakerPackage.PROJECT__CUSTOMIZERS:
				return ClassMakerPackage.ITEM__CUSTOMIZERS;
			case ClassMakerPackage.PROJECT__PARENT:
				return ClassMakerPackage.ITEM__PARENT;
			case ClassMakerPackage.PROJECT__LOCALE:
				return ClassMakerPackage.ITEM__LOCALE;
			case ClassMakerPackage.PROJECT__PROJECT:
				return ClassMakerPackage.ITEM__PROJECT;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Item.class) {
			switch (baseFeatureID) {
			case ClassMakerPackage.ITEM__MODEL_NAME:
				return ClassMakerPackage.PROJECT__MODEL_NAME;
			case ClassMakerPackage.ITEM__PHASE:
				return ClassMakerPackage.PROJECT__PHASE;
			case ClassMakerPackage.ITEM__LANGUAGE:
				return ClassMakerPackage.PROJECT__LANGUAGE;
			case ClassMakerPackage.ITEM__DOMAIN_MODEL:
				return ClassMakerPackage.PROJECT__DOMAIN_MODEL;
			case ClassMakerPackage.ITEM__CUSTOMIZERS:
				return ClassMakerPackage.PROJECT__CUSTOMIZERS;
			case ClassMakerPackage.ITEM__PARENT:
				return ClassMakerPackage.PROJECT__PARENT;
			case ClassMakerPackage.ITEM__LOCALE:
				return ClassMakerPackage.PROJECT__LOCALE;
			case ClassMakerPackage.ITEM__PROJECT:
				return ClassMakerPackage.PROJECT__PROJECT;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectImpl other = (ProjectImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
		result.append(" (modelName: ");
		result.append(modelName);
		result.append(", phase: ");
		result.append(phase);
		result.append(", language: ");
		result.append(language);
		result.append(", locale: ");
		result.append(locale);
		result.append(", name: ");
		result.append(name);
		result.append(", projectName: ");
		result.append(projectName);
		result.append(", dirty: ");
		result.append(dirty);
		result.append(", needCompletionNotification: ");
		result.append(needCompletionNotification);
		result.append(", savingResource: ");
		result.append(savingResource);
		result.append(", projectVersion: ");
		result.append(projectVersion);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} // ProjectImpl
