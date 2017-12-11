package org.enterprisedomain.workplace;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction quitAction;
	private IContributionItem openPerspectiveContribution;
	private IContributionItem showViewContribution;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		quitAction = ActionFactory.QUIT.create(window);
		openPerspectiveContribution = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(window);
		showViewContribution = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
		Separator additions = new Separator(IWorkbenchActionConstants.MB_ADDITIONS);
		fileMenu.add(additions);
		fileMenu.add(quitAction);
		menuBar.add(fileMenu);

		additions = new Separator(IWorkbenchActionConstants.MB_ADDITIONS);
		menuBar.add(additions);

		MenuManager windowMenu = new MenuManager("&Window", IWorkbenchActionConstants.M_WINDOW);
		menuBar.add(windowMenu);

		MenuManager openPerspectiveMenu = new MenuManager("&Open Perspective");
		openPerspectiveMenu.add(openPerspectiveContribution);
		windowMenu.add(openPerspectiveMenu);

		MenuManager showViewMenu = new MenuManager("Show &View");
		showViewMenu.add(showViewContribution);
		windowMenu.add(showViewMenu);
	}

}
