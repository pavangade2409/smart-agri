package com.agri.auth.dto.sendOtpRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerifyOtpRequest(

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Phone number must contain 10 digits"
        )
        String phone,

        @NotBlank(message = "OTP is required")
        @Pattern(
                regexp = "^[0-9]{6}$",
                message = "OTP must contain 6 digits"
        )
        String otp

) {
}