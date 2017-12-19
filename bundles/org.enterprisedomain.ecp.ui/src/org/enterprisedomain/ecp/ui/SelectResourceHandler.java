package org.enterprisedomain.ecp.ui;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.enterprisedomain.classmaker.impl.SelectRevealHandlerImpl;

public class SelectResourceHandler extends SelectRevealHandlerImpl {

	private Object selection;
	private Object part;

	@Override
	public void prepare() {
		String partId = "org.eclipse.emf.ecp.ui.ModelExplorerView";
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = page.getSelection(partId);
		if (selection.isEmpty()) {
			this.selection = null;
			return;
		}
		if (selection instanceof IStructuredSelection)
			this.selection = ((IStructuredSelection) selection).getFirstElement();
		part = page.findView(partId);
	}

	@Override
	public void selectReveal(Resource newResource) {
		if (selection == null)
			return;
		if (!(selection instanceof EObject))
			return;
		TreeIterator<Object> newIterator = EcoreUtil.getAllProperContents(newResource, true);
		while (newIterator.hasNext()) {
			Object newObject = newIterator.next();
			if (EcoreUtil.equals((EObject) newObject, (EObject) selection)) {
				selectReveal(newObject);
				break;
			}
		}
	}

	private void selectReveal(Object newObject) {
		if (part instanceof ISetSelectionTarget) {
			((ISetSelectionTarget) part).selectReveal(new StructuredSelection(newObject));
		} else if (part instanceof IViewerProvider) {
			Viewer viewer = ((IViewerProvider) part).getViewer();
			if (viewer != null) {
				viewer.setSelection(new StructuredSelection(newObject), true);
			}
		}
	}

}
