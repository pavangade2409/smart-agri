package com.agri.auth.jwt;

import com.agri.auth.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    private final long expiration;


    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration) {

        if (secret == null
                || secret.length() < 32) {

            throw new IllegalArgumentException(
                    "JWT secret must contain at least 32 characters"
            );
        }


        this.secretKey =
                Keys.hmacShaKeyFor(
                        secret.getBytes(
                                StandardCharsets.UTF_8
                        )
                );


        this.expiration = expiration;
    }


    public String generateToken(
            User user) {

        Date issuedAt =
                new Date();


        Date expiry =
                new Date(
                        issuedAt.getTime()
                                + expiration
                );


        return Jwts.builder()
                .subject(user.getEmail())
                .claim(
                        "userId",
                        user.getId()
                )
                .claim(
                        "role",
                        user.getRole().name()
                )
                .issuedAt(issuedAt)
                .expiration(expiry)
                .signWith(secretKey)
                .compact();
    }


    public String extractUsername(
            String token) {

        return extractClaims(token)
                .getSubject();
    }


    public boolean isTokenValid(
            String token,
            String username) {

        Claims claims =
                extractClaims(token);


        return username.equals(
                    claims.getSubject()
                )
                && claims
                    .getExpiration()
                    .after(new Date());
    }


    private Claims extractClaims(
            String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public long getExpiration() {

        return expiration;
    }
}