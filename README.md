ClassSupplier
===========

ClassSupplier is an Eclipse plug-in allowing you to make code programmatically.  

It generates source code from the model provided, exports binary, installs in runtime, and then loads classes making available for client code to access reflectively through modeled API.  


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
    Future<EList<EPackage>> result = contribution.apply(new NullProgressMonitor());
    EPackage jPackage = result.get().get(0);

    ...
    // Use the runtime
    EClass jClass = (EClass) jPackage.getEClassifier(eClass.getName());
    EObject jObject = jPackage.getEFactoryInstance().create(jClass); 
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
        .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName());  
        
[Here](/org.classupplier.tests/src/org/classupplier/tests/ClassSupplierTest.java) is more examples.  

For further details please see project's [wiki](https://github.com/kirillzotkin/ClassSupplier/wiki).
