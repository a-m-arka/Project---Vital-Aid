package com.vital_aid_crud_api.Config.CORS;

import org.aspectj.weaver.ast.Or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        
        // Specify the allowed origins
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedOrigin("http://localhost:3001");
        
        // Specify the allowed headers
        corsConfiguration.addAllowedHeader("*");
        
        // Specify the allowed methods
        corsConfiguration.addAllowedMethod("*");
        
        // Allow credentials (cookies, authorization headers, etc.)
        corsConfiguration.setAllowCredentials(true);

        // Register the CORS configuration for all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
