package com.agri.auth.dto.response;

public record ApiResponse<T>(

    boolean success,

    String message,

    T data
) {
}