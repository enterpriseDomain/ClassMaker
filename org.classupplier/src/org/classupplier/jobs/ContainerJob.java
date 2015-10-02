package org.classupplier.jobs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

public abstract class ContainerJob extends ClassSupplierJob {

	public ContainerJob(String name) {
		super(name);
	}

	public Collection<Bundle> getBundles(String symbolicName, BundleContext context) {
		if (context == null)
			return null;
		List<Bundle> results = new ArrayList<Bundle>();
		for (Bundle bundle : context.getBundles())
			if (bundle.getSymbolicName().equals(symbolicName))
				results.add(bundle);
		return results;
	}

	public Collection<Bundle> getBundles(String symbolicName) {
		return getBundles(symbolicName, getContext());

	}

	public Collection<Bundle> getBundles() {
		return getBundles(getContribution().getProjectName());

	}

	protected BundleContext getContext() {
		return FrameworkUtil.getBundle(this.getClass()).getBundleContext();
	}

	protected Bundle getBundle(Version version) {
		for (Bundle bundle : getBundles())
			if (versionsAreEqual(version, bundle.getVersion(), true))
				return bundle;
		return null;
	}

	protected boolean versionsAreEqual(Version version, Version version2, boolean ignoreQualifier) {
		return (version.getMajor() == version2.getMajor()) && (version.getMinor() == version2.getMinor())
				&& (version.getMicro() == version2.getMicro())
				&& (ignoreQualifier ? true : version.getQualifier().equals(version2.getQualifier()));
	}

}
