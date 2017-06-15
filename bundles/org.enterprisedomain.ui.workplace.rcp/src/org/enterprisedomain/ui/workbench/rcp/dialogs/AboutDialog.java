package org.enterprisedomain.ui.workbench.rcp.dialogs;

import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.internal.ProductProperties;

public class AboutDialog extends TrayDialog {

	private StyledText text;

	protected AboutDialog(IShellProvider parentShell) {
		super(parentShell);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridLayout layout = (GridLayout) parent.getLayout();
		layout.numColumns++;
		layout.makeColumnsEqualWidth = false;

		Button b = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		b.setFocus();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		String aboutText = "Enterprise Domain Workbench";

		Color background = JFaceColors.getBannerBackground(parent.getDisplay());
		Color foreground = JFaceColors.getBannerForeground(parent.getDisplay());

		Composite workArea = new Composite(parent, SWT.NONE);
		GridLayout workLayout = new GridLayout();
		workLayout.marginHeight = 0;
		workLayout.marginWidth = 0;
		workLayout.verticalSpacing = 0;
		workLayout.horizontalSpacing = 0;
		workArea.setLayout(workLayout);
		workArea.setLayoutData(new GridData(GridData.FILL_BOTH));

		final int minWidth = 400;
		final ScrolledComposite scroller = new ScrolledComposite(workArea, SWT.V_SCROLL | SWT.H_SCROLL);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = minWidth;
		scroller.setLayoutData(data);

		final Composite textComposite = new Composite(scroller, SWT.NONE);
		textComposite.setBackground(background);

		GridLayout layout = new GridLayout();
		textComposite.setLayout(layout);

		text = new StyledText(textComposite, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);

		text.setFont(parent.getFont());
		text.setText(aboutText);
		text.setCursor(null);
		text.setBackground(background);
		text.setForeground(foreground);

		GridData gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		text.setLayoutData(gd);

		scroller.getHorizontalBar().setIncrement(20);
		scroller.getVerticalBar().setIncrement(20);

		final boolean[] inresize = new boolean[1];
		textComposite.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				if (inresize[0]) {
					return;
				}
				inresize[0] = true;
				textComposite.layout(true);
				int width = textComposite.getClientArea().width;
				Point p = textComposite.computeSize(width, SWT.DEFAULT);
				scroller.setMinSize(minWidth, p.y);
				inresize[0] = false;
			}
		});

		scroller.setExpandHorizontal(true);
		scroller.setExpandVertical(true);
		Point p = textComposite.computeSize(minWidth, SWT.DEFAULT);
		textComposite.setSize(p.x, p.y);
		scroller.setMinWidth(minWidth);
		scroller.setMinHeight(p.y);

		scroller.setContent(textComposite);
		return workArea;
	}

	public static void open(IShellProvider shell) {
		new AboutDialog(shell).open();
	}

}
