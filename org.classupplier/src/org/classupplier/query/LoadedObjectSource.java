package org.classupplier.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.classupplier.Artifact;
import org.classupplier.ClasSupplierPackage;
import org.classupplier.Infrastructure;
import org.classupplier.State;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.query.conditions.eobjects.EObjectSource;

public class LoadedObjectSource extends EObjectSource {

	public LoadedObjectSource(Infrastructure modelWorkspace) {
		super(collectEObjects(modelWorkspace));

	}

	private static Collection<? extends EObject> collectEObjects(
			Infrastructure modelWorkspace) {
		List<EObject> results = new ArrayList<EObject>();
		for (Artifact artifact : modelWorkspace.getArtifacts()) {
			if (artifact.getState().equals(State.COMPLETE)
					&& artifact
							.eIsSet(ClasSupplierPackage.Literals.ARTIFACT__LOADED_EPACKAGE))
				results.add(artifact.getLoadedEPackage());
		}
		return results;
	}

}
