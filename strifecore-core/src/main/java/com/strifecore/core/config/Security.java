package com.strifecore.core.config;

import com.strifecore.core.security.SaltedBCryptPasswordEncoder;
import com.strifecore.core.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Calendar;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource(value = "file:${configPath}/security.properties")
public class Security extends GlobalMethodSecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${password.salt}")
    private String salt;

    @Value("${token.secret}")
    private String tokenSecret;

    @Value("${token.expiration}")
    private String tokenExpirationTime;

    @Autowired
    private Calendar calendar;

    @Bean
    public SaltedBCryptPasswordEncoder passwordEncoder() {
        return new SaltedBCryptPasswordEncoder(salt);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    protected TokenUtils tokenUtils() {
        return new TokenUtils(tokenSecret, calendar);
    }

    @Bean
    public Long tokenExpirationTime() {
         return Long.valueOf(tokenExpirationTime) * 60 * 1000;
    }

    @Bean
    public String tokenSecret() {
        return tokenSecret;
    }
}
