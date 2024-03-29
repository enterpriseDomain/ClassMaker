/**
 * Copyright 2012-2021 Kyrill Zotkin
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
package org.enterprisedomain.ecp.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.core.ECPRepository;
import org.eclipse.emf.ecp.core.util.ECPContainer;
import org.eclipse.emf.ecp.core.util.ECPProperties;
import org.eclipse.emf.ecp.core.util.ECPUtil;
import org.eclipse.emf.ecp.core.util.observer.ECPProjectContentChangedObserver;
import org.eclipse.emf.ecp.spi.core.InternalProject;
import org.eclipse.emf.ecp.spi.core.InternalProvider;
import org.eclipse.emf.ecp.spi.ui.CompositeStateObserver;
import org.eclipse.emf.ecp.spi.ui.DefaultUIProvider;
import org.eclipse.emf.ecp.ui.tester.ECPSavePropertySource;
import org.eclipse.emf.ecp.ui.tester.SaveButtonEnablementObserver;
import org.eclipse.emf.ecp.ui.views.ModelExplorerView;
import org.eclipse.emf.ecp.ui.views.ModelRepositoriesView;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.progress.ProgressManager;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.SelectRevealHandler;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.core.IRunWrapper;
import org.enterprisedomain.classmaker.core.IRunnerWithProgress;
import org.enterprisedomain.classmaker.provider.ClassMakerItemProviderAdapterFactory;
import org.enterprisedomain.classmaker.util.ClassMakerAdapterFactory;
import org.enterprisedomain.ecp.EnterpriseDomainProvider;
import org.enterprisedomain.ecp.IResourceHandler;
import org.enterprisedomain.ecp.ui.actions.ExecuteOperationAction;

@SuppressWarnings("restriction")
public class EnterpriseDomainUIProvider extends DefaultUIProvider implements IResourceHandler {

	public static final ComposeableAdapterFactory ENTERPRISE_DOMAIN_ITEM_PROVIDER_ADAPTER_FACTORY = new ComposedAdapterFactory(
			new AdapterFactory[] { new ClassMakerItemProviderAdapterFactory(), new ClassMakerAdapterFactory(),
					InternalProvider.EMF_ADAPTER_FACTORY });

	private static final ILabelProvider ENTERPRISE_DOMAIN_LABEL_PROVIDER = new DecoratingLabelProvider(
			new AdapterFactoryLabelProvider(ENTERPRISE_DOMAIN_ITEM_PROVIDER_ADAPTER_FACTORY), new StageDecorator());

	public static final String PROP_DISABLE_CONTRIBUTION = "disableContribution";

	public class LabelProviderListener implements ILabelProviderListener {

		@Override
		public void labelProviderChanged(LabelProviderChangedEvent event) {
			IViewPart reposView = null;
			try {
				IWorkbench workbench = (IWorkbench) PlatformUI.getWorkbench();
				if (workbench != null) {
					IWorkbenchWindow workbenchWindow = workbench.getWorkbenchWindows()[0];

					if (workbenchWindow != null) {
						IWorkbenchPage page = workbenchWindow.getActivePage();
						reposView = page.findView("org.eclipse.emf.ecp.ui.ModelRepositoriesView");
					}
				}
				if (reposView instanceof ModelRepositoriesView) {
					final ModelRepositoriesView modelRepos = (ModelRepositoriesView) reposView;
					Object element = event.getElement();
					if (element instanceof EObject)
						ClassMakerPlugin.runWithProgress(new IRunnableWithProgress() {

							@Override
							public void run(IProgressMonitor monitor)
									throws InvocationTargetException, InterruptedException {
								modelRepos.getViewer().refresh((EObject) element, true);
							}
						});
				}
			} catch (Exception e) {
				Activator.log(e);
			}
		}

	}

	private Button isContributionButton;
	private boolean isContribution;

	private SelectRevealHandler selectRevealHandler = new SelectResourceHandler();

	private Composite control;

	private final IResourceChangeListener resourceChangeListener = new EnterpriseDomainUIResourceListener();

	public EnterpriseDomainUIProvider() {
		super(EnterpriseDomainProvider.NAME);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener);
		ECPUtil.getECPObserverBus().register(new ECPProjectContentChangedObserver() {

			@Override
			public Collection<Object> objectsChanged(ECPProject project, Collection<Object> objectsChanged) {
				IViewPart modelExplorerView = null;
				try {
					IWorkbench workbench = (IWorkbench) PlatformUI.getWorkbench();
					if (workbench != null) {
						IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();

						if (workbenchWindow != null) {
							IWorkbenchPage page = workbenchWindow.getActivePage();
							modelExplorerView = page.showView("org.eclipse.emf.ecp.ui.ModelExplorerView");
						}
					}
					if (modelExplorerView instanceof ModelExplorerView) {
						final ModelExplorerView modelExplorer = (ModelExplorerView) modelExplorerView;
						Object object = objectsChanged.iterator().next();
						if (object instanceof EObject && modelExplorer.getViewer() != null)
							modelExplorer.getViewer().refresh(((EObject) object).eContainer(), true);
					}
				} catch (Exception e) {
					Activator.log(e);
				}
				return objectsChanged;
			}

		});

		ENTERPRISE_DOMAIN_LABEL_PROVIDER.addListener(new LabelProviderListener());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptable, Class<T> adapterType) {
		if (SelectRevealHandler.class.isAssignableFrom(adapterType))
			return (T) selectRevealHandler;
		if (IProgressMonitor.class.isAssignableFrom(adapterType))
			return (T) ClassMakerPlugin.getProgressMonitor();
		if (ComposeableAdapterFactory.class.isAssignableFrom(adapterType))
			return (T) ENTERPRISE_DOMAIN_ITEM_PROVIDER_ADAPTER_FACTORY;
		if (IResourceHandler.class.isAssignableFrom(adapterType))
			return (T) this;
		if (DefaultUIProvider.class.isAssignableFrom(adapterType))
			return (T) this;
		return (T) getProvider().getAdapter(adaptable, adapterType);
	}

	@Override
	public Control createNewProjectUI(Composite parent, final CompositeStateObserver observer,
			final ECPProperties projectProperties) {
		control = new Composite(parent, SWT.NONE);
		control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		control.setLayout(new GridLayout(1, false));

		isContributionButton = new Button(control, SWT.CHECK);
		isContributionButton.setText("Software Contribution");
		isContributionButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		isContribution = Boolean.valueOf(projectProperties.getValue(EnterpriseDomainProvider.PROP_CONTRIBUTION));
		isContributionButton.setSelection(isContribution);
		isContributionButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				isContribution = !isContribution;
				projectProperties.addProperty(EnterpriseDomainProvider.PROP_CONTRIBUTION,
						String.valueOf(isContribution));
				super.widgetSelected(e);
				observer.compositeChangedState(control, true, projectProperties);
			}

		});
		if (Boolean.getBoolean(PROP_DISABLE_CONTRIBUTION))
			isContributionButton.setEnabled(false);

		ClassMakerPlugin.setPreviousProgressProvider(ProgressManager.getInstance());
		observer.compositeChangedState(control, true, projectProperties);
		return control;
	}

	public void handleResourceChange(final ECPProject project, final Collection<Resource> changedResources,
			final Collection<Resource> removedResources) {
		if (!getProvider().isDirty((InternalProject) project)) {
			final Display display = Display.getCurrent() != null ? Display.getCurrent() : Display.getDefault();
			display.asyncExec(new Runnable() {
				@Override
				public void run() {
					ResourceSet resourceSet = Activator.getClassMaker().getWorkspace().getResourceSet();
					if (resourceSet == null || display.isDisposed()) {
						return;
					}
					resourceSet.getResources().removeAll(removedResources);
					for (final Resource changed : changedResources) {
						changed.unload();
						try {
							changed.load(null);
						} catch (final IOException ex) {
						}
					}

					IViewPart modelExplorerView = null;
					try {
						modelExplorerView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
								.showView("org.eclipse.emf.ecp.ui.ModelExplorerView");
						if (modelExplorerView instanceof ModelExplorerView) {
							final ModelExplorerView modelExplorer = (ModelExplorerView) modelExplorerView;
							selectRevealHandler.prepare();
							modelExplorer.getViewer().refresh(project);
							selectRevealHandler.selectReveal(changedResources.iterator().next());
						}
					} catch (Exception e) {
						Activator.log(e);
					}
					project.getEditingDomain().getCommandStack().flush();
				}
			});
		}
	}

	@Override
	public void fillContextMenu(IMenuManager manager, ECPContainer context, Object[] elements) {
		super.fillContextMenu(manager, context, elements);
		if (context instanceof ECPProject) {
			ECPProject project = (ECPProject) context;
//			if (elements.length == 1) {
//				final Object element = elements[0];
////				if (ClassMakerPlugin.getClassMaker() == null)
////					return;
//				if (ClassMakerPlugin.getClassMaker().getWorkspace()
//						.getProject(project.getName()) instanceof Contribution) {
//					manager.add(new Separator());
//					manager.add(new MakeAction(project.getEditingDomain(), new StructuredSelection(element), project));
//				}
//			}
			for (Object element : elements) {
				if (element instanceof EObject) {
					String eA = EcoreUtil.getAnnotation(((EObject) element).eClass().getEPackage(),
							EcorePackage.eNS_URI, "invocationDelegates");
					if (eA != null && eA.equals(ClassMakerService.INVOCATION_DELEGATE_URI)) {
						for (EOperation eOperation : ((EObject) element).eClass().getEOperations())
							if (eOperation.getEAnnotation(ClassMakerService.INVOCATION_DELEGATE_URI) != null
									&& EcoreUtil.getAnnotation(eOperation, "http://www.eclipse.org/emf/2002/GenModel",
											"body") != null) {
								manager.add(new ExecuteOperationAction(project.getEditingDomain(),
										new StructuredSelection(
												new Object[] { element, eOperation/* , control.getShell() */ }),
										project));
							}
					}
				}
			}
		}
	}

	public void setProgressMonitor(IProgressMonitor progressMonitor) {
		ClassMakerPlugin.setProgressMonitor(progressMonitor);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof ECPProject || element instanceof ECPRepository)
			return super.getImage(element);
		return ENTERPRISE_DOMAIN_LABEL_PROVIDER.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof ECPProject) {
			Project domainProject = Activator.getClassMaker().getWorkspace()
					.getProject(((ECPProject) element).getName().toLowerCase());
			if (domainProject != null)
				return super.getText(element) + " [" + domainProject.getPhase().getName().toLowerCase() + "]";
		}
		return ENTERPRISE_DOMAIN_LABEL_PROVIDER.getText(element);
	}

	public class EnterpriseDomainUIResourceListener implements IResourceChangeListener {

		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			if (event.getResource() != null && event.getResource().getType() == IResource.PROJECT
					&& event.getDelta() != null && event.getDelta().getKind() == IResourceDelta.CHANGED) {
				ECPProject project = ECPSavePropertySource.getProjectToSave();
				if (project != null) {
					ECPUtil.getECPObserverBus().notify(SaveButtonEnablementObserver.class)
							.notifyChangeButtonState(project, project.hasDirtyContents());
				}
			}

		}

	}

}
