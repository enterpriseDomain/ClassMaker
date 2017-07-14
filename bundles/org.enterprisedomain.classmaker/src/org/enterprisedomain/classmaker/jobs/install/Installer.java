/**
 * Copyright 2012-2016 Kyrill Zotkin
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
package org.enterprisedomain.classmaker.jobs.install;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.jobs.ContainerJob;

public abstract class Installer extends ContainerJob {

	public Installer() {
		super(Messages.JobNameInstaller);
	}

	public abstract IStatus install(IProgressMonitor monitor) throws CoreException;

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		try {
			return install(monitor);
		} finally {
			monitor.done();
		}
	}

}
