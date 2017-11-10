package org.enterprisedomain.workplace;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecp.core.util.ECPUtil;
import org.eclipse.emf.ecp.spi.core.DefaultProvider;
import org.eclipse.emf.ecp.spi.ui.UIProvider;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.progress.UIJob;
import org.enterprisedomain.classmaker.core.IProgressRunner;
import org.enterprisedomain.classmaker.core.IRunWrapper;
import org.enterprisedomain.ecp.ui.EnterpriseDomainUIProvider;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

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
	}

	@Override
	public void postWindowOpen() {
		final String ecpProviderName = "org.enterprisedomain.ecp.provider";
		UIProvider uiProvider = (UIProvider) ((DefaultProvider) ECPUtil.getECPProviderRegistry()
				.getProvider(ecpProviderName).getAdapter(DefaultProvider.class)).getUIProvider();
		EnterpriseDomainUIProvider enterpriseDomainUIProvider = ((EnterpriseDomainUIProvider) uiProvider
				.getAdapter(uiProvider, EnterpriseDomainUIProvider.class));
		enterpriseDomainUIProvider.setClientRunWrapper(new IRunWrapper() {

			@Override
			public void wrapRun(Runnable runnable) {
				Display display = Display.getCurrent() != null ? Display.getCurrent() : Display.getDefault();
				display.asyncExec(runnable);
			}
			
		});

		enterpriseDomainUIProvider.setProgressRunner(new IProgressRunner() {

			@Override
			public void run(final IRunnableWithProgress runnable)
					throws InvocationTargetException, InterruptedException {
				Display.getCurrent().asyncExec(new Runnable() {

					@Override
					public void run() {
						try {
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().run(true, true, runnable);
						} catch (InvocationTargetException e) {
							Activator.log(e.getTargetException());
						} catch (InterruptedException e) {
							return;
						}
					}
				});

			}
		});
		enterpriseDomainUIProvider.setProgressMonitor(
				getWindowConfigurer().getActionBarConfigurer().getStatusLineManager().getProgressMonitor());
	}

}
