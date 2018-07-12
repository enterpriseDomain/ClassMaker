package org.enterprisedomain.ecp.ui.actions;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.jface.viewers.ISelection;
import org.enterprisedomain.classmaker.edit.command.ExecuteOperationCommand;

public class ExecuteOperationAction extends StaticSelectionCommandAction {
	private Object project;

	public ExecuteOperationAction(EditingDomain editingDomain, ISelection selection, Object project) {
		super(editingDomain);
		this.project = project;
		configureAction(selection);
	}

	@Override
	protected Command createActionCommand(EditingDomain editingDomain, Collection<?> collection) {
		return ExecuteOperationCommand.create(editingDomain, null, ((ECPProject) project).getName(), collection);
	}

}
