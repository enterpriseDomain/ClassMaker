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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.CompletionNotificationAdapter;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Completion Notification Adapter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.impl.CompletionNotificationAdapterImpl#getError
 * <em>Error</em>}</li>
 * <li>{@link org.enterprisedomain.classmaker.impl.CompletionNotificationAdapterImpl#getProject
 * <em>Project</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompletionNotificationAdapterImpl extends EObjectImpl implements CompletionNotificationAdapter {
	/**
	 * The default value of the '{@link #getError() <em>Error</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getError()
	 * @generated
	 * @ordered
	 */
	protected static final IStatus ERROR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getError() <em>Error</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getError()
	 * @generated
	 * @ordered
	 */
	protected IStatus error = ERROR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProject() <em>Project</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProject()
	 * @generated
	 * @ordered
	 */
	protected Project project;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CompletionNotificationAdapterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.COMPLETION_NOTIFICATION_ADAPTER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IStatus getError() {
		return error;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Project getProject() {
		if (project != null && project.eIsProxy()) {
			InternalEObject oldProject = (InternalEObject) project;
			project = (Project) eResolveProxy(oldProject);
			if (project != oldProject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT, oldProject, project));
			}
		}
		return project;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Project basicGetProject() {
		return project;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetProject(Project newProject, NotificationChain msgs) {
		Project oldProject = project;
		project = newProject;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT, oldProject, newProject);
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
	public void setProject(Project newProject) {
		if (newProject != project) {
			NotificationChain msgs = null;
			if (project != null)
				msgs = ((InternalEObject) project).eInverseRemove(this,
						ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, Project.class, msgs);
			if (newProject != null)
				msgs = ((InternalEObject) newProject).eInverseAdd(this,
						ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, Project.class, msgs);
			msgs = basicSetProject(newProject, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT, newProject, newProject));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT:
			if (project != null)
				msgs = ((InternalEObject) project).eInverseRemove(this,
						ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, Project.class, msgs);
			return basicSetProject((Project) otherEnd, msgs);
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
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT:
			return basicSetProject(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * Returns <code>false</code>
	 * 
	 * @param type the type.
	 * @return <code>false</code>
	 */
	public boolean isAdapterForType(Object type) {
		return false;
	}

	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getFeatureID(Project.class) == ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION
				&& msg.getNewBooleanValue()) {
			error = ERROR_EDEFAULT;
			try {
				getProject().notifyCompletion();
			} catch (NullPointerException e) {
			} catch (Exception e) {
				error = ClassMakerPlugin.createErrorStatus(e);
				ClassMakerPlugin.getInstance().getLog().log(getError());
			}
		}

	}

	/*
	 * Javadoc copied from interface.
	 */
	public Notifier getTarget() {
		return null;
	}

	/*
	 * Javadoc copied from interface.
	 */
	public void setTarget(Notifier newTarget) {

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__ERROR:
			return getError();
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT:
			if (resolve)
				return getProject();
			return basicGetProject();
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
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT:
			setProject((Project) newValue);
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
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT:
			setProject((Project) null);
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
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__ERROR:
			return ERROR_EDEFAULT == null ? error != null : !ERROR_EDEFAULT.equals(error);
		case ClassMakerPackage.COMPLETION_NOTIFICATION_ADAPTER__PROJECT:
			return project != null;
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
		result.append(" (error: ");
		result.append(error);
		result.append(')');
		return result.toString();
	}

} // CompletionNotificationAdapterImpl
