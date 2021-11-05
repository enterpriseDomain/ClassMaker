package org.enterprisedomain.workbench;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.core.ECPProvider;
import org.eclipse.emf.ecp.core.exceptions.ECPProjectWithNameExistsException;
import org.eclipse.emf.ecp.core.util.ECPProperties;
import org.eclipse.emf.ecp.core.util.ECPUtil;
import org.eclipse.emf.ecp.internal.wizards.CreateProjectWizard;
import org.eclipse.emf.ecp.spi.core.InternalProvider;
import org.eclipse.emf.ecp.spi.core.InternalProvider.LifecycleEvent;
import org.eclipse.emf.ecp.ui.common.CreateProjectComposite;
import org.eclipse.emf.ecp.ui.common.ECPCompositeFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.ecp.ui.EnterpriseDomainUIProvider;

@SuppressWarnings("restriction")
public class NewECPProjectWizard extends Wizard implements INewWizard {

	private CreateProjectWizard backingWizard;
	private CreateProjectComposite createProjectComposite;

	public NewECPProjectWizard() {
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	@Override
	public void addPages() {
		super.addPages();
		if (backingWizard == null) {
			backingWizard = new CreateProjectWizard();
			List<ECPProvider> providers = new ArrayList<ECPProvider>();
			for (ECPProvider provider : ECPUtil.getECPProviderRegistry().getProviders())
				if (provider.hasCreateProjectWithoutRepositorySupport())
					providers.add(provider);
			createProjectComposite = ECPCompositeFactory.getCreateProjectComposite(providers);
			System.getProperties().setProperty(EnterpriseDomainUIProvider.PROP_DISABLE_CONTRIBUTION,
					Boolean.toString(false));
			backingWizard.setCompositeProvider(createProjectComposite);
			backingWizard.setContainer(getContainer());
		}
		backingWizard.addPages();
		for (IWizardPage page : backingWizard.getPages())
			addPage(page);
	}

	@Override
	public boolean performFinish() {
		final List<ECPProvider> providers = new ArrayList<ECPProvider>();
		for (final ECPProvider provider : ECPUtil.getECPProviderRegistry().getProviders()) {
			if (provider.hasCreateProjectWithoutRepositorySupport()) {
				providers.add(provider);
			}
		}
		if (providers.size() == 0) {
			showError("No Provider", "Please check if a suitable provider is installed."); //$NON-NLS-1$//$NON-NLS-2$
			return true;
		}

		final ECPProvider selectedProvider = createProjectComposite.getProvider();
		if (selectedProvider == null) {
			showError("No project created", "Please check if a suitable provider is installed."); //$NON-NLS-1$//$NON-NLS-2$
			return true;
		}

		final ECPProperties projectProperties = createProjectComposite.getProperties();
		final String projectName = createProjectComposite.getProjectName();

		ECPProject project = null;
		try {
			project = ECPUtil.getECPProjectManager().createProject(selectedProvider, projectName, projectProperties);
		} catch (final ECPProjectWithNameExistsException ex) {
			showError("No project created", "A project with name " + projectName //$NON-NLS-1$ //$NON-NLS-2$
					+ " already exists in the workspace."); //$NON-NLS-1$
			return true;
		}
		if (project == null) {
			showError("No project created", "Please check the log."); //$NON-NLS-1$//$NON-NLS-2$
			return true;
		}
		((InternalProvider) selectedProvider).handleLifecycle(project, LifecycleEvent.CREATE);
		project.open();
		try {
			ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).refreshLocal(2,
					ClassMakerPlugin.getProgressMonitor());
		} catch (CoreException e) {
			showError("Error refreshing", e.getLocalizedMessage());
			return true;
		}
		return true;
	}

	private void showError(String title, String message) {
		MessageDialog.openError(getShell(), title, message);
	}

}
