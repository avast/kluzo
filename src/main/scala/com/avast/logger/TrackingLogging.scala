package com.avast.logger

trait TrackingLogging {
  protected lazy val logger = TrackingLoggerFactory.getLogger(getClass)
}
