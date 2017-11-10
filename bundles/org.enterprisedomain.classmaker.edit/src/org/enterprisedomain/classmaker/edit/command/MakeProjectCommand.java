package org.enterprisedomain.classmaker.edit.command;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.provider.ClassMakerEditPlugin;

public class MakeProjectCommand extends CommandWrapper implements CommandActionDelegate {

	private Object projectName;

	/**
	 * This returns a command created by the editing domain to make the project
	 * described by <code>projectName</code>.
	 */
	public static Command create(EditingDomain domain, Object owner, Object projectName, Collection<?> selection) {
		return domain.createCommand(MakeProjectCommand.class,
				new CommandParameter(owner, null, projectName, new ArrayList<Object>(selection)));
	}

	public MakeProjectCommand(EditingDomain domain, CommandParameter commandParameter) {
		super();
		projectName = commandParameter.getValue();
	}

	@Override
	public boolean canExecute() {
		super.canExecute();
		return projectName != null;
	}

	@Override
	public void execute() {
		super.execute();
		if (projectName == null)
			return;
		try {
			ClassMakerPlugin.getClassMaker().getWorkspace().getProject((String) projectName)
					.make(ClassMakerPlugin.getProgressMonitor());
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		}
	}

	@Override
	public Object getImage() {
		return ClassMakerEditPlugin.INSTANCE
				.getImage("platform:/plugin/org.enterprisedomain.classmaker.edit/full/obj16/ClassMakerService");
	}

	@Override
	public String getText() {
		return "Make";
	}

	@Override
	public String getToolTipText() {
		return "Make the project.";
	}

}
