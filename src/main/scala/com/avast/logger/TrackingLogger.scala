package com.avast.logger

import org.slf4j.{Logger, LoggerFactory}

import scala.language.experimental.macros

final class TrackingLogger private[logger](val underlying: Logger) {

  def info(message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.infoMessage

  def info(message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.infoMessageCause

  def info(message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.infoMessageArgs

}

object TrackingLoggerFactory {
  def getLogger(cl: Class[_]): TrackingLogger = new TrackingLogger(LoggerFactory.getLogger(cl))

  def getLogger(name: String): TrackingLogger = new TrackingLogger(LoggerFactory.getLogger(name))
}
