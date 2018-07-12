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
import java.util.Collections;
import java.util.Date;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
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
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.CompletionNotificationAdapter;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceChangeListener;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SelectRevealHandler;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ResourceUtils;
import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Project</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
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
 * </ul>
 *
 * @generated
 */
public class ProjectImpl extends EObjectImpl implements Project {

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

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

	protected ListenerList<CompletionListener> completionListeners = new ListenerList<CompletionListener>();

	protected ListenerList<ResourceChangeListener> resourceChangeListeners = new ListenerList<ResourceChangeListener>();

	protected EList<Object> children;

	protected ResourceChangeAdapter resourceAdapter = new ResourceChangeAdapter(this);

	public class ProjectNameAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == ClassMakerPackage.PROJECT__NAME
					&& msg.getEventType() == Notification.SET && msg.getNewStringValue() != null)
				setProjectName(getWorkspace().getService().computeProjectName(msg.getNewStringValue()));
		}

	}

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
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
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
		if (eIsSet(ClassMakerPackage.Literals.PROJECT__REVISION)
				&& getRevision().eIsSet(ClassMakerPackage.Literals.REVISION__STATE))
			return (EList<Object>) (EList<?>) getRevision().getState().getResource().getContents();
		if (children == null)
			children = ECollections.newBasicEList();
		return children;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isDirty() {
		boolean result = true;
		for (Object child : getChildren())
			if (child instanceof Resource) {
				result &= ((Resource) child).isModified();
			} else if (child instanceof EObject) {
				result &= ((EObject) child).eResource().isModified();
			}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isNeedCompletionNotification() {
		return needCompletionNotification;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
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
	public CompletionNotificationAdapter getCompletionNotificationAdapter() {
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
	public void setCompletionNotificationAdapter(CompletionNotificationAdapter newCompletionNotificationAdapter) {
		if (newCompletionNotificationAdapter != completionNotificationAdapter) {
			NotificationChain msgs = null;
			if (completionNotificationAdapter != null)
				msgs = ((InternalEObject) completionNotificationAdapter).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, null,
						msgs);
			if (newCompletionNotificationAdapter != null)
				msgs = ((InternalEObject) newCompletionNotificationAdapter).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, null,
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
							&& !theResource.isModified() && theResource.isLoaded()) {
						loading = true;
						if (ProjectImpl.this.eIsSet(ClassMakerPackage.PROJECT__SELECT_REVEAL_HANDLER))
							getSelectRevealHandler().prepare();
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
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSavingResource() {
		return savingResource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
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
	public Version getProjectVersion() {
		return projectVersion;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
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
		((EObject) getChildren().get(0)).eResource().getContents().removeAll(objects);
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
		// for (Object listener : resourceChangeListeners.getListeners())
		// if (listener.equals(resourceListener))
		// return;
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
		if (getRevisions().containsKey(version))
			setProjectVersion(version);
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
		URI uri = getResourceURI();
		Resource resource = null;
		if (new File(uri.toFileString()).exists()) {
			getWorkspace().getResourceSet().getResource(uri, false);
			try {
				resource.load(Collections.emptyMap());
			} catch (IOException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			}
		} else {
			resource = getWorkspace().getResourceSet().createResource(uri);
			if (commit)
				try {
					resource.save(Collections.emptyMap());
				} catch (IOException e) {
					ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
				}
		}
		getWorkspace().getResourceSet().eAdapters().add(resourceAdapter);
		addResourceChangeListener(getResourceReloadListener());
		if (eIsSet(ClassMakerPackage.PROJECT__REVISION)
				&& getRevision().eIsSet(ClassMakerPackage.Literals.REVISION__STATE))
			getRevision().getState().setResource(resource);
		// TODO Add SCM support if commit
		return ""; //$NON-NLS-1$
	}

	private URI getResourceURI() {
		IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
		return URI.createFileURI(workspacePath.append(getResourcePath()).toString());
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
		case ClassMakerPackage.PROJECT__WORKSPACE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
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
		case ClassMakerPackage.PROJECT__WORKSPACE:
			return basicSetWorkspace(null, msgs);
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			return basicSetCompletionNotificationAdapter(null, msgs);
		case ClassMakerPackage.PROJECT__REVISIONS:
			return ((InternalEList<?>) getRevisions()).basicRemove(otherEnd, msgs);
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
		return getWorkspace().contains(rule);
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if (this == rule)
			return true;
		return ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()).isConflicting(rule)
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
			return getCompletionNotificationAdapter();
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
		case ClassMakerPackage.PROJECT__NAME:
			setName((String) newValue);
			return;
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			setProjectName((String) newValue);
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
		case ClassMakerPackage.PROJECT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			setProjectName(PROJECT_NAME_EDEFAULT);
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
		case ClassMakerPackage.PROJECT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? projectName != null : !PROJECT_NAME_EDEFAULT.equals(projectName);
		case ClassMakerPackage.PROJECT__CHILDREN:
			return !getChildren().isEmpty();
		case ClassMakerPackage.PROJECT__DIRTY:
			return isDirty() != DIRTY_EDEFAULT;
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
		}
		return super.eIsSet(featureID);
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", projectName: ");
		result.append(projectName);
		result.append(", needCompletionNotification: ");
		result.append(needCompletionNotification);
		result.append(", savingResource: ");
		result.append(savingResource);
		result.append(", projectVersion: ");
		result.append(projectVersion);
		result.append(')');
		return result.toString();
	}

} // ProjectImpl
