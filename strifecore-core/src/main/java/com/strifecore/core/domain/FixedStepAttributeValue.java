package com.strifecore.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attribute_fixed_val")
public class FixedStepAttributeValue {

    @Id
    private Integer id;

    private Double value;

    protected FixedStepAttributeValue() {}

    public FixedStepAttributeValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
