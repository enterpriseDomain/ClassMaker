package org.enterprisedomain.workplace;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecp.core.util.ECPUtil;
import org.eclipse.emf.ecp.spi.core.InternalProject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.emfforms.spi.editor.GenericEditor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.enterprisedomain.classmaker.Project;

public class ModelEditor extends MultiPageEditorPart {

	private int genericEditorIndex;
	private InternalProject ecpProject;

	public ModelEditor() {
	}

	@Override
	protected void createPages() {
		try {
			genericEditorIndex = addPage(new GenericEditor(), getEditorInput());
			setPageText(genericEditorIndex, "Form"); //$NON-NLS-1$
			getGenericEditor().getResourceSet().eAdapters()
					.add(new AdapterFactoryEditingDomain.EditingDomainProvider(getGenericEditor().getEditingDomain()));
			URI uri = EditUIUtil.getURI(getEditorInput());
			final Resource resource = Activator.getClassMaker().getWorkspace().getResourceSet().getResource(uri, true);
			final Project project = Activator.getClassMaker().getWorkspace().getProject(resource);
			try {
				ecpProject = (InternalProject) ECPUtil.getECPProjectManager().getProject(project.getProjectName());
			} catch (RuntimeException e) {
			}
			getGenericEditor().getResourceSet().eAdapters().add(new EContentAdapter() {

				@Override
				public void notifyChanged(Notification notification) {
					super.notifyChanged(notification);
					Collection<Object> objects = (Collection<Object>) (Collection<?>) Arrays.asList(ecpProject);
					if (ecpProject != null) {
						ecpProject.notifyObjectsChanged(objects, true);
					}
				}

			});
		} catch (PartInitException e) {
			Activator.log(e);
		}
	}

	public GenericEditor getGenericEditor() {
		if (!PlatformUI.isWorkbenchRunning())
			return null;
		return (GenericEditor) getEditor(genericEditorIndex);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		getActiveEditor().doSave(monitor);
		// for (Resource editedResource :
		// getGenericEditor().getResourceSet().getResources())
		// for (Resource resource :
		// Activator.getClassMaker().getWorkspace().getResourceSet().getResources())
		// if (editedResource.getURI().equals(resource.getURI())) {
		// resource.unload();
		// try {
		// resource.load(Collections.emptyMap());
		// } catch (IOException e) {
		// Activator.log(e);
		// }
		// }
	}

	@Override
	public void doSaveAs() {
		getActiveEditor().doSaveAs();
	}

	@Override
	public boolean isSaveAsAllowed() {
		if (getActiveEditor() == null)
			if (getGenericEditor() == null)
				return false;
			else
				return getGenericEditor().isSaveAsAllowed();
		else
			return getActiveEditor().isSaveAsAllowed();
	}

}
