package org.enterprisedomain.classmaker.invoke.ui;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ContributionItem extends org.eclipse.jface.action.ContributionItem {

	public ContributionItem() {
	}

	public ContributionItem(String id) {
		super(id);
	}

	@Override
	public void fill(Menu menu, int index) {
		MenuItem newInvocation = new MenuItem(menu, SWT.POP_UP, index);
		newInvocation.setText("In&voke...");
		newInvocation.addSelectionListener(widgetSelectedAdapter(e -> {
			InvocationDialog dialog = new InvocationDialog(e.display.getActiveShell());
			dialog.open();
		}));

	}

}
