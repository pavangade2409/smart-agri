package com.agri.agent.memory;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="agent_decisions")
public class AgentDecision {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private Long farmerId; private Long farmId; private Long plotId;
    private String decisionType;
    @Column(columnDefinition="TEXT") private String decisionData;
    private Instant createdAt;
    @PrePersist void create(){createdAt=Instant.now();}
    public void setFarmerId(Long v){farmerId=v;}
    public void setFarmId(Long v){farmId=v;}
    public void setPlotId(Long v){plotId=v;}
    public void setDecisionType(String v){decisionType=v;}
    public void setDecisionData(String v){decisionData=v;}
}
