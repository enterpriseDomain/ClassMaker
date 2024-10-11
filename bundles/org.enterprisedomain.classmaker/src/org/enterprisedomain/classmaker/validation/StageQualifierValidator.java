/**
 *
 * $Id$
 */
package org.enterprisedomain.classmaker.validation;

import org.enterprisedomain.classmaker.Stage;

/**
 * A sample validator interface for
 * {@link org.enterprisedomain.classmaker.StageQualifier}. This doesn't really
 * do anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's
 * code generator can be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface StageQualifierValidator {
	boolean validate();

	boolean validateStage(Stage value);

	boolean validateStep(String value);
}
