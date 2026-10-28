package com.agri.infrastructure.dto;

import java.time.Instant;

public record TankResponse(
        Long id,
        Long farmId,
        Long deviceId,
        String tankName,
        Double capacityLiters,
        Double currentLevelLiters,
        Double minimumLevelLiters,
        Instant lastUpdated
) {}
