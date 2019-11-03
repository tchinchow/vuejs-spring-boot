package net.tchinchow.vuejs_spring_boot.spring_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyAppConfig
 */
@Configuration
public class MyAppConfig {

    @Value("${vuejs.staticDir1:webapp1}")
    private String webapp1;

    @Value("${vuejs.staticDir2:webapp2}")
    private String webapp2;

    @Bean
    public SinglePageAppResourceResolver webapp1ResourceResolver() {
        final SinglePageAppResourceResolver tmpVueResolver = new SinglePageAppResourceResolver().setPublicPathLocation(webapp1);
        return tmpVueResolver;
    }

    @Bean
    public SinglePageAppResourceResolver webapp2ResourceResolver() {
        final SinglePageAppResourceResolver tmpVueResolver = new SinglePageAppResourceResolver().setPublicPathLocation(webapp2);
        return tmpVueResolver;
    }

}