package com.strifecore.api.config;

import com.strifecore.api.security.AuthenticationTokenProcessingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan(basePackages = {
    "com.strifecore.api.controller"
})
@Configuration
public class WebContext extends WebMvcConfigurerAdapter{

    @Bean
    public AuthenticationTokenProcessingInterceptor authenticationTokenProcessingInterceptor() {
        return new AuthenticationTokenProcessingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationTokenProcessingInterceptor());
    }
}
