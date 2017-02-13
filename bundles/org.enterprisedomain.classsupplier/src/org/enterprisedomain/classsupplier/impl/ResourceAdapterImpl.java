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
package org.enterprisedomain.classsupplier.impl;

import java.net.MalformedURLException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.enterprisedomain.classsupplier.ClassSupplierPackage;
import org.enterprisedomain.classsupplier.ResourceAdapter;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Resource Adapter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ResourceAdapterImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.impl.ResourceAdapterImpl#getFilename <em>Filename</em>}</li>
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
		return ClassSupplierPackage.Literals.RESOURCE_ADAPTER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClassSupplierPackage.RESOURCE_ADAPTER__RESOURCE,
					oldResource, resource));
	}

	private void copyAdapters(Resource resource) {
		eAdapters().clear();
		eAdapters().addAll(resource.eAdapters());
		eAdapters().addAll(resource.getResourceSet().eAdapters());
		eSetResource((org.eclipse.emf.ecore.resource.Resource.Internal) resource, null);
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassSupplierPackage.RESOURCE_ADAPTER__RESOURCE:
			if (resolve)
				return getResource();
			return basicGetResource();
		case ClassSupplierPackage.RESOURCE_ADAPTER__FILENAME:
			return getFilename();
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
		case ClassSupplierPackage.RESOURCE_ADAPTER__RESOURCE:
			setResource((Resource) newValue);
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
		case ClassSupplierPackage.RESOURCE_ADAPTER__RESOURCE:
			setResource((Resource) null);
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
		case ClassSupplierPackage.RESOURCE_ADAPTER__RESOURCE:
			return resource != null;
		case ClassSupplierPackage.RESOURCE_ADAPTER__FILENAME:
			return FILENAME_EDEFAULT == null ? getFilename() != null : !FILENAME_EDEFAULT.equals(getFilename());
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
