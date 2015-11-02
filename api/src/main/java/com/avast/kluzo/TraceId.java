package com.avast.kluzo;

public interface TraceId {
    String value();

    static TraceId create(String value) {
        return () -> value;
    }
}
