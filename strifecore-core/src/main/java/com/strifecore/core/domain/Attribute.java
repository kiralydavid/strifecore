package com.strifecore.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "type")
@Table(name = "attribute")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    public abstract Double getValueOnLevel(Integer level);

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    public AttributeName name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "map")
    public AttributeMap map;

    public AttributeName getName() {
        return name;
    }

    public abstract Integer getType();
}
