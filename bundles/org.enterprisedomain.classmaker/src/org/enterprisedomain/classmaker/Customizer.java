/**
 * Copyright 2012-2016 Kyrill Zotkin
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

import java.util.Comparator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Customizer</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Allows to customize the process. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.enterprisedomain.classmaker.Customizer#getRank
 * <em>Rank</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getCustomizer()
 * @model
 * @generated
 */
public interface Customizer extends EObject {

	public static class CustomizerComparator implements Comparator<Customizer> {

		@Override
		public int compare(Customizer o1, Customizer o2) {
			return o1.getRank() - o2.getRank();
		}

	}

	/**
	 * Returns the value of the '<em><b>Rank</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rank</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Rank</em>' attribute.
	 * @see #setRank(int)
	 * @see org.enterprisedomain.classmaker.ClassMakerPackage#getCustomizer_Rank()
	 * @model
	 * @generated
	 */
	int getRank();

	/**
	 * Sets the value of the
	 * '{@link org.enterprisedomain.classmaker.Customizer#getRank <em>Rank</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Rank</em>' attribute.
	 * @see #getRank()
	 * @generated
	 */
	void setRank(int value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Provides customization in the process. <!-- end-model-doc -->
	 * 
	 * @model argsMany="true"
	 * @generated
	 */
	Object customize(EList<Object> args);

} // Customizer
