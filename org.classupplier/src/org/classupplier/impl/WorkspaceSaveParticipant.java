package org.classupplier.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.classupplier.Workspace;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

public class WorkspaceSaveParticipant implements ISaveParticipant {

	@Override
	public void doneSaving(ISaveContext context) {
		switch (context.getKind()) {
		case ISaveContext.FULL_SAVE:
			int previousSaveNumber = context.getPreviousSaveNumber();
			String oldFileName = ClassSupplierOSGi.WORKSPACE_SAVE_FILE + "-"
					+ Integer.toString(previousSaveNumber) + ".xmi";
			try {
				File file = new File(
						ResourceUtil.workspaceResourcePath(oldFileName));
				file.delete();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	public void prepareToSave(ISaveContext context) throws CoreException {

	}

	@Override
	public void rollback(ISaveContext context) {
		switch (context.getKind()) {
		case ISaveContext.FULL_SAVE:
			int saveNumber = context.getSaveNumber();
			String saveFileName = ClassSupplierOSGi.WORKSPACE_SAVE_FILE + "-"
					+ Integer.toString(saveNumber) + ".xmi";
			try {
				File file = new File(
						ResourceUtil.workspaceResourcePath(saveFileName));
				file.delete();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	public void saving(ISaveContext context) throws CoreException {
		switch (context.getKind()) {
		case ISaveContext.FULL_SAVE:
			int saveNumber = context.getSaveNumber();
			String saveFileName = ClassSupplierOSGi.WORKSPACE_SAVE_FILE + "-"
					+ Integer.toString(saveNumber) + ".xmi";
			Workspace workspace = ClassSupplierOSGi.getClassSupplier()
					.getWorkspace();
			Resource resource = workspace.getResourceSet().createResource(
					ResourceUtil.workspaceResourceURI(saveFileName));
			Copier copier = new Copier();
			EObject copy = copier.copy(workspace);
			copier.copyReferences();
			resource.getContents().add(copy);
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				throw new CoreException(
						ClassSupplierOSGi.createWarningStatus(e));
			}
			context.map(new Path(ClassSupplierOSGi.WORKSPACE_SAVE_FILE),
					new Path(saveFileName));
			context.needSaveNumber();
			break;
		}

	}

}
