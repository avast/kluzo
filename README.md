# Kluzo

* Developer: kolena@avast.com, janecek@avast.com

> Kluzo as in [inspector Clouseau](https://en.wikipedia.org/wiki/Inspector_Clouseau)

**This repository is work/idea in progress.**

### Gradle
```groovy
dependencies {
    compile 'com.avast.kluzo:kluzo-logger:1.0-SNAPSHOT'
}
```

### Maven
```xml
<dependency>
    <groupId>com.avast.kluzo</groupId>
    <artifactId>kluzo-logger</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

The goal of this library is to add tracing capabilities to our backends. It defines [TraceId](api/src/main/java/com/avast/kluzo/TraceId.java)
which needs to be passed throughout the call chain and between backends to connect different calls and actions that were initiated by some 
initial request. Trace ID is alternatively called correlation ID.

The idea is that the trace ID should appear in all log messages related to the request, it should be passed between backends
(e.g. via HTTP headers) and hopefully it should also be somehow connected to database or queue calls. The end result should be 
 an overview of communication spurred by a single request. [Zipkin](http://zipkin.io/) could be used for that.

The main problem of the trace ID is that it needs to be always present in your scope however as a developer you don't want to manually pass it around.
This can quite easily be solved in **Scala** by using implicits. Nice solution in Java is currently pending.

## logger 

Implementation of a logging layer that **implicitly** takes a [TraceId](api/src/main/java/com/avast/kluzo/TraceId.java) and writes it into 
each log message. The implementation is based on [Scala macros](http://docs.scala-lang.org/overviews/macros/usecases.html) - 
it's basically slightly customized [scala-logging library](https://github.com/typesafehub/scala-logging).
