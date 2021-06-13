package org.enterprisedomain.ecp.ui;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jgit.api.CheckoutCommand.Stage;
import org.eclipse.swt.graphics.Image;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.Item;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class StageDecorator implements ILabelDecorator {

	private ListenerList<ILabelProviderListener> listeners = new ListenerList<ILabelProviderListener>();

	public StageDecorator() {
		ClassMakerPlugin.getClassMaker().getWorkspace().eAdapters().add(new EContentAdapter() {

			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				if (notification.getFeatureID(Item.class) == ClassMakerPackage.ITEM__PHASE)
					fireLabelProviderChanged(
							new LabelProviderChangedEvent(StageDecorator.this, notification.getNotifier()));
			}

		});
	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public synchronized void addListener(ILabelProviderListener listener) {
		listeners.add(listener);
	}

	@Override
	public synchronized void removeListener(ILabelProviderListener listener) {
		listeners.remove(listener);
	}

	protected void fireLabelProviderChanged(LabelProviderChangedEvent event) {
		for (ILabelProviderListener l : listeners)
			l.labelProviderChanged(event);
	}

	@Override
	public Image decorateImage(Image image, Object element) {
		return null;
	}

	@Override
	public String decorateText(String text, Object element) {
		if (element instanceof Item) {
			Item item = ((Item) element);
			return text + " [" + item.getPhase().getName().toLowerCase() + "]";
		}
		return text;
	}

}
