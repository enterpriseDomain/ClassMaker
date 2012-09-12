package org.k.classmaker.codegen;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.k.classmaker.Bundle;

public class CDOGenerator extends EcoreGenerator {

	@Override
	protected void setupGenModel(Bundle bundle, IPath projectPath,
			org.eclipse.emf.codegen.ecore.genmodel.GenModel ecoreGenModel) {
		super.setupGenModel(bundle, projectPath, ecoreGenModel);
		ecoreGenModel
				.setFeatureDelegation(GenDelegationKind.REFLECTIVE_LITERAL);
		ecoreGenModel.getModelPluginVariables().add("CDO=org.eclipse.emf.cdo");
		ecoreGenModel
				.setRootExtendsClass("org.eclipse.emf.internal.cdo.CDOObjectImpl");
		ecoreGenModel.setRootExtendsInterface("org.eclipse.emf.cdo.CDOObject");
	}

}
