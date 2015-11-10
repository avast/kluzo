package com.avast.kluzo

import com.avast.continuity.Continuity

object Kluzo {

  final val ContinuityKey = "traceId"

  def getTraceId: Option[TraceId] = Continuity.getFromContext(ContinuityKey).map(TraceId.apply)

  def setTraceId(traceId: TraceId): Unit = Continuity.putToContext(ContinuityKey, traceId.value)

}
