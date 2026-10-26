package com.agri.infrastructure.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "valves")
public class Valve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long farmId;
    private Long plotId;
    private Long deviceId;
    private String valveName;
    private String zoneName;
    private Boolean open = false;
    private Instant lastChangedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFarmId() { return farmId; }
    public void setFarmId(Long farmId) { this.farmId = farmId; }

    public Long getPlotId() { return plotId; }
    public void setPlotId(Long plotId) { this.plotId = plotId; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public String getValveName() { return valveName; }
    public void setValveName(String valveName) { this.valveName = valveName; }

    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }

    public Boolean getOpen() { return open; }
    public void setOpen(Boolean open) { this.open = open; }

    public Instant getLastChangedAt() { return lastChangedAt; }
    public void setLastChangedAt(Instant value) { this.lastChangedAt = value; }
}
