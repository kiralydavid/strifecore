package com.strifecore.core.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@DiscriminatorValue("3")
public class FixedStepAttribute extends Attribute {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attribute", fetch = FetchType.EAGER)
    private List<FixedStepAttributeValue> values;

    public FixedStepAttribute() {
        values = new LinkedList<>();
    }

    public FixedStepAttribute addValue(Double value) {
        FixedStepAttributeValue valueObj = new FixedStepAttributeValue(value);
        valueObj.setAttribute(this);
        values.add(valueObj);

        return this;
    }

    @Override
    public Double getValueOnLevel(Integer level) {
        if( level < 1 || level > values.size() ) {
            throw new IllegalArgumentException();
        }
        return values.get(level - 1).getValue();
    }

    @Override
    public Integer getType() {
        return 3;
    }

    public List<FixedStepAttributeValue> getValues() {
        return values;
    }
}
