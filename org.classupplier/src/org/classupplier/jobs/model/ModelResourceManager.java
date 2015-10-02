package org.classupplier.jobs.model;

import java.io.IOException;
import java.util.Collections;

import org.classupplier.Messages;
import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.core.ClassSupplierOSGi;
import org.classupplier.jobs.ClassSupplierJob;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ModelResourceManager extends ClassSupplierJob {

	public IStatus manage(IProgressMonitor monitor) throws CoreException {
		IFolder folder = getProject().getFolder(ResourceUtil.getModelFolderName());
		if (!folder.exists())
			folder.create(true, true, monitor);

		IPath modelPath = ResourceUtil.getModelResourcePath(getProject(),
				ClassSupplierOSGi.getClassSupplier().getWorkspace());
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		URI modelURI = URI.createFileURI(root.getRawLocation().append(modelPath).toString());
		// URI.createPlatformResourceURI(modelPath.toString(), true);
		ResourceSet resourceSet = ClassSupplierOSGi.getClassSupplier().getWorkspace().getResourceSet();
		Resource resource = resourceSet.getResource(modelURI, false);
		if (resource == null)
			resource = resourceSet.createResource(modelURI);
		else
			try {
				resource.load(Collections.emptyMap());
			} catch (IOException e) {
				return ClassSupplierOSGi.createWarningStatus(e);
			}
		State state = getContribution();
		if (state.getStage() == Phase.MODELED) {
			if (resource.getContents().isEmpty())
				resource.getContents().add(EcoreUtil.copy(state.getDynamicEPackage()));
			else if (resource.getContents().contains(state.getDynamicEPackage())) {
				int index = resource.getContents().lastIndexOf(state.getDynamicEPackage());
				resource.getContents().set(index, EcoreUtil.copy(state.getDynamicEPackage()));
			} else {
				EList<EObject> contents = resource.getContents();
				for (int i = 0; i < contents.size(); i++) {
					if (contents.get(i) instanceof EPackage) {
						resource.getContents().set(i, EcoreUtil.copy(state.getDynamicEPackage()));
					}
				}
			}
		}
		try {
			resource.save(Collections.emptyMap());
		} catch (IOException e) {
			return ClassSupplierOSGi.createWarningStatus(e);
		}
		state.setStage(Phase.MODELED);
		return Status.OK_STATUS;

	}

	public ModelResourceManager() {
		super(Messages.ModelResourceJobName);
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		return manage(monitor);
	}

	@Override
	public Phase requiredStage() {
		return Phase.DEFINED;
	}

}
