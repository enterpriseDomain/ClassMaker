package org.enterprisedomain.classmaker.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

class ResourceChangeAdapter extends EContentAdapter {

	/**
	 * 
	 */
	private final Project project;

	/**
	 * @param project
	 */
	public ResourceChangeAdapter(Project project) {
		this.project = project;
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__IS_MODIFIED
				&& notification.getEventType() == Notification.SET)
			if (notification.getNotifier() instanceof Resource && !notification.getNewBooleanValue())
				try {
					project.notifyResourceChanged(notification);
				} catch (Exception e) {
					ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
				}
	}

}