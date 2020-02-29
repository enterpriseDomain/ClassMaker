package org.enterprisedomain.ecp.ui;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.enterprisedomain.classmaker.impl.SelectRevealHandlerImpl;

public class SelectResourceHandler extends SelectRevealHandlerImpl {

	private ISelection selection;
	private TreePath[] paths;
	private TreePath[] expandedPaths;
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
		part = page.findView(partId);
		if (part instanceof IViewerProvider) {
			Viewer viewer = ((IViewerProvider) part).getViewer();
			expandedPaths = ((TreeViewer) viewer).getExpandedTreePaths();
			if (selection instanceof ITreeSelection) {
				this.paths = ((ITreeSelection) selection).getPathsFor(((ITreeSelection) selection).getFirstElement());
			}
		}
	}

	@Override
	public void selectReveal(Resource newResource) {
		if (selection == null)
			return;
		TreeIterator<Object> newIterator = EcoreUtil.getAllProperContents(newResource, true);
		while (newIterator.hasNext()) {
			Object newObject = newIterator.next();
			if (selection instanceof ITreeSelection) {
				TreePath[] paths = ((ITreeSelection) selection).getPathsFor(newObject);
				for (TreePath path : this.paths)
					for (TreePath objectPath : paths)
						if (path.equals(objectPath)) {
							selectReveal(path);
							break;
						}
			}
		}
	}

	private void selectReveal(TreePath path) {
		if (part instanceof IViewerProvider) {
			Viewer viewer = ((IViewerProvider) part).getViewer();
			if (viewer != null) {
				((TreeViewer) viewer).setExpandedTreePaths(expandedPaths);
				viewer.setSelection(new TreeSelection(path), true);
			}
		}
	}

}
