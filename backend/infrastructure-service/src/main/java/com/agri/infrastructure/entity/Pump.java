package com.agri.infrastructure.entity;

import com.agri.infrastructure.enums.PumpStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "pumps")
public class Pump {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long farmId;

    @Column(nullable = false)
    private Long deviceId;

    @Column(nullable = false)
    private String pumpName;

    private Double powerKw;
    private Double flowRateLitersPerMinute;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PumpStatus status = PumpStatus.OFF;

    private Instant lastStartedAt;
    private Instant lastStoppedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFarmId() { return farmId; }
    public void setFarmId(Long farmId) { this.farmId = farmId; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public String getPumpName() { return pumpName; }
    public void setPumpName(String pumpName) { this.pumpName = pumpName; }

    public Double getPowerKw() { return powerKw; }
    public void setPowerKw(Double powerKw) { this.powerKw = powerKw; }

    public Double getFlowRateLitersPerMinute() { return flowRateLitersPerMinute; }
    public void setFlowRateLitersPerMinute(Double value) {
        this.flowRateLitersPerMinute = value;
    }

    public PumpStatus getStatus() { return status; }
    public void setStatus(PumpStatus status) { this.status = status; }

    public Instant getLastStartedAt() { return lastStartedAt; }
    public void setLastStartedAt(Instant value) { this.lastStartedAt = value; }

    public Instant getLastStoppedAt() { return lastStoppedAt; }
    public void setLastStoppedAt(Instant value) { this.lastStoppedAt = value; }
}
