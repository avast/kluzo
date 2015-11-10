package com.avast.kluzo

import java.util.concurrent.{Executor, Executors}

import com.avast.continuity.Continuity
import org.scalatest.FunSuite
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

class KluzoTest extends FunSuite {

  /** See the log output to check that traceId is present. */
  private val logger = LoggerFactory.getLogger(this.getClass)

  test("traceId") {
    testTraceId(Executors.newSingleThreadExecutor)
    testTraceId(ExecutionContext.global)
  }

  private def testTraceId(pool: Executor) {
    val wrappedPool = Continuity.wrapExecutor(pool)

    val traceId1 = TraceId.generate
    setTraceId(traceId1)
    wrappedPool.execute(new Runnable {
      override def run(): Unit = {
        assert(getTraceId === Some(traceId1))
        logger.info("first")
      }
    })

    val traceId2 = TraceId.generate
    setTraceId(traceId2)
    pool.execute(new Runnable {
      override def run(): Unit = {
        assert(getTraceId === None)
        logger.info("second")
      }
    })

    val traceId3 = TraceId.generate
    setTraceId(traceId3)
    wrappedPool.execute(new Runnable {
      override def run(): Unit = {
        assert(getTraceId === Some(traceId3))
        logger.info("third")
      }
    })
  }
}
