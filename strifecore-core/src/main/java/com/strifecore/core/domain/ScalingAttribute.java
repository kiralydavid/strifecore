package com.strifecore.core.domain;

public class ScalingAttribute implements Attribute {

    private Double baseValue;
    private Double increasePerLevel;
    private Integer maxLevel;

    protected ScalingAttribute(Double baseValue, Double increasePerLevel, Integer maxLevel) {
        this.increasePerLevel = increasePerLevel;
        this.maxLevel = maxLevel;
        this.baseValue = baseValue;
    }

    @Override
    public Double getValueOnLevel(Integer level) {
        if( level < 1 || level > maxLevel ) {
            throw new IllegalArgumentException();
        }
        return baseValue + level * increasePerLevel;
    }
}
