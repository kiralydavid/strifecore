package com.strifecore.core.repository.impl;

import com.strifecore.core.domain.Guide;
import com.strifecore.core.repository.GuideRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GuideRepositoryImpl implements GuideRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer create(Guide guide) {
        return (Integer)sessionFactory.getCurrentSession().save(guide);
    }

    @Override
    public Guide read(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Guide.class);
        criteria.add(Restrictions.eq("id", id));

        return (Guide)criteria.uniqueResult();
    }

}
