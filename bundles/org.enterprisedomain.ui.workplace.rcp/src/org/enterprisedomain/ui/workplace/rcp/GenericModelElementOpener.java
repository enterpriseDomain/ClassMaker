package org.enterprisedomain.ui.workplace.rcp;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.emf.ecp.core.ECPProject;
import org.eclipse.emf.ecp.ui.e4.util.EPartServiceHelper;
import org.eclipse.emf.ecp.ui.util.ECPModelElementOpener;
import org.eclipse.emfforms.editor.e4.E4GenericEditor;
import org.eclipse.emfforms.spi.common.report.AbstractReport;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class GenericModelElementOpener implements ECPModelElementOpener {

	private final String partId = "org.eclipse.emf.e4.editor"; //$NON-NLS-1$

	public GenericModelElementOpener() {
	}

	@SuppressWarnings("restriction")
	@Override
	public void openModelElement(Object modelElement, ECPProject ecpProject) {
		final EPartService partService = EPartServiceHelper.getEPartService();
		for (final MPart existingPart : partService.getParts()) {
			if (!partId.equals(existingPart.getElementId())) {
				continue;
			}

			if (existingPart.getContext() == null) {
				continue;
			}

			if (existingPart.getContext().get(E4GenericEditor.INPUT) == modelElement) {
				if (!existingPart.isVisible() || !existingPart.isOnTop()) {
					partService.showPart(existingPart, PartState.ACTIVATE);
				}
				return;
			}
		}

		final MPart part = partService.createPart(partId);
		if (part == null) {
			final BundleContext bundleContext = FrameworkUtil.getBundle(getClass()).getBundleContext();
			final ServiceReference<ReportService> serviceReference = bundleContext
					.getServiceReference(ReportService.class);
			final ReportService reportService = bundleContext.getService(serviceReference);
			reportService
					.report(new AbstractReport("There is no partdescription with the id " + partId + " available!")); //$NON-NLS-1$ //$NON-NLS-2$
			bundleContext.ungetService(serviceReference);
			return;
		}
		partService.showPart(part, PartState.ACTIVATE);
		part.getContext().set(ECPProject.class, ecpProject);
		part.getContext().set(E4GenericEditor.INPUT, modelElement);
	}

}
