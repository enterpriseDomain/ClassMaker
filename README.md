ClassMaker [![Build Status](https://travis-ci.org/enterpriseDomain/ClassMaker.svg?branch=master)](https://travis-ci.org/enterpriseDomain/ClassMaker) [![Code Triagers Badge](https://www.codetriage.com/kyrillzotkin/classmaker/badges/users.svg)](https://www.codetriage.com/kyrillzotkin/classmaker)
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
    EPackage jPackage = classMaker.make(ePackage, progressMonitor);
    
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
        
There is more [code](/tests/org.enterprisedomain.classmaker.tests/src/org/enterprisedomain/classmaker/tests/TestEnterpriseDomain.java), where you can specify a method body and call a method, customize the generation templates, or create a meta-model of any meta-level.

Download
---------
 
### enterpriseDomain Workbench
To install enterpriseDomain Workbench RCP, please, follow these steps:


* download the [Oomph installer](https://wiki.eclipse.org/Eclipse_Installer)


* add the following line to the installer's eclipse-inst.ini  
`-Doomph.redirection.setups=http://git.eclipse.org/c/oomph/org.eclipse.oomph.git/plain/setups/->https://raw.githubusercontent.com/enterpriseDomain/ClassMaker/master/setups/`  


* start the Oomph installer
 
### ClassMaker
Please, use the [update site](https://dl.bintray.com/enterprisedomain/ClassMaker/) to install ClassMaker. [See](https://github.com/enterpriseDomain/ClassMaker/wiki/HowToRun#usage) how to use it.

Feedback
---------
If you have anything to complain or suggest, please feel free to file a [bug](https://github.com/enterpriseDomain/ClassMaker/issues) at [GitHub](https://help.github.com/articles/creating-an-issue/). 
