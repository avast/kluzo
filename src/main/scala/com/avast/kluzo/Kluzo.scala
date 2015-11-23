package com.avast.kluzo

import com.avast.continuity.{Continuity, ContinuityContextThreadNamer}

object Kluzo {

  final val ContinuityKey = "traceId"

  implicit private final val ThreadNamer = ContinuityContextThreadNamer.prefix(ContinuityKey)

  def withTraceId[A](traceId: TraceId)
                    (block: => A): A = {
    Continuity.withContext(ContinuityKey -> traceId.value) {
      block
    }
  }

  def getTraceId: Option[TraceId] = Continuity.getFromContext(ContinuityKey).map(TraceId.apply)

  def setTraceId(traceId: TraceId): Unit = Continuity.putToContext(ContinuityKey, traceId.value)

}
