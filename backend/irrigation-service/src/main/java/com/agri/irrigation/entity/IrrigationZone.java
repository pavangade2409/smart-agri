package com.agri.irrigation.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.agri.irrigation.enums.IrrigationMode;
import com.agri.irrigation.enums.ZoneStatus;

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
@Table(name = "irrigation_zones")
public class IrrigationZone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "farm_id", nullable = false)
    private UUID farmId;

    @Column(name = "zone_code", nullable = false, unique = true)
    private String zoneCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "crop_id")
    private UUID cropId;

    @Column(name = "area_acres", nullable = false)
    private Double areaAcres;

    @Column(name = "flow_rate_lpm")
    private Double flowRateLpm;

    @Column(name = "pump_id")
    private String pumpId;

    @Column(name = "valve_id")
    private String valveId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ZoneStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "irrigation_mode", nullable = false)
    private IrrigationMode irrigationMode;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public IrrigationZone() {
    }

    @PrePersist
    protected void onCreate() {

        OffsetDateTime now = OffsetDateTime.now();

        createdAt = now;
        updatedAt = now;

        if (status == null) {
            status = ZoneStatus.ACTIVE;
        }

        if (irrigationMode == null) {
            irrigationMode = IrrigationMode.MANUAL;
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

    public UUID getFarmId() {
        return farmId;
    }

    public void setFarmId(UUID farmId) {
        this.farmId = farmId;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCropId() {
        return cropId;
    }

    public void setCropId(UUID cropId) {
        this.cropId = cropId;
    }

    public Double getAreaAcres() {
        return areaAcres;
    }

    public void setAreaAcres(Double areaAcres) {
        this.areaAcres = areaAcres;
    }

    public Double getFlowRateLpm() {
        return flowRateLpm;
    }

    public void setFlowRateLpm(Double flowRateLpm) {
        this.flowRateLpm = flowRateLpm;
    }

    public String getPumpId() {
        return pumpId;
    }

    public void setPumpId(String pumpId) {
        this.pumpId = pumpId;
    }

    public String getValveId() {
        return valveId;
    }

    public void setValveId(String valveId) {
        this.valveId = valveId;
    }

    public ZoneStatus getStatus() {
        return status;
    }

    public void setStatus(ZoneStatus status) {
        this.status = status;
    }

    public IrrigationMode getIrrigationMode() {
        return irrigationMode;
    }

    public void setIrrigationMode(
            IrrigationMode irrigationMode) {

        this.irrigationMode = irrigationMode;
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