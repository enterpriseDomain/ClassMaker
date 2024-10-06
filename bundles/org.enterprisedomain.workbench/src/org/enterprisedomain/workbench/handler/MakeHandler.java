package org.enterprisedomain.workbench.handler;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class MakeHandler extends AbstractHandler implements IHandler {

	public MakeHandler() {
		super();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
		if (selection instanceof IStructuredSelection) {
			final Object eProject = ((IStructuredSelection) selection).getFirstElement();

			if (eProject instanceof IProject)
				doMake((String) ((IProject) eProject).getName());			
		}
		return null;
	}

	private void doMake(final String projectName) throws ExecutionException {
		final ClassMakerService classMaker = ClassMakerPlugin.getClassMaker();
		try {
			ClassMakerPlugin.runWithProgress(new IRunnableWithProgress() {

				@Override
				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						Project project = classMaker.getWorkspace().getProject(projectName);
						if (project == null)
							project = classMaker.getWorkspace().createProject(projectName, monitor);
						else {
							Revision newRevision = project.newRevision(project.nextVersion());
							newRevision.create(monitor);
							project.setProjectVersion(newRevision.getVersion());
						}
						project.make(monitor);
					} catch (CoreException e) {
						ClassMakerPlugin.wrapRun(new Runnable() {
							public void run() {
								monitor.setCanceled(true);
							}
						});
						throw new InvocationTargetException(e);
					}
				}

			});
		} catch (InvocationTargetException e) {
			Throwable exception = e.getTargetException();
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(exception));
			throw new ExecutionException(e.getLocalizedMessage(), e.getTargetException());
		} catch (InterruptedException e) {
		}
	}
}
