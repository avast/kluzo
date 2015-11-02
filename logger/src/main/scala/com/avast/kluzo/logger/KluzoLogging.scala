package com.avast.kluzo.logger

trait KluzoLogging {

  protected[this] lazy val logger: KluzoLogger = KluzoLoggerFactory.getLogger(getClass)

}
