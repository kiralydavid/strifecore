package com.strifecore.core.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Calendar;

@Configuration
@ComponentScan(basePackages = {
        "com.strifecore.core.service",
        "com.strifecore.core.security",
        "com.strifecore.core.dao"
})
@Import({
        Migration.class,
        Persistence.class,
        Security.class
})
@EnableTransactionManagement
public class RootContext {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Scope("prototype")
    public Calendar calendar() {
        return Calendar.getInstance();
    }
}
