package com.agri.irrigation.decision;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.agri.irrigation.client.SensorClient;
import com.agri.irrigation.client.SoilMoistureData;
import com.agri.irrigation.enums.DecisionStatus;

@Component
public class IrrigationDecisionEngineImpl
        implements IrrigationDecisionEngine {

    private final SensorClient sensorClient;

    public IrrigationDecisionEngineImpl(
            SensorClient sensorClient) {

        this.sensorClient = sensorClient;
    }

    @Override
    public IrrigationDecision evaluate(
            UUID zoneId) {

        /*
         * This is the first basic decision layer.
         *
         * Later we will add:
         *
         * Crop requirement
         * Weather
         * Rain forecast
         * ET0
         * Soil type
         * Farm configuration
         */

        // Temporary example.
        // Real farmId should come from the zone.

        SoilMoistureData sensorData =
                sensorClient.getLatestSoilMoisture(
                        null,
                        zoneId
                );

        if (sensorData == null
                || sensorData.getMoisturePercentage()
                == null) {

            return new IrrigationDecision(
                    DecisionStatus.SENSOR_BLOCKED,
                    "Soil moisture data unavailable",
                    null
            );
        }

        double moisture =
                sensorData.getMoisturePercentage();

        if (moisture < 40.0) {

            return new IrrigationDecision(
                    DecisionStatus.IRRIGATION_REQUIRED,
                    "Soil moisture is below threshold",
                    30
            );
        }

        return new IrrigationDecision(
                DecisionStatus.IRRIGATION_NOT_REQUIRED,
                "Soil moisture is sufficient",
                0
        );
    }
}