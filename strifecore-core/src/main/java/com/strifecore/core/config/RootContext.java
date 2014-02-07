package com.strifecore.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {
        "com.strifecore.core.service"
})
@Import({
        Migration.class,
        Persistence.class
})
@EnableTransactionManagement
public class RootContext {
}
