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
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.core.ECPRepository;
import org.eclipse.emf.ecp.core.util.ECPContainer;
import org.eclipse.emf.ecp.core.util.ECPModelContextAdapter;
import org.eclipse.emf.ecp.core.util.ECPModelContextProvider;
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
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.enterprisedomain.classmaker.Blueprint;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.SelectRevealHandler;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.CompletionListenerImpl;
import org.enterprisedomain.classmaker.impl.ResourceChangeListenerImpl;
import org.enterprisedomain.classmaker.util.ClassMakerAdapterFactory;

public class EnterpriseDomainProvider extends DefaultProvider {

	public static final AdapterFactory ENTERPRISE_DOMAIN_ADAPTER_FACTORY = new ComposedAdapterFactory(
			new AdapterFactory[] { new ClassMakerAdapterFactory(), InternalProvider.EMF_ADAPTER_FACTORY });

	public static final String NAME = "org.enterprisedomain.ecp.provider";

	public static final String PROP_CONTRIBUTION = "isContribution";

	private static Set<String> visiblePackages = new HashSet<String>();

	private Blueprint blueprint;

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
			Project domainProject = Activator.getClassMaker().getWorkspace().getProject(resource);
			if (domainProject != null && !(domainProject instanceof Contribution)) {
				try {
					resource.save(Collections.emptyMap());
				} catch (final IOException ex) {
					Activator.log(ex);
				}
			}
			InternalProject project = (InternalProject) getModelContext(resource);
			if (project != null)
				project.notifyObjectsChanged((Collection<Object>) (Collection<?>) Arrays.asList(project), true);
		}

	};

	public EnterpriseDomainProvider() {
		super(NAME);
		Activator.getClassMaker().getWorkspace().getResourceSet().eAdapters().add(adapter);
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
		// TODO Auto-generated method stub

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
				addVisiblePackages(project);
				domainProject.initialize(false);
			} else {
				boolean contribution = false;
				if (project.getProperties().getKeys().contains(PROP_CONTRIBUTION))
					contribution = Boolean.valueOf(project.getProperties().getValue(PROP_CONTRIBUTION));
				IProgressMonitor monitor = getUIProvider().getAdapter(project, IProgressMonitor.class);
				if (contribution) {
					EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
					EPackage model = ecoreFactory.createEPackage();
					model.setName(project.getName());
					model.setNsPrefix(CodeGenUtil.capName(project.getName().replaceAll(" ", "").toLowerCase()));
					model.setNsURI("http://" + project.getName().replaceAll(" ", "") + "/1.0");
					EClass dummyEClass = ecoreFactory.createEClass();
					dummyEClass.setName("MyObject");
					EAttribute dummyEAttribute = ecoreFactory.createEAttribute();
					dummyEAttribute.setName("value");
					dummyEAttribute.setEType(EcorePackage.Literals.EJAVA_OBJECT);
					dummyEClass.getEStructuralFeatures().add(dummyEAttribute);
					model.getEClassifiers().add(dummyEClass);
					try {
						blueprint = classMaker.createBlueprint();
						blueprint.setDynamicModel(model);
						blueprint.getCompletionListeners().add(new ProviderCompletionListener(project));
						ClassMakerPlugin.runWithProgress(new IRunnableWithProgress() {

							@Override
							public void run(IProgressMonitor monitor)
									throws InvocationTargetException, InterruptedException {
								try {
									classMaker.make(blueprint, monitor);
								} catch (CoreException e) {
									throw new InvocationTargetException(e);
								}
							}

						});
						domainProject = classMaker.getWorkspace().getContribution(model);
					} catch (InvocationTargetException e) {
						Activator.log(e.getTargetException());
					} catch (InterruptedException e) {
						monitor.setCanceled(true);
						return;
					}
					project.getVisiblePackages().add(EcorePackage.eINSTANCE);
				} else
					try {
						domainProject = classMaker.getWorkspace().createProject(project.getName(), monitor);
						addVisiblePackages(project);
						domainProject.initialize(true);
					} catch (CoreException e) {
						Activator.log(e);
					}
				classMaker.getWorkspace().getResourceSet().eAdapters()
						.add(new AdapterFactoryEditingDomain.EditingDomainProvider(project.getEditingDomain()));
			}

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
	}

	@SuppressWarnings("unchecked")
	@Override
	public ECPContainer getModelContext(Object element) {
		if (element instanceof ECPContainer) {
			return (ECPContainer) element;
		}

		if (element instanceof ECPModelContextProvider) {
			return ((ECPModelContextProvider) element).getModelContext(element);
		}

		if (element instanceof Project) {
			for (InternalProject project : getOpenProjects()) {
				if (project.getName().equals(((Project) element).getName()))
					return project;
			}
		}

		if (element instanceof EList<?>)
			return getModelContext(((EList<?>) element).get(0));

		if (element instanceof ResourceAdapter)
			return getModelContext(((ResourceAdapter) element).getProject());

		if (element instanceof Resource) {
			for (InternalProject project : getOpenProjects()) {
				Project domainProject = Activator.getClassMaker().getWorkspace().getProject((Resource) element);
				if (domainProject != null && project.getName().equals(domainProject.getName()))
					return project;
			}
			element = ((Resource) element).getResourceSet();
		}

		if (element instanceof ResourceSet) {
			ResourceSet resourceSet = Activator.getClassMaker().getWorkspace().getResourceSet();
			ECPContainer context = getModelContextFromAdapter(resourceSet);
			if (context != null && context instanceof ECPProject) {
				for (InternalProject project : getOpenProjects()) {
					Project domainProject = Activator.getClassMaker().getWorkspace()
							.getProject(((ECPProject) context).getName());
					if (project.getName().equals(domainProject.getName()))
						return project;
				}
			}
		}

		if (element instanceof EObject) {
			return getModelContext(((EObject) element).eResource());
		}

		return null;
	}

	private void addVisiblePackages(InternalProject project) {
		for (EPackage visiblePackage : getVisiblePackages(project)) {
			project.getVisiblePackages().add(visiblePackage);
		}
	}

	protected void disposeProject(InternalProject project) {
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
					domainProject.close(monitor);
				} catch (CoreException e) {
					Activator.log(e);
				}
		}

	}

	protected void removeProject(InternalProject project) {
		Project domainProject = (Project) Activator.getClassMaker().getWorkspace().getProject(project.getName());
		try {
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
		for (String packageNsURI : visiblePackages) {
			EPackage ePackage = project.getEditingDomain().getResourceSet().getPackageRegistry()
					.getEPackage(packageNsURI);
			results.add(ePackage);
		}
		return results;
	}

	@Override
	public boolean contains(InternalProject project, Object object) {
		if (object instanceof EObject)
			return Activator.getClassMaker().getWorkspace().contains((EObject) object);
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
				if (domainProject instanceof Contribution && domainProject.getChildren().get(0) instanceof Resource)
					childrenList.addChildren(((Resource) domainProject.getChildren().get(0)).getContents());
				else if (domainProject instanceof Project && !(domainProject instanceof Contribution))
					childrenList.addChildren(domainProject.getChildren());
			// } else if (parent instanceof Resource) {
			// Resource resource = (Resource) parent;
			// childrenList.addChildren(resource.getContents());
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
		final BasicCommandStack commandStack = new BasicCommandStack();
		final EditingDomain editingDomain = new AdapterFactoryEditingDomain(ENTERPRISE_DOMAIN_ADAPTER_FACTORY,
				commandStack, Activator.getClassMaker().getWorkspace().getResourceSet());
		editingDomain.getResourceSet().eAdapters().add(new ECPModelContextAdapter(project));
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

				@SuppressWarnings("unchecked")
				@Override
				public void changed(Notification notification) throws Exception {
					project.notifyObjectsChanged(((Collection<Object>) (Collection<?>) Arrays.asList(project)), true);
				}

			});
			visiblePackages.add(((Contribution) result).getDomainModel().getGenerated().getNsURI());
			addVisiblePackages(project);
			project.notifyObjectsChanged((Collection<Object>) (Collection<?>) Arrays.asList(project), true);
			Collection<ECPRepository> repositories = (Collection<ECPRepository>) (Collection<?>) Arrays
					.asList(project.getRepository());
			ECPUtil.getECPObserverBus().notify(ECPRepositoriesChangedObserver.class).repositoriesChanged(repositories,
					repositories);
		}
	}

	@Override
	public Notifier getRoot(InternalProject project) {
		return Activator.getClassMaker().getWorkspace();
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
		boolean result = false;
		for (Object child : Activator.getClassMaker().getWorkspace().getProject(project.getName()).getChildren())
			if (child instanceof Resource)
				result |= ((Resource) child).isModified();
			else if (child instanceof EObject)
				result |= ((EObject) child).eResource().isModified();
		return result;
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
		if (domainProject instanceof Contribution) {
			((Contribution) domainProject).getState().saveResource();
			// blueprint.setDynamicModel(
			// ((Contribution)
			// domainProject).getContribution().getDomainModel().getDynamic());
		}
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
	public void doDelete(final InternalProject project, final Collection<Object> objects) {
		final Command changeCommand = new ChangeCommand(project.getEditingDomain().getResourceSet()) {
			@Override
			protected void doExecute() {
				Project domainProject = Activator.getClassMaker().getWorkspace().getProject(project.getName());
				if (objects.isEmpty())
					try {
						domainProject.delete(getUIProvider().getAdapter(project, IProgressMonitor.class));
					} catch (CoreException e) {
						Activator.log(e);
					}
				else
					domainProject.delete(ECollections.newBasicEList(objects));
			}
		};
		if (changeCommand.canExecute()) {
			project.getEditingDomain().getCommandStack().execute(changeCommand);
			return;
		}
	}

}
