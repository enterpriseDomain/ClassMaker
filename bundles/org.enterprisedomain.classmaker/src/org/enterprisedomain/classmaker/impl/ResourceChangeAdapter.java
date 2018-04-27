package org.enterprisedomain.classmaker.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.enterprisedomain.classmaker.Contribution;
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
				&& notification.getEventType() == Notification.SET && notification.getNotifier() instanceof Resource
				&& project != null && project instanceof Contribution && !project.getChildren().isEmpty()
				&& project.getChildren().get(0) instanceof Resource && ((Resource) project.getChildren().get(0))
						.getURI().equals(((Resource) notification.getNotifier()).getURI())
				&& !notification.getNewBooleanValue())
			try {
				project.notifyResourceChanged(notification);
			} catch (Exception e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			}
	}

}