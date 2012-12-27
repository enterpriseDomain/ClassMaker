Class-Manufacturer
============

This library is for creating a classes at runtime.  
It produces generated Java classes based on metamodel defined in Ecore. The methods of these classes can be invoked through Java reflection.


Usage:  

    // Build source dynamic EPackage (or acquire the existing one)
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
    
    // Provide to ClassManufacturer
    ClassMaker.getInstance().addEPackage(sourceEPackage);

    // Get the produced generated EPackage
    EPackage nativeEPackage = ClassMaker.getInstance().getEPackage(
    	            sourceEPackage.getNsURI());

    // Use it
    EClass theClass = (EClass) nativeEPackage.getEClassifier(eClass.getName());
    EObject theObject = nativeEPackage.getEFactoryInstance().create(theClass);
    assertEquals(eClass.getName(), theObject.getClass().getSimpleName());

    int pages = 500;
    EStructuralFeature attrPages = theClass.getEStructuralFeature(attr
                .getName());
    theObject.eSet(attrPages, pages);
    assertEquals(pages, theObject.eGet(attrPages));  
  
[Method invocation sample](/kirillzotkin/Class-Manufacturer/blob/master/org.k.classmaker.test/src/org/k/classmaker/test/ClassManufacturerTests.java)


