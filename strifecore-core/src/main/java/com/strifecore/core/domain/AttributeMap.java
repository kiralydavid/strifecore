package com.strifecore.core.domain;

import com.strifecore.core.exception.AttributeNotFoundException;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "attribute_map")
public class AttributeMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(targetEntity = Attribute.class, mappedBy = "map", cascade=CascadeType.ALL)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "name")
    private Map<AttributeName, Attribute> attributes;

    public AttributeMap() {
        attributes = new HashMap<>();
    }

    public void addAttribute(AttributeName type, Attribute attribute) {
        attribute.map = this;
        attribute.name = type;
        attributes.put(type, attribute);
    }

    public Attribute get(AttributeName attributeType) {
        if(!attributes.containsKey(attributeType)) {
            throw new AttributeNotFoundException("Attribute not found: " + attributeType.toString());
        }

        return attributes.get(attributeType);
    }
}
