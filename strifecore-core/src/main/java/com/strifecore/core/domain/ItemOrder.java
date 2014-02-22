package com.strifecore.core.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class ItemOrder extends EntityOrder {

    @OneToMany
    @JoinTable(name = "item_order_element", joinColumns = {
            @JoinColumn(name = "item")
    }, inverseJoinColumns = {
            @JoinColumn(name = "entity_order")
    })
    @OrderColumn(name = "position")
    private List<Item> elements;

    @ManyToOne
    @JoinColumn(name = "guide")
    protected Guide guide;

    protected ItemOrder(){}

    public ItemOrder(String title, String comment, List<Item> elements, Integer position) {
        super(title, comment, position);

        this.elements = elements;

        if(elements.size() == 0) {
            throw new IllegalArgumentException("An item order musth have at least one element!");
        }
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }
}
