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
package org.enterprisedomain.classsupplier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Stage</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getStage()
 * @model
 * @generated
 */
public enum Stage implements Enumerator {
	/**
	 * The '<em><b>DEFINED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEFINED_VALUE
	 * @generated
	 * @ordered
	 */
	DEFINED(0, "DEFINED", "DEFINED"),

	/**
	 * The '<em><b>MODELED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MODELED_VALUE
	 * @generated
	 * @ordered
	 */
	MODELED(1, "MODELED", "MODELED"),

	/**
	 * The '<em><b>GENERATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATED_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATED(2, "GENERATED", "GENERATED"),

	/**
	 * The '<em><b>EXPORTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPORTED_VALUE
	 * @generated
	 * @ordered
	 */
	EXPORTED(3, "EXPORTED", "EXPORTED"),

	/**
	 * The '<em><b>INSTALLED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INSTALLED_VALUE
	 * @generated
	 * @ordered
	 */
	INSTALLED(4, "INSTALLED", "INSTALLED"),

	/**
	 * The '<em><b>LOADED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOADED_VALUE
	 * @generated
	 * @ordered
	 */
	LOADED(5, "LOADED", "LOADED");

	/**
	 * The '<em><b>DEFINED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DEFINED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DEFINED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DEFINED_VALUE = 0;

	/**
	 * The '<em><b>MODELED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MODELED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MODELED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MODELED_VALUE = 1;

	/**
	 * The '<em><b>GENERATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GENERATED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GENERATED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GENERATED_VALUE = 2;

	/**
	 * The '<em><b>EXPORTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EXPORTED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXPORTED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int EXPORTED_VALUE = 3;

	/**
	 * The '<em><b>INSTALLED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INSTALLED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INSTALLED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int INSTALLED_VALUE = 4;

	/**
	 * The '<em><b>LOADED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOADED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOADED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOADED_VALUE = 5;

	/**
	 * An array of all the '<em><b>Stage</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Stage[] VALUES_ARRAY = new Stage[] { DEFINED, MODELED, GENERATED, EXPORTED, INSTALLED,
			LOADED, };

	/**
	 * A public read-only list of all the '<em><b>Stage</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Stage> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Stage</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Stage get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Stage result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Stage</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Stage getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Stage result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Stage</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Stage get(int value) {
		switch (value) {
		case DEFINED_VALUE:
			return DEFINED;
		case MODELED_VALUE:
			return MODELED;
		case GENERATED_VALUE:
			return GENERATED;
		case EXPORTED_VALUE:
			return EXPORTED;
		case INSTALLED_VALUE:
			return INSTALLED;
		case LOADED_VALUE:
			return LOADED;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Stage(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
		return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} //Stage
