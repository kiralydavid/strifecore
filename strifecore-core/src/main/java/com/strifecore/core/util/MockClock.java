package com.strifecore.core.util;

public class MockClock implements Clock {

    private Long millis;

    @Override
    public Long getTimeInMillis() {
        return millis;
    }

    public void setTime(Long millis) {
        this.millis = millis;
    }
}
