package com.agri.agent.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class FarmClient {
    private final RestClient client;

    public FarmClient(RestClient.Builder builder,
                 @Value("${agri.services.farm-url}") String baseUrl) {
        client = builder.baseUrl(baseUrl).build();
    }

    public String get(Long id) {
        try {
            String result = client.get().uri("/api/farms/%s", id)
                    .retrieve().body(String.class);
            return result == null ? "{}" : result;
        } catch (Exception e) {
            return "Service unavailable: " + e.getMessage();
        }
    }
}
