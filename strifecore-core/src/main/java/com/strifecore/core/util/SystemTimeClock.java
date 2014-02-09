package com.strifecore.core.util;

public class SystemTimeClock implements Clock {
    @Override
    public Long getTimeInMillis() {
        return System.currentTimeMillis();
    }
}
