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

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Project;

/**
 * This is the item provider adapter for a {@link org.enterprisedomain.classmaker.Project} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProjectItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProjectItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
			addProjectNamePropertyDescriptor(object);
			addChildrenPropertyDescriptor(object);
			addDirtyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Project_name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Project_name_feature", "_UI_Project_type"),
						ClassMakerPackage.Literals.PROJECT__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Project Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProjectNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Project_projectName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Project_projectName_feature",
								"_UI_Project_type"),
						ClassMakerPackage.Literals.PROJECT__PROJECT_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Children feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addChildrenPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Project_children_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Project_children_feature",
								"_UI_Project_type"),
						ClassMakerPackage.Literals.PROJECT__CHILDREN, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Dirty feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDirtyPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Project_dirty_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Project_dirty_feature",
								"_UI_Project_type"),
						ClassMakerPackage.Literals.PROJECT__DIRTY, false, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
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
			childrenFeatures.add(ClassMakerPackage.Literals.PROJECT__CHILDREN);
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
	 * This returns Project.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Project"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Project) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Project_type")
				: getString("_UI_Project_type") + " " + label;
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

		switch (notification.getFeatureID(Project.class)) {
		case ClassMakerPackage.PROJECT__NAME:
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
		case ClassMakerPackage.PROJECT__DIRTY:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case ClassMakerPackage.PROJECT__CHILDREN:
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

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createProject()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createContribution()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createRevision()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createState()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createWorkspace()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.create(ClassMakerPackage.Literals.INTEGER_TO_STATE_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.create(ClassMakerPackage.Literals.VERSION_TO_REVISION_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createCustomizer()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createStageQualifier()));

		newChildDescriptors
				.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN, ClassMakerFactory.eINSTANCE
						.create(ClassMakerPackage.Literals.STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createModelPair()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createResourceAdapter()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				ClassMakerFactory.eINSTANCE.createClassPlant()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEAttribute()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEAnnotation()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEClass()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEDataType()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEEnum()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEEnumLiteral()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEFactory()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEObject()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEOperation()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEPackage()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEParameter()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEReference()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.create(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY)));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createEGenericType()));

		newChildDescriptors.add(createChildParameter(ClassMakerPackage.Literals.PROJECT__CHILDREN,
				EcoreFactory.eINSTANCE.createETypeParameter()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ClassMakerEditPlugin.INSTANCE;
	}

}
