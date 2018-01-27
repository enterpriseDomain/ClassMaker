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
package org.enterprisedomain.ecp.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.core.ECPRepository;
import org.eclipse.emf.ecp.core.util.ECPContainer;
import org.eclipse.emf.ecp.core.util.ECPProperties;
import org.eclipse.emf.ecp.spi.core.InternalProvider;
import org.eclipse.emf.ecp.spi.ui.CompositeStateObserver;
import org.eclipse.emf.ecp.spi.ui.DefaultUIProvider;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ILabelProvider;
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
import org.eclipse.ui.internal.progress.ProgressManager;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.SelectRevealHandler;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.core.IRunWrapper;
import org.enterprisedomain.classmaker.core.IRunnerWithProgress;
import org.enterprisedomain.classmaker.provider.ClassMakerItemProviderAdapterFactory;
import org.enterprisedomain.classmaker.util.ClassMakerAdapterFactory;
import org.enterprisedomain.ecp.Activator;
import org.enterprisedomain.ecp.EnterpriseDomainProvider;
import org.enterprisedomain.ecp.ui.actions.MakeAction;

@SuppressWarnings("restriction")
public class EnterpriseDomainUIProvider extends DefaultUIProvider {

	public static final ComposeableAdapterFactory ENTERPRISE_DOMAIN_ITEM_PROVIDER_ADAPTER_FACTORY = new ComposedAdapterFactory(
			new AdapterFactory[] { new ClassMakerItemProviderAdapterFactory(), new ClassMakerAdapterFactory(),
					InternalProvider.EMF_ADAPTER_FACTORY });

	private static final ILabelProvider ENTERPRISE_DOMAIN_LABEL_PROVIDER = new AdapterFactoryLabelProvider(
			ENTERPRISE_DOMAIN_ITEM_PROVIDER_ADAPTER_FACTORY);

	private Button isContributionButton;
	private boolean isContribution;

	private SelectRevealHandler selectRevealHandler = new SelectResourceHandler();

	public EnterpriseDomainUIProvider() {
		super(EnterpriseDomainProvider.NAME);
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
		if (DefaultUIProvider.class.isAssignableFrom(adapterType))
			return (T) this;
		return (T) getProvider().getAdapter(adaptable, adapterType);
	}

	@SuppressWarnings("restriction")
	@Override
	public Control createNewProjectUI(Composite parent, CompositeStateObserver observer,
			final ECPProperties projectProperties) {
		Composite control = new Composite(parent, SWT.NONE);
		control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		control.setLayout(new GridLayout(1, false));

		isContributionButton = new Button(control, SWT.CHECK);
		isContributionButton.setText("Software Contribution");
		isContributionButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		isContribution = Boolean.valueOf(projectProperties.getValue(EnterpriseDomainProvider.PROP_CONTRIBUTION));
		isContributionButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				isContribution = !isContribution;
				projectProperties.addProperty(EnterpriseDomainProvider.PROP_CONTRIBUTION,
						String.valueOf(isContribution));
				super.widgetSelected(e);
			}

		});
		observer.compositeChangedState(control, true, projectProperties);
		ClassMakerPlugin.setPreviousProgressProvider(ProgressManager.getInstance());
		return control;
	}

	// observer.compositeChangedState(this, complete, projectProperties);
	//
	// Composite control = new Composite(parent, SWT.NULL);
	// control.setLayout(new GridLayout(2, false));
	//
	// Label lblName = new Label(control, SWT.NONE);
	// lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
	// 1, 1));
	// lblName.setText("Name:");
	//
	// nameText = new Text(control, SWT.BORDER);
	// nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
	// 1, 1));
	//
	// Label lblNsPrefix = new Label(control, SWT.NONE);
	// lblNsPrefix.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
	// false, 1, 1));
	// lblNsPrefix.setText("Ns Prefix:");
	//
	// nsPrefixText = new Text(control, SWT.BORDER);
	// nsPrefixText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
	// false, 1, 1));
	//
	// Label lblNsUri = new Label(control, SWT.NONE);
	// lblNsUri.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
	// false, 1, 1));
	// lblNsUri.setText("Ns URI:");
	//
	// nsURIText = new Text(control, SWT.BORDER);
	// nsURIText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
	// 1, 1));
	// nameText.addModifyListener(modifyListener);
	// nsPrefixText.addModifyListener(modifyListener);
	// nsURIText.addModifyListener(modifyListener);
	//
	// return control;
	// }
	//
	// private Text nameText;
	//
	// private ModifyListener modifyListener = new ModifyListener() {
	//
	// @Override
	// public void modifyText(ModifyEvent e) {
	// if (isInputComplete())
	// setPageComplete(true);
	// if (e.getSource() == nameText) {
	// nsPrefixText.setText(produceNsPrefix(nameText.getText()));
	// nsURIText.setText(produceNsURI(nameText.getText()));
	// }
	// }
	//
	// };
	// private Text nsPrefixText;
	// private Text nsURIText;
	//
	// private boolean isInputComplete() {
	// return !nameText.getText().isEmpty()
	// && !nsPrefixText.getText().isEmpty()
	// && nsURIText.getText().matches("http:\\/\\/.+\\/.*");
	// }
	//
	// private String produceNsPrefix(String name) {
	// return name.replaceAll(" ", "").toLowerCase();
	// }
	//
	// private String produceNsURI(String name) {
	// return "http://" + name.replaceAll(" ", "") + "/1.0";
	// }

	@Override
	public void fillContextMenu(IMenuManager manager, ECPContainer context, Object[] elements) {
		super.fillContextMenu(manager, context, elements);
		if (elements.length == 1) {
			final Object element = elements[0];
			if (context instanceof ECPProject) {
				ECPProject project = (ECPProject) context;
				manager.add(new Separator());
				manager.add(new MakeAction(project.getEditingDomain(), new StructuredSelection(element), project));
			}
		}
	}

	public void setProgressMonitor(IProgressMonitor progressMonitor) {
		ClassMakerPlugin.setProgressMonitor(progressMonitor);
	}

	public void setClientRunWrapper(IRunWrapper wrapper) {
		ClassMakerPlugin.setClientRunWrapper(wrapper);
	}

	public void setRunnerWithProgress(IRunnerWithProgress runner) {
		ClassMakerPlugin.setRunnerWithProgress(runner);
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
			Contribution domainProject = Activator.getClassMaker().getWorkspace()
					.getContribution(((ECPProject) element).getName());
			if (domainProject != null)
				return super.getText(element) + " [" + domainProject.getPhase().toString().toLowerCase() + "]";
		}
		return super.getText(element);
	}

}
