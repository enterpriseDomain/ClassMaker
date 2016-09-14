package org.classupplier;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.classupplier.messages"; //$NON-NLS-1$
	public static String JobNameExport;
	public static String BundleNotFound;
	public static String BundleNotInstalled;
	public static String BundleState;
	public static String JobNameCodeGeneration;
	public static String JobNameCodeGenerator;
	public static String BundleDuplicate;
	public static String EPackageClassLoadComplete;
	public static String JobNameGenModelConfiguration;
	public static String JobNameGenModelGeneration;
	public static String JobNameInstaller;
	public static String DefaultModelFolder;
	public static String ModelNotSpecified;
	public static String JobNameModelResource;
	public static String BundleNo;
	public static String ContributionStageNotEnough;
	public static String JobNamePDEExport;
	public static String ProjectNotExist;
	public static String DefaultResourceExt;
	public static String None;
	public static String OK;
	public static String WarningEPackageNoClassifiers;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
