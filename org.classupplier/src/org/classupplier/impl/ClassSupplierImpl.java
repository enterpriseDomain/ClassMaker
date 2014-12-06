/**
 */
package org.classupplier.impl;

import org.classupplier.ClassSupplier;
import org.classupplier.ClassSupplierFactory;
import org.classupplier.Contribution;
import org.classupplier.Phase;
import org.classupplier.Workspace;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Class Supplier</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.classupplier.impl.ClassSupplierImpl#getWorkspace <em>Workspace
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassSupplierImpl implements ClassSupplier {

	/**
	 * The cached value of the '{@link #getWorkspace() <em>Workspace</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWorkspace()
	 * @generated
	 * @ordered
	 */
	protected Workspace workspace;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ClassSupplierImpl() {
		workspace = ClassSupplierFactory.eINSTANCE.createWorkspace();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Workspace getWorkspace() {
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setWorkspace(Workspace newWorkspace) {
		workspace = newWorkspace;
	}

	/**
	 * <!-- begin-user-doc -->With output of progress to System.out<!--
	 * end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void supply(EPackage model) {
		supply(model, new CodeGenUtil.EclipseUtil.StreamProgressMonitor(
				System.out));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void supply(EPackage model, IProgressMonitor monitor) {
		Contribution result = null;
		switch (getWorkspace().contains(model).getValue()) {
		case Phase.MODELED_VALUE:
			result = getWorkspace().getContribution(model);
			if (result == null)
				result = getWorkspace().createContribution(model);
			constructPrototype(model, result, monitor);
			break;
		case Phase.LOADED_VALUE:
			result = getWorkspace().getContribution(model);
			if (result != null)
				if (result.getAppropriateEPackage() != null
						&& result.getAppropriateEPackage().getNsURI()
								.equals(model.getNsURI()))
					break;
				else {
					constructPrototype(model, result, monitor);
					break;
				}
		default:
			result = getWorkspace().createContribution(model);
			result.construct(monitor);
		}
	}

	private void constructPrototype(EPackage model, Contribution contribution,
			IProgressMonitor monitor) {
		contribution.setDynamicEPackage(model);
		contribution.construct(monitor);
	}

} // ClassSupplierImpl
