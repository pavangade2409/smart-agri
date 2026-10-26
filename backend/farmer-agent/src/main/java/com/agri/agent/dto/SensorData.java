package com.agri.agent.dto;
public record SensorData(
    Double soilMoisture, Double soilTemperature, Double soilPh,
    Double soilEc, Double tankLevel, String timestamp) {}
