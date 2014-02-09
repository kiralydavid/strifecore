package com.strifecore.core;

import com.strifecore.core.config.RootContext;
import com.strifecore.core.config.TestContext;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RootContext.class,
        TestContext.class
})
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected TestEntityFactory testEntityFactory;

}
