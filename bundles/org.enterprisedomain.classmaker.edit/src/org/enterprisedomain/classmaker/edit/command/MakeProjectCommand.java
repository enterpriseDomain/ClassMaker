/**
 * Copyright 2019 Kyrill Zotkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.edit.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						Project project = classMaker.getWorkspace().getProject((String) projectName);
						EPackage ePackage = project.getRevision().getState().getDomainModel().getDynamic();
						Revision newRevision = project.newRevision(project.nextVersion());
						newRevision.create(monitor);
						project.checkout(newRevision.getVersion());
						newRevision.getState().getDomainModel().setDynamic(EcoreUtil.copy(ePackage));
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
