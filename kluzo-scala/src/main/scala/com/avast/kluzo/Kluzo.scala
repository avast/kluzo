package com.avast.kluzo

import com.avast.continuity.{Continuity, IdentityThreadNamer, ThreadNamer}

/* KEEP THIS IMPLEMENTATION IN SYNC WITH THE JAVA API VERSION */

object Kluzo {

  /** Key of the Kluzo trace ID in the Continuity context. */
  final val ContinuityKey = "traceId"

  /** HTTP header name that is used to transfer Kluzo trace ID over HTTP. */
  final val HttpHeaderName = "Avast-Kluzo-TraceId"

  /** Puts the [[com.avast.kluzo.TraceId]] into the context, names a thread and runs the given block of code
    * if traceId exists. It correctly cleans up everything after the block finishes.
    *
    * @see [[com.avast.continuity.Continuity#withContext]]
    */
  def withTraceId[A](traceId: Option[TraceId])(block: => A)(implicit threadNamer: ThreadNamer = IdentityThreadNamer): A = traceId match {
    case Some(tid) => Continuity.withContext(ContinuityKey -> tid.value)(block)
    case None => block
  }

  def getTraceId: Option[TraceId] = Continuity.getFromContext(ContinuityKey).map(TraceId.apply)

  def setTraceId(traceId: TraceId): Unit = Continuity.putToContext(ContinuityKey, traceId.value)

}
