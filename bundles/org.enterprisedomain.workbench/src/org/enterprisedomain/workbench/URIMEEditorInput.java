package org.enterprisedomain.workbench;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecp.editor.e3.ECPEditorContext;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;

public class URIMEEditorInput extends URIEditorInput implements IEditorInput {

	private EStructuralFeature problemFeature;
	private ECPEditorContext modelElementContext;

	public URIMEEditorInput(URI uri) {
		super(uri);
	}

	public URIMEEditorInput(URI uri, String name) {
		super(uri, name);
	}

	public URIMEEditorInput(IMemento memento) {
		super(memento);
	}

	/**
	 * Constructor to add a problemFeature.
	 *
	 * @param context        context of the model element
	 * @param problemFeature the problem feature
	 */
	public URIMEEditorInput(URI uri, ECPEditorContext context, EStructuralFeature problemFeature) {
		this(uri, context);
		this.problemFeature = problemFeature;
	}

	/**
	 * Default constructor.
	 *
	 * @param context context of the modelelement
	 */
	public URIMEEditorInput(URI uri, ECPEditorContext context) {
		this(uri);
		modelElementContext = context;
	}

	/**
	 * @return the problemFeature
	 */
	public EStructuralFeature getProblemFeature() {
		return problemFeature;
	}

	/**
	 * @param problemFeature the problemFeature to set
	 */
	public void setProblemFeature(EStructuralFeature problemFeature) {
		this.problemFeature = problemFeature;
	}

	/**
	 * Custom equals() for this class.
	 *
	 * @param obj the compared object.
	 * @return the boolean state. {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof URIMEEditorInput) {
			final URIMEEditorInput other = (URIMEEditorInput) obj;
			if (modelElementContext == other.modelElementContext) {
				return true;
			}
			if (other.modelElementContext == null) {
				return false;
			}
			final boolean ret = modelElementContext.getDomainObject()
					.equals(other.modelElementContext.getDomainObject());
			return ret || super.equals(obj);
		}
		return false;
	}

	@Override
	protected String getBundleSymbolicName() {
		return Activator.getContext().getBundle().getSymbolicName();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class clazz) {

		if (clazz.equals(EObject.class) && modelElementContext != null) {
			return modelElementContext.getDomainObject();
		}
		return super.getAdapter(clazz);
	}

	/**
	 * Returns the {@link ECPEditorContext}.
	 *
	 * @return {@link ECPEditorContext}
	 */
	public ECPEditorContext getModelElementContext() {
		return modelElementContext;
	}

	/**
	 * Disposes the input.
	 */
	public void dispose() {
		modelElementContext.dispose();
		modelElementContext = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		if (modelElementContext == null) {
			return "Error"; //$NON-NLS-1$
		}
		return super.getName() != null ? super.getName() : modelElementContext.getDomainObject().eClass().getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getToolTipText() {
		return getName();
	}
}
