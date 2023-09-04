package org.enterprisedomain.classmaker.osgi.systemextension;

import java.util.Collection;

import org.osgi.framework.Version;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;

public class ResolverHook implements ResolverHookFactory {

	@Override
	public org.osgi.framework.hooks.resolver.ResolverHook begin(Collection<BundleRevision> triggers) {
		return new org.osgi.framework.hooks.resolver.ResolverHook() {

			@Override
			public void filterSingletonCollisions(BundleCapability singleton,
					Collection<BundleCapability> collisionCandidates) {
				String projectName = singleton.getRevision().getSymbolicName();
				Version version = singleton.getRevision().getVersion();
				collisionCandidates.removeIf(candidate -> {
					return !(candidate.getRevision().getSymbolicName().equals(projectName)
							&& candidate.getRevision().getVersion().equals(version));
				});
			}

			@Override
			public void filterResolvable(Collection<BundleRevision> candidates) {
			}

			@Override
			public void filterMatches(BundleRequirement requirement, Collection<BundleCapability> candidates) {
			}

			@Override
			public void end() {
			}
		};
	}

}
