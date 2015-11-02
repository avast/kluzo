package com.avast.kluzo.logger

import com.avast.kluzo.TraceId
import org.slf4j.{Logger, LoggerFactory, Marker}

import scala.language.experimental.macros

final class KluzoLogger private[logger](val underlying: Logger) {

  def trace(message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.traceMessage

  def trace(message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.traceMessageCause

  def trace(message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.traceMessageArgs

  def trace(marker: Marker, message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.traceMessageMarker

  def trace(marker: Marker, message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.traceMessageCauseMarker

  def trace(marker: Marker, message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.traceMessageArgsMarker


  def debug(message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.debugMessage

  def debug(message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.debugMessageCause

  def debug(message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.debugMessageArgs

  def debug(marker: Marker, message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.debugMessageMarker

  def debug(marker: Marker, message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.debugMessageCauseMarker

  def debug(marker: Marker, message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.debugMessageArgsMarker


  def info(message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.infoMessage

  def info(message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.infoMessageCause

  def info(message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.infoMessageArgs

  def info(marker: Marker, message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.infoMessageMarker

  def info(marker: Marker, message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.infoMessageCauseMarker

  def info(marker: Marker, message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.infoMessageArgsMarker


  def warn(message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.warnMessage

  def warn(message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.warnMessageCause

  def warn(message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.warnMessageArgs

  def warn(marker: Marker, message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.warnMessageMarker

  def warn(marker: Marker, message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.warnMessageCauseMarker

  def warn(marker: Marker, message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.warnMessageArgsMarker


  def error(message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.errorMessage

  def error(message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.errorMessageCause

  def error(message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.errorMessageArgs

  def error(marker: Marker, message: String)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.errorMessageMarker

  def error(marker: Marker, message: String, cause: Throwable)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.errorMessageCauseMarker

  def error(marker: Marker, message: String, args: AnyRef*)(implicit traceId: TraceId): Unit = macro KluzoLoggerMacros.errorMessageArgsMarker

}

object KluzoLoggerFactory {
  def getLogger(cl: Class[_]): KluzoLogger = new KluzoLogger(LoggerFactory.getLogger(cl))

  def getLogger(name: String): KluzoLogger = new KluzoLogger(LoggerFactory.getLogger(name))
}
