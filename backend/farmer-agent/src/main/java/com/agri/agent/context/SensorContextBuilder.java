package com.agri.agent.context;

import com.agri.agent.client.SensorClient;
import org.springframework.stereotype.Component;

@Component
public class SensorContextBuilder {
    private final SensorClient client;
    public SensorContextBuilder(SensorClient client) { this.client=client; }
    public String build(Long farmId) { return client.get(farmId); }
}
