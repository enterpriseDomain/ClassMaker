package org.enterprisedomain.workbench.ide;

import java.net.URISyntaxException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.ui.util.ECPModelElementOpener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.enterprisedomain.classmaker.Contribution;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class ModelElementOpener implements ECPModelElementOpener {

	public static final String EDITOR_ID = "org.eclipse.emf.ecore.presentation.EcoreEditorID"; //$NON-NLS-1$
	private Resource resource;

	public ModelElementOpener() {
	}

	@Override
	public void openModelElement(Object modelElement, final ECPProject ecpProject) {
		resource = null;
		if (modelElement instanceof Resource)
			resource = (Resource) modelElement;
		if (modelElement instanceof EObject) {
			if (((EObject) modelElement).eResource() != null)
				resource = ((EObject) modelElement).eResource();
			else {
				Project project = Activator.getClassMaker().getWorkspace().getProject(ecpProject.getName());
				resource = (Resource) project.getChildren().get(0);
			}
		}
		if (resource != null)
			open(resource.getURI(), resource.getResourceSet());
	}

	private IEditorPart open(URI uri, ResourceSet resourceSet) {
		return openEditor(uri, EDITOR_ID, resourceSet);
	}

	private IEditorPart openEditor(URI uri, String editorId, ResourceSet resourceSet) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart result = null;
		try {
			result = IDE.openEditor(page, new java.net.URI(resourceSet.getURIConverter().normalize(uri).toString()),
					editorId, true);
			EList<Adapter> target = ((EcoreEditor) result).getEditingDomain().getResourceSet().eAdapters();
			for (Adapter adapter : Activator.getClassMaker().getWorkspace().getResourceSet().eAdapters())
				if (adapter instanceof EContentAdapter && !target.contains(adapter))
					target.add(adapter);
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return result;
	}

}
