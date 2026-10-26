package com.agri.infrastructure.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "water_tanks")
public class WaterTank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long farmId;
    private Long deviceId;
    private String tankName;
    private Double capacityLiters;
    private Double currentLevelLiters;
    private Double minimumLevelLiters;
    private Instant lastUpdated;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFarmId() { return farmId; }
    public void setFarmId(Long farmId) { this.farmId = farmId; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public String getTankName() { return tankName; }
    public void setTankName(String tankName) { this.tankName = tankName; }

    public Double getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(Double value) { this.capacityLiters = value; }

    public Double getCurrentLevelLiters() { return currentLevelLiters; }
    public void setCurrentLevelLiters(Double value) { this.currentLevelLiters = value; }

    public Double getMinimumLevelLiters() { return minimumLevelLiters; }
    public void setMinimumLevelLiters(Double value) { this.minimumLevelLiters = value; }

    public Instant getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(Instant value) { this.lastUpdated = value; }
}
