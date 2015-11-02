package com.avast.kluzo.logger

import com.avast.kluzo.TraceId
import org.slf4j.{Logger, Marker}

import scala.reflect.macros.blackbox

private[logger] object KluzoLoggerMacros {

  type LoggerContext = blackbox.Context {type PrefixType = Logger}

  def traceMessage(c: LoggerContext)(message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isTraceEnabled) $underlying.trace($mod)"
  }

  def traceMessageCause(c: LoggerContext)(message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isTraceEnabled) $underlying.trace($mod, $cause)"
  }

  def traceMessageArgs(c: LoggerContext)(message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isTraceEnabled) $underlying.trace($mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isTraceEnabled) $underlying.trace($mod, ..$args)"
    }
  }

  def traceMessageMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isTraceEnabled) $underlying.trace($marker, $mod)"
  }

  def traceMessageCauseMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isTraceEnabled) $underlying.trace($marker, $mod, $cause)"
  }

  def traceMessageArgsMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isTraceEnabled) $underlying.trace($marker, $mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isTraceEnabled) $underlying.trace($marker, $mod, ..$args)"
    }
  }

  def debugMessage(c: LoggerContext)(message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isDebugEnabled) $underlying.debug($mod)"
  }

  def debugMessageCause(c: LoggerContext)(message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isDebugEnabled) $underlying.debug($mod, $cause)"
  }

  def debugMessageArgs(c: LoggerContext)(message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isDebugEnabled) $underlying.debug($mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isDebugEnabled) $underlying.debug($mod, ..$args)"
    }
  }

  def debugMessageMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isDebugEnabled) $underlying.debug($marker, $mod)"
  }

  def debugMessageCauseMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isDebugEnabled) $underlying.debug($marker, $mod, $cause)"
  }

  def debugMessageArgsMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isDebugEnabled) $underlying.debug($marker, $mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isDebugEnabled) $underlying.debug($marker, $mod, ..$args)"
    }
  }

  def infoMessage(c: LoggerContext)(message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isInfoEnabled) $underlying.info($mod)"
  }

  def infoMessageCause(c: LoggerContext)(message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isInfoEnabled) $underlying.info($mod, $cause)"
  }

  def infoMessageArgs(c: LoggerContext)(message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isInfoEnabled) $underlying.info($mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isInfoEnabled) $underlying.info($mod, ..$args)"
    }
  }

  def infoMessageMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isInfoEnabled) $underlying.info($marker, $mod)"
  }

  def infoMessageCauseMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isInfoEnabled) $underlying.info($marker, $mod, $cause)"
  }

  def infoMessageArgsMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isInfoEnabled) $underlying.info($marker, $mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isInfoEnabled) $underlying.info($marker, $mod, ..$args)"
    }
  }

  def warnMessage(c: LoggerContext)(message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isWarnEnabled) $underlying.warn($mod)"
  }

  def warnMessageCause(c: LoggerContext)(message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isWarnEnabled) $underlying.warn($mod, $cause)"
  }

  def warnMessageArgs(c: LoggerContext)(message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isWarnEnabled) $underlying.warn($mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isWarnEnabled) $underlying.warn($mod, ..$args)"
    }
  }

  def warnMessageMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isWarnEnabled) $underlying.warn($marker, $mod)"
  }

  def warnMessageCauseMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isWarnEnabled) $underlying.warn($marker, $mod, $cause)"
  }

  def warnMessageArgsMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isWarnEnabled) $underlying.warn($marker, $mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isWarnEnabled) $underlying.warn($marker, $mod, ..$args)"
    }
  }

  def errorMessage(c: LoggerContext)(message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isErrorEnabled) $underlying.error($mod)"
  }

  def errorMessageCause(c: LoggerContext)(message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isErrorEnabled) $underlying.error($mod, $cause)"
  }

  def errorMessageArgs(c: LoggerContext)(message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isErrorEnabled) $underlying.error($mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isErrorEnabled) $underlying.error($mod, ..$args)"
    }
  }

  def errorMessageMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isErrorEnabled) $underlying.error($marker, $mod)"
  }

  def errorMessageCauseMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], cause: c.Expr[Throwable])(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    q"if ($underlying.isErrorEnabled) $underlying.error($marker, $mod, $cause)"
  }

  def errorMessageArgsMarker(c: LoggerContext)(marker: c.Expr[Marker], message: c.Expr[String], args: c.Expr[Any]*)(traceId: c.Expr[TraceId]) = {
    import c.universe._
    val underlying = q"${ c.prefix }.underlying"
    val mod = c.Expr( q""" "["+$traceId.value + "] " + $message""")
    if (args.length == 2) {
      q"if ($underlying.isErrorEnabled) $underlying.error($marker, $mod, List(${ args(0) }, ${ args(1) }): _*)"
    }
    else {
      q"if ($underlying.isErrorEnabled) $underlying.error($marker, $mod, ..$args)"
    }
  }

}
