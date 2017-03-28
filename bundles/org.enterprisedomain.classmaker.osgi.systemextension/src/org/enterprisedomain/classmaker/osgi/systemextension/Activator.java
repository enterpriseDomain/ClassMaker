package org.enterprisedomain.classmaker.osgi.systemextension;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;

public class Activator implements BundleActivator {

	private ServiceRegistration<ResolverHookFactory> reg;

	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		reg = context.registerService(ResolverHookFactory.class, new ResolverHook(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (reg != null) {
			reg.unregister();
			reg = null;
		}
	}

}
