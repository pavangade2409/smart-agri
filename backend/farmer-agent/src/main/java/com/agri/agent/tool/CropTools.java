package com.agri.agent.tool;

import com.agri.agent.client.CropClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class CropTools {
    private final CropClient client;
    public CropTools(CropClient client){this.client=client;}
    @Tool(description="Get crop information for a farm.")
    public String getCropInformation(Long farmId){return client.get(farmId);}
}
