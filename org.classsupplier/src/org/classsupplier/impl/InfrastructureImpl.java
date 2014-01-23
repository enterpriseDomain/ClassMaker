/**
 */
package org.classsupplier.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.classsupplier.Artifact;
import org.classsupplier.ClassSupplierFactory;
import org.classsupplier.ClassSupplierPackage;
import org.classsupplier.Infrastructure;
import org.classsupplier.State;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Infrastructure</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.classsupplier.impl.InfrastructureImpl#getContents <em>Contents
 * </em>}</li>
 * <li>{@link org.classsupplier.impl.InfrastructureImpl#getResourceSet <em>
 * Resource Set</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class InfrastructureImpl extends EObjectImpl implements Infrastructure {

	/**
	 * The cached value of the '{@link #getContents() <em>Contents</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getContents()
	 * @generated
	 * @ordered
	 */
	protected EList<Artifact> contents;

	/**
	 * The default value of the '{@link #getResourceSet() <em>Resource Set</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResourceSet()
	 * @generated NOT
	 * @ordered
	 */
	protected static final ResourceSet RESOURCE_SET_EDEFAULT = new ResourceSetImpl();

	/**
	 * The cached value of the '{@link #getResourceSet() <em>Resource Set</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResourceSet()
	 * @generated
	 * @ordered
	 */
	protected ResourceSet resourceSet = RESOURCE_SET_EDEFAULT;

	protected Map<EPackage, Artifact> prototypeModelsToArtifaсts = new HashMap<EPackage, Artifact>();

	protected Map<EPackage, Artifact> loadedModelsToArtifaсts = new HashMap<EPackage, Artifact>();

	private EAdapterList<Adapter> listeners = new EAdapterList<Adapter>(this);

	private Adapter listeningAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Artifact.class) == ClassSupplierPackage.ARTIFACT__STATE
					&& msg.getNewValue() == State.PROTOTYPE)
				notifyListeners(new NotificationImpl(msg.getEventType(), null,
						msg.getNotifier()));
		}

	};

	private Adapter contentsAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(getClass()) == ClassSupplierPackage.INFRASTRUCTURE__CONTENTS) {
				switch (msg.getEventType()) {
				case Notification.ADD:
					notifyEPackageAdd((Artifact) msg.getNewValue());
					((EObject) msg.getNewValue()).eAdapters().add(
							listeningAdapter);
					break;
				case Notification.REMOVE:
					((EObject) msg.getOldValue()).eAdapters().remove(
							listeningAdapter);
					notifyEPackageRemove((Artifact) msg.getOldValue());
					break;
				}
			}
		}

	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected InfrastructureImpl() {
		super();
		eAdapters().add(contentsAdapter);
		init();
	}

	private void init() {
		IProgressMonitor monitor = new NullProgressMonitor();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		for (IProject project : workspace.getRoot().getProjects()) {
			Artifact artifact = null;
			try {
				if (!project.isOpen())
					project.open(monitor);
				if (project.hasNature(OSGi.NATURE_ID)) {
					artifact = ClassSupplierFactory.eINSTANCE.createArtifact();
					artifact.setProjectName(project.getName());
					registerArtifact(artifact);
					workspace.run(new Initializer(project, artifact, this),
							monitor);
				}
			} catch (CoreException e) {
				if (artifact != null)
					artifact.setStatus(e.getStatus());
			}
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.INFRASTRUCTURE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Artifact> getContents() {
		if (contents == null) {
			contents = new EObjectContainmentEList<Artifact>(Artifact.class,
					this, ClassSupplierPackage.INFRASTRUCTURE__CONTENTS);
		}
		return contents;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResourceSet(ResourceSet newResourceSet) {
		ResourceSet oldResourceSet = resourceSet;
		resourceSet = newResourceSet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassSupplierPackage.INFRASTRUCTURE__RESOURCE_SET,
					oldResourceSet, resourceSet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void addRefreshListener(Adapter listener) {
		listeners.add(listener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void removeRefreshListener(Adapter listener) {
		listeners.remove(listener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void registerArtifact(Artifact artifact) {
		getContents().add(artifact);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void unregisterArtifact(Artifact artifact) {
		getContents().remove(artifact);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int containsArtifact(EPackage blueprint) {
		for (EPackage ePackage : loadedModelsToArtifaсts.keySet())
			if (packagesAreEqual(ePackage, blueprint))
				return CONTAINS_LOADED;
		for (EPackage ePackage : prototypeModelsToArtifaсts.keySet())
			if (packagesAreEqual(ePackage, blueprint))
				return CONTAINS_PROTOTYPE;
		return DOESNT_CONTAIN;
	}

	private boolean packagesAreEqual(EPackage first, EPackage second) {
		return first.getNsURI().equals(second.getNsURI())
				|| first.getName().equals(second.getName());
	}

	/**
	 * a <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact getArtifact(String projectName) {
		for (Artifact artifact : getContents()) {
			if (artifact.getProjectName() != null
					&& artifact.getProjectName().equals(projectName))
				return artifact;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact getArtifact(EPackage ePackage) {
		switch (containsArtifact(ePackage)) {
		case CONTAINS_LOADED:
			for (EPackage loaded : loadedModelsToArtifaсts.keySet())
				if (packagesAreEqual(loaded, ePackage))
					return loadedModelsToArtifaсts.get(loaded);
		case CONTAINS_PROTOTYPE:
			for (EPackage prototype : prototypeModelsToArtifaсts.keySet())
				if (packagesAreEqual(prototype, ePackage))
					return prototypeModelsToArtifaсts.get(prototype);
		case DOESNT_CONTAIN:
		default:
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void save(IProgressMonitor monitor) {
		try {
			ResourcesPlugin.getWorkspace().save(true, monitor);
		} catch (CoreException e) {
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact createArtifact(EPackage blueprint) {
		Artifact result = ClassSupplierFactory.eINSTANCE.createArtifact();
		result.setName(blueprint.getName());
		result.setPrototypeEPackage(blueprint);
		registerArtifact(result);
		return result;
	}

	private void notifyListeners(Notification notification) {
		for (Adapter listener : listeners)
			listener.notifyChanged(notification);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassSupplierPackage.INFRASTRUCTURE__CONTENTS:
			return ((InternalEList<?>) getContents()).basicRemove(otherEnd,
					msgs);
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
		case ClassSupplierPackage.INFRASTRUCTURE__CONTENTS:
			return getContents();
		case ClassSupplierPackage.INFRASTRUCTURE__RESOURCE_SET:
			return getResourceSet();
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
		case ClassSupplierPackage.INFRASTRUCTURE__CONTENTS:
			getContents().clear();
			getContents().addAll((Collection<? extends Artifact>) newValue);
			return;
		case ClassSupplierPackage.INFRASTRUCTURE__RESOURCE_SET:
			setResourceSet((ResourceSet) newValue);
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
		case ClassSupplierPackage.INFRASTRUCTURE__CONTENTS:
			getContents().clear();
			return;
		case ClassSupplierPackage.INFRASTRUCTURE__RESOURCE_SET:
			setResourceSet(RESOURCE_SET_EDEFAULT);
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
		case ClassSupplierPackage.INFRASTRUCTURE__CONTENTS:
			return contents != null && !contents.isEmpty();
		case ClassSupplierPackage.INFRASTRUCTURE__RESOURCE_SET:
			return RESOURCE_SET_EDEFAULT == null ? resourceSet != null
					: !RESOURCE_SET_EDEFAULT.equals(resourceSet);
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (resourceSet: ");
		result.append(resourceSet);
		result.append(')');
		return result.toString();
	}

	protected void notifyEPackageAdd(Artifact artifact) {
		if (artifact
				.eIsSet(ClassSupplierPackage.Literals.ARTIFACT__PROTOTYPE_EPACKAGE))
			prototypeModelsToArtifaсts.put(artifact.getPrototypeEPackage(),
					artifact);
		if (artifact
				.eIsSet(ClassSupplierPackage.Literals.ARTIFACT__LOADED_EPACKAGE))
			loadedModelsToArtifaсts.put(artifact.getLoadedEPackage(), artifact);
	}

	protected void notifyEPackageRemove(Artifact artifact) {
		if (artifact
				.eIsSet(ClassSupplierPackage.Literals.ARTIFACT__PROTOTYPE_EPACKAGE))
			prototypeModelsToArtifaсts.remove(artifact.getPrototypeEPackage());
		if (artifact
				.eIsSet(ClassSupplierPackage.Literals.ARTIFACT__LOADED_EPACKAGE))
			loadedModelsToArtifaсts.remove(artifact.getLoadedEPackage());
	}

} // InfrastructureImpl
