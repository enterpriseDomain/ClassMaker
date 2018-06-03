/**
 * Copyright 2012-2018 Kyrill Zotkin
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
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.enterprisedomain.classmaker.ClassMakerFactory
 * @model kind="package"
 * @generated
 */
public interface ClassMakerPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "classmaker";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://org/enterprisedomain/ClassMaker/0.7.929";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "ClassMaker";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	ClassMakerPackage eINSTANCE = org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl.init();

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling
	 * Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getISchedulingRule()
	 * @generated
	 */
	int ISCHEDULING_RULE = 8;

	/**
	 * The number of structural features of the '<em>IScheduling Rule</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ISCHEDULING_RULE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.ProjectImpl <em>Project</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.ProjectImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getProject()
	 * @generated
	 */
	int PROJECT = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__NAME = ISCHEDULING_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__PROJECT_NAME = ISCHEDULING_RULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__CHILDREN = ISCHEDULING_RULE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__DIRTY = ISCHEDULING_RULE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__WORKSPACE = ISCHEDULING_RULE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Resource Path</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__RESOURCE_PATH = ISCHEDULING_RULE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Need Completion Notification</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__NEED_COMPLETION_NOTIFICATION = ISCHEDULING_RULE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Completion Notification Adapter</b></em>'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__COMPLETION_NOTIFICATION_ADAPTER = ISCHEDULING_RULE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Resource Reload Listener</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__RESOURCE_RELOAD_LISTENER = ISCHEDULING_RULE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Saving Resource</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__SAVING_RESOURCE = ISCHEDULING_RULE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Revision</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__REVISION = ISCHEDULING_RULE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Revisions</b></em>' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__REVISIONS = ISCHEDULING_RULE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Project Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__PROJECT_VERSION = ISCHEDULING_RULE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Select Reveal Handler</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__SELECT_REVEAL_HANDLER = ISCHEDULING_RULE_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Project</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT_FEATURE_COUNT = ISCHEDULING_RULE_FEATURE_COUNT + 14;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.ContributionImpl
	 * <em>Contribution</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.ContributionImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getContribution()
	 * @generated
	 */
	int CONTRIBUTION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__NAME = PROJECT__NAME;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__PROJECT_NAME = PROJECT__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__CHILDREN = PROJECT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__DIRTY = PROJECT__DIRTY;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__WORKSPACE = PROJECT__WORKSPACE;

	/**
	 * The feature id for the '<em><b>Resource Path</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__RESOURCE_PATH = PROJECT__RESOURCE_PATH;

	/**
	 * The feature id for the '<em><b>Need Completion Notification</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__NEED_COMPLETION_NOTIFICATION = PROJECT__NEED_COMPLETION_NOTIFICATION;

	/**
	 * The feature id for the '<em><b>Completion Notification Adapter</b></em>'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__COMPLETION_NOTIFICATION_ADAPTER = PROJECT__COMPLETION_NOTIFICATION_ADAPTER;

	/**
	 * The feature id for the '<em><b>Resource Reload Listener</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__RESOURCE_RELOAD_LISTENER = PROJECT__RESOURCE_RELOAD_LISTENER;

	/**
	 * The feature id for the '<em><b>Saving Resource</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__SAVING_RESOURCE = PROJECT__SAVING_RESOURCE;

	/**
	 * The feature id for the '<em><b>Revision</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__REVISION = PROJECT__REVISION;

	/**
	 * The feature id for the '<em><b>Revisions</b></em>' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__REVISIONS = PROJECT__REVISIONS;

	/**
	 * The feature id for the '<em><b>Project Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__PROJECT_VERSION = PROJECT__PROJECT_VERSION;

	/**
	 * The feature id for the '<em><b>Select Reveal Handler</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__SELECT_REVEAL_HANDLER = PROJECT__SELECT_REVEAL_HANDLER;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__MODEL_NAME = PROJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__PHASE = PROJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__VERSION = PROJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__LANGUAGE = PROJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__DOMAIN_MODEL = PROJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__CUSTOMIZERS = PROJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__PARENT = PROJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__LOCALE = PROJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Contribution</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__CONTRIBUTION = PROJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__DEPENDENCIES = PROJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__STATE = PROJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Latest Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__LATEST_VERSION = PROJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Model Resource Adapter</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__MODEL_RESOURCE_ADAPTER = PROJECT_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Contribution</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_FEATURE_COUNT = PROJECT_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.ItemImpl <em>Item</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.ItemImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getItem()
	 * @generated
	 */
	int ITEM = 3;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__MODEL_NAME = 0;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__PHASE = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__LANGUAGE = 3;

	/**
	 * The feature id for the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__DOMAIN_MODEL = 4;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__CUSTOMIZERS = 5;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__PARENT = 6;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__LOCALE = 7;

	/**
	 * The feature id for the '<em><b>Contribution</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__CONTRIBUTION = 8;

	/**
	 * The number of structural features of the '<em>Item</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.RevisionImpl <em>Revision</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.RevisionImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getRevision()
	 * @generated
	 */
	int REVISION = 1;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__MODEL_NAME = ITEM__MODEL_NAME;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__PHASE = ITEM__PHASE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__VERSION = ITEM__VERSION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__LANGUAGE = ITEM__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__DOMAIN_MODEL = ITEM__DOMAIN_MODEL;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__CUSTOMIZERS = ITEM__CUSTOMIZERS;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__PARENT = ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__LOCALE = ITEM__LOCALE;

	/**
	 * The feature id for the '<em><b>Contribution</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__CONTRIBUTION = ITEM__CONTRIBUTION;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__STATE = ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__TIMESTAMP = ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>State History</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__STATE_HISTORY = ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Latest Timestamp</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION__LATEST_TIMESTAMP = ITEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Revision</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REVISION_FEATURE_COUNT = ITEM_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.StateImpl <em>State</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.StateImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getState()
	 * @generated
	 */
	int STATE = 2;

	/**
	 * The feature id for the '<em><b>Model Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__MODEL_NAME = ITEM__MODEL_NAME;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__PHASE = ITEM__PHASE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__VERSION = ITEM__VERSION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__LANGUAGE = ITEM__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__DOMAIN_MODEL = ITEM__DOMAIN_MODEL;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__CUSTOMIZERS = ITEM__CUSTOMIZERS;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__PARENT = ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__LOCALE = ITEM__LOCALE;

	/**
	 * The feature id for the '<em><b>Contribution</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__CONTRIBUTION = ITEM__CONTRIBUTION;

	/**
	 * The feature id for the '<em><b>Package Class Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__PACKAGE_CLASS_NAME = ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Plugins</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__REQUIRED_PLUGINS = ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Revision</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__REVISION = ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__TIMESTAMP = ITEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Deployable Unit Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__DEPLOYABLE_UNIT_NAME = ITEM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Job Family</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__JOB_FAMILY = ITEM_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__RESOURCE = ITEM_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Commit Ids</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__COMMIT_IDS = ITEM_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Commit Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__COMMIT_ID = ITEM_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>State Customizers</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__STATE_CUSTOMIZERS = ITEM_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__PROJECT_NAME = ITEM_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Making</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__MAKING = ITEM_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>State</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = ITEM_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.common.notify.Adapter
	 * <em>Adapter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getAdapter()
	 * @generated
	 */
	int ADAPTER = 4;

	/**
	 * The number of structural features of the '<em>Adapter</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.WorkspaceImpl
	 * <em>Workspace</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.WorkspaceImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getWorkspace()
	 * @generated
	 */
	int WORKSPACE = 5;

	/**
	 * The feature id for the '<em><b>Projects</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__PROJECTS = ISCHEDULING_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resource Set</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__RESOURCE_SET = ISCHEDULING_RULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Customizers</b></em>' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__CUSTOMIZERS = ISCHEDULING_RULE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Service</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__SERVICE = ISCHEDULING_RULE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>SCM Registry</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__SCM_REGISTRY = ISCHEDULING_RULE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Workspace</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_FEATURE_COUNT = ISCHEDULING_RULE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.IntegerToStateMapEntryImpl
	 * <em>Integer To State Map Entry</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.IntegerToStateMapEntryImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIntegerToStateMapEntry()
	 * @generated
	 */
	int INTEGER_TO_STATE_MAP_ENTRY = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTEGER_TO_STATE_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTEGER_TO_STATE_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Integer To State Map
	 * Entry</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTEGER_TO_STATE_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.VersionToRevisionMapEntryImpl
	 * <em>Version To Revision Map Entry</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.VersionToRevisionMapEntryImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getVersionToRevisionMapEntry()
	 * @generated
	 */
	int VERSION_TO_REVISION_MAP_ENTRY = 7;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_TO_REVISION_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_TO_REVISION_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Version To Revision Map
	 * Entry</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_TO_REVISION_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link java.util.concurrent.Executor
	 * <em>Executor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.util.concurrent.Executor
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getExecutor()
	 * @generated
	 */
	int EXECUTOR = 9;

	/**
	 * The number of structural features of the '<em>Executor</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXECUTOR_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link java.util.concurrent.Future
	 * <em>Future</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.util.concurrent.Future
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getFuture()
	 * @generated
	 */
	int FUTURE = 10;

	/**
	 * The number of structural features of the '<em>Future</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FUTURE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.core.runtime.IAdapterFactory
	 * <em>IAdapter Factory</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIAdapterFactory()
	 * @generated
	 */
	int IADAPTER_FACTORY = 11;

	/**
	 * The number of structural features of the '<em>IAdapter Factory</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IADAPTER_FACTORY_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.CustomizerImpl
	 * <em>Customizer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.CustomizerImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCustomizer()
	 * @generated
	 */
	int CUSTOMIZER = 12;

	/**
	 * The feature id for the '<em><b>Rank</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZER__RANK = 0;

	/**
	 * The number of structural features of the '<em>Customizer</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.StageQualifierImpl <em>Stage
	 * Qualifier</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.StageQualifierImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStageQualifier()
	 * @generated
	 */
	int STAGE_QUALIFIER = 13;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER__STAGE = 0;

	/**
	 * The feature id for the '<em><b>Step</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER__STEP = 1;

	/**
	 * The number of structural features of the '<em>Stage Qualifier</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.StageQualifierToCustomizerMapEntryImpl
	 * <em>Stage Qualifier To Customizer Map Entry</em>}' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.StageQualifierToCustomizerMapEntryImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStageQualifierToCustomizerMapEntry()
	 * @generated
	 */
	int STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY = 14;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Stage Qualifier To Customizer
	 * Map Entry</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.ModelPairImpl <em>Model
	 * Pair</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.ModelPairImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getModelPair()
	 * @generated
	 */
	int MODEL_PAIR = 15;

	/**
	 * The feature id for the '<em><b>Dynamic</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODEL_PAIR__DYNAMIC = 0;

	/**
	 * The feature id for the '<em><b>Generated</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODEL_PAIR__GENERATED = 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODEL_PAIR__PARENT = 2;

	/**
	 * The number of structural features of the '<em>Model Pair</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MODEL_PAIR_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.SCMOperatorImpl <em>SCM
	 * Operator</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.SCMOperatorImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSCMOperator()
	 * @generated
	 */
	int SCM_OPERATOR = 16;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCM_OPERATOR__PROJECT_NAME = 0;

	/**
	 * The feature id for the '<em><b>Registry</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCM_OPERATOR__REGISTRY = 1;

	/**
	 * The number of structural features of the '<em>SCM Operator</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCM_OPERATOR_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.SCMRegistryImpl <em>SCM
	 * Registry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.SCMRegistryImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSCMRegistry()
	 * @generated
	 */
	int SCM_REGISTRY = 17;

	/**
	 * The number of structural features of the '<em>SCM Registry</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCM_REGISTRY_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.ecore.resource.Resource
	 * <em>Resource</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.resource.Resource
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 19;

	/**
	 * The number of structural features of the '<em>Resource</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.ResourceChangeListenerImpl
	 * <em>Resource Change Listener</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.ResourceChangeListenerImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResourceChangeListener()
	 * @generated
	 */
	int RESOURCE_CHANGE_LISTENER = 20;

	/**
	 * The number of structural features of the '<em>Resource Change Listener</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CHANGE_LISTENER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.common.notify.Notification
	 * <em>Notification</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.notify.Notification
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getNotification()
	 * @generated
	 */
	int NOTIFICATION = 21;

	/**
	 * The number of structural features of the '<em>Notification</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.CompletionListenerImpl
	 * <em>Completion Listener</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.CompletionListenerImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCompletionListener()
	 * @generated
	 */
	int COMPLETION_LISTENER = 22;

	/**
	 * The number of structural features of the '<em>Completion Listener</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPLETION_LISTENER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.jobs.Worker <em>Worker</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.jobs.Worker
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getWorker()
	 * @generated
	 */
	int WORKER = 27;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.ResourceAdapterImpl <em>Resource
	 * Adapter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.ResourceAdapterImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResourceAdapter()
	 * @generated
	 */
	int RESOURCE_ADAPTER = 23;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__RESOURCE = ADAPTER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Filename</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__FILENAME = ADAPTER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Project</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__PROJECT = ADAPTER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Resource Adapter</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER_FEATURE_COUNT = ADAPTER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl
	 * <em>Service</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getClassMakerService()
	 * @generated
	 */
	int CLASS_MAKER_SERVICE = 24;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLASS_MAKER_SERVICE__WORKSPACE = 0;

	/**
	 * The number of structural features of the '<em>Service</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLASS_MAKER_SERVICE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.BlueprintImpl
	 * <em>Blueprint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.BlueprintImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getBlueprint()
	 * @generated
	 */
	int BLUEPRINT = 25;

	/**
	 * The feature id for the '<em><b>Dynamic Model</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BLUEPRINT__DYNAMIC_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BLUEPRINT__DEPENDENCIES = 1;

	/**
	 * The feature id for the '<em><b>Completion Listeners</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BLUEPRINT__COMPLETION_LISTENERS = 2;

	/**
	 * The number of structural features of the '<em>Blueprint</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BLUEPRINT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.CompletionNotificationAdapterImpl
	 * <em>Completion Notification Adapter</em>}' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.CompletionNotificationAdapterImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCompletionNotificationAdapter()
	 * @generated
	 */
	int COMPLETION_NOTIFICATION_ADAPTER = 26;

	/**
	 * The feature id for the '<em><b>Error</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPLETION_NOTIFICATION_ADAPTER__ERROR = ADAPTER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Completion Notification
	 * Adapter</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPLETION_NOTIFICATION_ADAPTER_FEATURE_COUNT = ADAPTER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Worker</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.enterprisedomain.classmaker.impl.SelectRevealHandlerImpl
	 * <em>Select Reveal Handler</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.impl.SelectRevealHandlerImpl
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSelectRevealHandler()
	 * @generated
	 */
	int SELECT_REVEAL_HANDLER = 28;

	/**
	 * The number of structural features of the '<em>Select Reveal Handler</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SELECT_REVEAL_HANDLER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '<em>Locale</em>' data type. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see java.util.Locale
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getLocale()
	 * @generated
	 */
	int LOCALE = 39;

	/**
	 * The meta object id for the '{@link org.enterprisedomain.classmaker.Stage
	 * <em>Stage</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.enterprisedomain.classmaker.Stage
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStage()
	 * @generated
	 */
	int STAGE = 29;

	/**
	 * The meta object id for the '<em>Properties</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.util.Properties
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getProperties()
	 * @generated
	 */
	int PROPERTIES = 30;

	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 31;

	/**
	 * The meta object id for the '<em>OS Gi Version</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.osgi.framework.Version
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getOSGiVersion()
	 * @generated
	 */
	int OS_GI_VERSION = 32;

	/**
	 * The meta object id for the '<em>Semaphore</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.util.concurrent.Semaphore
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSemaphore()
	 * @generated
	 */
	int SEMAPHORE = 33;

	/**
	 * The meta object id for the '<em>Core Exception</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.CoreException
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCoreException()
	 * @generated
	 */
	int CORE_EXCEPTION = 34;

	/**
	 * The meta object id for the '<em>Invocation Target Exception</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.lang.reflect.InvocationTargetException
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getInvocationTargetException()
	 * @generated
	 */
	int INVOCATION_TARGET_EXCEPTION = 35;

	/**
	 * The meta object id for the '<em>IStatus</em>' data type. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IStatus
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIStatus()
	 * @generated
	 */
	int ISTATUS = 36;

	/**
	 * The meta object id for the '<em>URI</em>' data type. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.util.URI
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getURI()
	 * @generated
	 */
	int URI = 37;

	/**
	 * The meta object id for the '<em>Exception</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.lang.Exception
	 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getException()
	 * @generated
	 */
	int EXCEPTION = 38;

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.Contribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Contribution</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution
	 * @generated
	 */
	EClass getContribution();

	/**
	 * Returns the meta object for the attribute list
	 * '{@link org.enterprisedomain.classmaker.Contribution#getDependencies
	 * <em>Dependencies</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Dependencies</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#getDependencies()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_Dependencies();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Contribution#getState
	 * <em>State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#getState()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_State();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Contribution#getLatestVersion
	 * <em>Latest Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Latest Version</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#getLatestVersion()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_LatestVersion();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Contribution#getModelResourceAdapter
	 * <em>Model Resource Adapter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference '<em>Model Resource Adapter</em>'.
	 * @see org.enterprisedomain.classmaker.Contribution#getModelResourceAdapter()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_ModelResourceAdapter();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.Revision <em>Revision</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Revision</em>'.
	 * @see org.enterprisedomain.classmaker.Revision
	 * @generated
	 */
	EClass getRevision();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Revision#getState <em>State</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see org.enterprisedomain.classmaker.Revision#getState()
	 * @see #getRevision()
	 * @generated
	 */
	EReference getRevision_State();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Revision#getTimestamp
	 * <em>Timestamp</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.enterprisedomain.classmaker.Revision#getTimestamp()
	 * @see #getRevision()
	 * @generated
	 */
	EAttribute getRevision_Timestamp();

	/**
	 * Returns the meta object for the map
	 * '{@link org.enterprisedomain.classmaker.Revision#getStateHistory <em>State
	 * History</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>State History</em>'.
	 * @see org.enterprisedomain.classmaker.Revision#getStateHistory()
	 * @see #getRevision()
	 * @generated
	 */
	EReference getRevision_StateHistory();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Revision#getLatestTimestamp
	 * <em>Latest Timestamp</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Latest Timestamp</em>'.
	 * @see org.enterprisedomain.classmaker.Revision#getLatestTimestamp()
	 * @see #getRevision()
	 * @generated
	 */
	EAttribute getRevision_LatestTimestamp();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.State <em>State</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>State</em>'.
	 * @see org.enterprisedomain.classmaker.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.State#getPackageClassName <em>Package
	 * Class Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Package Class Name</em>'.
	 * @see org.enterprisedomain.classmaker.State#getPackageClassName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_PackageClassName();

	/**
	 * Returns the meta object for the attribute list
	 * '{@link org.enterprisedomain.classmaker.State#getRequiredPlugins <em>Required
	 * Plugins</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Required Plugins</em>'.
	 * @see org.enterprisedomain.classmaker.State#getRequiredPlugins()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_RequiredPlugins();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.State#getRevision
	 * <em>Revision</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Revision</em>'.
	 * @see org.enterprisedomain.classmaker.State#getRevision()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Revision();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.State#getTimestamp
	 * <em>Timestamp</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.enterprisedomain.classmaker.State#getTimestamp()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Timestamp();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.State#getDeployableUnitName
	 * <em>Deployable Unit Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Deployable Unit Name</em>'.
	 * @see org.enterprisedomain.classmaker.State#getDeployableUnitName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_DeployableUnitName();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.State#getJobFamily <em>Job
	 * Family</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Job Family</em>'.
	 * @see org.enterprisedomain.classmaker.State#getJobFamily()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_JobFamily();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.State#getResource
	 * <em>Resource</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Resource</em>'.
	 * @see org.enterprisedomain.classmaker.State#getResource()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Resource();

	/**
	 * Returns the meta object for the attribute list
	 * '{@link org.enterprisedomain.classmaker.State#getCommitIds <em>Commit
	 * Ids</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Commit Ids</em>'.
	 * @see org.enterprisedomain.classmaker.State#getCommitIds()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_CommitIds();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.State#getCommitId <em>Commit
	 * Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Commit Id</em>'.
	 * @see org.enterprisedomain.classmaker.State#getCommitId()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_CommitId();

	/**
	 * Returns the meta object for the map
	 * '{@link org.enterprisedomain.classmaker.State#getStateCustomizers <em>State
	 * Customizers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>State Customizers</em>'.
	 * @see org.enterprisedomain.classmaker.State#getStateCustomizers()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_StateCustomizers();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.State#getProjectName <em>Project
	 * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.enterprisedomain.classmaker.State#getProjectName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_ProjectName();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.State#isMaking <em>Making</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Making</em>'.
	 * @see org.enterprisedomain.classmaker.State#isMaking()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Making();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.Item <em>Item</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Item</em>'.
	 * @see org.enterprisedomain.classmaker.Item
	 * @generated
	 */
	EClass getItem();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Item#getModelName <em>Model
	 * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Model Name</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getModelName()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_ModelName();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Item#getPhase <em>Phase</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Phase</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getPhase()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Phase();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Item#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getVersion()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Version();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Item#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getLanguage()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Language();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link org.enterprisedomain.classmaker.Item#getDomainModel <em>Domain
	 * Model</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Domain
	 *         Model</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getDomainModel()
	 * @see #getItem()
	 * @generated
	 */
	EReference getItem_DomainModel();

	/**
	 * Returns the meta object for the map
	 * '{@link org.enterprisedomain.classmaker.Item#getCustomizers
	 * <em>Customizers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Customizers</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getCustomizers()
	 * @see #getItem()
	 * @generated
	 */
	EReference getItem_Customizers();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Item#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getParent()
	 * @see #getItem()
	 * @generated
	 */
	EReference getItem_Parent();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Item#getLocale <em>Locale</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Locale</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getLocale()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Locale();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Item#getContribution
	 * <em>Contribution</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Contribution</em>'.
	 * @see org.enterprisedomain.classmaker.Item#getContribution()
	 * @see #getItem()
	 * @generated
	 */
	EReference getItem_Contribution();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emf.common.notify.Adapter <em>Adapter</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Adapter</em>'.
	 * @see org.eclipse.emf.common.notify.Adapter
	 * @model instanceClass="org.eclipse.emf.common.notify.Adapter"
	 * @generated
	 */
	EClass getAdapter();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.Workspace <em>Workspace</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Workspace</em>'.
	 * @see org.enterprisedomain.classmaker.Workspace
	 * @generated
	 */
	EClass getWorkspace();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link org.enterprisedomain.classmaker.Workspace#getProjects
	 * <em>Projects</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list
	 *         '<em>Projects</em>'.
	 * @see org.enterprisedomain.classmaker.Workspace#getProjects()
	 * @see #getWorkspace()
	 * @generated
	 */
	EReference getWorkspace_Projects();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Workspace#getResourceSet <em>Resource
	 * Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Resource Set</em>'.
	 * @see org.enterprisedomain.classmaker.Workspace#getResourceSet()
	 * @see #getWorkspace()
	 * @generated
	 */
	EAttribute getWorkspace_ResourceSet();

	/**
	 * Returns the meta object for the map
	 * '{@link org.enterprisedomain.classmaker.Workspace#getCustomizers
	 * <em>Customizers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Customizers</em>'.
	 * @see org.enterprisedomain.classmaker.Workspace#getCustomizers()
	 * @see #getWorkspace()
	 * @generated
	 */
	EReference getWorkspace_Customizers();

	/**
	 * Returns the meta object for the container reference
	 * '{@link org.enterprisedomain.classmaker.Workspace#getService
	 * <em>Service</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Service</em>'.
	 * @see org.enterprisedomain.classmaker.Workspace#getService()
	 * @see #getWorkspace()
	 * @generated
	 */
	EReference getWorkspace_Service();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Workspace#getSCMRegistry <em>SCM
	 * Registry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>SCM Registry</em>'.
	 * @see org.enterprisedomain.classmaker.Workspace#getSCMRegistry()
	 * @see #getWorkspace()
	 * @generated
	 */
	EReference getWorkspace_SCMRegistry();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Integer To
	 * State Map Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Integer To State Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EIntegerObject"
	 *        valueType="org.enterprisedomain.classmaker.State"
	 *        valueContainment="true"
	 * @generated
	 */
	EClass getIntegerToStateMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry
	 * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getIntegerToStateMapEntry()
	 * @generated
	 */
	EAttribute getIntegerToStateMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link java.util.Map.Entry <em>Value</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getIntegerToStateMapEntry()
	 * @generated
	 */
	EReference getIntegerToStateMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Version To
	 * Revision Map Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Version To Revision Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.enterprisedomain.classmaker.OSGiVersion"
	 *        valueType="org.enterprisedomain.classmaker.Revision"
	 *        valueContainment="true"
	 * @generated
	 */
	EClass getVersionToRevisionMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry
	 * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVersionToRevisionMapEntry()
	 * @generated
	 */
	EAttribute getVersionToRevisionMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link java.util.Map.Entry <em>Value</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVersionToRevisionMapEntry()
	 * @generated
	 */
	EReference getVersionToRevisionMapEntry_Value();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling
	 * Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IScheduling Rule</em>'.
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
	 * @model instanceClass="org.eclipse.core.runtime.jobs.ISchedulingRule"
	 * @generated
	 */
	EClass getISchedulingRule();

	/**
	 * Returns the meta object for class '{@link java.util.concurrent.Executor
	 * <em>Executor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Executor</em>'.
	 * @see java.util.concurrent.Executor
	 * @model instanceClass="java.util.concurrent.Executor"
	 * @generated
	 */
	EClass getExecutor();

	/**
	 * Returns the meta object for class '{@link java.util.concurrent.Future
	 * <em>Future</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Future</em>'.
	 * @see java.util.concurrent.Future
	 * @model instanceClass="java.util.concurrent.Future" typeParameters="V"
	 * @generated
	 */
	EClass getFuture();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.core.runtime.IAdapterFactory <em>IAdapter Factory</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IAdapter Factory</em>'.
	 * @see org.eclipse.core.runtime.IAdapterFactory
	 * @model instanceClass="org.eclipse.core.runtime.IAdapterFactory"
	 * @generated
	 */
	EClass getIAdapterFactory();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.Customizer <em>Customizer</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Customizer</em>'.
	 * @see org.enterprisedomain.classmaker.Customizer
	 * @generated
	 */
	EClass getCustomizer();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Customizer#getRank <em>Rank</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Rank</em>'.
	 * @see org.enterprisedomain.classmaker.Customizer#getRank()
	 * @see #getCustomizer()
	 * @generated
	 */
	EAttribute getCustomizer_Rank();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.StageQualifier <em>Stage
	 * Qualifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Stage Qualifier</em>'.
	 * @see org.enterprisedomain.classmaker.StageQualifier
	 * @generated
	 */
	EClass getStageQualifier();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.StageQualifier#getStage
	 * <em>Stage</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Stage</em>'.
	 * @see org.enterprisedomain.classmaker.StageQualifier#getStage()
	 * @see #getStageQualifier()
	 * @generated
	 */
	EAttribute getStageQualifier_Stage();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.StageQualifier#getStep
	 * <em>Step</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Step</em>'.
	 * @see org.enterprisedomain.classmaker.StageQualifier#getStep()
	 * @see #getStageQualifier()
	 * @generated
	 */
	EAttribute getStageQualifier_Step();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Stage
	 * Qualifier To Customizer Map Entry</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Stage Qualifier To Customizer Map
	 *         Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="org.enterprisedomain.classmaker.StageQualifier"
	 *        valueType="org.enterprisedomain.classmaker.Customizer"
	 *        valueContainment="true"
	 * @generated
	 */
	EClass getStageQualifierToCustomizerMapEntry();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry
	 * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStageQualifierToCustomizerMapEntry()
	 * @generated
	 */
	EReference getStageQualifierToCustomizerMapEntry_Key();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link java.util.Map.Entry <em>Value</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStageQualifierToCustomizerMapEntry()
	 * @generated
	 */
	EReference getStageQualifierToCustomizerMapEntry_Value();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.Project <em>Project</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Project</em>'.
	 * @see org.enterprisedomain.classmaker.Project
	 * @generated
	 */
	EClass getProject();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Project#getName <em>Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getName()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Name();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Project#getProjectName <em>Project
	 * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getProjectName()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_ProjectName();

	/**
	 * Returns the meta object for the attribute list
	 * '{@link org.enterprisedomain.classmaker.Project#getChildren
	 * <em>Children</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Children</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getChildren()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Children();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Project#isDirty <em>Dirty</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Dirty</em>'.
	 * @see org.enterprisedomain.classmaker.Project#isDirty()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Dirty();

	/**
	 * Returns the meta object for the container reference
	 * '{@link org.enterprisedomain.classmaker.Project#getWorkspace
	 * <em>Workspace</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Workspace</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getWorkspace()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Workspace();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Project#getResourcePath <em>Resource
	 * Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Resource Path</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getResourcePath()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_ResourcePath();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Project#isNeedCompletionNotification
	 * <em>Need Completion Notification</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Need Completion
	 *         Notification</em>'.
	 * @see org.enterprisedomain.classmaker.Project#isNeedCompletionNotification()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_NeedCompletionNotification();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link org.enterprisedomain.classmaker.Project#getCompletionNotificationAdapter
	 * <em>Completion Notification Adapter</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Completion
	 *         Notification Adapter</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getCompletionNotificationAdapter()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_CompletionNotificationAdapter();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Project#getResourceReloadListener
	 * <em>Resource Reload Listener</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Resource Reload
	 *         Listener</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getResourceReloadListener()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_ResourceReloadListener();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Project#isSavingResource <em>Saving
	 * Resource</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Saving Resource</em>'.
	 * @see org.enterprisedomain.classmaker.Project#isSavingResource()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_SavingResource();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Project#getRevision
	 * <em>Revision</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Revision</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getRevision()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Revision();

	/**
	 * Returns the meta object for the map
	 * '{@link org.enterprisedomain.classmaker.Project#getRevisions
	 * <em>Revisions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Revisions</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getRevisions()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Revisions();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.Project#getProjectVersion <em>Project
	 * Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Project Version</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getProjectVersion()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_ProjectVersion();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Project#getSelectRevealHandler
	 * <em>Select Reveal Handler</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference '<em>Select Reveal Handler</em>'.
	 * @see org.enterprisedomain.classmaker.Project#getSelectRevealHandler()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_SelectRevealHandler();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.ModelPair <em>Model Pair</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Model Pair</em>'.
	 * @see org.enterprisedomain.classmaker.ModelPair
	 * @generated
	 */
	EClass getModelPair();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link org.enterprisedomain.classmaker.ModelPair#getDynamic
	 * <em>Dynamic</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Dynamic</em>'.
	 * @see org.enterprisedomain.classmaker.ModelPair#getDynamic()
	 * @see #getModelPair()
	 * @generated
	 */
	EReference getModelPair_Dynamic();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.ModelPair#getGenerated
	 * <em>Generated</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Generated</em>'.
	 * @see org.enterprisedomain.classmaker.ModelPair#getGenerated()
	 * @see #getModelPair()
	 * @generated
	 */
	EReference getModelPair_Generated();

	/**
	 * Returns the meta object for the container reference
	 * '{@link org.enterprisedomain.classmaker.ModelPair#getParent
	 * <em>Parent</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.enterprisedomain.classmaker.ModelPair#getParent()
	 * @see #getModelPair()
	 * @generated
	 */
	EReference getModelPair_Parent();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.SCMOperator <em>SCM Operator</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>SCM Operator</em>'.
	 * @see org.enterprisedomain.classmaker.SCMOperator
	 * @generated
	 */
	EClass getSCMOperator();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.SCMOperator#getProjectName
	 * <em>Project Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.enterprisedomain.classmaker.SCMOperator#getProjectName()
	 * @see #getSCMOperator()
	 * @generated
	 */
	EAttribute getSCMOperator_ProjectName();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.SCMOperator#getRegistry
	 * <em>Registry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Registry</em>'.
	 * @see org.enterprisedomain.classmaker.SCMOperator#getRegistry()
	 * @see #getSCMOperator()
	 * @generated
	 */
	EReference getSCMOperator_Registry();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.SCMRegistry <em>SCM Registry</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>SCM Registry</em>'.
	 * @see org.enterprisedomain.classmaker.SCMRegistry
	 * @generated
	 */
	EClass getSCMRegistry();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emf.ecore.resource.Resource <em>Resource</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see org.eclipse.emf.ecore.resource.Resource
	 * @model instanceClass="org.eclipse.emf.ecore.resource.Resource"
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.ResourceChangeListener <em>Resource
	 * Change Listener</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Resource Change Listener</em>'.
	 * @see org.enterprisedomain.classmaker.ResourceChangeListener
	 * @generated
	 */
	EClass getResourceChangeListener();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emf.common.notify.Notification <em>Notification</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Notification</em>'.
	 * @see org.eclipse.emf.common.notify.Notification
	 * @model instanceClass="org.eclipse.emf.common.notify.Notification"
	 * @generated
	 */
	EClass getNotification();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.CompletionListener <em>Completion
	 * Listener</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Completion Listener</em>'.
	 * @see org.enterprisedomain.classmaker.CompletionListener
	 * @generated
	 */
	EClass getCompletionListener();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.jobs.Worker <em>Worker</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Worker</em>'.
	 * @see org.enterprisedomain.classmaker.jobs.Worker
	 * @model instanceClass="org.enterprisedomain.classmaker.jobs.Worker"
	 * @generated
	 */
	EClass getWorker();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.SelectRevealHandler <em>Select Reveal
	 * Handler</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Select Reveal Handler</em>'.
	 * @see org.enterprisedomain.classmaker.SelectRevealHandler
	 * @generated
	 */
	EClass getSelectRevealHandler();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.ResourceAdapter <em>Resource
	 * Adapter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Resource Adapter</em>'.
	 * @see org.enterprisedomain.classmaker.ResourceAdapter
	 * @generated
	 */
	EClass getResourceAdapter();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.ResourceAdapter#getResource
	 * <em>Resource</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Resource</em>'.
	 * @see org.enterprisedomain.classmaker.ResourceAdapter#getResource()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_Resource();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.ResourceAdapter#getFilename
	 * <em>Filename</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filename</em>'.
	 * @see org.enterprisedomain.classmaker.ResourceAdapter#getFilename()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EAttribute getResourceAdapter_Filename();

	/**
	 * Returns the meta object for the container reference
	 * '{@link org.enterprisedomain.classmaker.ResourceAdapter#getProject
	 * <em>Project</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Project</em>'.
	 * @see org.enterprisedomain.classmaker.ResourceAdapter#getProject()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_Project();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.ClassMakerService <em>Service</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Service</em>'.
	 * @see org.enterprisedomain.classmaker.ClassMakerService
	 * @generated
	 */
	EClass getClassMakerService();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link org.enterprisedomain.classmaker.ClassMakerService#getWorkspace
	 * <em>Workspace</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Workspace</em>'.
	 * @see org.enterprisedomain.classmaker.ClassMakerService#getWorkspace()
	 * @see #getClassMakerService()
	 * @generated
	 */
	EReference getClassMakerService_Workspace();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.Blueprint <em>Blueprint</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Blueprint</em>'.
	 * @see org.enterprisedomain.classmaker.Blueprint
	 * @generated
	 */
	EClass getBlueprint();

	/**
	 * Returns the meta object for the reference
	 * '{@link org.enterprisedomain.classmaker.Blueprint#getDynamicModel <em>Dynamic
	 * Model</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Dynamic Model</em>'.
	 * @see org.enterprisedomain.classmaker.Blueprint#getDynamicModel()
	 * @see #getBlueprint()
	 * @generated
	 */
	EReference getBlueprint_DynamicModel();

	/**
	 * Returns the meta object for the attribute list
	 * '{@link org.enterprisedomain.classmaker.Blueprint#getDependencies
	 * <em>Dependencies</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Dependencies</em>'.
	 * @see org.enterprisedomain.classmaker.Blueprint#getDependencies()
	 * @see #getBlueprint()
	 * @generated
	 */
	EAttribute getBlueprint_Dependencies();

	/**
	 * Returns the meta object for the reference list
	 * '{@link org.enterprisedomain.classmaker.Blueprint#getCompletionListeners
	 * <em>Completion Listeners</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference list '<em>Completion
	 *         Listeners</em>'.
	 * @see org.enterprisedomain.classmaker.Blueprint#getCompletionListeners()
	 * @see #getBlueprint()
	 * @generated
	 */
	EReference getBlueprint_CompletionListeners();

	/**
	 * Returns the meta object for class
	 * '{@link org.enterprisedomain.classmaker.CompletionNotificationAdapter
	 * <em>Completion Notification Adapter</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Completion Notification Adapter</em>'.
	 * @see org.enterprisedomain.classmaker.CompletionNotificationAdapter
	 * @generated
	 */
	EClass getCompletionNotificationAdapter();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.enterprisedomain.classmaker.CompletionNotificationAdapter#getError
	 * <em>Error</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Error</em>'.
	 * @see org.enterprisedomain.classmaker.CompletionNotificationAdapter#getError()
	 * @see #getCompletionNotificationAdapter()
	 * @generated
	 */
	EAttribute getCompletionNotificationAdapter_Error();

	/**
	 * Returns the meta object for data type '{@link java.util.Locale
	 * <em>Locale</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Locale</em>'.
	 * @see java.util.Locale
	 * @model instanceClass="java.util.Locale"
	 * @generated
	 */
	EDataType getLocale();

	/**
	 * Returns the meta object for enum
	 * '{@link org.enterprisedomain.classmaker.Stage <em>Stage</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Stage</em>'.
	 * @see org.enterprisedomain.classmaker.Stage
	 * @generated
	 */
	EEnum getStage();

	/**
	 * Returns the meta object for data type '{@link java.util.Properties
	 * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Properties</em>'.
	 * @see java.util.Properties
	 * @model instanceClass="java.util.Properties"
	 * @generated
	 */
	EDataType getProperties();

	/**
	 * Returns the meta object for data type
	 * '{@link org.eclipse.core.runtime.IProgressMonitor <em>IProgress
	 * Monitor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IProgress Monitor</em>'.
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @model instanceClass="org.eclipse.core.runtime.IProgressMonitor"
	 * @generated
	 */
	EDataType getIProgressMonitor();

	/**
	 * Returns the meta object for data type '{@link org.osgi.framework.Version
	 * <em>OS Gi Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>OS Gi Version</em>'.
	 * @see org.osgi.framework.Version
	 * @model instanceClass="org.osgi.framework.Version"
	 * @generated
	 */
	EDataType getOSGiVersion();

	/**
	 * Returns the meta object for data type '{@link java.util.concurrent.Semaphore
	 * <em>Semaphore</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Semaphore</em>'.
	 * @see java.util.concurrent.Semaphore
	 * @model instanceClass="java.util.concurrent.Semaphore"
	 * @generated
	 */
	EDataType getSemaphore();

	/**
	 * Returns the meta object for data type
	 * '{@link org.eclipse.core.runtime.CoreException <em>Core Exception</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Core Exception</em>'.
	 * @see org.eclipse.core.runtime.CoreException
	 * @model instanceClass="org.eclipse.core.runtime.CoreException"
	 * @generated
	 */
	EDataType getCoreException();

	/**
	 * Returns the meta object for data type
	 * '{@link java.lang.reflect.InvocationTargetException <em>Invocation Target
	 * Exception</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Invocation Target Exception</em>'.
	 * @see java.lang.reflect.InvocationTargetException
	 * @model instanceClass="java.lang.reflect.InvocationTargetException"
	 * @generated
	 */
	EDataType getInvocationTargetException();

	/**
	 * Returns the meta object for data type
	 * '{@link org.eclipse.core.runtime.IStatus <em>IStatus</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IStatus</em>'.
	 * @see org.eclipse.core.runtime.IStatus
	 * @model instanceClass="org.eclipse.core.runtime.IStatus"
	 * @generated
	 */
	EDataType getIStatus();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.URI
	 * <em>URI</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see org.eclipse.emf.common.util.URI
	 * @model instanceClass="org.eclipse.emf.common.util.URI"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the meta object for data type '{@link java.lang.Exception
	 * <em>Exception</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Exception</em>'.
	 * @see java.lang.Exception
	 * @model instanceClass="java.lang.Exception"
	 * @generated
	 */
	EDataType getException();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ClassMakerFactory getClassMakerFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.ContributionImpl
		 * <em>Contribution</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.ContributionImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getContribution()
		 * @generated
		 */
		EClass CONTRIBUTION = eINSTANCE.getContribution();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' attribute list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONTRIBUTION__DEPENDENCIES = eINSTANCE.getContribution_Dependencies();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTRIBUTION__STATE = eINSTANCE.getContribution_State();

		/**
		 * The meta object literal for the '<em><b>Latest Version</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONTRIBUTION__LATEST_VERSION = eINSTANCE.getContribution_LatestVersion();

		/**
		 * The meta object literal for the '<em><b>Model Resource Adapter</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTRIBUTION__MODEL_RESOURCE_ADAPTER = eINSTANCE.getContribution_ModelResourceAdapter();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.RevisionImpl <em>Revision</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.RevisionImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getRevision()
		 * @generated
		 */
		EClass REVISION = eINSTANCE.getRevision();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference REVISION__STATE = eINSTANCE.getRevision_State();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REVISION__TIMESTAMP = eINSTANCE.getRevision_Timestamp();

		/**
		 * The meta object literal for the '<em><b>State History</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference REVISION__STATE_HISTORY = eINSTANCE.getRevision_StateHistory();

		/**
		 * The meta object literal for the '<em><b>Latest Timestamp</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REVISION__LATEST_TIMESTAMP = eINSTANCE.getRevision_LatestTimestamp();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.StateImpl <em>State</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.StateImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Package Class Name</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__PACKAGE_CLASS_NAME = eINSTANCE.getState_PackageClassName();

		/**
		 * The meta object literal for the '<em><b>Required Plugins</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__REQUIRED_PLUGINS = eINSTANCE.getState_RequiredPlugins();

		/**
		 * The meta object literal for the '<em><b>Revision</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STATE__REVISION = eINSTANCE.getState_Revision();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__TIMESTAMP = eINSTANCE.getState_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Deployable Unit Name</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__DEPLOYABLE_UNIT_NAME = eINSTANCE.getState_DeployableUnitName();

		/**
		 * The meta object literal for the '<em><b>Job Family</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__JOB_FAMILY = eINSTANCE.getState_JobFamily();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STATE__RESOURCE = eINSTANCE.getState_Resource();

		/**
		 * The meta object literal for the '<em><b>Commit Ids</b></em>' attribute list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__COMMIT_IDS = eINSTANCE.getState_CommitIds();

		/**
		 * The meta object literal for the '<em><b>Commit Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__COMMIT_ID = eINSTANCE.getState_CommitId();

		/**
		 * The meta object literal for the '<em><b>State Customizers</b></em>' map
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STATE__STATE_CUSTOMIZERS = eINSTANCE.getState_StateCustomizers();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__PROJECT_NAME = eINSTANCE.getState_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Making</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__MAKING = eINSTANCE.getState_Making();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.ItemImpl <em>Item</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.ItemImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getItem()
		 * @generated
		 */
		EClass ITEM = eINSTANCE.getItem();

		/**
		 * The meta object literal for the '<em><b>Model Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITEM__MODEL_NAME = eINSTANCE.getItem_ModelName();

		/**
		 * The meta object literal for the '<em><b>Phase</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITEM__PHASE = eINSTANCE.getItem_Phase();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITEM__VERSION = eINSTANCE.getItem_Version();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITEM__LANGUAGE = eINSTANCE.getItem_Language();

		/**
		 * The meta object literal for the '<em><b>Domain Model</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ITEM__DOMAIN_MODEL = eINSTANCE.getItem_DomainModel();

		/**
		 * The meta object literal for the '<em><b>Customizers</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ITEM__CUSTOMIZERS = eINSTANCE.getItem_Customizers();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ITEM__PARENT = eINSTANCE.getItem_Parent();

		/**
		 * The meta object literal for the '<em><b>Locale</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITEM__LOCALE = eINSTANCE.getItem_Locale();

		/**
		 * The meta object literal for the '<em><b>Contribution</b></em>' reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ITEM__CONTRIBUTION = eINSTANCE.getItem_Contribution();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.common.notify.Adapter
		 * <em>Adapter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getAdapter()
		 * @generated
		 */
		EClass ADAPTER = eINSTANCE.getAdapter();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.WorkspaceImpl
		 * <em>Workspace</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.WorkspaceImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getWorkspace()
		 * @generated
		 */
		EClass WORKSPACE = eINSTANCE.getWorkspace();

		/**
		 * The meta object literal for the '<em><b>Projects</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference WORKSPACE__PROJECTS = eINSTANCE.getWorkspace_Projects();

		/**
		 * The meta object literal for the '<em><b>Resource Set</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute WORKSPACE__RESOURCE_SET = eINSTANCE.getWorkspace_ResourceSet();

		/**
		 * The meta object literal for the '<em><b>Customizers</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference WORKSPACE__CUSTOMIZERS = eINSTANCE.getWorkspace_Customizers();

		/**
		 * The meta object literal for the '<em><b>Service</b></em>' container reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference WORKSPACE__SERVICE = eINSTANCE.getWorkspace_Service();

		/**
		 * The meta object literal for the '<em><b>SCM Registry</b></em>' reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference WORKSPACE__SCM_REGISTRY = eINSTANCE.getWorkspace_SCMRegistry();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.IntegerToStateMapEntryImpl
		 * <em>Integer To State Map Entry</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.IntegerToStateMapEntryImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIntegerToStateMapEntry()
		 * @generated
		 */
		EClass INTEGER_TO_STATE_MAP_ENTRY = eINSTANCE.getIntegerToStateMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INTEGER_TO_STATE_MAP_ENTRY__KEY = eINSTANCE.getIntegerToStateMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INTEGER_TO_STATE_MAP_ENTRY__VALUE = eINSTANCE.getIntegerToStateMapEntry_Value();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.VersionToRevisionMapEntryImpl
		 * <em>Version To Revision Map Entry</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.VersionToRevisionMapEntryImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getVersionToRevisionMapEntry()
		 * @generated
		 */
		EClass VERSION_TO_REVISION_MAP_ENTRY = eINSTANCE.getVersionToRevisionMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VERSION_TO_REVISION_MAP_ENTRY__KEY = eINSTANCE.getVersionToRevisionMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VERSION_TO_REVISION_MAP_ENTRY__VALUE = eINSTANCE.getVersionToRevisionMapEntry_Value();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.core.runtime.jobs.ISchedulingRule <em>IScheduling
		 * Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.jobs.ISchedulingRule
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getISchedulingRule()
		 * @generated
		 */
		EClass ISCHEDULING_RULE = eINSTANCE.getISchedulingRule();

		/**
		 * The meta object literal for the '{@link java.util.concurrent.Executor
		 * <em>Executor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.util.concurrent.Executor
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getExecutor()
		 * @generated
		 */
		EClass EXECUTOR = eINSTANCE.getExecutor();

		/**
		 * The meta object literal for the '{@link java.util.concurrent.Future
		 * <em>Future</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.util.concurrent.Future
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getFuture()
		 * @generated
		 */
		EClass FUTURE = eINSTANCE.getFuture();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.core.runtime.IAdapterFactory <em>IAdapter Factory</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.IAdapterFactory
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIAdapterFactory()
		 * @generated
		 */
		EClass IADAPTER_FACTORY = eINSTANCE.getIAdapterFactory();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.CustomizerImpl
		 * <em>Customizer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.CustomizerImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCustomizer()
		 * @generated
		 */
		EClass CUSTOMIZER = eINSTANCE.getCustomizer();

		/**
		 * The meta object literal for the '<em><b>Rank</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CUSTOMIZER__RANK = eINSTANCE.getCustomizer_Rank();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.StageQualifierImpl <em>Stage
		 * Qualifier</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.StageQualifierImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStageQualifier()
		 * @generated
		 */
		EClass STAGE_QUALIFIER = eINSTANCE.getStageQualifier();

		/**
		 * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STAGE_QUALIFIER__STAGE = eINSTANCE.getStageQualifier_Stage();

		/**
		 * The meta object literal for the '<em><b>Step</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STAGE_QUALIFIER__STEP = eINSTANCE.getStageQualifier_Step();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.StageQualifierToCustomizerMapEntryImpl
		 * <em>Stage Qualifier To Customizer Map Entry</em>}' class. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.StageQualifierToCustomizerMapEntryImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStageQualifierToCustomizerMapEntry()
		 * @generated
		 */
		EClass STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY = eINSTANCE.getStageQualifierToCustomizerMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__KEY = eINSTANCE.getStageQualifierToCustomizerMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STAGE_QUALIFIER_TO_CUSTOMIZER_MAP_ENTRY__VALUE = eINSTANCE
				.getStageQualifierToCustomizerMapEntry_Value();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.ProjectImpl <em>Project</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.ProjectImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getProject()
		 * @generated
		 */
		EClass PROJECT = eINSTANCE.getProject();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROJECT__NAME = eINSTANCE.getProject_Name();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROJECT__PROJECT_NAME = eINSTANCE.getProject_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' attribute list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROJECT__CHILDREN = eINSTANCE.getProject_Children();

		/**
		 * The meta object literal for the '<em><b>Dirty</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROJECT__DIRTY = eINSTANCE.getProject_Dirty();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' container
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROJECT__WORKSPACE = eINSTANCE.getProject_Workspace();

		/**
		 * The meta object literal for the '<em><b>Resource Path</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROJECT__RESOURCE_PATH = eINSTANCE.getProject_ResourcePath();

		/**
		 * The meta object literal for the '<em><b>Need Completion
		 * Notification</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROJECT__NEED_COMPLETION_NOTIFICATION = eINSTANCE.getProject_NeedCompletionNotification();

		/**
		 * The meta object literal for the '<em><b>Completion Notification
		 * Adapter</b></em>' containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROJECT__COMPLETION_NOTIFICATION_ADAPTER = eINSTANCE.getProject_CompletionNotificationAdapter();

		/**
		 * The meta object literal for the '<em><b>Resource Reload Listener</b></em>'
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROJECT__RESOURCE_RELOAD_LISTENER = eINSTANCE.getProject_ResourceReloadListener();

		/**
		 * The meta object literal for the '<em><b>Saving Resource</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROJECT__SAVING_RESOURCE = eINSTANCE.getProject_SavingResource();

		/**
		 * The meta object literal for the '<em><b>Revision</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROJECT__REVISION = eINSTANCE.getProject_Revision();

		/**
		 * The meta object literal for the '<em><b>Revisions</b></em>' map feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROJECT__REVISIONS = eINSTANCE.getProject_Revisions();

		/**
		 * The meta object literal for the '<em><b>Project Version</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROJECT__PROJECT_VERSION = eINSTANCE.getProject_ProjectVersion();

		/**
		 * The meta object literal for the '<em><b>Select Reveal Handler</b></em>'
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROJECT__SELECT_REVEAL_HANDLER = eINSTANCE.getProject_SelectRevealHandler();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.ModelPairImpl <em>Model
		 * Pair</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.ModelPairImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getModelPair()
		 * @generated
		 */
		EClass MODEL_PAIR = eINSTANCE.getModelPair();

		/**
		 * The meta object literal for the '<em><b>Dynamic</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MODEL_PAIR__DYNAMIC = eINSTANCE.getModelPair_Dynamic();

		/**
		 * The meta object literal for the '<em><b>Generated</b></em>' reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MODEL_PAIR__GENERATED = eINSTANCE.getModelPair_Generated();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MODEL_PAIR__PARENT = eINSTANCE.getModelPair_Parent();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.SCMOperatorImpl <em>SCM
		 * Operator</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.SCMOperatorImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSCMOperator()
		 * @generated
		 */
		EClass SCM_OPERATOR = eINSTANCE.getSCMOperator();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCM_OPERATOR__PROJECT_NAME = eINSTANCE.getSCMOperator_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Registry</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCM_OPERATOR__REGISTRY = eINSTANCE.getSCMOperator_Registry();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.SCMRegistryImpl <em>SCM
		 * Registry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.SCMRegistryImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSCMRegistry()
		 * @generated
		 */
		EClass SCM_REGISTRY = eINSTANCE.getSCMRegistry();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emf.ecore.resource.Resource <em>Resource</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emf.ecore.resource.Resource
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.ResourceChangeListenerImpl
		 * <em>Resource Change Listener</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.ResourceChangeListenerImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResourceChangeListener()
		 * @generated
		 */
		EClass RESOURCE_CHANGE_LISTENER = eINSTANCE.getResourceChangeListener();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emf.common.notify.Notification <em>Notification</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emf.common.notify.Notification
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getNotification()
		 * @generated
		 */
		EClass NOTIFICATION = eINSTANCE.getNotification();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.CompletionListenerImpl
		 * <em>Completion Listener</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.CompletionListenerImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCompletionListener()
		 * @generated
		 */
		EClass COMPLETION_LISTENER = eINSTANCE.getCompletionListener();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.jobs.Worker <em>Worker</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.jobs.Worker
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getWorker()
		 * @generated
		 */
		EClass WORKER = eINSTANCE.getWorker();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.SelectRevealHandlerImpl
		 * <em>Select Reveal Handler</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.SelectRevealHandlerImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSelectRevealHandler()
		 * @generated
		 */
		EClass SELECT_REVEAL_HANDLER = eINSTANCE.getSelectRevealHandler();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.ResourceAdapterImpl <em>Resource
		 * Adapter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.ResourceAdapterImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getResourceAdapter()
		 * @generated
		 */
		EClass RESOURCE_ADAPTER = eINSTANCE.getResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__RESOURCE = eINSTANCE.getResourceAdapter_Resource();

		/**
		 * The meta object literal for the '<em><b>Filename</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__FILENAME = eINSTANCE.getResourceAdapter_Filename();

		/**
		 * The meta object literal for the '<em><b>Project</b></em>' container reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__PROJECT = eINSTANCE.getResourceAdapter_Project();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl
		 * <em>Service</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerServiceImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getClassMakerService()
		 * @generated
		 */
		EClass CLASS_MAKER_SERVICE = eINSTANCE.getClassMakerService();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CLASS_MAKER_SERVICE__WORKSPACE = eINSTANCE.getClassMakerService_Workspace();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.BlueprintImpl
		 * <em>Blueprint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.BlueprintImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getBlueprint()
		 * @generated
		 */
		EClass BLUEPRINT = eINSTANCE.getBlueprint();

		/**
		 * The meta object literal for the '<em><b>Dynamic Model</b></em>' reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference BLUEPRINT__DYNAMIC_MODEL = eINSTANCE.getBlueprint_DynamicModel();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' attribute list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BLUEPRINT__DEPENDENCIES = eINSTANCE.getBlueprint_Dependencies();

		/**
		 * The meta object literal for the '<em><b>Completion Listeners</b></em>'
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference BLUEPRINT__COMPLETION_LISTENERS = eINSTANCE.getBlueprint_CompletionListeners();

		/**
		 * The meta object literal for the
		 * '{@link org.enterprisedomain.classmaker.impl.CompletionNotificationAdapterImpl
		 * <em>Completion Notification Adapter</em>}' class. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.impl.CompletionNotificationAdapterImpl
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCompletionNotificationAdapter()
		 * @generated
		 */
		EClass COMPLETION_NOTIFICATION_ADAPTER = eINSTANCE.getCompletionNotificationAdapter();

		/**
		 * The meta object literal for the '<em><b>Error</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPLETION_NOTIFICATION_ADAPTER__ERROR = eINSTANCE.getCompletionNotificationAdapter_Error();

		/**
		 * The meta object literal for the '<em>Locale</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.util.Locale
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getLocale()
		 * @generated
		 */
		EDataType LOCALE = eINSTANCE.getLocale();

		/**
		 * The meta object literal for the '{@link org.enterprisedomain.classmaker.Stage
		 * <em>Stage</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.enterprisedomain.classmaker.Stage
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getStage()
		 * @generated
		 */
		EEnum STAGE = eINSTANCE.getStage();

		/**
		 * The meta object literal for the '<em>Properties</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.util.Properties
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getProperties()
		 * @generated
		 */
		EDataType PROPERTIES = eINSTANCE.getProperties();

		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();

		/**
		 * The meta object literal for the '<em>OS Gi Version</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.osgi.framework.Version
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getOSGiVersion()
		 * @generated
		 */
		EDataType OS_GI_VERSION = eINSTANCE.getOSGiVersion();

		/**
		 * The meta object literal for the '<em>Semaphore</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.util.concurrent.Semaphore
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getSemaphore()
		 * @generated
		 */
		EDataType SEMAPHORE = eINSTANCE.getSemaphore();

		/**
		 * The meta object literal for the '<em>Core Exception</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.CoreException
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getCoreException()
		 * @generated
		 */
		EDataType CORE_EXCEPTION = eINSTANCE.getCoreException();

		/**
		 * The meta object literal for the '<em>Invocation Target Exception</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.lang.reflect.InvocationTargetException
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getInvocationTargetException()
		 * @generated
		 */
		EDataType INVOCATION_TARGET_EXCEPTION = eINSTANCE.getInvocationTargetException();

		/**
		 * The meta object literal for the '<em>IStatus</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.IStatus
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getIStatus()
		 * @generated
		 */
		EDataType ISTATUS = eINSTANCE.getIStatus();

		/**
		 * The meta object literal for the '<em>URI</em>' data type. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emf.common.util.URI
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();

		/**
		 * The meta object literal for the '<em>Exception</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.lang.Exception
		 * @see org.enterprisedomain.classmaker.impl.ClassMakerPackageImpl#getException()
		 * @generated
		 */
		EDataType EXCEPTION = eINSTANCE.getException();

	}

} // ClassMakerPackage
