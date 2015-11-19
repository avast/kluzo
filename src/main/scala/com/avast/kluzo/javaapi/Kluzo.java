package com.avast.kluzo.javaapi;

import com.avast.kluzo.Kluzo$;
import com.avast.kluzo.TraceId;
import scala.Option;

import java.util.Optional;

public final class Kluzo {

    private static final Kluzo$ KLUZO = Kluzo$.MODULE$;

    public static final String CONTINUITY_KEY = KLUZO.ContinuityKey();

    private Kluzo() {
    }

    public static Optional<TraceId> getTraceId() {
        Option<String> traceId = KLUZO.getTraceId();
        if (traceId.isDefined()) {
            return Optional.of(new TraceId(traceId.get()));
        } else {
            return Optional.empty();
        }
    }

    public static void setTraceId(TraceId traceId) {
        KLUZO.setTraceId(traceId.value());
    }

}
