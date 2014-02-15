package com.strifecore.core.domain;

import com.strifecore.core.exception.AttributeNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class AttributeMap< T extends Enum > {
    private Map<T, Attribute> attributes;

    public AttributeMap() {
        attributes = new HashMap<>();
    }

    public void addAttribute(T type, Attribute attribute) {
        attributes.put(type, attribute);
    }

    public Attribute get(T attributeType) {
        if(!attributes.containsKey(attributeType)) {
            throw new AttributeNotFoundException("Attribute not found: " + attributeType.toString());
        }

        return attributes.get(attributeType);
    }
}
