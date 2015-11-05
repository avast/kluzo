package com.avast.kluzo

import org.slf4j.MDC

private trait KluzoWrapper {

  /** Stores the current value of traceId ThreadLocal when this is instantiated. */
  protected val traceIdFromOriginalThread: Option[TraceId] = Option(traceId.get)

  protected def wrap[A](block: => A): A = {
    try {
      traceIdFromOriginalThread.foreach { t =>
        MDC.put(MdcKey, t.value)
        traceId.set(t)
      }

      block
    } finally {
      MDC.remove(MdcKey)
      traceId.remove()
    }
  }

}
