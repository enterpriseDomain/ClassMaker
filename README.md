ClassSupplier
===========

ClassSupplier is a Java library allowing to make code programmatically.  

It generates, builds and loads the Java classes that conform the dynamic EMF model provided.  


Example:  

    // Model EPackage
    EPackage modelEPackage = EcoreFactory.eINSTANCE.createEPackage();
    modelEPackage.setName("library");
    modelEPackage.setNsPrefix("library");
    modelEPackage.setNsURI("http://library/1.0");
    final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Book");
    final EAttribute eAttr = EcoreFactory.eINSTANCE.createEAttribute();
    eAttr.setName("pages");
    eAttr.setEType(EcorePackage.Literals.EINT);
    eClass.getEStructuralFeatures().add(eAttr);
    modelEPackage.getEClassifiers().add(eClass);
    // Acquire ClassSupplier service
    BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass())
                                .getBundleContext();
    ServiceReference<?> serviceReference = bundleContext
                              .getServiceReference(ClassSupplier.class);
    ClassSupplier classSupplier = (ClassSupplier) bundleContext
                                 .getService(serviceReference);
    // Combine them
    Artifact artifact = classSupplier.getWorkspace().createArtifact(modelEPackage);
    artifact.eAdapters().add(new SupplyNotifier() {

            @Override
            protected void supplyCompleted(EPackage ePackage) {
                EPackage nativeEPackage = ePackage); 
                // Use the runtime
                EClass jClass = (EClass) ePackage.getEClassifier(eClass.getName());
                EObject jObject = ePackage.getEFactoryInstance().create(jClass); 
                int pages = 500;
                EStructuralFeature jAttr = jClass.getEStructuralFeature(eAttr
                    .getName());
                jObject.eSet(jAttr, pages);
                assertEquals(pages, jObject.eGet(jAttr));
                assertEquals(eClass.getName(), jObject.getClass().getSimpleName());  
            }

        });
    artifact.produce(new NullProgressMonitor());	
    
[Here](/org.classupplier.test/src/org/classupplier/test/ClassSupplierTests.java) is an example.  

Clone this repo ```https://github.com/kirillzotkin/ClassSupplier.git```.

See [wiki](https://github.com/kirillzotkin/ClassSupplier/wiki) for further details.
