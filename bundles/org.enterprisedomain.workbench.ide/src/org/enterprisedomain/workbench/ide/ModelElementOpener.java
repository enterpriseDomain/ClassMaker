/**
 * Copyright 2019 Kyrill Zotkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.workbench.ide;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.editor.e3.ECPEditorContext;
import org.eclipse.emf.ecp.editor.e3.MEEditorInput;
import org.eclipse.emf.ecp.editor.internal.e3.MEEditor;
import org.eclipse.emf.ecp.explorereditorbridge.internal.EditorContext;
import org.eclipse.emf.ecp.ui.util.ECPModelElementOpener;
import org.eclipse.emf.emfstore.client.ESLocalProject;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.enterprisedomain.classmaker.Project;

public class ModelElementOpener implements ECPModelElementOpener {

	public static final String EDITOR_ID = "org.eclipse.emf.ecore.presentation.EcoreEditorID"; //$NON-NLS-1$
	public static final String MODEL_EDITOR_ID = "org.eclipse.emf.ecp.editor"; //$NON-NLS-1$
	private Resource resource;

	public ModelElementOpener() {
	}

	@Override
	public void openModelElement(Object modelElement, final ECPProject ecpProject) {
		resource = null;
		String fragment = null;
		if (modelElement instanceof Resource)
			resource = (Resource) modelElement;
		if (modelElement instanceof EObject) {
			if (((EObject) modelElement).eResource() != null) {
				resource = ((EObject) modelElement).eResource();
				fragment = resource.getURIFragment((EObject) modelElement);
			} else {
				Project project = Activator.getClassMaker().getWorkspace().getProject(ecpProject.getName());
				resource = (Resource) project.getChildren().get(0);
			}
		}
		if (resource != null)
			open(resource.getURI(), fragment, resource.getResourceSet(), ecpProject);
	}

	private IEditorPart open(URI uri, String fragment, ResourceSet resourceSet, ECPProject project) {
		if (uri.toString().endsWith("project") || uri.toString().endsWith("xmi"))
			return openEditor(uri, fragment, MODEL_EDITOR_ID, resourceSet, project);
		return openEditor(uri, fragment, EDITOR_ID, resourceSet, project);
	}

	private IEditorPart openEditor(URI uri, String fragment, String editorId, ResourceSet resourceSet,
			ECPProject project) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart result = null;
		try {
			EObject eObject = null;
			if (!resourceSet.getResource(uri, true).getContents().isEmpty())
				eObject = resourceSet.getResource(uri, true).getContents().get(0);
			if (fragment != null)
				eObject = resourceSet.getResource(uri, true).getEObject(fragment);
			EObject modelElement = null;
			if (eObject instanceof ESLocalProject)
				modelElement = ((ESLocalProject) eObject).getAllModelElements().iterator().next();
			else if (eObject instanceof EObject)
				modelElement = (EObject) eObject;
			final ECPEditorContext eec = new EditorContext(modelElement, project);
			result = IDE.openEditor(page, new MEEditorInput((EditorContext) eec), editorId);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return result;
	}

}
