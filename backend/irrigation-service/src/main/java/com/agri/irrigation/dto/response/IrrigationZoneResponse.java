package com.agri.irrigation.dto.response;

import java.util.UUID;

import com.agri.irrigation.enums.ZoneStatus;

public record IrrigationZoneResponse(
        UUID id,
        UUID farmId,
        String zoneCode,
        String zoneName,
        ZoneStatus status,
        String pumpId,
        String valveId,
        Double areaAcres,
        Double flowRateLpm) {
}
