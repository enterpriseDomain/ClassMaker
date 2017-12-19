package org.enterprisedomain.classmaker.edit.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class MakeProjectCommand extends ChangeCommand implements CommandActionDelegate {

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
		super(ClassMakerPlugin.getClassMaker().getWorkspace().getProject((String) commandParameter.getValue()));
		projectName = commandParameter.getValue();

	}

	@Override
	public boolean canExecute() {
		return projectName != null && super.canExecute();
	}

	@Override
	protected void doExecute() {
		if (projectName == null)
			return;
		final ClassMakerService classMaker = ClassMakerPlugin.getClassMaker();
		try {
			ClassMakerPlugin.runWithProgress(new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						Project project = classMaker.getWorkspace().getProject((String) projectName);
						Revision newRevision = project.newRevision(project.nextVersion());
						newRevision.create(monitor);
						EPackage ePackage = (EPackage) ((Resource) project.getChildren().get(0)).getContents().get(0);
						project.checkout(newRevision.getVersion());
						newRevision.getState().getDomainModel().setDynamic(ePackage);
						project.make(monitor);
					} catch (CoreException e) {
						monitor.setCanceled(true);
						throw new InvocationTargetException(e);
					}
				}

			});
		} catch (InvocationTargetException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e.getTargetException()));
		} catch (InterruptedException e) {
		}
	}

	@Override
	public Object getImage() {
		return null;
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
