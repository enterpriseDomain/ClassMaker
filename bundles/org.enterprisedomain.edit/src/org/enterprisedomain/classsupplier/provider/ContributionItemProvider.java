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
package org.enterprisedomain.classsupplier.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.enterprisedomain.classsupplier.ClassSupplierFactory;
import org.enterprisedomain.classsupplier.ClassSupplierPackage;
import org.enterprisedomain.classsupplier.Contribution;

/**
 * This is the item provider adapter for a {@link org.enterprisedomain.classsupplier.Contribution} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContributionItemProvider extends ProjectItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addModelNamePropertyDescriptor(object);
			addPhasePropertyDescriptor(object);
			addVersionPropertyDescriptor(object);
			addLanguagePropertyDescriptor(object);
			addParentPropertyDescriptor(object);
			addLocalePropertyDescriptor(object);
			addRevisionPropertyDescriptor(object);
			addStatePropertyDescriptor(object);
			addLatestVersionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Model Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addModelNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Item_modelName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Item_modelName_feature", "_UI_Item_type"),
						ClassSupplierPackage.Literals.ITEM__MODEL_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Phase feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPhasePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Item_phase_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Item_phase_feature", "_UI_Item_type"),
						ClassSupplierPackage.Literals.ITEM__PHASE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Version feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Item_version_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Item_version_feature", "_UI_Item_type"),
						ClassSupplierPackage.Literals.ITEM__VERSION, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Language feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLanguagePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Item_language_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Item_language_feature", "_UI_Item_type"),
						ClassSupplierPackage.Literals.ITEM__LANGUAGE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Parent feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParentPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Item_parent_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Item_parent_feature", "_UI_Item_type"),
						ClassSupplierPackage.Literals.ITEM__PARENT, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Locale feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLocalePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Item_locale_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Item_locale_feature", "_UI_Item_type"),
						ClassSupplierPackage.Literals.ITEM__LOCALE, false, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Revision feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRevisionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Contribution_revision_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Contribution_revision_feature",
								"_UI_Contribution_type"),
						ClassSupplierPackage.Literals.CONTRIBUTION__REVISION, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the State feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStatePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Contribution_state_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Contribution_state_feature",
								"_UI_Contribution_type"),
						ClassSupplierPackage.Literals.CONTRIBUTION__STATE, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Latest Version feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLatestVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Contribution_latestVersion_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Contribution_latestVersion_feature",
								"_UI_Contribution_type"),
						ClassSupplierPackage.Literals.CONTRIBUTION__LATEST_VERSION, false, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ClassSupplierPackage.Literals.ITEM__DOMAIN_MODEL);
			childrenFeatures.add(ClassSupplierPackage.Literals.ITEM__CUSTOMIZERS);
			childrenFeatures.add(ClassSupplierPackage.Literals.CONTRIBUTION__REVISIONS);
			childrenFeatures.add(ClassSupplierPackage.Literals.CONTRIBUTION__MODEL);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Contribution.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Contribution"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Contribution) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Contribution_type")
				: getString("_UI_Contribution_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Contribution.class)) {
		case ClassSupplierPackage.CONTRIBUTION__MODEL_NAME:
		case ClassSupplierPackage.CONTRIBUTION__PHASE:
		case ClassSupplierPackage.CONTRIBUTION__VERSION:
		case ClassSupplierPackage.CONTRIBUTION__LANGUAGE:
		case ClassSupplierPackage.CONTRIBUTION__LOCALE:
		case ClassSupplierPackage.CONTRIBUTION__LATEST_VERSION:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case ClassSupplierPackage.CONTRIBUTION__DOMAIN_MODEL:
		case ClassSupplierPackage.CONTRIBUTION__CUSTOMIZERS:
		case ClassSupplierPackage.CONTRIBUTION__REVISIONS:
		case ClassSupplierPackage.CONTRIBUTION__MODEL:
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
				createChildParameter(ClassSupplierPackage.Literals.ITEM__CUSTOMIZERS, ClassSupplierFactory.eINSTANCE
						.create(ClassSupplierPackage.Literals.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(ClassSupplierPackage.Literals.CONTRIBUTION__REVISIONS,
				ClassSupplierFactory.eINSTANCE.create(ClassSupplierPackage.Literals.VERSION_TO_REVISION_MAP_ENTRY)));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == ClassSupplierPackage.Literals.PROJECT__CHILDREN
				|| childFeature == ClassSupplierPackage.Literals.CONTRIBUTION__REVISIONS
				|| childFeature == ClassSupplierPackage.Literals.ITEM__CUSTOMIZERS;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
