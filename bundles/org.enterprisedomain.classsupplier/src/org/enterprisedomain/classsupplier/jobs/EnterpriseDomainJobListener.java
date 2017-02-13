package org.enterprisedomain.classsupplier.jobs;

import org.eclipse.core.runtime.CoreException;
import org.enterprisedomain.classsupplier.State;

public interface EnterpriseDomainJobListener {

	void hookBefore(State contributionState) throws CoreException;

}
