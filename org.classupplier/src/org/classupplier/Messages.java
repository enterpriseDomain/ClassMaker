package org.classupplier;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.classupplier.messages"; //$NON-NLS-1$
	public static String BundleNotFound;
	public static String BundleNotInstalled;
	public static String BundleState;
	public static String DuplicateBundle;
	public static String EPackageClassLoadComplete;
	public static String ModelNotSpecified;
	public static String NoBundle;
	public static String NotEnoughContributionStage;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
