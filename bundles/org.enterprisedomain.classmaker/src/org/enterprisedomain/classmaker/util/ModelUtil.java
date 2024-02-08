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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.enterprisedomain.classmaker.Item;

public class ModelUtil {

	/***
	 * 
	 * @param first       the first EObject
	 * @param second      the second EObject
	 * @param conjunction true if exact matching is required, false if at least one
	 *                    of features are equal
	 * @return whether the first and second are the same EPackages
	 */
	public static boolean eObjectsAreEqual(EObject first, EObject second, boolean conjunction) {
		if (first == null || second == null)
			return false;
		else if (first instanceof EPackage && second instanceof EPackage) {
			EPackage firstEPackage = (EPackage) first;
			EPackage secondEPackage = (EPackage) second;
			return conjunction
					? (firstEPackage.eIsSet(EcorePackage.Literals.EPACKAGE__NS_URI)
							&& secondEPackage.eIsSet(EcorePackage.Literals.EPACKAGE__NS_URI)
									? firstEPackage.getNsURI().equals(secondEPackage.getNsURI())
											? EcoreUtil.equals(firstEPackage, secondEPackage)
											: false
									: false)
							&& (firstEPackage.eIsSet(EcorePackage.Literals.ENAMED_ELEMENT__NAME)
									&& secondEPackage.eIsSet(EcorePackage.Literals.ENAMED_ELEMENT__NAME)
											? firstEPackage.getName().equals(secondEPackage.getName())
													? EcoreUtil.equals(firstEPackage, secondEPackage)
													: false
											: false)
					: (firstEPackage.eIsSet(EcorePackage.Literals.EPACKAGE__NS_URI)
							&& secondEPackage.eIsSet(EcorePackage.Literals.EPACKAGE__NS_URI)
									? firstEPackage.getNsURI().equals(secondEPackage.getNsURI()) ? true
											: EcoreUtil.equals(firstEPackage, secondEPackage)
									: false)
							|| (firstEPackage.eIsSet(EcorePackage.Literals.ENAMED_ELEMENT__NAME)
									&& secondEPackage.eIsSet(EcorePackage.Literals.ENAMED_ELEMENT__NAME)
											? firstEPackage.getName().equals(secondEPackage.getName()) ? true
													: EcoreUtil.equals(firstEPackage, secondEPackage)
											: false);
		} else if (first.getClass().isAssignableFrom(Item.class) || second.getClass().isAssignableFrom(Item.class))
			return conjunction
					? first.getClass().isAssignableFrom(Item.class)
							&& second.getClass().isAssignableFrom(first.getClass())
					: (first.getClass().isAssignableFrom(Item.class)
							|| second.getClass().isAssignableFrom(first.getClass()))
							|| (second.getClass().isAssignableFrom(Item.class)
									|| first.getClass().isAssignableFrom(second.getClass()));
		return EcoreUtil.equals(first, second);

	}

	public static boolean eObjectsAreEqual(EObject first, EList<EObject> second, boolean conjunction) {
		boolean result = second.contains(first);
		if (result)
			return result;
		for (EObject secondEObject : second)
			if (conjunction)
				result &= eObjectsAreEqual(first, secondEObject, conjunction);
			else
				result |= eObjectsAreEqual(first, secondEObject, conjunction);
		return result;
	}

	public static boolean eObjectsAreEqual(EList<EObject> first, EList<EObject> second, boolean conjunction) {
		if (first.size() != second.size())
			return false;
		boolean result = first.equals(second);
		if (result)
			return result;
		for (EObject firstEObject : first)
			if (conjunction)
				result &= eObjectsAreEqual(firstEObject, second, conjunction);
			else
				result |= eObjectsAreEqual(firstEObject, second, conjunction);
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
