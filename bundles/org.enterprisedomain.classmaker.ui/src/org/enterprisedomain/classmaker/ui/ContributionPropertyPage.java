package org.enterprisedomain.classmaker.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyPage;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class ContributionPropertyPage extends PropertyPage {

	/**
	 * Create the property page.
	 */
	public ContributionPropertyPage() {
	}

	private Contribution selectedContribution;

	/**
	 * Create contents of the property page.
	 * 
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
				.getSelection();
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			if (selected instanceof IProject)
				selectedContribution = ClassMakerPlugin.getClassMaker().getWorkspace()
						.getContribution(((IProject) selected).getName());
		}
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(1, false));

		Button btnModel = new Button(container, SWT.CHECK);
		btnModel.setSelection(true);
		btnModel.setEnabled(false);
		btnModel.setText("Model");

		Button btnEdit = new Button(container, SWT.CHECK);
		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedContribution.getState().setEdit(btnEdit.getSelection());
			}
		});
		btnEdit.setText("Edit");
		btnEdit.setSelection(selectedContribution.getState().isEdit());

		Button btnEditor = new Button(container, SWT.CHECK);
		btnEditor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean select = btnEditor.getSelection();
				selectedContribution.getState().setEditor(select);
				if (select) {
					btnEdit.setSelection(select);
					selectedContribution.getState().setEdit(select);
				}
			}
		});
		btnEditor.setText("Editor");
		btnEditor.setSelection(selectedContribution.getState().isEditor());

		return container;
	}

}
