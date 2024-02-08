package org.enterprisedomain.classmaker.cdo.ui;

import org.eclipse.emf.cdo.explorer.ui.checkouts.CDOModelEditorInput;
import org.eclipse.emf.cdo.transaction.CDOCommitContext;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.transaction.CDOTransactionHandler2;
import org.eclipse.emf.cdo.ui.CDOEditorUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class CDOEditorOpener extends org.eclipse.emf.cdo.ui.CDOEditorOpener.Default {

	public CDOEditorOpener() {
	}

	@Override
	protected IEditorPart doOpenEditor(IWorkbenchPage page, URI uri) {
		CDOModelEditorInput editorInput = new CDOModelEditorInput(uri) {

			@Override
			protected void configureTransaction(CDOTransaction transaction) {
				transaction.addTransactionHandler(new CDOTransactionHandler2() {

					@Override
					public void rolledBackTransaction(CDOTransaction transaction) {
						System.out.println("rolledBackTransaction " + transaction);
					}

					@Override
					public void committingTransaction(CDOTransaction transaction, CDOCommitContext commitContext) {
						System.out.println("committingTransaction " + transaction);
					}

					@Override
					public void committedTransaction(CDOTransaction transaction, CDOCommitContext commitContext) {
						System.out.println("committedTransaction " + transaction);
					}
				});
				super.configureTransaction(transaction);
			}

		};
		String editorID = CDOEditorUtil.getEditorID();
		try {
			return page.openEditor(editorInput, editorID);
		} catch (Exception ex) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(ex));
			return null;
		}
	}

}
