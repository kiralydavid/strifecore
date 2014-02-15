package com.strifecore.core.domain;

import java.util.List;

public class ItemOrder extends EntityOrder<Item> {
    public ItemOrder(String title, String comment, List<Item> elements) {
        super(title, comment, elements);

        if(elements.size() == 0) {
            throw new IllegalArgumentException("An item order musth have at least one element!");
        }
    }
}
