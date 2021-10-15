ClassMaker ![Java CI & CD with Maven](https://github.com/enterpriseDomain/ClassMaker/workflows/Java%20CI%20&%20CD%20with%20Maven/badge.svg) [![Code Triagers Badge](https://www.codetriage.com/kyrillzotkin/classmaker/badges/users.svg)](https://www.codetriage.com/kyrillzotkin/classmaker)
===========

ClassMaker is an Eclipse plug-in that allows to create classes programmatically, providing them to client bundle.  

ClassMaker, automatically, generates the source code from the provided model, compiles and releases binary, then installs it into its own runtime, and loads classes, making available to client's code, through model reflective API.  

Here is how you can use it:  

    // Design a blueprint model - dynamic EMF EPackage
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("library");
    ePackage.setNsPrefix("library");
    ePackage.setNsURI("http://library/1.0");
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Book");
    EAttribute eAttr = EcoreFactory.eINSTANCE.createEAttribute();
    eAttr.setName("pages");
    eAttr.setEType(EcorePackage.Literals.EINT);
    eClass.getEStructuralFeatures().add(eAttr);
    ePackage.getEClassifiers().add(eClass);

    // Acquire ClassMaker's OSGi service
    BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass())
                                .getBundleContext();
    ServiceReference<?> serviceReference = bundleContext
                                .getServiceReference(ClassMakerService.class);
    ClassMakerService classMaker = (ClassMakerService) bundleContext
                                .getService(serviceReference);

    // Produce result by combining them
    EPackage jPackage = (EPackage) classMaker.make(ePackage, progressMonitor);
    
    ...
    // Use the generated model at runtime
    EClass jClass = (EClass) jPackage.getEClassifier(eClass.getName());
    EObject jObject = jPackage.getEFactoryInstance().create(jClass); 
    int pages = 500;
    EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
        .getName());
    jObject.eSet(jAttr, pages);
    assertEquals(pages, jObject.eGet(jAttr));
    assertEquals(eClass.getName(), jObject.getClass().getSimpleName());  
        
There is more [code](/tests/org.enterprisedomain.classmaker.tests/src/org/enterprisedomain/classmaker/tests/TestEnterpriseDomain.java), where you can specify a method body and call a method or create a meta-model of any meta-level.

Download
---------
 
[Download latest release](https://github.com/enterpriseDomain/ClassMaker/releases/latest).

Feedback
---------
If you have anything to complain or suggest, please feel free to file a [bug](https://github.com/enterpriseDomain/ClassMaker/issues) at [GitHub](https://help.github.com/articles/creating-an-issue/). 
