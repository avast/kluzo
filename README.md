# Kluzo

> Kluzo as in [inspector Clouseau](https://en.wikipedia.org/wiki/Inspector_Clouseau)

**This repository is work/idea in progress.**

### Gradle
```groovy
dependencies {
    compile 'com.avast:kluzo_?:1.0-SNAPSHOT'
}
```

### Maven
```xml
<dependency>
    <groupId>com.avast</groupId>
    <artifactId>kluzo_${build.scala.version}</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

The goal of this library is to add tracing capabilities to our backends. It defines [TraceId](src/main/scala/com/avast/kluzo/TraceId.scala)
which needs to be passed throughout the call chain and between backends to connect different calls and actions that were initiated by some 
initial request. Trace ID is alternatively called correlation ID.

The idea is that the trace ID should appear in all log messages related to the request, it should be passed between backends
(e.g. via HTTP headers) and hopefully it should also be somehow connected to database or queue calls. The end result should be 
 an overview of communication spurred by a single request. [Zipkin](http://zipkin.io/) could be used for that.

