package org.enterprisedomain.ui.workbench.rcp.editor;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.core.util.ECPModelContextProvider;
import org.eclipse.emf.ecp.core.util.ECPUtil;
import org.eclipse.emf.ecp.ui.e4.util.EPartServiceHelper;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContextFactory;
import org.eclipse.emf.ecp.view.spi.context.ViewModelService;
import org.eclipse.emfforms.editor.e4.E4GenericEditorOpener;
import org.eclipse.emfforms.spi.editor.IEditorSharedStrategy;
import org.eclipse.emfforms.spi.swt.core.di.EMFFormsContextProvider;

public class CustomEditorOpener extends E4GenericEditorOpener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emfforms.editor.e4.E4EditorOpener#openModelElement(java.lang.
	 * Object, org.eclipse.emf.ecp.core.ECPProject)
	 */
	@Override
	public void openModelElement(Object modelElement, ECPProject ecpProject) {
		IEclipseContext context = EPartServiceHelper.getEPartService().getActivePart().getContext().getParent();
		context.set(IEditorSharedStrategy.class, new E4GenericEditorStrategy());
		super.openModelElement(modelElement, ecpProject);
	}

}
