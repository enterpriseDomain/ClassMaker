ClassSupplier
===========

ClassSupplier is a Java library for making code programmatically.  

It invokes generator, builds and loads Java classes that defined in user-provided EMF model.  


Example:  

    // Model EPackage
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
    // Acquire ClassSupplier service
    BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass())
                                .getBundleContext();
    ServiceReference<?> serviceReference = bundleContext
                              .getServiceReference(ClassSupplier.class);
    ClassSupplier classSupplier = (ClassSupplier) bundleContext
                                 .getService(serviceReference);
    // Combine them
    EPackage nativeEPackage = classSupplier.supply(modelEPackage); 
    // Use the runtime
    EClass jClass = (EClass) nativeEPackage.getEClassifier(eClass.getName());
    EObject jObject = nativeEPackage.getEFactoryInstance().create(jClass); 
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
                .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName());  

Example is [here](/org.classupplier.test/src/org/classupplier/test/ClassSupplierTests.java).  

To make use of ClassSupplier

1.  [Download](https://github.com/kirillzotkin/ClassSupplier/releases) it
2.  Add it to the target platform satisfying subsequent dependencies
3.  Add it to your plug-in's dependencies
4.  Write code similar to the above. E4 DI is supported.

See [wiki](https://github.com/kirillzotkin/ClassSupplier/wiki) also.
