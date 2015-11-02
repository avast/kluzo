package com.avast.kluzo.logger

import org.slf4j.LoggerFactory

object KluzoLoggerFactory {

  def getLogger(cl: Class[_]): KluzoLogger = new KluzoLogger(LoggerFactory.getLogger(cl))

  def getLogger(name: String): KluzoLogger = new KluzoLogger(LoggerFactory.getLogger(name))

}
