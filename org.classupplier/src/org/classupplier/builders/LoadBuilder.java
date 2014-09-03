package org.classupplier.builders;

import java.util.Map;

import org.classupplier.impl.ClassSupplierOSGi;
import org.classupplier.load.BundleEPackageLoader;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;

public class LoadBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = ClassSupplierOSGi.PLUGIN_ID + '.'
			+ "loadBuilder";

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind != FULL_BUILD)
			return null;
		BundleEPackageLoader loader = new BundleEPackageLoader(getProject());
		loader.load(!loader.bundleExists());
		try {
			Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD,
					monitor);
		} catch (OperationCanceledException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		return null;
	}
}
