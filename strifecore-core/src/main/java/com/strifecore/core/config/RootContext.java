package com.strifecore.core.config;

import com.strifecore.core.util.Clock;
import com.strifecore.core.util.SystemTimeClock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
    public Clock clock() {
        return new SystemTimeClock();
    }
}
