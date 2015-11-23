package com.avast.kluzo.javaapi;

import com.avast.continuity.javaapi.Continuity;
import com.avast.kluzo.Kluzo$;
import scala.runtime.AbstractFunction0;

import java.util.Optional;
import java.util.concurrent.Callable;

public final class Kluzo {

    public static final String CONTINUITY_KEY = Kluzo$.MODULE$.ContinuityKey();

    private Kluzo() {
    }

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
