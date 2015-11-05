package com.avast.kluzo

import java.util.concurrent.{Executor, Executors}

import org.scalatest.FunSuite
import org.slf4j.{LoggerFactory, MDC}

import scala.concurrent.ExecutionContext

class KluzoTest extends FunSuite {

  /** See the log output to check that traceId is present. */
  private val logger = LoggerFactory.getLogger(this.getClass)

  test("traceId") {
    testTraceId(Executors.newSingleThreadExecutor())
    testTraceId(ExecutionContext.global)
  }

  private def testTraceId(pool: Executor) {
    val wrappedPool = Kluzo.wrapExecutor(pool)

    val traceId1 = TraceId.generate
    traceId.set(traceId1)
    MDC.put(MdcKey, traceId1.value)
    wrappedPool.execute(new Runnable {
      override def run(): Unit = {
        assert(traceId.get === traceId1)
        logger.info("first")
      }
    })

    val traceId2 = TraceId.generate
    traceId.set(traceId2)
    // this will probably be visible in log because MDC is usin InheritableThreadLocal
    // and the threads are created in this test so they inherit the value
    MDC.put(MdcKey, traceId2.value)
    pool.execute(new Runnable {
      override def run(): Unit = {
        assert(traceId.get === null)
        logger.info("second")
      }
    })

    val traceId3 = TraceId.generate
    traceId.set(traceId3)
    MDC.put(MdcKey, traceId3.value)
    wrappedPool.execute(new Runnable {
      override def run(): Unit = {
        assert(traceId.get === traceId3)
        logger.info("third")
      }
    })
  }
}
