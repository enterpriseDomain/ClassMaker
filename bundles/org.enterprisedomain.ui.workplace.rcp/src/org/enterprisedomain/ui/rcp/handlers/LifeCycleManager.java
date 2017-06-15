package org.enterprisedomain.ui.rcp.handlers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimBar;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.jface.action.IStatusLineManager;
import org.enterprisedomain.ecp.ui.EnterpriseDomainUIProvider;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class LifeCycleManager {

	@PostContextCreate
	public void postContextCreate(final IEventBroker eventBroker) {
		// eventBroker.subscribe(UIEvents.Context.TOPIC_CONTEXT, new
		// EventHandler() {
		// @Override
		// public void handleEvent(Event event) {
		// IEclipseContext context = (IEclipseContext)
		// event.getProperty(UIEvents.Context.CONTEXT);
		// if (context == null)
		// return;
		// IStatusLineManager statusLineManager =
		// context.get(IStatusLineManager.class);
		// if (statusLineManager != null) {
		// EnterpriseDomainUIProvider.setProgessMonitor(statusLineManager.getProgressMonitor());
		// eventBroker.unsubscribe(this);
		// }
		// }
		// });
		// eventBroker.subscribe(UIEvents.TrimmedWindow.TOPIC_TRIMBARS, new
		// EventHandler() {
		// @Override
		// public void handleEvent(Event event) {
		// if ((UIEvents.isCREATE(event) || UIEvents.isSET(event))
		// && event.containsProperty(UIEvents.TrimmedWindow.TRIMBARS)) {
		// MTrimBar trimBar = (MTrimBar)
		// event.getProperty(UIEvents.TrimmedWindow.TRIMBARS);
		// trimBar.setVisible(true);
		// }
		// }
		// });
	}

}
