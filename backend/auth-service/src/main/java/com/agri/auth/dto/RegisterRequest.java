package com.agri.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 150)
    String email,

    @NotBlank(message = "Password is required")
    @Size(
        min = 8,
        max = 100,
        message = "Password must contain 8-100 characters"
    )
    String password,

    @NotBlank(message = "First name is required")
    @Size(max = 100)
    String firstName,

    @Size(max = 100)
    String lastName,

    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Phone must contain 10 digits"
    )
    String phone
) {
}