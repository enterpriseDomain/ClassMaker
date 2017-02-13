package org.enterprisedomain.ui.workbench.rcp.editor;

import org.eclipse.emfforms.spi.editor.shared.GenericEditorSharedStrategy;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeMasterDetailComposite;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeMasterDetailSWTFactory;
import org.eclipse.emfforms.spi.swt.treemasterdetail.util.CreateElementCallback;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class E4GenericEditorStrategy extends GenericEditorSharedStrategy {

	@Override
	public TreeMasterDetailComposite createTreeMasterDetail(Composite composite, Object editorInput,
			CreateElementCallback createElementCallback) {
		setTreeMasterDetail(TreeMasterDetailSWTFactory.createTreeMasterDetail(composite, SWT.NONE, editorInput,
				new E4GenericEditorTMDCustomization(createElementCallback)));
		return getTreeMasterDetail();
	}

}
