ClassSupplier
===========

ClassSupplier is the Eclipse plug-in allowing to make code programmatically.  

It generates, builds, installs and loads the entire plug-in with the Java classes that conform the reflective model provided and are accessible through that API.  


Example:  

    // Model EMF EPackage
    EPackage modelEPackage = EcoreFactory.eINSTANCE.createEPackage();
    modelEPackage.setName("library");
    modelEPackage.setNsPrefix("library");
    modelEPackage.setNsURI("http://library/1.0");
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Book");
    EAttribute eAttr = EcoreFactory.eINSTANCE.createEAttribute();
    eAttr.setName("pages");
    eAttr.setEType(EcorePackage.Literals.EINT);
    eClass.getEStructuralFeatures().add(eAttr);
    modelEPackage.getEClassifiers().add(eClass);
    
    // Acquire ClassSupplier OSGi service
    BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass())
                                .getBundleContext();
    ServiceReference<?> serviceReference = bundleContext
                              .getServiceReference(ClassSupplier.class);
    ClassSupplier classupplier = (ClassSupplier) bundleContext
                                 .getService(serviceReference);
    
    // Combine them
    Contribution contribution = classupplier.getWorkspace().createContribution(
				modelEPackage);
	 Future<? extends EPackage> result = contribution
				.construct(new NullProgressMonitor()));
	 EPackage ePackage = result.get();
    
    // And use the runtime
    EClass jClass = (EClass) ePackage.getEClassifier(eClass.getName());
    EObject jObject = ePackage.getEFactoryInstance().create(jClass); 
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
        .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName());  
        
[Here](/org.classupplier.test/src/org/classupplier/test/ClassSupplierTests.java) is an example.  

Clone this repo ```https://github.com/kirillzotkin/ClassSupplier.git```.

For further details see [project's wiki](https://github.com/kirillzotkin/ClassSupplier/wiki) .
