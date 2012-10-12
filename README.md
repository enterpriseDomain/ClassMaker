Class-Maker
===========

This project is all about creating classes in runtime.  
It generates Java, Ecore metamodel-based classes, with operations capable to be invoked.


You can use it like this:  

    // First, fill the source dynamic EPackage (or, here you may get the existing one)
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
    
    // Then, provide it to ClasssMaker and wait,
    ClassMaker.getInstance().addEPackage(sourceEPackage);

    // get the real produced EPackage
    EPackage nativeEPackage = ClassMaker.getInstance().getEPackage(
    	            sourceEPackage.getNsURI());

    // ...and use it!
    EClass theClass = (EClass) nativeEPackage.getEClassifier(eClass.getName());
    EObject theObject = nativeEPackage.getEFactoryInstance().create(theClass);
    assertEquals(eClass.getName(), theObject.getClass().getSimpleName());

    int pages = 500;
    EStructuralFeature pagesAttr = theClass.getEStructuralFeature(attr
                .getName());
    theObject.eSet(pagesAttr, pages);
    assertEquals(pages, theObject.eGet(pagesAttr));  
  
Example with the operation call is [here](/kirillzotkin/Class-Maker/blob/master/org.k.classmaker.test/src/org/k/classmaker/test/Tests.java).


All contributors are welcome!
