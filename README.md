Class-Manufacturer
============

This project is about creating the classes in runtime.  
It generates the Java classes with invocable operations, based on Ecore metamodel.


Usage:  

    // Fill the source dynamic EPackage (or get the existing)
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    EPackage sourceEPackage = ecoreFactory.createEPackage();
    // ... 
    sourceEPackage.setNsURI("http://library/1.0");
    EClass eClass = ecoreFactory.createEClass();
    eClass.setName("Book");
    EAttribute attr = ecoreFactory.createEAttribute();
    attr.setName("pages");
    attr.setEType(EcorePackage.Literals.EINT);
    eClass.getEStructuralFeatures().add(attr);
    sourceEPackage.getEClassifiers().add(eClass);
    
    // Provide it to ClassManufacturer
    ClassMaker.getInstance().addEPackage(sourceEPackage);
    // And you've got real produced EPackage
    EPackage nativeEPackage = ClassMaker.getInstance().getEPackage(
    	            sourceEPackage.getNsURI());

    // Use
    EClass theClass = (EClass) nativeEPackage.getEClassifier(eClass.getName());
    EObject theObject = nativeEPackage.getEFactoryInstance().create(theClass);
    assertEquals(eClass.getName(), theObject.getClass().getSimpleName());

    int pages = 500;
    EStructuralFeature pagesAttr = theClass.getEStructuralFeature(attr
                .getName());
    theObject.eSet(pagesAttr, pages);
    assertEquals(pages, theObject.eGet(pagesAttr));  
  
[Operation invocation example](/kirillzotkin/Class-Manufacturer/blob/master/org.k.classmaker.test/src/org/k/classmaker/test/ClassManufacturerTests.java).


