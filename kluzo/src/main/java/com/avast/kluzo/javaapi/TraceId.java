package com.avast.kluzo.javaapi;

import com.avast.kluzo.TraceId$;

import java.util.Objects;

public final class TraceId {

    private final String value;

    public TraceId(String value) {
        this.value = value;
    }

    public static TraceId generate() {
        return new TraceId(TraceId$.MODULE$.generate());
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("TraceId(%s)", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TraceId traceId = (TraceId) o;
        return Objects.equals(value, traceId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
