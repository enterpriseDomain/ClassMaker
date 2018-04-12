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
package org.enterprisedomain.classmaker.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreSwitch;

public class ModelUtil {

	/***
	 * 
	 * @param first
	 *            the first EPackage
	 * @param second
	 *            the second EPackage
	 * @param conjunction
	 *            true if exact matching is required, false if at least one of
	 *            features are equal
	 * @return whether the first and second are the same EPackages
	 */
	public static boolean ePackagesAreEqual(EPackage first, EPackage second, boolean conjunction) {
		if (first == null || second == null)
			return false;
		else
			return conjunction
					? (first.eIsSet(EcorePackage.Literals.EPACKAGE__NS_URI)
							&& second.eIsSet(EcorePackage.Literals.EPACKAGE__NS_URI)
									? first.getNsURI().equals(second.getNsURI())
									: false)
							&& (first.eIsSet(EcorePackage.Literals.ENAMED_ELEMENT__NAME)
									&& second.eIsSet(EcorePackage.Literals.ENAMED_ELEMENT__NAME)
											? first.getName().equals(second.getName()) ? true : first.equals(second)
											: false)
					: (first.eIsSet(EcorePackage.Literals.EPACKAGE__NS_URI)
							&& second.eIsSet(EcorePackage.Literals.EPACKAGE__NS_URI)
									? first.getNsURI().equals(second.getNsURI())
									: false)
							|| (first.eIsSet(EcorePackage.Literals.ENAMED_ELEMENT__NAME)
									&& second.eIsSet(EcorePackage.Literals.ENAMED_ELEMENT__NAME)
											? first.getName().equals(second.getName()) ? true : first.equals(second)
											: false);

	}

	public static boolean ePackagesAreEqual(EPackage first, EList<EPackage> second, boolean conjunction) {
		boolean result = second.contains(first);
		if (result)
			return result;
		for (EPackage secondEPackage : second)
			result |= ePackagesAreEqual(first, secondEPackage, conjunction);
		return result;
	}

	public static boolean ePackagesAreEqual(EList<EPackage> first, EList<EPackage> second, boolean conjunction) {
		if (first.size() != second.size())
			return false;
		boolean result = first.equals(second);
		if (result)
			return result;
		for (EPackage firstEPackage : first)
			result |= ePackagesAreEqual(firstEPackage, second, conjunction);
		return result;
	}

	public static EPackage getEPackage(EObject eObject) {
		EcoreSwitch<EPackage> getSwitch = new EcoreSwitch<EPackage>() {

			@Override
			public EPackage caseEPackage(EPackage object) {
				return object;
			}

			@Override
			public EPackage caseEClassifier(EClassifier object) {
				return object.getEPackage();
			}

			@Override
			public EPackage caseEFactory(EFactory object) {
				return object.getEPackage();
			}

			@Override
			public EPackage caseETypedElement(ETypedElement object) {
				return object.getEType().getEPackage();
			}

			@Override
			public EPackage defaultCase(EObject object) {
				return object.eClass().getEPackage();
			}

		};
		return getSwitch.doSwitch(eObject);

	}

}
