package org.enterprisedomain.classmaker.jobs;

import org.eclipse.core.runtime.CoreException;
import org.enterprisedomain.classmaker.State;

public interface EnterpriseDomainJobListener {

	void hookBefore(State contributionState) throws CoreException;

}
