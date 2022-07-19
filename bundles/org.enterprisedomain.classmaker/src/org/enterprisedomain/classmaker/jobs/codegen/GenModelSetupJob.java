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
package org.enterprisedomain.classmaker.jobs.codegen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class GenModelSetupJob extends EcoreGenerator.GeneratorJob {

	/**
	 * 
	 */
	private final EcoreGenerator ecoreGenerator;

	public GenModelSetupJob(EcoreGenerator ecoreGenerator, int depth, long stateTimestamp) {
		super(Messages.JobNameGenModelConfiguration, depth, stateTimestamp);
		this.ecoreGenerator = ecoreGenerator;
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		URI modelURI = URI.createFileURI(root.getRawLocation().append(getModelLocation()).toString());
		Resource resource = getResourceSet().getResource(modelURI, true);
		EList<EPackage> ePackages = ECollections.newBasicEList();
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof EPackage) {
				ePackages.add((EPackage) eObject);
			}
		}

		URI genModelURI = URI.createFileURI(root.getRawLocation().append(getGenModelLocation()).toString());
		resource = getResourceSet().getResource(genModelURI, true);
		GenModel genModel = (GenModel) resource.getContents().get(0);
		this.ecoreGenerator.setupGenModel(computeProjectPath(), genModel, ePackages);
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put(XMLResource.OPTION_ENCODING, "UTF-8");
			resource.save(options);
		} catch (IOException e) {
			throw new CoreException(ClassMakerPlugin.createWarningStatus(e));
		}
		monitor.worked(1);
		return Status.OK_STATUS;
	}
}