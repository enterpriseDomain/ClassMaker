package org.classupplier.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.classupplier.Artifact;
import org.classupplier.Infrastructure;
import org.classupplier.Phase;
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
			if (artifact.getStage().equals(Phase.LOADED))
				results.add(artifact.getLoadedEPackage());
		}
		return results;
	}
}
