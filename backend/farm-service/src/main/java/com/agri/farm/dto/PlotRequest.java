package com.agri.farm.dto;

import jakarta.validation.constraints.*;

public record PlotRequest(

        @NotNull(message = "farmId is required")
        Long farmId,

        @NotBlank(message = "plotName is required")
        @Size(
                max = 150,
                message = "plotName cannot exceed 150 characters"
        )
        String plotName,

        @NotNull(message = "acres is required")
        @DecimalMin(
                value = "0.01",
                message = "acres must be greater than zero"
        )
        Double acres

) {
}