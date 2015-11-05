package com.avast.kluzo

import java.util.concurrent.Callable

/** Wraps any Callable and transfers the value of [[com.avast.kluzo.traceId]]
  * from the current thread to the thread this Callable runs in.
  */
private class KluzoCallable[A](wrapped: Callable[A]) extends Callable[A] with KluzoWrapper {

  override def call(): A = wrap {
    wrapped.call()
  }

}
