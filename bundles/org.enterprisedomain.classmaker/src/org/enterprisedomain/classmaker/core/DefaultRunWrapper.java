package org.enterprisedomain.classmaker.core;

public class DefaultRunWrapper implements IRunWrapper {

	@Override
	public void wrapRun(Runnable runnable) {
		runnable.run();
	}

}
