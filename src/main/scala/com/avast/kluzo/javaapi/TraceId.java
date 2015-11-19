package com.avast.kluzo.javaapi;

import com.avast.kluzo.TraceId$;

public final class TraceId {

    private TraceId() {
    }

    public static com.avast.kluzo.TraceId generate() {
        return new com.avast.kluzo.TraceId(TraceId$.MODULE$.generate());
    }

}
