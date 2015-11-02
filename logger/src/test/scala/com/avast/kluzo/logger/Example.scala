package com.avast.kluzo.logger

import com.avast.kluzo.TraceId

object Example extends KluzoLogging {

  private implicit val id = TraceId.create("test")

  def main(args: Array[String]) {
    for (i <- 1 to 3) {
      logger.info("Hello {}", i)
      logger.info("Hi", new Exception(i.toString))
    }
  }

}
