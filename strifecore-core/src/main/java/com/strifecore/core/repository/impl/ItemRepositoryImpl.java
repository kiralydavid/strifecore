package com.strifecore.core.repository.impl;

import com.strifecore.core.domain.Item;
import com.strifecore.core.repository.ItemRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer create(Item item) {
        return (Integer)sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public Item read(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Item.class);
        criteria.add(Restrictions.eq("id", id));

        return (Item)criteria.uniqueResult();
    }

}
