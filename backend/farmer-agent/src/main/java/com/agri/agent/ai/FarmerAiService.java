package com.agri.agent.ai;

import com.agri.agent.tool.FarmTools;
import com.agri.agent.tool.SensorTools;
import com.agri.agent.tool.WeatherTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class FarmerAiService {
    private final ChatClient client;
    private final PromptService prompts;
    public FarmerAiService(ChatClient client,PromptService prompts){
        this.client=client;this.prompts=prompts;
    }
    public String ask(String prompt,FarmTools farmTools,SensorTools sensorTools,WeatherTools weatherTools){
        return client.prompt().system(prompts.system()).user(prompt)
            .tools(farmTools,sensorTools,weatherTools).call().content();
    }
}
