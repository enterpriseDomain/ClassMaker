enterpriseDomain ClassMaker Documentation
=========================================

enterpriseDomain ClassMaker is a library that performs one several-step action. The first step, receiving as input model object (meta-model of which could be an EMF Ecore) starts code generation driven by that model. Then, next it builds and exports the code, yielded from the first step. And, exported binary are being installed into library client's own runtime, and, finally, loaded, providing generated model to reflective API-owning client.

All this is done through interacting with `ClassMakerService` class instance.