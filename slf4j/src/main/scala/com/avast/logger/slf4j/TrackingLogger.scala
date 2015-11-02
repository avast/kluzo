package com.avast.logger.slf4j

import com.avast.logger.RequestId
import org.slf4j.{Logger, LoggerFactory, Marker}

import scala.language.experimental.macros

final class TrackingLogger private[logger](val underlying: Logger) {

  def trace(message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.traceMessage

  def trace(message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.traceMessageCause

  def trace(message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.traceMessageArgs

  def trace(marker: Marker, message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.traceMessageMarker

  def trace(marker: Marker, message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.traceMessageCauseMarker

  def trace(marker: Marker, message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.traceMessageArgsMarker


  def debug(message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.debugMessage

  def debug(message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.debugMessageCause

  def debug(message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.debugMessageArgs

  def debug(marker: Marker, message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.debugMessageMarker

  def debug(marker: Marker, message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.debugMessageCauseMarker

  def debug(marker: Marker, message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.debugMessageArgsMarker


  def info(message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.infoMessage

  def info(message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.infoMessageCause

  def info(message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.infoMessageArgs

  def info(marker: Marker, message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.infoMessageMarker

  def info(marker: Marker, message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.infoMessageCauseMarker

  def info(marker: Marker, message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.infoMessageArgsMarker


  def warn(message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.warnMessage

  def warn(message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.warnMessageCause

  def warn(message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.warnMessageArgs

  def warn(marker: Marker, message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.warnMessageMarker

  def warn(marker: Marker, message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.warnMessageCauseMarker

  def warn(marker: Marker, message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.warnMessageArgsMarker


  def error(message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.errorMessage

  def error(message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.errorMessageCause

  def error(message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.errorMessageArgs

  def error(marker: Marker, message: String)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.errorMessageMarker

  def error(marker: Marker, message: String, cause: Throwable)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.errorMessageCauseMarker

  def error(marker: Marker, message: String, args: AnyRef*)(implicit requestId: RequestId): Unit = macro TrackingLoggerMacros.errorMessageArgsMarker

}

object TrackingLoggerFactory {
  def getLogger(cl: Class[_]): TrackingLogger = new TrackingLogger(LoggerFactory.getLogger(cl))

  def getLogger(name: String): TrackingLogger = new TrackingLogger(LoggerFactory.getLogger(name))
}
