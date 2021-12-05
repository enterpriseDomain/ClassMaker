/**
 * Copyright 2012-2018 Kyrill Zotkin
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
package org.enterprisedomain.ecp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.core.ECPRepository;
import org.eclipse.emf.ecp.core.util.ECPContainer;
import org.eclipse.emf.ecp.core.util.ECPModelContextAdapter;
import org.eclipse.emf.ecp.core.util.ECPUtil;
import org.eclipse.emf.ecp.core.util.observer.ECPProjectContentChangedObserver;
import org.eclipse.emf.ecp.core.util.observer.ECPRepositoriesChangedObserver;
import org.eclipse.emf.ecp.spi.core.DefaultProvider;
import org.eclipse.emf.ecp.spi.core.InternalProject;
import org.eclipse.emf.ecp.spi.core.InternalProvider;
import org.eclipse.emf.ecp.spi.core.InternalRepository;
import org.eclipse.emf.ecp.spi.core.ProviderChangeListener;
import org.eclipse.emf.ecp.spi.core.util.InternalChildrenList;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.SelectRevealHandler;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.CompletionListenerImpl;
import org.enterprisedomain.classmaker.impl.ResourceChangeListenerImpl;
import org.enterprisedomain.classmaker.util.ClassMakerAdapterFactory;
import org.enterprisedomain.classmaker.util.ResourceUtils;

public class EnterpriseDomainProvider extends DefaultProvider {

	public static final AdapterFactory ENTERPRISE_DOMAIN_ADAPTER_FACTORY = new ComposedAdapterFactory(
			new AdapterFactory[] { new ClassMakerAdapterFactory(), InternalProvider.EMF_ADAPTER_FACTORY });

	public static final String NAME = "org.enterprisedomain.ecp.provider";

	public static final String PROP_CONTRIBUTION = "isContribution";

	private static Map<String, HashSet<String>> visiblePackagesToClasses = new HashMap<String, HashSet<String>>();

	private final IResourceChangeListener resourceChangeListener = new EnterpriseDomainResourceListener();

	private static Map<InternalProject, Boolean> initing = new HashMap<InternalProject, Boolean>();

	private Adapter adapter = new EContentAdapter() {

		@SuppressWarnings("unchecked")
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			Resource resource = null;
			if (notification.getEventType() == Notification.ADD
					&& notification.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS
					&& notification.getNotifier() instanceof Resource) {
				resource = (Resource) notification.getNotifier();
			} else if (notification.getEventType() == Notification.ADD
					&& notification.getNotifier() instanceof EObject) {
				final Object feature = notification.getFeature();
				if (feature instanceof EReference) {
					final EReference eReference = (EReference) feature;
					if (eReference.isContainment()) {
						resource = ((EObject) notification.getNewValue()).eResource();
					}
				}
			}
			if (resource == null) {
				return;
			}
			if (Activator.getClassMaker() == null)
				return;
			Project domainProject = Activator.getClassMaker().getWorkspace().getProject(resource);
			if (domainProject != null && !(domainProject instanceof Contribution)) {
				try {
					Map<String, String> options = new HashMap<String, String>();
					options.put(XMLResource.OPTION_ENCODING, "UTF-8");
					resource.save(options);
				} catch (final IOException ex) {
					Activator.log(ex);
				}
			}
			InternalProject project = (InternalProject) getModelContext(resource);
			if (project != null) {
				propagatePackagesVisibility();
				project.notifyObjectsChanged((Collection<Object>) (Collection<?>) Arrays.asList(project), true);
			}
		}

	};

	private BasicCommandStack commandStack;

	public EnterpriseDomainProvider() {
		super(NAME);
		if (Activator.getClassMaker() != null) {
			Activator.getClassMaker().getWorkspace().getResourceSet().eAdapters().add(adapter);
		}
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener);
	}

	@Override
	public boolean isThreadSafe() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCreateProjectWithoutRepositorySupport() {
		return true;
	}

	@Override
	public boolean hasCreateRepositorySupport() {
		return false;
	}

	@Override
	public EList<? extends Object> getElements(InternalProject project) {
		Project domainProject = (Project) Activator.getClassMaker().getWorkspace().getProject(project.getName());
		return domainProject.getChildren();
	}

	@Override
	public void cloneProject(InternalProject projectToClone, InternalProject targetProject) {
		Project domainProjectToClone = Activator.getClassMaker().getWorkspace().getProject(projectToClone.getName());
		Project domainTargetProject = Activator.getClassMaker().getWorkspace().getProject(targetProject.getName());
		domainTargetProject.getChildren().addAll(EcoreUtil.copyAll(domainProjectToClone.getChildren()));
	}

	@Override
	public void handleLifecycle(ECPContainer context, LifecycleEvent event) {
		super.handleLifecycle(context, event);
		if (context instanceof InternalProject) {
			final InternalProject project = (InternalProject) context;
			switch (event) {
			case INIT:
				initProject(project);
				break;

			case CREATE:
				createProject(project);
				break;

			case DISPOSE:
				disposeProject(project);
				break;

			case REMOVE:
				removeProject(project);
				break;

			default:
				break;
			}
		}
	}

	protected void initProject(final InternalProject project) {
		if (initing.containsKey(project) && initing.get(project))
			return;
		initing.put(project, true);
		final EditingDomain editingDomain = project.getEditingDomain();
		editingDomain.getResourceSet().eAdapters().add(new EnterpriseDomainProjectObserver(project, this));
		final ClassMakerService classMaker = Activator.getClassMaker();
		if (classMaker != null) {
			Project domainProject = classMaker.getWorkspace().getProject(project.getName());
			if (domainProject != null) {
				try {
					IProgressMonitor monitor = null;
					if (getUIProvider() != null)
						monitor = getUIProvider().getAdapter(project, IProgressMonitor.class);
					else
						monitor = ClassMakerPlugin.getProgressMonitor();
					domainProject.open(monitor);
				} catch (CoreException e) {
					Activator.log(e);
				}
				if (domainProject.getRevision().getState().getDomainModel().getGenerated() instanceof EPackage)
					addVisiblePackage(project,
							(EPackage) domainProject.getRevision().getState().getDomainModel().getGenerated());
				domainProject.initialize(false);
				try {
					domainProject.load(false, true);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
			} else {
				boolean contribution = false;
				if (project.getProperties().getKeys().contains(PROP_CONTRIBUTION))
					contribution = Boolean.valueOf(project.getProperties().getValue(PROP_CONTRIBUTION));
				IProgressMonitor monitor = null;
				if (getUIProvider() == null)
					monitor = ClassMakerPlugin.getProgressMonitor();
				else
					monitor = getUIProvider().getAdapter(project, IProgressMonitor.class);
				if (contribution) {
					EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
					EPackage model = ecoreFactory.createEPackage();
					model.setName(project.getName());
					model.setNsPrefix(CodeGenUtil.capName(project.getName().replaceAll(" ", "").toLowerCase()));
					model.setNsURI("http://" + project.getName().replaceAll(" ", "") + "/1.0");
					EClass dummyEClass = ecoreFactory.createEClass();
					dummyEClass.setName("TheObject");
					EAttribute dummyEAttribute = ecoreFactory.createEAttribute();
					dummyEAttribute.setName("value");
					dummyEAttribute.setEType(EcorePackage.Literals.ESTRING);
					dummyEClass.getEStructuralFeatures().add(dummyEAttribute);
					EOperation op = ecoreFactory.createEOperation();
					op.setName("perform");
					op.setEType(EcorePackage.Literals.EINT);
					EAnnotation an = ecoreFactory.createEAnnotation();
					an.setSource("http://www.eclipse.org/emf/2002/GenModel");
					an.getDetails().put("body", "return 7;");
					op.getEAnnotations().add(an);
					EAnnotation invocation = ecoreFactory.createEAnnotation();
					invocation.setSource(ClassMakerService.INVOCATION_DELEGATE_URI);
					op.getEAnnotations().add(invocation);
					dummyEClass.getEOperations().add(op);
					model.getEClassifiers().add(dummyEClass);
					EAnnotation invocationDelegate = ecoreFactory.createEAnnotation();
					invocationDelegate.setSource(EcorePackage.eNS_URI);
					invocationDelegate.getDetails().put("invocationDelegates",
							ClassMakerService.INVOCATION_DELEGATE_URI);
					model.getEAnnotations().add(invocationDelegate);
					try {
						domainProject = classMaker.getWorkspace().createContribution(model, monitor);
						domainProject.getState().setEdit(true);
						domainProject.getState().setEditor(true);
						domainProject.addCompletionListener(new ProviderCompletionListener(project));
					} catch (CoreException e) {
						Activator.log(e);
					}
					addVisiblePackage(project, EcorePackage.eINSTANCE);
				} else
					try {
						domainProject = classMaker.getWorkspace().createProject(project.getName(), monitor);
						domainProject.initialize(true);
						propagatePackagesVisibility();
					} catch (CoreException e) {
						Activator.log(e);
					}
			}
			classMaker.getWorkspace().getResourceSet().eAdapters()
					.add(new AdapterFactoryEditingDomain.EditingDomainProvider(project.getEditingDomain()));

		}
		registerChangeListener(new ProviderChangeListener() {

			private Map<EObject, ECPProject> projects = new HashMap<EObject, ECPProject>();

			@Override
			public void preDelete(EObject objectToBeDeleted) {
				ECPContainer project = getModelContext(objectToBeDeleted);
				if (project != null && ECPProject.class.isInstance(project)) {
					projects.put(objectToBeDeleted, (ECPProject) project);
				}

			}

			@Override
			public void postDelete(EObject objectToBeDeleted) {
				if (!projects.containsKey(objectToBeDeleted))
					return;
				final Object[] eObjects = new Object[] { objectToBeDeleted };
				ECPUtil.getECPObserverBus().notify(ECPProjectContentChangedObserver.class)
						.objectsChanged(projects.get(objectToBeDeleted), (Collection<Object>) Arrays.asList(eObjects));
				projects.remove(objectToBeDeleted);
			}

			@Override
			public void notify(Notification notification) {
			}

			@Override
			public boolean canDelete(EObject objectToBeDeleted) {
				return true;
			}

		});
		initing.put(project, false);

	}

	protected void createProject(final InternalProject project) {
		project.open();
		final EditingDomain editingDomain = project.getEditingDomain();
		Adapter observer = new EnterpriseDomainProjectObserver(project, this);
		editingDomain.getResourceSet().eAdapters().add(observer);
		ClassMakerService classMaker = Activator.getClassMaker();
		if (classMaker != null)
			classMaker.getWorkspace().getResourceSet().eAdapters().add(observer);
	}

	@Override
	public ECPContainer getModelContext(Object element) {
		if (element instanceof ECPContainer) {
			return (ECPContainer) element;
		}

//		if (element instanceof ECPModelContextProvider) {
//			ECPContainer container = ((ECPModelContextProvider) element).getModelContext(element);
//			if (container.equals(element))
//				return null;
//			return container;
//		}

		if (element instanceof Project) {
			for (InternalProject project : getOpenProjects()) {
				if (project.getName().equals(((Project) element).getName())
						&& !project.getContents().equals(((Project) element).getChildren()))
					return project;
			}
		}

		if (element instanceof EList<?>)
			return getModelContext(((EList<?>) element).get(0));

		if (element instanceof ResourceAdapter)
			return getModelContext(((ResourceAdapter) element).getProject());

		if (element instanceof Resource) {
			Collection<InternalProject> projects = null;
			try {
				projects = getOpenProjects();
			} catch (RuntimeException e) {
				return null;
			}
			for (InternalProject project : projects) {
				Project domainProject = Activator.getClassMaker().getWorkspace().getProject((Resource) element);
				if (domainProject != null && domainProject.getProjectName().equals(project.getName()))
					return project;
			}
		}

		if (element instanceof EObject) {
			if (((EObject) element).eContainer() instanceof ECPContainer) {
				return getModelContext(((EObject) element).eContainer().eResource());
			}
			return getModelContext(((EObject) element).eResource());
		}

		return null;
	}

	protected void disposeProject(InternalProject project) {
		initing.remove(project);
		ClassMakerService service = Activator.getClassMaker();
		if (service != null) {
			Project domainProject = service.getWorkspace().getProject(project.getName());
			if (domainProject != null)
				try {
					IProgressMonitor monitor = null;
					if (getUIProvider() == null)
						monitor = ClassMakerPlugin.getProgressMonitor();
					else
						monitor = getUIProvider().getAdapter(project, IProgressMonitor.class);
					Object object = domainProject.getChildren().get(0);
					if (object instanceof Resource)
						((Resource) object).setTrackingModification(false);
					domainProject.close(monitor);
				} catch (CoreException e) {
					Activator.log(e);
				}
		}

	}

	protected void removeProject(InternalProject project) {
		initing.remove(project);
		Project domainProject = (Project) Activator.getClassMaker().getWorkspace().getProject(project.getName());
		try {
			if (domainProject != null)
				domainProject.delete(getUIProvider().getAdapter(project, IProgressMonitor.class));
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Set<EPackage> getUnsupportedEPackages(Collection<EPackage> packages, InternalRepository repository) {
		Set<EPackage> results = new HashSet<EPackage>();
		results.addAll(packages);
		for (InternalProject project : getOpenProjects())
			results.removeAll(getVisiblePackages(project));
		return results;
	}

	public Set<EPackage> getVisiblePackages(InternalProject project) {
		Set<EPackage> results = new HashSet<EPackage>();
		for (String packageNsURI : visiblePackagesToClasses.keySet()) {
			EPackage ePackage = project.getEditingDomain().getResourceSet().getPackageRegistry()
					.getEPackage(packageNsURI);
			results.add(ePackage);
		}
		return results;
	}

	@Override
	public boolean contains(InternalProject project, Object object) {
		if (object instanceof EObject)
			return Activator.getClassMaker().getWorkspace().contains((EObject) object).getValue() == Stage.LOADED_VALUE;
		return super.contains(project, object);
	}

	@Override
	public void fillChildren(ECPContainer context, Object parent, InternalChildrenList childrenList) {
		if (parent instanceof ECPRepository) {
			childrenList.addChildren(Activator.getClassMaker().getWorkspace().getProjects());
		} else if (parent instanceof ECPProject) {
			final ECPProject project = (ECPProject) parent;
			final Project domainProject = Activator.getClassMaker().getWorkspace().getProject(project.getName());
			if (domainProject != null && !domainProject.getChildren().isEmpty())
				childrenList.addChildren(domainProject.getChildren());
		} else if (parent instanceof Resource) {
			Resource resource = (Resource) parent;
			childrenList.addChildren(resource.getContents());
		} else if (parent instanceof EObject) {
			final EObject eObject = (EObject) parent;
			childrenList.addChildren(eObject.eContents());
		} else {
			super.fillChildren(context, parent, childrenList);
		}
	}

	@Override
	public <T> T getAdapter(Object adaptable, Class<T> adapterType) {
		final T adapter = EnterpriseDomainProviderAdapterFactory.adapt(adaptable, adapterType);
		if (adapter != null)
			return adapter;
		return super.getAdapter(adaptable, adapterType);
	}

	@Override
	public EditingDomain createEditingDomain(final InternalProject project) {
		commandStack = (BasicCommandStack) createCommandStack(project);
		final EditingDomain editingDomain = new AdapterFactoryEditingDomain(ENTERPRISE_DOMAIN_ADAPTER_FACTORY,
				commandStack, Activator.getClassMaker().getWorkspace().getResourceSet());
		ECPModelContextAdapter a = new ECPModelContextAdapter(project);
		if (!editingDomain.getResourceSet().eAdapters().contains(a))
			editingDomain.getResourceSet().eAdapters().add(a);
		return editingDomain;
	}

	public class ProviderCompletionListener extends CompletionListenerImpl {

		private InternalProject project;

		public ProviderCompletionListener(InternalProject project) {
			this.project = project;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void completed(final Project result) throws Exception {
			result.setSelectRevealHandler(getUIProvider().getAdapter(project, SelectRevealHandler.class));
			result.addResourceChangeListener(new ResourceChangeListenerImpl() {

				@Override
				public void changed(Notification notification) throws Exception {
					project.notifyObjectsChanged(((Collection<Object>) (Collection<?>) Arrays.asList(project)), true);
				}

			});
			if (result.getDomainModel().getGenerated() instanceof EPackage)
				addVisiblePackage(project, (EPackage) result.getDomainModel().getGenerated());
			project.notifyObjectsChanged((Collection<Object>) (Collection<?>) Arrays.asList(project), true);
			Collection<ECPRepository> repositories = (Collection<ECPRepository>) (Collection<?>) Arrays
					.asList(project.getRepository());
			ECPUtil.getECPObserverBus().notify(ECPRepositoriesChangedObserver.class).repositoriesChanged(repositories,
					repositories);
		}
	}

	@Override
	public Notifier getRoot(InternalProject project) {
		return Activator.getClassMaker().getWorkspace().getProject(project.getName());
	}

	public void addVisiblePackage(InternalProject project, EPackage ePackage) {
		HashSet<String> eClasses = null;
		if (ePackage != null && visiblePackagesToClasses.containsKey(ePackage.getNsURI())) {
			eClasses = visiblePackagesToClasses.get(ePackage.getNsURI());
			eClasses.clear();
		} else
			eClasses = new HashSet<String>();
		if (ePackage != null) {
			for (EClassifier eClass : ePackage.getEClassifiers())
				if (eClass instanceof EClass)
					eClasses.add(eClass.getName());
			visiblePackagesToClasses.put(ePackage.getNsURI(), eClasses);
		}
		propagatePackagesVisibility();
	}

	private void propagatePackagesVisibility() {
		for (InternalProject project : getOpenProjects())
			for (String ePackageNsURI : visiblePackagesToClasses.keySet()) {
				EPackage eP = project.getEditingDomain().getResourceSet().getPackageRegistry()
						.getEPackage(ePackageNsURI);
				Set<EPackage> ePs = new HashSet<EPackage>();
				ePs.addAll(project.getVisiblePackages());
				ePs.add(eP);
				project.setVisiblePackages(ePs);
				for (Map.Entry<String, HashSet<String>> eClassNames : visiblePackagesToClasses.entrySet())
					if (eClassNames.getKey().equals(ePackageNsURI))
						for (String eClassName : eClassNames.getValue()) {
							Set<EClass> eCls = new HashSet<EClass>();
							eCls.addAll(project.getVisibleEClasses());
							eCls.add((EClass) eP.getEClassifier(eClassName));
							project.setVisibleEClasses(eCls);
						}
			}
	}

	private class EnterpriseDomainResourceListener implements IResourceChangeListener {

		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			final Collection<Resource> changedResources = new ArrayList<Resource>();
			final Collection<Resource> removedResources = new ArrayList<Resource>();
			final IResourceDelta delta = event.getDelta();

			if (delta == null) {
				return;
			}

			try {
				delta.accept(new EnterpriseDomainEditorResourceDeltaVisitor(removedResources, changedResources));
			} catch (final CoreException ex) {
				Activator.log(ex);
			}
			if (changedResources.isEmpty() && removedResources.isEmpty()) {
				return;
			}
			String projectName = ResourceUtils.parseProjectName(changedResources.iterator().next().getURI());
			ECPProject project = ECPUtil.getECPProjectManager().getProject(projectName);
			if (getUIProvider() != null)
				((IResourceHandler) getUIProvider().getAdapter(this, IResourceHandler.class))
						.handleResourceChange(project, changedResources, removedResources);
		}

	}

	private final class EnterpriseDomainEditorResourceDeltaVisitor implements IResourceDeltaVisitor {
		private final Collection<Resource> removedResources;
		private final Collection<Resource> changedResources;

		EnterpriseDomainEditorResourceDeltaVisitor(Collection<Resource> removedResources,
				Collection<Resource> changedResources) {
			this.removedResources = removedResources;
			this.changedResources = changedResources;
		}

		@Override
		public boolean visit(final IResourceDelta delta) {
			if (delta.getResource().getType() == IResource.FILE
					&& (delta.getKind() == IResourceDelta.REMOVED || delta.getKind() == IResourceDelta.CHANGED)) {
				final ResourceSet resourceSet = Activator.getClassMaker().getWorkspace().getResourceSet();
				if (resourceSet == null) {
					return false;
				}
				Resource resource = null;

				final URI uri = URI.createPlatformResourceURI(delta.getFullPath().toString(), true);
				resource = resourceSet.getResource(uri, false);
				if (resource == null) {
					try {
						final URL fileURL = FileLocator.resolve(new URL(uri.toString()));
						resource = resourceSet.getResource(URI.createFileURI(fileURL.getPath()), false);
					} catch (final IOException ex) {
						return false;
					}
				}

				if (resource != null) {
					if (delta.getKind() == IResourceDelta.REMOVED) {
						removedResources.add(resource);
					} else {
						changedResources.add(resource);
					}
				}
				return false;
			} else if (delta.getResource() != null && delta.getResource().getType() == IResource.PROJECT
					&& delta.getKind() == IResourceDelta.REMOVED) {
				try {
					ECPProject project = ECPUtil.getECPProjectManager().getProject(delta.getResource().getName());
					project.delete();
				} catch (Exception e) {
					ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
				}
				return false;
			}
			return true;
		}
	}

	public class EnterpriseDomainProjectObserver extends EContentAdapter {

		private final InternalProject project;
		private final EnterpriseDomainProvider provider;

		public EnterpriseDomainProjectObserver(InternalProject project, EnterpriseDomainProvider provider) {
			this.project = project;
			this.provider = provider;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);

			if (notification.getNotifier() instanceof EObject) {
				provider.notifyProviderChangeListeners(notification);
				final EObject eObject = (EObject) notification.getNotifier();
				final Object[] eObjects = new Object[] { eObject };
				project.notifyObjectsChanged((Collection<Object>) Arrays.asList(eObjects), false);

				final Object feature = notification.getFeature();
				if (feature instanceof EReference) {
					final EReference eReference = (EReference) feature;

					if (eReference.isContainment() && notification.getNewValue() instanceof EObject) {
						project.notifyObjectsChanged(Collections.singleton(notification.getNewValue()), true);
					}

				} else if (feature instanceof EAttribute) {
					final EAttribute eAttribute = (EAttribute) feature;

					if (notification.getNewValue() instanceof EObject) {
						project.notifyObjectsChanged(Collections.singleton(notification.getNewValue()), true);
					}

				} else if (feature instanceof EOperation) {
					final EOperation eOperation = (EOperation) feature;

					if (notification.getNewValue() instanceof EObject) {
						project.notifyObjectsChanged(Collections.singleton(notification.getNewValue()), true);
					}
				}
				return;
			}

			if (notification.getNotifier() instanceof Resource) {
				project.notifyObjectsChanged(
						(Collection<Object>) (Collection<?>) Collections.singleton(notification.getNotifier()), true);
			}
//			Diagnostician.INSTANCE
//					.validate(ClassMakerPlugin.getClassMaker().getWorkspace().getProject(project.getName()));
			// if (notification.getNotifier() instanceof Notifier) {
			// provider.notifyProviderChangeListeners(notification);
			//
			// if (notification.getNewValue() instanceof EObject) {
			// project.notifyObjectsChanged(Collections.singleton(notification.getNewValue()),
			// true);
			// }
			// if ((notification.getEventType() == ((Notification.REMOVE |
			// Notification.REMOVE_MANY)
			// & notification.getEventType())) && notification.getOldValue()
			// instanceof EObject) {
			// Collection<Object> oldObjects = null;
			// if (notification.getOldValue() instanceof Collection<?>)
			// oldObjects = (Collection<Object>) notification.getOldValue();
			// else
			// oldObjects = Collections.singleton(notification.getOldValue());
			// ECPUtil.getECPObserverBus().notify(ECPProjectContentChangedObserver.class).objectsChanged(project,
			// oldObjects);
			// }
			// }
		}

	}

	@Override
	public boolean isDirty(InternalProject project) {
		if (commandStack != null)
			return commandStack.isSaveNeeded();
		Project domainProject = Activator.getClassMaker().getWorkspace().getProject(project.getName());
		return domainProject.isDirty();
	}

	@Override
	public void doSave(InternalProject project) {
		// IProgressMonitor monitor = null;
		// try {
		// monitor = getUIProvider().getAdapter(project, IProgressMonitor.class);
		// final Semaphore saved = new Semaphore(0);
		// CompletionListener saveListener = new CompletionListenerImpl() {
		//
		// @Override
		// public void completed(Project result) {
		// saved.release();
		// }
		//
		// };
		ClassMakerService classMaker = Activator.getClassMaker();
		Project domainProject = classMaker.getWorkspace().getProject(project.getName());
		// Blueprint blueprint = classMaker.createBlueprint();
		// blueprint.getCompletionListeners().add(saveListener);
		domainProject.getState().saveResource();
		// blueprint.setDynamicModel(
		// ((Contribution)
		// domainProject).getContribution().getDomainModel().getDynamic());
		// classMaker.make(blueprint, monitor);
		// try {
		// saved.acquire();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// if (monitor != null)
		// monitor.setCanceled(true);
		// }
		// domainProject.removeCompletionListener(saveListener);
		((BasicCommandStack) project.getEditingDomain().getCommandStack()).saveIsDone();
		// } catch (CoreException e) {
		// e.printStackTrace();
		// if (monitor != null)
		// monitor.setCanceled(true);
		// }
		super.doSave(project);
	}

	@Override
	public void doDelete(InternalProject project, Collection<Object> objects) {
		final InternalProject p = project;
		final Collection<Object> o = objects;
		final Command changeCommand = new ChangeCommand(project.getEditingDomain().getResourceSet()) {
			@Override
			protected void doExecute() {
				Project domainProject = Activator.getClassMaker().getWorkspace().getProject(p.getName());
				if (o.isEmpty())
					try {
						domainProject.delete(getUIProvider().getAdapter(p, IProgressMonitor.class));
					} catch (CoreException e) {
						Activator.log(e);
					}
				else
					domainProject.delete(ECollections.asEList(o));
			}
		};
		if (changeCommand.canExecute()) {
			project.getEditingDomain().getCommandStack().execute(changeCommand);
			return;
		}
	}

}
