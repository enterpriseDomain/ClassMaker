/**
 * Copyright 2016 Kyrill Zotkin
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

import java.net.MalformedURLException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.ResourceAdapter;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Resource Adapter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ResourceAdapterImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ResourceAdapterImpl#getFilename <em>Filename</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ResourceAdapterImpl#getProject <em>Project</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceAdapterImpl extends EObjectImpl implements ResourceAdapter {
	/**
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected Resource resource;

	/**
	 * The default value of the '{@link #getFilename() <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getFilename()
	 * @generated
	 * @ordered
	 */
	protected static final String FILENAME_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceAdapterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.RESOURCE_ADAPTER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Resource getResource() {
		return basicGetResource();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Resource basicGetResource() {
		return resource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setResource(Resource newResource) {
		Resource oldResource = resource;
		resource = newResource;
		copyAdapters(resource);
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.RESOURCE_ADAPTER__RESOURCE,
					oldResource, resource));
	}

	private void copyAdapters(Resource resource) {
		eAdapters().clear();
		eAdapters().addAll(resource.eAdapters());
		for (Adapter adapter : resource.getResourceSet().eAdapters())
			if (!(adapter instanceof EContentAdapter))
				eAdapters().add(adapter);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getFilename() {
		URI uri = getResource().getURI();
		try {
			uri = uri.deresolve(URI.createURI(ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toFile().toURI()
					.toURL().toExternalForm().toString()));
		} catch (MalformedURLException e) {
		}
		return uri.path();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Contribution getProject() {
		if (eContainerFeatureID() != ClassMakerPackage.RESOURCE_ADAPTER__PROJECT)
			return null;
		return (Contribution) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProject(Contribution newProject, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newProject, ClassMakerPackage.RESOURCE_ADAPTER__PROJECT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setProject(Contribution newProject) {
		if (newProject != eInternalContainer()
				|| (eContainerFeatureID() != ClassMakerPackage.RESOURCE_ADAPTER__PROJECT && newProject != null)) {
			if (EcoreUtil.isAncestor(this, newProject))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProject != null)
				msgs = ((InternalEObject) newProject).eInverseAdd(this,
						ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER, Contribution.class, msgs);
			msgs = basicSetProject(newProject, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.RESOURCE_ADAPTER__PROJECT,
					newProject, newProject));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.RESOURCE_ADAPTER__PROJECT:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetProject((Contribution) otherEnd, msgs);
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
		case ClassMakerPackage.RESOURCE_ADAPTER__PROJECT:
			return basicSetProject(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ClassMakerPackage.RESOURCE_ADAPTER__PROJECT:
			return eInternalContainer().eInverseRemove(this, ClassMakerPackage.CONTRIBUTION__MODEL_RESOURCE_ADAPTER,
					Contribution.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.RESOURCE_ADAPTER__RESOURCE:
			if (resolve)
				return getResource();
			return basicGetResource();
		case ClassMakerPackage.RESOURCE_ADAPTER__FILENAME:
			return getFilename();
		case ClassMakerPackage.RESOURCE_ADAPTER__PROJECT:
			return getProject();
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
		case ClassMakerPackage.RESOURCE_ADAPTER__RESOURCE:
			setResource((Resource) newValue);
			return;
		case ClassMakerPackage.RESOURCE_ADAPTER__PROJECT:
			setProject((Contribution) newValue);
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
		case ClassMakerPackage.RESOURCE_ADAPTER__RESOURCE:
			setResource((Resource) null);
			return;
		case ClassMakerPackage.RESOURCE_ADAPTER__PROJECT:
			setProject((Contribution) null);
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
		case ClassMakerPackage.RESOURCE_ADAPTER__RESOURCE:
			return resource != null;
		case ClassMakerPackage.RESOURCE_ADAPTER__FILENAME:
			return FILENAME_EDEFAULT == null ? getFilename() != null : !FILENAME_EDEFAULT.equals(getFilename());
		case ClassMakerPackage.RESOURCE_ADAPTER__PROJECT:
			return getProject() != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public void notifyChanged(Notification notification) {

	}

	@Override
	public Notifier getTarget() {
		return getResource();
	}

	@Override
	public void setTarget(Notifier newTarget) {
		setResource((Resource) newTarget);
	}

	@Override
	public boolean isAdapterForType(Object type) {
		if (type == Resource.class)
			return true;
		return false;
	}

} // ResourceAdapterImpl
