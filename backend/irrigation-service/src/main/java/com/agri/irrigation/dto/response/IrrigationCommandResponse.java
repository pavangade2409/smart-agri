package com.agri.irrigation.dto.response;

import java.util.UUID;

import com.agri.irrigation.enums.DecisionStatus;
import com.agri.irrigation.enums.IrrigationStatus;

public class IrrigationCommandResponse {

    private UUID executionId;

    private UUID zoneId;

    private IrrigationStatus status;

    private DecisionStatus decision;

    private String message;

    public IrrigationCommandResponse() {
    }

    public UUID getExecutionId() {
        return executionId;
    }

    public void setExecutionId(UUID executionId) {
        this.executionId = executionId;
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public void setZoneId(UUID zoneId) {
        this.zoneId = zoneId;
    }

    public IrrigationStatus getStatus() {
        return status;
    }

    public void setStatus(IrrigationStatus status) {
        this.status = status;
    }

    public DecisionStatus getDecision() {
        return decision;
    }

    public void setDecision(
            DecisionStatus decision) {

        this.decision = decision;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}