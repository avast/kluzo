package com.avast.logger.slf4j

trait TrackingLogging {
  protected lazy val logger = TrackingLoggerFactory.getLogger(getClass)
}
