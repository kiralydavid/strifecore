package com.strifecore.core.dao.impl;

import com.strifecore.core.dao.UserDao;
import com.strifecore.core.domain.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User read(Integer id) {
        return (User)sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public Integer create(User user) {
        return (Integer)sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("name", name));

        return (User)criteria.uniqueResult();
    }
}
