package com.strifecore.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
@DiscriminatorValue("3")
public class FixedStepAttribute extends Attribute {

    @OneToMany
    private List<FixedStepAttributeValue> values;

    protected FixedStepAttribute() {
        values = new LinkedList<>();
    }

    public FixedStepAttribute addValue(Double value) {
        values.add(new FixedStepAttributeValue(value));

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
