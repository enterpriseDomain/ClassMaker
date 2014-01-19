ClassSupplier
===========

ClassSupplier is a Java library for creating the code that is immediately available to invoke. That is created according to a blueprint model of EMF metamodel and is loaded in runtime by the OSGi framework.  


Example:  

    // Populate the model dynamic EPackage
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
    EPackage nativeEPackage = service.supply(blueprintEPackage, progressMonitor); // Provide it to ClassSupplier to create the EPackage...
    EClass jClass = (EClass) nativeEPackage.getEClassifier(eClass.getName()); // ... that you can use
    EObject jObject = nativeEPackage.getEFactoryInstance().create(jClass);
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
                .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));  
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName()); // * Note this *
  
A more [complete example is here](/org.classsupplier.test/src/org/classsupplier/test/ClassSupplierTests.java).  
To use the lib, import it to the Eclipse plugin's dependencies with satisfying subsequent.
