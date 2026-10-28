package com.agri.agent.dto;

public record FarmContext(
    Long farmerId, Long farmId, Long plotId,
    String farmData, String cropData, String sensorData,
    String weatherData, String historicalData) {

    public String asText() {
        return String.format("""
            Farmer ID: %s
            Farm ID: %s
            Plot ID: %s
            Farm data: %s
            Crop data: %s
            Sensor data: %s
            Weather data: %s
            Historical data: %s
            """, farmerId, farmId, plotId, farmData, cropData,
            sensorData, weatherData, historicalData);
    }
}
