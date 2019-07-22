package org.enterprisedomain.workbench.ide;

import java.net.URISyntaxException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
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

	public static final String[] EDITOR_IDS = { "EcoreEditor", "org.eclipse.emfforms.editor.ecore.genericxmieditor", //$NON-NLS-1$ //$NON-NLS-2$
			"org.enterprisedomain.workbench.ide.modelEditor" }; //$NON-NLS-1$
	private Resource resource;

	public ModelElementOpener() {
	}

	@Override
	public void openModelElement(Object modelElement, final ECPProject ecpProject) {
		IEditorPart editor = null;
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
			editor = open(resource.getURI(), resource.getResourceSet());
		// if (editor != null)
		// editor.addPropertyListener(new IPropertyListener() {
		//
		// @SuppressWarnings("unchecked")
		// @Override
		// public void propertyChanged(Object source, int propId) {
		// if (propId == ISaveablePart.PROP_DIRTY) {
		// if (!((IEditorPart) source).isDirty())
		// ((InternalProject) ecpProject).notifyObjectsChanged(
		// (Collection<Object>) (Collection<?>) Collections.singleton(ecpProject),
		// true);
		// }
		//
		// }
		// });

	}

	private IEditorPart open(URI uri, ResourceSet resourceSet) {
		return openEditor(uri, EDITOR_IDS[2], resourceSet);
		// if (uri.lastSegment().equals("ecore")) //$NON-NLS-1$
		// return openEditor(uri, EDITOR_IDS[0]);
		// else
		// return openEditor(uri, EDITOR_IDS[1]);
	}

	private IEditorPart openEditor(URI uri, String editorId, ResourceSet resourceSet) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart result = null;
		try {
			result = IDE.openEditor(page, new java.net.URI(resourceSet.getURIConverter().normalize(uri).toString()),
					editorId, true);
			EList<Adapter> target = ((ModelEditor) result).getGenericEditor().getEditingDomain().getResourceSet()
					.eAdapters();
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
