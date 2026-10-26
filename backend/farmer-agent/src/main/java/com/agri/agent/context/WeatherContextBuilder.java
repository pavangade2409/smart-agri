package com.agri.agent.context;

import com.agri.agent.client.WeatherClient;
import org.springframework.stereotype.Component;

@Component
public class WeatherContextBuilder {
    private final WeatherClient client;
    public WeatherContextBuilder(WeatherClient client) { this.client=client; }
    public String build(Long farmId) { return client.get(farmId); }
}
