/**
 *
 * $Id$
 */
package org.enterprisedomain.classmaker.validation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.enterprisedomain.classmaker.CompletionNotificationAdapter;
import org.enterprisedomain.classmaker.ResourceAdapter;
import org.enterprisedomain.classmaker.ResourceChangeListener;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SelectRevealHandler;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.osgi.framework.Version;

/**
 * A sample validator interface for
 * {@link org.enterprisedomain.classmaker.Project}. This doesn't really do
 * anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's
 * code generator can be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ProjectValidator {
	boolean validate();

	boolean validateName(String value);

	boolean validateProjectName(String value);

	boolean validateChildren(EList<Object> value);

	boolean validateDirty(boolean value);

	boolean validateWorkspace(Workspace value);

	boolean validateResourcePath(String value);

	boolean validateNeedCompletionNotification(boolean value);

	boolean validateCompletionNotificationAdapter(CompletionNotificationAdapter value);

	boolean validateResourceReloadListener(ResourceChangeListener value);

	boolean validateSavingResource(boolean value);

	boolean validateRevision(Revision value);

	boolean validateRevisions(EMap<Version, Revision> value);

	boolean validateProjectVersion(Version value);

	boolean validateSelectRevealHandler(SelectRevealHandler value);

	boolean validateVersion(Version value);

	boolean validateState(State value);

	boolean validateModelResourceAdapter(ResourceAdapter value);
}
