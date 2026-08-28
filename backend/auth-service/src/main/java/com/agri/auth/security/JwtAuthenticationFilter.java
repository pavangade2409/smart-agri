package com.agri.auth.security;

import com.agri.auth.jwt.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;


    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService =
                userDetailsService;
    }


    @Override
    protected boolean shouldNotFilter(
            HttpServletRequest request) {

        String path =
                request.getServletPath();


        return path.equals(
                    "/api/auth/register"
                )
                || path.equals(
                    "/api/auth/login"
                )
                || path.equals(
                    "/api/auth/otp/send"
                )
                || path.equals(
                    "/api/auth/otp/verify"
                )
                || path.equals(
                    "/api/auth/users"
                )
                || path.startsWith(
                    "/h2-console/"
                )
                || path.equals(
                    "/actuator/health"
                )
                || path.equals(
                    "/actuator/info"
                );
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader =
                request.getHeader(
                        "Authorization"
                );


        if (authHeader == null
                || !authHeader.startsWith(
                        "Bearer ")) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }


        String token =
                authHeader.substring(7);


        try {

            String username =
                    jwtService.extractUsername(
                            token
                    );


            if (username != null
                    && SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        == null) {


                UserDetails userDetails =
                        userDetailsService
                                .loadUserByUsername(
                                        username
                                );


                if (jwtService.isTokenValid(
                        token,
                        userDetails.getUsername()
                )) {


                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails
                                            .getAuthorities()
                            );


                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(
                                    authentication
                            );
                }
            }

        } catch (Exception e) {

            // Invalid JWT.
            // Continue request.
        }


        filterChain.doFilter(
                request,
                response
        );
    }
}