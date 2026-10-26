package com.agri.irrigation.client;

public class SoilMoistureData {

    private Double moisturePercentage;

    private String measuredAt;

    public SoilMoistureData() {
    }

    public Double getMoisturePercentage() {
        return moisturePercentage;
    }

    public void setMoisturePercentage(
            Double moisturePercentage) {

        this.moisturePercentage =
                moisturePercentage;
    }

    public String getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(String measuredAt) {
        this.measuredAt = measuredAt;
    }
}