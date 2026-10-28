package com.agri.agent.context;

import com.agri.agent.client.FarmClient;
import org.springframework.stereotype.Component;

@Component
public class FarmContextBuilder {
    private final FarmClient client;
    public FarmContextBuilder(FarmClient client) { this.client=client; }
    public String build(Long farmId) { return client.get(farmId); }
}
