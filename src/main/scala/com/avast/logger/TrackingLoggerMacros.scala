package com.avast.logger

import org.slf4j.Logger

import scala.reflect.macros.blackbox

private[logger] object TrackingLoggerMacros {
  type LoggerContext = blackbox.Context {type PrefixType = Logger}

  def infoMessage(c: LoggerContext)(message: c.Expr[String])(requestId: c.Expr[RequestId]) = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val mod = c.Expr( q""" "["+$requestId.value + "] " + $message""")
    q"if ($underlying.isInfoEnabled) $underlying.info($mod)"
  }

  def infoMessageCause(c: LoggerContext)(message: c.Expr[String], cause: c.Expr[Throwable])(requestId: c.Expr[RequestId]) = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val mod = c.Expr( q""" "["+$requestId.value + "] " + $message""")
    q"if ($underlying.isInfoEnabled) $underlying.info($mod, $cause)"
  }

  def infoMessageArgs(c: LoggerContext)(message: c.Expr[String], args: c.Expr[AnyRef]*)(requestId: c.Expr[RequestId]) = {
    import c.universe._
    val underlying = q"${c.prefix}.underlying"
    val mod = c.Expr( q""" "["+$requestId.value + "] " + $message""")
    if (args.length == 2)
      q"if ($underlying.isInfoEnabled) $underlying.info($mod, List(${args(0)}, ${args(1)}): _*)"
    else
      q"if ($underlying.isInfoEnabled) $underlying.info($mod, ..$args)"
  }
}
