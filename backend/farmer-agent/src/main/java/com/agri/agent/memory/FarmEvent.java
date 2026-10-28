package com.agri.agent.memory;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="farm_events")
public class FarmEvent {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long farmerId;
    private Long farmId;
    private Long plotId;
    @Column(nullable=false) private String eventType;
    @Column(columnDefinition="TEXT") private String eventData;
    private Instant eventTime;
    private Instant createdAt;

    @PrePersist void onCreate() {
        if (eventTime == null) eventTime = Instant.now();
        createdAt = Instant.now();
    }

    public Long getId(){return id;}
    public Long getFarmerId(){return farmerId;}
    public Long getFarmId(){return farmId;}
    public Long getPlotId(){return plotId;}
    public String getEventType(){return eventType;}
    public String getEventData(){return eventData;}
    public Instant getEventTime(){return eventTime;}
    public Instant getCreatedAt(){return createdAt;}
    public void setFarmerId(Long v){farmerId=v;}
    public void setFarmId(Long v){farmId=v;}
    public void setPlotId(Long v){plotId=v;}
    public void setEventType(String v){eventType=v;}
    public void setEventData(String v){eventData=v;}
}
