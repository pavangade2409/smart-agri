package com.agri.agent.client;

public record WeatherContext(
        Double temperature,
        Double humidity,
        Double precipitation,
        Double windSpeed,
        String condition
) {
}