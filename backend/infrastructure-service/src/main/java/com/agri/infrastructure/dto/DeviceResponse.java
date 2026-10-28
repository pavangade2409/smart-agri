package com.agri.infrastructure.dto;

import com.agri.infrastructure.enums.DeviceStatus;
import com.agri.infrastructure.enums.DeviceType;

public record DeviceResponse(
        Long id,
        Long farmId,
        Long plotId,
        String deviceCode,
        DeviceType deviceType,
        DeviceStatus status,
        String manufacturer,
        String model
) {}
