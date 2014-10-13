package org.classupplier;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EContentAdapter;

public abstract class SupplyNotifier extends EContentAdapter {

	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getEventType() == Notification.SET
				&& msg.getFeatureID(Artifact.class) == ClassSupplierPackage.STATE__RUNTIME_EPACKAGE) {
			supplyCompleted((EPackage) msg.getNewValue());
		}
	}

	protected abstract void supplyCompleted(EPackage ePackage);

}