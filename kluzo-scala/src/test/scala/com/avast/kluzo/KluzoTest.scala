package com.avast.kluzo

import java.util.concurrent.{Executor, Executors}

import com.avast.continuity.{Continuity, ContinuityContextThreadNamer}
import com.avast.kluzo.Kluzo._
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

  private def testTraceId(pool: Executor): Unit = {
    val wrappedPool = Continuity.wrapExecutor(pool)(ContinuityContextThreadNamer.prefix(ContinuityKey))

    val traceId1 = TraceId.generate
    Kluzo.withTraceId(Some(traceId1)) {
      wrappedPool.execute(new Runnable {
        override def run(): Unit = {
          assert(getTraceId === Some(traceId1))
          logger.info("first")
        }
      })
    }

    val traceId2 = TraceId.generate
    Kluzo.withTraceId(Some(traceId2)) {
      pool.execute(new Runnable {
        override def run(): Unit = {
          assert(getTraceId === None)
          logger.info("second")
        }
      })
    }

    val traceId3 = TraceId.generate
    Kluzo.withTraceId(Some(traceId3)) {
      wrappedPool.execute(new Runnable {
        override def run(): Unit = {
          assert(getTraceId === Some(traceId3))
          logger.info("third")
        }
      })
    }
  }

  ignore("performance") {
    // implicit val tn = ContinuityContextThreadNamer.prefix(ContinuityKey)
    val tid = TraceId("test")
    val count = 100000
    var totalTime = 0L
    var workTime = 0L
    for (i <- 0 until count) {
      val start = System.nanoTime
      Kluzo.withTraceId(Some(tid)) {
        val start1 = System.nanoTime
        var sum = 0L
        for (j <- i until 100000) {
          if (j % 2 == 0) {
            sum += j
          } else {
            sum *= j
          }
        }
        workTime += System.nanoTime - start1
      }
      totalTime += System.nanoTime - start
    }

    println(s"workTime: $workTime, average: ${ workTime.toDouble / count }")
    println(s"totalTime: $totalTime, average: ${ totalTime.toDouble / count }")
    println(s"overhead: ${ totalTime - workTime }, average: ${ (totalTime - workTime).toDouble / count }")
  }

}
