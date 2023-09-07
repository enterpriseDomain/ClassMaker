/**
 *
 * $Id$
 */
package org.enterprisedomain.classmaker.validation;

import java.util.Locale;

import org.eclipse.emf.common.util.EMap;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.Models;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.StageQualifier;

/**
 * A sample validator interface for
 * {@link org.enterprisedomain.classmaker.Item}. This doesn't really do
 * anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's
 * code generator can be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ItemValidator {
	boolean validate();

	boolean validateModelName(String value);

	boolean validatePhase(Stage value);

	boolean validateLanguage(String value);

	boolean validateDomainModel(Models value);

	boolean validateCustomizers(EMap<StageQualifier, Customizer> value);

	boolean validateParent(Item value);

	boolean validateLocale(Locale value);

	boolean validateProject(Project value);
}