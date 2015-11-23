package com.avast.kluzo.javaapi;

import com.avast.continuity.ContinuityContextThreadNamer;
import com.avast.continuity.ThreadNamer;
import com.avast.continuity.javaapi.Continuity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class KluzoTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testTraceId() throws InterruptedException {
        ThreadNamer threadNamer = ContinuityContextThreadNamer.prefix("traceId");
        ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService wrappedExecutor = Continuity.wrapExecutorService(executor, threadNamer);

        CountDownLatch latch = new CountDownLatch(3);

        TraceId traceId1 = TraceId.generate();
        Kluzo.withTraceId(traceId1, () -> {
            wrappedExecutor.execute(() -> {
                assertEquals(Kluzo.getTraceId(), Optional.of(traceId1));
                logger.info("first");
            });
            latch.countDown();
            return null;
        });

        TraceId traceId2 = TraceId.generate();
        Kluzo.withTraceId(traceId2, () -> {
            executor.execute(() -> {
                assertEquals(Kluzo.getTraceId(), Optional.empty());
                logger.info("second");
            });
            latch.countDown();
            return null;
        });

        TraceId traceId3 = TraceId.generate();
        Kluzo.withTraceId(traceId3, () -> {
            wrappedExecutor.execute(() -> {
                assertEquals(Kluzo.getTraceId(), Optional.of(traceId3));
                logger.info("third");
            });
            latch.countDown();
            return null;
        });

        latch.await();
        Thread.sleep(500);
    }

}
