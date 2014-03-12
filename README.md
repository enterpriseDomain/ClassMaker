ClasSupplier
===========

ClasSupplier is a Java library for creating the code that is available to invoke in-place, achieving this behavour through the actual generation and loading (synchroniously only, for a while) of native Java code.  


Usage example:  

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
    ClassSupplier service = ... // acquire the ClasSupplier OSGi service
    EPackage nativeEPackage = service.supply(blueprintEPackage); // Provide it to ClasSupplier to generate the EPackage...
    EClass jClass = (EClass) nativeEPackage.getEClassifier(eClass.getName()); // ... that you can use
    EObject jObject = nativeEPackage.getEFactoryInstance().create(jClass);
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
                .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));  
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName()); // * Note this *
  
A more [complete example is here](/org.classupplier.test/src/org/classupplier/test/ClasSupplierTests.java).  

To use the library, import it to the Eclipse plugin's dependencies with satisfying subsequent. E4 DI is supported. 

See also [wiki](https://github.com/kirillzotkin/ClasSupplier/wiki).
