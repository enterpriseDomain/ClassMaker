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

import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.core.ECPRepository;
import org.eclipse.emf.ecp.core.util.ECPProperties;
import org.eclipse.emf.ecp.spi.core.InternalProvider;
import org.eclipse.emf.ecp.spi.ui.CompositeStateObserver;
import org.eclipse.emf.ecp.spi.ui.DefaultUIProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.enterprisedomain.ecp.EnterpriseDomainProvider;

public class EnterpriseDomainUIProvider extends DefaultUIProvider {

	public static final ILabelProvider ENTERPRISE_DOMAIN_LABEL_PROVIDER = new AdapterFactoryLabelProvider(
			EnterpriseDomainProvider.ENTERPRISE_DOMAIN_ADAPTER_FACTORY);

	@Inject
	private Shell shell;

	private Button isContributionButton;
	private StackLayout providerStackLayout;
	private static IProgressMonitor monitor;

	private boolean isContribution;

	public EnterpriseDomainUIProvider() {
		super(EnterpriseDomainProvider.NAME);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptable, Class<T> adapterType) {
		if (IProgressMonitor.class.isAssignableFrom(adapterType))
			if (monitor != null)
				return (T) monitor;
		return (T) getProvider().getAdapter(adaptable, adapterType);
	}

	@Override
	public Control createNewProjectUI(Composite parent, CompositeStateObserver observer,
			final ECPProperties projectProperties) {
		providerStackLayout = new StackLayout();
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
		return control;
	}

	public static void setProgessMonitor(IProgressMonitor monitor) {
		EnterpriseDomainUIProvider.monitor = monitor;
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
	public Image getImage(Object element) {
		// Image image = null;
		// try {
		if (element instanceof ECPProject || element instanceof ECPRepository)
			return super.getImage(element);
		// } catch (Exception e) {
		// }
		// if (image != null)
		// return image;
		return ENTERPRISE_DOMAIN_LABEL_PROVIDER.getImage(element);
	}

	@Override
	public String getText(Object element) {
		return super.getText(element);
	}

}
