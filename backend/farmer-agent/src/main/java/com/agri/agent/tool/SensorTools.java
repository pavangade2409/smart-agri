package com.agri.agent.tool;

import com.agri.agent.client.SensorClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class SensorTools {
    private final SensorClient client;
    public SensorTools(SensorClient client){this.client=client;}
    @Tool(description="Get latest sensor data for a farm.")
    public String getLatestSensorData(Long farmId){return client.get(farmId);}
}
