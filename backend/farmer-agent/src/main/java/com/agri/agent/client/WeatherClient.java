package com.agri.agent.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherClient {
    private final RestClient client;

    public WeatherClient(RestClient.Builder builder,
                 @Value("${agri.services.weather-url}") String baseUrl) {
        client = builder.baseUrl(baseUrl).build();
    }

    public String get(Long id) {
        try {
            String result = client.get().uri("/api/weather/farm/%s/current", id)
                    .retrieve().body(String.class);
            return result == null ? "{}" : result;
        } catch (Exception e) {
            return "Service unavailable: " + e.getMessage();
        }
    }
}
