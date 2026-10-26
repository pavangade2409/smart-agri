package com.agri.gateway.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;

import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Value("${app.cors.frontend-origin:}")
    private String frontendOrigin;

    @Bean
    public CorsWebFilter corsWebFilter() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        List<String> allowedOrigins = new ArrayList<>(
                List.of("http://localhost:3000", "http://localhost:5173")
        );
        if (!frontendOrigin.isBlank()) {
            String origin = frontendOrigin.startsWith("http://")
                    || frontendOrigin.startsWith("https://")
                    ? frontendOrigin
                    : "https://" + frontendOrigin;
            allowedOrigins.add(origin);
        }
        configuration.setAllowedOrigins(allowedOrigins);

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return new CorsWebFilter(source);
    }
}