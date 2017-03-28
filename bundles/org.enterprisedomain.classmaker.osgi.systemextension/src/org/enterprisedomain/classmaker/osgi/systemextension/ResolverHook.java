package org.enterprisedomain.classmaker.osgi.systemextension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
				List<BundleCapability> toRemove = new ArrayList<BundleCapability>();
				for (BundleCapability candidate : collisionCandidates)
					if (!candidate.equals(singleton))
						toRemove.add(candidate);
				collisionCandidates.removeAll(toRemove);
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
