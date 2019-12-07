/**
 * Copyright 2019 Kyrill Zotkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.core;

import java.util.Map;

import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Project;
import org.osgi.framework.ServiceException;

public class ClassMakerBuilder extends IncrementalProjectBuilder implements IBuildConfiguration {

	public static final String BUILDER_ID = ClassMakerPlugin.BUILDER_ID;

	private boolean buildRunning = false;

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public String getName() {
		return BUILDER_ID;
	}

	@Override
	protected IProject[] build(int kind, Map<String, String> options, IProgressMonitor monitor) throws CoreException {
		ClassMakerService service = null;
		try {
			service = ClassMakerPlugin.getClassMaker();
		} catch (ServiceException e) {
			return null;
		}
		if (service == null)
			return null;
		Project project = service.getWorkspace().getProject(getProject().getName());
		if (project instanceof Contribution && project.isDirty() && !buildRunning) {
			buildRunning = true;
			((Contribution) project).build(monitor);
			buildRunning = false;
		}
		return null;
	}

}
