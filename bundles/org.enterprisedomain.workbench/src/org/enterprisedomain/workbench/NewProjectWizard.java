package org.enterprisedomain.workbench;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class NewProjectWizard extends Wizard implements INewWizard {

	public static final String PROJECT_NAME = Activator.PLUGIN_ID + ".projectName";

	private WizardNewProjectCreationPage page;

	public NewProjectWizard() {
	}

	@Override
	public void addPages() {
		page = new WizardNewProjectCreationPage("newProjectPage");
		addPage(page);
		super.addPages();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}

	public String getProjectName() {
		return page.getProjectName();
	}

	@Override
	public boolean performFinish() {
		final ClassMakerService classMaker = ClassMakerPlugin.getClassMaker();
		System.setProperty(PROJECT_NAME, getProjectName());
		if (classMaker != null) {
			Project domainProject = classMaker.getWorkspace().getProject(getProjectName());
			if (domainProject != null) {
				try {
					IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
					domainProject.open(monitor);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
				domainProject.initialize(false);
				try {
					domainProject.load(false, true);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
			} else {
				IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
				try {
					domainProject = classMaker.getWorkspace().createProject(getProjectName(), monitor);
					((Resource) domainProject.getChildren().get(0)).getContents()
							.add(EcoreFactory.eINSTANCE.createEObject());
					domainProject.initialize(true);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
			}
		}
		try {
			ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()).refreshLocal(1,
					ClassMakerPlugin.getProgressMonitor());
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		}
		return true;
	}
}
