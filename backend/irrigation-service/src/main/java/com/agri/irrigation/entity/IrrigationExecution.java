package com.agri.irrigation.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agri.irrigation.enums.CommandType;
import com.agri.irrigation.enums.IrrigationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "irrigation_executions")
public class IrrigationExecution {

    @Id
    private UUID id;

    @Column(name = "zone_id", nullable = false)
    private UUID zoneId;

    @Enumerated(EnumType.STRING)
    @Column(name = "command_type", nullable = false)
    private CommandType commandType;

    @Column(name = "started_at")
    private OffsetDateTime startedAt;

    @Column(name = "completed_at")
    private OffsetDateTime completedAt;

    @Column(name = "requested_duration_minutes")
    private Integer requestedDurationMinutes;

    @Column(name = "actual_duration_minutes")
    private Integer actualDurationMinutes;

    @Column(name = "requested_water_liters")
    private Double requestedWaterLiters;

    @Column(name = "actual_water_liters")
    private Double actualWaterLiters;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private IrrigationStatus status;

    @Column(name = "failure_reason")
    private String failureReason;

    @Column(name = "idempotency_key", nullable = false, unique = true)
    private String idempotencyKey;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    public IrrigationExecution() {
    }

    @PrePersist
    protected void onCreate() {

        if (id == null) {
            id = UUID.randomUUID();
        }

        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public void setZoneId(UUID zoneId) {
        this.zoneId = zoneId;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(
            CommandType commandType) {

        this.commandType = commandType;
    }

    public OffsetDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(
            OffsetDateTime startedAt) {

        this.startedAt = startedAt;
    }

    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(
            OffsetDateTime completedAt) {

        this.completedAt = completedAt;
    }

    public Integer getRequestedDurationMinutes() {
        return requestedDurationMinutes;
    }

    public void setRequestedDurationMinutes(
            Integer requestedDurationMinutes) {

        this.requestedDurationMinutes =
                requestedDurationMinutes;
    }

    public Integer getActualDurationMinutes() {
        return actualDurationMinutes;
    }

    public void setActualDurationMinutes(
            Integer actualDurationMinutes) {

        this.actualDurationMinutes =
                actualDurationMinutes;
    }

    public Double getRequestedWaterLiters() {
        return requestedWaterLiters;
    }

    public void setRequestedWaterLiters(
            Double requestedWaterLiters) {

        this.requestedWaterLiters =
                requestedWaterLiters;
    }

    public Double getActualWaterLiters() {
        return actualWaterLiters;
    }

    public void setActualWaterLiters(
            Double actualWaterLiters) {

        this.actualWaterLiters =
                actualWaterLiters;
    }

    public IrrigationStatus getStatus() {
        return status;
    }

    public void setStatus(
            IrrigationStatus status) {

        this.status = status;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(
            String failureReason) {

        this.failureReason = failureReason;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(
            String idempotencyKey) {

        this.idempotencyKey = idempotencyKey;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            OffsetDateTime createdAt) {

        this.createdAt = createdAt;
    }
}