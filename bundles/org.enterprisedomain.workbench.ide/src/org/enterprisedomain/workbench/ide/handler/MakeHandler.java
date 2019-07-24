package org.enterprisedomain.workbench.ide.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class MakeHandler extends AbstractHandler implements IHandler {

	public MakeHandler() {
		super();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
		if (selection instanceof IStructuredSelection)
			try {
				Object project = ((IStructuredSelection) selection).getFirstElement();
				if (project instanceof IProject)
					ClassMakerPlugin.getClassMaker().getWorkspace().getContribution(((IProject) project).getName())
							.make(ClassMakerPlugin.getProgressMonitor());
				else if (project instanceof ECPProject)
					ClassMakerPlugin.getClassMaker().getWorkspace().getContribution(((ECPProject) project).getName())
							.make(ClassMakerPlugin.getProgressMonitor());
			} catch (CoreException e) {
				ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				throw new ExecutionException(e.getLocalizedMessage(), e);
			}
		return null;
	}

}
