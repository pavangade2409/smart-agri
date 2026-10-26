package com.agri.agent.tool;

import com.agri.agent.client.IrrigationClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class IrrigationTools {
    private final IrrigationClient client;
    public IrrigationTools(IrrigationClient client){this.client=client;}
    @Tool(description="Get current irrigation service status for a farm. Read-only.")
    public String getIrrigationStatus(Long farmId){return client.get(farmId);}
}
