package com.avast.kluzo

import java.util.concurrent.ThreadLocalRandom

case class TraceId(value: String) extends AnyVal

object TraceId {

  final val Length: Int = 10

  final val AllowedChars: Array[Char] = (('a' to 'z') ++ ('0' to '9')).toArray

  private val numberOfAllowedChars = AllowedChars.length

  /** This array is just used to map over it to get an array of random characters of the same length. */
  private val helperArray = new Array[Unit](numberOfAllowedChars)

  /** Generates a new [[com.avast.kluzo.TraceId]] that consists of characters from [[com.avast.kluzo.TraceId.AllowedChars]]
    * and is [[com.avast.kluzo.TraceId.Length]] long.
    */
  def generate: TraceId = {
    val rnd = ThreadLocalRandom.current
    def randomChar = (_: Unit) => AllowedChars(rnd.nextInt(numberOfAllowedChars))
    val value = new String(helperArray.map(randomChar))
    TraceId(value)
  }

}
