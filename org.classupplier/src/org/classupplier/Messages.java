package org.classupplier;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.classupplier.messages"; //$NON-NLS-1$
	public static String AbstractExporter_ExportJobName;
	public static String BundleNotFound;
	public static String BundleNotInstalled;
	public static String BundleState;
	public static String Class;
	public static String CodeGenerationJobName;
	public static String CodeGeneratorJobName;
	public static String Datatype;
	public static String DuplicateBundle;
	public static String EPackageClassLoadComplete;
	public static String GenModelConfigurationJobName;
	public static String GenModelGenerationJobName;
	public static String InstallerJobName;
	public static String ModelFolderDefault;
	public static String ModelNotSpecified;
	public static String ModelResourceJobName;
	public static String NoBundle;
	public static String NotEnoughContributionStage;
	public static String NotValidClassifier;
	public static String NotValidEnumerator;
	public static String PDEExportJobName;
	public static String ProjectNotExist;
	public static String Quote;
	public static String ResourceExtDefault;
	public static String TargetFolderDefault;
	public static String Value;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
