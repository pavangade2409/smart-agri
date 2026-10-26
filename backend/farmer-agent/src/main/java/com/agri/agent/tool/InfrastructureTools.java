package com.agri.agent.tool;

import com.agri.agent.client.InfrastructureClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class InfrastructureTools {
    private final InfrastructureClient client;
    public InfrastructureTools(InfrastructureClient client){this.client=client;}
    @Tool(description="Get current infrastructure and pump status for a farm. Read-only.")
    public String getInfrastructureStatus(Long farmId){return client.get(farmId);}
}
