package com.strifecore.core.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class ScalingAttribute extends Attribute {

    @Column(name = "value")
    private Double value;

    @Column(name = "increase_per_level")
    private Double increasePerLevel;

    @Column(name = "max_level")
    private Integer maxLevel;

    protected ScalingAttribute() {}

    public ScalingAttribute(Double value, Double increasePerLevel, Integer maxLevel) {
        this.increasePerLevel = increasePerLevel;
        this.maxLevel = maxLevel;
        this.value = value;
    }

    @Override
    public Double getValueOnLevel(Integer level) {
        if( level < 1 || level > maxLevel ) {
            throw new IllegalArgumentException();
        }
        return value + level * increasePerLevel;
    }

    @Override
    public Integer getType() {
        return 2;
    }

    public Double getValue() {
        return value;
    }

    public Double getIncreasePerLevel() {
        return increasePerLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }
}
