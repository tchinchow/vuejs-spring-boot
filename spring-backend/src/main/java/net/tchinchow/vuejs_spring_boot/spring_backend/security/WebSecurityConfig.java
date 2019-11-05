package net.tchinchow.vuejs_spring_boot.spring_backend.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * WebSecurityConfig
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // get allowed CORS origins from application.properties...
    @Value("${application.allowed_cors_urls:#{T(java.util.Collections).emptyList()}}")
    private String allowedCorsUrls[];

    // used by http.cors() in configure()
    @Bean
	CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        // We trust that allowedOrigins is not null because we set it to a default value when it is not provided
        if (allowedCorsUrls.length > 0) {
            configuration.setAllowedOrigins(Arrays.asList(allowedCorsUrls));
            configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        }
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // The code below partially reproduce what is done by the WebSecurityConfigurerAdapter when disableDefaults is not set
        http.csrf().and()
            .addFilter(new WebAsyncManagerIntegrationFilter())
            .exceptionHandling().and()
            .headers().and()
            .sessionManagement().and()
            .securityContext().and()
            .requestCache().and()
            // .anonymous().and()
            // .servletApi().and()
            .apply(new DefaultLoginPageConfigurer<>()).and()
            .logout().and()
            .formLogin().and()
            .httpBasic();

        // The code below is our contribution on top of the above defaults
        http
            .cors().and()
            .authorizeRequests()
            .antMatchers("/webapp", "/webapp/**").permitAll()
            .antMatchers("/api/public/**").permitAll()
            .anyRequest().authenticated();
    }
}