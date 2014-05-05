package org.classupplier.builders;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.classupplier.Artifact;
import org.classupplier.State;
import org.classupplier.impl.OSGi;
import org.classupplier.impl.ResourceHelper;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ResourceBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = OSGi.PLUGIN_ID + '.'
			+ "resourceBuilder";

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		IFolder folder = getProject()
				.getFolder(ResourceHelper.getModelFolderName());
		if (!folder.exists())
			folder.create(true, true, monitor);
		ISchedulingRule rule = getRule(kind, args);
		Artifact artifact = OSGi.getClasSupplier().getWorkspace()
				.getArtifact(getProject().getName());
		getProject().getWorkspace().run(new ResourceRunnable(artifact), rule,
				0, monitor);
		return null;
	}

	private class ResourceRunnable implements IWorkspaceRunnable {

		private Artifact artifact;

		public ResourceRunnable(Artifact artifact) {
			this.artifact = artifact;
		}

		@Override
		public void run(IProgressMonitor monitor) throws CoreException {
			IPath modelPath = ResourceHelper.getModelResourcePath(getProject(),
					OSGi.getClasSupplier().getWorkspace());
			URI modelURI = URI.createPlatformResourceURI(modelPath.toString(),
					true);
			ResourceSet resourceSet = OSGi.getClasSupplier().getWorkspace()
					.getResourceSet();
			Resource resource = resourceSet.getResource(modelURI, false);
			if (resource == null)
				resource = resourceSet.createResource(modelURI);
			else
				try {
					resource.load(Collections.emptyMap());
				} catch (IOException e) {
					throw new CoreException(new Status(IStatus.WARNING,
							OSGi.PLUGIN_ID, e.getLocalizedMessage(), e));
				}
			if (artifact.getState() == State.PROTOTYPE
					|| artifact.getState() == State.PROCESSING) {
				if (!resource.getContents().isEmpty()
						&& resource.getContents().contains(
								artifact.getPrototypeEPackage())) {
					int index = resource.getContents().lastIndexOf(
							artifact.getPrototypeEPackage());
					resource.getContents().set(index,
							EcoreUtil.copy(artifact.getPrototypeEPackage()));
				} else {
					resource.getContents().add(
							EcoreUtil.copy(artifact.getPrototypeEPackage()));
				}
			}
			try {
				resource.save(Collections.emptyMap());
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.WARNING,
						OSGi.PLUGIN_ID, e.getLocalizedMessage(), e));
			}
			try {
				Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD,
						monitor);
			} catch (OperationCanceledException e) {
				return;
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
