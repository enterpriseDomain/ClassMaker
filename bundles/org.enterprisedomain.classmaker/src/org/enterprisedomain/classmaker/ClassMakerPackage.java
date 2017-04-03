/**
 * Copyright 2012-2016 Kyrill Zotkin
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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.enterprisedomain.classmaker.ClassMakerFactory
 * @model kind="package"
 * @generated
 */
public interface ClassMakerPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "classmaker";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org/enterprisedomain/ClassMaker/0.7.8";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ClassMaker";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClassMakerPackage eINSTANCE = org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getISchedulingRule()
	 * @generated
	 */
	int ISCHEDULING_RULE = 8;

	/**
	 * The number of structural features of the '<em>IScheduling Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISCHEDULING_RULE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.ProjectImpl <em>Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.ProjectImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getProject()
	 * @generated
	 */
	int PROJECT = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__NAME = ISCHEDULING_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__PROJECT_NAME = ISCHEDULING_RULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CHILDREN = ISCHEDULING_RULE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DIRTY = ISCHEDULING_RULE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__WORKSPACE = ISCHEDULING_RULE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_FEATURE_COUNT = ISCHEDULING_RULE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.ContributionImpl <em>Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.ContributionImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getContribution()
	 * @generated
	 */
	int CONTRIBUTION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__NAME = PROJECT__NAME;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__PROJECT_NAME = PROJECT__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__CHILDREN = PROJECT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__DIRTY = PROJECT__DIRTY;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__WORKSPACE = PROJECT__WORKSPACE;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__MODEL_NAME = PROJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__PHASE = PROJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__VERSION = PROJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__LANGUAGE = PROJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__DOMAIN_MODEL = PROJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__CUSTOMIZERS = PROJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__PARENT = PROJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__LOCALE = PROJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Contribution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__CONTRIBUTION = PROJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Revision</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__REVISION = PROJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Revisions</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__REVISIONS = PROJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__STATE = PROJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Latest Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__LATEST_VERSION = PROJECT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__MODEL = PROJECT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Completion Notified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__COMPLETION_NOTIFIED = PROJECT_FEATURE_COUNT + 14;

	/**
	 * The number of structural features of the '<em>Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_FEATURE_COUNT = PROJECT_FEATURE_COUNT + 15;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.ItemImpl <em>Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.ItemImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getItem()
	 * @generated
	 */
	int ITEM = 3;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM__MODEL_NAME = 0;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM__PHASE = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM__LANGUAGE = 3;

	/**
	 * The feature id for the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM__DOMAIN_MODEL = 4;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM__CUSTOMIZERS = 5;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM__PARENT = 6;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM__LOCALE = 7;

	/**
	 * The feature id for the '<em><b>Contribution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM__CONTRIBUTION = 8;

	/**
	 * The number of structural features of the '<em>Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.RevisionImpl <em>Revision</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.RevisionImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getRevision()
	 * @generated
	 */
	int REVISION = 1;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__MODEL_NAME = ITEM__MODEL_NAME;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__PHASE = ITEM__PHASE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__VERSION = ITEM__VERSION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__LANGUAGE = ITEM__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__DOMAIN_MODEL = ITEM__DOMAIN_MODEL;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__CUSTOMIZERS = ITEM__CUSTOMIZERS;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__PARENT = ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__LOCALE = ITEM__LOCALE;

	/**
	 * The feature id for the '<em><b>Contribution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__CONTRIBUTION = ITEM__CONTRIBUTION;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__STATE = ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__TIMESTAMP = ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>State History</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__STATE_HISTORY = ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Latest Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION__LATEST_TIMESTAMP = ITEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Revision</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVISION_FEATURE_COUNT = ITEM_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.StateImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getState()
	 * @generated
	 */
	int STATE = 2;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__MODEL_NAME = ITEM__MODEL_NAME;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__PHASE = ITEM__PHASE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__VERSION = ITEM__VERSION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__LANGUAGE = ITEM__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DOMAIN_MODEL = ITEM__DOMAIN_MODEL;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__CUSTOMIZERS = ITEM__CUSTOMIZERS;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__PARENT = ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__LOCALE = ITEM__LOCALE;

	/**
	 * The feature id for the '<em><b>Contribution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__CONTRIBUTION = ITEM__CONTRIBUTION;

	/**
	 * The feature id for the '<em><b>Revision</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__REVISION = ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__TIMESTAMP = ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Deployable Unit Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DEPLOYABLE_UNIT_NAME = ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Job Family</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__JOB_FAMILY = ITEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__RESOURCE = ITEM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Commit Ids</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__COMMIT_IDS = ITEM_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>State Customizers</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__STATE_CUSTOMIZERS = ITEM_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__PROJECT_NAME = ITEM_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Saving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__SAVING = ITEM_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = ITEM_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.common.notify.Adapter <em>Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getAdapter()
	 * @generated
	 */
	int ADAPTER = 4;

	/**
	 * The number of structural features of the '<em>Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.WorkspaceImpl <em>Workspace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.WorkspaceImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getWorkspace()
	 * @generated
	 */
	int WORKSPACE = 5;

	/**
	 * The feature id for the '<em><b>Projects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__PROJECTS = ISCHEDULING_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resource Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__RESOURCE_SET = ISCHEDULING_RULE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Workspace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_FEATURE_COUNT = ISCHEDULING_RULE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.IntegerToStateMapEntryImpl <em>Integer To State Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.IntegerToStateMapEntryImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIntegerToStateMapEntry()
	 * @generated
	 */
	int INTEGER_TO_STATE_MAP_ENTRY = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TO_STATE_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TO_STATE_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Integer To State Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_TO_STATE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.VersionToRevisionMapEntryImpl <em>Version To Revision Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.VersionToRevisionMapEntryImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getVersionToRevisionMapEntry()
	 * @generated
	 */
	int VERSION_TO_REVISION_MAP_ENTRY = 7;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_TO_REVISION_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_TO_REVISION_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Version To Revision Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_TO_REVISION_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link java.util.concurrent.Future <em>Future</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.concurrent.Future
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getFuture()
	 * @generated
	 */
	int FUTURE = 9;

	/**
	 * The number of structural features of the '<em>Future</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUTURE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.common.util.EMap <em>EMap</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.EMap
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getEMap()
	 * @generated
	 */
	int EMAP = 10;

	/**
	 * The number of structural features of the '<em>EMap</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAP_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.CustomizerImpl <em>Customizer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.CustomizerImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCustomizer()
	 * @generated
	 */
	int CUSTOMIZER = 11;

	/**
	 * The number of structural features of the '<em>Customizer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.StageQualifierImpl <em>Stage Qualifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.StageQualifierImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStageQualifier()
	 * @generated
	 */
	int STAGE_QUALIFIER = 12;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER__STAGE = 0;

	/**
	 * The feature id for the '<em><b>Step</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER__STEP = 1;

	/**
	 * The number of structural features of the '<em>Stage Qualifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.StageQualifierToCustomizerMapEntryImpl <em>Stage Qualifier To Customizer Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.StageQualifierToCustomizerMapEntryImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStageQualifierToCustomizerMapEntry()
	 * @generated
	 */
	int STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY = 13;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Stage Qualifier To Customizer Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.ecore.resource.ResourceSet <em>Resource Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.resource.ResourceSet
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResourceSet()
	 * @generated
	 */
	int RESOURCE_SET = 15;

	/**
	 * The number of structural features of the '<em>Resource Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SET_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.ModelPairImpl <em>Model Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.ModelPairImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getModelPair()
	 * @generated
	 */
	int MODEL_PAIR = 16;

	/**
	 * The feature id for the '<em><b>Dynamic</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_PAIR__DYNAMIC = 0;

	/**
	 * The feature id for the '<em><b>Generated</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_PAIR__GENERATED = 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_PAIR__PARENT = 2;

	/**
	 * The number of structural features of the '<em>Model Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_PAIR_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.ecore.resource.Resource <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.resource.Resource
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 17;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.CompletionListenerImpl <em>Completion Listener</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.CompletionListenerImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCompletionListener()
	 * @generated
	 */
	int COMPLETION_LISTENER = 18;

	/**
	 * The number of structural features of the '<em>Completion Listener</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLETION_LISTENER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.ResourceAdapterImpl <em>Resource Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.ResourceAdapterImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResourceAdapter()
	 * @generated
	 */
	int RESOURCE_ADAPTER = 19;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__RESOURCE = ADAPTER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__FILENAME = ADAPTER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER_FEATURE_COUNT = ADAPTER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.impl.ClassPlantImpl <em>Class Plant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.impl.ClassPlantImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getClassPlant()
	 * @generated
	 */
	int CLASS_PLANT = 20;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PLANT__WORKSPACE = 0;

	/**
	 * The number of structural features of the '<em>Class Plant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PLANT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '<em>Locale</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Locale
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getLocale()
	 * @generated
	 */
	int LOCALE = 28;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.Stage <em>Stage</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.enterprisedomain.classmaker.Stage
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStage()
	 * @generated
	 */
	int STAGE = 21;

	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 22;

	/**
	 * The meta object id for the '<em>OS Gi Version</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.framework.Version
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getOSGiVersion()
	 * @generated
	 */
	int OS_GI_VERSION = 23;

	/**
	 * The meta object id for the '<em>Semaphore</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.concurrent.Semaphore
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSemaphore()
	 * @generated
	 */
	int SEMAPHORE = 24;

	/**
	 * The meta object id for the '<em>Core Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.CoreException
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCoreException()
	 * @generated
	 */
	int CORE_EXCEPTION = 25;

	/**
	 * The meta object id for the '<em>URI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.URI
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getURI()
	 * @generated
	 */
	int URI = 26;

	/**
	 * The meta object id for the '<em>Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Exception
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getException()
	 * @generated
	 */
	int EXCEPTION = 27;

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.Contribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contribution</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution
	 * @generated
	 */
	EClass getContribution();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.Contribution#getRevision <em>Revision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Revision</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#getRevision()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Revision();

	/**
	 * Returns the meta object for the map '{@link org.enterprisedomain.classmaker.Contribution#getRevisions <em>Revisions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Revisions</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#getRevisions()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Revisions();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.Contribution#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#getState()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_State();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Contribution#getLatestVersion <em>Latest Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latest Version</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#getLatestVersion()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_LatestVersion();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.Contribution#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#getModel()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Model();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Contribution#isCompletionNotified <em>Completion Notified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Completion Notified</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#isCompletionNotified()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_CompletionNotified();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.Revision <em>Revision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Revision</em>'.
	 * @see org.enterprisedomain.classmaker.Revision
	 * @generated
	 */
	EClass getRevision();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.Revision#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see org.enterprisedomain.classmaker.Revision#getState()
	 * @see #getRevision()
	 * @generated
	 */
	EReference getRevision_State();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Revision#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.enterprisedomain.classmaker.Revision#getTimestamp()
	 * @see #getRevision()
	 * @generated
	 */
	EAttribute getRevision_Timestamp();

	/**
	 * Returns the meta object for the map '{@link org.enterprisedomain.classmaker.Revision#getStateHistory <em>State History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>State History</em>'.
	 * @see org.enterprisedomain.classmaker.Revision#getStateHistory()
	 * @see #getRevision()
	 * @generated
	 */
	EReference getRevision_StateHistory();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Revision#getLatestTimestamp <em>Latest Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Latest Timestamp</em>'.
	 * @see org.enterprisedomain.classmaker.Revision#getLatestTimestamp()
	 * @see #getRevision()
	 * @generated
	 */
	EAttribute getRevision_LatestTimestamp();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see org.enterprisedomain.classmaker.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.State#getRevision <em>Revision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Revision</em>'.
	 * @see org.enterprisedomain.classmaker.State#getRevision()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Revision();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.State#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.enterprisedomain.classmaker.State#getTimestamp()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.State#getDeployableUnitName <em>Deployable Unit Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deployable Unit Name</em>'.
	 * @see org.enterprisedomain.classmaker.State#getDeployableUnitName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_DeployableUnitName();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.State#getJobFamily <em>Job Family</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Job Family</em>'.
	 * @see org.enterprisedomain.classmaker.State#getJobFamily()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_JobFamily();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.State#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resource</em>'.
	 * @see org.enterprisedomain.classmaker.State#getResource()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Resource();

	/**
	 * Returns the meta object for the attribute list '{@link org.enterprisedomain.classmaker.State#getCommitIds <em>Commit Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Commit Ids</em>'.
	 * @see org.enterprisedomain.classmaker.State#getCommitIds()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_CommitIds();

	/**
	 * Returns the meta object for the map '{@link org.enterprisedomain.classmaker.State#getStateCustomizers <em>State Customizers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>State Customizers</em>'.
	 * @see org.enterprisedomain.classmaker.State#getStateCustomizers()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_StateCustomizers();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.State#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.enterprisedomain.classmaker.State#getProjectName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_ProjectName();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.State#isSaving <em>Saving</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Saving</em>'.
	 * @see org.enterprisedomain.classmaker.State#isSaving()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Saving();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.Item <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Item</em>'.
	 * @see org.enterprisedomain.classmaker.Item
	 * @generated
	 */
	EClass getItem();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Item#getModelName <em>Model Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Name</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getModelName()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_ModelName();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Item#getPhase <em>Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getPhase()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Phase();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Item#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getVersion()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Item#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getLanguage()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Language();

	/**
	 * Returns the meta object for the containment reference '{@link org.enterprisedomain.classmaker.Item#getDomainModel <em>Domain Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Domain Model</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getDomainModel()
	 * @see #getItem()
	 * @generated
	 */
	EReference getItem_DomainModel();

	/**
	 * Returns the meta object for the map '{@link org.enterprisedomain.classmaker.Item#getCustomizers <em>Customizers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Customizers</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getCustomizers()
	 * @see #getItem()
	 * @generated
	 */
	EReference getItem_Customizers();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.Item#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getParent()
	 * @see #getItem()
	 * @generated
	 */
	EReference getItem_Parent();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Item#getLocale <em>Locale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Locale</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getLocale()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Locale();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.Item#getContribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contribution</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getContribution()
	 * @see #getItem()
	 * @generated
	 */
	EReference getItem_Contribution();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.common.notify.Adapter <em>Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Adapter</em>'.
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @model instanceClass="org.eclipse.emf.common.notify.Adapter"
	 * @generated
	 */
	EClass getAdapter();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.Workspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workspace</em>'.
	 * @see org.enterprisedomain.classmaker.Workspace
	 * @generated
	 */
	EClass getWorkspace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.enterprisedomain.classmaker.Workspace#getProjects <em>Projects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Projects</em>'.
	 * @see org.enterprisedomain.classmaker.Workspace#getProjects()
	 * @see #getWorkspace()
	 * @generated
	 */
	EReference getWorkspace_Projects();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Workspace#getResourceSet <em>Resource Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Set</em>'.
	 * @see org.enterprisedomain.classmaker.Workspace#getResourceSet()
	 * @see #getWorkspace()
	 * @generated
	 */
	EAttribute getWorkspace_ResourceSet();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Integer To State Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer To State Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EIntegerObject"
	 *        valueType="org.enterprisedomain.classmaker.State" valueContainment="true"
	 * @generated
	 */
	EClass getIntegerToStateMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getIntegerToStateMapEntry()
	 * @generated
	 */
	EAttribute getIntegerToStateMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getIntegerToStateMapEntry()
	 * @generated
	 */
	EReference getIntegerToStateMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Version To Revision Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Version To Revision Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        valueType="org.enterprisedomain.classmaker.Revision" valueContainment="true"
	 * @generated
	 */
	EClass getVersionToRevisionMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVersionToRevisionMapEntry()
	 * @generated
	 */
	EAttribute getVersionToRevisionMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVersionToRevisionMapEntry()
	 * @generated
	 */
	EReference getVersionToRevisionMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IScheduling Rule</em>'.
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
	 * @model instanceClass="org.eclipse.core.runtime.jobs.ISchedulingRule"
	 * @generated
	 */
	EClass getISchedulingRule();

	/**
	 * Returns the meta object for class '{@link java.util.concurrent.Future <em>Future</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Future</em>'.
	 * @see java.util.concurrent.Future
	 * @model instanceClass="java.util.concurrent.Future" typeParameters="ResultType"
	 * @generated
	 */
	EClass getFuture();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.common.util.EMap <em>EMap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EMap</em>'.
	 * @see org.eclipse.emf.common.util.EMap
	 * @model instanceClass="org.eclipse.emf.common.util.EMap" typeParameters="K V"
	 * @generated
	 */
	EClass getEMap();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.Customizer <em>Customizer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customizer</em>'.
	 * @see org.enterprisedomain.classmaker.Customizer
	 * @generated
	 */
	EClass getCustomizer();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.StageQualifier <em>Stage Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stage Qualifier</em>'.
	 * @see org.enterprisedomain.classmaker.StageQualifier
	 * @generated
	 */
	EClass getStageQualifier();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.StageQualifier#getStage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stage</em>'.
	 * @see org.enterprisedomain.classmaker.StageQualifier#getStage()
	 * @see #getStageQualifier()
	 * @generated
	 */
	EAttribute getStageQualifier_Stage();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.StageQualifier#getStep <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Step</em>'.
	 * @see org.enterprisedomain.classmaker.StageQualifier#getStep()
	 * @see #getStageQualifier()
	 * @generated
	 */
	EAttribute getStageQualifier_Step();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Stage Qualifier To Customizer Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stage Qualifier To Customizer Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="org.enterprisedomain.classmaker.StageQualifier"
	 *        valueType="org.enterprisedomain.classmaker.Customizer" valueContainment="true"
	 * @generated
	 */
	EClass getStageQualifierToCustomizerMapEntry();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStageQualifierToCustomizerMapEntry()
	 * @generated
	 */
	EReference getStageQualifierToCustomizerMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStageQualifierToCustomizerMapEntry()
	 * @generated
	 */
	EReference getStageQualifierToCustomizerMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.Project <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project</em>'.
	 * @see org.enterprisedomain.classmaker.Project
	 * @generated
	 */
	EClass getProject();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Project#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getName()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Project#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getProjectName()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_ProjectName();

	/**
	 * Returns the meta object for the reference list '{@link org.enterprisedomain.classmaker.Project#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getChildren()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Children();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.Project#isDirty <em>Dirty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dirty</em>'.
	 * @see org.enterprisedomain.classmaker.Project#isDirty()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Dirty();

	/**
	 * Returns the meta object for the container reference '{@link org.enterprisedomain.classmaker.Project#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Workspace</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getWorkspace()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Workspace();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.ecore.resource.ResourceSet <em>Resource Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Set</em>'.
	 * @see org.eclipse.emf.ecore.resource.ResourceSet
	 * @model instanceClass="org.eclipse.emf.ecore.resource.ResourceSet"
	 * @generated
	 */
	EClass getResourceSet();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.ModelPair <em>Model Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Pair</em>'.
	 * @see org.enterprisedomain.classmaker.ModelPair
	 * @generated
	 */
	EClass getModelPair();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.ModelPair#getDynamic <em>Dynamic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dynamic</em>'.
	 * @see org.enterprisedomain.classmaker.ModelPair#getDynamic()
	 * @see #getModelPair()
	 * @generated
	 */
	EReference getModelPair_Dynamic();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.ModelPair#getGenerated <em>Generated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Generated</em>'.
	 * @see org.enterprisedomain.classmaker.ModelPair#getGenerated()
	 * @see #getModelPair()
	 * @generated
	 */
	EReference getModelPair_Generated();

	/**
	 * Returns the meta object for the container reference '{@link org.enterprisedomain.classmaker.ModelPair#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.enterprisedomain.classmaker.ModelPair#getParent()
	 * @see #getModelPair()
	 * @generated
	 */
	EReference getModelPair_Parent();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.ecore.resource.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see org.eclipse.emf.ecore.resource.Resource
	 * @model instanceClass="org.eclipse.emf.ecore.resource.Resource"
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.CompletionListener <em>Completion Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Completion Listener</em>'.
	 * @see org.enterprisedomain.classmaker.CompletionListener
	 * @generated
	 */
	EClass getCompletionListener();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.ResourceAdapter <em>Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Adapter</em>'.
	 * @see org.enterprisedomain.classmaker.ResourceAdapter
	 * @generated
	 */
	EClass getResourceAdapter();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.ResourceAdapter#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resource</em>'.
	 * @see org.enterprisedomain.classmaker.ResourceAdapter#getResource()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_Resource();

	/**
	 * Returns the meta object for the attribute '{@link org.enterprisedomain.classmaker.ResourceAdapter#getFilename <em>Filename</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filename</em>'.
	 * @see org.enterprisedomain.classmaker.ResourceAdapter#getFilename()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EAttribute getResourceAdapter_Filename();

	/**
	 * Returns the meta object for class '{@link org.enterprisedomain.classmaker.ClassPlant <em>Class Plant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Plant</em>'.
	 * @see org.enterprisedomain.classmaker.ClassPlant
	 * @generated
	 */
	EClass getClassPlant();

	/**
	 * Returns the meta object for the reference '{@link org.enterprisedomain.classmaker.ClassPlant#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Workspace</em>'.
	 * @see org.enterprisedomain.classmaker.ClassPlant#getWorkspace()
	 * @see #getClassPlant()
	 * @generated
	 */
	EReference getClassPlant_Workspace();

	/**
	 * Returns the meta object for data type '{@link java.util.Locale <em>Locale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Locale</em>'.
	 * @see java.util.Locale
	 * @model instanceClass="java.util.Locale"
	 * @generated
	 */
	EDataType getLocale();

	/**
	 * Returns the meta object for enum '{@link org.enterprisedomain.classmaker.Stage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Stage</em>'.
	 * @see org.enterprisedomain.classmaker.Stage
	 * @generated
	 */
	EEnum getStage();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IProgressMonitor <em>IProgress Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IProgress Monitor</em>'.
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @model instanceClass="org.eclipse.core.runtime.IProgressMonitor"
	 * @generated
	 */
	EDataType getIProgressMonitor();

	/**
	 * Returns the meta object for data type '{@link org.osgi.framework.Version <em>OS Gi Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>OS Gi Version</em>'.
	 * @see org.osgi.framework.Version
	 * @model instanceClass="org.osgi.framework.Version"
	 * @generated
	 */
	EDataType getOSGiVersion();

	/**
	 * Returns the meta object for data type '{@link java.util.concurrent.Semaphore <em>Semaphore</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Semaphore</em>'.
	 * @see java.util.concurrent.Semaphore
	 * @model instanceClass="java.util.concurrent.Semaphore"
	 * @generated
	 */
	EDataType getSemaphore();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.CoreException <em>Core Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Core Exception</em>'.
	 * @see org.eclipse.core.runtime.CoreException
	 * @model instanceClass="org.eclipse.core.runtime.CoreException"
	 * @generated
	 */
	EDataType getCoreException();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.URI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see org.eclipse.emf.common.util.URI
	 * @model instanceClass="org.eclipse.emf.common.util.URI"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the meta object for data type '{@link java.lang.Exception <em>Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Exception</em>'.
	 * @see java.lang.Exception
	 * @model instanceClass="java.lang.Exception"
	 * @generated
	 */
	EDataType getException();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ClassMakerFactory getClassMakerFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.ContributionImpl <em>Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.ContributionImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getContribution()
		 * @generated
		 */
		EClass CONTRIBUTION = eINSTANCE.getContribution();

		/**
		 * The meta object literal for the '<em><b>Revision</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__REVISION = eINSTANCE.getContribution_Revision();

		/**
		 * The meta object literal for the '<em><b>Revisions</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__REVISIONS = eINSTANCE.getContribution_Revisions();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__STATE = eINSTANCE.getContribution_State();

		/**
		 * The meta object literal for the '<em><b>Latest Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__LATEST_VERSION = eINSTANCE.getContribution_LatestVersion();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__MODEL = eINSTANCE.getContribution_Model();

		/**
		 * The meta object literal for the '<em><b>Completion Notified</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__COMPLETION_NOTIFIED = eINSTANCE.getContribution_CompletionNotified();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.RevisionImpl <em>Revision</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.RevisionImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getRevision()
		 * @generated
		 */
		EClass REVISION = eINSTANCE.getRevision();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REVISION__STATE = eINSTANCE.getRevision_State();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION__TIMESTAMP = eINSTANCE.getRevision_Timestamp();

		/**
		 * The meta object literal for the '<em><b>State History</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REVISION__STATE_HISTORY = eINSTANCE.getRevision_StateHistory();

		/**
		 * The meta object literal for the '<em><b>Latest Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVISION__LATEST_TIMESTAMP = eINSTANCE.getRevision_LatestTimestamp();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.StateImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Revision</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__REVISION = eINSTANCE.getState_Revision();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__TIMESTAMP = eINSTANCE.getState_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Deployable Unit Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__DEPLOYABLE_UNIT_NAME = eINSTANCE.getState_DeployableUnitName();

		/**
		 * The meta object literal for the '<em><b>Job Family</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__JOB_FAMILY = eINSTANCE.getState_JobFamily();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__RESOURCE = eINSTANCE.getState_Resource();

		/**
		 * The meta object literal for the '<em><b>Commit Ids</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__COMMIT_IDS = eINSTANCE.getState_CommitIds();

		/**
		 * The meta object literal for the '<em><b>State Customizers</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__STATE_CUSTOMIZERS = eINSTANCE.getState_StateCustomizers();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__PROJECT_NAME = eINSTANCE.getState_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Saving</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__SAVING = eINSTANCE.getState_Saving();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.ItemImpl <em>Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.ItemImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getItem()
		 * @generated
		 */
		EClass ITEM = eINSTANCE.getItem();

		/**
		 * The meta object literal for the '<em><b>Model Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITEM__MODEL_NAME = eINSTANCE.getItem_ModelName();

		/**
		 * The meta object literal for the '<em><b>Phase</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITEM__PHASE = eINSTANCE.getItem_Phase();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITEM__VERSION = eINSTANCE.getItem_Version();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITEM__LANGUAGE = eINSTANCE.getItem_Language();

		/**
		 * The meta object literal for the '<em><b>Domain Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITEM__DOMAIN_MODEL = eINSTANCE.getItem_DomainModel();

		/**
		 * The meta object literal for the '<em><b>Customizers</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITEM__CUSTOMIZERS = eINSTANCE.getItem_Customizers();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITEM__PARENT = eINSTANCE.getItem_Parent();

		/**
		 * The meta object literal for the '<em><b>Locale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ITEM__LOCALE = eINSTANCE.getItem_Locale();

		/**
		 * The meta object literal for the '<em><b>Contribution</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITEM__CONTRIBUTION = eINSTANCE.getItem_Contribution();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.common.notify.Adapter <em>Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.notify.Adapter
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getAdapter()
		 * @generated
		 */
		EClass ADAPTER = eINSTANCE.getAdapter();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.WorkspaceImpl <em>Workspace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.WorkspaceImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getWorkspace()
		 * @generated
		 */
		EClass WORKSPACE = eINSTANCE.getWorkspace();

		/**
		 * The meta object literal for the '<em><b>Projects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE__PROJECTS = eINSTANCE.getWorkspace_Projects();

		/**
		 * The meta object literal for the '<em><b>Resource Set</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE__RESOURCE_SET = eINSTANCE.getWorkspace_ResourceSet();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.IntegerToStateMapEntryImpl <em>Integer To State Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.IntegerToStateMapEntryImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIntegerToStateMapEntry()
		 * @generated
		 */
		EClass INTEGER_TO_STATE_MAP_ENTRY = eINSTANCE.getIntegerToStateMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_TO_STATE_MAP_ENTRY__KEY = eINSTANCE.getIntegerToStateMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTEGER_TO_STATE_MAP_ENTRY__VALUE = eINSTANCE.getIntegerToStateMapEntry_Value();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.VersionToRevisionMapEntryImpl <em>Version To Revision Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.VersionToRevisionMapEntryImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getVersionToRevisionMapEntry()
		 * @generated
		 */
		EClass VERSION_TO_REVISION_MAP_ENTRY = eINSTANCE.getVersionToRevisionMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERSION_TO_REVISION_MAP_ENTRY__KEY = eINSTANCE.getVersionToRevisionMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERSION_TO_REVISION_MAP_ENTRY__VALUE = eINSTANCE.getVersionToRevisionMapEntry_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getISchedulingRule()
		 * @generated
		 */
		EClass ISCHEDULING_RULE = eINSTANCE.getISchedulingRule();

		/**
		 * The meta object literal for the '{@link java.util.concurrent.Future <em>Future</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.concurrent.Future
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getFuture()
		 * @generated
		 */
		EClass FUTURE = eINSTANCE.getFuture();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.common.util.EMap <em>EMap</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.util.EMap
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getEMap()
		 * @generated
		 */
		EClass EMAP = eINSTANCE.getEMap();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.CustomizerImpl <em>Customizer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.CustomizerImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCustomizer()
		 * @generated
		 */
		EClass CUSTOMIZER = eINSTANCE.getCustomizer();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.StageQualifierImpl <em>Stage Qualifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.StageQualifierImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStageQualifier()
		 * @generated
		 */
		EClass STAGE_QUALIFIER = eINSTANCE.getStageQualifier();

		/**
		 * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE_QUALIFIER__STAGE = eINSTANCE.getStageQualifier_Stage();

		/**
		 * The meta object literal for the '<em><b>Step</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE_QUALIFIER__STEP = eINSTANCE.getStageQualifier_Step();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.StageQualifierToCustomizerMapEntryImpl <em>Stage Qualifier To Customizer Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.StageQualifierToCustomizerMapEntryImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStageQualifierToCustomizerMapEntry()
		 * @generated
		 */
		EClass STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY = eINSTANCE.getStageQualifierToCustomizerMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__KEY = eINSTANCE.getStageQualifierToCustomizerMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__VALUE = eINSTANCE
				.getStageQualifierToCustomizerMapEntry_Value();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.ProjectImpl <em>Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.ProjectImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getProject()
		 * @generated
		 */
		EClass PROJECT = eINSTANCE.getProject();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__NAME = eINSTANCE.getProject_Name();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__PROJECT_NAME = eINSTANCE.getProject_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT__CHILDREN = eINSTANCE.getProject_Children();

		/**
		 * The meta object literal for the '<em><b>Dirty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__DIRTY = eINSTANCE.getProject_Dirty();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT__WORKSPACE = eINSTANCE.getProject_Workspace();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.ecore.resource.ResourceSet <em>Resource Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.resource.ResourceSet
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResourceSet()
		 * @generated
		 */
		EClass RESOURCE_SET = eINSTANCE.getResourceSet();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.ModelPairImpl <em>Model Pair</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.ModelPairImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getModelPair()
		 * @generated
		 */
		EClass MODEL_PAIR = eINSTANCE.getModelPair();

		/**
		 * The meta object literal for the '<em><b>Dynamic</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_PAIR__DYNAMIC = eINSTANCE.getModelPair_Dynamic();

		/**
		 * The meta object literal for the '<em><b>Generated</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_PAIR__GENERATED = eINSTANCE.getModelPair_Generated();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_PAIR__PARENT = eINSTANCE.getModelPair_Parent();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.ecore.resource.Resource <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.resource.Resource
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.CompletionListenerImpl <em>Completion Listener</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.CompletionListenerImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCompletionListener()
		 * @generated
		 */
		EClass COMPLETION_LISTENER = eINSTANCE.getCompletionListener();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.ResourceAdapterImpl <em>Resource Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.ResourceAdapterImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResourceAdapter()
		 * @generated
		 */
		EClass RESOURCE_ADAPTER = eINSTANCE.getResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__RESOURCE = eINSTANCE.getResourceAdapter_Resource();

		/**
		 * The meta object literal for the '<em><b>Filename</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__FILENAME = eINSTANCE.getResourceAdapter_Filename();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.impl.ClassPlantImpl <em>Class Plant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.impl.ClassPlantImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getClassPlant()
		 * @generated
		 */
		EClass CLASS_PLANT = eINSTANCE.getClassPlant();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_PLANT__WORKSPACE = eINSTANCE.getClassPlant_Workspace();

		/**
		 * The meta object literal for the '<em>Locale</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Locale
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getLocale()
		 * @generated
		 */
		EDataType LOCALE = eINSTANCE.getLocale();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.Stage <em>Stage</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.enterprisedomain.classmaker.Stage
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStage()
		 * @generated
		 */
		EEnum STAGE = eINSTANCE.getStage();

		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();

		/**
		 * The meta object literal for the '<em>OS Gi Version</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.framework.Version
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getOSGiVersion()
		 * @generated
		 */
		EDataType OS_GI_VERSION = eINSTANCE.getOSGiVersion();

		/**
		 * The meta object literal for the '<em>Semaphore</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.concurrent.Semaphore
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSemaphore()
		 * @generated
		 */
		EDataType SEMAPHORE = eINSTANCE.getSemaphore();

		/**
		 * The meta object literal for the '<em>Core Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.CoreException
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCoreException()
		 * @generated
		 */
		EDataType CORE_EXCEPTION = eINSTANCE.getCoreException();

		/**
		 * The meta object literal for the '<em>URI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.util.URI
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();

		/**
		 * The meta object literal for the '<em>Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Exception
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getException()
		 * @generated
		 */
		EDataType EXCEPTION = eINSTANCE.getException();

	}

} //ClassMakerPackage
