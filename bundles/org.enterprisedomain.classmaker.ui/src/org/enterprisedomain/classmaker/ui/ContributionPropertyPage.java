package org.enterprisedomain.classmaker.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;

public class ContributionPropertyPage extends PropertyPage implements IWorkbenchPropertyPage {

	public ContributionPropertyPage() {
	}

	@Override
	protected Control createContents(Composite parent) {
		parent.setLayout(new FillLayout());
		Label l = new Label(parent, SWT.HORIZONTAL);
		l.setText("help");
		setControl(parent);
		return parent;
	}

}
