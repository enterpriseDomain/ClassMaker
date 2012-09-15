Class-Maker
===========

This project is about creating classes in runtime.  
It generates Java, Ecore metamodel-based classes with operations capable to be invoked.

You can use it like:  

    EPackage sourceEPackage = EcoreFactory.eINSTANCE.createEPackage();    
    // ... fill dynamic EPackage        
    ClassMaker.getDefault().addEPackage(sourceEPackage);  
    EPackage resultEPackage = ClassMaker.getDefault().getEPackage(
    				sourceEPackage.getNsURI());    
    // ... use real EPackage  
  
[Here](https://github.com/kirillzotkin/Class-Maker/blob/master/org.k.classmaker.test/src/org/k/classmaker/test/Tests.java)
is the more elaborated sample.


Contributions are welcome!
