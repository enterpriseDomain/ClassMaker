ClasSupplier
===========

ClasSupplier is a Java library for producing the code programmatically.  

'Producing' means generation, building and loading client-defined classes.  


Example:  

    // Fill the modeled EPackage
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

    // Acquire the ClasSupplier OSGi service
    BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass())
                                .getBundleContext();
    ServiceReference<?> serviceReference = bundleContext
                              .getServiceReference(ClasSupplier.class.getName());
    ClasSupplier service = (ClasSupplier) bundleContext
                                 .getService(serviceReference);
    // Provide EPackage to ClasSupplier to generate the runtime
    EPackage nativeEPackage = service.supply(modelEPackage); 
    // That you can use 
    EClass jClass = (EClass) nativeEPackage.getEClassifier(eClass.getName());
    EObject jObject = nativeEPackage.getEFactoryInstance().create(jClass); 
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
                .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName());
  
[Full example here](/org.classupplier.test/src/org/classupplier/test/ClasSupplierTests.java).  

To make use of ClasSupplier 

1.  [Download](https://github.com/kirillzotkin/ClasSupplier/releases) it
2.  Add it to the target platform satisfying subsequent dependencies (p2 TBD ;-)
3.  Add it to your plug-in's dependencies
4.  Write code similar to above. E4 DI is supported.

See also [wiki](https://github.com/kirillzotkin/ClasSupplier/wiki).
