package org.enterprisedomain.classmaker.impl;

import org.eclipse.emf.common.util.EList;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Workspace;

public class WorkspaceInitCustomizer extends CustomizerImpl implements Customizer {

	public WorkspaceInitCustomizer() {
	}

	@Override
	public Object customize(EList<Object> args) {
		Workspace workspace = (Workspace) args.get(0);
		workspace.getResourceSet().setURIConverter(new ResourceSetURIConverter());
		return super.customize(args);
	}

}
