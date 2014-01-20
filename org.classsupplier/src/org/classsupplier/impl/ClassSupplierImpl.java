/**
 */
package org.classsupplier.impl;

import org.classsupplier.Artifact;
import org.classsupplier.ClassSupplier;
import org.classsupplier.ClassSupplierFactory;
import org.classsupplier.ClassSupplierPackage;
import org.classsupplier.Infrastructure;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Class Factory</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.classsupplier.impl.ClassSupplierImpl#getWorkspace <em>
 * Workspace</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ClassSupplierImpl extends EObjectImpl implements ClassSupplier {

	protected Infrastructure workspace = ClassSupplierFactory.eINSTANCE
			.createInfrastructure();

	private IProgressMonitor progressMonitor;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ClassSupplierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassSupplierPackage.Literals.CLASS_SUPPLIER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Infrastructure getWorkspace() {
		if (workspace != null && workspace.eIsProxy()) {
			InternalEObject oldWorkspace = (InternalEObject) workspace;
			workspace = (Infrastructure) eResolveProxy(oldWorkspace);
			if (workspace != oldWorkspace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE,
							oldWorkspace, workspace));
			}
		}
		return workspace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Infrastructure basicGetWorkspace() {
		return workspace;
	}

	/**
	 * <!-- begin-user-doc -->With output of progress to System.out<!--
	 * end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage supply(EPackage model) {
		return supply(model, new CodeGenUtil.EclipseUtil.StreamProgressMonitor(
				System.out));
	}

	public IProgressMonitor monitor() {
		if (progressMonitor == null)
			progressMonitor = new NullProgressMonitor();
		return progressMonitor;
	}

	public void setMonitor(IProgressMonitor monitor) {
		this.progressMonitor = monitor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage supply(EPackage model, IProgressMonitor monitor) {
		Artifact result = null;
		setMonitor(monitor);
		if (getWorkspace().containsArtifact(model)) {
			result = getWorkspace().getArtifact(model);
			result.setPrototypeEPackage(model);
			return result.getEPackage();
		}
		result = ClassSupplierFactory.eINSTANCE.createArtifact();
		result.setName(model.getName());
		getWorkspace().registerArtifact(model, result);
		result.setPrototypeEPackage(model);
		workspace.getContents().add(result);
		return result.getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE:
			if (resolve)
				return getWorkspace();
			return basicGetWorkspace();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ClassSupplierPackage.CLASS_SUPPLIER__WORKSPACE:
			return workspace != null;
		}
		return super.eIsSet(featureID);
	}

} // ClassSupplierImpl
