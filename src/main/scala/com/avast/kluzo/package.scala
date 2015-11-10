package com.avast

import com.avast.continuity.Continuity

package object kluzo {

  final val ContinuityKey = "traceId"

  def getTraceId: Option[TraceId] = Continuity.getFromContext(ContinuityKey).map(TraceId.apply)

  def setTraceId(traceId: TraceId): Unit = Continuity.putToContext(ContinuityKey, traceId.value)

}
