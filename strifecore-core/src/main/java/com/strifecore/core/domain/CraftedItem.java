package com.strifecore.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@DiscriminatorValue("craftedItem")
public class CraftedItem extends Item {

    @ManyToOne
    @JoinColumn(name = "crafter")
    private User crafter;

    protected CraftedItem() {
    }

    public CraftedItem(Component component, List<Component> subcomponents, User crafter) {
        super(component, subcomponents);

        this.crafter = crafter;
    }
}
