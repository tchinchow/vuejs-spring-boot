package net.tchinchow.vuejs_spring_boot.spring_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
        "classpath:/META-INF/resources/",
        "classpath:/resources/",
        "classpath:/static/",
        "classpath:/public/"
    };

    @Autowired
    MyAppConfig config;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        SinglePageAppResourceResolver web1 = config.webapp1ResourceResolver();
        SinglePageAppResourceResolver web2 = config.webapp2ResourceResolver();

        registry.addResourceHandler("/webapp", "/webapp/", "/webapp/**").resourceChain(false).addResolver(web1);
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        
    }
}