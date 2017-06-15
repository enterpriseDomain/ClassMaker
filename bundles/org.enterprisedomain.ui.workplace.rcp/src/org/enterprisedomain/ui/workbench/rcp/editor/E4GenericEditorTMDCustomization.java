package org.enterprisedomain.ui.workbench.rcp.editor;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecp.common.spi.ChildrenDescriptorCollector;
import org.eclipse.emf.ecp.view.model.common.edit.provider.CustomReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.ecp.view.spi.context.ViewModelService;
import org.eclipse.emf.ecp.view.spi.model.VView;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emfforms.internal.editor.ecore.GroupExpansionViewModelService;
import org.eclipse.emfforms.internal.editor.ecore.helpers.EcoreHelpers;
import org.eclipse.emfforms.internal.swt.treemasterdetail.DefaultTreeMasterDetailCustomization;
import org.eclipse.emfforms.internal.swt.treemasterdetail.defaultprovider.DefaultDeleteActionBuilder;
import org.eclipse.emfforms.spi.swt.treemasterdetail.ContentProviderProvider;
import org.eclipse.emfforms.spi.swt.treemasterdetail.LabelProviderProvider;
import org.eclipse.emfforms.spi.swt.treemasterdetail.MenuProvider;
import org.eclipse.emfforms.spi.swt.treemasterdetail.TreeMasterDetailMenuListener;
import org.eclipse.emfforms.spi.swt.treemasterdetail.ViewModelServiceProvider;
import org.eclipse.emfforms.spi.swt.treemasterdetail.actions.ActionCollector;
import org.eclipse.emfforms.spi.swt.treemasterdetail.actions.MasterDetailAction;
import org.eclipse.emfforms.spi.swt.treemasterdetail.util.CreateElementCallback;
import org.eclipse.emfforms.spi.swt.treemasterdetail.util.RootObject;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Menu;

public class E4GenericEditorTMDCustomization extends DefaultTreeMasterDetailCustomization {

	private E4GenericLabelProviderProvider labelProvider;
	private E4GenericContentProviderProvider contentProvider;
	private ComposedAdapterFactory adapterFactory;

	public E4GenericEditorTMDCustomization(CreateElementCallback createElementCallback) {
		super();
		labelProvider = new E4GenericLabelProviderProvider();
		contentProvider = new E4GenericContentProviderProvider();

		setMenu(new MenuProvider() {
			@Override
			public Menu getMenu(TreeViewer treeViewer, EditingDomain editingDomain) {
				final MenuManager menuMgr = new MenuManager();
				menuMgr.setRemoveAllWhenShown(true);
				final List<MasterDetailAction> masterDetailActions = ActionCollector.newList()
						.addCutAction(editingDomain).addCopyAction(editingDomain).addPasteAction(editingDomain)
						.getList();
				menuMgr.addMenuListener(new TreeMasterDetailMenuListener(new ChildrenDescriptorCollector(), menuMgr,
						treeViewer, editingDomain, masterDetailActions, createElementCallback,
						new DefaultDeleteActionBuilder()));
				final Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
				return menu;
			}
		});

		setViewModelServices(new ViewModelServiceProvider() {

			@Override
			public ViewModelService[] getViewModelServices(VView view, EObject eObject) {
				return new ViewModelService[] { new GroupExpansionViewModelService() };
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emfforms.internal.swt.treemasterdetail.
	 * DefaultTreeViewerCustomization#getLabelProvider()
	 */
	@Override
	public IBaseLabelProvider getLabelProvider() {
		return labelProvider.getLabelProvider();
	}

	protected ComposedAdapterFactory getComposedAdapterFactory() {
		if (adapterFactory == null) {
			adapterFactory = new ComposedAdapterFactory(
					new AdapterFactory[] { new CustomReflectiveItemProviderAdapterFactory(),
							new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE) });
		}
		return adapterFactory;
	}

	@Override
	public void dispose() {
		labelProvider.dispose();
		contentProvider.dispose();
		if (adapterFactory != null) {
			adapterFactory.dispose();
		}
	}

	private final class E4GenericLabelProviderProvider implements LabelProviderProvider {
		private AdapterFactoryLabelProvider provider;

		@Override
		public IBaseLabelProvider getLabelProvider() {
			final ComposedAdapterFactory adapterFactory = getComposedAdapterFactory();
			provider = new AdapterFactoryLabelProvider(adapterFactory);
			return provider;
		}

		@Override
		public void dispose() {
			provider.dispose();
		}
	}

	private final class E4GenericContentProviderProvider implements ContentProviderProvider {
		private AdapterFactoryContentProvider provider;

		@Override
		public IContentProvider getContentProvider() {
			final ComposedAdapterFactory adapterFactory = getComposedAdapterFactory();
			provider = new AdapterFactoryContentProvider(adapterFactory) {

				@Override
				public Object[] getElements(Object object) {
					if (RootObject.class.isInstance(object)) {
						return new Object[] { RootObject.class.cast(object).getRoot() };
					}
					return super.getElements(object);
				}

				@Override
				public boolean hasChildren(Object object) {
					return getChildren(object).length > 0;
				}

				@Override
				public Object[] getChildren(Object object) {
					return EcoreHelpers.filterGenericElements(super.getChildren(object));
				}
			};

			return provider;
		}

		@Override
		public void dispose() {
			provider.dispose();
		}

	}

}
