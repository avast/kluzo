package com.avast.kluzo.javaapi;

import com.avast.continuity.ThreadNamer;
import com.avast.continuity.javaapi.Continuity;
import com.avast.kluzo.Kluzo$;

import java.util.HashMap;
import java.util.Map;
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

    private Kluzo() {
    }

    /**
     * Puts the {@link TraceId} into the context, names a thread and runs the given block of code
     * if traceId exists. It correctly cleans up everything after the block finishes.
     *
     * @see com.avast.continuity.javaapi.Continuity#withContext(Callable)
     */
    public static <T> T withTraceId(Optional<TraceId> traceId, Callable<T> block) throws RuntimeException {
        if (traceId.isPresent()) {
            Map<String, String> ctxValues = new HashMap<>();
            ctxValues.put(CONTINUITY_KEY, traceId.get().getValue());
            return Continuity.withContext(ctxValues, block);
        } else {
            try {
                return block.call();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Puts the {@link TraceId} into the context, names a thread and runs the given block of code
     * if traceId exists. It correctly cleans up everything after the block finishes.
     *
     * @see com.avast.continuity.javaapi.Continuity#withContext(Callable)
     */
    public static <T> T withTraceId(Optional<TraceId> traceId, ThreadNamer threadNamer, final Callable<T> block) throws RuntimeException {
        if (traceId.isPresent()) {
            Map<String, String> ctxValues = new HashMap<>();
            ctxValues.put(CONTINUITY_KEY, traceId.get().getValue());
            return Continuity.withContext(ctxValues, threadNamer, block);
        } else {
            try {
                return block.call();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static Optional<TraceId> getTraceId() {
        return Continuity.getFromContext(CONTINUITY_KEY).map(TraceId::new);
    }

    public static void setTraceId(TraceId traceId) {
        Continuity.putToContext(CONTINUITY_KEY, traceId.getValue());
    }

}
