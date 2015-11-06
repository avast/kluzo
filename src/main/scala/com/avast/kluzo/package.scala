package com.avast

package object kluzo {

  final val MdcKey = "traceId"

  lazy val traceId: ThreadLocal[TraceId] = new ThreadLocal()

}
