/**
 * Copyright 2012-2018 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.enterprisedomain.classmaker.messages"; //$NON-NLS-1$
	public static String JobNameExport;
	public static String BundleNotFound;
	public static String BundleNotInstalled;
	public static String BundleState;
	public static String JobNameCodeGeneration;
	public static String JobNameCodeGenerator;
	public static String BundleDuplicate;
	public static String ObjectClassLoadComplete;
	public static String ObjectNo;
	public static String JobNameGenModelConfiguration;
	public static String JobNameGenModelGeneration;
	public static String JobNameInstaller;
	public static String JobNameLoader;
	public static String DefaultModelFolder;
	public static String ModelNotSpecified;
	public static String JobNameModelResource;
	public static String BundleNo;
	public static String JobNamePDEExport;
	public static String ProjectNotExist;
	public static String DefaultResourceExt;
	public static String None;
	public static String OK;
	public static String ResourceImported;
	public static String Save;
	public static String SubTaskNamePluginExport;
	public static String SubTaskNameSetProjectVersion;
	public static String TaskNamePluginExport;
	public static String TaskNameSetProjectVersion;
	public static String VersionDoesNotExists;
	public static String WarningEPackageNoClassifiers;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
