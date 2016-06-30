package org.classupplier.jobs.codegen;

import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.ecore.EPackage;

public class CDOGenerator extends EcoreGenerator {

	protected static void setupGenModel(IPath projectPath,
			org.eclipse.emf.codegen.ecore.genmodel.GenModel ecoreGenModel, Collection<EPackage> ePackages) {
		EcoreGenerator.setupGenModel(projectPath, ecoreGenModel, ePackages);
		ecoreGenModel.setSuppressInterfaces(false);
		ecoreGenModel.setFeatureDelegation(GenDelegationKind.REFLECTIVE_LITERAL);
		ecoreGenModel.getModelPluginVariables().add("CDO=org.eclipse.emf.cdo"); //$NON-NLS-1$
		ecoreGenModel.setRootExtendsClass("org.eclipse.emf.internal.cdo.CDOObjectImpl"); //$NON-NLS-1$
		ecoreGenModel.setRootExtendsInterface("org.eclipse.emf.cdo.CDOObject"); //$NON-NLS-1$
	}

}
