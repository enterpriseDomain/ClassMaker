/**
 */
package org.classupplier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Phase</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.classupplier.ClassSupplierPackage#getPhase()
 * @model
 * @generated
 */
public enum Phase implements Enumerator {
	/**
	 * The '<em><b>PROCESSING</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROCESSING_VALUE
	 * @generated
	 * @ordered
	 */
	PROCESSING(-1, "PROCESSING", "PROCESSING"), /**
	 * The '<em><b>NEW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEW_VALUE
	 * @generated
	 * @ordered
	 */
	NEW(0, "NEW", "NEW"), /**
	 * The '<em><b>MODELED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MODELED_VALUE
	 * @generated
	 * @ordered
	 */
	MODELED(1, "MODELED", "MODELED"), /**
	 * The '<em><b>GENERATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GENERATED_VALUE
	 * @generated
	 * @ordered
	 */
	GENERATED(2, "GENERATED", ""), /**
	 * The '<em><b>EXPORTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXPORTED_VALUE
	 * @generated
	 * @ordered
	 */
	EXPORTED(3, "EXPORTED", "EXPORTED"), /**
	 * The '<em><b>LOADED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOADED_VALUE
	 * @generated
	 * @ordered
	 */
	LOADED(4, "LOADED", "LOADED");

	/**
	 * The '<em><b>PROCESSING</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROCESSING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROCESSING
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PROCESSING_VALUE = -1;

	/**
	 * The '<em><b>NEW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NEW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEW
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NEW_VALUE = 0;

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
	 * @model literal=""
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
	public static final int LOADED_VALUE = 4;

	/**
	 * An array of all the '<em><b>Phase</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Phase[] VALUES_ARRAY =
		new Phase[] {
			PROCESSING,
			NEW,
			MODELED,
			GENERATED,
			EXPORTED,
			LOADED,
		};

	/**
	 * A public read-only list of all the '<em><b>Phase</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Phase> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Phase</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Phase get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Phase result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Phase</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Phase getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Phase result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Phase</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Phase get(int value) {
		switch (value) {
			case PROCESSING_VALUE: return PROCESSING;
			case NEW_VALUE: return NEW;
			case MODELED_VALUE: return MODELED;
			case GENERATED_VALUE: return GENERATED;
			case EXPORTED_VALUE: return EXPORTED;
			case LOADED_VALUE: return LOADED;
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
	private Phase(int value, String name, String literal) {
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
	
} //Phase
