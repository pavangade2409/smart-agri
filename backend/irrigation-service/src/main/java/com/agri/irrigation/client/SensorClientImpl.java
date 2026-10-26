package com.agri.irrigation.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SensorClientImpl implements SensorClient {

    private final RestClient restClient;

    public SensorClientImpl(
            @Value("${services.sensor.base-url}") String baseUrl) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public SoilMoistureData getLatestSoilMoisture(
            UUID farmId,
            UUID zoneId) {

        /*
         * Current Sensor Service API requires sensorId,
         * while Irrigation Service currently provides farmId
         * and zoneId.
         *
         * We therefore need a lookup strategy here before
         * production integration.
         */

        throw new UnsupportedOperationException(
                "Sensor lookup by farmId and zoneId is not implemented yet"
        );
    }
}