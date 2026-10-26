package com.agri.infrastructure.dto;

import java.time.Instant;

public record ValveResponse(
        Long id,
        Long farmId,
        Long plotId,
        Long deviceId,
        String valveName,
        String zoneName,
        Boolean open,
        Instant lastChangedAt
) {}
