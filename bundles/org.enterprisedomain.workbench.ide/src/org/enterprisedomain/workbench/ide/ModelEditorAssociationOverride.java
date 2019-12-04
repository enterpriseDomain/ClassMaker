package org.enterprisedomain.workbench.ide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IEditorAssociationOverride;

public class ModelEditorAssociationOverride implements IEditorAssociationOverride {

	@Override
	public IEditorDescriptor overrideDefaultEditor(IEditorInput editorInput, IContentType contentType,
			IEditorDescriptor editorDescriptor) {
		if (contentType.equals(Platform.getContentTypeManager().getContentType("org.eclipse.emf.ecore.xmi"))
				|| contentType.equals(Platform.getContentTypeManager().getContentType("org.eclipse.emf.ecore")))
			return PlatformUI.getWorkbench().getEditorRegistry().findEditor(ModelElementOpener.EDITOR_ID);
		return null;
	}

	@Override
	public IEditorDescriptor overrideDefaultEditor(String fileName, IContentType contentType,
			IEditorDescriptor editorDescriptor) {
		if (fileName.endsWith("xmi") || fileName.endsWith("ecore"))
			return PlatformUI.getWorkbench().getEditorRegistry().findEditor(ModelElementOpener.EDITOR_ID);
		return null;
	}

	@Override
	public IEditorDescriptor[] overrideEditors(IEditorInput editorInput, IContentType contentType,
			IEditorDescriptor[] editorDescriptors) {
		List<IEditorDescriptor> results = new ArrayList<IEditorDescriptor>();
		for (IEditorDescriptor e : editorDescriptors)
			results.add(e);
		if (contentType.equals(Platform.getContentTypeManager().getContentType("org.eclipse.emf.ecore.xmi"))
				|| contentType.equals(Platform.getContentTypeManager().getContentType("org.eclipse.emf.ecore"))) {
			results.add(PlatformUI.getWorkbench().getEditorRegistry().findEditor(ModelElementOpener.EDITOR_ID));
		}
		return results.toArray(new IEditorDescriptor[results.size()]);
	}

	@Override
	public IEditorDescriptor[] overrideEditors(String fileName, IContentType contentType,
			IEditorDescriptor[] editorDescriptors) {
		List<IEditorDescriptor> results = new ArrayList<IEditorDescriptor>();
		for (IEditorDescriptor e : editorDescriptors)
			results.add(e);
		if (fileName.endsWith("xmi") || fileName.endsWith("ecore")) {
			results.add(PlatformUI.getWorkbench().getEditorRegistry().findEditor(ModelElementOpener.EDITOR_ID));
		}
		return results.toArray(new IEditorDescriptor[results.size()]);
	}

}
