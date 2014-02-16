package com.strifecore.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class StaticAttribute extends Attribute {

    private Double value;

    protected StaticAttribute() {}

    public StaticAttribute(Double value) {
        this.value = value;
    }

    @Override
    public Double getValueOnLevel(Integer level) {
        return value;
    }

    @Override
    public Integer getType() {
        return 1;
    }

    public Double getValue() {
        return value;
    }
}
