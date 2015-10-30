#Tracking logger

The goal of this library is to provide Scala programmers an easy way, how to track request flow in asynchronous code.
It's done by adding a request ID to start of each logging message. The ID is supposed to be provided via implicit parameter 
(both to the logger and to other methods).  
The implementation is based on Macros (it's basically slightly customized scala-logging library).

##Usage:
See [Test script](test/Test.scala) for more info.  
It's not really easy to run the project in IDE, since macros cannot be used in the same compile run where they are defined. Because of that, 
you have to run the [script](test/Test.scala) as a Scala script and add "Make project" as `Before launch configuration`. That should work :-)

kolena@avast.com