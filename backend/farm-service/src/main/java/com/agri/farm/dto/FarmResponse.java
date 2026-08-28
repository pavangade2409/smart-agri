package com.agri.farm.dto;

import java.time.LocalDateTime;

public record FarmResponse(

        Long farmId,

        Long farmerId,

        String farmName,

        Double latitude,

        Double longitude,

        Double totalAcres,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}