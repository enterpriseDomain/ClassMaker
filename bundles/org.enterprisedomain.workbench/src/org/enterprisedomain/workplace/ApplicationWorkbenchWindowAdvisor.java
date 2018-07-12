package org.enterprisedomain.workplace;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.ecp.core.util.ECPUtil;
import org.eclipse.emf.ecp.spi.core.DefaultProvider;
import org.eclipse.emf.ecp.spi.ui.DefaultUIProvider;
import org.eclipse.emf.ecp.spi.ui.UIProvider;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.core.IRunWrapper;
import org.enterprisedomain.classmaker.core.IRunnerWithProgress;
import org.enterprisedomain.ecp.EnterpriseDomainProvider;
import org.enterprisedomain.ecp.ui.EnterpriseDomainUIProvider;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private PrintStream oldOut;

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(800, 600));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(true);
		configurer.setTitle("enterpriseDomain Workbench"); //$NON-NLS-1$

		configurer.setShowProgressIndicator(true);
		ClassMakerPlugin.getInstance().setTurnOffAutoBuilding(true);
//		if (PlatformUI.isWorkbenchRunning())
//			PlatformUI.getWorkbench().addWindowListener(new IWindowListener() {
//
//				@Override
//				public void windowActivated(IWorkbenchWindow window) {
//
//				}
//
//				@Override
//				public void windowDeactivated(IWorkbenchWindow window) {
//
//				}
//
//				@Override
//				public void windowClosed(IWorkbenchWindow window) {
//
//				}
//
//				@Override
//				public void windowOpened(IWorkbenchWindow window) {
//					final EnterpriseDomainProvider provider = (EnterpriseDomainProvider) ECPUtil
//							.getECPProviderRegistry().getProvider(EnterpriseDomainProvider.NAME)
//							.getAdapter(DefaultProvider.class);
//					UIProvider uiProvider = (UIProvider) provider.getUIProvider();
//					EnterpriseDomainUIProvider enterpriseDomainUIProvider = (EnterpriseDomainUIProvider) uiProvider
//							.getAdapter(uiProvider, DefaultUIProvider.class);
//					enterpriseDomainUIProvider.setClientRunWrapper(new IRunWrapper() {
//
//						@Override
//						public void wrapRun(Runnable runnable) {
//							Display display = Display.getCurrent() != null ? Display.getCurrent()
//									: Display.getDefault();
//							display.asyncExec(runnable);
//						}
//
//					});
//
//					enterpriseDomainUIProvider.setRunnerWithProgress(new IRunnerWithProgress() {
//
//						@Override
//						public void run(final IRunnableWithProgress runnable)
//								throws InvocationTargetException, InterruptedException {
//							Display.getCurrent().asyncExec(new Runnable() {
//
//								@Override
//								public void run() {
//									try {
//										PlatformUI.getWorkbench().getActiveWorkbenchWindow().run(true, true, runnable);
//									} catch (InvocationTargetException e) {
//										Activator.log(e.getTargetException());
//									} catch (InterruptedException e) {
//										return;
// 									}
//								}
//							});
//
//						}
//					});
//					enterpriseDomainUIProvider.setProgressMonitor(
//							getWindowConfigurer().getActionBarConfigurer().getStatusLineManager().getProgressMonitor());
//				}
//
//			});
	}

	@Override
	public void postWindowCreate() {
		oldOut = System.out;
		MessageConsole out = new MessageConsole("Output", null);
		System.setOut(new PrintStream(out.newOutputStream()));
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { out });
		ConsolePlugin.getDefault().getConsoleManager().refresh(out);
	}

	@Override
	public void postWindowClose() {
		System.setOut(oldOut);
	}

}
