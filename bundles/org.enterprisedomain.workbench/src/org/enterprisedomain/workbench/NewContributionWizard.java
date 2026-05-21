package org.enterprisedomain.workbench;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.enterprisedomain.classmaker.ClassMakerService;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class NewContributionWizard extends Wizard implements INewWizard {

	private WizardNewProjectCreationPage page;

	public NewContributionWizard() {
	}

	@Override
	public void addPages() {
		page = new WizardNewProjectCreationPage("newContributionPage");
		addPage(page);
		super.addPages();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}

	@Override
	public boolean performFinish() {
		final ClassMakerService classMaker = ClassMakerPlugin.getClassMaker();
		if (classMaker != null) {
			Project domainProject = classMaker.getWorkspace().getProject(page.getProjectName());
			if (domainProject != null) {
				try {
					IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
					domainProject.open(monitor);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
				domainProject.initialize();
				try {
					domainProject.load(false, true);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
			} else {
				IProgressMonitor monitor = ClassMakerPlugin.getProgressMonitor();
				EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
				EPackage model = ecoreFactory.createEPackage();
				model.setName(page.getProjectName());
				model.setNsPrefix(CodeGenUtil.capName(page.getProjectName().replaceAll(" ", "").toLowerCase()));
				model.setNsURI("http://" + page.getProjectName().replaceAll(" ", "") + "/1.0");
				EClass dummyEClass = ecoreFactory.createEClass();
				dummyEClass.setName("TheObject");
				EAttribute dummyEAttribute = ecoreFactory.createEAttribute();
				dummyEAttribute.setName("value");
				dummyEAttribute.setEType(EcorePackage.Literals.ESTRING);
				dummyEClass.getEStructuralFeatures().add(dummyEAttribute);
				EOperation op = ecoreFactory.createEOperation();
				op.setName("perform");
				op.setEType(EcorePackage.Literals.EINT);
				EAnnotation an = ecoreFactory.createEAnnotation();
				an.setSource("http://www.eclipse.org/emf/2002/GenModel");
				an.getDetails().put("body", "return 7;");
				op.getEAnnotations().add(an);
				EAnnotation invocation = ecoreFactory.createEAnnotation();
				invocation.setSource(ClassMakerService.INVOCATION_DELEGATE_URI);
				op.getEAnnotations().add(invocation);
				dummyEClass.getEOperations().add(op);
				model.getEClassifiers().add(dummyEClass);
				EAnnotation invocationDelegate = ecoreFactory.createEAnnotation();
				invocationDelegate.setSource(EcorePackage.eNS_URI);
				invocationDelegate.getDetails().put("invocationDelegates", ClassMakerService.INVOCATION_DELEGATE_URI);
				model.getEAnnotations().add(invocationDelegate);
				EClass queriesEClass = ecoreFactory.createEClass();
				queriesEClass.setName("Queries");
				EOperation query = ecoreFactory.createEOperation();
				query.setName("selectAllTheObjects");
				EClass eObjectEClass = EcorePackage.Literals.EOBJECT;
				query.setEType(eObjectEClass);
				query.setUpperBound(-1);
				EParameter param = ecoreFactory.createEParameter();
				param.setName("selectedEObjects");
				param.setEType(eObjectEClass);
				param.setUpperBound(-1);
				query.getEParameters().add(param);
				EAnnotation ann = ecoreFactory.createEAnnotation();
				ann.setSource("http://www.eclipse.org/emf/2002/GenModel");
				ann.getDetails().put("body", "if (null == selectedEObjects) {\n"
						+ "	throw new <%java.lang.NullPointerException%>(\"Argument is null\"); //$NON-NLS-1$\n" + "}\n"
						+ "\n" + "<%org.eclipse.emf.query.conditions.eobjects.EObjectCondition%> condition = "
						+ "new <%org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition%>("
						+ "(<%org.eclipse.emf.ecore.EClass%>) <%org.eclipse.emf.ecore.EPackage%>.Registry.INSTANCE.getEPackage(\"http://"
						+ page.getProjectName().replaceAll(" ", "") + "/1.0\")" + ".getEClassifier(\""
						+ dummyEClass.getName() + "\"));\n"
						+ "<%org.eclipse.emf.query.statements.SELECT%> statement = new <%org.eclipse.emf.query.statements.SELECT%>(\n"
						+ " new <%org.eclipse.emf.query.statements.FROM%>(selectedEObjects), \n"
						+ "	new <%org.eclipse.emf.query.statements.WHERE%>(condition)\n" + ");\n" + "\n"
						+ "return <%org.eclipse.emf.common.util.ECollections%>.toEList(statement.execute().getEObjects());");
				query.getEAnnotations().add(ann);
				EAnnotation in = ecoreFactory.createEAnnotation();
				in.setSource(ClassMakerService.INVOCATION_DELEGATE_URI);
				query.getEAnnotations().add(in);
				queriesEClass.getEOperations().add(query);
				model.getEClassifiers().add(queriesEClass);
				try {
					domainProject = classMaker.getWorkspace().createContribution(model, monitor);
					domainProject.getState().setEdit(true);
					domainProject.getState().setEditor(true);
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
			}
		}
		try {
			ResourcesPlugin.getWorkspace().getRoot().getProject(page.getProjectName()).refreshLocal(2,
					ClassMakerPlugin.getProgressMonitor());
		} catch (CoreException e) {
			ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
		}
		return true;
	}

}
