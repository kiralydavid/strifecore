package com.strifecore.core.domain;

public class StaticAttribute implements Attribute {

    private Double value;

    public StaticAttribute(Double value) {
        this.value = value;
    }

    @Override
    public Double getValueOnLevel(Integer level) {
        return value;
    }
}
