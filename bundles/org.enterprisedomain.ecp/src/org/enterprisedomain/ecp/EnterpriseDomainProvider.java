/**
 * Copyright 2012-2016 Kyrill Zotkin
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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
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
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.core.ECPRepository;
import org.eclipse.emf.ecp.core.util.ECPContainer;
import org.eclipse.emf.ecp.core.util.ECPModelContextAdapter;
import org.eclipse.emf.ecp.core.util.ECPUtil;
import org.eclipse.emf.ecp.core.util.observer.ECPProjectContentChangedObserver;
import org.eclipse.emf.ecp.spi.core.DefaultProvider;
import org.eclipse.emf.ecp.spi.core.InternalProject;
import org.eclipse.emf.ecp.spi.core.InternalProvider;
import org.eclipse.emf.ecp.spi.core.ProviderChangeListener;
import org.eclipse.emf.ecp.spi.core.util.InternalChildrenList;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.enterprisedomain.classmaker.ClassMakerPlant;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.CompletionListenerImpl;
import org.enterprisedomain.classmaker.util.ClassMakerAdapterFactory;

public class EnterpriseDomainProvider extends DefaultProvider {

	public static final AdapterFactory ENTERPRISE_DOMAIN_ADAPTER_FACTORY = new ComposedAdapterFactory(
			new AdapterFactory[] { new ClassMakerAdapterFactory(), InternalProvider.EMF_ADAPTER_FACTORY });

	public static final String NAME = "org.enterprisedomain.ecp.provider";

	public static final String PROP_CONTRIBUTION = "isContribution";

	public EnterpriseDomainProvider() {
		super(NAME);
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

	protected void initProject(InternalProject project) {
		final EditingDomain editingDomain = project.getEditingDomain();
		editingDomain.getResourceSet().eAdapters().add(new EnterpriseDomainProjectObserver(project, this));
		ClassMakerPlant plant = Activator.getClassMaker();
		if (plant != null) {
			Project domainProject = plant.getWorkspace().getProject(project.getName());
			if (domainProject != null)
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

	protected void createProject(InternalProject project) {
		Project domainProject = null;
		domainProject = Activator.getClassMaker().getWorkspace().getProject(project.getName());
		if (domainProject != null)
			return;
		boolean contribution = false;
		if (project.getProperties().getKeys().contains(PROP_CONTRIBUTION))
			contribution = Boolean.valueOf(project.getProperties().getValue(PROP_CONTRIBUTION));
		if (contribution) {
			EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
			EPackage model = ecoreFactory.createEPackage();
			model.setName(project.getName());
			model.setNsPrefix(CodeGenUtil.capName(project.getName().replaceAll(" ", "").toLowerCase()));
			model.setNsURI("http://" + project.getName().replaceAll(" ", "") + "/1.0");
			EClass ec = ecoreFactory.createEClass();
			ec.setName("Class0");
			EAttribute a = ecoreFactory.createEAttribute();
			a.setName("value");
			a.setEType(EcorePackage.Literals.EJAVA_OBJECT);
			ec.getEStructuralFeatures().add(a);
			model.getEClassifiers().add(ec);
			try {
				Activator.getClassMaker().produce(model, getUIProvider().getAdapter(project, IProgressMonitor.class));
				domainProject = Activator.getClassMaker().getWorkspace().getContribution(model);
			} catch (CoreException e) {
				Activator.log(e);
			}
			project.getVisiblePackages().add(EcorePackage.eINSTANCE);
			((Contribution) domainProject).addCompletionListener(new VisiblePackagesListener(project));
		} else
			domainProject = Activator.getClassMaker().getWorkspace().getProject(project.getName());
		domainProject.getWorkspace().getResourceSet().eAdapters()
				.add(new AdapterFactoryEditingDomain.EditingDomainProvider(project.getEditingDomain()));
	}

	protected void disposeProject(InternalProject project) {
		ClassMakerPlant plant = Activator.getClassMaker();
		if (plant != null) {
			Project domainProject = plant.getWorkspace().getProject(project.getName());
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
	public boolean contains(InternalProject project, Object object) {
		if (object instanceof EObject)
			return Activator.getClassMaker().getWorkspace().contains((EObject) object);
		return super.contains(project, object);
	}

	@Override
	public void fillChildren(ECPContainer context, Object parent, InternalChildrenList childrenList) {
		if (parent instanceof ECPRepository) {
		} else if (parent instanceof ECPProject) {
			final ECPProject project = (ECPProject) parent;
			final Project domainProject = Activator.getClassMaker().getWorkspace().getProject(project.getName());
			childrenList.addChildren(domainProject.getChildren());
		} else if (parent instanceof ResourceAdapter) {
			final ResourceAdapter resourceAdapter = (ResourceAdapter) parent;
			childrenList.addChildren(resourceAdapter.getResource().getContents());
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

	private class VisiblePackagesListener extends CompletionListenerImpl {

		private InternalProject project;

		public VisiblePackagesListener(InternalProject project) {
			this.project = project;
		}

		@Override
		public void completed(Project result) {
			project.getVisiblePackages().add(project.getEditingDomain().getResourceSet().getPackageRegistry()
					.getEPackage(((Contribution) result).getDomainModel().getGenerated().getNsURI()));
		}

	};

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
		return ((BasicCommandStack) project.getEditingDomain().getCommandStack()).isSaveNeeded();
	}

	@Override
	public void doSave(InternalProject project) {
		IProgressMonitor monitor = null;
		try {
			monitor = getUIProvider().getAdapter(project, IProgressMonitor.class);
			// final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
			// saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
			// Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
			final Semaphore saved = new Semaphore(0);
			CompletionListener saveListener = new CompletionListenerImpl() {

				@Override
				public void completed(Project result) {
					saved.release();
				}

			};
			Project domainProject = Activator.getClassMaker().getWorkspace().getProject(project.getName());
			if (domainProject instanceof Contribution) {
				Contribution contrib = (Contribution) domainProject;
				contrib.addCompletionListener(saveListener);
				contrib.make(monitor);
				try {
					saved.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
					if (monitor != null)
						monitor.setCanceled(true);
				}
				contrib.removeCompletionListener(saveListener);
			} else
				domainProject.make(monitor);
			((BasicCommandStack) project.getEditingDomain().getCommandStack()).saveIsDone();
		} catch (CoreException e) {
			e.printStackTrace();
			if (monitor != null)
				monitor.setCanceled(true);
		}
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
