Class-Maker
===========

This project is about creating classes in runtime.  
Currently it generates Java and, more specific, Ecore.

You can use it like that:  

    EPackage sourceEPackage = EcoreFactory.eINSTANCE.createEPackage();    
    // ... fill dynamic EPackage        
    ClassMaker.getDefault().addEPackage(sourceEPackage);  
    EPackage resultEPackage = ClassMaker.getDefault().getEPackage(
    				sourceEPackage.getNsURI());    
    // ... use real EPackage  
  
More elaborated sample [here](https://github.com/kirillzotkin/Class-Maker/blob/master/org.k.classmaker.test/src/org/k/classmaker/test/Tests.java).


Contributions are welcome!