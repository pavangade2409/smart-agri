package com.agri.auth.dto.response;

public record LoginResponse(

    String accessToken,

    String tokenType,

    long expiresIn,

    Long userId,

    String email,

    String role
) {
}