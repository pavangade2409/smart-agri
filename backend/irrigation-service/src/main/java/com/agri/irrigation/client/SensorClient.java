package com.agri.irrigation.client;

import java.util.UUID;

public interface SensorClient {

    SoilMoistureData getLatestSoilMoisture(
            UUID farmId,
            UUID zoneId
    );
}