.. ClassMaker documentation master file, created by
   sphinx-quickstart on Mon Apr 15 18:39:58 2019.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to ClassMaker's documentation!
======================================

enterpriseDomain ClassMaker is a library that performs the one several-step action. The first step, receiving as input model object (meta-model of which could be an EMF Ecore) starts code generation driven by that model. Then, next it builds and exports the code, yielded from the first step. And, exported binary are being installed into library client's own runtime, and, finally, loaded, providing generated model to reflective API-owning client.

All of this is done through interacting with `ClassMakerService` class instance.

.. toctree::
   :maxdepth: 2
   :caption: Contents:



Indices and tables
==================

* :ref:`genindex`
* :ref:`modindex`
* :ref:`search`
