/**
 * Copyright 2012-2018 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.jobs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

public abstract class ContainerJob extends EnterpriseDomainJob {

	public ContainerJob(String name, int stateTimestamp) {
		super(name, stateTimestamp);
	}

	public Collection<Bundle> getBundles(String symbolicName, Version version, BundleContext context) {
		if (context == null)
			return null;
		List<Bundle> results = new ArrayList<Bundle>();
		for (Bundle bundle : context.getBundles())
			if (bundle.getSymbolicName().equals(symbolicName) && (versionsAreEqual(version, bundle.getVersion(), true)
					|| versionAreLess(version, bundle.getVersion(), true)))
				results.add(bundle);
		return results;
	}

	public Collection<Bundle> getBundles(String symbolicName, Version version) {
		return getBundles(symbolicName, version, getContext());
	}

	public Collection<Bundle> getBundles() {
		return getBundles(getContributionState().getProjectName(), getContributionState().getVersion());

	}

	protected BundleContext getContext() {
		return FrameworkUtil.getBundle(this.getClass()).getBundleContext();
	}

	protected boolean versionsAreEqual(Version version, Version version2, boolean ignoreQualifier) {
		return (version.getMajor() == version2.getMajor()) && (version.getMinor() == version2.getMinor())
				&& (version.getMicro() == version2.getMicro())
				&& (ignoreQualifier ? true : version.getQualifier().equals(version2.getQualifier()));
	}

	protected boolean versionAreLess(Version version, Version version2, boolean ignoreQualifier) {
		return (version.getMajor() < version2.getMajor()) || (version.getMinor() < version2.getMinor())
				|| (version.getMicro() < version2.getMicro())
						&& (ignoreQualifier ? true : version.getQualifier().equals(version2.getQualifier()));
	}

}
