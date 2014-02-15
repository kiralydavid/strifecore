package com.strifecore.core.domain;

public class ItemOrderBuilder extends EntityOrderBuilder<Item> {
    @Override
    public ItemOrder build() {
        return new ItemOrder(title, comment, elements);
    }
}
