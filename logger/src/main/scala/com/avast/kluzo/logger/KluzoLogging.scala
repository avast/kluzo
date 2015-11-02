package com.avast.kluzo.logger

trait KluzoLogging {
  protected lazy val logger = KluzoLoggerFactory.getLogger(getClass)
}
