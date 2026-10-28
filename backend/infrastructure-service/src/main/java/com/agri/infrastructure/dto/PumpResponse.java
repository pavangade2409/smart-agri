package com.agri.infrastructure.dto;

import com.agri.infrastructure.enums.PumpStatus;
import java.time.Instant;

public record PumpResponse(
        Long id,
        Long farmId,
        Long deviceId,
        String pumpName,
        Double powerKw,
        Double flowRateLitersPerMinute,
        PumpStatus status,
        Instant lastStartedAt,
        Instant lastStoppedAt
) {}
