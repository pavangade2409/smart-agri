package com.agri.irrigation.decision;

import com.agri.irrigation.enums.DecisionStatus;

public class IrrigationDecision {

    private DecisionStatus status;

    private String reason;

    private Integer recommendedDurationMinutes;

    public IrrigationDecision() {
    }

    public IrrigationDecision(
            DecisionStatus status,
            String reason,
            Integer recommendedDurationMinutes) {

        this.status = status;
        this.reason = reason;
        this.recommendedDurationMinutes =
                recommendedDurationMinutes;
    }

    public DecisionStatus getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public Integer getRecommendedDurationMinutes() {
        return recommendedDurationMinutes;
    }

    public void setStatus(
            DecisionStatus status) {

        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRecommendedDurationMinutes(
            Integer value) {

        this.recommendedDurationMinutes = value;
    }
}