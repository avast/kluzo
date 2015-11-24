package com.avast.kluzo

import com.avast.continuity.{Continuity, ContinuityContextThreadNamer, ThreadNamer}

/* KEEP THIS IMPLEMENTATION IN SYNC WITH THE JAVA API VERSION */

object Kluzo {

  /** Key of the Kluzo trace ID in the Continuity context. */
  final val ContinuityKey = "traceId"

  /** HTTP header name that is used to transfer Kluzo trace ID over HTTP. */
  final val HttpHeaderName = "Avast-Kluzo-TraceId"

  /** Default [[com.avast.continuity.ThreadNamer]] that puts Kluzo trace ID into thread name. */
  implicit final val ThreadNamer: ThreadNamer = ContinuityContextThreadNamer.prefix(ContinuityKey)

  /** Puts the [[com.avast.kluzo.TraceId]] into the context, names a thread and runs the given block of code.
    * It correctly cleans up everything after the block finishes.
    *
    * @see [[com.avast.continuity.Continuity#withContext]]
    */
  def withTraceId[A](traceId: TraceId)
                    (block: => A): A = {
    Continuity.withContext(ContinuityKey -> traceId.value) {
      block
    }
  }

  def getTraceId: Option[TraceId] = Continuity.getFromContext(ContinuityKey).map(TraceId.apply)

  def setTraceId(traceId: TraceId): Unit = Continuity.putToContext(ContinuityKey, traceId.value)

}
