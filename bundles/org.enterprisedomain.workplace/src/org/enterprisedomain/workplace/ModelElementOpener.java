package org.enterprisedomain.workplace;

import java.net.URISyntaxException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.ui.util.ECPModelElementOpener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class ModelElementOpener implements ECPModelElementOpener {

	public ModelElementOpener() {
	}

	@Override
	public void openModelElement(Object modelElement, ECPProject ecpProject) {
		if (modelElement instanceof Resource)
			open(((Resource) modelElement).getURI());
		if (modelElement instanceof EObject)
			open(((EObject) modelElement).eResource().getURI());

	}

	private void open(URI uri) {
		if (uri.lastSegment().equals("ecore"))
			openEditor(uri, "EcoreEditor");
		else
			openEditor(uri, "org.eclipse.emfforms.editor.ecore.genericxmieditor");
	}

	private void openEditor(URI uri, String editorId) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IDE.openEditor(page, new java.net.URI(uri.toString()), editorId, true);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
