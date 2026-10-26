package com.agri.sensor.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agri.sensor.enums.SensorStatus;
import com.agri.sensor.enums.SensorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            name = "sensor_code",
            nullable = false,
            unique = true,
            length = 50
    )
    private String sensorCode;

    @Column(
            name = "farm_id",
            nullable = false
    )
    private UUID farmId;

    @Column(name = "zone_id")
    private UUID zoneId;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "sensor_type",
            nullable = false,
            length = 50
    )
    private SensorType sensorType;

    @Column(
            name = "unit",
            nullable = false,
            length = 20
    )
    private String unit;

    @Column(
            name = "device_id",
            length = 100
    )
    private String deviceId;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false,
            length = 30
    )
    private SensorStatus status;

    @Column(
            name = "created_at",
            nullable = false
    )
    private OffsetDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private OffsetDateTime updatedAt;

    public Sensor() {
    }

    @PrePersist
    protected void onCreate() {

        OffsetDateTime now =
                OffsetDateTime.now();

        createdAt = now;
        updatedAt = now;

        if (status == null) {
            status = SensorStatus.ACTIVE;
        }
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt = OffsetDateTime.now();
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

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}