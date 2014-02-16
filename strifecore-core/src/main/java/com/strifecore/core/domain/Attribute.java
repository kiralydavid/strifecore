package com.strifecore.core.domain;

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

    @Enumerated(EnumType.STRING)
    public AttributeName name;

    @ManyToOne
    @JoinColumn(name = "map")
    public AttributeMap map;
}
