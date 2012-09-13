Class-Maker
===========

This project is about creating classes in runtime. 
Currently it generates Java and, more specific, Ecore.

You can use it like that:  

    EPackage sourceEPackage = EcoreFactory.eINSTANCE.createEPackage();    
    // ... fill dynamic EPackage
      
    ClassMaker.getDefault().addEPackage(sourceEPackage);
	
    EPackage resultingEPackage = ClassMaker.getDefault().getEPackage(
    				sourceEPackage.getNsURI());    
    // ... use real EPackage  
  
That's all for now!

Contributors are welcome!