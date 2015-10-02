package org.classupplier.jobs.codegen;

import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.ecore.EPackage;

public class CDOGenerator extends EcoreGenerator {

	@Override
	protected void setupGenModel(IPath projectPath, org.eclipse.emf.codegen.ecore.genmodel.GenModel ecoreGenModel,
			EPackage modelEPackage, Collection<EPackage> allEPackages) {
		super.setupGenModel(projectPath, ecoreGenModel, modelEPackage, allEPackages);
		ecoreGenModel.setSuppressInterfaces(false);
		ecoreGenModel.setFeatureDelegation(GenDelegationKind.REFLECTIVE_LITERAL);
		ecoreGenModel.getModelPluginVariables().add("CDO=org.eclipse.emf.cdo");
		ecoreGenModel.setRootExtendsClass("org.eclipse.emf.internal.cdo.CDOObjectImpl");
		ecoreGenModel.setRootExtendsInterface("org.eclipse.emf.cdo.CDOObject");
	}

}
