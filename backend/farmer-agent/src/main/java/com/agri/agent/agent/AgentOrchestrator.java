package com.agri.agent.agent;

import com.agri.agent.ai.*;
import com.agri.agent.context.*;
import com.agri.agent.dto.*;
import com.agri.agent.memory.FarmMemoryService;
import com.agri.agent.rag.KnowledgeService;
import com.agri.agent.tool.*;
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
    private final FarmTools farmTools;
    private final SensorTools sensorTools;
    private final WeatherTools weatherTools;

    public AgentOrchestrator(AgentPlanner planner,AgentExecutor executor,
        FarmContextBuilder farm,CropContextBuilder crop,SensorContextBuilder sensor,
        WeatherContextBuilder weather,HistoricalContextBuilder history,
        KnowledgeService knowledge,PromptService prompts,FarmerAiService ai,
        ResponseService response,FarmMemoryService memory,FarmTools farmTools,
        SensorTools sensorTools,WeatherTools weatherTools){
        this.planner=planner;this.executor=executor;this.farm=farm;this.crop=crop;
        this.sensor=sensor;this.weather=weather;this.history=history;this.knowledge=knowledge;
        this.prompts=prompts;this.ai=ai;this.response=response;this.memory=memory;
        this.farmTools=farmTools;this.sensorTools=sensorTools;this.weatherTools=weatherTools;
    }

    public AgentResponse process(AgentRequest r){
        String id=(r.conversationId()==null||r.conversationId().isBlank())
            ?UUID.randomUUID().toString():r.conversationId();
        memory.saveConversation(id,r.farmerId(),r.farmId());
        memory.saveEvent(r.farmerId(),r.farmId(),r.plotId(),"FARMER_QUERY",r.message());
        try{
            executor.executePlan(planner.plan(r.message()));
            FarmContext c=new FarmContext(r.farmerId(),r.farmId(),r.plotId(),
                farm.build(r.farmId()),crop.build(r.farmId()),sensor.build(r.farmId()),
                weather.build(r.farmId()),history.build(r.farmId()));
            String k=knowledge.searchAsText(r.message()+" "+c.cropData());
            String p=prompts.user(c,k,r.message());
            String answer=response.clean(ai.ask(p,farmTools,sensorTools,weatherTools));
            memory.saveEvent(r.farmerId(),r.farmId(),r.plotId(),"AGENT_RESPONSE",answer);
            return new AgentResponse(id,answer,answer,"Farm services + sensor/weather data + RAG");
        }catch(Exception e){
            memory.saveEvent(r.farmerId(),r.farmId(),r.plotId(),"AGENT_ERROR",e.getMessage());
            return new AgentResponse(id,"I could not complete the farm analysis: "+e.getMessage(),
                "Verify connected farm services and try again.","Agent error");
        }
    }
}
