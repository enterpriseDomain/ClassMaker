ClasSupplier
===========

ClasSupplier is a Java library for producing the code programmatically.  

Production means generation, building and loading client-defined classes.  


Example:  

    // Fill the modeled dynamic EPackage
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

    // acquire the ClasSupplier OSGi service
    BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass())
                                .getBundleContext();
    ServiceReference<?> serviceReference = bundleContext
                              .getServiceReference(ClasSupplier.class.getName());
    ClasSupplier service = (ClasSupplier) bundleContext
                                 .getService(serviceReference);
    // Provide EPackage to ClasSupplier service to generate the runtime
    EPackage nativeEPackage = service.supply(modelEPackage); 

    EClass jClass = (EClass) nativeEPackage.getEClassifier(eClass.getName());
    EObject jObject = nativeEPackage.getEFactoryInstance().create(jClass); 
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
                .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName());
  
[Full example here](/org.classupplier.test/src/org/classupplier/test/ClasSupplierTests.java).  

To use the library, [download it](https://github.com/kirillzotkin/ClasSupplier/releases) and add to the target platform  satisfying subsequent dependencies (p2 TBD). E4 DI is supported.

See also [wiki](https://github.com/kirillzotkin/ClasSupplier/wiki).
