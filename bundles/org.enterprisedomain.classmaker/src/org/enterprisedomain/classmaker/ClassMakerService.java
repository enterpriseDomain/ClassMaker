/**
 * Copyright 2017, 2022 Kyrill Zotkin
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
package org.enterprisedomain.classmaker;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Service</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> ClassMaker API facade service. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.ClassMakerService#getWorkspace
 * <em>Workspace</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getClassMakerService()
 * @model
 * @generated
 */
public interface ClassMakerService extends EObject {

	public static final String INVOCATION_DELEGATE_URI = "org.enterprisedomain.classmaker.reflection.java"; //$NON-NLS-1$

	public abstract class Stages {

		public static final String ID_PREFIX = "org.enterprisedomain.classmaker.customization.stages.";

		private static final String ROLE_ATTR = "role";

		private static final String ID_ATTR = "id";

		private static final String STEP_ATTR = "step";

		private static final String STAGE_ATTR = "stage";

		private static final String RANK_ATTR = "rank";

		private static final String EXCLUSIVE_ATTR = "exclusive";

		private static final String CLASS_ATTR = "class";

		private static HashMap<String, StageQualifier> stages = new HashMap<String, StageQualifier>();

		private static StageQualifier createStageQualifier(Stage phase, String step) {
			StageQualifier stageQualifier = ClassMakerFactory.eINSTANCE.createStageQualifier();
			stageQualifier.setStage(phase);
			stageQualifier.setStep(step);
			return stageQualifier;
		}

		public static void contributeStages() {
			IConfigurationElement[] stageElements = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(ClassMakerPlugin.STAGES_EXT_POINT);
			for (IConfigurationElement e : stageElements) {
				Stage phase = Stage.getByName(e.getAttribute(ROLE_ATTR).toUpperCase());
				String id = e.getAttribute(ID_ATTR);
				stages.put(id, createStageQualifier(phase, e.getAttribute(STEP_ATTR)));
			}
		}

		public static StageQualifier lookup(String id) {
			return stages.get(id);
		}

		public static StageQualifier lookup(Stage stage, String step) {
			for (StageQualifier q : stages.values())
				if (q.getStage().equals(stage) && q.getStep().equals(step))
					return q;
			return null;
		}

		public static Set<String> ids() {
			return stages.keySet();
		}

		public static SortedSet<Customizer> createCustomizers(String id) {
			SortedSet<Customizer> results = new TreeSet<Customizer>(new Customizer.CustomizerComparator());
			IConfigurationElement[] customizerElements = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(ClassMakerPlugin.CUSTOMIZERS_EXT_POINT);
			for (IConfigurationElement ce : customizerElements) {
				if (ce.getAttribute(STAGE_ATTR).equals(id)) {
					try {
						Customizer result = (Customizer) ce.createExecutableExtension(CLASS_ATTR);
						result.setRank(Integer.valueOf(ce.getAttribute(RANK_ATTR)));
						boolean e = true;
						if (ce.getAttribute(EXCLUSIVE_ATTR) != null)
							e = Boolean.valueOf(ce.getAttribute(EXCLUSIVE_ATTR));
						result.setExclusive(e);
						result.setStage(stages.get(ce.getAttribute(STAGE_ATTR)));
						results.add(result);
					} catch (CoreException ex) {
						ClassMakerPlugin.getInstance().getLog().log(ex.getStatus());
					}
				}
			}
			return results;
		}

	}

	public static class NameUtil {

		private static Set<String> javaReservedWords;

		public static String safeName(String name) {
			if (ClassMakerService.NameUtil.isJavaReservedWord(name))
				return name + "_";
			return name;
		}

		public static boolean isJavaReservedWord(String s) {
			return getJavaReservedWords().contains(s);
		}

		public static Set<String> getJavaReservedWords() {
			if (javaReservedWords == null) {
				Set<String> result = new HashSet<String>(100);
				result.add("abstract");
				result.add("assert");
				result.add("boolean");
				result.add("break");
				result.add("byte");
				result.add("case");
				result.add("catch");
				result.add("char");
				result.add("class");
				result.add("const");
				result.add("continue");
				result.add("default");
				result.add("do");
				result.add("double");
				result.add("else");
				result.add("enum");
				result.add("extends");
				result.add("false");
				result.add("final");
				result.add("finally");
				result.add("float");
				result.add("for");
				result.add("goto");
				result.add("if");
				result.add("implements");
				result.add("import");
				result.add("instanceof");
				result.add("int");
				result.add("interface");
				result.add("long");
				result.add("native");
				result.add("new");
				result.add("null");
				result.add("package");
				result.add("private");
				result.add("protected");
				result.add("public");
				result.add("return");
				result.add("short");
				result.add("static");
				result.add("strictfp");
				result.add("super");
				result.add("switch");
				result.add("synchronized");
				result.add("this");
				result.add("throw");
				result.add("throws");
				result.add("transient");
				result.add("true");
				result.add("try");
				result.add("void");
				result.add("volatile");
				result.add("while");
				javaReservedWords = Collections.unmodifiableSet(result);
			}
			return javaReservedWords;
		}

		public static String capName(String name, Locale locale) {
			if (name.length() == 0)
				return name;
			else
				return name.substring(0, 1).toUpperCase(locale) + name.substring(1);
		}

		public static String capName(String name) {
			return capName(name, Locale.getDefault());
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 * @ordered
	 */
	public static final boolean INITIALIZING_EDEFAULT = false;

	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
	 * {@link Workspace <em><b>Workspace</b></em>} containing the {@link Project
	 * <em>Projects</em>}. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Workspace</em>' reference.
	 * @see #setWorkspace(Workspace)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getClassMakerService_Workspace()
	 * @model
	 * @generated
	 */
	Workspace getWorkspace();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.ClassMakerService#getWorkspace
	 * <em>Workspace</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Workspace</em>' containment reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Workspace value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Performs {@link org.eclipse.emf.ecore.EPackage EPackage} instance generation,
	 * build and export, then installs the result into OSGi container, and then
	 * loads a generated {@link org.eclipse.emf.ecore.EPackage EPackage} class. <!--
	 * end-model-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject make(EObject input, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Performs {@link org.eclipse.emf.ecore.EPackage EPackage} instance generation,
	 * build and export, then installs the result into OSGi container, and then
	 * loads a generated {@link org.eclipse.emf.ecore.EPackage EPackage} class. <!--
	 * end-model-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject make(Blueprint input, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject replace(EObject source, EObject target, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject replace(Blueprint source, Blueprint target, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject replace(EObject source, EObject target, boolean changeVersion, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject replace(Blueprint source, Blueprint target, boolean changeVersion, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject replace(EObject source, EObject target, Version version, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject replace(Blueprint source, Blueprint target, Version version, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Performs {@link org.eclipse.emf.ecore.EPackage EPackage} instance generation,
	 * build and export, then installs the result into OSGi container, and then
	 * loads a generated {@link org.eclipse.emf.ecore.EPackage EPackage} class. <!--
	 * end-model-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EObject&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EObject> make(EObject input, Executor executor, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Performs {@link org.eclipse.emf.ecore.EPackage EPackage} instance generation,
	 * build and export, then installs the result into OSGi container, and then
	 * loads a generated {@link org.eclipse.emf.ecore.EPackage EPackage} class. <!--
	 * end-model-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EPackage&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EObject> make(Blueprint input, Executor executor, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EObject&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EObject> replace(EObject source, EObject target, Executor executor, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EObject&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EObject> replace(Blueprint source, Blueprint target, Executor executor, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EObject&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EObject> replace(EObject source, EObject target, boolean changeVersion, Executor executor,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EObject&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EObject> replace(Blueprint source, Blueprint target, boolean changeVersion, Executor executor,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EObject&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EObject> replace(EObject source, EObject target, Version version, Executor executor,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model type="org.enterprisedomain.classmaker.Future&lt;? extends
	 *        org.eclipse.emf.ecore.EObject&gt;"
	 *        exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        versionDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        executorType="org.enterprisedomain.classmaker.Executor"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	Future<? extends EObject> replace(Blueprint source, Blueprint target, Version version, Executor executor,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        transformationURIDataType="org.enterprisedomain.classmaker.URI"
	 * @generated
	 */
	EObject transform(EObject source, URI transformationURI) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        transformationURIDataType="org.enterprisedomain.classmaker.URI"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	EObject transform(EObject source, URI transformationURI, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.InvocationTargetException"
	 *        argumentsMany="true"
	 * @generated
	 */
	Object invoke(EOperation operation, EObject object, EList<Object> arguments) throws InvocationTargetException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model exceptions="org.enterprisedomain.classmaker.CoreException"
	 *        monitorDataType="org.enterprisedomain.classmaker.IProgressMonitor"
	 * @generated
	 */
	void delete(String packageName, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean checkEquals(EObject model1, EObject model2);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	Blueprint createBlueprint();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	String computeProjectName(String packageName);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	<T extends EObject> T copy(T original);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void initialize();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void dispose();

} // ClassMakerService
