ClassSupplier
===========

ClassSupplier is an Eclipse plug-in allowing to make code programmatically.  

It generates, builds & exports, installs in runtime and loads the entire plug-in with the Java classes that conform the model provided and are reflectively accessible through modeled API.  


Example:  

    // Model EMF EPackage
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("library");
    ePackage.setNsPrefix("library");
    ePackage.setNsURI("http://library/1.0");
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Book");
    EAttribute eAttr = EcoreFactory.eINSTANCE.createEAttribute();
    eAttr.setName("pages");
    eAttr.setEType(EcorePackage.Literals.EINT);
    eClass.getEStructuralFeatures().add(eAttr);
    ePackage.getEClassifiers().add(eClass);
    
    // Acquire ClassSupplier OSGi service
    BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass())
                                .getBundleContext();
    ServiceReference<?> serviceReference = bundleContext
                              .getServiceReference(ClassSupplier.class);
    ClassSupplier classupplier = (ClassSupplier) bundleContext
                              .getService(serviceReference);
    
    // Combine them
    Contribution contribution = classupplier.getWorkspace().createContribution(
                              ePackage);
    Future<? extends EPackage> result = contribution
                              .construct(new NullProgressMonitor());
    EPackage jPackage = result.get();
    
    // Use the runtime
    EClass jClass = (EClass) jPackage.getEClassifier(eClass.getName());
    EObject jObject = jPackage.getEFactoryInstance().create(jClass); 
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
        .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName());  
        
[Here](/org.classupplier.tests/src/org/classupplier/tests/ClassSupplierTest.java) is the example.  

For further details please see the project's [wiki](https://github.com/kirillzotkin/ClassSupplier/wiki).