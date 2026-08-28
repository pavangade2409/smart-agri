package com.agri.farm.dto;

import jakarta.validation.constraints.*;

public record FarmRequest(

        @NotNull(message = "farmerId is required")
        Long farmerId,

        @NotBlank(message = "farmName is required")
        @Size(
                max = 150,
                message = "farmName cannot exceed 150 characters"
        )
        String farmName,

        @NotNull(message = "latitude is required")
        @DecimalMin(
                value = "-90.0",
                message = "Invalid latitude"
        )
        @DecimalMax(
                value = "90.0",
                message = "Invalid latitude"
        )
        Double latitude,

        @NotNull(message = "longitude is required")
        @DecimalMin(
                value = "-180.0",
                message = "Invalid longitude"
        )
        @DecimalMax(
                value = "180.0",
                message = "Invalid longitude"
        )
        Double longitude,

        @NotNull(message = "totalAcres is required")
        @DecimalMin(
                value = "0.01",
                message = "totalAcres must be greater than zero"
        )
        Double totalAcres

) {
}