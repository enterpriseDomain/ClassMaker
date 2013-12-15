ClassSupplier
============

ClassSupplier is a Java library for creating the code that is immediately available to call. The code is created according to EMF model-based blueprint and is loaded by OSGi.  


Example:  

    // Provide the model dynamic EPackage to ClassSupplier
    EPackage blueprintEPackage = EcoreFactory.eINSTANCE.createEPackage();
    ...
    blueprintEPackage.setNsURI("http://library/1.0");
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Book");
    EAttribute eAttr = EcoreFactory.eINSTANCE.createEAttribute();
    eAttr.setName("pages");
    eAttr.setEType(EcorePackage.Literals.EINT);
    eClass.getEStructuralFeatures().add(eAttr);
    blueprintEPackage.getEClassifiers().add(eClass);
    ClassSupplier service = ... // acquire the ClassSupplier OSGi service
    EPackage nativeEPackage = service.supply(blueprintEPackage, progressMonitor); // Create the EPackage
    EClass jClass = (EClass) nativeEPackage.getEClassifier(eClass.getName()); // ... that you can use
    EObject jObject = nativeEPackage.getEFactoryInstance().create(jClass);
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName()); // * Note this *
    
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
                .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));  
  
A more [complete example](/org.classsupplier.test/src/org/classsupplier/test/ClassSupplierTests.java).  
To run it see the project [wiki](https://github.com/kirillzotkin/ClassSupplier/wiki).  
