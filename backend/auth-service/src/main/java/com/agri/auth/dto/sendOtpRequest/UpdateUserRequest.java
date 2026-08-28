package com.agri.auth.dto.sendOtpRequest;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

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