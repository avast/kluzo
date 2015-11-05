package com.avast

import java.util.UUID

package object kluzo {

  final val MdcKey = "traceId"

  lazy val traceId: ThreadLocal[TraceId] = new ThreadLocal()

  def generateTraceId: TraceId = TraceId(UUID.randomUUID().toString)

}
