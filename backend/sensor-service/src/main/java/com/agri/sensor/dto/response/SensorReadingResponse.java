package com.agri.sensor.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agri.sensor.enums.ReadingStatus;

public class SensorReadingResponse {

    private UUID id;
    private UUID sensorId;
    private Double value;
    private String unit;
    private OffsetDateTime measuredAt;
    private ReadingStatus status;

    public SensorReadingResponse() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(
            OffsetDateTime measuredAt) {

        this.measuredAt = measuredAt;
    }

    public ReadingStatus getStatus() {
        return status;
    }

    public void setStatus(
            ReadingStatus status) {

        this.status = status;
    }
}