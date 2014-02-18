package com.strifecore.core.repository;

import com.strifecore.core.domain.Item;

public interface ItemRepository {

    public Integer create(Item item);

    public Item read(Integer id);
}
