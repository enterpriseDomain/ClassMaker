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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.enterprisedomain.classmaker.util.ClassMakerAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ClassMakerItemProviderAdapterFactory extends ClassMakerAdapterFactory
		implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassMakerItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.Contribution} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContributionItemProvider contributionItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.Contribution}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createContributionAdapter() {
		if (contributionItemProvider == null) {
			contributionItemProvider = new ContributionItemProvider(this);
		}

		return contributionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.Revision} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RevisionItemProvider revisionItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.Revision}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRevisionAdapter() {
		if (revisionItemProvider == null) {
			revisionItemProvider = new RevisionItemProvider(this);
		}

		return revisionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.State} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateItemProvider stateItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.State}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStateAdapter() {
		if (stateItemProvider == null) {
			stateItemProvider = new StateItemProvider(this);
		}

		return stateItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.Workspace} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkspaceItemProvider workspaceItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.Workspace}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWorkspaceAdapter() {
		if (workspaceItemProvider == null) {
			workspaceItemProvider = new WorkspaceItemProvider(this);
		}

		return workspaceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntegerToStateMapEntryItemProvider integerToStateMapEntryItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIntegerToStateMapEntryAdapter() {
		if (integerToStateMapEntryItemProvider == null) {
			integerToStateMapEntryItemProvider = new IntegerToStateMapEntryItemProvider(this);
		}

		return integerToStateMapEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VersionToRevisionMapEntryItemProvider versionToRevisionMapEntryItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createVersionToRevisionMapEntryAdapter() {
		if (versionToRevisionMapEntryItemProvider == null) {
			versionToRevisionMapEntryItemProvider = new VersionToRevisionMapEntryItemProvider(this);
		}

		return versionToRevisionMapEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.Customizer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomizerItemProvider customizerItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.Customizer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCustomizerAdapter() {
		if (customizerItemProvider == null) {
			customizerItemProvider = new CustomizerItemProvider(this);
		}

		return customizerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.StageQualifier} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StageQualifierItemProvider stageQualifierItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.StageQualifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStageQualifierAdapter() {
		if (stageQualifierItemProvider == null) {
			stageQualifierItemProvider = new StageQualifierItemProvider(this);
		}

		return stageQualifierItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StageQualifierToCustomizerMapEntryItemProvider stageQualifierToCustomizerMapEntryItemProvider;

	/**
	 * This creates an adapter for a {@link java.util.Map.Entry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStageQualifierToCustomizerMapEntryAdapter() {
		if (stageQualifierToCustomizerMapEntryItemProvider == null) {
			stageQualifierToCustomizerMapEntryItemProvider = new StageQualifierToCustomizerMapEntryItemProvider(this);
		}

		return stageQualifierToCustomizerMapEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.Project} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProjectItemProvider projectItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.Project}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProjectAdapter() {
		if (projectItemProvider == null) {
			projectItemProvider = new ProjectItemProvider(this);
		}

		return projectItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.ModelPair} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelPairItemProvider modelPairItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.ModelPair}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createModelPairAdapter() {
		if (modelPairItemProvider == null) {
			modelPairItemProvider = new ModelPairItemProvider(this);
		}

		return modelPairItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.SCMRegistry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SCMRegistryItemProvider scmRegistryItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.SCMRegistry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSCMRegistryAdapter() {
		if (scmRegistryItemProvider == null) {
			scmRegistryItemProvider = new SCMRegistryItemProvider(this);
		}

		return scmRegistryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.ResourceChangeListener} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceChangeListenerItemProvider resourceChangeListenerItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.ResourceChangeListener}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createResourceChangeListenerAdapter() {
		if (resourceChangeListenerItemProvider == null) {
			resourceChangeListenerItemProvider = new ResourceChangeListenerItemProvider(this);
		}

		return resourceChangeListenerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.ResourceAdapter} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceAdapterItemProvider resourceAdapterItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.ResourceAdapter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createResourceAdapterAdapter() {
		if (resourceAdapterItemProvider == null) {
			resourceAdapterItemProvider = new ResourceAdapterItemProvider(this);
		}

		return resourceAdapterItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.ClassMakerService} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassMakerServiceItemProvider classMakerServiceItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.ClassMakerService}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createClassMakerServiceAdapter() {
		if (classMakerServiceItemProvider == null) {
			classMakerServiceItemProvider = new ClassMakerServiceItemProvider(this);
		}

		return classMakerServiceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.Blueprint} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlueprintItemProvider blueprintItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.Blueprint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBlueprintAdapter() {
		if (blueprintItemProvider == null) {
			blueprintItemProvider = new BlueprintItemProvider(this);
		}

		return blueprintItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.enterprisedomain.classmaker.CompletionNotificationAdapter} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompletionNotificationAdapterItemProvider completionNotificationAdapterItemProvider;

	/**
	 * This creates an adapter for a {@link org.enterprisedomain.classmaker.CompletionNotificationAdapter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCompletionNotificationAdapterAdapter() {
		if (completionNotificationAdapterItemProvider == null) {
			completionNotificationAdapterItemProvider = new CompletionNotificationAdapterItemProvider(this);
		}

		return completionNotificationAdapterItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>) type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (contributionItemProvider != null)
			contributionItemProvider.dispose();
		if (revisionItemProvider != null)
			revisionItemProvider.dispose();
		if (stateItemProvider != null)
			stateItemProvider.dispose();
		if (workspaceItemProvider != null)
			workspaceItemProvider.dispose();
		if (integerToStateMapEntryItemProvider != null)
			integerToStateMapEntryItemProvider.dispose();
		if (versionToRevisionMapEntryItemProvider != null)
			versionToRevisionMapEntryItemProvider.dispose();
		if (customizerItemProvider != null)
			customizerItemProvider.dispose();
		if (stageQualifierItemProvider != null)
			stageQualifierItemProvider.dispose();
		if (stageQualifierToCustomizerMapEntryItemProvider != null)
			stageQualifierToCustomizerMapEntryItemProvider.dispose();
		if (projectItemProvider != null)
			projectItemProvider.dispose();
		if (modelPairItemProvider != null)
			modelPairItemProvider.dispose();
		if (scmRegistryItemProvider != null)
			scmRegistryItemProvider.dispose();
		if (resourceChangeListenerItemProvider != null)
			resourceChangeListenerItemProvider.dispose();
		if (resourceAdapterItemProvider != null)
			resourceAdapterItemProvider.dispose();
		if (classMakerServiceItemProvider != null)
			classMakerServiceItemProvider.dispose();
		if (blueprintItemProvider != null)
			blueprintItemProvider.dispose();
		if (completionNotificationAdapterItemProvider != null)
			completionNotificationAdapterItemProvider.dispose();
	}

}
