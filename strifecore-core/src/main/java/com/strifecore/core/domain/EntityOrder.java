package com.strifecore.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "entity_order")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class EntityOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String title;

    protected String comment;

    protected Integer position;

    protected EntityOrder(){}

    public EntityOrder(String title, String comment, Integer position) {
        this.title = title;
        this.comment = comment;
        this.position = position;
    }
}
