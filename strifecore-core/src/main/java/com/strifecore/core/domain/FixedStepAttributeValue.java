package com.strifecore.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "attribute_fixed_val")
public class FixedStepAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double value;

    @ManyToOne
    @JoinColumn(name = "attribute")
    private FixedStepAttribute attribute;

    protected FixedStepAttributeValue() {}

    public FixedStepAttributeValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setAttribute(FixedStepAttribute attribute) {
        this.attribute = attribute;
    }
}
