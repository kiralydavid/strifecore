package com.strifecore.core.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class ScalingAttribute extends Attribute {

    @Column(name = "value")
    private Double baseValue;

    @Column(name = "increase_per_level")
    private Double increasePerLevel;

    @Column(name = "max_level")
    private Integer maxLevel;

    protected ScalingAttribute() {}

    public ScalingAttribute(Double baseValue, Double increasePerLevel, Integer maxLevel) {
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
