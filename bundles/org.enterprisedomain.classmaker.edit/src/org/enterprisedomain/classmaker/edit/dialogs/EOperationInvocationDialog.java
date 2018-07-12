package org.enterprisedomain.classmaker.edit.dialogs;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.emf.ecp.view.internal.swt.Activator;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContextFactory;
import org.eclipse.emf.ecp.view.spi.provider.ViewProviderHelper;
import org.eclipse.emf.ecp.view.spi.swt.reporting.RenderingFailedReport;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class EOperationInvocationDialog extends Dialog {

	private final EOperation eOperation;
	
	public EOperationInvocationDialog(Shell shell, EOperation eOperation) {
		super(shell);
		this.eOperation = eOperation;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(eOperation.getName());
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite composite = (Composite) super.createDialogArea(parent);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).hint(450, 250).applyTo(composite);

		final ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(scrolledComposite);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);

		final Composite content = new Composite(scrolledComposite, SWT.NONE);
		GridLayoutFactory.fillDefaults().applyTo(content);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(content);

		final ViewModelContext vmc = ViewModelContextFactory.INSTANCE
				.createViewModelContext(ViewProviderHelper.getView(eOperation, null), eOperation);
		try {
			ECPSWTViewRenderer.INSTANCE.render(content, vmc);
		} catch (final ECPRendererException ex) {
			Activator.getDefault().getReportService().report(new RenderingFailedReport(ex));
		}

		scrolledComposite.setContent(content);
		final Point point = content.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		scrolledComposite.setMinSize(point);
		scrolledComposite.layout(true);

		return composite;
	}

	public EOperation getEOperation() {
		return eOperation;
	}
}
