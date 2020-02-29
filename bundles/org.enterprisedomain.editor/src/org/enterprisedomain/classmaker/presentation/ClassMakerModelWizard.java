/**
 * Copyright 2017 Kyrill Zotkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.presentation;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.provider.ClassMakerEditPlugin;

/**
 * This is a simple wizard for creating a new model file.
 * <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * @generated
 */
public class ClassMakerModelWizard extends Wizard implements INewWizard {
	/**
	 * The supported extensions for created files.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static final List<String> FILE_EXTENSIONS = Collections.unmodifiableList(Arrays.asList(
			ClassMakerEditorPlugin.INSTANCE.getString("_UI_ClassMakerEditorFilenameExtensions").split("\\s*,\\s*")));

	/**
	 * A formatted list of supported file extensions, suitable for display. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String FORMATTED_FILE_EXTENSIONS = ClassMakerEditorPlugin.INSTANCE
			.getString("_UI_ClassMakerEditorFilenameExtensions").replaceAll("\\s*,\\s*", ", ");

	/**
	 * This caches an instance of the model package.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected ClassMakerPackage classMakerPackage = ClassMakerPackage.eINSTANCE;

	/**
	 * This caches an instance of the model factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected ClassMakerFactory classMakerFactory = classMakerPackage.getClassMakerFactory();

	/**
	 * This is the initial object creation page.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected ClassMakerModelWizardInitialObjectCreationPage initialObjectCreationPage;

	/**
	 * Remember the selection during initialization for populating the default container.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected IStructuredSelection selection;

	/**
	 * Remember the workbench during initialization.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected IWorkbench workbench;

	/**
	 * Caches the names of the types that can be created as the root object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected List<String> initialObjectNames;

	/**
	 * This just records the information. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle(ClassMakerEditorPlugin.INSTANCE.getString("_UI_Wizard_label"));
		setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE
				.getImageDescriptor(ClassMakerEditorPlugin.INSTANCE.getImage("full/wizban/NewClassMaker")));
	}

	/**
	 * Returns the names of the types that can be created as the root object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection<String> getInitialObjectNames() {
		if (initialObjectNames == null) {
			initialObjectNames = new ArrayList<String>();
			for (EClassifier eClassifier : classMakerPackage.getEClassifiers()) {
				if (eClassifier instanceof EClass) {
					EClass eClass = (EClass) eClassifier;
					if (!eClass.isAbstract()) {
						initialObjectNames.add(eClass.getName());
					}
				}
			}
			Collections.sort(initialObjectNames, java.text.Collator.getInstance());
		}
		return initialObjectNames;
	}

	/**
	 * Create a new model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EObject createInitialModel() {
		EClass eClass = (EClass) classMakerPackage.getEClassifier(initialObjectCreationPage.getInitialObjectName());
		EObject rootObject = classMakerFactory.create(eClass);
		return rootObject;
	}

	/**
	 * Do the work after everything is specified.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean performFinish() {
		try {
			// Get the URI of the model file.
			//
			final URI fileURI = getModelURI();
			if (new File(fileURI.toFileString()).exists()) {
				if (!MessageDialog.openQuestion(getShell(),
						ClassMakerEditorPlugin.INSTANCE.getString("_UI_Question_title"), ClassMakerEditorPlugin.INSTANCE
								.getString("_WARN_FileConflict", new String[] { fileURI.toFileString() }))) {
					initialObjectCreationPage.selectFileField();
					return false;
				}
			}

			// Do the work within an operation.
			//
			IRunnableWithProgress operation = new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor progressMonitor) {
					try {
						// Create a resource set
						//
						ResourceSet resourceSet = new ResourceSetImpl();

						// Create a resource for this file.
						//
						Resource resource = resourceSet.createResource(fileURI);

						// Add the initial model object to the contents.
						//
						EObject rootObject = createInitialModel();
						if (rootObject != null) {
							resource.getContents().add(rootObject);
						}

						// Save the contents of the resource to the file system.
						//
						Map<Object, Object> options = new HashMap<Object, Object>();
						options.put(XMLResource.OPTION_ENCODING, initialObjectCreationPage.getEncoding());
						resource.save(options);
					} catch (Exception exception) {
						ClassMakerEditorPlugin.INSTANCE.log(exception);
					} finally {
						progressMonitor.done();
					}
				}
			};

			getContainer().run(false, false, operation);

			return ClassMakerEditorAdvisor.openEditor(workbench, fileURI);
		} catch (Exception exception) {
			ClassMakerEditorPlugin.INSTANCE.log(exception);
			return false;
		}
	}

	/**
	 * This is the page where the type of object to create is selected. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public class ClassMakerModelWizardInitialObjectCreationPage extends WizardPage {
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected Text fileField;

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected Combo initialObjectField;

		/**
		 * @generated <!-- begin-user-doc --> <!-- end-user-doc -->
		 */
		protected List<String> encodings;

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected Combo encodingField;

		/**
		 * Pass in the selection.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public ClassMakerModelWizardInitialObjectCreationPage(String pageId) {
			super(pageId);
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			{
				GridLayout layout = new GridLayout();
				layout.numColumns = 1;
				layout.verticalSpacing = 12;
				composite.setLayout(layout);

				GridData data = new GridData();
				data.verticalAlignment = GridData.FILL;
				data.grabExcessVerticalSpace = true;
				data.horizontalAlignment = GridData.FILL;
				composite.setLayoutData(data);
			}

			Label resourceURILabel = new Label(composite, SWT.LEFT);
			{
				resourceURILabel.setText(ClassMakerEditorPlugin.INSTANCE.getString("_UI_File_label"));

				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				resourceURILabel.setLayoutData(data);
			}

			Composite fileComposite = new Composite(composite, SWT.NONE);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.END;
				fileComposite.setLayoutData(data);

				GridLayout layout = new GridLayout();
				data.horizontalAlignment = GridData.FILL;
				layout.marginHeight = 0;
				layout.marginWidth = 0;
				layout.numColumns = 2;
				fileComposite.setLayout(layout);
			}

			fileField = new Text(fileComposite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				data.horizontalSpan = 1;
				fileField.setLayoutData(data);
			}

			fileField.addModifyListener(validator);

			Label containerLabel = new Label(composite, SWT.LEFT);
			{
				containerLabel.setText(ClassMakerEditorPlugin.INSTANCE.getString("_UI_ModelObject"));

				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				containerLabel.setLayoutData(data);
			}

			initialObjectField = new Combo(composite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				initialObjectField.setLayoutData(data);
			}

			for (String objectName : getInitialObjectNames()) {
				initialObjectField.add(getLabel(objectName));
			}

			if (initialObjectField.getItemCount() == 1) {
				initialObjectField.select(0);
			}
			initialObjectField.addModifyListener(validator);

			Label encodingLabel = new Label(composite, SWT.LEFT);
			{
				encodingLabel.setText(ClassMakerEditorPlugin.INSTANCE.getString("_UI_XMLEncoding"));

				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				encodingLabel.setLayoutData(data);
			}
			encodingField = new Combo(composite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				encodingField.setLayoutData(data);
			}

			for (String encoding : getEncodings()) {
				encodingField.add(encoding);
			}

			encodingField.select(0);
			encodingField.addModifyListener(validator);

			setPageComplete(validatePage());
			setControl(composite);
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected ModifyListener validator = new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		};

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected boolean validatePage() {
			URI fileURI = getFileURI();
			if (fileURI == null || fileURI.isEmpty()) {
				setErrorMessage(null);
				return false;
			}

			String extension = fileURI.fileExtension();
			if (extension == null || !FILE_EXTENSIONS.contains(extension)) {
				String key = FILE_EXTENSIONS.size() > 1 ? "_WARN_FilenameExtensions" : "_WARN_FilenameExtension";
				setErrorMessage(
						ClassMakerEditorPlugin.INSTANCE.getString(key, new Object[] { FORMATTED_FILE_EXTENSIONS }));
				return false;
			}

			setErrorMessage(null);
			return getInitialObjectName() != null && getEncodings().contains(encodingField.getText());
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		@Override
		public void setVisible(boolean visible) {
			super.setVisible(visible);
			if (visible) {
				initialObjectField.clearSelection();
				encodingField.clearSelection();
				fileField.setFocus();
			}
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public String getInitialObjectName() {
			String label = initialObjectField.getText();

			for (String name : getInitialObjectNames()) {
				if (getLabel(name).equals(label)) {
					return name;
				}
			}
			return null;
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public String getEncoding() {
			return encodingField.getText();
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public URI getFileURI() {
			try {
				return URI.createFileURI(fileField.getText());
			} catch (Exception exception) {
				// Ignore
			}
			return null;
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public void selectFileField() {
			initialObjectField.clearSelection();
			encodingField.clearSelection();
			fileField.selectAll();
			fileField.setFocus();
		}

		/**
		 * Returns the label for the specified type name.
		 * <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * @generated
		 */
		protected String getLabel(String typeName) {
			try {
				return ClassMakerEditPlugin.INSTANCE.getString("_UI_" + typeName + "_type");
			} catch (MissingResourceException mre) {
				ClassMakerEditorPlugin.INSTANCE.log(mre);
			}
			return typeName;
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected Collection<String> getEncodings() {
			if (encodings == null) {
				encodings = new ArrayList<String>();
				for (StringTokenizer stringTokenizer = new StringTokenizer(
						ClassMakerEditorPlugin.INSTANCE.getString("_UI_XMLEncodingChoices")); stringTokenizer
								.hasMoreTokens();) {
					encodings.add(stringTokenizer.nextToken());
				}
			}
			return encodings;
		}
	}

	/**
	 * The framework calls this to create the contents of the wizard. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void addPages() {
		initialObjectCreationPage = new ClassMakerModelWizardInitialObjectCreationPage("Whatever2");
		initialObjectCreationPage
				.setTitle(ClassMakerEditorPlugin.INSTANCE.getString("_UI_ClassMakerModelWizard_label"));
		initialObjectCreationPage
				.setDescription(ClassMakerEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
		addPage(initialObjectCreationPage);
	}

	/**
	 * Get the URI from the page.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public URI getModelURI() {
		return initialObjectCreationPage.getFileURI();
	}

}
