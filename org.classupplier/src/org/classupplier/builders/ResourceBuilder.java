package org.classupplier.builders;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.classupplier.Phase;
import org.classupplier.State;
import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.util.ResourceUtil;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ResourceBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = ClassSupplierOSGi.PLUGIN_ID + '.'
			+ "resourceBuilder";

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		IFolder folder = getProject().getFolder(
				ResourceUtil.getModelFolderName());
		if (!folder.exists())
			folder.create(true, true, monitor);

		ResourcesJob job = new ResourcesJob();
		job.setProject(getProject());
		job.setProgressGroup(monitor, 1);
		job.schedule();
		try {
			job.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new IProject[] { getProject() };
	}

	private class ResourcesJob extends SupplementaryJob {

		public ResourcesJob() {
			super("Resource Persistence");
		}

		@Override
		public IStatus work(IProgressMonitor monitor) throws CoreException {
			IPath modelPath = ResourceUtil.getModelResourcePath(getProject(),
					ClassSupplierOSGi.getClassSupplier().getWorkspace());
			URI modelURI = URI.createPlatformResourceURI(modelPath.toString(),
					true);
			ResourceSet resourceSet = ClassSupplierOSGi.getClassSupplier()
					.getWorkspace().getResourceSet();
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
				if (!resource.getContents().isEmpty()
						&& resource.getContents().contains(
								state.getDynamicEPackage())) {
					int index = resource.getContents().lastIndexOf(
							state.getDynamicEPackage());
					resource.getContents().set(index,
							EcoreUtil.copy(state.getDynamicEPackage()));
				} else {
					resource.getContents().add(
							EcoreUtil.copy(state.getDynamicEPackage()));
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

		@Override
		public void checkStage() throws CoreException {
			if (getContribution().getStage().getValue() < Phase.DEFINED_VALUE)
				throw new CoreException(
						ClassSupplierOSGi
								.createWarningStatus("Contribution is not defined."));
		}
	}

}
