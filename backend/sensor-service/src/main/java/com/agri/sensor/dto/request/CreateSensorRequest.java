package com.agri.sensor.dto.request;

import java.util.UUID;

import com.agri.sensor.enums.SensorType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateSensorRequest {

    @NotBlank
    private String sensorCode;

    @NotNull
    private UUID farmId;

    private UUID zoneId;

    @NotNull
    private SensorType sensorType;

    @NotBlank
    private String unit;

    private String deviceId;

    public CreateSensorRequest() {
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(
            String sensorCode) {

        this.sensorCode = sensorCode;
    }

    public UUID getFarmId() {
        return farmId;
    }

    public void setFarmId(UUID farmId) {
        this.farmId = farmId;
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public void setZoneId(UUID zoneId) {
        this.zoneId = zoneId;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(
            SensorType sensorType) {

        this.sensorType = sensorType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}