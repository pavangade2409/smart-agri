package com.agri.agent.memory;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="recommendations")
public class Recommendation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private Long farmerId; private Long farmId; private Long plotId;
    @Column(columnDefinition="TEXT") private String recommendation;
    private String status; private Instant createdAt;
    @PrePersist void create(){createdAt=Instant.now();}
    public void setFarmerId(Long v){farmerId=v;}
    public void setFarmId(Long v){farmId=v;}
    public void setPlotId(Long v){plotId=v;}
    public void setRecommendation(String v){recommendation=v;}
    public void setStatus(String v){status=v;}
}
