# Kluzo

> Kluzo as in [inspector Clouseau](https://en.wikipedia.org/wiki/Inspector_Clouseau)

The goal of this library is to add tracing capabilities to our backends. It defines [TraceId](kluzo-scala/src/main/scala/com/avast/kluzo/TraceId.scala)
which needs to be passed throughout the call chain and between backends to connect different calls and actions that were initiated by some 
initial request. Trace ID is alternatively called correlation ID. This library is based upon the [Continuity](https://git.int.avast.com/ff/continuity) 
library.

The idea is that the trace ID should appear in all log messages related to the request, it should be passed between backends
(e.g. via [HTTP headers](kluzo-scala/src/main/scala/com/avast/kluzo/Kluzo.scala)) and hopefully it should also be somehow connected to database or queue calls.
The end result should be an overview of communication spurred by a single request. [Zipkin](http://zipkin.io/) could be used for that.

It has both [Scala](kluzo-scala/src/main/scala/com/avast/kluzo) and [Java](kluzo/src/main/java/com/avast/kluzo/javaapi) APIs.

Most of you won't need to work with this library as it should be integrated into other libraries ([Yap](https://git.int.avast.com/ff/yap), 
[HTTP Clients](https://git.int.avast.com/ff/clients)) for seamless cooperation. The only thing that is required and you might need to do
yourself is to wrap all your executors (`Executor`, `ExecutorService`, `ExecutionContext`, `EventLoopGroup`) in Continuity wrappers.

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

## Logback
This is a recommended format for the [Kluzo](https://git.int.avast.com/ff/kluzo) trace ID: 
```xml
<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-10mdc{traceId}] [%thread] %-35logger{35}: %msg \(%file:%line\)%n%xThrowable{full}</pattern>
```

## Example
```scala
val executor = Continuity.wrapExecutionContext(ExecutionContext.global)(Kluzo.ThreadNamer)

Kluzo.withTraceId(Some(TraceId.generate)) {
  executor.execute(new Runnable {
    override def run(): Unit = {
      println(Kluzo.getTraceId)
      logger.info("logging message")
    }
  })
}
```
