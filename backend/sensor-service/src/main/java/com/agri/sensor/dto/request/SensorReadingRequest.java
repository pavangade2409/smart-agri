package com.agri.sensor.dto.request;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class SensorReadingRequest {

    @NotNull
    private UUID sensorId;

    @NotNull
    private Double value;

    @NotNull
    private OffsetDateTime measuredAt;

    public SensorReadingRequest() {
    }

    public UUID getSensorId() {
        return sensorId;
    }

    public void setSensorId(UUID sensorId) {
        this.sensorId = sensorId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(
            OffsetDateTime measuredAt) {

        this.measuredAt = measuredAt;
    }
}