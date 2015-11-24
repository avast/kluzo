package com.avast.kluzo.javaapi;

import com.avast.continuity.ThreadNamer;
import com.avast.continuity.javaapi.Continuity;
import com.avast.kluzo.Kluzo$;
import scala.runtime.AbstractFunction0;

import java.util.Optional;
import java.util.concurrent.Callable;

/* KEEP THIS IMPLEMENTATION IN SYNC WITH THE SCALA API VERSION */

public final class Kluzo {

    /**
     * Key of the Kluzo trace ID in the Continuity context.
     */
    public static final String CONTINUITY_KEY = Kluzo$.MODULE$.ContinuityKey();

    /**
     * HTTP header name that is used to transfer Kluzo trace ID over HTTP.
     */
    public static final String HTTP_HEADER_NAME = Kluzo$.MODULE$.HttpHeaderName();

    /**
     * Default {@link com.avast.continuity.ThreadNamer} that puts Kluzo trace ID into thread name.
     */
    public static final ThreadNamer THREAD_NAMER = Kluzo$.MODULE$.ThreadNamer();

    private Kluzo() {
    }

    /**
     * Puts the {@link TraceId} into the context, names a thread and runs the given block of code.
     * It correctly cleans up everything after the block finishes.
     *
     * @see com.avast.continuity.javaapi.Continuity#withContext(Callable)
     */
    public static <T> T withTraceId(TraceId traceId, final Callable<T> block) throws RuntimeException {
        return Kluzo$.MODULE$.withTraceId(traceId.getValue(), new AbstractFunction0<T>() {
            @Override
            public T apply() {
                try {
                    return block.call();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static Optional<TraceId> getTraceId() {
        return Continuity.getFromContext(CONTINUITY_KEY).map(TraceId::new);
    }

    public static void setTraceId(TraceId traceId) {
        Continuity.putToContext(CONTINUITY_KEY, traceId.getValue());
    }

}
