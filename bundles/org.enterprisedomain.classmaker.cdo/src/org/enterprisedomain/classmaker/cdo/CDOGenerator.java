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
package org.enterprisedomain.classmaker.cdo;

import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EPackage;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.jobs.codegen.EcoreGenerator;

public class CDOGenerator extends EcoreGenerator {

	public CDOGenerator(int depth, long stateTimestamp) {
		super(depth, stateTimestamp);
	}

	protected void setupGenModel(IPath projectPath, GenModel ecoreGenModel, Collection<EPackage> ePackages) {
		super.setupGenModel(projectPath, ecoreGenModel, ePackages);
		ClassMakerPlugin.getClassMaker().getWorkspace().getContribution(projectPath.lastSegment()).getDependencies()
				.add("org.eclipse.emf.cdo");
		ecoreGenModel.setSuppressInterfaces(false);
		ecoreGenModel.setFeatureDelegation(GenDelegationKind.REFLECTIVE_LITERAL);
		ecoreGenModel.getModelPluginVariables().add("CDO=org.eclipse.emf.cdo"); //$NON-NLS-1$
		ecoreGenModel.setRootExtendsClass("org.eclipse.emf.internal.cdo.CDOObjectImpl"); //$NON-NLS-1$
		ecoreGenModel.setRootExtendsInterface("org.eclipse.emf.cdo.CDOObject"); //$NON-NLS-1$
	}

}
