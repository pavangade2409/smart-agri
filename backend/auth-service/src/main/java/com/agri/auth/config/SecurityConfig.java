package com.agri.auth.config;

import com.agri.auth.security.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

            // ==========================================
            // CSRF
            // ==========================================

            .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                    "/h2-console/**"
                )
            )

            // ==========================================
            // H2 Console iframe
            // ==========================================

            .headers(headers -> headers
                .frameOptions(frame ->
                    frame.sameOrigin()
                )
            )

            // ==========================================
            // JWT Stateless
            // ==========================================

            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            // ==========================================
            // Authorization
            // ==========================================

            .authorizeHttpRequests(auth -> auth

                // H2 Console
                .requestMatchers(
                    "/h2-console/**"
                ).permitAll()

                // Register
                .requestMatchers(
                    "/api/auth/register"
                ).permitAll()

                // Login
                .requestMatchers(
                    "/api/auth/login"
                ).permitAll()

                // OTP
                .requestMatchers(
                    "/api/auth/otp/**"
                ).permitAll()

                // Users - development testing
                .requestMatchers(
                    "/api/auth/users",
                    "/api/auth/users/**"
                ).permitAll()

                // Actuator
                .requestMatchers(
                    "/actuator/health",
                    "/actuator/info"
                ).permitAll()

                // Everything else
                .anyRequest()
                .authenticated()
            )

            // ==========================================
            // JWT Filter
            // ==========================================

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }


    // ==============================================
    // Password Encoder
    // ==============================================

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    // ==============================================
    // Authentication Manager
    // ==============================================

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration
                .getAuthenticationManager();
    }
}