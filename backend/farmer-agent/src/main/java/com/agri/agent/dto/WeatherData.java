package com.agri.agent.dto;
public record WeatherData(
    Double temperature, Double humidity, Double rainfallMm,
    Double windSpeed, String condition, String timestamp) {}
