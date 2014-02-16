package com.strifecore.core.repository.impl;

import com.strifecore.core.domain.Hero;
import com.strifecore.core.repository.HeroRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HeroRepositoryImpl implements HeroRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public Integer create(Hero hero) {
        return (Integer)sessionFactory.getCurrentSession().save(hero);
    }

    @Transactional
    @Override
    public Hero read(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Hero.class);
        criteria.add(Restrictions.eq("id", id));

        return (Hero)criteria.uniqueResult();
    }

}
