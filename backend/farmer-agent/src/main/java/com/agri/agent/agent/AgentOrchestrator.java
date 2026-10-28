package com.agri.agent.agent;

import com.agri.agent.ai.*;
import com.agri.agent.context.*;
import com.agri.agent.dto.*;
import com.agri.agent.memory.FarmMemoryService;
import com.agri.agent.rag.KnowledgeService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AgentOrchestrator {
    private final AgentPlanner planner;
    private final AgentExecutor executor;
    private final FarmContextBuilder farm;
    private final CropContextBuilder crop;
    private final SensorContextBuilder sensor;
    private final WeatherContextBuilder weather;
    private final HistoricalContextBuilder history;
    private final KnowledgeService knowledge;
    private final PromptService prompts;
    private final FarmerAiService ai;
    private final ResponseService response;
    private final FarmMemoryService memory;

    public AgentOrchestrator(AgentPlanner planner,AgentExecutor executor,
        FarmContextBuilder farm,CropContextBuilder crop,SensorContextBuilder sensor,
        WeatherContextBuilder weather,HistoricalContextBuilder history,
        KnowledgeService knowledge,PromptService prompts,FarmerAiService ai,
        ResponseService response,FarmMemoryService memory){
        this.planner=planner;this.executor=executor;this.farm=farm;this.crop=crop;
        this.sensor=sensor;this.weather=weather;this.history=history;this.knowledge=knowledge;
        this.prompts=prompts;this.ai=ai;this.response=response;this.memory=memory;
    }

    public AgentResponse process(AgentRequest r, Long farmerId, String authorization){
        String id=(r.conversationId()==null||r.conversationId().isBlank())
            ?UUID.randomUUID().toString():r.conversationId();
        var farmData = farm.build(r.farmId(), farmerId, authorization);
        memory.saveConversation(id, farmerId, r.farmId());
        memory.saveEvent(farmerId, r.farmId(), null, "FARMER_QUERY", r.message());
        executor.executePlan(planner.plan(r.message()));

        String cropData;
        String sensorData;
        String weatherData;
        String historicalData;
        try { cropData = crop.build(r.farmId(), authorization); }
        catch (RuntimeException exception) { cropData = "Crop data unavailable."; }
        try { sensorData = sensor.build(r.farmId(), authorization); }
        catch (RuntimeException exception) { sensorData = "Sensor data unavailable."; }
        try { weatherData = weather.build(farmData, authorization); }
        catch (RuntimeException exception) { weatherData = "Weather data unavailable."; }
        try { historicalData = history.build(r.farmId(), farmerId); }
        catch (RuntimeException exception) { historicalData = "Historical data unavailable."; }

        FarmContext context = new FarmContext(
                farmerId,
                r.farmId(),
                null,
                farm.asJson(farmData),
                cropData,
                sensorData,
                weatherData,
                historicalData
        );
        String knowledgeText;
        try {
            knowledgeText = knowledge.searchAsText(r.message() + " " + context.cropData());
        } catch (RuntimeException exception) {
            knowledgeText = "No matching agricultural knowledge is currently available.";
        }
        String answer = response.clean(
                ai.ask(prompts.user(context, knowledgeText, r.message()), r.message())
        );
        memory.saveEvent(farmerId, r.farmId(), null, "AGENT_RESPONSE", answer);
        return new AgentResponse(
                id,
                answer,
                answer,
                "Farm, crop, sensor, NPK soil-health, and weather services; measurements include source timestamps."
        );
    }

}
