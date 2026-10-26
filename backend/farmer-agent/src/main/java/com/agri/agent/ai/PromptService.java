package com.agri.agent.ai;
import com.agri.agent.dto.FarmContext;
import org.springframework.stereotype.Service;

@Service
public class PromptService {
    public String system(){
        return """
        You are a Smart Agriculture Farmer AI Agent.
        Use farm, crop, sensor, weather, historical and retrieved agricultural data.
        Never invent sensor or weather values.
        Separate facts from recommendations.
        Never claim a pump or valve operated unless a service confirms it.
        Hardware actions must go through controlled irrigation/infrastructure workflows.
        Give practical, simple farmer-friendly answers.
        """;
    }
    public String user(FarmContext c,String knowledge,String message){
        return String.format("""
        FARM CONTEXT
        %s

        AGRICULTURAL KNOWLEDGE
        %s

        FARMER QUESTION
        %s

        Provide Facts, Recommendation, and Safety Note when relevant.
        """,c.asText(),knowledge,message);
    }
}
