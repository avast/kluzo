package com.avast.kluzo

import scala.concurrent.forkjoin.ThreadLocalRandom

case class TraceId(value: String) extends AnyVal

object TraceId {

  final val Length: Int = 10

  final val AllowedChars: Array[Char] = (('a' to 'z') ++ ('0' to '9')).toArray

  private val range = 0 until Length

  def generate: TraceId = {
    val rnd = ThreadLocalRandom.current

    val value = range
                .map(_ => rnd.nextInt(AllowedChars.length))
                .map(AllowedChars)
                .mkString

    TraceId(value)
  }

}
