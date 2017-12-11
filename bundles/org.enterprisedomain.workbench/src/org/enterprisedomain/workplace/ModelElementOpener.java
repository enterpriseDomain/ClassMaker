package org.enterprisedomain.workplace;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.core.util.ECPUtil;
import org.eclipse.emf.ecp.core.util.observer.ECPProjectContentChangedObserver;
import org.eclipse.emf.ecp.core.util.observer.ECPProjectContentTouchedObserver;
import org.eclipse.emf.ecp.core.util.observer.ECPProvidersChangedObserver;
import org.eclipse.emf.ecp.spi.core.DefaultProvider;
import org.eclipse.emf.ecp.spi.core.InternalProject;
import org.eclipse.emf.ecp.ui.util.ECPModelElementOpener;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emfforms.spi.editor.GenericEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.enterprisedomain.ecp.EnterpriseDomainProvider;

public class ModelElementOpener implements ECPModelElementOpener {

	public static final String[] EDITOR_IDS = { "EcoreEditor", "org.eclipse.emfforms.editor.ecore.genericxmieditor" }; //$NON-NLS-1$ //$NON-NLS-2$
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
			else
				resource = (Resource) Activator.getClassMaker().getWorkspace().getProject(ecpProject.getName())
						.getChildren().get(0);
		}
		if (resource != null)
			editor = open(resource.getURI());
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

	private IEditorPart open(URI uri) {
		if (uri.lastSegment().equals("ecore")) //$NON-NLS-1$
			return openEditor(uri, EDITOR_IDS[0]);
		else
			return openEditor(uri, EDITOR_IDS[1]);
	}

	private IEditorPart openEditor(URI uri, String editorId) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart result = null;
		try {
			result = IDE.openEditor(page,
					new java.net.URI(
							"file" + IPath.DEVICE_SEPARATOR + IPath.SEPARATOR + IPath.SEPARATOR + uri.toFileString()), //$NON-NLS-1$
					editorId, true);
			EList<Adapter> target = ((GenericEditor) result).getEditingDomain().getResourceSet().eAdapters();
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
