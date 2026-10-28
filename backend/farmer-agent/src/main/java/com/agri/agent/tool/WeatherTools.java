package com.agri.agent.tool;

import com.agri.agent.client.WeatherClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class WeatherTools {
    private final WeatherClient client;
    public WeatherTools(WeatherClient client){this.client=client;}
    @Tool(description="Get current weather data for a farm.")
    public String getCurrentWeather(Long farmId){return client.get(farmId);}
}
