package org.k.classmaker;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.ecore.EPackage;
import org.osgi.framework.BundleContext;

public class ClassMaker extends Plugin {

	public static final String PLUGIN_ID = "org.k.classmaker";

	public static final String NATURE_ID = PLUGIN_ID + '.' + "domainNature";

	public static final String MODEL_FOLDER_PREF_KEY = "modelFolder";

	public static final String RESOURCE_EXT_PREF_KEY = "resourceExt";

	private BundleContext context;

	private static ClassMaker instance;

	public static ClassMaker getDefault() {
		return instance;
	}

	private IProgressMonitor progressMonitor;

	public IProgressMonitor monitor() {
		if (progressMonitor == null)
			progressMonitor = new NullProgressMonitor();
		return progressMonitor;
	}

	BundleContext getBundleContext() {
		return context;
	}

	public void setMonitor(IProgressMonitor monitor) {
		this.progressMonitor = monitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		context = bundleContext;
		instance = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		context = null;
		instance = null;
	}

	protected Map<EPackage, Bundle> models = new HashMap<EPackage, Bundle>();

	protected NameLookup names = new NameLookup();

	public NameLookup names() {
		return names;
	}

	public Bundle getBundle(String projectName) {
		String name = names.getName(projectName);
		for (Bundle bundle : models.values()) {
			if (bundle.getName().equals(name))
				return bundle;
		}
		return null;
	}

	/**
	 * 
	 * @param value
	 *            the blueprint for making
	 */
	public void addEPackage(EPackage value) {
		Bundle bundle = ClassMakerFactory.eINSTANCE.createBundle();
		bundle.setDynamicEPackage(value);
		models.put(value, bundle);
		bundle.setNeedRefresh(true);		
	}

	/**
	 * Returns the product of making. Please call {@code}
	 * {@link org.classmaker.domainlogic.ClassMaker#addPackage(EPackage))}
	 * before calling this method.
	 * 
	 * @return the result of making
	 */
	public EPackage getEPackage(String nsURI) {
		for (EPackage ePackage : models.keySet())
			if (ePackage.getNsURI().equals(nsURI))
				return models.get(ePackage).getEPackage();
		return null;
	}

}
