package org.enterprisedomain.workplace;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.emf.ecore.EObject;
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
		EObject model = (EObject) modelElement;
		if (model instanceof EObject)
			if (model.eResource().getURI().lastSegment().equals("ecore"))
				openEditor(model, "EcoreEditor");
			else
				openEditor(model, "org.eclipse.emfforms.editor.ecore.genericxmieditor");

	}

	private void openEditor(EObject object, String editorId) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IDE.openEditor(page, new URI(object.eResource().getURI().toString()), editorId, true);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
