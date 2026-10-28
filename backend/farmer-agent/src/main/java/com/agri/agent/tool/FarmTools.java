package com.agri.agent.tool;

import com.agri.agent.client.FarmClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class FarmTools {
    private final FarmClient client;
    public FarmTools(FarmClient client){this.client=client;}
    @Tool(description="Get current farm information for a farm ID.")
    public String getFarmInformation(Long farmId){return client.get(farmId);}
}
