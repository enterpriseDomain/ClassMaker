Class-Maker
===========

This project is about creating classes in runtime. 
It is currently Java and, to be more specific, Ecore.

You can use it like that:
  EPackage sourceEPackage = EcoreFactory.eINSTANCE.createEPackage();
  // ... fill dynamic EPackage
  
  ClassMaker.getDefault().addEPackage(sourceEPackage);
	
  EPackage resultingEPackage = ClassMaker.getDefault().getEPackage(
				sourceEPackage.getNsURI());
  // ... use the real EPackage
That's all!

Contributors are welcome!