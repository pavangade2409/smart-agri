package com.agri.sensor.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agri.sensor.enums.ReadingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "sensor_readings")
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            name = "sensor_id",
            nullable = false
    )
    private UUID sensorId;

    @Column(
            name = "reading_value",
            nullable = false
    )
    private Double value;

    @Column(
            name = "unit",
            nullable = false,
            length = 20
    )
    private String unit;

    @Column(
            name = "measured_at",
            nullable = false
    )
    private OffsetDateTime measuredAt;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false,
            length = 30
    )
    private ReadingStatus status;

    @Column(
            name = "created_at",
            nullable = false
    )
    private OffsetDateTime createdAt;

    public SensorReading() {
    }

    @PrePersist
    protected void onCreate() {

        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }

        if (status == null) {
            status = ReadingStatus.VALID;
        }
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
