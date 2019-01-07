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
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Shell;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class ExecuteOperationCommand extends ChangeCommand implements CommandActionDelegate {

	private Object projectName;

	private EObject eObject;

	private EOperation eOperation;

	private Shell shell;

	public ExecuteOperationCommand(EditingDomain domain, CommandParameter commandParameter) {
		super(ClassMakerPlugin.getClassMaker().getWorkspace().getProject((String) commandParameter.getValue()));
		projectName = commandParameter.getValue();
		Object[] collection = new Object[3];
		commandParameter.getCollection().toArray(collection);
		eObject = (EObject) collection[0];
		eOperation = (EOperation) collection[1];
		shell = (Shell) collection[2];
	}

	public static Command create(EditingDomain editingDomain, Object owner, String name, Collection<?> collection) {
		return new ExecuteOperationCommand(editingDomain, new CommandParameter(owner, null, name, collection));
	}

	@Override
	public Object getImage() {
		return null;
	}

	@Override
	public String getText() {
		return eOperation.getName();
	}

	@Override
	public String getToolTipText() {
		return "Invoke EOperation";
	}

	@Override
	protected void doExecute() {
		// EOperationInvocationDialog dialog = new EOperationInvocationDialog(shell,
		// eOperation);
		// EList<?> arguments = null;
		// if (dialog.open() == Window.OK) {
		// arguments=ECollections.newBasicEList();
		// for(EParameter parameter:dialog.getEOperation().getEParameters())
		// arguments.add(parameter.)
		// } else {
		// arguments = ECollections.newBasicEList();
		// }

		// arguments
		try {
			Object result = EcoreUtil.getInvocationDelegateFactory(eOperation).createInvocationDelegate(eOperation)
					.dynamicInvoke((InternalEObject) eObject, null); // arguments);
			if (result instanceof EObject)
				eObject.eContainer().eContents().add(((EObject) result));
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
