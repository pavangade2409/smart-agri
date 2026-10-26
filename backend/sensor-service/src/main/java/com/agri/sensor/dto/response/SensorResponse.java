package com.agri.sensor.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agri.sensor.enums.SensorStatus;
import com.agri.sensor.enums.SensorType;

public class SensorResponse {

    private UUID id;
    private String sensorCode;
    private UUID farmId;
    private UUID zoneId;
    private SensorType sensorType;
    private String unit;
    private String deviceId;
    private SensorStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public SensorResponse() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public SensorStatus getStatus() {
        return status;
    }

    public void setStatus(
            SensorStatus status) {

        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            OffsetDateTime createdAt) {

        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(
            OffsetDateTime updatedAt) {

        this.updatedAt = updatedAt;
    }
}