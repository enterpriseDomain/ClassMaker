Class-Maker
===========

This project is all about creating classes in runtime.  
It generates Java, Ecore metamodel-based classes, with operations capable to be invoked.


You can use it like:  

    // Fill the sample dynamic EPackage (or, get the existing one)
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
    
    // Provide it to ClassMaker,
    ClassMaker.getInstance().addEPackage(sourceEPackage);
    // wait...
    // Now you've got the real produced EPackage...
    EPackage nativeEPackage = ClassMaker.getInstance().getEPackage(
    	            sourceEPackage.getNsURI());

    // ... to use.
    EClass theClass = (EClass) nativeEPackage.getEClassifier(eClass.getName());
    EObject theObject = nativeEPackage.getEFactoryInstance().create(theClass);
    assertEquals(eClass.getName(), theObject.getClass().getSimpleName());

    int pages = 500;
    EStructuralFeature pagesAttr = theClass.getEStructuralFeature(attr
                .getName());
    theObject.eSet(pagesAttr, pages);
    assertEquals(pages, theObject.eGet(pagesAttr));  
  
The operation call sample [is here](/kirillzotkin/Class-Maker/blob/master/org.k.classmaker.test/src/org/k/classmaker/test/Tests.java).


You're welcome to use!
