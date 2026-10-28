package com.agri.agent.memory;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="conversations")
public class Conversation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true) private String conversationId;
    private Long farmerId;
    private Long farmId;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist void create(){createdAt=Instant.now();updatedAt=createdAt;}
    @PreUpdate void update(){updatedAt=Instant.now();}

    public void setConversationId(String v){conversationId=v;}
    public void setFarmerId(Long v){farmerId=v;}
    public void setFarmId(Long v){farmId=v;}
}
