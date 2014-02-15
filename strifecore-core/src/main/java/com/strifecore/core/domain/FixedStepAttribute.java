package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public class FixedStepAttribute implements Attribute {

    private List<Double> values;

    protected FixedStepAttribute() {
        values = new LinkedList<>();
    }

    public FixedStepAttribute addValue(Double value) {
        values.add(value);

        return this;
    }

    @Override
    public Double getValueOnLevel(Integer level) {
        if( level < 1 || level > values.size() ) {
            throw new IllegalArgumentException();
        }
        return values.get(level - 1);
    }
}
