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
package org.enterprisedomain.classmaker.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.State;

/**
 * This is the item provider adapter for a {@link org.enterprisedomain.classmaker.State} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StateItemProvider extends ItemItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public StateItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addPackageClassNamePropertyDescriptor(object);
			addEditPluginClassNamePropertyDescriptor(object);
			addEditorPluginClassNamePropertyDescriptor(object);
			addRequiredPluginsPropertyDescriptor(object);
			addRevisionPropertyDescriptor(object);
			addTimestampPropertyDescriptor(object);
			addDeployableUnitNamePropertyDescriptor(object);
			addEditDeployableUnitNamePropertyDescriptor(object);
			addEditorDeployableUnitNamePropertyDescriptor(object);
			addJobFamilyPropertyDescriptor(object);
			addResourcePropertyDescriptor(object);
			addCommitIdsPropertyDescriptor(object);
			addCommitIdPropertyDescriptor(object);
			addProjectNamePropertyDescriptor(object);
			addMakingPropertyDescriptor(object);
			addEditPropertyDescriptor(object);
			addEditorPropertyDescriptor(object);
			addStrategyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Package Class Name feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPackageClassNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_packageClassName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_packageClassName_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__PACKAGE_CLASS_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Edit Plugin Class Name feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addEditPluginClassNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_editPluginClassName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_editPluginClassName_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__EDIT_PLUGIN_CLASS_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Editor Plugin Class Name feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditorPluginClassNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_editorPluginClassName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_editorPluginClassName_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__EDITOR_PLUGIN_CLASS_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Required Plugins feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRequiredPluginsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_requiredPlugins_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_requiredPlugins_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__REQUIRED_PLUGINS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Revision feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRevisionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_revision_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_revision_feature", "_UI_State_type"),
						ClassMakerPackage.Literals.STATE__REVISION, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Timestamp feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTimestampPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_timestamp_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_timestamp_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__TIMESTAMP, true, false, false,
						ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Deployable Unit Name feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDeployableUnitNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_deployableUnitName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_deployableUnitName_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__DEPLOYABLE_UNIT_NAME, false, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Edit Deployable Unit Name feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditDeployableUnitNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_editDeployableUnitName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_editDeployableUnitName_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__EDIT_DEPLOYABLE_UNIT_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Editor Deployable Unit Name feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditorDeployableUnitNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_editorDeployableUnitName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_editorDeployableUnitName_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__EDITOR_DEPLOYABLE_UNIT_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Job Family feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addJobFamilyPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_jobFamily_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_jobFamily_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__JOB_FAMILY, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Resource feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourcePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_resource_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_resource_feature", "_UI_State_type"),
						ClassMakerPackage.Literals.STATE__RESOURCE, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Commit Ids feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addCommitIdsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_commitIds_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_commitIds_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__COMMIT_IDS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Commit Id feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addCommitIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_commitId_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_commitId_feature", "_UI_State_type"),
						ClassMakerPackage.Literals.STATE__COMMIT_ID, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Project Name feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addProjectNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_projectName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_projectName_feature",
								"_UI_State_type"),
						ClassMakerPackage.Literals.STATE__PROJECT_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Making feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMakingPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_making_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_making_feature", "_UI_State_type"),
						ClassMakerPackage.Literals.STATE__MAKING, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Edit feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_edit_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_edit_feature", "_UI_State_type"),
						ClassMakerPackage.Literals.STATE__EDIT, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Editor feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditorPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_editor_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_editor_feature", "_UI_State_type"),
						ClassMakerPackage.Literals.STATE__EDITOR, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Strategy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStrategyPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_State_strategy_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_State_strategy_feature", "_UI_State_type"),
						ClassMakerPackage.Literals.STATE__STRATEGY, true, false, true, null, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ClassMakerPackage.Literals.STATE__STATE_CUSTOMIZERS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns State.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/State"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		State state = (State) object;
		return getString("_UI_State_type") + " " + state.getTimestamp();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(State.class)) {
		case ClassMakerPackage.STATE__PACKAGE_CLASS_NAME:
		case ClassMakerPackage.STATE__EDIT_PLUGIN_CLASS_NAME:
		case ClassMakerPackage.STATE__EDITOR_PLUGIN_CLASS_NAME:
		case ClassMakerPackage.STATE__REQUIRED_PLUGINS:
		case ClassMakerPackage.STATE__TIMESTAMP:
		case ClassMakerPackage.STATE__DEPLOYABLE_UNIT_NAME:
		case ClassMakerPackage.STATE__EDIT_DEPLOYABLE_UNIT_NAME:
		case ClassMakerPackage.STATE__EDITOR_DEPLOYABLE_UNIT_NAME:
		case ClassMakerPackage.STATE__JOB_FAMILY:
		case ClassMakerPackage.STATE__COMMIT_IDS:
		case ClassMakerPackage.STATE__COMMIT_ID:
		case ClassMakerPackage.STATE__PROJECT_NAME:
		case ClassMakerPackage.STATE__MAKING:
		case ClassMakerPackage.STATE__EDIT:
		case ClassMakerPackage.STATE__EDITOR:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case ClassMakerPackage.STATE__STATE_CUSTOMIZERS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(
				createChildParameter(ClassMakerPackage.Literals.STATE__STATE_CUSTOMIZERS, ClassMakerFactory.eINSTANCE
						.create(ClassMakerPackage.Literals.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY)));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == ClassMakerPackage.Literals.ITEM__CUSTOMIZERS
				|| childFeature == ClassMakerPackage.Literals.STATE__STATE_CUSTOMIZERS;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
