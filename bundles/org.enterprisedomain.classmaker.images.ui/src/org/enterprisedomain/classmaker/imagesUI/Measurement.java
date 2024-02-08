/**
 */
package org.enterprisedomain.classmaker.imagesUI;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.swt.graphics.Point;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.imagesUI.Measurement#getFrom <em>From</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.imagesUI.Measurement#getTo <em>To</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classmaker.imagesUI.ImagesUIPackage#getMeasurement()
 * @model
 * @generated
 */
public interface Measurement extends EObject {
	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(Point)
	 * @see org.enterprisedomain.classmaker.imagesUI.ImagesUIPackage#getMeasurement_From()
	 * @model dataType="org.enterprisedomain.classmaker.imagesUI.Point"
	 * @generated
	 */
	Point getFrom();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.imagesUI.Measurement#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(Point value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' attribute.
	 * @see #setTo(Point)
	 * @see org.enterprisedomain.classmaker.imagesUI.ImagesUIPackage#getMeasurement_To()
	 * @model dataType="org.enterprisedomain.classmaker.imagesUI.Point"
	 * @generated
	 */
	Point getTo();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classmaker.imagesUI.Measurement#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(Point value);

} // Measurement
