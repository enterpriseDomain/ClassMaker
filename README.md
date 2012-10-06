Class-Maker
===========

This project is about creating classes in runtime.  
It generates Java, Ecore metamodel-based classes with operations capable to be invoked.

You can use it like:  

    EPackage sourceEPackage = EcoreFactory.eINSTANCE.createEPackage();    
    // ... fill the dynamic EPackage
    sourceEPackage.setName("library");
	sourceEPackage.setNsPrefix("library");
	sourceEPackage.setNsURI("http://library/1.0");
	EClass eClass = ecoreFactory.createEClass();
	eClass.setName("Book");
	EAttribute attr = ecoreFactory.createEAttribute();
	attr.setName("pages");
	attr.setEType(EcorePackage.Literals.EINT);
	eClass.getEStructuralFeatures().add(attr);
	sourceEPackage.getEClassifiers().add(eClass);
		        
    ClassMaker.getInstance().addEPackage(sourceEPackage);  
    
    EPackage realEPackage = ClassMaker.getInstance().getEPackage(
    				sourceEPackage.getNsURI());
    
    // ... use the real EPackage
    EClass theClass = (EClass) realPackage.getEClassifier(eClass.getName());
	EObject theObject = nativePackage.getEFactoryInstance().create(theClass);
	assertEquals(eClass.getName(), theObject.getClass().getSimpleName());
		
	int pages = 500;
	EStructuralFeature pagesAttr = theClass.getEStructuralFeature(attr
				.getName());
	theObject.eSet(pagesAttr, pages);
	assertEquals(pages, theObject.eGet(pagesAttr));  
  
Example with the operation call is [here](/kirillzotkin/Class-Maker/blob/master/org.k.classmaker.test/src/org/k/classmaker/test/Tests.java).


Contributions are welcome!
