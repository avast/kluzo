package com.avast.kluzo

/** Wraps any Runnable and transfers the value of [[com.avast.kluzo.traceId]]
  * from the current thread to the thread this Runnable runs in.
  */
private class KluzoRunnable(wrapped: Runnable) extends Runnable with KluzoWrapper {

  override def run(): Unit = wrap {
    wrapped.run()
  }

}
