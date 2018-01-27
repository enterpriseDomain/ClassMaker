package org.enterprisedomain.workplace;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emfforms.spi.editor.GenericEditor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

public class ModelEditor extends MultiPageEditorPart {

	private int genericEditorIndex;

	public ModelEditor() {
	}

	@Override
	protected void createPages() {
		try {
			genericEditorIndex = addPage(new GenericEditor(), getEditorInput());
			setPageText(genericEditorIndex, "Form"); //$NON-NLS-1$
		} catch (PartInitException e) {
			Activator.log(e);
		}
	}

	public GenericEditor getGenericEditor() {
		return (GenericEditor) getEditor(genericEditorIndex);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		getActiveEditor().doSave(monitor);
	}

	@Override
	public void doSaveAs() {
		getActiveEditor().doSaveAs();

	}

	@Override
	public boolean isSaveAsAllowed() {
		return getActiveEditor().isSaveAsAllowed();
	}

}
