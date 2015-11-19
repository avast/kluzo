package com.avast.kluzo.javaapi;

import com.avast.continuity.javaapi.Continuity;
import com.avast.kluzo.Kluzo$;

import java.util.Optional;

public final class Kluzo {

    public static final String CONTINUITY_KEY = Kluzo$.MODULE$.ContinuityKey();

    private Kluzo() {
    }

    public static Optional<TraceId> getTraceId() {
        return Continuity.getFromContext(CONTINUITY_KEY).map(TraceId::new);
    }

    public static void setTraceId(TraceId traceId) {
        Continuity.putToContext(CONTINUITY_KEY, traceId.getValue());
    }

}
